import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;

public class TestesArquivo {

    private static JSONObject jsonObject;
    private static String baseUrl;
    private WebDriver driver = new ChromeDriver();

    @Before
    public void setUp() throws Exception {
        //System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        jsonObject = (JSONObject) new JSONParser().parse(new FileReader(getClass().getResource("json/dados.json").getPath()));
        baseUrl = jsonObject.get("baseUrl").toString();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        fazerLogin();
    }
    
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void adicionarArquivoConceitoSemDescricao() throws InterruptedException {
        JSONObject arquivo = (JSONObject) jsonObject.get("adicionarArquivoConceitoSemDescricao");
        String tituloSecaoEsperado = arquivo.get("tituloSecao").toString();
        String nomeArquivoEsperado = arquivo.get("tituloAtividade").toString();

        clicarMeusCursos();
        clicarNoCurso();
        ativarModoEdicao();

        WebElement section = driver.findElement(By.id("section-1"));
        String dadosSection = section.getText().split("\n")[0];
        assertEquals(tituloSecaoEsperado, dadosSection);
        pausa(1);

        WebElement botaoAdicionarArquivo = driver.findElement(By.xpath("//div[@id='coursecontentcollapse1']//button[@class='btn btn-link text-decoration-none section-modchooser section-modchooser-link activity-add d-flex align-items-center p-3 mb-3']"));
        botaoAdicionarArquivo.click();
        pausa(2);

        clicarCardArquivo();
        adicionarNomeAtividade(arquivo.get("tituloAtividade").toString());
        clicarBotaoAdicionarArquivo();
        uploadArquivo(arquivo.get("caminhoArquivo").toString());
        clicarBotaoEnviarArquivo();
        pausa(20);
        enviarAlteracoes();

        List<WebElement> listaModulos = driver.findElement(By.id("section-1")).findElement(By.className("d-block")).findElements(By.tagName("li"));
        assertTrue(verificarCriacaoTitulo(listaModulos, nomeArquivoEsperado));        
    }

    @Test
    public void adicionarArquivoIntroducaoComDescricao() {
        JSONObject arquivo = (JSONObject) jsonObject.get("adicionarArquivoIntroducaoComDescricao");
        String tituloSecaoEsperado = arquivo.get("tituloSecao").toString();
        String nomeArquivoEsperado = arquivo.get("tituloAtividade").toString();
        String descricaoArquivoEsperado = arquivo.get("descricao").toString();

        clicarMeusCursos();
        clicarNoCurso();
        ativarModoEdicao();

        WebElement section = driver.findElement(By.id("section-2"));
        String[] dadosSection = section.getText().split("\n");
        assertEquals(tituloSecaoEsperado, dadosSection[0]);
        pausa(1);

        WebElement botaoAdicionarArquivo = driver.findElement(By.xpath("//div[@id='coursecontentcollapse2']//button[@class='btn btn-link text-decoration-none section-modchooser section-modchooser-link activity-add d-flex align-items-center p-3 mb-3']"));
        botaoAdicionarArquivo.click();
        pausa(5);

        clicarCardArquivo();
        pausa(2);
        WebElement campoDescricao = driver.findElement(By.id("id_introeditoreditable"));
        campoDescricao.sendKeys(arquivo.get("descricao").toString());
        WebElement checkboxDescricao = driver.findElement(By.id("id_showdescription"));
        checkboxDescricao.click();
        adicionarNomeAtividade(arquivo.get("tituloAtividade").toString());
        clicarBotaoAdicionarArquivo();
        uploadArquivo(arquivo.get("caminhoArquivo").toString());
        pausa(1);
        clicarBotaoEnviarArquivo();
        pausa(20);
        enviarAlteracoes();

        pausa(1);

        List<WebElement> listaModulos = driver.findElement(By.id("section-2")).findElement(By.className("d-block")).findElements(By.tagName("li"));
        assertTrue(verificarCriacaoTitulo(listaModulos, nomeArquivoEsperado));
        assertTrue(verificarCriacaoTitulo(listaModulos, descricaoArquivoEsperado));
    }

