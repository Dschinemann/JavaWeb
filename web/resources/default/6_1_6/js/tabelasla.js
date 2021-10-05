
const tabela = document.querySelector(".tabelaexemplo");
const cliente = document.getElementById("formregrasla:cliente");
const prazoRecebimento = document.getElementById("formregrasla:prazo")
const prazoExpedicao = document.getElementById("formregrasla:prazoexp")


cliente.addEventListener("keyup", () => {
    let linhaEntrada = tabela.querySelectorAll("tr")[2]
    let linhaSaida = tabela.querySelectorAll("tr")[1]
    linhaEntrada.cells[0].textContent = cliente.value
    linhaSaida.cells[0].textContent = cliente.value
})

prazoRecebimento.addEventListener("keyup", () => {
    let linhaEntrada = tabela.querySelectorAll("tr")[2]
    linhaEntrada.cells[6].textContent = `Expedição ${prazoRecebimento.value}`
})

prazoExpedicao.addEventListener("keyup", () => {
    let linhaSaida = tabela.querySelectorAll("tr")[1]
    linhaSaida.cells[6].textContent = `Expedição ${prazoExpedicao.value}`
})

function validarSla() {
    const tipoMov = document.getElementById("formregrasla:tipomov").value;
    const prazoEtq = document.getElementById("formregrasla:prazoetq")
    const prazoRec = document.getElementById("formregrasla:prazorec")
    const prazoExp = document.getElementById("formregrasla:prazoex")

    let linhaEntrada = tabela.querySelectorAll("tr")[2]
    let linhaSaida = tabela.querySelectorAll("tr")[1]

    let sdtInclusao = document.getElementById("sdtinclusao").value;
    let shrInclusao = document.getElementById("shrfinal").value;


    let edtInclusao = document.getElementById("edtinclusao").value;
    let ehrFinalizacao = document.getElementById("ehrfinalizacao").value;

    let SlaRecebimento = String(prazoRecebimento.value).split("+")[1] == undefined ? 0 : String(prazoRecebimento.value).split("+")[1]
    let SlaExpedicao = String(prazoExpedicao.value).split("+")[1] == undefined ? 0 : String(prazoExpedicao.value).split("+")[1]

    let prazoExtraRec = prazoRec.value == "" ? 0 : prazoRec.value
    let prazoExtraExp = prazoExp.value == "" ? 0 : prazoExp.value

    linhaEntrada.cells[7].textContent = prazoExtraRec;
    linhaSaida.cells[7].textContent = prazoExtraExp;

    let prazoExtraEtq = prazoEtq.value == "" ? 0 : prazoEtq.value

    if (tipoMov == "saída") {
        return linhaSaida.cells[7].textContent = (parseInt(linhaSaida.cells[7].textContent) + parseInt(prazoExtraEtq))
    } else {
        linhaEntrada.cells[7].textContent = (parseInt(linhaEntrada.cells[7].textContent) + parseInt(prazoExtraEtq))
    }
    let totalDeExtraRec = linhaEntrada.cells[7].textContent;
    let totalDeExtraExp = linhaSaida.cells[7].textContent;

    calculoRecebimento(edtInclusao, ehrFinalizacao, SlaRecebimento, totalDeExtraRec);
    calculoExpedicao(sdtInclusao, shrInclusao, SlaExpedicao, totalDeExtraExp);

}

