import React, { useState } from "react";

const Test5 = () => {

  const [people, setPeople] = useState({
    name: "김자바",
    age: 20,
    address: "울산시",
  });

  const rePeople = {
    ...people,
  };

  setPeople(rePeople);

  return (
    <>
      <div>Test5</div>
      <div>
        이름 : {people.name}
        <br />
        나이 : {people.age}
        <br />
        주소 : {people.address}
      </div>

      <button
        type="button"
        onClick={() => {
          setPeople(people.name === "김자바" ? "홍길동" : null);
        }}
      >
        이름을 홍길동으로 변경
      </button>

      <button
        type="button"
        onClick={() => {
          setPeople(people.age === 20 ? 30 : null);
        }}
      >
        나이를 30으로 변경
      </button>

      <button
        type="button"
        onClick={() => {
          setPeople(people.address === "울산시" ? "서울시" : null);
        }}
      >
        주소를 서울시로 변경
      </button>
    </>
  );
};

export default Test5;
