/*
    
*/
package view;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class CoffeeDailyTableModelCarrinho6 extends AbstractTableModel {
    
    // ---------- Interface 6: Carrinho ----------
    
    // Atributos para manipular a tabela tabCarrinho6 de Carrinho na Interface 6
    private final String[] colunasTabCarrinho6 = {"ID", "Nome", "Marca", "Quantidade", "Preço"};
    private final List<Produto> dadosTabCarrinho6 = new ArrayList<>();
    
    // Método para atualizar a tabela tabCarrinho6 toda vez que uma consulta for feita antes ou depois da inserção de novos dados
    public void atualizarTabCarrinho6(List<Produto> novosDados) {
        // Apagar tudo que há na tabela
        this.dadosTabCarrinho6.clear();
        // Carregar dados atualizados
        this.dadosTabCarrinho6.addAll(novosDados);
        // Atualizar a tabela
        this.fireTableDataChanged();
    }
    
    // Implementação dos métodos abstratos para Carrinho
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Produto produto = dadosTabCarrinho6.get(rowIndex);
        
        switch (columnIndex) {
            case 0 -> {
                return String.format("%d", produto.getId_produto());
            }
            case 1 -> {
                return String.format("%s", produto.getNm_produto());
            }
            case 2 -> {
                return String.format("%s", produto.getMarca());
            }
            case 3 -> {
                return String.format("%d", produto.getQtd_estoque());
            }
            case 4 -> {
                return String.format("%.2f", produto.getPreco());
            }
        }
        return null;
    }
    
    @Override
    public int getRowCount() {
        return dadosTabCarrinho6.size();
    }

    @Override
    public int getColumnCount() {
        return colunasTabCarrinho6.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return colunasTabCarrinho6[column];
    }
    
    // -------------------------------------------------------------------------
}