function calculoRecebimento(edtInclusao, ehrFinalizacao, slaRecebimento, prazoExtra = 0) {
    let hrCorte = document.getElementById("formregrasla:hrreceb").value;
    let linhaEntrada = tabela.querySelectorAll("tr")[2];

    let hj = new Date();

    let dtRegistro = new Date(edtInclusao)
    let dtFinalizado = new Date(ehrFinalizacao)

    let horaCorretaInclusao = dtRegistro.getHours().toString().length = 1 ? `0${dtRegistro.getHours()}` : dtRegistro.getHours()
    let horaCorretaFinal = dtRegistro.getHours().toString().length = 1 ? `0${dtRegistro.getHours()}` : dtRegistro.getHours()

    let minCorretaInclusao = dtRegistro.getMinutes().toString().length = 1 ? `0${dtRegistro.getMinutes()}` : dtRegistro.getMinutes()
    let minCorretaFinal = dtRegistro.getMinutes().toString().length = 1 ? `0${dtRegistro.getMinutes()}` : dtRegistro.getMinutes()

    const horaRegistro = `${horaCorretaInclusao}:${minCorretaInclusao}`.split(":")
    const horaFinalizado = `${horaCorretaFinal}:${minCorretaFinal}`.split(":")
    const horaDoCorte = String(hrCorte).split(":")

    let dataComparaHoraRegistro = new Date(hj.getFullYear(), hj.getMonth(), hj.getDate(), horaRegistro[0], horaRegistro[1])
    let dataComparaHoraDoCorte = new Date(hj.getFullYear(), hj.getMonth(), hj.getDate(), horaDoCorte[0], horaDoCorte[1])

    if (dtRegistro.getFullYear == dtFinalizado.getFullYear) {
        if (dtFinalizado.getDate() <= dtRegistro.getDate() + (parseInt(slaRecebimento) + parseInt(prazoExtra))) {
            linhaEntrada.cells[8].textContent = "OK"
        } else {
            if (dataComparaHoraRegistro <= dataComparaHoraDoCorte) {
                linhaEntrada.cells[8].textContent = "Fora do SLA"
            } else {
                if (dtFinalizado.getDate() <= dtRegistro.getDate() + (parseInt(slaRecebimento) + 1)) {
                    linhaEntrada.cells[8].textContent = "OK"
                } else {
                    linhaEntrada.cells[8].textContent = "verifcar outro Filtros";
                }
            }
        }
    }
}

function calculoExpedicao(sdtInclusao, shrInclusao, SlaExpedicao, prazoExtra = 0) {
    let hrCorte = document.getElementById("formregrasla:hrcorte").value;
    let linhaSaida = tabela.querySelectorAll("tr")[1];

    let hj = new Date();

    let dtRegistro = new Date(sdtInclusao)
    let dtFinalizado = new Date(shrInclusao)

    let horaCorretaInclusao = dtRegistro.getHours().toString().length = 1 ? `0${dtRegistro.getHours()}` : dtRegistro.getHours()
    let horaCorretaFinal = dtRegistro.getHours().toString().length = 1 ? `0${dtRegistro.getHours()}` : dtRegistro.getHours()

    let minCorretaInclusao = dtRegistro.getMinutes().toString().length = 1 ? `0${dtRegistro.getMinutes()}` : dtRegistro.getMinutes()
    let minCorretaFinal = dtRegistro.getMinutes().toString().length = 1 ? `0${dtRegistro.getMinutes()}` : dtRegistro.getMinutes()

    const horaRegistro = `${horaCorretaInclusao}:${minCorretaInclusao}`.split(":")
    //const horaFinalizado = `${horaCorretaFinal}:${minCorretaFinal}`.split(":")
    const horaDoCorte = String(hrCorte).split(":")

    let dataComparaHoraRegistro = new Date(hj.getFullYear(), hj.getMonth(), hj.getDate(), horaRegistro[0], horaRegistro[1])
    let dataComparaHoraDoCorte = new Date(hj.getFullYear(), hj.getMonth(), hj.getDate(), horaDoCorte[0], horaDoCorte[1])

    if (dtFinalizado.getDate() <= dtRegistro.getDate() + (parseInt(SlaExpedicao)) + parseInt(prazoExtra) ) {
        linhaSaida.cells[8].textContent = "OK"
    } else {
        if (dataComparaHoraRegistro <= dataComparaHoraDoCorte) {
            linhaSaida.cells[8].textContent = "Fora do SLA"
        } else {
            if (dtFinalizado.getDate() <= dtRegistro.getDate() + (parseInt(SlaExpedicao) + 1)) {
                linhaSaida.cells[8].textContent = "OK"
            } else {
                linhaSaida.cells[8].textContent = "verifcar outro Filtros";
            }
        }
    }
}
/*
menu.addEventListener("click", e => {
    checkBox.checked = !checkBox.checked;
    if (checkBox.checked) {
        displayMenu.style.display = "block";
    } else {
        displayMenu.style.display = "none";
    }
})

displayMenu.addEventListener("mouseleave", () => {
    displayMenu.style.display = "none";
})*/