    @Test
    public void adicionarArquivoSHAGrandeAdicionarAceito() {
        JSONObject arquivo = (JSONObject) jsonObject.get("adicionarArquivoSHAGrandeAdicionarAceito");
        String tituloSecaoEsperado = arquivo.get("tituloSecao").toString();
        String mensagemErroEsperado = arquivo.get("erro").toString();
        String nomeArquivoEsperado = arquivo.get("tituloAtividade").toString();

        clicarMeusCursos();
        clicarNoCurso();
        ativarModoEdicao();

        WebElement section = driver.findElement(By.id("section-3"));
        String[] dadosSection = section.getText().split("\n");
        assertEquals(tituloSecaoEsperado, dadosSection[0]);
        pausa(1);

        WebElement botaoAdicionarArquivo = driver.findElement(By.xpath("//div[@id='coursecontentcollapse3']//button[@class='btn btn-link text-decoration-none section-modchooser section-modchooser-link activity-add d-flex align-items-center p-3 mb-3']"));
        botaoAdicionarArquivo.click();
        pausa(5);

        clicarCardArquivo();
        adicionarNomeAtividade(arquivo.get("tituloAtividade").toString());
        clicarBotaoAdicionarArquivo();
        pausa(1);
        uploadArquivo(arquivo.get("caminhoArquivoGrande").toString());
        clicarBotaoEnviarArquivo();
        pausa(90);
        
        String mensagemErroAtual = driver.findElement(By.className("moodle-exception-message")).getText();
        assertEquals(mensagemErroEsperado, mensagemErroAtual);

        Actions acao = new Actions(driver);
        acao.sendKeys(Keys.ENTER).build().perform();

        WebElement botaoEnviarArquivo = driver.findElement(By.className("fp-repo-area")).findElements(By.tagName("a")).get(3);
        botaoEnviarArquivo.click();

        pausa(2);
        uploadArquivo(arquivo.get("caminhoArquivoAceito").toString());
        pausa(5);
        clicarBotaoEnviarArquivo();
        pausa(20);
        enviarAlteracoes();
        pausa(2);

        List<WebElement> listaModulos = driver.findElement(By.id("section-3")).findElement(By.className("d-block")).findElements(By.tagName("li"));
        assertTrue(verificarCriacaoTitulo(listaModulos, nomeArquivoEsperado));
    }

    @Test
    public void adicionarArquivoSHARemover() {
        JSONObject arquivo = (JSONObject) jsonObject.get("adicionarArquivoSHARemover");
        String tituloSecaoEsperado = arquivo.get("tituloSecao").toString();
        String nomeArquivoEsperado = arquivo.get("tituloAtividade").toString();
        int quantidadeModulos;

        clicarMeusCursos();
        clicarNoCurso();
        ativarModoEdicao();

        WebElement section = driver.findElement(By.id("section-3"));
        String[] dadosSection = section.getText().split("\n");
        assertEquals(tituloSecaoEsperado, dadosSection[0]);
        pausa(1);

        WebElement botaoAdicionarArquivo = driver.findElement(By.xpath("//div[@id='coursecontentcollapse3']//button[@class='btn btn-link text-decoration-none section-modchooser section-modchooser-link activity-add d-flex align-items-center p-3 mb-3']"));
        botaoAdicionarArquivo.click();
        pausa(5);

        clicarCardArquivo();
        adicionarNomeAtividade(arquivo.get("tituloAtividade").toString());
        clicarBotaoAdicionarArquivo();
        pausa(2);
        uploadArquivo(arquivo.get("caminhoArquivo").toString());
        clicarBotaoEnviarArquivo();
        pausa(20);
        enviarAlteracoes();

        List<WebElement> listaModulos = driver.findElement(By.id("section-3")).findElement(By.className("d-block")).findElements(By.tagName("li"));
        quantidadeModulos = listaModulos.size();
        assertTrue(verificarCriacaoTitulo(listaModulos, nomeArquivoEsperado));

        for(WebElement elemento : listaModulos) {
            if(elemento.getText().contains(nomeArquivoEsperado)) {
                WebElement exibirAcoes = elemento.findElement(By.className("dropdown-toggle"));
                pausa(1);
                exibirAcoes.click();
                pausa(1);
                WebElement acaoExcluir = elemento.findElements(By.className("dropdown-menu")).get(0).findElements(By.tagName("a")).get(5);
                acaoExcluir.click();
                pausa(2);
                WebElement botaoConfirmarExclusao = driver.findElement(By.className("modal-content")).findElement(By.className("btn-primary"));
                botaoConfirmarExclusao.click();
                break;
            }
        }

        pausa(2);
        listaModulos = driver.findElement(By.id("section-3")).findElement(By.className("d-block")).findElements(By.tagName("li"));

        assertEquals(quantidadeModulos - 1, listaModulos.size());
    }

