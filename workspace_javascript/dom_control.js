function test(){
  const ul_tag = document.querySelector('.my-ul');

  for(let i = 0 ; i < 2000 ; i++){
    ul_tag.innerHTML = ul_tag.innerHTML + `<li>${i}</li>`;
  }
}