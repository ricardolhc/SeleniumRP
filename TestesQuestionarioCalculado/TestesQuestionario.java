
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileReader;

import java.util.Calendar;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestesQuestionario {

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
    /* Criação do questionário com data de entrega passada */
    public void G1210() throws InterruptedException {
        JSONObject G1210 = (JSONObject) jsonObject.get("G1210");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1210.get("tituloSection").toString();

        Calendar calendar = Calendar.getInstance();

        int diaInvalido = calendar.get(Calendar.DAY_OF_MONTH) - 1;

        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        driver.findElement(By.id("id_timing")).findElement(By.id("id_timeclose_enabled")).click();

        driver.findElement(By.id("id_timeclose_day")).sendKeys(diaInvalido + "");

        facilitador.clicarSalvarEMostrar();

        assertTrue(driver.getPageSource().contains("Fechado:"));
    }

    @Test
    /* Criação do questionário sem adicionar questões */
    public void G1211() throws InterruptedException {
        JSONObject G1211 = (JSONObject) jsonObject.get("G1211");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1211.get("tituloSection").toString();

        String feedback = G1211.get("feedback").toString();

        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        assertTrue(driver.getPageSource().contains(feedback));
    }

    @Test
    /* Criação do questionário com questão Calculado com resposta válida correta */
    public void G1212() throws InterruptedException {
        JSONObject G1212 = (JSONObject) jsonObject.get("G1212");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1212.get("tituloSection").toString();
        String resposta = G1212.get("resposta").toString();

        String feedbackRespostaAntesEnviarEsperada = G1212.get("feedbackRespostaAntesEnviar").toString();
        String feedbackRespostaAntesEnviar;                    

        String tituloQuestao = G1212.get("tituloQuestao").toString();
        String textoQuestao = G1212.get("textoQuestao").toString();
        String respostaQuestao = G1212.get("respostaQuestao").toString();
        String porcentagemQuestao = G1212.get("porcentagemQuestao").toString();

        String coringa1 = G1212.get("coringa_1").toString();
        String coringa2 = G1212.get("coringa_2").toString();

        String feedback = G1212.get("feedback").toString();
    
        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        facilitador.clicarAdicionarQuestao();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("item_qtype_calculated")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestao);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestao);

        driver.findElement(By.id("id_answer_0")).sendKeys(respostaQuestao);

        driver.findElement(By.id("id_fraction_0")).sendKeys(porcentagemQuestao);

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.id("id_addbutton")).click();

        driver.findElement(By.id("id_number_1")).clear();

        driver.findElement(By.id("id_number_2")).clear();

        driver.findElement(By.id("id_number_1")).sendKeys(coringa1);

        driver.findElement(By.id("id_number_2")).sendKeys(coringa2);

        driver.findElement(By.id("id_savechanges")).click();

        driver.findElement(By.className("editing_maxmark")).click();

        facilitador.pausaSegundos(2);

        Actions acao = new Actions(driver);
        acao.sendKeys(Keys.NUMPAD1).build().perform();
        acao.sendKeys(Keys.NUMPAD0).build().perform();
        acao.sendKeys(Keys.ENTER).build().perform();

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarMudarPapel();
        facilitador.clicarPapelEstudante();

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("more-nav")).findElements(By.tagName("li")).get(0).click();

        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.clicarModuloSection(section, tituloSection);

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("form-control")).sendKeys(resposta);

        driver.findElement(By.id("mod_quiz-next-nav")).click();

        facilitador.pausaSegundos(2);

        feedbackRespostaAntesEnviar = driver.findElement(By.tagName("tbody")).findElement(By.className("c1")).getText();

        assertEquals(feedbackRespostaAntesEnviarEsperada, feedbackRespostaAntesEnviar);

        driver.findElement(By.className("btn-primary")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();

        assertTrue(driver.findElement(By.className("info")).getText().contains(feedback));
    }

    @Test
    /* Criação do questionário com questão Calculado com resposta válida incorreta */
    public void G1213() throws InterruptedException {
        JSONObject G1213 = (JSONObject) jsonObject.get("G1213");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1213.get("tituloSection").toString();
        String resposta = G1213.get("resposta").toString();

        String feedbackRespostaAntesEnviarEsperada = G1213.get("feedbackRespostaAntesEnviar").toString();
        String feedbackRespostaAntesEnviar;                    

        String tituloQuestao = G1213.get("tituloQuestao").toString();
        String textoQuestao = G1213.get("textoQuestao").toString();
        String respostaQuestao = G1213.get("respostaQuestao").toString();
        String porcentagemQuestao = G1213.get("porcentagemQuestao").toString();

        String coringa1 = G1213.get("coringa_1").toString();
        String coringa2 = G1213.get("coringa_2").toString();

        String feedback = G1213.get("feedback").toString();
    
        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        facilitador.clicarAdicionarQuestao();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("item_qtype_calculated")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestao);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestao);

        driver.findElement(By.id("id_answer_0")).sendKeys(respostaQuestao);

        driver.findElement(By.id("id_fraction_0")).sendKeys(porcentagemQuestao);

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.id("id_addbutton")).click();

        driver.findElement(By.id("id_number_1")).clear();

        driver.findElement(By.id("id_number_2")).clear();

        driver.findElement(By.id("id_number_1")).sendKeys(coringa1);

        driver.findElement(By.id("id_number_2")).sendKeys(coringa2);

        driver.findElement(By.id("id_savechanges")).click();

        driver.findElement(By.className("editing_maxmark")).click();

        facilitador.pausaSegundos(2);

        Actions acao = new Actions(driver);
        acao.sendKeys(Keys.NUMPAD1).build().perform();
        acao.sendKeys(Keys.NUMPAD0).build().perform();
        acao.sendKeys(Keys.ENTER).build().perform();

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarMudarPapel();
        facilitador.clicarPapelEstudante();

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("more-nav")).findElements(By.tagName("li")).get(0).click();

        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.clicarModuloSection(section, tituloSection);

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("form-control")).sendKeys(resposta);

        driver.findElement(By.id("mod_quiz-next-nav")).click();

        facilitador.pausaSegundos(2);

        feedbackRespostaAntesEnviar = driver.findElement(By.tagName("tbody")).findElement(By.className("c1")).getText();

        assertEquals(feedbackRespostaAntesEnviarEsperada, feedbackRespostaAntesEnviar);

        driver.findElement(By.className("btn-primary")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();

        assertTrue(driver.findElement(By.className("info")).getText().contains(feedback));
    }

    @Test
    /* Criação do questionário com questão Calculado com resposta inválida */
    public void G1214() throws InterruptedException {
        JSONObject G1214 = (JSONObject) jsonObject.get("G1214");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1214.get("tituloSection").toString();
        String resposta = G1214.get("resposta").toString();

        String feedbackRespostaAntesEnviarEsperada = G1214.get("feedbackRespostaAntesEnviar").toString();
        String feedbackRespostaAntesEnviar;                    

        String tituloQuestao = G1214.get("tituloQuestao").toString();
        String textoQuestao = G1214.get("textoQuestao").toString();
        String respostaQuestao = G1214.get("respostaQuestao").toString();
        String porcentagemQuestao = G1214.get("porcentagemQuestao").toString();

        String coringa1 = G1214.get("coringa_1").toString();
        String coringa2 = G1214.get("coringa_2").toString();

        String feedback = G1214.get("feedback").toString();
    
        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        facilitador.clicarAdicionarQuestao();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("item_qtype_calculated")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestao);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestao);

        driver.findElement(By.id("id_answer_0")).sendKeys(respostaQuestao);

        driver.findElement(By.id("id_fraction_0")).sendKeys(porcentagemQuestao);

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.id("id_addbutton")).click();

        driver.findElement(By.id("id_number_1")).clear();

        driver.findElement(By.id("id_number_2")).clear();

        driver.findElement(By.id("id_number_1")).sendKeys(coringa1);

        driver.findElement(By.id("id_number_2")).sendKeys(coringa2);

        driver.findElement(By.id("id_savechanges")).click();

        driver.findElement(By.className("editing_maxmark")).click();

        facilitador.pausaSegundos(2);

        Actions acao = new Actions(driver);
        acao.sendKeys(Keys.NUMPAD1).build().perform();
        acao.sendKeys(Keys.NUMPAD0).build().perform();
        acao.sendKeys(Keys.ENTER).build().perform();

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarMudarPapel();
        facilitador.clicarPapelEstudante();

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("more-nav")).findElements(By.tagName("li")).get(0).click();

        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.clicarModuloSection(section, tituloSection);

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("form-control")).sendKeys(resposta);

        driver.findElement(By.id("mod_quiz-next-nav")).click();

        facilitador.pausaSegundos(2);

        feedbackRespostaAntesEnviar = driver.findElement(By.tagName("tbody")).findElement(By.className("c1")).getText();

        assertEquals(feedbackRespostaAntesEnviarEsperada, feedbackRespostaAntesEnviar);

        driver.findElement(By.className("btn-primary")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();

        assertTrue(driver.findElement(By.className("info")).getText().contains(feedback));
    }

    @Test
    /* Criação do questionário com questão Calculado com resposta vazia */
    public void G1215() throws InterruptedException {
        JSONObject G1215 = (JSONObject) jsonObject.get("G1215");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1215.get("tituloSection").toString();
        String resposta = G1215.get("resposta").toString();

        String feedbackRespostaAntesEnviarEsperada = G1215.get("feedbackRespostaAntesEnviar").toString();
        String feedbackRespostaAntesEnviar;                    

        String tituloQuestao = G1215.get("tituloQuestao").toString();
        String textoQuestao = G1215.get("textoQuestao").toString();
        String respostaQuestao = G1215.get("respostaQuestao").toString();
        String porcentagemQuestao = G1215.get("porcentagemQuestao").toString();

        String coringa1 = G1215.get("coringa_1").toString();
        String coringa2 = G1215.get("coringa_2").toString();

        String feedback = G1215.get("feedback").toString();
    
        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        facilitador.clicarAdicionarQuestao();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("item_qtype_calculated")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestao);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestao);

        driver.findElement(By.id("id_answer_0")).sendKeys(respostaQuestao);

        driver.findElement(By.id("id_fraction_0")).sendKeys(porcentagemQuestao);

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.id("id_addbutton")).click();

        driver.findElement(By.id("id_number_1")).clear();

        driver.findElement(By.id("id_number_2")).clear();

        driver.findElement(By.id("id_number_1")).sendKeys(coringa1);

        driver.findElement(By.id("id_number_2")).sendKeys(coringa2);

        driver.findElement(By.id("id_savechanges")).click();

        driver.findElement(By.className("editing_maxmark")).click();

        facilitador.pausaSegundos(2);

        Actions acao = new Actions(driver);
        acao.sendKeys(Keys.NUMPAD1).build().perform();
        acao.sendKeys(Keys.NUMPAD0).build().perform();
        acao.sendKeys(Keys.ENTER).build().perform();

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarMudarPapel();
        facilitador.clicarPapelEstudante();

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("more-nav")).findElements(By.tagName("li")).get(0).click();

        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.clicarModuloSection(section, tituloSection);

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("form-control")).sendKeys(resposta);

        driver.findElement(By.id("mod_quiz-next-nav")).click();

        facilitador.pausaSegundos(2);

        feedbackRespostaAntesEnviar = driver.findElement(By.tagName("tbody")).findElement(By.className("c1")).getText();

        assertEquals(feedbackRespostaAntesEnviarEsperada, feedbackRespostaAntesEnviar);

        driver.findElement(By.className("btn-primary")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();

        assertTrue(driver.findElement(By.className("info")).getText().contains(feedback));
    }

    @Test
    /* Criação do questionário com questão Cálculo Simples com resposta válida correta */
    public void G1216() throws InterruptedException {
        JSONObject G1216 = (JSONObject) jsonObject.get("G1216");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1216.get("tituloSection").toString();
        String resposta = G1216.get("resposta").toString();

        String feedbackRespostaAntesEnviarEsperada = G1216.get("feedbackRespostaAntesEnviar").toString();
        String feedbackRespostaAntesEnviar;                    

        String tituloQuestao = G1216.get("tituloQuestao").toString();
        String textoQuestao = G1216.get("textoQuestao").toString();
        String respostaQuestao = G1216.get("respostaQuestao").toString();
        String porcentagemQuestao = G1216.get("porcentagemQuestao").toString();

        String coringa1 = G1216.get("coringa_1").toString();

        String feedback = G1216.get("feedback").toString();
    
        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        facilitador.clicarAdicionarQuestao();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("item_qtype_calculatedsimple")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestao);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestao);

        driver.findElement(By.id("id_answer_0")).sendKeys(respostaQuestao);

        driver.findElement(By.id("id_fraction_0")).sendKeys(porcentagemQuestao);

        driver.findElement(By.id("id_analyzequestion")).click();

        driver.findElement(By.id("id_addbutton")).click();

        facilitador.clicarExpandirTudo();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("moreless-toggler")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("id_number_1")).clear();

        driver.findElement(By.id("id_number_1")).sendKeys(coringa1);

        driver.findElement(By.id("id_updatedatasets")).click();

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.className("editing_maxmark")).click();

        facilitador.pausaSegundos(2);

        Actions acao = new Actions(driver);
        acao.sendKeys(Keys.NUMPAD1).build().perform();
        acao.sendKeys(Keys.NUMPAD0).build().perform();
        acao.sendKeys(Keys.ENTER).build().perform();

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarMudarPapel();
        facilitador.clicarPapelEstudante();

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("more-nav")).findElements(By.tagName("li")).get(0).click();

        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.clicarModuloSection(section, tituloSection);

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("form-control")).sendKeys(resposta);

        driver.findElement(By.id("mod_quiz-next-nav")).click();

        facilitador.pausaSegundos(2);

        feedbackRespostaAntesEnviar = driver.findElement(By.tagName("tbody")).findElement(By.className("c1")).getText();

        assertEquals(feedbackRespostaAntesEnviarEsperada, feedbackRespostaAntesEnviar);

        driver.findElement(By.className("btn-primary")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();

        assertTrue(driver.findElement(By.className("info")).getText().contains(feedback));
    }

    @Test
    /* Criação do questionário com questão Cálculo Simples com resposta válida incorreta */
    public void G1217() throws InterruptedException {
        JSONObject G1217 = (JSONObject) jsonObject.get("G1217");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1217.get("tituloSection").toString();
        String resposta = G1217.get("resposta").toString();

        String feedbackRespostaAntesEnviarEsperada = G1217.get("feedbackRespostaAntesEnviar").toString();
        String feedbackRespostaAntesEnviar;                    

        String tituloQuestao = G1217.get("tituloQuestao").toString();
        String textoQuestao = G1217.get("textoQuestao").toString();
        String respostaQuestao = G1217.get("respostaQuestao").toString();
        String porcentagemQuestao = G1217.get("porcentagemQuestao").toString();

        String coringa1 = G1217.get("coringa_1").toString();

        String feedback = G1217.get("feedback").toString();
    
        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        facilitador.clicarAdicionarQuestao();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("item_qtype_calculatedsimple")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestao);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestao);

        driver.findElement(By.id("id_answer_0")).sendKeys(respostaQuestao);

        driver.findElement(By.id("id_fraction_0")).sendKeys(porcentagemQuestao);

        driver.findElement(By.id("id_analyzequestion")).click();

        driver.findElement(By.id("id_addbutton")).click();

        facilitador.clicarExpandirTudo();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("moreless-toggler")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("id_number_1")).clear();

        driver.findElement(By.id("id_number_1")).sendKeys(coringa1);

        driver.findElement(By.id("id_updatedatasets")).click();

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.className("editing_maxmark")).click();

        facilitador.pausaSegundos(2);

        Actions acao = new Actions(driver);
        acao.sendKeys(Keys.NUMPAD1).build().perform();
        acao.sendKeys(Keys.NUMPAD0).build().perform();
        acao.sendKeys(Keys.ENTER).build().perform();

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarMudarPapel();
        facilitador.clicarPapelEstudante();

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("more-nav")).findElements(By.tagName("li")).get(0).click();

        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.clicarModuloSection(section, tituloSection);

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("form-control")).sendKeys(resposta);

        driver.findElement(By.id("mod_quiz-next-nav")).click();

        facilitador.pausaSegundos(2);

        feedbackRespostaAntesEnviar = driver.findElement(By.tagName("tbody")).findElement(By.className("c1")).getText();

        assertEquals(feedbackRespostaAntesEnviarEsperada, feedbackRespostaAntesEnviar);

        driver.findElement(By.className("btn-primary")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();

        assertTrue(driver.findElement(By.className("info")).getText().contains(feedback));
    }

    @Test
    /* Criação do questionário com questão Cálculo Simples com resposta vazia */
    public void G1218() throws InterruptedException {
        JSONObject G1218 = (JSONObject) jsonObject.get("G1218");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1218.get("tituloSection").toString();
        String resposta = G1218.get("resposta").toString();

        String feedbackRespostaAntesEnviarEsperada = G1218.get("feedbackRespostaAntesEnviar").toString();
        String feedbackRespostaAntesEnviar;                    

        String tituloQuestao = G1218.get("tituloQuestao").toString();
        String textoQuestao = G1218.get("textoQuestao").toString();
        String respostaQuestao = G1218.get("respostaQuestao").toString();
        String porcentagemQuestao = G1218.get("porcentagemQuestao").toString();

        String coringa1 = G1218.get("coringa_1").toString();

        String feedback = G1218.get("feedback").toString();
    
        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        facilitador.clicarAdicionarQuestao();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("item_qtype_calculatedsimple")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestao);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestao);

        driver.findElement(By.id("id_answer_0")).sendKeys(respostaQuestao);

        driver.findElement(By.id("id_fraction_0")).sendKeys(porcentagemQuestao);

        driver.findElement(By.id("id_analyzequestion")).click();

        driver.findElement(By.id("id_addbutton")).click();

        facilitador.clicarExpandirTudo();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("moreless-toggler")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("id_number_1")).clear();

        driver.findElement(By.id("id_number_1")).sendKeys(coringa1);

        driver.findElement(By.id("id_updatedatasets")).click();

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.className("editing_maxmark")).click();

        facilitador.pausaSegundos(2);

        Actions acao = new Actions(driver);
        acao.sendKeys(Keys.NUMPAD1).build().perform();
        acao.sendKeys(Keys.NUMPAD0).build().perform();
        acao.sendKeys(Keys.ENTER).build().perform();

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarMudarPapel();
        facilitador.clicarPapelEstudante();

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("more-nav")).findElements(By.tagName("li")).get(0).click();

        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.clicarModuloSection(section, tituloSection);

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("form-control")).sendKeys(resposta);

        driver.findElement(By.id("mod_quiz-next-nav")).click();

        facilitador.pausaSegundos(2);

        feedbackRespostaAntesEnviar = driver.findElement(By.tagName("tbody")).findElement(By.className("c1")).getText();

        assertEquals(feedbackRespostaAntesEnviarEsperada, feedbackRespostaAntesEnviar);

        driver.findElement(By.className("btn-primary")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();

        assertTrue(driver.findElement(By.className("info")).getText().contains(feedback));
    }

    @Test
    /* Criação do questionário com questão Calculado com resposta válida correta */
    public void G1219() throws InterruptedException {
        JSONObject G1219 = (JSONObject) jsonObject.get("G1219");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1219.get("tituloSection").toString();
        String resposta = G1219.get("resposta").toString();

        String feedbackRespostaAntesEnviarEsperada = G1219.get("feedbackRespostaAntesEnviar").toString();
        String feedbackRespostaAntesEnviar;                    

        String tituloQuestao = G1219.get("tituloQuestao").toString();
        String textoQuestao = G1219.get("textoQuestao").toString();
        String respostaQuestao = G1219.get("respostaQuestao").toString();
        String porcentagemQuestao = G1219.get("porcentagemQuestao").toString();

        String coringa1 = G1219.get("coringa_1").toString();

        String feedback = G1219.get("feedback").toString();
    
        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        facilitador.clicarAdicionarQuestao();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("item_qtype_calculatedsimple")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestao);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestao);

        driver.findElement(By.id("id_answer_0")).sendKeys(respostaQuestao);

        driver.findElement(By.id("id_fraction_0")).sendKeys(porcentagemQuestao);

        driver.findElement(By.id("id_analyzequestion")).click();

        driver.findElement(By.id("id_addbutton")).click();

        facilitador.clicarExpandirTudo();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("moreless-toggler")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("id_number_1")).clear();

        driver.findElement(By.id("id_number_1")).sendKeys(coringa1);

        driver.findElement(By.id("id_updatedatasets")).click();

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.className("editing_maxmark")).click();

        facilitador.pausaSegundos(2);

        Actions acao = new Actions(driver);
        acao.sendKeys(Keys.NUMPAD1).build().perform();
        acao.sendKeys(Keys.NUMPAD0).build().perform();
        acao.sendKeys(Keys.ENTER).build().perform();

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarMudarPapel();
        facilitador.clicarPapelEstudante();

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("more-nav")).findElements(By.tagName("li")).get(0).click();

        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.clicarModuloSection(section, tituloSection);

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("form-control")).sendKeys(resposta);

        driver.findElement(By.id("mod_quiz-next-nav")).click();

        facilitador.pausaSegundos(2);

        feedbackRespostaAntesEnviar = driver.findElement(By.tagName("tbody")).findElement(By.className("c1")).getText();

        assertEquals(feedbackRespostaAntesEnviarEsperada, feedbackRespostaAntesEnviar);

        driver.findElement(By.className("btn-primary")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();

        assertTrue(driver.findElement(By.className("info")).getText().contains(feedback));
    }

    @Test
    /* Criação do questionário com questão Múltipla Escolha com resposta válida correta */
    public void G1220() throws InterruptedException {
        JSONObject G1220 = (JSONObject) jsonObject.get("G1220");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1220.get("tituloSection").toString();

        String feedbackRespostaAntesEnviarEsperada = G1220.get("feedbackRespostaAntesEnviar").toString();
        String feedbackRespostaAntesEnviar;                    

        String tituloQuestao = G1220.get("tituloQuestao").toString();
        String textoQuestao = G1220.get("textoQuestao").toString();
        String respostaQuestao = G1220.get("respostaQuestao").toString();
        String porcentagemQuestao = G1220.get("porcentagemQuestao").toString();

        String respostaQuestao1 = G1220.get("respostaQuestao1").toString();
        String respostaQuestao2 = G1220.get("respostaQuestao2").toString();

        String coringa1 = G1220.get("coringa_1").toString();
        String coringa2 = G1220.get("coringa_2").toString();

        String feedback = G1220.get("feedback").toString();

        String marcarAlternativa = G1220.get("marcarAlternativa").toString();
    
        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        facilitador.clicarAdicionarQuestao();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("item_qtype_calculatedmulti")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestao);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestao);

        driver.findElement(By.id("id_answer_0")).sendKeys(respostaQuestao);

        driver.findElement(By.id("id_fraction_0")).sendKeys(porcentagemQuestao);

        driver.findElement(By.id("id_answer_1")).sendKeys(respostaQuestao1);

        driver.findElement(By.id("id_answer_2")).sendKeys(respostaQuestao2);

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.id("id_addbutton")).click();

        driver.findElement(By.id("id_number_1")).clear();

        driver.findElement(By.id("id_number_2")).clear();

        driver.findElement(By.id("id_number_1")).sendKeys(coringa1);

        driver.findElement(By.id("id_number_2")).sendKeys(coringa2);

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("id_savechanges")).click();

        driver.findElement(By.className("editing_maxmark")).click();

        facilitador.pausaSegundos(2);

        Actions acao = new Actions(driver);
        acao.sendKeys(Keys.NUMPAD1).build().perform();
        acao.sendKeys(Keys.NUMPAD0).build().perform();
        acao.sendKeys(Keys.ENTER).build().perform();

        facilitador.pausaSegundos(4);

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarMudarPapel();
        facilitador.clicarPapelEstudante();

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("more-nav")).findElements(By.tagName("li")).get(0).click();

        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.clicarModuloSection(section, tituloSection);

        driver.findElement(By.className("btn-primary")).click();

        List<WebElement> listaQuestoes = driver.findElements(By.cssSelector("div[data-region='answer-label']"));

        for(WebElement questao : listaQuestoes) {
            if(questao.getText().contains(marcarAlternativa)) {
                questao.click();
            }
        }

        driver.findElement(By.id("mod_quiz-next-nav")).click();

        facilitador.pausaSegundos(2);

        feedbackRespostaAntesEnviar = driver.findElement(By.tagName("tbody")).findElement(By.className("c1")).getText();

        assertEquals(feedbackRespostaAntesEnviarEsperada, feedbackRespostaAntesEnviar);

        driver.findElement(By.className("btn-primary")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();

        assertTrue(driver.findElement(By.className("info")).getText().contains(feedback));
    }

    @Test
    /* Criação do questionário com questão Múltipla Escolha com resposta válida incorreta */
    public void G1221() throws InterruptedException {
        JSONObject G1221 = (JSONObject) jsonObject.get("G1221");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1221.get("tituloSection").toString();

        String feedbackRespostaAntesEnviarEsperada = G1221.get("feedbackRespostaAntesEnviar").toString();
        String feedbackRespostaAntesEnviar;                    

        String tituloQuestao = G1221.get("tituloQuestao").toString();
        String textoQuestao = G1221.get("textoQuestao").toString();
        String respostaQuestao = G1221.get("respostaQuestao").toString();
        String porcentagemQuestao = G1221.get("porcentagemQuestao").toString();

        String respostaQuestao1 = G1221.get("respostaQuestao1").toString();
        String respostaQuestao2 = G1221.get("respostaQuestao2").toString();

        String coringa1 = G1221.get("coringa_1").toString();
        String coringa2 = G1221.get("coringa_2").toString();

        String feedback = G1221.get("feedback").toString();

        String marcarAlternativa = G1221.get("marcarAlternativa").toString();
    
        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        facilitador.clicarAdicionarQuestao();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("item_qtype_calculatedmulti")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestao);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestao);

        driver.findElement(By.id("id_answer_0")).sendKeys(respostaQuestao);

        driver.findElement(By.id("id_fraction_0")).sendKeys(porcentagemQuestao);

        driver.findElement(By.id("id_answer_1")).sendKeys(respostaQuestao1);

        driver.findElement(By.id("id_answer_2")).sendKeys(respostaQuestao2);

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.id("id_addbutton")).click();

        driver.findElement(By.id("id_number_1")).clear();

        driver.findElement(By.id("id_number_2")).clear();

        driver.findElement(By.id("id_number_1")).sendKeys(coringa1);

        driver.findElement(By.id("id_number_2")).sendKeys(coringa2);

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("id_number_1")).clear();

        driver.findElement(By.id("id_number_1")).sendKeys(coringa1);

        driver.findElement(By.id("id_savechanges")).click();

        driver.findElement(By.className("editing_maxmark")).click();

        facilitador.pausaSegundos(2);

        Actions acao = new Actions(driver);
        acao.sendKeys(Keys.NUMPAD1).build().perform();
        acao.sendKeys(Keys.NUMPAD0).build().perform();
        acao.sendKeys(Keys.ENTER).build().perform();

        facilitador.pausaSegundos(4);

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarMudarPapel();
        facilitador.clicarPapelEstudante();

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("more-nav")).findElements(By.tagName("li")).get(0).click();

        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.clicarModuloSection(section, tituloSection);

        driver.findElement(By.className("btn-primary")).click();

        List<WebElement> listaQuestoes = driver.findElements(By.cssSelector("div[data-region='answer-label']"));

        for(WebElement questao : listaQuestoes) {
            if(questao.getText().contains(marcarAlternativa)) {
                questao.click();
            }
        }

        driver.findElement(By.id("mod_quiz-next-nav")).click();

        facilitador.pausaSegundos(2);

        feedbackRespostaAntesEnviar = driver.findElement(By.tagName("tbody")).findElement(By.className("c1")).getText();

        assertEquals(feedbackRespostaAntesEnviarEsperada, feedbackRespostaAntesEnviar);

        driver.findElement(By.className("btn-primary")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();

        assertTrue(driver.findElement(By.className("info")).getText().contains(feedback));
    }

    @Test
    /* Criação do questionário com questão Múltipla Escolha com resposta vazia */
    public void G1223() throws InterruptedException {
        JSONObject G1223 = (JSONObject) jsonObject.get("G1223");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1223.get("tituloSection").toString();

        String feedbackRespostaAntesEnviarEsperada = G1223.get("feedbackRespostaAntesEnviar").toString();
        String feedbackRespostaAntesEnviar;                    

        String tituloQuestao = G1223.get("tituloQuestao").toString();
        String textoQuestao = G1223.get("textoQuestao").toString();
        String respostaQuestao = G1223.get("respostaQuestao").toString();
        String porcentagemQuestao = G1223.get("porcentagemQuestao").toString();

        String respostaQuestao1 = G1223.get("respostaQuestao1").toString();
        String respostaQuestao2 = G1223.get("respostaQuestao2").toString();

        String coringa1 = G1223.get("coringa_1").toString();
        String coringa2 = G1223.get("coringa_2").toString();

        String feedback = G1223.get("feedback").toString();
    
        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        facilitador.clicarAdicionarQuestao();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("item_qtype_calculatedmulti")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestao);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestao);

        driver.findElement(By.id("id_answer_0")).sendKeys(respostaQuestao);

        driver.findElement(By.id("id_fraction_0")).sendKeys(porcentagemQuestao);

        driver.findElement(By.id("id_answer_1")).sendKeys(respostaQuestao1);

        driver.findElement(By.id("id_answer_2")).sendKeys(respostaQuestao2);

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.id("id_addbutton")).click();

        driver.findElement(By.id("id_number_1")).clear();

        driver.findElement(By.id("id_number_2")).clear();

        driver.findElement(By.id("id_number_1")).sendKeys(coringa1);

        driver.findElement(By.id("id_number_2")).sendKeys(coringa2);

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("id_number_1")).clear();

        driver.findElement(By.id("id_number_1")).sendKeys(coringa1);

        driver.findElement(By.id("id_savechanges")).click();

        driver.findElement(By.className("editing_maxmark")).click();

        facilitador.pausaSegundos(2);

        Actions acao = new Actions(driver);
        acao.sendKeys(Keys.NUMPAD1).build().perform();
        acao.sendKeys(Keys.NUMPAD0).build().perform();
        acao.sendKeys(Keys.ENTER).build().perform();

        facilitador.pausaSegundos(4);

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarMudarPapel();
        facilitador.clicarPapelEstudante();

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("more-nav")).findElements(By.tagName("li")).get(0).click();

        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.clicarModuloSection(section, tituloSection);

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.id("mod_quiz-next-nav")).click();

        facilitador.pausaSegundos(2);

        feedbackRespostaAntesEnviar = driver.findElement(By.tagName("tbody")).findElement(By.className("c1")).getText();

        assertEquals(feedbackRespostaAntesEnviarEsperada, feedbackRespostaAntesEnviar);

        driver.findElement(By.className("btn-primary")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();

        assertTrue(driver.findElement(By.className("info")).getText().contains(feedback));
    }

    @Test
    /* Criação do questionário com questão SPF com resposta válida correta */
    public void G1224() throws InterruptedException {
        JSONObject G1224 = (JSONObject) jsonObject.get("G1224");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1224.get("tituloSection").toString();

        String resposta = G1224.get("resposta").toString();

        String feedbackRespostaAntesEnviarEsperada = G1224.get("feedbackRespostaAntesEnviar").toString();
        String feedbackRespostaAntesEnviar;                    

        String tituloQuestao = G1224.get("tituloQuestao").toString();
        String textoQuestao = G1224.get("textoQuestao").toString();

        String opcao1 = G1224.get("opcao_1").toString();
        String opcao2 = G1224.get("opcao_2").toString();
        String opcao3 = G1224.get("opcao_3").toString();
        String opcao4 = G1224.get("opcao_4").toString();

        String feedback = G1224.get("feedback").toString();
    
        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(3);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        facilitador.clicarAdicionarQuestao();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("item_qtype_gapselect")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestao);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestao);

        driver.findElement(By.id("id_choices_0_answer")).sendKeys(opcao1);
        driver.findElement(By.id("id_choices_1_answer")).sendKeys(opcao2);
        driver.findElement(By.id("id_choices_2_answer")).sendKeys(opcao3);
        driver.findElement(By.id("id_choices_3_answer")).sendKeys(opcao4);

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.className("editing_maxmark")).click();

        facilitador.pausaSegundos(2);

        Actions acao = new Actions(driver);
        acao.sendKeys(Keys.NUMPAD1).build().perform();
        acao.sendKeys(Keys.NUMPAD0).build().perform();
        acao.sendKeys(Keys.ENTER).build().perform();

        facilitador.pausaSegundos(4);

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarMudarPapel();
        facilitador.clicarPapelEstudante();

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("more-nav")).findElements(By.tagName("li")).get(0).click();

        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.clicarModuloSection(section, tituloSection);

        driver.findElement(By.className("btn-primary")).click();


        driver.findElement(By.className("custom-select")).sendKeys(resposta);

        driver.findElement(By.id("mod_quiz-next-nav")).click();

        facilitador.pausaSegundos(2);

        feedbackRespostaAntesEnviar = driver.findElement(By.tagName("tbody")).findElement(By.className("c1")).getText();

        assertEquals(feedbackRespostaAntesEnviarEsperada, feedbackRespostaAntesEnviar);

        driver.findElement(By.className("btn-primary")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();

        assertTrue(driver.findElement(By.className("info")).getText().contains(feedback));
    }

    @Test
    /* Criação do questionário com questão SPF com resposta válida incorreta */
    public void G1225() throws InterruptedException {
        JSONObject G1225 = (JSONObject) jsonObject.get("G1225");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1225.get("tituloSection").toString();

        String resposta = G1225.get("resposta").toString();

        String feedbackRespostaAntesEnviarEsperada = G1225.get("feedbackRespostaAntesEnviar").toString();
        String feedbackRespostaAntesEnviar;                    

        String tituloQuestao = G1225.get("tituloQuestao").toString();
        String textoQuestao = G1225.get("textoQuestao").toString();

        String opcao1 = G1225.get("opcao_1").toString();
        String opcao2 = G1225.get("opcao_2").toString();
        String opcao3 = G1225.get("opcao_3").toString();
        String opcao4 = G1225.get("opcao_4").toString();

        String feedback = G1225.get("feedback").toString();
    
        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        facilitador.clicarAdicionarQuestao();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("item_qtype_gapselect")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestao);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestao);

        driver.findElement(By.id("id_choices_0_answer")).sendKeys(opcao1);
        driver.findElement(By.id("id_choices_1_answer")).sendKeys(opcao2);
        driver.findElement(By.id("id_choices_2_answer")).sendKeys(opcao3);
        driver.findElement(By.id("id_choices_3_answer")).sendKeys(opcao4);

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.className("editing_maxmark")).click();

        facilitador.pausaSegundos(2);

        Actions acao = new Actions(driver);
        acao.sendKeys(Keys.NUMPAD1).build().perform();
        acao.sendKeys(Keys.NUMPAD0).build().perform();
        acao.sendKeys(Keys.ENTER).build().perform();

        facilitador.pausaSegundos(4);

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarMudarPapel();
        facilitador.clicarPapelEstudante();

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("more-nav")).findElements(By.tagName("li")).get(0).click();

        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.clicarModuloSection(section, tituloSection);

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("custom-select")).sendKeys(resposta);

        driver.findElement(By.id("mod_quiz-next-nav")).click();

        facilitador.pausaSegundos(2);

        feedbackRespostaAntesEnviar = driver.findElement(By.tagName("tbody")).findElement(By.className("c1")).getText();

        assertEquals(feedbackRespostaAntesEnviarEsperada, feedbackRespostaAntesEnviar);

        driver.findElement(By.className("btn-primary")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();

        assertTrue(driver.findElement(By.className("info")).getText().contains(feedback));
    }

    @Test
    /* Criação do questionário com questão SPF com resposta vazia */
    public void G1227() throws InterruptedException {
        JSONObject G1227 = (JSONObject) jsonObject.get("G1227");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1227.get("tituloSection").toString();

        String feedbackRespostaAntesEnviarEsperada = G1227.get("feedbackRespostaAntesEnviar").toString();
        String feedbackRespostaAntesEnviar;                    

        String tituloQuestao = G1227.get("tituloQuestao").toString();
        String textoQuestao = G1227.get("textoQuestao").toString();

        String opcao1 = G1227.get("opcao_1").toString();
        String opcao2 = G1227.get("opcao_2").toString();
        String opcao3 = G1227.get("opcao_3").toString();
        String opcao4 = G1227.get("opcao_4").toString();

        String feedback = G1227.get("feedback").toString();
    
        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        facilitador.clicarAdicionarQuestao();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("item_qtype_gapselect")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestao);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestao);

        driver.findElement(By.id("id_choices_0_answer")).sendKeys(opcao1);
        driver.findElement(By.id("id_choices_1_answer")).sendKeys(opcao2);
        driver.findElement(By.id("id_choices_2_answer")).sendKeys(opcao3);
        driver.findElement(By.id("id_choices_3_answer")).sendKeys(opcao4);

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.className("editing_maxmark")).click();

        facilitador.pausaSegundos(2);

        Actions acao = new Actions(driver);
        acao.sendKeys(Keys.NUMPAD1).build().perform();
        acao.sendKeys(Keys.NUMPAD0).build().perform();
        acao.sendKeys(Keys.ENTER).build().perform();

        facilitador.pausaSegundos(4);

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarMudarPapel();
        facilitador.clicarPapelEstudante();

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("more-nav")).findElements(By.tagName("li")).get(0).click();

        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.clicarModuloSection(section, tituloSection);

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.id("mod_quiz-next-nav")).click();

        facilitador.pausaSegundos(2);

        feedbackRespostaAntesEnviar = driver.findElement(By.tagName("tbody")).findElement(By.className("c1")).getText();

        assertEquals(feedbackRespostaAntesEnviarEsperada, feedbackRespostaAntesEnviar);

        driver.findElement(By.className("btn-primary")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();

        assertTrue(driver.findElement(By.className("info")).getText().contains(feedback));
    }

    @Test
    /* Criação do questionário com questão Cloze com resposta válida correta  */
    public void G1228() throws InterruptedException {
        JSONObject G1228 = (JSONObject) jsonObject.get("G1228");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1228.get("tituloSection").toString();

        String feedbackRespostaAntesEnviarEsperada = G1228.get("feedbackRespostaAntesEnviar").toString();
        String feedbackRespostaAntesEnviar;                    

        String tituloQuestao = G1228.get("tituloQuestao").toString();
        String textoQuestao = G1228.get("textoQuestao").toString();

        String feedback = G1228.get("feedback").toString();

        String resposta1 = G1228.get("resposta_1").toString();
        String resposta2 = G1228.get("resposta_2").toString();
    
        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        facilitador.clicarAdicionarQuestao();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("item_qtype_multianswer")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestao);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestao);

        driver.findElement(By.id("id_analyzequestion")).click();

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.className("editing_maxmark")).click();

        facilitador.pausaSegundos(2);

        Actions acao = new Actions(driver);
        acao.sendKeys(Keys.NUMPAD1).build().perform();
        acao.sendKeys(Keys.NUMPAD0).build().perform();
        acao.sendKeys(Keys.ENTER).build().perform();

        facilitador.pausaSegundos(4);

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarMudarPapel();
        facilitador.clicarPapelEstudante();

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("more-nav")).findElements(By.tagName("li")).get(0).click();

        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.clicarModuloSection(section, tituloSection);

        driver.findElement(By.className("btn-primary")).click();

        driver.findElements(By.tagName("select")).get(0).sendKeys(resposta1);
        driver.findElements(By.tagName("select")).get(1).sendKeys(resposta2);

        driver.findElement(By.id("mod_quiz-next-nav")).click();

        facilitador.pausaSegundos(2);

        feedbackRespostaAntesEnviar = driver.findElement(By.tagName("tbody")).findElement(By.className("c1")).getText();

        assertEquals(feedbackRespostaAntesEnviarEsperada, feedbackRespostaAntesEnviar);

        driver.findElement(By.className("btn-primary")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();

        assertTrue(driver.findElement(By.className("info")).getText().contains(feedback));
    }

    @Test
    /* Criação do questionário com questão Cloze com resposta válida incorreta  */
    public void G1230() throws InterruptedException {
        JSONObject G1230 = (JSONObject) jsonObject.get("G1230");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1230.get("tituloSection").toString();

        String feedbackRespostaAntesEnviarEsperada = G1230.get("feedbackRespostaAntesEnviar").toString();
        String feedbackRespostaAntesEnviar;                    

        String tituloQuestao = G1230.get("tituloQuestao").toString();
        String textoQuestao = G1230.get("textoQuestao").toString();

        String feedback = G1230.get("feedback").toString();

        String resposta1 = G1230.get("resposta_1").toString();
        String resposta2 = G1230.get("resposta_2").toString();
    
        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        facilitador.clicarAdicionarQuestao();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("item_qtype_multianswer")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestao);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestao);

        driver.findElement(By.id("id_analyzequestion")).click();

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.className("editing_maxmark")).click();

        facilitador.pausaSegundos(2);

        Actions acao = new Actions(driver);
        acao.sendKeys(Keys.NUMPAD1).build().perform();
        acao.sendKeys(Keys.NUMPAD0).build().perform();
        acao.sendKeys(Keys.ENTER).build().perform();

        facilitador.pausaSegundos(4);

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarMudarPapel();
        facilitador.clicarPapelEstudante();

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("more-nav")).findElements(By.tagName("li")).get(0).click();

        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.clicarModuloSection(section, tituloSection);

        driver.findElement(By.className("btn-primary")).click();

        driver.findElements(By.tagName("select")).get(0).sendKeys(resposta1);
        driver.findElements(By.tagName("select")).get(1).sendKeys(resposta2);

        driver.findElement(By.id("mod_quiz-next-nav")).click();

        facilitador.pausaSegundos(2);

        feedbackRespostaAntesEnviar = driver.findElement(By.tagName("tbody")).findElement(By.className("c1")).getText();

        assertEquals(feedbackRespostaAntesEnviarEsperada, feedbackRespostaAntesEnviar);

        driver.findElement(By.className("btn-primary")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();

        assertTrue(driver.findElement(By.className("info")).getText().contains(feedback));
    }

    
    @Test
    /* Criação do questionário com várias questões */
    public void G1232() throws InterruptedException {
        JSONObject G1232 = (JSONObject) jsonObject.get("G1232");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1232.get("tituloSection").toString();

        String tituloQuestaoCloze = G1232.get("tituloQuestaoCloze").toString();
        String textoQuestaoCloze = G1232.get("textoQuestaoCloze").toString();

        String textoQuestaoCalculated = G1232.get("textoQuestaoCalculated").toString();

        String respostaQuestaoCalculated = G1232.get("respostaQuestaoCalculated").toString();

        String tituloQuestaoCalculated = G1232.get("tituloQuestaoCalculated").toString();

        String coringa1 = G1232.get("coringa_1").toString();
        String coringa2 = G1232.get("coringa_2").toString();

        String porcentagemQuestaoCalculated = G1232.get("porcentagemQuestaoCalculated").toString();

        String feedback = G1232.get("feedback").toString();

        String feedbackRespostaAntesEnviarEsperada = G1232.get("feedbackRespostaAntesEnviarEsperada").toString();

        String feedbackRespostaAntesEnviar;

        String resposta1 = G1232.get("resposta_1").toString();
        String resposta2 = G1232.get("resposta_2").toString();

        String resposta = G1232.get("resposta").toString();
    
        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        facilitador.clicarAdicionarQuestao();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("item_qtype_multianswer")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestaoCloze);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestaoCloze);

        driver.findElement(By.id("id_analyzequestion")).click();

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);


        /* CALCULATED */

        driver.findElement(By.id("item_qtype_calculated")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestaoCalculated);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestaoCalculated);

        driver.findElement(By.id("id_answer_0")).sendKeys(respostaQuestaoCalculated);

        driver.findElement(By.id("id_fraction_0")).sendKeys(porcentagemQuestaoCalculated);

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.id("id_addbutton")).click();

        driver.findElement(By.id("id_number_1")).clear();

        driver.findElement(By.id("id_number_2")).clear();

        driver.findElement(By.id("id_number_1")).sendKeys(coringa1);

        driver.findElement(By.id("id_number_2")).sendKeys(coringa2);

        driver.findElement(By.id("id_savechanges")).click();


        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarMudarPapel();
        facilitador.clicarPapelEstudante();

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("more-nav")).findElements(By.tagName("li")).get(0).click();

        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.clicarModuloSection(section, tituloSection);

        driver.findElement(By.className("btn-primary")).click();

        driver.findElements(By.tagName("select")).get(0).sendKeys(resposta1);
        driver.findElements(By.tagName("select")).get(1).sendKeys(resposta2);

        driver.findElement(By.className("form-control")).sendKeys(resposta);

        driver.findElement(By.id("mod_quiz-next-nav")).click();

        facilitador.pausaSegundos(2);

        feedbackRespostaAntesEnviar = driver.findElement(By.tagName("tbody")).findElement(By.className("c1")).getText();

        assertEquals(feedbackRespostaAntesEnviarEsperada, feedbackRespostaAntesEnviar);

        driver.findElement(By.className("btn-primary")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();

        assertTrue(driver.findElement(By.className("info")).getText().contains(feedback));
    }

    

    @Test
    /* Criação do questionário com questão Cloze com resposta vazia */
    public void G1242() throws InterruptedException {
        JSONObject G1242 = (JSONObject) jsonObject.get("G1242");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1242.get("tituloSection").toString();

        String feedbackRespostaAntesEnviarEsperada = G1242.get("feedbackRespostaAntesEnviar").toString();
        String feedbackRespostaAntesEnviar;                    

        String tituloQuestao = G1242.get("tituloQuestao").toString();
        String textoQuestao = G1242.get("textoQuestao").toString();

        String feedback = G1242.get("feedback").toString();
    
        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        facilitador.clicarAdicionarQuestao();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("item_qtype_multianswer")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestao);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestao);

        driver.findElement(By.id("id_analyzequestion")).click();

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.className("editing_maxmark")).click();

        facilitador.pausaSegundos(2);

        Actions acao = new Actions(driver);
        acao.sendKeys(Keys.NUMPAD1).build().perform();
        acao.sendKeys(Keys.NUMPAD0).build().perform();
        acao.sendKeys(Keys.ENTER).build().perform();

        facilitador.pausaSegundos(4);

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarMudarPapel();
        facilitador.clicarPapelEstudante();

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("more-nav")).findElements(By.tagName("li")).get(0).click();

        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.clicarModuloSection(section, tituloSection);

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.id("mod_quiz-next-nav")).click();

        facilitador.pausaSegundos(2);

        feedbackRespostaAntesEnviar = driver.findElement(By.tagName("tbody")).findElement(By.className("c1")).getText();

        assertEquals(feedbackRespostaAntesEnviarEsperada, feedbackRespostaAntesEnviar);

        driver.findElement(By.className("btn-primary")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();

        assertTrue(driver.findElement(By.className("info")).getText().contains(feedback));
    }

    @Test
    /* Criação do questionário com questão Cloze com resposta parcialmente correta */
    public void G1243() throws InterruptedException {
        JSONObject G1243 = (JSONObject) jsonObject.get("G1243");

        String loginUsuario = jsonObject.get("login").toString();
        String senhaUsuario = jsonObject.get("senha").toString();

        String section = jsonObject.get("section").toString();
        String modulo = jsonObject.get("modulo").toString();

        String tituloSection = G1243.get("tituloSection").toString();

        String feedbackRespostaAntesEnviarEsperada = G1243.get("feedbackRespostaAntesEnviar").toString();
        String feedbackRespostaAntesEnviar;                    

        String tituloQuestao = G1243.get("tituloQuestao").toString();
        String textoQuestao = G1243.get("textoQuestao").toString();

        String feedback = G1243.get("feedback").toString();

        String resposta1 = G1243.get("resposta_1").toString();
        String resposta2 = G1243.get("resposta_2").toString();
    
        facilitador.fazerLogin(loginUsuario, senhaUsuario);
        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.habilitarModoEdicao();
        
        facilitador.pausaSegundos(1);

        facilitador.clicarAdicionarModuloSection(section);

        facilitador.pausaSegundos(3);

        facilitador.clicarModulo(modulo);

        facilitador.pausaSegundos(1);

        facilitador.clicarExpandirTudo();

        facilitador.colocarNomeModulo(tituloSection);

        facilitador.pausaSegundos(1);

        facilitador.clicarSalvarEMostrar();

        facilitador.clicarAdicionarQuestao();

        driver.findElement(By.className("add-menu")).click();

        facilitador.pausaSegundos(1);

        driver.findElement(By.className("addquestion")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.id("item_qtype_multianswer")).click();

        driver.findElement(By.className("submitbutton")).click();

        facilitador.colocarNomeModulo(tituloQuestao);

        driver.findElement(By.id("id_questiontexteditable")).sendKeys(textoQuestao);

        driver.findElement(By.id("id_analyzequestion")).click();

        driver.findElement(By.id("id_submitbutton")).click();

        driver.findElement(By.className("editing_maxmark")).click();

        facilitador.pausaSegundos(2);

        Actions acao = new Actions(driver);
        acao.sendKeys(Keys.NUMPAD1).build().perform();
        acao.sendKeys(Keys.NUMPAD0).build().perform();
        acao.sendKeys(Keys.ENTER).build().perform();

        facilitador.pausaSegundos(4);

        facilitador.clicarPerfil();
        facilitador.pausaSegundos(1);
        facilitador.clicarMudarPapel();
        facilitador.clicarPapelEstudante();

        driver.findElement(By.className("btn-primary")).click();

        driver.findElement(By.className("more-nav")).findElements(By.tagName("li")).get(0).click();

        facilitador.clicarMeusCursos();
        facilitador.clicarNoCurso();
        facilitador.pausaSegundos(1);

        facilitador.clicarModuloSection(section, tituloSection);

        driver.findElement(By.className("btn-primary")).click();

        driver.findElements(By.tagName("select")).get(0).sendKeys(resposta1);
        driver.findElements(By.tagName("select")).get(1).sendKeys(resposta2);

        driver.findElement(By.id("mod_quiz-next-nav")).click();

        facilitador.pausaSegundos(2);

        feedbackRespostaAntesEnviar = driver.findElement(By.tagName("tbody")).findElement(By.className("c1")).getText();

        assertEquals(feedbackRespostaAntesEnviarEsperada, feedbackRespostaAntesEnviar);

        driver.findElement(By.className("btn-primary")).click();

        facilitador.pausaSegundos(2);

        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();

        assertTrue(driver.findElement(By.className("info")).getText().contains(feedback));
    }

}