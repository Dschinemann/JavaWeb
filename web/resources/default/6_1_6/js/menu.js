const menu = document.getElementById("one");
const displayMenu = document.getElementById("menuitens");
const checkBox = document.getElementById("togle");
const voltarMenu = document.getElementById("togle-voltar");
//const conteudo = document.getElementById("tabelas");
//const container = document.getElementById("conteudo");

menu.addEventListener("click", e => {
    const width = window.outerWidth

    if (width > 700) {
        checkBox.checked = !checkBox.checked;
        if (checkBox.checked) {
            displayMenu.style.transform = "translateX(0)";
        } else {
            displayMenu.style.transform = "translateX(-100%)";
        }
    }

    if(width < 700){
        checkBox.checked = !checkBox.checked;
        if (checkBox.checked) {
            displayMenu.style.transform = "translateX(0)";
        } else {
            displayMenu.style.transform = "translateX(-100%)";
        }
    }
});

displayMenu.addEventListener("mouseleave", () => {
    const width = window.outerWidth
    if(width > 700){
        checkBox.checked = !checkBox.checked;
        displayMenu.style.transform = "translateX(-100%)";
    }

});

voltarMenu.addEventListener("click",()=>{
    checkBox.checked = !checkBox.checked;
    if (checkBox.checked) {
        displayMenu.style.transform = "translateX(0)";
    } else {
        displayMenu.style.transform = "translateX(-100%)";
    }
})

window.addEventListener("load",()=>{
    const width = window.outerWidth
    if(width < 700){
        displayMenu.style.transform = "translateX(-100%)";
    }
})





