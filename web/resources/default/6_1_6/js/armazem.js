const width = window.outerWidth;
google.charts.load('current', {'packages': ['corechart']});

if (width > 700) {

    google.charts.setOnLoadCallback(drawChart);
    google.charts.setOnLoadCallback(drawChart1);
    google.charts.setOnLoadCallback(drawChart2);
}else{

    google.charts.setOnLoadCallback(drawChartLineMobile);
    google.charts.setOnLoadCallback(drawChartColumnChart1);
    google.charts.setOnLoadCallback(drawChartColumnChart2);

}

function limparDados(tipo) {
    const procuraNF = document.getElementById("tableForm:procurarNF");
    const procuraItem = document.getElementById("tableForm:procurar_item");
    const procuraLote = document.getElementById("tableForm:procurar_lote");

    const table = document.getElementById("tableForm:tabelaModal");
    const tr = table.querySelectorAll("tr");
    switch (tipo) {
        case "item":
            if (procuraItem.value === "") {
                for (let index = 0; index < tr.length; index++) {
                    tr.item(index).style.display = '';
                }
            }
            break;
        case "lote":
            if (procuraLote.value === "") {
                for (let index = 0; index < tr.length; index++) {
                    tr.item(index).style.display = '';
                }
            }
            break;
        case "nf":
            if (procuraNF.value === "") {
                for (let index = 0; index < tr.length; index++) {
                    tr.item(index).style.display = '';
                }
            }
            break;
    }
}

window.addEventListener("resize", () => {
    const width = window.outerWidth;
    if (width > 700) {
        drawChart()
        drawChart1()
        drawChart2()
    }else{
        drawChartLineMobile();
        drawChartColumnChart1();
        drawChartColumnChart2();
    }

});

function drawChartLineMobile(){

    const width = window.outerWidth;
    let tamanhoGrafico = 300;

    if(width > 460){
        tamanhoGrafico = '50%';
    }

    const novoArray = array.map((ele, i) => {
        const entrada = i === 0 ? ele[1] : parseInt(ele[1]);
        const saida = i === 0 ? ele[2] : parseInt(ele[2]);
        return [
            ele[0], entrada, saida
        ]
    })
    let data = google.visualization.arrayToDataTable(novoArray);
    let options = {
        chart: {
            title: 'Movimentação',
        },
        colors: ['#1b7b9e', '#d95f02'],
        legend:{
            position: 'bottom',
        },
        hAxis: {
            textPosition: 'none' },
        vAxis: { textPosition: 'none' },
        chartArea:{
            width:"90%"
        },
        width:tamanhoGrafico
    };
    let chart = new google.visualization.ColumnChart(document.getElementById('curve_chart'));
    chart.draw(data, options);
}

function drawChartColumnChart1(){
    const width = window.outerWidth;
    let tamanhoGrafico = 300;

    if(width > 360){
        tamanhoGrafico = '50%';
    }

    const novoArrayOcupacaoMes = arrayOcupacaoMes.map((ele, i) => {
        {
            const quantidade = i === 0 ? ele[1] : parseInt(ele[1]);
            return [
                ele[0], quantidade
            ]
        }
    })

    let data = google.visualization.arrayToDataTable(novoArrayOcupacaoMes);
    let options = {
        chart: {
            title: 'Ocupação',
        },
        colors: ['#1b7b9e', '#d95f02'],
        legend:{
            position: 'bottom',
            textStyle:{
                fontSize: 10
            }
        },
        bar: { groupWidth: "80%" },
        hAxis: { textPosition: 'none' },
        vAxis: { textPosition: 'none' },
        width:tamanhoGrafico,
        chartArea:{
            width:"90%"
        },
    };
    let chart = new google.visualization.ColumnChart(document.getElementById('curve_chart1'));

    chart.draw(data, options);

}

