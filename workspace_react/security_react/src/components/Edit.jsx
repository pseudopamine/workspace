// React 회원 정보 수정 폼 예시 (JWT 기반 인증 사용)
import { useEffect, useState } from "react";
import axios from "axios";

const UserEditForm = () => {
  const [userInfo, setUserInfo] = useState({
    userId: "",
    userName: "",
    userEmail: "",
  });

  const token = localStorage.getItem("accessToken");

  useEffect(() => {
    axios
      .get("http://localhost:8080/user/profile", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => {
        setUserInfo(res.data);
      })
      .catch((err) => {
        console.error("프로필 정보를 가져오는 중 오류 발생:", err);
      });
  }, [token]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUserInfo((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .put("http://localhost:8080/user/update", userInfo, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then(() => {
        alert("회원 정보가 수정되었습니다.");
      })
      .catch((err) => {
        console.error("정보 수정 실패:", err);
        alert("정보 수정 중 오류가 발생했습니다.");
      });
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-4 max-w-md mx-auto p-4">
      <h2 className="text-xl font-semibold">회원 정보 수정</h2>
      <div>
        <label className="block text-sm font-medium">아이디</label>
        <input
          type="text"
          name="userId"
          value={userInfo.userId}
          onChange={handleChange}
          className="mt-1 block w-full border p-2 rounded"
          disabled
        />
      </div>
      <div>
        <label className="block text-sm font-medium">이름</label>
        <input
          type="text"
          name="userName"
          value={userInfo.userName}
          onChange={handleChange}
          className="mt-1 block w-full border p-2 rounded"
        />
      </div>
      <div>
        <label className="block text-sm font-medium">이메일</label>
        <input
          type="email"
          name="userEmail"
          value={userInfo.userEmail}
          onChange={handleChange}
          className="mt-1 block w-full border p-2 rounded"
        />
      </div>
      <button
        type="submit"
        className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
      >
        저장
      </button>
    </form>
  );
};

export default UserEditForm;
