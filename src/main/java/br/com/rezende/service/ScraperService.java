package br.com.rezende.service;

import br.com.rezende.model.Produto;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Serviço responsável por realizar o web scraping no site da Netshoes.
 */
public class ScraperService {

    public Produto extrairProduto(String url) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Produto produto = null;

        try {
            driver.get(url);

            try {
                WebElement acceptCookies = new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.elementToBeClickable(By.id("cookie-notice-ok")));
                acceptCookies.click();
            } catch (Exception e) {
                System.out.println("Banner de cookies não encontrado ou não foi necessário clicar.");
            }


            String titulo = extrairTexto(wait,
                    By.cssSelector("h1.product-name")
            );

            String preco = extrairTexto(wait,
                    By.cssSelector("span.saleInCents-value")
            );

            String imagem = extrairAtributo(wait, "src",
                    By.cssSelector("div.swiper-slide-active img")
            );

            String descricao = extrairTexto(wait,
                    By.cssSelector("p.features--description")
            );

            produto = new Produto(titulo, preco, imagem, descricao);

        } catch (Exception e) {
            System.out.println("Erro final durante o scraping. Não foi possível extrair todos os dados.");
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
        return produto;
    }

    private String extrairTexto(WebDriverWait wait, By... seletores) {
        for (By seletor : seletores) {
            try {
                WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(seletor));
                return elemento.getText();
            } catch (TimeoutException e) {
                System.out.println("INFO: Seletor não encontrado: " + seletor + ". Tentando próximo...");
            }
        }
        System.out.println("AVISO: Nenhum seletor encontrou o texto desejado.");
        return "Não encontrado";
    }

    private String extrairAtributo(WebDriverWait wait, String atributo, By... seletores) {
        for (By seletor : seletores) {
            try {
                WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(seletor));
                return elemento.getAttribute(atributo);
            } catch (TimeoutException e) {
                System.out.println("INFO: Seletor não encontrado: " + seletor + ". Tentando próximo...");
            }
        }
        System.out.println("AVISO: Nenhum seletor encontrou o atributo '" + atributo + "'.");
        return "Não encontrado";
    }
}
