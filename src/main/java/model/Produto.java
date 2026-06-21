/*
    Implementação da tabela tb_produto do Banco de Dados DB_CoffeeDaily
*/
package model;

import java.util.Date;

public class Produto {
    
    // Atributos
    private int id_produto;     // PK id_produto
    private String nm_produto;
    private String categoria;
    private String descricao;
    private double preco;
    private int qtd_estoque;
    private Date dt_fabricacao;
    private Date dt_validade;
    private String marca;
    // ---------
    
    // Construtor => Polimorfismo por Sobrecarga (Quando há o Construtor Mestre)
    public Produto() {
        
    }
    public Produto(int id_produto, String nm_produto, String categoria, String descricao, double preco, int qtd_estoque, Date dt_fabricacao, Date dt_validade, String marca) {
        this.id_produto = id_produto;   // PK id_produto
        this.nm_produto = nm_produto;
        this.categoria = categoria;
        this.descricao = descricao;
        this.preco = preco;
        this.qtd_estoque = qtd_estoque;
        this.dt_fabricacao = dt_fabricacao;
        this.dt_validade = dt_validade;
        this.marca = marca;
    }
    // -----------------------------------------
    
    // Métodos Getters e Setters
    // id_produto => PK
    public int getId_produto() {
        return id_produto;
    }
    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    // nm_produto
    public String getNm_produto() {
        return nm_produto;
    }
    public void setNm_produto(String nm_produto) {
        this.nm_produto = nm_produto;
    }
    
    // categoria
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // descricao
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // preco
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    // qtd_estoque
    public int getQtd_estoque() {
        return qtd_estoque;
    }
    public void setQtd_estoque(int qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    // dt_fabricacao
    public Date getDt_fabricacao() {
        return dt_fabricacao;
    }
    public void setDt_fabricacao(Date dt_fabricacao) {
        this.dt_fabricacao = dt_fabricacao;
    }

    // dt_validade
    public Date getDt_validade() {
        return dt_validade;
    }
    public void setDt_validade(Date dt_validade) {
        this.dt_validade = dt_validade;
    }

    // marca
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    // ------------------------------
}