function drawChartColumnChart2(){
    const width = window.outerWidth;
    let tamanhoGrafico = 300;

    if(width > 360){
        tamanhoGrafico = '50%';
    }

    const novoArrayOcupacaoDiaria = arrayOcupDiario.map((ele, i) => {
        {
            const quantidade = i === 0 ? ele[1] : parseInt(ele[1]);
            return [
                ele[0], quantidade
            ]
        }
    })

    let data = google.visualization.arrayToDataTable(novoArrayOcupacaoDiaria);
    let options = {
        chart: {
            title: 'Ocupação Diária',
        },
        colors: ['#1b7b9e', '#d95f02'],
        legend:{
            position: 'bottom',
        },
        bar: { groupWidth: "80%" },
        hAxis: { textPosition: 'none' },
        vAxis: { textPosition: 'none' },
        width:tamanhoGrafico,
        chartArea:{
            width:"90%"
        },
    };
    let chart = new google.visualization.ColumnChart(document.getElementById('curve_chart2'));
    chart.draw(data, options);
}

function drawChart() {
    const width = window.outerWidth;
    let mostraLegenda = false;

    if (width < 700) {
        mostraLegenda = true
    }

    const novoArray = array.map((ele, i) => {
        const entrada = i === 0 ? ele[1] : parseInt(ele[1]);
        const saida = i === 0 ? ele[2] : parseInt(ele[2]);
        return [
            ele[0], entrada, saida
        ]
    })
    var data = google.visualization.arrayToDataTable(novoArray);

    let hAxis = mostraLegenda ? {/* */
        textStyle: {
            color: '#1f4e4e',
            fontSize: 10,
            bold: true,
            italic: true
        }
    } : {
        title:"",
        textPosition:"none"
    }

    var options = {
        legend: {position: 'bottom'},
        tooltip: {isHtml: true},
        colors: ['#1b7b9e', '#d95f02'],
        backgroundColor: '#fcfcfd',
        hAxis: hAxis,
        width: '100%',
        height:'100%',
    };

    var chart1 = new google.visualization.ColumnChart(document.getElementById('curve_chart'));
    chart1.draw(data, options);
}

function drawChart1() {
    const width = window.outerWidth;
    let mostraLegenda = false;

    if (width < 700) {
        mostraLegenda = true
    }

    let hAxis = mostraLegenda ? {/* */
        textStyle: {
            color: '#1f4e4e',
            fontSize: 10,
            bold: true,
            italic: true
        }
    } : {
        title:"",
        textPosition:"none"
    }

    const novoArrayOcupacaoMes = arrayOcupacaoMes.map((ele, i) => {
        {
            const quantidade = i === 0 ? ele[1] : parseInt(ele[1]);
            return [
                ele[0], quantidade
            ]
        }
    })

    let data1 = google.visualization.arrayToDataTable(novoArrayOcupacaoMes);
    let options1 = {
        title: "OCUPAÇÃO",
        legend: {position: 'bottom'},
        tooltip: {isHtml: true},
        colors: ['#1f4e4e', '#437f7f'],
        backgroundColor: '#fcfcfd',
        hAxis: hAxis,
        width: '100%'
    };
    let chart2 = new google.visualization.LineChart(document.getElementById('curve_chart1'));
    chart2.draw(data1, options1);
}

function drawChart2() {
    const novoArrayOcupacaoDiaria = arrayOcupDiario.map((ele, i) => {
        {
            const quantidade = i === 0 ? ele[1] : parseInt(ele[1]);
            return [
                ele[0], quantidade
            ]
        }
    })

    let data2 = google.visualization.arrayToDataTable(novoArrayOcupacaoDiaria);
    let options2 = {
        title: "OCUPAÇÃO DIÁRIA",
        legend: "none",
        tooltip: {isHtml: true},
        colors: ['#1f4e4e', '#437f7f'],
        backgroundColor: '#fcfcfd',
        width: '100%',
        hAxis: { textPosition: 'none' },
    };
    let chart3 = new google.visualization.LineChart(document.getElementById('curve_chart2'));
    chart3.draw(data2, options2);
}

google.charts.load('current', {'packages': ['corechart']});
google.charts.setOnLoadCallback(drawChartPie);

