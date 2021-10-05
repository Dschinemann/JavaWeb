const button = document.getElementById("tableForm:pesquisa_ordem");
const tabela = document.getElementById("tableForm:tabelaModal");
const formDash = document.getElementById("two");

formDash.addEventListener("submit", e => e.preventDefault())

const container = document.getElementById("containerBoxes");

function saldoDoBox2(box) {
    switch (box) {
        case 1:
            container.classList.remove("armazenagem");
            container.classList.add("bg");
            modal.style.display = "block";
            break;
        case 2:
            container.classList.remove("armazenagem");
            container.classList.add("bg");
            modal.style.display = "block";
            break;
        case 3:
            container.classList.remove("armazenagem");
            container.classList.add("bg");
            modal.style.display = "block";
            break;
        case 4:
            container.classList.remove("armazenagem");
            container.classList.add("bg");
            modal.style.display = "block";
            break;

        default:
            break;
    }
}

function procurarPedido(){
    const inputPedido = document.getElementById("tableForm:pesquisa_pedido");
    if(inputPedido.value === ""){
        return alert('Informe um pedido válido')
    }
    let tr = tabela.querySelectorAll("tr")
    for (let index = 1; index < tr.length; index++) {
        let element = tr.item(index)
        if(element.children.item(2).textContent !== inputPedido.value ){
            tr.item(index).style.display = "none"
        }
    }
}

function procurarNotaDaOrdem(){
    const inputNotaFiscal = document.getElementById("tableForm:pesquisa_nota");
    if(inputNotaFiscal.value === ""){
        return alert('Informe uma Nota Fiscal válida')
    }
    let tr = tabela.querySelectorAll("tr")
    for (let index = 1; index < tr.length; index++) {
        let element = tr.item(index)
        if(element.children.item(3).textContent !== inputNotaFiscal.value ){
            tr.item(index).style.display = "none"
        }
    }
}

function fecharModalOrdem(){
    modal.style.display = "none";
    container.classList.remove("bg");
    container.classList.add("armazenagem");
}



function exportTableToExcel(tableID, filename = ''){
    var downloadLink;
    var dataType = 'application/vnd.ms-excel';
    var tableSelect = document.getElementById(tableID);
    var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');

    // Specify file name
    filename = filename?filename+'.xls':'excel_data.xls';

    // Create download link element
    downloadLink = document.createElement("a");

    document.body.appendChild(downloadLink);

    if(navigator.msSaveOrOpenBlob){
        var blob = new Blob(['\ufeff', tableHTML], {
            type: dataType
        });
        navigator.msSaveOrOpenBlob( blob, filename);
    }else{
        // Create a link to the file
        downloadLink.href = 'data:' + dataType + ', ' + tableHTML;

        // Setting the file name
        downloadLink.download = filename;

        //triggering the function
        downloadLink.click();
    }
}

const inputPedido = document.getElementById("tableForm:pesquisa_pedido");
const inputNotaFiscal = document.getElementById("tableForm:pesquisa_nota");

inputPedido.addEventListener("keyup",()=>{
    let tr = tabela.querySelectorAll("tr")
    if(inputPedido.value === ""){
        for (let index = 1; index < tr.length; index++) {
            tr.item(index).style.display = ""
        }
    }
})

inputNotaFiscal.addEventListener("keyup",()=>{
    let tr = tabela.querySelectorAll("tr")
    if(inputNotaFiscal.value === ""){
        for (let index = 1; index < tr.length; index++) {
            tr.item(index).style.display = ""
        }
    }
})