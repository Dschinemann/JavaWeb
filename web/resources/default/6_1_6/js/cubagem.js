const procurarOrdem = document.querySelector(".btn");
const modal = document.getElementById("modal");

procurarOrdem.addEventListener("click", e => e.preventDefault())

function showTable(){
    container.classList.remove("content")
    container.classList.add("bg")
    modal.style.display = "block"
}


function fecharModal() {
    modal.style.display = "none"
    container.classList.remove("bg")
    container.classList.add("content")
}