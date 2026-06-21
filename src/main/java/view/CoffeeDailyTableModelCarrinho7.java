/*
    
*/
package view;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

public class CoffeeDailyTableModelCarrinho7 extends AbstractTableModel {
    
    // ---------- Interface 7: Vender ----------
    
    // Atributos para manipular a tabela tabCarrinho7 de Vender na Interface 7
    private final String[] colunasTabCarrinho7 = {"ID", "Nome", "Marca", "Quantidade", "Preço"};
    private final List<Produto> dadosTabCarrinho7 = new ArrayList<>();
    
    // Método para atualizar a tabela tabCarrinho7 toda vez que uma consulta for feita antes ou depois da inserção de novos dados
    public void atualizarTabCarrinho7(List<Produto> novosDados) {
        // Limpar tudo que há na tabela
        this.dadosTabCarrinho7.clear();
        // Carregar dados atualizados
        this.dadosTabCarrinho7.addAll(novosDados);
        // Atualizar a tabela
        this.fireTableDataChanged();
    }
    
    // Implementação dos métodos abstratos para Vender
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Produto produto = dadosTabCarrinho7.get(rowIndex);
        
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
        return dadosTabCarrinho7.size();
    }

    @Override
    public int getColumnCount() {
        return colunasTabCarrinho7.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return colunasTabCarrinho7[column];
    }
    
    // -------------------------------------------------------------------------
}
