
from http.server import BaseHTTPRequestHandler, HTTPServer
from picamera2 import Picamera2
from threading import Condition
import io

class StreamingOutput:
    def __init__(self):
        self.frame = None
        self.buffer = io.BytesIO()
        self.condition = Condition()

    def write(self, buf):
        if buf.startswith(b'\xff\xd8'):
            self.buffer.truncate()
            with self.condition:
                self.frame = self.buffer.getvalue()
                self.condition.notify_all()
            self.buffer.seek(0)
        return self.buffer.write(buf)

class StreamingHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        if self.path == '/':
            self.send_response(200)
            self.send_header('Content-type', 'text/html')
            self.end_headers()
            self.wfile.write(b"""
                <html>
                <head>
                    <title>Raspberry Pi Camera</title>
                </head>
                <body>
                    <h1>Raspberry Pi Camera Stream</h1>
                    <img src="/stream.mjpg" />
                </body>
                </html>
            """)
        elif self.path == '/stream.mjpg':
            self.send_response(200)
            self.send_header('Age', 0)
            self.send_header('Cache-Control', 'no-cache, private')
            self.send_header('Pragma', 'no-cache')
            self.send_header('Content-Type', 'multipart/x-mixed-replace; boundary=FRAME')
            self.end_headers()
            try:
                while True:
                    with output.condition:
                        output.condition.wait()
                        frame = output.frame
                    self.wfile.write(b'--FRAME\r\n')
                    self.send_header('Content-Type', 'image/jpeg')
                    self.send_header('Content-Length', str(len(frame)))
                    self.end_headers()
                    self.wfile.write(frame)
                    self.wfile.write(b'\r\n')
            except Exception as e:
                print(f"Removed streaming client {self.client_address}: {str(e)}")
        else:
            self.send_error(404)
            self.end_headers()

class StreamingServer(HTTPServer):
    allow_reuse_address = True

# 메인 실행 코드
picam2 = Picamera2()
picam2.configure(picam2.create_video_configuration(main={"size": (640, 480)}))
output = StreamingOutput()
picam2.start_recording(output, format='mjpeg')

try:
    address = ('', 8000)
    server = StreamingServer(address, StreamingHandler)
    print("서버가 실행 중입니다. http://<라즈베리파이_IP>:8000 에 접속하세요.")
    server.serve_forever()
finally:
    picam2.stop_recording()
