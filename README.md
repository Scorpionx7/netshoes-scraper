# Netshoes Scraper

Este projeto é um web scraper desenvolvido em Java para extrair informações de produtos do site da Netshoes.
Ele foi criado como parte de um case de estágio em desenvolvimento back-end.

## Funcionalidades

* Extrai título, preço, imagem e descrição de um produto a partir de uma URL da Netshoes.
* Utiliza Selenium para controlar o navegador e extrair os dados.
* Usa o WebDriverManager para gerenciar o driver do Chrome automaticamente.
* O projeto contém testes unitários com JUnit para validar a extração dos dados.

## Tecnologias Utilizadas

* Java 11
* Maven
* Selenium
* WebDriverManager
* JUnit 5

## Pré-requisitos

* Java JDK 11 ou superior instalado.
* Apache Maven instalado.
* Google Chrome instalado.

## Como Executar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/Scorpionx7/netshoes-scraper.git](https://github.com/Scorpionx7/netshoes-scraper.git)
    cd SEU-REPOSITORIO
    ```

2.  **Compile o projeto com o Maven:**
    ```bash
    mvn clean install
    ```

3.  **Execute o scraper via linha de comando:**
    Passe a URL do produto da Netshoes que você deseja analisar como um argumento.

    ```bash
    java -cp target/netshoes-scraper-1.0-SNAPSHOT.jar br.com.rezende.NetshoesScraper "URL_DO_PRODUTO_AQUI"
    ```
    **Exemplo:**
    ```bash
    java -cp target/netshoes-scraper-1.0-SNAPSHOT.jar br.com.rezende.NetshoesScraper "[https://www.netshoes.com.br/p/tenis-nike-revolution-7-masculino-JD8-6364-014](https://www.netshoes.com.br/p/tenis-nike-revolution-7-masculino-JD8-6364-014)"
    ```
    O resultado com as informações do produto será exibido no console.

## Como Executar os Testes

Para rodar os testes unitários do projeto, execute o seguinte comando Maven na raiz do projeto:

```bash
mvn test
```
Isso executará o teste definido em `src/test/java/br/com/rezende/NetshoesScraperTest.java` e confirmará que a extração de dados está funcionando corretamente para a URL de teste.
