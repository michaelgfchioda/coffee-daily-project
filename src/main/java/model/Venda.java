/*
    Implementação da tabela tb_produto do Banco de Dados DB_CoffeeDaily
*/
package model;

import java.util.Date;

public class Venda {
    
    // Atributos
    private int id_venda;       // PK id_venda
    private Date dt_venda;
    private Usuario usuario;    // FK id_usuario
    // ---------
    
    // Construtor => Polimorfismo por Sobrecarga (Quando há o Construtor Mestre)
    public Venda() {
        
    }
    public Venda(int id_venda, Date dt_venda, Usuario usuario) {
        this.id_venda = id_venda;   // PK id_venda
        this.dt_venda = dt_venda;
        this.usuario = usuario;     // FK id_usuario
    }
    // -----------------------------------------
    
    // Métodos Getters e Setters
    // id_venda => PK
    public int getId_venda() {
        return id_venda;
    }
    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    // dt_venda
    public Date getDt_venda() {
        return dt_venda;
    }
    public void setDt_venda(Date dt_venda) {
        this.dt_venda = dt_venda;
    }

    // id_usuario => FK
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    // --------------------------
}