function drawChartPie() {
    let data = google.visualization.arrayToDataTable([
        ['Tipo', 'Quant.'],
        ['Assertivo', 1],
        ['Divergente', 0],
    ]);

    let options = {
        is3D: true,
        width: 200,
        backgroundColor: '#2c405f',
        tooltip: {isHtml: true},
        height: '100%',
        legend: "none"
    };
    let options1 = {
        is3D: true,
        width: 200,
        legend: "none",
        backgroundColor: '#2c405f',
        tooltip: {isHtml: true},
        height: '100%'
    };

    let chart2 = new google.visualization.PieChart(document.getElementById('piechart'));
    let chart3 = new google.visualization.PieChart(document.getElementById('piechart1'));

    chart2.draw(data, options);
    chart3.draw(data, options1);
}


const form = document.getElementById('two');
const modal = document.getElementById("modal");
//const container = document.getElementById("containerDash");
const fecharModal = document.getElementById("tableForm:fecharmodal");

form.addEventListener("submit", e => {
    e.preventDefault();
})

function saldoDoBox(box) {
    let container = document.getElementById("containerDash");
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

function fecharModalSaldo() {
    let container = document.getElementById("containerDash");
    modal.style.display = "none";
    container.classList.remove("bg");
    container.classList.add("armazenagem");
}

fecharModal.addEventListener("click", () => {
    modal.style.display = "none";
    container.classList.remove("bg");
    container.classList.add("armazenagem");
})

const tableForm = document.getElementById("tableForm");

tableForm.addEventListener("submit", (e) => {
    console.log("onSubmit")
    e.preventDefault();
})

function procurarNF() {
    let table = document.getElementById("tableForm:tabelaModal")
    let tr = table.lastChild.parentNode.children.item(1).children;
    let procuraNF = document.getElementById("tableForm:procurarNF");
    if (procuraNF.value === "") {
        return alert("Informe uma nota fiscal!")
    }
    for (let index = 0; index < tr.length; index++) {
        const element = tr[index];
        if (procuraNF.value !== element.children.item(3).textContent) {
            element.style.display = "none";
        } else {
            if (element.style.display === "none") {
                element.style.display = ""
            }
        }
    }
}

function procurarItem() {
    let table = document.getElementById("tableForm:tabelaModal")
    let tr = table.lastChild.parentNode.children.item(1).children;
    let procuraItem = document.getElementById("tableForm:procurar_item");
    if (procuraItem.value === "") {
        return alert("Informe uma nota fiscal!")
    }
    for (let index = 0; index < tr.length; index++) {
        const element = tr[index];
        if (procuraItem.value !== element.children.item(0).textContent) {
            element.style.display = "none";
        } else {
            if (element.style.display === "none") {
                element.style.display = ""
            }
        }
    }
}

function procurarLote() {
    let table = document.getElementById("tableForm:tabelaModal")
    let tr = table.lastChild.parentNode.children.item(1).children;
    let procuraLote = document.getElementById("tableForm:procurar_lote");
    if (procuraLote.value === "") {
        return alert("Informe uma nota fiscal!")
    }
    for (let index = 0; index < tr.length; index++) {
        const element = tr[index];
        if (procuraLote.value !== element.children.item(2).textContent) {
            element.style.display = "none";
        } else {
            if (element.style.display === "none") {
                element.style.display = ""
            }
        }
    }
}

function exportTableToExcel(tableID, filename = '') {
    var downloadLink;
    var dataType = 'application/vnd.ms-excel';
    var tableSelect = document.getElementById(tableID);
    var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');

    // Specify file name
    filename = filename ? filename + '.xls' : 'excel_data.xls';

    // Create download link element
    downloadLink = document.createElement("a");

    document.body.appendChild(downloadLink);

    if (navigator.msSaveOrOpenBlob) {
        var blob = new Blob(['\ufeff', tableHTML], {
            type: dataType
        });
        navigator.msSaveOrOpenBlob(blob, filename);
    } else {
        // Create a link to the file
        downloadLink.href = 'data:' + dataType + ', ' + tableHTML;

        // Setting the file name
        downloadLink.download = filename;

        //triggering the function
        downloadLink.click();
    }
}

