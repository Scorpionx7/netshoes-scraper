package br.com.rezende;

import br.com.rezende.model.Produto;
import br.com.rezende.service.ScraperService;

public class NetshoesScraper {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("⚠️ Nenhuma URL foi informada. Use como: java -jar seu-jar.jar \"URL_DO_PRODUTO\"");
            return;
        }

        String url = args[0];
        System.out.println("Iniciando scraping para a URL: " + url);

        // Instancia o serviço e chama o metodo de extração
        ScraperService scraperService = new ScraperService();
        Produto produto = scraperService.extrairProduto(url);

        if (produto != null) {
            System.out.println("\n--- INFORMAÇÕES DO PRODUTO ---");
            System.out.println(produto);
            System.out.println("---------------------------------");
            System.out.println("\n✅ Scraping finalizado com sucesso!");
        } else {
            System.out.println("\n❌ Não foi possível extrair as informações do produto.");
        }
    }
}