    @Test
    public void adicionarArquivoCriandoTrocarTitulo() {
        JSONObject arquivo = (JSONObject) jsonObject.get("adicionarArquivoCriandoTrocarTitulo");
        String tituloSecaoEsperado = arquivo.get("tituloSecao").toString();
        String nomeArquivoEsperado = arquivo.get("tituloAtividade").toString();
        String nomeArquivoAlterado = arquivo.get("tituloAtividadeAlterado").toString();

        clicarMeusCursos();
        pausa(1);
        clicarNoCurso();
        pausa(1);
        ativarModoEdicao();

        WebElement section = driver.findElement(By.id("section-4"));
        String[] dadosSection = section.getText().split("\n");
        assertEquals(tituloSecaoEsperado, dadosSection[0]);
        pausa(1);

        WebElement botaoAdicionarArquivo = driver.findElement(By.xpath("//div[@id='coursecontentcollapse4']//button[@class='btn btn-link text-decoration-none section-modchooser section-modchooser-link activity-add d-flex align-items-center p-3 mb-3']"));
        botaoAdicionarArquivo.click();
        pausa(5);

        clicarCardArquivo();
        adicionarNomeAtividade(arquivo.get("tituloAtividade").toString());
        clicarBotaoAdicionarArquivo();
        uploadArquivo(arquivo.get("caminhoArquivo").toString());
        clicarBotaoEnviarArquivo();
        pausa(2);
        enviarAlteracoes();

        List<WebElement> listaModulos = driver.findElement(By.id("section-4")).findElement(By.className("d-block")).findElements(By.tagName("li"));
        assertTrue(verificarCriacaoTitulo(listaModulos, nomeArquivoEsperado));

        for(WebElement elemento : listaModulos) {
            if(elemento.getText().contains(nomeArquivoEsperado)) {
                WebElement botaoEditar = elemento.findElement(By.className("quickeditlink"));
                botaoEditar.click();
                pausa(1);
                WebElement campoDigitar = elemento.findElement(By.className("form-control"));
                campoDigitar.sendKeys(nomeArquivoAlterado);
                campoDigitar.sendKeys(Keys.ENTER);
                break;
            }
        }

        pausa(2);
        listaModulos = driver.findElement(By.id("section-4")).findElement(By.className("d-block")).findElements(By.tagName("li"));
        assertTrue(verificarCriacaoTitulo(listaModulos, nomeArquivoAlterado));
    }

    @Test
    public void naoAdicionarArquivoUpload() {
        JSONObject arquivo = (JSONObject) jsonObject.get("naoAdicionarArquivoUpload");
        String tituloSecaoEsperado = arquivo.get("tituloSecao").toString();
        String mensagemErroEsperado = arquivo.get("erro").toString();

        clicarMeusCursos();
        clicarNoCurso();
        ativarModoEdicao();

        WebElement section = driver.findElement(By.id("section-3"));
        String[] dadosSection = section.getText().split("\n");
        assertEquals(tituloSecaoEsperado, dadosSection[0]);
        pausa(1);

        WebElement botaoAdicionarArquivo = driver.findElement(By.xpath("//div[@id='coursecontentcollapse3']//button[@class='btn btn-link text-decoration-none section-modchooser section-modchooser-link activity-add d-flex align-items-center p-3 mb-3']"));
        botaoAdicionarArquivo.click();
        pausa(5);

        clicarCardArquivo();
        adicionarNomeAtividade(arquivo.get("tituloAtividade").toString());
        enviarAlteracoes();
        pausa(5);

        String mensagemErro = driver.findElement(By.xpath("//div[@id='id_error_files']")).getText();
        assertEquals(mensagemErroEsperado, mensagemErro);
    }

