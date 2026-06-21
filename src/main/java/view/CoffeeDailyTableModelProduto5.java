/*
    
*/
package view;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class CoffeeDailyTableModelProduto5 extends AbstractTableModel {
    
    // ---------- Interface 5: Consultar/Editar Produtos ----------
    
    // Atributos para manipular a tabela tabProdutos5 de Consultar/Editar Produtos na Interface 5
    private final String[] colunasTabProdutos5 = {"ID", "Nome", "Categoria", "Descrição", "Preço", "Quantidade", "Fabricação", "Validade", "Marca"};
    private final List<Produto> dadosTabProdutos5 = new ArrayList<>();
    
    // Método para atualizar a tabela tabProdutos5 toda vez que uma consulta for feita antes ou depois da inserção de novos dados
    public void atualizarTabProdutos5(List<Produto> novosDados) {
        // Apagar tudo que há na tabela
        this.dadosTabProdutos5.clear();
        // Carregar dados atualizados
        this.dadosTabProdutos5.addAll(novosDados);
        // Atualizar a tabela
        this.fireTableDataChanged();
    }
    
    // Implementação dos métodos abstratos para Consultar/Editar Produtos
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Produto produto = dadosTabProdutos5.get(rowIndex);
        
        switch (columnIndex) {
            case 0 -> {
                return String.format("%d", produto.getId_produto());
            }
            case 1 -> {
                return String.format("%s", produto.getNm_produto());
            }
            case 2 -> {
                return String.format("%s", produto.getCategoria());
            }
            case 3 -> {
                return String.format("%s", produto.getDescricao());
            }
            case 4 -> {
                return String.format("%.2f", produto.getPreco());
            }
            case 5 -> {
                return String.format("%d", produto.getQtd_estoque());
            }
            case 6 -> {
                return String.format("%s", produto.getDt_fabricacao());
            }
            case 7 -> {
                return String.format("%s", produto.getDt_validade());
            }
            case 8 -> {
                return String.format("%s", produto.getMarca());
            }
        }
        return null;
    }
    
    @Override
    public int getRowCount() {
        return dadosTabProdutos5.size();
    }

    @Override
    public int getColumnCount() {
        return colunasTabProdutos5.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return colunasTabProdutos5[column];
    }
    
    // -------------------------------------------------------------------------
}
