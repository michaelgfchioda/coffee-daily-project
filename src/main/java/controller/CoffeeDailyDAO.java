/*
    Projeto CoffeeDaily - Desenvolvido por Michael Gabriel Ferreira Chioda durante a disciplina de Laboratório de Programação Orientada a Objetos

    Implementação das operações com SQL no Banco de Dados DB_CoffeeDaily:
        - Inserção de dados
        - Consulta de dados
        - Atualização de dados
*/
package controller;
// Importação das packages para os métodos de operações SQL
import java.sql.Connection;         // Conectar com o BD somente quando uma operação SQL for executada
import java.sql.PreparedStatement;  // Interface da API JDBC para executar comandos SQL
import java.sql.SQLException;       // Package para tratar erros com SQL
import java.sql.ResultSet;          // Para criar tabelas virtuais
import javax.transaction.xa.XAResource;
// Importação das classes que representam as tabelas do Banco de Dados
import model.Usuario;
import model.Venda;
import model.ItemVenda;
import model.Pagamento;
import model.Produto;
// Importação das packages auxiliares
import java.util.List;
import java.util.ArrayList;

public class CoffeeDailyDAO {
    
    // Variável para permitir a conexão com o Banco de Dados
    private final Connection con;

    // Construtor de CoffeeDailyDAO
    public CoffeeDailyDAO() {
        con = Conexao.conectar();
    }
    
    // ---------- Métodos para a Interface 1: Cadastrar Usuários ----------
    
    // Método para cadastrar novos usuários no Banco de Dados
    public boolean inserirUsuarios(Usuario usuario) {
        // Variável para realizar operações SQL
        String sql = "INSERT INTO tb_usuario (nome, email, telefone, senha) "
                   + "VALUES (?, ?, ?, MD5(?))";
        
        // Declarar o PreparedStatement dentro do try garante que ele seja FECHADO automaticamente
        try (PreparedStatement cmd = con.prepareStatement(sql)) {
            
            // Settando os Placeholders
            cmd.setString(1, usuario.getNome());
            cmd.setString(2, usuario.getEmail());
            cmd.setString(3, usuario.getTelefone());
            cmd.setString(4, usuario.getSenha());
            
            // Exibir mensagem de sucesso
            System.out.println("Usuário inserido com sucesso!");
            
            return cmd.executeUpdate() > 0;
            
        } catch (Exception e) {
            // Exibir mensagem de fracasso
            System.err.println("ERRO no método inserirUsuarios():");
            e.printStackTrace(); // Mostra os detalhes da falha
            return false;
        }
    }
    
    // Método para verificar se o email e telefone já existem no Banco de Dados
    public boolean verificarExistenciaUsuario(Usuario usuario) {
        // Variável para realizar operações SQL
        String sql = "SELECT email, telefone "
                   + "FROM tb_usuario "
                   + "WHERE email = ? OR telefone = ?";
        
        // Declarar o PreparedStatement dentro do try garante que ele seja FECHADO automaticamente
        try (PreparedStatement cmd = con.prepareStatement(sql)) {
            
            // Settando os Placeholders
            cmd.setString(1, usuario.getEmail());
            cmd.setString(2, usuario.getTelefone());
            
            try (ResultSet rs = cmd.executeQuery()) {
                if (rs.next()) {
                    // Exibir mensagem informativa
                    System.out.println("Não foi possível cadastrar o usuário. E-mail ou telefone já existentes!");
                    return true;
                }
            }
            return false;
            
        } catch (Exception e) {
            // Exibir mensagem de fracasso
            System.err.println("ERRO no método verificarExistenciaUsuario():");
            e.printStackTrace();
            return false;
        }
    }
    // -------------------------------------------------------------------------
    
    
    // ---------- Métodos para a Interface 2: Acessar Sistema ----------
    
