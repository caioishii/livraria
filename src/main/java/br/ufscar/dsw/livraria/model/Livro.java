package br.ufscar.dsw.livraria.model;

import java.math.BigDecimal;

public class Livro {
    private Long id;
    private String titulo;
    private String autor;
    private Integer ano;
    private BigDecimal preco;
    private Editora editora;

    public Livro() {}

    public Livro(Long id, String titulo, String autor, Integer ano, BigDecimal preco, Editora editora) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.preco = preco;
        this.editora = editora;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public Editora getEditora() { return editora; }
    public void setEditora(Editora editora) { this.editora = editora; }
}
