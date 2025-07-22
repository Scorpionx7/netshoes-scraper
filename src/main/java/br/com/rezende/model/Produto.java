package br.com.rezende.model;

public class Produto {

    private String titulo;
    private String preco;
    private String imagem;
    private String descricao;

    public Produto(String titulo, String preco, String imagem, String descricao) {
        this.titulo = titulo;
        this.preco = preco;
        this.imagem = imagem;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Título: " + titulo +
                "\nPreço: " + preco +
                "\nImagem: " + imagem +
                "\nDescrição: " + descricao;
    }
}
