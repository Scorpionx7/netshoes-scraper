package br.com.rezende;

import br.com.rezende.model.Produto;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.io.IOException;

public class NetshoesScraper {
    public static void main(String[] args) {
        // Configura o WebDriverManager para ChromeDriver
        WebDriverManager.chromedriver().setup();

        // Configurações do navegador Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-allow-origins=*");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            if (args.length == 0) {
                System.out.println("⚠️ Nenhuma URL foi informada. Use como: java NetshoesScraper <URL>");
                return;
            }
            String url = args[0];
            driver.get(url);

            // Aguarda carregamento completo da página (document.readyState)
            wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));

            // Aguarda carregamento de scripts adicionais
            Thread.sleep(5000);

            // Salva o HTML renderizado para debug
            try {
                String pageSource = driver.getPageSource();
                Files.writeString(Path.of("pagina_netshoes.html"), pageSource);
                System.out.println("HTML salvo em 'pagina_netshoes.html'");
            } catch (IOException e) {
                System.out.println("Erro ao salvar HTML:");
                e.printStackTrace();
            }

            // Aceita cookies, se aparecer
            try {
                WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button#cookie-notice-ok")));
                acceptCookies.click();
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Botão de cookies não encontrado ou não clicável");
            }

            // Título usando XPath genérico
            WebElement tituloEl = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//h1[contains(text(), 'Tênis')]")));
            String titulo = tituloEl.getText();

            // Preço com seletor padrão Netshoes
            String preco = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'R$')]"))).getText();

            // Imagem principal do produto
            String imagem = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("img[itemprop='image']"))).getAttribute("src");

            // Descrição do produto (pode estar oculta ou ausente)
            String descricao;
            try {
                descricao = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div[itemprop='description']"))).getText();
            } catch (Exception e) {
                descricao = "Descrição não encontrada.";
            }

            // Criação do objeto Produto e exibição no console
            Produto produto = new Produto(titulo, preco, imagem, descricao);
            System.out.println(produto);

        } catch (Exception e) {
            System.out.println("Erro durante o scraping:");
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
