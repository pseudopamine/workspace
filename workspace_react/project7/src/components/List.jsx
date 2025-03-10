import React, { useEffect } from "react";
import Detail from "./Detail";
import axios from "axios";

const List = (props) => {
  


  return (
    <>
      <tr>
        <td>{props.info1.num}</td>
        <td type="button" onClick={(e) => {
          //제목을 클릭하면 모든 데이터가 저장된 리스트에서 클릭한 데이터만 찾아서(리스트에 있는 모든 게시글 정보 vs 클릭한 제목을 갖는 게시글의 번호)
          //detail이라는 state변수에 넣어준다.
          props.getDetail(props.i);
        }}>{props.info1.title}</td>
        <td>{props.info1.name}</td>
        <td>{props.info1.cnt}</td>
      </tr>
    </>
  );
};

export default List;
