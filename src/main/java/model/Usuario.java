/*
    Implementação da tabela tb_usuario do Banco de Dados DB_CoffeeDaily
*/
package model;

public class Usuario {
    
    // Atributos
    private int id_usuario;     // PK id_usuario
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    // ----------
    
    // Construtor => Polimorfismo por Sobrecarga (Quando há o Construtor Mestre)
    public Usuario() {
        
    }
    public Usuario(int id_usuario, String nome, String email, String telefone, String senha) {
        this.id_usuario = id_usuario;   // PK id_usuario
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }
    // -----------------------------------------
    
    // Métodos Getters e Setters
    // id_usuario => PK
    public int getIDUsuario() {
        return id_usuario;
    }
    public void setIDUsuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    // nome
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    // email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    // telefone
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    // senha
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    // ------------------------
}
