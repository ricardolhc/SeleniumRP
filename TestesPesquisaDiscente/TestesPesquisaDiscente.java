
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestesPesquisaDiscente {

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
    /* Responder pesquisa de múltipla escolha obrigatória com apenas uma opção */
    public void G1135() throws InterruptedException {
        JSONObject G1135 = (JSONObject) jsonObject.get("G1135");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String opcao = G1135.get("opcao").toString();

        String mensagemFeedbackEsperada = G1135.get("mensagemFeedback").toString();
        String mensagemFeedback;

        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.clicarPerfil();
        facilitador.clicarMudarPapel();
        facilitador.pausaSegundos(2);

        facilitador.clicarPapelEstudante();

        facilitador.clicarModuloSection("Geral", "G1-135");

        facilitador.clicarResponderPesquisa();

        facilitador.clicarCheckBoxOpcaoUnica(opcao);

        facilitador.submeterRespostas();

        mensagemFeedback = facilitador.getMensagemFeedback();

        assertEquals(mensagemFeedbackEsperada, mensagemFeedback);
    }

    @Test
    /* Não responder pesquisa de múltipla escolha obrigatória com apenas uma opção */
    public void G1136() throws InterruptedException {
        JSONObject G1136 = (JSONObject) jsonObject.get("G1136");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String mensagemFeedbackEsperada = G1136.get("mensagemFeedback").toString();

        boolean possuiFeedInvalido = false;

        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.clicarPerfil();
        facilitador.clicarMudarPapel();

        facilitador.pausaSegundos(2);
        facilitador.clicarPapelEstudante();

        facilitador.clicarModuloSection("Geral", "G1-136");

        facilitador.clicarResponderPesquisa();

        facilitador.submeterRespostas();

        facilitador.pausaSegundos(2);
        possuiFeedInvalido = facilitador.getMensagemFeedBackInvalido(mensagemFeedbackEsperada);

        assertTrue(possuiFeedInvalido);
    }

    @Test
    /* Responder pesquisa de múltipla escolha obrigatória com várias opções */
    public void G1137() throws InterruptedException {
        JSONObject G1137 = (JSONObject) jsonObject.get("G1137");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String opcao1 = G1137.get("opcao1").toString();
        String opcao2 = G1137.get("opcao2").toString();

        String mensagemFeedbackEsperada = G1137.get("mensagemFeedback").toString();
        String mensagemFeedback;

        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.clicarPerfil();
        facilitador.clicarMudarPapel();

        facilitador.pausaSegundos(2);
        facilitador.clicarPapelEstudante();

        facilitador.clicarModuloSection("Geral", "G1-137");

        facilitador.clicarResponderPesquisa();

        facilitador.clicarCheckVarias(opcao1);
        facilitador.clicarCheckVarias(opcao2);

        facilitador.submeterRespostas();

        mensagemFeedback = facilitador.getMensagemFeedback();

        assertEquals(mensagemFeedbackEsperada, mensagemFeedback);
    }

    @Test
    /* Não responder pesquisa de múltipla escolha não obrigatória com apenas uma opção */
    public void G1138() throws InterruptedException {
        JSONObject G1138 = (JSONObject) jsonObject.get("G1138");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String mensagemFeedbackEsperada = G1138.get("mensagemFeedback").toString();
        String mensagemFeedback;

        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.clicarPerfil();
        facilitador.clicarMudarPapel();

        facilitador.pausaSegundos(2);
        facilitador.clicarPapelEstudante();

        facilitador.clicarModuloSection("Geral", "G1-138");

        facilitador.clicarResponderPesquisa();

        facilitador.submeterRespostas();

        facilitador.pausaSegundos(2);

        mensagemFeedback = facilitador.getMensagemFeedback();
        assertEquals(mensagemFeedbackEsperada, mensagemFeedback);
    }

    @Test
    /* Responder pesquisa de texto longo obrigatório */
    public void G1141() throws InterruptedException {
        JSONObject G1141 = (JSONObject) jsonObject.get("G1141");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String resposta = G1141.get("resposta").toString();

        String mensagemFeedbackEsperada = G1141.get("mensagemFeedback").toString();
        String mensagemFeedback;

        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.clicarPerfil();
        facilitador.clicarMudarPapel();

        facilitador.pausaSegundos(2);
        facilitador.clicarPapelEstudante();

        
        facilitador.clicarModuloSection("Geral", "G1-141");

        facilitador.clicarResponderPesquisa();

        facilitador.escreverResposta(resposta);

        facilitador.submeterRespostas();

        mensagemFeedback = facilitador.getMensagemFeedback();

        assertEquals(mensagemFeedbackEsperada, mensagemFeedback);
    }

    @Test
    /* Não responder pesquisa de texto longo obrigatório */
    public void G1142() throws InterruptedException {
        JSONObject G1142 = (JSONObject) jsonObject.get("G1142");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String mensagemFeedbackEsperada = G1142.get("mensagemFeedback").toString();

        boolean possuiFeedInvalido = false;

        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.clicarPerfil();
        facilitador.clicarMudarPapel();

        facilitador.pausaSegundos(2);
        facilitador.clicarPapelEstudante();

        facilitador.clicarModuloSection("Geral", "G1-142");

        facilitador.clicarResponderPesquisa();

        facilitador.submeterRespostas();

        facilitador.pausaSegundos(2);
        possuiFeedInvalido = facilitador.getMensagemFeedBackInvalido(mensagemFeedbackEsperada);

        assertTrue(possuiFeedInvalido);
    }

    @Test
    /* Responder pesquisa de texto longo com apenas 1 carácter */
    public void G1143() throws InterruptedException {
        JSONObject G1143 = (JSONObject) jsonObject.get("G1143");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String resposta = G1143.get("resposta").toString();

        String mensagemFeedbackEsperada = G1143.get("mensagemFeedback").toString();
        String mensagemFeedback;

        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.clicarPerfil();
        facilitador.clicarMudarPapel();

        facilitador.pausaSegundos(2);
        facilitador.clicarPapelEstudante();

        facilitador.clicarModuloSection("Geral", "G1-143");

        facilitador.clicarResponderPesquisa();

        facilitador.escreverResposta(resposta);

        facilitador.submeterRespostas();

        mensagemFeedback = facilitador.getMensagemFeedback();

        assertEquals(mensagemFeedbackEsperada, mensagemFeedback);
    }

    @Test
    /* Não responder pesquisa de texto longo não obrigatório */
    public void G1144() throws InterruptedException {
        JSONObject G1144 = (JSONObject) jsonObject.get("G1144");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String mensagemFeedbackEsperada = G1144.get("mensagemFeedback").toString();
        String mensagemFeedback;

        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.clicarPerfil();
        facilitador.clicarMudarPapel();

        facilitador.pausaSegundos(2);
        facilitador.clicarPapelEstudante();

        facilitador.clicarModuloSection("Geral", "G1-144");

        facilitador.clicarResponderPesquisa();

        facilitador.submeterRespostas();

        mensagemFeedback = facilitador.getMensagemFeedback();

        assertEquals(mensagemFeedbackEsperada, mensagemFeedback);
    }
    
    @Test
    /* Responder campo número obrigatório */
    public void G1145() throws InterruptedException {
        JSONObject G1145 = (JSONObject) jsonObject.get("G1145");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String resposta = G1145.get("resposta").toString();

        String mensagemFeedbackEsperada = G1145.get("mensagemFeedback").toString();
        String mensagemFeedback;

        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.clicarPerfil();
        facilitador.clicarMudarPapel();

        facilitador.pausaSegundos(2);
        facilitador.clicarPapelEstudante();

        facilitador.clicarModuloSection("Geral", "G1-145");

        facilitador.clicarResponderPesquisa();

        facilitador.escreverResposta(resposta);

        facilitador.submeterRespostas();

        mensagemFeedback = facilitador.getMensagemFeedback();

        assertEquals(mensagemFeedbackEsperada, mensagemFeedback);
    }

    @Test
    /* Não responder campo número obrigatório */
    public void G1146() throws InterruptedException {
        JSONObject G1146 = (JSONObject) jsonObject.get("G1146");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String mensagemFeedbackEsperada = G1146.get("mensagemFeedback").toString();

        boolean possuiFeedInvalido = false;

        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.clicarPerfil();
        facilitador.clicarMudarPapel();

        facilitador.pausaSegundos(2);
        facilitador.clicarPapelEstudante();

        facilitador.clicarModuloSection("Geral", "G1-146");

        facilitador.clicarResponderPesquisa();

        facilitador.submeterRespostas();

        facilitador.pausaSegundos(2);
        possuiFeedInvalido = facilitador.getMensagemFeedBackInvalido(mensagemFeedbackEsperada);

        assertTrue(possuiFeedInvalido);
    }

    @Test
    /* Não responder campo número não obrigatório */
    public void G1147() throws InterruptedException {
        JSONObject G1147 = (JSONObject) jsonObject.get("G1147");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String mensagemFeedbackEsperada = G1147.get("mensagemFeedback").toString();
        String mensagemFeedback;

        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.clicarPerfil();
        facilitador.clicarMudarPapel();

        facilitador.pausaSegundos(2);
        facilitador.clicarPapelEstudante();

        facilitador.clicarModuloSection("Geral", "G1-147");

        facilitador.clicarResponderPesquisa();

        facilitador.submeterRespostas();

        mensagemFeedback = facilitador.getMensagemFeedback();

        assertEquals(mensagemFeedbackEsperada, mensagemFeedback);
    }

    @Test
    /* Responder campo número com número inválido */
    public void G1148() throws InterruptedException {
        JSONObject G1148 = (JSONObject) jsonObject.get("G1148");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String resposta = G1148.get("resposta").toString();

        String mensagemFeedbackEsperada = G1148.get("mensagemFeedback").toString();

        boolean possuiFeedInvalido = false;

        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.clicarPerfil();
        facilitador.clicarMudarPapel();

        facilitador.pausaSegundos(2);
        facilitador.clicarPapelEstudante();

        facilitador.clicarModuloSection("Geral", "G1-148");

        facilitador.clicarResponderPesquisa();

        facilitador.escreverResposta(resposta);

        facilitador.submeterRespostas();

        facilitador.pausaSegundos(2);
        possuiFeedInvalido = facilitador.getMensagemFeedBackInvalido(mensagemFeedbackEsperada);

        assertTrue(possuiFeedInvalido);
    }

    @Test
    /*Responder pesquisa de texto curto obrigatório */
    public void G1149() throws InterruptedException {
        JSONObject G1149 = (JSONObject) jsonObject.get("G1149");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String resposta = G1149.get("resposta").toString();

        String mensagemFeedbackEsperada = G1149.get("mensagemFeedback").toString();
        String mensagemFeedback;

        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.clicarPerfil();
        facilitador.clicarMudarPapel();

        facilitador.pausaSegundos(2);
        facilitador.clicarPapelEstudante();

        facilitador.clicarModuloSection("Geral", "G1-149");

        facilitador.clicarResponderPesquisa();

        facilitador.escreverResposta(resposta);

        facilitador.submeterRespostas();

        mensagemFeedback = facilitador.getMensagemFeedback();

        assertEquals(mensagemFeedbackEsperada, mensagemFeedback);
    }

    @Test
    /* Não responder pesquisa de texto curto obrigatório */
    public void G1150() throws InterruptedException {
        JSONObject G1150 = (JSONObject) jsonObject.get("G1150");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String mensagemFeedbackEsperada = G1150.get("mensagemFeedback").toString();

        boolean possuiFeedInvalido = false;

        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.clicarPerfil();
        facilitador.clicarMudarPapel();

        facilitador.pausaSegundos(2);
        facilitador.clicarPapelEstudante();

        facilitador.clicarModuloSection("Geral", "G1-150");

        facilitador.clicarResponderPesquisa();

        facilitador.submeterRespostas();

        facilitador.pausaSegundos(2);
        possuiFeedInvalido = facilitador.getMensagemFeedBackInvalido(mensagemFeedbackEsperada);

        assertTrue(possuiFeedInvalido);
    }

    @Test
    /* Não responder pesquisa de texto curto não obrigatório */
    public void G1151() throws InterruptedException {
        JSONObject G1151 = (JSONObject) jsonObject.get("G1151");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String mensagemFeedbackEsperada = G1151.get("mensagemFeedback").toString();
        String mensagemFeedback;

        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.clicarPerfil();
        facilitador.clicarMudarPapel();

        facilitador.pausaSegundos(2);
        facilitador.clicarPapelEstudante();

        facilitador.clicarModuloSection("Geral", "G1-151");

        facilitador.clicarResponderPesquisa();

        facilitador.submeterRespostas();

        mensagemFeedback = facilitador.getMensagemFeedback();

        assertEquals(mensagemFeedbackEsperada, mensagemFeedback);
    }

    @Test
    /* Responder pesquisa de texto curto obrigatório acima do limite */
    public void G1152() throws InterruptedException {
        JSONObject G1152 = (JSONObject) jsonObject.get("G1152");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String resposta = G1152.get("resposta").toString();

        String mensagemFeedbackEsperada = G1152.get("mensagemFeedback").toString();

        boolean possuiFeedInvalido = false;

        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.clicarPerfil();
        facilitador.clicarMudarPapel();

        facilitador.pausaSegundos(2);
        facilitador.clicarPapelEstudante();

        facilitador.clicarModuloSection("Geral", "G1-152");

        facilitador.clicarResponderPesquisa();

        facilitador.escreverResposta(resposta);
        
        facilitador.submeterRespostas();

        facilitador.pausaSegundos(2);
        possuiFeedInvalido = facilitador.getMensagemFeedBackInvalido(mensagemFeedbackEsperada);

        assertTrue(possuiFeedInvalido);
    }

    @Test
    /* Responder pesquisa e tentar responder novamente */
    public void G1153() throws InterruptedException {
        JSONObject G1153 = (JSONObject) jsonObject.get("G1153");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String resposta = G1153.get("resposta").toString();

        String mensagemFeedbackEsperada = G1153.get("mensagemFeedback").toString();
        String mensagemFeedbackErroEsperada = G1153.get("mensagemFeedbackErro").toString();

        String mensagemFeedback;

        boolean possuiFeedInvalido = false;

        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.clicarPerfil();
        facilitador.clicarMudarPapel();

        facilitador.pausaSegundos(2);
        facilitador.clicarPapelEstudante();

        facilitador.clicarModuloSection("Geral", "G1-153");

        facilitador.clicarResponderPesquisa();

        facilitador.escreverResposta(resposta);

        facilitador.submeterRespostas();

        mensagemFeedback = facilitador.getMensagemFeedback();

        assertEquals(mensagemFeedbackEsperada, mensagemFeedback);

        facilitador.clicarResponderPesquisa();

        facilitador.clicarModuloSection("Geral", "G1-153");

        possuiFeedInvalido = facilitador.getMensagemFeedBackInvalido(mensagemFeedbackErroEsperada);

        assertTrue(possuiFeedInvalido);
    }

}