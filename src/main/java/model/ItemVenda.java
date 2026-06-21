/*
    Implementação da tabela tb_item_venda do Banco de Dados DB_CoffeeDaily
*/
package model;

public class ItemVenda {
    
    // Atributos
    private int id_item_venda;  // PK id_item_venda
    private int quantidade;
    private double subtotal;
    private Venda venda;        // FK id_venda
    private Produto produto;    // FK id_produto
    // ---------
    
    // Construtor => Polimorfismo por Sobrecarga (Quando há o Construtor Mestre)
    public ItemVenda() {
        
    }
    public ItemVenda(int id_item_venda, int quantidade, double subtotal, Venda venda, Produto produto) {
        this.id_item_venda = id_item_venda;     // PK id_item_venda
        this.quantidade = quantidade;
        this.subtotal = subtotal;
        this.venda = venda;                     // FK id_venda
        this.produto = produto;                 // FK id_produto
    }
    // -----------------------------------------
    
    // Métodos Getters e Setters
    // id_item_venda => PK
    public int getId_item_venda() {
        return id_item_venda;
    }
    public void setId_item_venda(int id_item_venda) {
        this.id_item_venda = id_item_venda;
    }

    // quantidade
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // subtotal
    public double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    // id_venda => FK
    public Venda getVenda() {
        return venda;
    }
    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    // id_produto => FK
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    // ------------------------------
}