    // Método para verificar se o usuário existe no Banco de Dados
    public boolean acessarSistema(Usuario usuario) {
        // Variável para realizar operações SQL
        String sql = "SELECT email, senha "
                   + "FROM tb_usuario "
                   + "WHERE email = ? AND senha = MD5(?)";
        
        // Declarar o PreparedStatement dentro do try garante que ele seja FECHADO automaticamente
        try (PreparedStatement cmd = con.prepareStatement(sql)) {
            
            // Settando os Placeholders
            cmd.setString(1, usuario.getEmail());
            cmd.setString(2, usuario.getSenha());
            
            // Declarar o ResultSet no try garante que ele seja Fechado automaticamente
            try (ResultSet rs = cmd.executeQuery()) {
                if (rs.next()) {
                    // Exibir mensagem de sucesso
                    System.out.println("Usuário encontrado! Seja bem-vindo(a) ao sistema...");
                    return true;
                }
            }
            return false;
            
        } catch (Exception e) {
            // Exibir mensagem de fracasso
            System.err.println("ERRO no método acessarSistema(): ");
            e.printStackTrace();
            return false;
        }
    }
    // -------------------------------------------------------------------------
    
    
    // ---------- Métodos para a Interface 3: Consultar/Editar Usuários ----------
    
    // Método para realizar uma query no Banco de Dados e retornar uma lista contendo-os
    public List<Usuario> consultarUsuarios() {
        
        // Criando uma lista de produtos para armazenar os dados do Banco de Dados
        List<Usuario> listaUsuarios = new ArrayList<>();
        
        // Variável para realizar operações SQL
        String sql = "SELECT id_usuario, nome, email, telefone, senha "
                    + "FROM tb_usuario";
        
        // Declarar o PreparedStatement dentro do try garante que ele seja FECHADO automaticamente
        try (PreparedStatement cmd = con.prepareStatement(sql)) {
            
            // Criando a tabela virtual para obter os dados do Banco de Dados
            // Declarar o ResultSet no try garante que ele seja fechado automaticamente
            try (ResultSet rs = cmd.executeQuery()) {
                // Enquanto houver tuplas, retorná-las na lista
                while (rs.next()) {
                    // Criando um Objeto Usuario pra cada tupla retornada
                    Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("senha")
                    );
                    // Depois de criar o Objeto, adicioná-lo na lista
                    listaUsuarios.add(usuario);
                }

                // Exibir mensagem de sucesso
                System.out.println("Consulta de usuários realizada com sucesso!");
            }           
            
        } catch (Exception e) {
            // Exibir mensagem de fracasso
            System.err.println("ERRO no método consultarUsuarios(): ");
            e.printStackTrace();
        }
        // Retornar a lista completa de Usuários do Banco de Dados
        return listaUsuarios;        
    }
    
    // Método para verificar se outro usuário já usa o email ou telefone informado
    public boolean verificarDuplicidadeEdicao(String email, String telefone, int idUsuario) {
        // Variável para realizar operações SQL
        String sql = "SELECT COUNT(*) "
                    + "FROM tb_usuario "
                    + "WHERE (email = ? OR telefone = ?) AND id_usuario != ?";
        
        // Declarar o PreparedStatement no try garante que ele seja FECHADO automaticamente
        try (PreparedStatement cmd = con.prepareStatement(sql)) {
            
            // Settando os placeholders
            cmd.setString(1, email);
            cmd.setString(2, telefone);
            cmd.setInt(3, idUsuario);
            
            try (ResultSet rs = cmd.executeQuery()) {
                if (rs.next()) {
                    // Se o COUNT for maior que 0, significa que o email/telefone já é usado por outro usuário
                    return rs.getInt(1) > 0;
                }
            }
            
        } catch (Exception e) {
            // Exibir mensagem de fracasso
            System.err.println("ERRO no método verificarDuplicidadeEdicao(): ");
            e.printStackTrace();
        }
        return false;
    }
    
    // Método para editar os dados dos usuários existentes no Banco de Dados
    public boolean atualizarUsuarios(Usuario usuario) {
        
        // Verificar se o Email e Telefone dos novos dados já existem no Banco de Dados com a chamada do método que verifica duplicidade
        if (verificarDuplicidadeEdicao(usuario.getEmail(), usuario.getTelefone(), usuario.getIDUsuario())) {
            System.err.println("Não foi possível editar os dados... Email e/ou telefone já pertecem a outro usuário!");
            return false;
        }            
        
        // Variável para realizar operações SQL
        String sql = "UPDATE tb_usuario "
                    + "SET nome = ?, email = ?, telefone = ?, senha = MD5(?) "
                    + "WHERE id_usuario = ?";
        
        try (PreparedStatement cmd = con.prepareStatement(sql)) {
            
            // Settando os placeholders
            cmd.setString(1, usuario.getNome());
            cmd.setString(2, usuario.getEmail());
            cmd.setString(3, usuario.getTelefone());
            cmd.setString(4, usuario.getSenha());
            cmd.setInt(5, usuario.getIDUsuario());  // ID que foi capturado do clique da tabela
            
            // Exibir mensagem de sucesso
            System.out.println("Dados do usuário foram alterados com sucesso!");
            return cmd.executeUpdate() > 0;
            
        } catch (Exception e) {
            // Exibir mensagem de fracasso
            System.err.println("ERRO no método atualizarUsuarios(): ");
            e.printStackTrace();
            return false;
        }
    }
    
    // -------------------------------------------------------------------------
    
    
    // ---------- Métodos para a Interface 4: Cadastrar Produtos ----------
    
    // Método para cadastrar novos produtos no Banco de Dados
    public boolean inserirProdutos(Produto produto) {
        // Variável para realizar operações SQL
        String sql = "INSERT INTO tb_produto (nm_produto, categoria, descricao, preco, qtd_estoque, dt_fabricacao, dt_validade, marca)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Declarar o PreparedStatement dentro do try garante que ele seja FECHADO automaticamente
        try (PreparedStatement cmd = con.prepareStatement(sql)) {
                        
            // Settando os Placeholders
            cmd.setString(1, produto.getNm_produto());
            cmd.setString(2, produto.getCategoria());
            cmd.setString(3, produto.getDescricao());
            cmd.setDouble(4, produto.getPreco());
            cmd.setInt(5, produto.getQtd_estoque());
            cmd.setDate(6, new java.sql.Date(produto.getDt_fabricacao().getTime()));    // Convertendo de java.util.Date para java.sql.Date
            cmd.setDate(7, new java.sql.Date(produto.getDt_validade().getTime()));      // Convertendo de java.util.Date para java.sql.Date
            cmd.setString(8, produto.getMarca());
            
            // Exibir mensagem de sucesso
            System.out.println("Produto inserido com sucesso!");
            
            return cmd.executeUpdate() > 0;
            
        } catch (Exception e) {
            // Exibir mensagem de fracasso
            System.err.println("ERRO no método inserirProdutos(): ");
            e.printStackTrace();    // Exibir detalhes do erro ocorrido
            return false;
        }
    }
    
    // -------------------------------------------------------------------------
    
    
    // ---------- Métodos para a Interface 5: Consultar/Editar Produtos ----------
    
    // Método para realizar uma query no Banco de Dados e retornar uma lista contendo-os
    public List<Produto> consultarProdutos() {
        
        // Criando uma lista de produtos para armazenar os dados do Banco de Dados
        List<Produto> listaProdutos = new ArrayList<>();
        
        // Variável que realiza operações SQL
        String sql = "SELECT id_produto, nm_produto, categoria, descricao, preco, qtd_estoque, dt_fabricacao, dt_validade, marca "
                    + "FROM tb_produto";
        
        // Declarar o PreparedStatement dentro do try permite que ele seja FECHADO automaticamente
        try (PreparedStatement cmd = con.prepareStatement(sql)) {
                        
            // Declarar o ResultSet dentro do try permite que ele seja FECHADO automaticamente
            try (ResultSet rs = cmd.executeQuery()) {
                // Enquanto houver tuplas, tranformá-las em Objeto Produto e adicionar à lista de produtos
                while (rs.next()) {
                    Produto produto = new Produto(
                        rs.getInt("id_produto"),
                        rs.getString("nm_produto"),
                        rs.getString("categoria"),
                        rs.getString("descricao"),
                        rs.getDouble("preco"),
                        rs.getInt("qtd_estoque"),
                        rs.getDate("dt_fabricacao"),
                        rs.getDate("dt_validade"),
                        rs.getString("marca")
                    );
                    // Adicionar produto à lista
                    listaProdutos.add(produto);
                }
                
                // Exibir mensagem de sucesso
                System.out.println("Consulta de produtos realizada com sucesso!");
            }
            
        } catch (Exception e) {
            // Exibir mensagem de fracasso
            System.err.println("ERRO no método consultarProdutos(): ");
            e.printStackTrace();
            return null;
        }
        // Retornar lista completa com todos os produtos do Banco de Dados
        return listaProdutos;
    }
    
    // Método para editar os dados dos produtos existentes no Banco de Dados
    public boolean atualizarProdutos(Produto produto) {
        // Variável para realizar operações SQL
        String sql = "UPDATE tb_produto "
                    + "SET nm_produto = ?, categoria = ?, descricao = ?, preco = ?, qtd_estoque = ?, dt_fabricacao = ?, dt_validade = ?, marca = ? "
                    + "WHERE id_produto = ?";
        
        // Declarar o PreparedStatement no try garante que ele seja FECHADO automaticamente
        try (PreparedStatement cmd = con.prepareStatement(sql)) {
            
            // Settando os placeholders
            cmd.setString(1, produto.getNm_produto());
            cmd.setString(2, produto.getCategoria());
            cmd.setString(3, produto.getDescricao());
            cmd.setDouble(4, produto.getPreco());
            cmd.setInt(5, produto.getQtd_estoque());
            cmd.setDate(6, new java.sql.Date(produto.getDt_fabricacao().getTime()));
            cmd.setDate(7, new java.sql.Date(produto.getDt_validade().getTime()));
            cmd.setString(8, produto.getMarca());
            cmd.setInt(9, produto.getId_produto());
            
            // Exibir mensagem de sucesso
            System.out.println("Dados do produto foram alterados com sucesso!");
            
            return cmd.executeUpdate() > 0;
            
        } catch (Exception e) {
            // Exibir mensagem de fracasso
            System.err.println("ERRO no método atualizarProdutos(): ");
            e.printStackTrace();
            return false;
        }
    }
    // -------------------------------------------------------------------------
    
    
    // ---------- Métodos para a Interface 6: Carrinho ----------
    
    // Método responsável por retornar todos os dados do carrinho
    public List<Produto> consultarCarrinho() {
        
        // Criando uma lista de produtos para armazenar os dados do Banco de Dados
        List<Produto> listaDoCarrinho = new ArrayList<>();
        
        // Variável que realiza operações SQL
        String sql = "SELECT * "
                    + "FROM tb_produto";
        
        // Declarar o PreparedStatement dentro do try permite que ele seja FECHADO automaticamente
        try (PreparedStatement cmd = con.prepareStatement(sql)) {
                        
            // Declarar o ResultSet dentro do try permite que ele seja FECHADO automaticamente
            try (ResultSet rs = cmd.executeQuery()) {
                // Enquanto houver tuplas, tranformá-las em Objeto Produto e adicionar à lista de produtos
                while (rs.next()) {
                    Produto produto = new Produto(
                        rs.getInt("id_produto"),
                        rs.getString("nm_produto"),
                        rs.getString("categoria"),
                        rs.getString("descricao"),
                        rs.getDouble("preco"),
                        rs.getInt("qtd_estoque"),
                        rs.getDate("dt_fabricacao"),
                        rs.getDate("dt_validade"),
                        rs.getString("marca")
                    );
                    // Adicionar produto à lista
                    listaDoCarrinho.add(produto);
                }
                
                // Exibir mensagem de sucesso
                System.out.println("Consulta do carrinho realizada com sucesso!");
            }
            
        } catch (Exception e) {
            // Exibir mensagem de fracasso
            System.err.println("ERRO no método consultarCarrinho(): ");
            e.printStackTrace();
            return null;
        }
        // Retornar lista completa com todos os produtos do Banco de Dados
        return listaDoCarrinho;
    }    
    // -------------------------------------------------------------------------
    
    
    // ---------- Métodos para a Interface 7: Vender ----------
    
    // Método auxiliar para buscar o ID do usuário através do email e senha digitados na venda
    // Método para buscar o ID do usuário através do email e senha
    public int obterIdUsuario(String email, String senha) {
        
        String sql = "SELECT id_usuario "
                    + "FROM tb_usuario "
                    + "WHERE email = ? AND senha = MD5(?)";
        
        
        try (PreparedStatement cmd = con.prepareStatement(sql)) {
            
            cmd.setString(1, email);
            cmd.setString(2, senha);
            
            
            try (ResultSet rs = cmd.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_usuario");
                }
            }
            
        } catch (Exception e) {
            
            System.err.println("ERROR ao obter ID do usuário: " + e.getMessage());
        }
        return -1;
    }

    // Método unificado que cria a Venda, captura o ID gerado e insere o Pagamento (Usando Transação/Commit)
    public boolean realizarVendaETratarPagamento(int idUsuario, String formaPagamento, int parcelas, double valorPago) {
        
        String sqlVenda = "INSERT INTO tb_venda (dt_venda, id_usuario) "
                        + "VALUES (CURRENT_DATE, ?)";
        
        String sqlPagamento = "INSERT INTO tb_pagamento (forma_pagamento, valor_pago, parcelas, id_venda) "
                            + "VALUES (?, ?, ?, ?)";

        try {
            // Desativa o auto-commit para garantir que se um falhar, nenhum dado seja salvo incorretamente
            con.setAutoCommit(false); 

            // Inserir na tb_venda e pedir ao JDBC para retornar a chave primária auto-gerada (SERIAL)
            try (PreparedStatement cmdVenda = con.prepareStatement(sqlVenda, PreparedStatement.RETURN_GENERATED_KEYS)) {
                cmdVenda.setInt(1, idUsuario);
                cmdVenda.executeUpdate();

                try (ResultSet rsKeys = cmdVenda.getGeneratedKeys()) {
                    if (rsKeys.next()) {
                        int idVendaGerado = rsKeys.getInt(1); // Captura o id_venda gerado na hora

                        // Agora insere na tb_pagamento passando o id_venda correto
                        try (PreparedStatement cmdPagamento = con.prepareStatement(sqlPagamento)) {
                            
                            
                            cmdPagamento.setString(1, formaPagamento);
                            cmdPagamento.setDouble(2, valorPago);
                            cmdPagamento.setInt(3, parcelas);
                            cmdPagamento.setInt(4, idVendaGerado);

                            cmdPagamento.executeUpdate();
                        }
                    } else {
                        throw new Exception("Falha ao recuperar o ID da Venda gerado pelo banco.");
                    }
                }
            }

            con.commit(); // Se chegou até aqui sem erros, confirma tudo no banco de dados!
            return true;
            
        } catch (Exception e) {
            try { con.rollback(); } 
            catch (Exception ex) { } // Desfaz qualquer alteração em caso de erro
            
            System.err.println("ERROR ao realizar venda/pagamento: " + e.getMessage());
            return false;
            
        } finally {
            try { con.setAutoCommit(true); } 
            catch (Exception e) { }
        }
    }
    // -------------------------------------------------------------------------
    
    
    // ---------- Métodos para a Interface 8: Consultar Vendas ----------
    
    // Método que faz a Query unindo tb_usuario e tb_pagamento
    public void consultarVendasUnificadas(List<Pagamento> listaPagamentos, List<Usuario> listaUsuarios) {
        // Limpa as listas antes de preencher
        listaPagamentos.clear();
        listaUsuarios.clear();

        // CORREÇÃO DO JOIN: Passando por tb_venda para interligar as tabelas corretamente
        String sql = "SELECT u.id_usuario, u.email, p.forma_pagamento, p.parcelas, p.valor_pago " +
                     "FROM tb_usuario u " +
                     "INNER JOIN tb_venda v ON u.id_usuario = v.id_usuario " +
                     "INNER JOIN tb_pagamento p ON v.id_venda = p.id_venda";

        try (PreparedStatement cmd = con.prepareStatement(sql);
             ResultSet rs = cmd.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIDUsuario(rs.getInt("id_usuario")); 
                usuario.setEmail(rs.getString("email"));

                Pagamento pagamento = new Pagamento();
                pagamento.setForma_pagamento(rs.getString("forma_pagamento")); 
                pagamento.setParcelas(rs.getInt("parcelas"));
                pagamento.setValor_pago(rs.getDouble("valor_pago"));

                listaUsuarios.add(usuario);
                listaPagamentos.add(pagamento);
            }
        } catch (Exception e) {
            System.err.println("ERROR ao consultar vendas unificadas: " + e.getMessage());
        }
    }
    
    // -------------------------------------------------------------------------
}
