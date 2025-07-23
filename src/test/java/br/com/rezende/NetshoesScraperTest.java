package br.com.rezende;

import br.com.rezende.model.Produto;
import br.com.rezende.service.ScraperService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NetshoesScraperTest {

    @Test
    public void testExtrairProdutoComSucesso() {
        ScraperService scraperService = new ScraperService();

        // Use uma URL válida e estável da Netshoes
        String url = "https://www.netshoes.com.br/p/tenis-asics-novablast-5-tr-masculino-2FW-2898-912";

        Produto produto = scraperService.extrairProduto(url);

        assertNotNull(produto, "O produto não pode ser nulo.");
        assertNotEquals("Não encontrado", produto.getTitulo(), "Título não foi encontrado.");
        assertNotEquals("Não encontrado", produto.getPreco(), "Preço não foi encontrado.");
        assertNotEquals("Não encontrado", produto.getImagem(), "Imagem não foi encontrada.");
        assertNotEquals("Não encontrado", produto.getDescricao(), "Descrição não foi encontrada.");

        System.out.println("✅ Produto extraído com sucesso:");
        System.out.println(produto);
    }

    @Test
    public void testExtrairProdutoComUrlInvalida() {
        ScraperService scraperService = new ScraperService();

        String urlInvalida = "https://www.netshoes.com.br/produto-inexistente";

        Produto produto = scraperService.extrairProduto(urlInvalida);

        assertNotNull(produto, "Mesmo com URL inválida, o produto deve ser retornado (com campos 'Não encontrado').");
        assertEquals("Não encontrado", produto.getTitulo(), "Título deveria estar como 'Não encontrado'.");
        assertEquals("Não encontrado", produto.getPreco(), "Preço deveria estar como 'Não encontrado'.");
        assertEquals("Não encontrado", produto.getImagem(), "Imagem deveria estar como 'Não encontrado'.");
        assertEquals("Não encontrado", produto.getDescricao(), "Descrição deveria estar como 'Não encontrado'.");

        System.out.println("⚠️ Produto com dados não encontrados (como esperado para URL inválida):");
        System.out.println(produto);
    }
}