    @Test
    public void uploadExcedenteTamanho() {
        JSONObject arquivo = (JSONObject) jsonObject.get("uploadExcedenteTamanho");
        String tituloSecaoEsperado = arquivo.get("tituloSecao").toString();
        String mensagemErroEsperado = arquivo.get("erro").toString();

        clicarMeusCursos();
        clicarNoCurso();
        ativarModoEdicao();

        WebElement section = driver.findElement(By.id("section-3"));
        String[] dadosSection = section.getText().split("\n");
        assertEquals(tituloSecaoEsperado, dadosSection[0]);
        pausa(1);

        WebElement botaoAdicionarArquivo = driver.findElement(By.xpath("//div[@id='coursecontentcollapse3']//button[@class='btn btn-link text-decoration-none section-modchooser section-modchooser-link activity-add d-flex align-items-center p-3 mb-3']"));
        botaoAdicionarArquivo.click();
        pausa(5);

        clicarCardArquivo();
        adicionarNomeAtividade(arquivo.get("tituloAtividade").toString());
        clicarBotaoAdicionarArquivo();
        uploadArquivo(arquivo.get("caminhoArquivo").toString());
        clicarBotaoEnviarArquivo();
        pausa(90);
        
        String mensagemErroAtual = driver.findElement(By.className("moodle-exception-message")).getText();
        assertEquals(mensagemErroEsperado, mensagemErroAtual);
    }

    public void clicarMeusCursos() {
        WebElement meusCursos = driver.findElement(By.xpath("//a[@role='menuitem'][normalize-space()='Meus cursos']"));
        meusCursos.click();
        pausa(5);
    }

    public void clicarNoCurso() {
        WebElement curso = driver.findElement(By.xpath("//div[@class='card-img dashboard-card-img']"));
        curso.click();
        pausa(5);
    }

    public void ativarModoEdicao() {
        WebElement slideEdicao = driver.findElement(By.className("custom-switch"));
        slideEdicao.click();
    }

    public void enviarAlteracoes() {
        WebElement btnUploadAlteracoes = driver.findElement(By.id("id_submitbutton2"));
        btnUploadAlteracoes.click();
    }

    public void clicarBotaoEnviarArquivo() {
        WebElement btnUploadArquivo = driver.findElement(By.className("fp-upload-btn"));
        btnUploadArquivo.click();
        pausa(2);
    }

    public void uploadArquivo(String caminho) {
        WebElement escolherArquivo = driver.findElement(By.name("repo_upload_file"));
        escolherArquivo.sendKeys(caminho);
    }

    public void clicarBotaoAdicionarArquivo() {
        WebElement btnAdicionarArquivo = driver.findElement(By.className("fp-btn-add")).findElement(By.className("btn"));
        btnAdicionarArquivo.click();
        pausa(3);
    }

    public void adicionarNomeAtividade(String nomeAtividade) {
        WebElement campoAtividade = driver.findElement(By.id("id_name"));
        campoAtividade.sendKeys(nomeAtividade);
        pausa(15);
    }

    public void clicarCardArquivo() {
        WebElement cardBotaoArquivo = driver.findElement(By.className("card-body"));
        cardBotaoArquivo.click();
    }

    public boolean verificarCriacaoTitulo(List<WebElement> listaModulos, String nomeArquivoEsperado) {
        System.out.println(listaModulos);
        for(WebElement element : listaModulos) {
            if(element.getText().contains(nomeArquivoEsperado)) {
                return true;
            }
        }
        return false;
    }


    public void fazerLogin() {
        adicionarUsuario();
        adicionarSenha();
        apertarBotaoLogin();
    }

    public void adicionarUsuario() {
        WebElement campo = driver.findElement(By.id("username"));
        campo.sendKeys(jsonObject.get("matricula").toString());
    }

    public void adicionarSenha() {
        WebElement campo = driver.findElement(By.id("password"));
        campo.sendKeys(jsonObject.get("senha").toString());
    }

    public void apertarBotaoLogin() {
        WebElement botao = driver.findElement(By.id("loginbtn"));
        botao.click();
    }

    public void pausa(double valor) {
        try {
            Thread.sleep((long) valor * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}