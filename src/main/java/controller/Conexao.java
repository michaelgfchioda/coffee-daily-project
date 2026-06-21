/*
    // Implementação do método de conexão com o Banco de Dados DB_CoffeeDaily
*/
package controller;

// Importação das packages para o método de conexão
import java.sql.DriverManager;          // Para gerenciar o Driver JDBC
import java.sql.Connection;             // Para conectar com o BD
import java.sql.SQLException;           // Para identificar exeções, erros do BD

public class Conexao {
    
    // OBS: Os atributos devem ser obrigatoriamente STATIC, já que serão utilizados
    // no método static (conectar), também.
    
    // ---- Atributos => Parâmetros de Conexão ----
    private static final String URL = "jdbc:postgresql://localhost:5432/DB_CoffeeDaily";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "@220109@20@220206@";
    // --------------------------------------------
    
    // O uso de STATIC na criação de um método de uma determinada classe permite 
    // que a gente utilize esse mesmo método em outras classes sem a necessidade 
    // de criar Objetos para acessá-los.
    
    // ---- Método para realizar a conexão com o BD ----
    public static Connection conectar() {
        // Bloco try catch para tratar exeções
        // Se a conexão com o Banco de Dados for bem sucedida, retornar mensagem de sucesso
        try {
            Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("A conexão com DB_CoffeeDaily foi bem sucedida!");
            
            return conn;
        
        // Se a conexão com o Banco de Dados falhar, retornar mensagem de erro com a causa
        // Exception => Abrange todos os tipos de erro.
        } catch (Exception e) { 
            System.out.println("Erro ao conectar... ERRO: " + e.getMessage());
            
            return null;
        }
    }
    // -------------------------------------------------
    
    // Chamada do método de conexão implementado para testar a conexão
    public static void main(String[] args) {
        conectar();
    }
}
