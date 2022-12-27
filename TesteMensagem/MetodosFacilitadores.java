import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MetodosFacilitadores {

    private WebDriver driver;

    public MetodosFacilitadores(WebDriver driver) {
        this.driver = driver;
    }

    public void fazerLogin(String usuario, String senha) {
        adicionarUsuario(usuario);
        adicionarSenha(senha);
        apertarBotaoLogin();
    }

    public void adicionarUsuario(String usuario) {
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(usuario);
    }

    public void adicionarSenha(String senha) {
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(senha);
    }

    public void apertarBotaoLogin() {
        driver.findElement(By.id("loginbtn")).click();
    }

    public void clicarNoBalaoDeMensagem() {
        driver.findElement(By.id("usernavigation")).findElements(By.className("popover-region")).get(1).click();
    }

    public void clicarContatos() {
        driver.findElement(By.cssSelector("a[data-route='view-contacts']")).click();
    }

    public void clicarContatoDesejado(List<WebElement> contatos, String nome) {
        for(WebElement contato : contatos) {
            if(contato.getText().contains(nome)) {
                contato.click();
                break;
            }
        }
    }

    public void digitarMensagemContatoPessoal(String mensagem) {
        driver.findElement(By.tagName("textarea")).sendKeys(mensagem);
    }

    public void digitarMensagemEmMassa(String mensagem) {
        driver.findElement(By.id("bulk-message")).sendKeys(mensagem);
    }

    public void enviarMensagemContatoPessoalEnter() {
        driver.findElement(By.tagName("textarea")).sendKeys(Keys.ENTER);
    }

    public void enviarMensagemContatoPessoalIcon() {
        driver.findElement(By.className("fa-paper-plane")).click();
    }

    public void clicarPerfil() {
        driver.findElement(By.className("usermenu")).click();
    }

    public void clicarSairPerfil() {
        driver.findElement(By.id("carousel-item-main")).findElements(By.tagName("a")).get(7).click();
    }

    public void clicarSairPerfilCurso() {
        driver.findElement(By.id("carousel-item-main")).findElements(By.tagName("a")).get(8).click();
    }

    public void clicarAcessar() {
        driver.findElement(By.className("usermenu")).click();
    }

    public void clicarParticipantes() {
        driver.findElement(By.xpath("//a[normalize-space()='Participantes']")).click();
    }

    public List<WebElement> getParticipantes() {
        return driver.findElement(By.tagName("table")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
    }

    public void marcarParticipante(List<WebElement> participantes, String nomeParticipante) {
        for(WebElement participante : participantes) {
            if(participante.getText().contains(nomeParticipante)) {
                participante.findElement(By.tagName("input")).click();
                break;
            }
        }
    }

    public void clicarEscolher() {
        driver.findElement(By.id("formactionid")).click();
    }

    public void clicarEnviarUmaMensagem() {
        driver.findElement(By.id("formactionid")).findElements(By.tagName("option")).get(1).click();
    }

    public void clicarEnviarMensagemEmMassa() {
        driver.findElement(By.className("modal-footer")).findElement(By.className("btn-primary")).click();
    }

    public List<WebElement> getMensagens() {
        return driver.findElement(By.className("content-message-container")).findElements(By.className("message"));
    }

    public List<WebElement> getContatosGeral() {
        return driver.findElements(By.className("list-group-item"));
    }

    public List<WebElement> getContatosPrivado() {
        return driver.findElement(By.cssSelector("div[data-region='view-overview-messages']")).findElements(By.className("list-group-item"));
    }


    public void clicarMeusCursos() {
        driver.findElement(By.xpath("//a[@role='menuitem'][normalize-space()='Meus cursos']")).click();
    }

    public void clicarNoCurso() {
        driver.findElement(By.xpath("//div[@class='card-img dashboard-card-img']")).click();
    }


    public void pausaSegundos(double valor) throws InterruptedException {
        Thread.sleep((long) valor * 1000);
    }

}