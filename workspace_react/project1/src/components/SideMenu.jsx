import React from "react";

const SideMenu = () => {
  function click(){
    console.log(3);
  }

  return (
    <>
      <div>SideMenu</div>
      <button type="button" onClick={click}>사이드버튼1</button>
      <button type="button" onClick={click}>사이드버튼2</button>
    </>
  );
};

export default SideMenu;
