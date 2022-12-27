import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.FileReader;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestesMensagem {

    private static JSONObject jsonObject;
    private static String baseUrl;
    private WebDriver driver = new ChromeDriver();
    private MetodosFacilitadores facilitador;

    @Before
    public void setUp() throws Exception {
        //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        jsonObject = (JSONObject) new JSONParser().parse(new FileReader(getClass().getResource("json/dados.json").getPath()));
        baseUrl = jsonObject.get("baseUrl").toString();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        facilitador = new MetodosFacilitadores(driver);
    }

    @After
    public void tearDown() throws Exception {
        driver.close();
    }

    @Test
    public void envioDeMensagem() throws InterruptedException {
        JSONObject arquivo = (JSONObject) jsonObject.get("envioDeMensagem");

        JSONObject usuario1 = (JSONObject) arquivo.get("usuario1");
        String loginUsuario1 = usuario1.get("login").toString();
        String senhaUsuario1 = usuario1.get("senha").toString();
        String nomeUsuario1 = usuario1.get("nome").toString();

        JSONObject usuario2 = (JSONObject) arquivo.get("usuario2");
        String loginUsuario2 = usuario2.get("login").toString();
        String senhaUsuario2 = usuario2.get("senha").toString();
        String nomeUsuario2 = usuario2.get("nome").toString();

        String mensagemEnviar = arquivo.get("mensagem").toString();

        List<WebElement> mensagens;

        String[] ultimaMensagemEnviada;
        String[] ultimaMensagemRecebida;

        facilitador.fazerLogin(loginUsuario1, senhaUsuario1);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(1);

        facilitador.clicarContatos();
        facilitador.pausaSegundos(2);

        facilitador.clicarContatoDesejado(facilitador.getContatosGeral(), nomeUsuario2);
        facilitador.pausaSegundos(2);

        facilitador.digitarMensagemContatoPessoal(mensagemEnviar);
        facilitador.enviarMensagemContatoPessoalEnter();
        facilitador.pausaSegundos(2);

        mensagens = facilitador.getMensagens();
        ultimaMensagemEnviada = mensagens.get(mensagens.size() - 1).getText().split("\n");
        
        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);

        facilitador.clicarSairPerfil();
        facilitador.clicarAcessar();
        facilitador.fazerLogin(loginUsuario2, senhaUsuario2);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(1);
        
        facilitador.clicarContatoDesejado(facilitador.getContatosPrivado(), nomeUsuario1);
        facilitador.pausaSegundos(2);

        mensagens = facilitador.getMensagens();
        ultimaMensagemRecebida = mensagens.get(mensagens.size() - 1).getText().split("\n");

        assertEquals(nomeUsuario1, ultimaMensagemRecebida[0]);
        assertEquals(ultimaMensagemEnviada[0], ultimaMensagemRecebida[1]);
        assertEquals(ultimaMensagemEnviada[1], ultimaMensagemRecebida[2]);
    }

    @Test
    public void envioDeMensagemEntrePessoas() throws InterruptedException {
        JSONObject arquivo = (JSONObject) jsonObject.get("envioDeMensagemEntrePessoas");

        JSONObject usuario1 = (JSONObject) arquivo.get("usuario1");
        String loginUsuario1 = usuario1.get("login").toString();
        String senhaUsuario1 = usuario1.get("senha").toString();
        String nomeUsuario1 = usuario1.get("nome").toString();

        JSONObject usuario2 = (JSONObject) arquivo.get("usuario2");
        String loginUsuario2 = usuario2.get("login").toString();
        String senhaUsuario2 = usuario2.get("senha").toString();
        String nomeUsuario2 = usuario2.get("nome").toString();

        String mensagemEnviar = arquivo.get("mensagem").toString();

        List<WebElement> mensagens;

        String[] ultimaMensagemEnviada;
        String[] ultimaMensagemRecebida;

        facilitador.fazerLogin(loginUsuario1, senhaUsuario1);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(1);

        facilitador.clicarContatos();
        facilitador.pausaSegundos(2);

        facilitador.clicarContatoDesejado(facilitador.getContatosGeral(), nomeUsuario2);
        facilitador.pausaSegundos(2);

        facilitador.digitarMensagemContatoPessoal(mensagemEnviar);
        facilitador.enviarMensagemContatoPessoalEnter();
        facilitador.pausaSegundos(2);

        mensagens = facilitador.getMensagens();
        ultimaMensagemEnviada = mensagens.get(mensagens.size() - 1).getText().split("\n");
        
        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarSairPerfil();

        facilitador.clicarAcessar();
        facilitador.fazerLogin(loginUsuario2, senhaUsuario2);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(1);
        
        facilitador.clicarContatoDesejado(facilitador.getContatosPrivado(), nomeUsuario1);
        facilitador.pausaSegundos(2);

        mensagens = facilitador.getMensagens();
        ultimaMensagemRecebida = mensagens.get(mensagens.size() - 1).getText().split("\n");

        assertEquals(nomeUsuario1, ultimaMensagemRecebida[0]);
        assertEquals(ultimaMensagemEnviada[0], ultimaMensagemRecebida[1]);
        assertEquals(ultimaMensagemEnviada[1], ultimaMensagemRecebida[2]);

        facilitador.digitarMensagemContatoPessoal(mensagemEnviar);
        facilitador.enviarMensagemContatoPessoalEnter();
        facilitador.pausaSegundos(2);

        mensagens = facilitador.getMensagens();
        ultimaMensagemEnviada = mensagens.get(mensagens.size() - 1).getText().split("\n");

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarSairPerfil();

        facilitador.clicarAcessar();
        facilitador.fazerLogin(loginUsuario1, senhaUsuario1);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(1);

        facilitador.clicarContatoDesejado(facilitador.getContatosPrivado(), nomeUsuario2);
        facilitador.pausaSegundos(2);

        mensagens = facilitador.getMensagens();
        ultimaMensagemRecebida = mensagens.get(mensagens.size() - 1).getText().split("\n");

        assertEquals(nomeUsuario2, ultimaMensagemRecebida[0]);
        assertEquals(ultimaMensagemEnviada[0], ultimaMensagemRecebida[1]);
        assertEquals(ultimaMensagemEnviada[1], ultimaMensagemRecebida[2]);
    }

    @Test
    public void envioDeMensagensEntrePessoas() throws InterruptedException {
        JSONObject arquivo = (JSONObject) jsonObject.get("envioDeMensagensEntrePessoas");

        JSONObject usuario1 = (JSONObject) arquivo.get("usuario1");
        String loginUsuario1 = usuario1.get("login").toString();
        String senhaUsuario1 = usuario1.get("senha").toString();
        String nomeUsuario1 = usuario1.get("nome").toString();
        String mensagem1Usuario1 = usuario1.get("mensagem1").toString();
        String mensagem2Usuario1 = usuario1.get("mensagem2").toString();

        JSONObject usuario2 = (JSONObject) arquivo.get("usuario2");
        String loginUsuario2 = usuario2.get("login").toString();
        String senhaUsuario2 = usuario2.get("senha").toString();
        String nomeUsuario2 = usuario2.get("nome").toString();
        String mensagem1Usuario2 = usuario2.get("mensagem1").toString();
        String mensagem2Usuario2 = usuario2.get("mensagem2").toString();

        List<WebElement> mensagens;

        String[] ultimaMensagemEnviada;
        String[] penultimaMensagemEnviada;
        String[] ultimaMensagemRecebida;
        String[] penultimaMensagemRecebida;

        facilitador.fazerLogin(loginUsuario1, senhaUsuario1);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(1);

        facilitador.clicarContatos();
        facilitador.pausaSegundos(2);

        facilitador.clicarContatoDesejado(facilitador.getContatosGeral(), nomeUsuario2);
        facilitador.pausaSegundos(2);

        facilitador.digitarMensagemContatoPessoal(mensagem1Usuario1);
        facilitador.enviarMensagemContatoPessoalEnter();
        facilitador.pausaSegundos(2);
        facilitador.digitarMensagemContatoPessoal(mensagem2Usuario1);
        facilitador.enviarMensagemContatoPessoalEnter();
        facilitador.pausaSegundos(2);

        mensagens = facilitador.getMensagens();
        ultimaMensagemEnviada = mensagens.get(mensagens.size() - 1).getText().split("\n");
        penultimaMensagemEnviada = mensagens.get(mensagens.size() - 2).getText().split("\n");
        
        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarSairPerfil();

        facilitador.clicarAcessar();
        facilitador.fazerLogin(loginUsuario2, senhaUsuario2);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(1);
        
        facilitador.clicarContatoDesejado(facilitador.getContatosPrivado(), nomeUsuario1);
        facilitador.pausaSegundos(2);

        mensagens = facilitador.getMensagens();
        ultimaMensagemRecebida = mensagens.get(mensagens.size() - 1).getText().split("\n");
        penultimaMensagemRecebida = mensagens.get(mensagens.size() - 2).getText().split("\n");

        assertEquals(nomeUsuario1, ultimaMensagemRecebida[0]);
        assertEquals(ultimaMensagemEnviada[0], ultimaMensagemRecebida[1]);
        assertEquals(ultimaMensagemEnviada[1], ultimaMensagemRecebida[2]);
        
        assertEquals(nomeUsuario1, penultimaMensagemRecebida[0]);
        assertEquals(penultimaMensagemEnviada[0], penultimaMensagemRecebida[1]);
        assertEquals(penultimaMensagemEnviada[1], penultimaMensagemRecebida[2]);

        facilitador.digitarMensagemContatoPessoal(mensagem1Usuario2);
        facilitador.enviarMensagemContatoPessoalEnter();
        facilitador.pausaSegundos(2);
        facilitador.digitarMensagemContatoPessoal(mensagem2Usuario2);
        facilitador.enviarMensagemContatoPessoalEnter();
        facilitador.pausaSegundos(2);

        mensagens = facilitador.getMensagens();
        ultimaMensagemEnviada = mensagens.get(mensagens.size() - 1).getText().split("\n");
        penultimaMensagemEnviada = mensagens.get(mensagens.size() - 2).getText().split("\n");

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarSairPerfil();

        facilitador.clicarAcessar();
        facilitador.fazerLogin(loginUsuario1, senhaUsuario1);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(1);

        facilitador.clicarContatoDesejado(facilitador.getContatosPrivado(), nomeUsuario2);
        facilitador.pausaSegundos(2);

        mensagens = facilitador.getMensagens();
        ultimaMensagemRecebida = mensagens.get(mensagens.size() - 1).getText().split("\n");
        penultimaMensagemRecebida = mensagens.get(mensagens.size() - 2).getText().split("\n");

        assertEquals(nomeUsuario2, ultimaMensagemRecebida[0]);
        assertEquals(ultimaMensagemEnviada[0], ultimaMensagemRecebida[1]);
        assertEquals(ultimaMensagemEnviada[1], ultimaMensagemRecebida[2]);
        assertEquals(nomeUsuario2, penultimaMensagemRecebida[0]);
        assertEquals(penultimaMensagemEnviada[0], penultimaMensagemRecebida[1]);
        assertEquals(penultimaMensagemEnviada[1], penultimaMensagemRecebida[2]);
    }

    @Test
    public void envioDeMensagemClicandoNoIcone() throws InterruptedException {
        JSONObject arquivo = (JSONObject) jsonObject.get("envioDeMensagemClicandoNoIcone");

        JSONObject usuario1 = (JSONObject) arquivo.get("usuario1");
        String loginUsuario1 = usuario1.get("login").toString();
        String senhaUsuario1 = usuario1.get("senha").toString();
        String nomeUsuario1 = usuario1.get("nome").toString();

        JSONObject usuario2 = (JSONObject) arquivo.get("usuario2");
        String loginUsuario2 = usuario2.get("login").toString();
        String senhaUsuario2 = usuario2.get("senha").toString();
        String nomeUsuario2 = usuario2.get("nome").toString();

        String mensagemEnviar = arquivo.get("mensagem").toString();

        List<WebElement> mensagens;

        String[] ultimaMensagemEnviada;
        String[] ultimaMensagemRecebida;

        facilitador.fazerLogin(loginUsuario1, senhaUsuario1);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(1);

        facilitador.clicarContatos();
        facilitador.pausaSegundos(2);

        facilitador.clicarContatoDesejado(facilitador.getContatosGeral(), nomeUsuario2);
        facilitador.pausaSegundos(2);

        facilitador.digitarMensagemContatoPessoal(mensagemEnviar);
        facilitador.enviarMensagemContatoPessoalIcon();
        facilitador.pausaSegundos(2);

        mensagens = facilitador.getMensagens();
        ultimaMensagemEnviada = mensagens.get(mensagens.size() - 1).getText().split("\n");
        
        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);

        facilitador.clicarSairPerfil();
        facilitador.clicarAcessar();
        facilitador.fazerLogin(loginUsuario2, senhaUsuario2);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(1);
        
        facilitador.clicarContatoDesejado(facilitador.getContatosPrivado(), nomeUsuario1);
        facilitador.pausaSegundos(2);

        mensagens = facilitador.getMensagens();
        ultimaMensagemRecebida = mensagens.get(mensagens.size() - 1).getText().split("\n");

        assertEquals(nomeUsuario1, ultimaMensagemRecebida[0]);
        assertEquals(ultimaMensagemEnviada[0], ultimaMensagemRecebida[1]);
        assertEquals(ultimaMensagemEnviada[1], ultimaMensagemRecebida[2]);
    }

    @Test
    public void envioEmMassaParaTodosOsParticipantes() throws InterruptedException {
        JSONObject arquivo = (JSONObject) jsonObject.get("envioEmMassaParaTodosOsParticipantes");

        JSONObject usuario1 = (JSONObject) arquivo.get("usuario1");
        String loginUsuario1 = usuario1.get("login").toString();
        String senhaUsuario1 = usuario1.get("senha").toString();
        String nomeUsuario1 = usuario1.get("nome").toString();

        JSONObject usuario2 = (JSONObject) arquivo.get("usuario2");
        String loginUsuario2 = usuario2.get("login").toString();
        String senhaUsuario2 = usuario2.get("senha").toString();
        String nomeUsuario2 = usuario2.get("nome").toString();

        JSONObject usuario3 = (JSONObject) arquivo.get("usuario3");
        String loginUsuario3 = usuario3.get("login").toString();
        String senhaUsuario3 = usuario3.get("senha").toString();
        String nomeUsuario3 = usuario3.get("nome").toString();

        JSONObject usuario4 = (JSONObject) arquivo.get("usuario4");
        String loginUsuario4 = usuario4.get("login").toString();
        String senhaUsuario4 = usuario4.get("senha").toString();
        String nomeUsuario4 = usuario4.get("nome").toString();

        JSONObject usuario5 = (JSONObject) arquivo.get("usuario5");
        String loginUsuario5 = usuario5.get("login").toString();
        String senhaUsuario5 = usuario5.get("senha").toString();
        String nomeUsuario5 = usuario5.get("nome").toString();

        String mensagemEnviar = arquivo.get("mensagem").toString();

        List<WebElement> mensagens;

        String[] ultimaMensagemRecebida;

        facilitador.fazerLogin(loginUsuario1, senhaUsuario1);
        facilitador.clicarMeusCursos();
        facilitador.pausaSegundos(2);
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);
        facilitador.clicarParticipantes();
        facilitador.pausaSegundos(1);

        List<WebElement> participantes = facilitador.getParticipantes();
        facilitador.marcarParticipante(participantes, nomeUsuario2);
        facilitador.marcarParticipante(participantes, nomeUsuario3);
        facilitador.marcarParticipante(participantes, nomeUsuario4);
        facilitador.marcarParticipante(participantes, nomeUsuario5);

        facilitador.clicarEscolher();
        facilitador.pausaSegundos(1);
        facilitador.clicarEnviarUmaMensagem();
        facilitador.pausaSegundos(2);
        facilitador.digitarMensagemEmMassa(mensagemEnviar);
        facilitador.pausaSegundos(1);
        facilitador.clicarEnviarMensagemEmMassa();

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarSairPerfilCurso();
        facilitador.clicarAcessar();
        facilitador.fazerLogin(loginUsuario2, senhaUsuario2);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(2);

        facilitador.clicarContatoDesejado(facilitador.getContatosPrivado(), nomeUsuario1);
        facilitador.pausaSegundos(3);

        mensagens = facilitador.getMensagens();
        ultimaMensagemRecebida = mensagens.get(mensagens.size() - 1).getText().split("\n");

        assertEquals(nomeUsuario1, ultimaMensagemRecebida[0]);
        assertEquals(mensagemEnviar, ultimaMensagemRecebida[2]);

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarSairPerfil();
        facilitador.clicarAcessar();
        facilitador.fazerLogin(loginUsuario3, senhaUsuario3);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(2);

        facilitador.clicarContatoDesejado(facilitador.getContatosPrivado(), nomeUsuario1);
        facilitador.pausaSegundos(3);

        mensagens = facilitador.getMensagens();
        ultimaMensagemRecebida = mensagens.get(mensagens.size() - 1).getText().split("\n");

        assertEquals(nomeUsuario1, ultimaMensagemRecebida[0]);
        assertEquals(mensagemEnviar, ultimaMensagemRecebida[2]);

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarSairPerfil();
        facilitador.clicarAcessar();
        facilitador.fazerLogin(loginUsuario4, senhaUsuario4);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(2);

        facilitador.clicarContatoDesejado(facilitador.getContatosPrivado(), nomeUsuario1);
        facilitador.pausaSegundos(3);

        mensagens = facilitador.getMensagens();
        ultimaMensagemRecebida = mensagens.get(mensagens.size() - 1).getText().split("\n");

        assertEquals(nomeUsuario1, ultimaMensagemRecebida[0]);
        assertEquals(mensagemEnviar, ultimaMensagemRecebida[2]);

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarSairPerfil();
        facilitador.clicarAcessar();
        facilitador.fazerLogin(loginUsuario5, senhaUsuario5);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(2);

        facilitador.clicarContatoDesejado(facilitador.getContatosPrivado(), nomeUsuario1);
        facilitador.pausaSegundos(3);

        mensagens = facilitador.getMensagens();
        ultimaMensagemRecebida = mensagens.get(mensagens.size() - 1).getText().split("\n");

        assertEquals(nomeUsuario1, ultimaMensagemRecebida[0]);
        assertEquals(mensagemEnviar, ultimaMensagemRecebida[2]);
    }

    @Test
    public void envioEmMassaParaUmParticipante() throws InterruptedException {
        JSONObject arquivo = (JSONObject) jsonObject.get("envioEmMassaParaUmParticipante");

        JSONObject usuario1 = (JSONObject) arquivo.get("usuario1");
        String loginUsuario1 = usuario1.get("login").toString();
        String senhaUsuario1 = usuario1.get("senha").toString();
        String nomeUsuario1 = usuario1.get("nome").toString();

        JSONObject usuario2 = (JSONObject) arquivo.get("usuario2");
        String loginUsuario2 = usuario2.get("login").toString();
        String senhaUsuario2 = usuario2.get("senha").toString();
        String nomeUsuario2 = usuario2.get("nome").toString();

        String mensagemEnviar = arquivo.get("mensagem").toString();

        List<WebElement> mensagens;
        List<WebElement> participantes;

        String[] ultimaMensagemRecebida;

        facilitador.fazerLogin(loginUsuario1, senhaUsuario1);
        facilitador.clicarMeusCursos();
        facilitador.pausaSegundos(2);
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);
        facilitador.clicarParticipantes();
        facilitador.pausaSegundos(1);

        participantes = facilitador.getParticipantes();
        facilitador.marcarParticipante(participantes, nomeUsuario2);

        facilitador.clicarEscolher();
        facilitador.pausaSegundos(1);
        facilitador.clicarEnviarUmaMensagem();
        facilitador.pausaSegundos(2);
        facilitador.digitarMensagemEmMassa(mensagemEnviar);
        facilitador.pausaSegundos(1);
        facilitador.clicarEnviarMensagemEmMassa();

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarSairPerfilCurso();
        facilitador.clicarAcessar();
        facilitador.fazerLogin(loginUsuario2, senhaUsuario2);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(2);

        facilitador.clicarContatoDesejado(facilitador.getContatosPrivado(), nomeUsuario1);
        facilitador.pausaSegundos(3);

        mensagens = facilitador.getMensagens();
        ultimaMensagemRecebida = mensagens.get(mensagens.size() - 1).getText().split("\n");

        assertEquals(nomeUsuario1, ultimaMensagemRecebida[0]);
        assertEquals(mensagemEnviar, ultimaMensagemRecebida[2]);
    }

    @Test
    public void envioDeMensagemVazia() throws InterruptedException {
        JSONObject arquivo = (JSONObject) jsonObject.get("envioDeMensagemVazia");

        JSONObject usuario1 = (JSONObject) arquivo.get("usuario1");
        String loginUsuario1 = usuario1.get("login").toString();
        String senhaUsuario1 = usuario1.get("senha").toString();

        JSONObject usuario2 = (JSONObject) arquivo.get("usuario2");
        String nomeUsuario2 = usuario2.get("nome").toString();

        String mensagemEnviar = arquivo.get("mensagem").toString();

        List<WebElement> mensagens;

        String[] ultimaMensagemEnviada;

        facilitador.fazerLogin(loginUsuario1, senhaUsuario1);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(1);

        facilitador.clicarContatos();
        facilitador.pausaSegundos(2);

        facilitador.clicarContatoDesejado(facilitador.getContatosGeral(), nomeUsuario2);
        facilitador.pausaSegundos(2);

        facilitador.digitarMensagemContatoPessoal(mensagemEnviar);
        facilitador.enviarMensagemContatoPessoalEnter();
        facilitador.pausaSegundos(2);

        mensagens = facilitador.getMensagens();
        ultimaMensagemEnviada = mensagens.get(mensagens.size() - 1).getText().split("\n");
        
        assertNotEquals(ultimaMensagemEnviada, mensagemEnviar);
    }

    @Test
    public void envioDeMensagemComFormatacao() throws InterruptedException {
        JSONObject arquivo = (JSONObject) jsonObject.get("envioDeMensagemComFormatacao");

        JSONObject usuario1 = (JSONObject) arquivo.get("usuario1");
        String loginUsuario1 = usuario1.get("login").toString();
        String senhaUsuario1 = usuario1.get("senha").toString();
        String nomeUsuario1 = usuario1.get("nome").toString();

        JSONObject usuario2 = (JSONObject) arquivo.get("usuario2");
        String loginUsuario2 = usuario2.get("login").toString();
        String senhaUsuario2 = usuario2.get("senha").toString();
        String nomeUsuario2 = usuario2.get("nome").toString();

        String mensagemEnviar = arquivo.get("mensagem").toString();

        List<WebElement> mensagens;

        String[] ultimaMensagemRecebida;
        String[] ultimaMensagemEnviada;

        facilitador.fazerLogin(loginUsuario1, senhaUsuario1);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(1);

        facilitador.clicarContatos();
        facilitador.pausaSegundos(2);

        facilitador.clicarContatoDesejado(facilitador.getContatosGeral(), nomeUsuario2);
        facilitador.pausaSegundos(2);

        facilitador.digitarMensagemContatoPessoal(mensagemEnviar);
        facilitador.enviarMensagemContatoPessoalEnter();
        facilitador.pausaSegundos(2);

        mensagens = facilitador.getMensagens();
        ultimaMensagemEnviada = mensagens.get(mensagens.size() - 1).getText().split("\n");
        
        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);

        facilitador.clicarSairPerfil();
        facilitador.clicarAcessar();
        facilitador.fazerLogin(loginUsuario2, senhaUsuario2);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(1);
        
        facilitador.clicarContatoDesejado(facilitador.getContatosPrivado(), nomeUsuario1);
        facilitador.pausaSegundos(2);

        mensagens = facilitador.getMensagens();
        ultimaMensagemRecebida = mensagens.get(mensagens.size() - 1).getText().split("\n");

        assertEquals(nomeUsuario1, ultimaMensagemRecebida[0]);
        assertEquals(ultimaMensagemEnviada[0], ultimaMensagemRecebida[1]);
        assertEquals(ultimaMensagemEnviada[1], ultimaMensagemRecebida[2]);
    }

    @Test
    public void envioDeMensagemEmMassaVazia() throws InterruptedException {
        JSONObject arquivo = (JSONObject) jsonObject.get("envioDeMensagemEmMassaVazia");

        JSONObject usuario1 = (JSONObject) arquivo.get("usuario1");
        String loginUsuario1 = usuario1.get("login").toString();
        String senhaUsuario1 = usuario1.get("senha").toString();

        JSONObject usuario2 = (JSONObject) arquivo.get("usuario2");
        String nomeUsuario2 = usuario2.get("nome").toString();

        String mensagemEnviar = arquivo.get("mensagem").toString();
        String mensagemErroEsperada = arquivo.get("erro").toString();
        String mensagemErro;

        facilitador.fazerLogin(loginUsuario1, senhaUsuario1);
        facilitador.clicarMeusCursos();
        facilitador.pausaSegundos(2);
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);
        facilitador.clicarParticipantes();
        facilitador.pausaSegundos(1);

        List<WebElement> participantes = facilitador.getParticipantes();
        facilitador.marcarParticipante(participantes, nomeUsuario2);

        facilitador.clicarEscolher();
        facilitador.pausaSegundos(1);
        facilitador.clicarEnviarUmaMensagem();
        facilitador.pausaSegundos(2);
        facilitador.digitarMensagemEmMassa(mensagemEnviar);
        facilitador.pausaSegundos(1);
        facilitador.clicarEnviarMensagemEmMassa();
        facilitador.pausaSegundos(1);

        mensagemErro = driver.findElement(By.className("text-danger")).getText();

        assertEquals(mensagemErro, mensagemErroEsperada);
    }

    @Test
    public void envioExclusaoConversa() throws InterruptedException {
        JSONObject arquivo = (JSONObject) jsonObject.get("envioExclusaoConversa");

        JSONObject usuario1 = (JSONObject) arquivo.get("usuario1");
        String loginUsuario1 = usuario1.get("login").toString();
        String senhaUsuario1 = usuario1.get("senha").toString();
        String nomeUsuario1 = usuario1.get("nome").toString();

        JSONObject usuario2 = (JSONObject) arquivo.get("usuario2");
        String loginUsuario2 = usuario2.get("login").toString();
        String senhaUsuario2 = usuario2.get("senha").toString();
        String nomeUsuario2 = usuario2.get("nome").toString();

        String mensagemEnviar = arquivo.get("mensagem").toString();
        String mensagemVaziaEsperada = arquivo.get("mensagemConversaVazia").toString();

        List<WebElement> mensagens;

        String[] ultimaMensagemEnviada;
        String[] ultimaMensagemRecebida;
        String mensagemConversa;
        String nenhumaConversa;

        facilitador.fazerLogin(loginUsuario1, senhaUsuario1);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(1);

        facilitador.clicarContatos();
        facilitador.pausaSegundos(2);

        facilitador.clicarContatoDesejado(facilitador.getContatosGeral(), nomeUsuario2);
        facilitador.pausaSegundos(2);

        facilitador.digitarMensagemContatoPessoal(mensagemEnviar);
        facilitador.enviarMensagemContatoPessoalEnter();
        facilitador.pausaSegundos(2);

        mensagens = facilitador.getMensagens();
        ultimaMensagemEnviada = mensagens.get(mensagens.size() - 1).getText().split("\n");
        
        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);

        facilitador.clicarSairPerfil();
        facilitador.clicarAcessar();
        facilitador.fazerLogin(loginUsuario2, senhaUsuario2);
        facilitador.clicarNoBalaoDeMensagem();
        facilitador.pausaSegundos(1);
        
        facilitador.clicarContatoDesejado(facilitador.getContatosPrivado(), nomeUsuario1);
        facilitador.pausaSegundos(2);

        mensagens = facilitador.getMensagens();
        ultimaMensagemRecebida = mensagens.get(mensagens.size() - 1).getText().split("\n");

        assertEquals(nomeUsuario1, ultimaMensagemRecebida[0]);
        assertEquals(ultimaMensagemEnviada[0], ultimaMensagemRecebida[1]);
        assertEquals(ultimaMensagemEnviada[1], ultimaMensagemRecebida[2]);

        driver.findElement(By.id("conversation-actions-menu-button")).click();

        driver.findElement(By.cssSelector("a[data-action='request-delete-conversation']")).click();
        facilitador.pausaSegundos(1);
        driver.findElement(By.cssSelector("button[data-action='confirm-delete-conversation']")).click();
        facilitador.pausaSegundos(2);

        mensagemConversa = driver.findElement(By.className("content-message-container")).getText();

        assertEquals("", mensagemConversa);

        facilitador.pausaSegundos(1);
        
        driver.findElement(By.cssSelector("div[data-region='header-content']")).findElement(By.className("fa-chevron-left")).click();
        facilitador.pausaSegundos(1);

        nenhumaConversa = driver.findElement(By.cssSelector("div[class='text-center p-2'] p[class='text-muted mt-2']")).getText();
        
        assertEquals(mensagemVaziaEsperada, nenhumaConversa);
    }
}