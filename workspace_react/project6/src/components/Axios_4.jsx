import axios from "axios";
import React, { useEffect, useState } from "react";
import './Axios_4.css'

const Axios_4 = () => {
  //서버에서 넘어오는 데이터를 저장하기 위한 state 변수 생성
  const [personList, setPersonList] = useState([]);

  useEffect(() => {
    axios.get('/api/t4')
        .then((res) => {
          console.log('통신 성공');
          console.log(res.data);
          setPersonList(res.data)
        })
        .catch((error) => {
          console.log(error);
        });
  }, []);
  
  return (
    <>
      <div>Axios_4</div>
      <table>
        <thead>
          <tr>
            <td>이름</td>
            <td>나이</td>
            <td>주소</td>
          </tr>
        </thead>
        <tbody>
          {
            personList.map((personInfo, i) => {
              return (
                <tr key={i}>
                  <td>{personInfo.name}</td>
                  <td>{personInfo.age}</td>
                  <td>{personInfo.addr}</td>
                </tr>
              );
            })
          }
        </tbody>
      </table>
    </>
  );
};

export default Axios_4;
