import React from "react";

const Map_2 = () => {
  const arr = ['자장면', '짬뽕', '탕수육']
  return (
    <>
      <ul>
        {
          arr.map((food, i) => {
            return (
              <li key={i}>{food}</li>
            )
          })
        }
      </ul>
    </>
  );
};

export default Map_2;
