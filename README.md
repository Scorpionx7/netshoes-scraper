```markdown
# 🕷️ Web Scraper Netshoes - Case Técnico

[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://openjdk.org/projects/jdk/21/)
[![Selenium](https://img.shields.io/badge/Selenium-4.21.0-brightgreen.svg)](https://www.selenium.dev/)
[![Maven](https://img.shields.io/badge/Maven-3.8.6-yellow.svg)](https://maven.apache.org/)

Este projeto realiza **web scraping no site da Netshoes** para extração de informações de produtos (nome, preço, imagem e descrição), utilizando Java 21 com Selenium WebDriver.

## 🔧 Stack Tecnológica

| Tecnologia         | Versão   | Finalidade                          |
|--------------------|----------|-------------------------------------|
| Java               | 21       | Linguagem principal                 |
| Selenium WebDriver | 4.21.0   | Automação do navegador              |
| ChromeDriver       | 138+     | Driver para Chrome                  |
| WebDriverManager   | 5.8.0    | Gerenciamento automático de drivers |
| Maven              | 3.8.6+   | Gerenciamento de dependências       |
| JUnit              | 5        | Testes unitários                    |

## 🚀 Configuração do Ambiente

1. **Pré-requisitos**:
   - JDK 21 ([Download](https://jdk.java.net/21/))
   - Maven ([Instalação](https://maven.apache.org/install.html))
   - Google Chrome (última versão)

2. **Clone o repositório**:
   ```bash
   git clone https://github.com/Scorpionx7/netshoes-scraper.git
   cd netshoes-scraper
   ```

3. **Instalação**:
   ```bash
   mvn clean install
   ```

## 🛠️ Execução

### Opção 1: Via linha de comando
```bash
mvn exec:java -Dexec.mainClass="br.com.rezende.Main" \
  -Dexec.args="https://www.netshoes.com.br/p/tenis-corrida-galaxy-7-adidas-FBA-0335-006"
```

### Opção 2: Classe Main
```java
public class Main {
    public static void main(String[] args) {
        ScraperService scraper = new ScraperService();
        Produto produto = scraper.extrairProduto(args[0]);
        System.out.println(produto.toJson());
    }
}
```

### Opção 3: Testes Unitários
```bash
mvn test
```

## 🧩 Estrutura do Projeto

```
netshoes-scraper/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/com/rezende/
│   │   │       ├── model/
│   │   │       │   └── Produto.java       # Modelo de dados
│   │   │       └── service/
│   │   │           └── ScraperService.java # Lógica de scraping
│   │   └── resources/
│   └── test/
│       └── java/
│           └── br/com/rezende/
│               └── service/
│                   └── ScraperTest.java   # Testes
├── pom.xml                              # Dependências Maven
└── README.md
```

## 🎯 Funcionalidades

- [x] Extração de título do produto
- [x] Captura de preço (com múltiplos seletores de fallback)
- [x] Obtenção de URL da imagem principal
- [x] Coleta de descrição completa
- [x] Tratamento de cookies
- [x] Modo headless (sem interface gráfica)
- [x] Timeout configurável

## 💡 Exemplo de Saída

```json
{
  "titulo": "Tênis Corrida Galaxy 7 Adidas - Preto",
  "preco": "R$ 379,98",
  "imagem": "https://static.netshoes.com.br/produtos/...zoom1.jpg",
  "descricao": "Cada corrida é uma jornada de descobertas. Calce este tênis..."
}
```

## ⚠️ Limitações e Boas Práticas

1. **Variabilidade do Site**:
    - Seletores CSS/XPath podem precisar de atualização conforme mudanças no site
    - Recomendado monitorar via testes automatizados

2. **Política de Robôs**:
   ```txt
   # Adicione no seu projeto
   User-agent: *
   Crawl-delay: 10
   Disallow: /busca
   ```

3. **Performance**:
    - Tempo médio por scraping: ~5-15s
    - Uso de cache recomendado para múltiplas requisições

---

Desenvolvido com ❤️ por [Esther Rezende](https://github.com/Scorpionx7)  
📧 Contato: rezendealvesesther@gmail.com
🔗 LinkedIn: [https://linkedin.com/in/estherrezende](https://linkedin.com/in/estherrezende)
```
