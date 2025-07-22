package br.com.rezende;

import br.com.rezende.model.Produto;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class NetshoesScraperTest {

    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");

        // options.addArguments("--headless=new");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterAll
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void deveExtrairInformacoesDoProduto() throws InterruptedException {
        String url = "https://www.netshoes.com.br/p/tenis-nike-revolution-7-masculino-JD8-6364-014";
        driver.get(url);

        // Aguarda carregamento total
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));

        Thread.sleep(5000); // aguarda renderização de conteúdo dinâmico

        // Aceita cookies se necessário
        try {
            WebElement accept = wait.until(ExpectedConditions.elementToBeClickable(By.id("cookie-notice-ok")));
            accept.click();
        } catch (Exception ignored) {
        }

        // Título com XPath genérico
        String titulo = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h1[contains(text(), 'Tênis')]"))).getText();

        // Preço com fallback: qualquer texto contendo "R$"
        String preco = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(),'R$')]"))).getText();

        // Imagem principal
        String imagem = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("img[itemprop='image']"))).getAttribute("src");

        // Descrição se disponível
        String descricao;
        try {
            descricao = driver.findElement(By.cssSelector("div[itemprop='description']")).getText();
        } catch (NoSuchElementException e) {
            descricao = "Descrição não encontrada.";
        }

        Produto produto = new Produto(titulo, preco, imagem, descricao);

        // Validações
        assertNotNull(produto);
        assertTrue(titulo.toLowerCase().contains("tênis"));
        assertTrue(preco.contains("R$"));
        assertTrue(imagem.startsWith("http"));
        assertFalse(produto.toString().isEmpty());

        // Exibe no console
        System.out.println(produto);
    }
}
