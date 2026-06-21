/*
    Implementação da tabela tb_pagamento do Banco de Dados DB_CoffeeDaily
*/
package model;

public class Pagamento {
    
    // Atributos
    private int id_pagamento;   // PK id_pagamento
    private String forma_pagamento;
    private double valor_pago;
    private int parcelas;
    private Venda venda;        // FK id_venda
    // ---------
    
    // Construtor => Polimorfismo por Sobrecarga (Quando há o Construtor Mestre)
    public Pagamento() {
        
    }
    public Pagamento(int id_pagamento, String forma_pagamento, double valor_pago, int parcelas, Venda venda) {
        this.id_pagamento = id_pagamento;       // PK id_pagamento
        this.forma_pagamento = forma_pagamento;
        this.valor_pago = valor_pago;
        this.parcelas = parcelas;
        this.venda = venda;                     // FK id_venda
    }
    // -----------------------------------------
    
    // Métodos Getters e Setters
    // id_pagamento => PK
    public int getId_pagamento() {
        return id_pagamento;
    }
    public void setId_pagamento(int id_pagamento) {
        this.id_pagamento = id_pagamento;
    }

    // forma_pagamento
    public String getForma_pagamento() {
        return forma_pagamento;
    }
    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    // valor_pago
    public double getValor_pago() {
        return valor_pago;
    }
    public void setValor_pago(double valor_pago) {
        this.valor_pago = valor_pago;
    }

    // parcelas
    public int getParcelas() {
        return parcelas;
    }
    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    // id_venda => FK
    public Venda getVenda() {
        return venda;
    }
    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    // ---------------------------------------
}
