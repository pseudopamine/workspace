import React from "react";

const Map_1 = () => {
  const arr1 = [1, 2, 3, 4, 5];

  return (
    <>
      {
        arr1.map((num, i) => {
          return (
            <div key={i}>안녕하세요</div>
            // <div>안녕하세요</div>
            //오류 표시가 나는 이유 : 배열에 있는 데이터 하나하나는 반드시 속성키를 가져야하는데
            //반복적으로 그려지는 태그들을 구분하기 위해 속성키가 필요
          )
        })
      }
    </>
  );
};

export default Map_1;
