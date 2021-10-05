window.addEventListener("beforeunload",()=>{
    const spinner = document.getElementById("spinner")
    if(spinner != null){
        spinner.style.display = "flex"
    }
})

window.addEventListener("load",()=>{
    const spinner = document.getElementById("spinner")
    if(spinner != null){
        spinner.style.display = "none"
    }
})

window.addEventListener("ended",()=>{
    const spinner = document.getElementById("spinner")
    if(spinner != null){
        spinner.style.display = "none"
    }
})
