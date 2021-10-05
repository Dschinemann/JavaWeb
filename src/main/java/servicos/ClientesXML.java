package servicos;

import database.ConexaoBD;
import models.Armazem.Ocupacao;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientesXML {
    private static final File file = new File("src/main/configXml/clientes.xml");
    private static final List<Ocupacao> ocupacaoList = new ArrayList<>();
    private static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    public static ArrayList<Ocupacao> carregarDadosDoCliente(int CLIENTE) {
        ArrayList<Ocupacao> taxaOcupacao = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            NodeList nodeList = document.getDocumentElement().getElementsByTagName("cliente");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Element element = (Element) node;
                int IDCLIENTE = Integer.parseInt(element.getElementsByTagName("id-cliente")
                        .item(0)
                        .getTextContent());

                if (IDCLIENTE == CLIENTE) {
                    String nomeCLiente = element.getElementsByTagName("nome").item(0).getTextContent();
                    NodeList nodeOcupacao = element.getElementsByTagName("data");
                    for (int x = 0; x < nodeOcupacao.getLength(); x++) {
                        Node nodeOcup = nodeOcupacao.item(x);
                        Element element1 = (Element) nodeOcup;
                        int ind = element1.getTextContent().indexOf("Q");
                        String palet = element1.getTextContent().substring(ind + 1);
                        String data = element1.getTextContent().substring(0, ind);
                        int QUANTIDADEPALET = Integer.parseInt(palet);
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = formato.parse(data);
                        Ocupacao ocupacao = new Ocupacao(IDCLIENTE, nomeCLiente, QUANTIDADEPALET, formato.format(date));
                        taxaOcupacao.add(ocupacao);
                    }
                }
            }
            return taxaOcupacao;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return taxaOcupacao;
    }

    /*public static void main(String[] args) {
        try {
            ocupacao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static void ocupacao() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String hoje = LocalDate.now().format(formatter);

        ConexaoBD.criarConexaoComBD();
        String sql = "SELECT  CL.HANDLE,CL.APELIDO," +
                " COUNT(DISTINCT SL.ENDERECO) 'POSIÇOES OCUPADAS'" +
                " FROM AM_SALDOESTOQUE SL" +
                " INNER JOIN MS_PESSOA CL ON SL.CLIENTE = CL.HANDLE" +
                " GROUP BY CL.HANDLE,CL.APELIDO";
        ResultSet resultSet = ConexaoBD.consultarBancoDeDados(sql);

        while (resultSet.next()) {
            Ocupacao ocupacao = new Ocupacao(
                    resultSet.getInt("HANDLE"),
                    resultSet.getString("APELIDO"),
                    resultSet.getInt("POSIÇOES OCUPADAS"),
                    hoje
            );
            ocupacaoList.add(ocupacao);
        }
        ConexaoBD.disconnect();
        criarDadosDoCliente();
    }

    public static void criarDadosDoCliente() throws Exception {

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(file);
        NodeList nodeList = doc.getDocumentElement().getElementsByTagName("cliente");
        NodeList tagCliente = doc.getElementsByTagName("clientes");


        for (Ocupacao ocupacao : ocupacaoList) {
            int salvar = existCliente(ocupacao.getID());

            Element dataOcupacaoCliente = doc.createElement("data");
            dataOcupacaoCliente.setTextContent(ocupacao.getDataOcupacao() + "Q" + ocupacao.getOCUPACAOPP());

            if(salvar != -1){
                Node node = nodeList.item(salvar);
                Element element = (Element) node;


                NodeList nodeList1 = element.getElementsByTagName("ocupacao");
                nodeList1.item(0).appendChild(dataOcupacaoCliente);
                System.out.println("Tem  " + ocupacao.getID());
            }else{
                Element cliente = doc.createElement("cliente");
                Element idCliente = doc.createElement("id-cliente");
                Element nomeCliente = doc.createElement("nome");
                Element ocupacaoCliente = doc.createElement("ocupacao");

                idCliente.setTextContent(String.valueOf(ocupacao.getID()));
                nomeCliente.setTextContent(ocupacao.getNome());

                ocupacaoCliente.appendChild(dataOcupacaoCliente);
                cliente.appendChild(idCliente);
                cliente.appendChild(nomeCliente);
                cliente.appendChild(ocupacaoCliente);
                tagCliente.item(0).appendChild(cliente);
                System.out.println("nao tem  " + ocupacao.getID());
            }
        }

        String arquivo = converter(doc);
        salvarArquivo(arquivo);

    }

    private static int existCliente(int id) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(file);
        NodeList nodeList = doc.getDocumentElement().getElementsByTagName("cliente");

        int ITENS = nodeList.getLength();
        int salvar = -1;

        for (int i = 0; i < ITENS; i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;

            int IDCLIENTE = Integer.parseInt(element.getElementsByTagName("id-cliente")
                    .item(0)
                    .getTextContent());
            if(IDCLIENTE == id){
                salvar = i;
                break;
            }
        }
        return salvar;
    }


    public static void salvarArquivo(String documento) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file, false);
        fileOutputStream.write(documento.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public static String converter(Document document) throws TransformerException {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        StreamResult result = new StreamResult(new StringWriter());
        DOMSource source = new DOMSource(document);
        transformer.transform(source, result);

        return result.getWriter().toString();
    }

    public static void teste(){
        System.out.println("teste");
    }

}
