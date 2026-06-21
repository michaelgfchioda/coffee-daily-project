/*
    
*/
package view;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class CoffeeDailyTableModelUsuario3 extends AbstractTableModel {
    
    // ---------- Interface 3: Consultar/Editar Usuários ----------
    
    // Atributos para manipular a tabela tabUsuarios3 de Consultar/Editar Usuários na Interface 3
    private final String[] colunasTabUsuarios3 = {"ID", "Nome", "Email", "Telefone", "Senha"};
    private final List<Usuario> dadosTabUsuarios3 = new ArrayList<>();
    
    // Método para atualizar a tabela tabUsuarios3 toda vez que uma consulta for feita antes ou depois da inserção de novos dados
    public void atualizarTabUsuarios3(List<Usuario> novosDados) {
        // Limpar tudo que há na tabela
        this.dadosTabUsuarios3.clear();
        // Carregar dados atualizados
        this.dadosTabUsuarios3.addAll(novosDados);
        // Atualizar a tabela
        this.fireTableDataChanged();
    }
    
    // Implementação dos métodos abstratos para Consultar/Editar Usuários
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Usuario usuario = dadosTabUsuarios3.get(rowIndex);
        
        switch (columnIndex) {
            case 0 -> {
                return String.format("%d", usuario.getIDUsuario());
            }
            case 1 -> {
                return String.format("%s", usuario.getNome());
            }
            case 2 -> {
                return String.format("%s", usuario.getEmail());
            }
            case 3 -> {
                return String.format("%s", usuario.getTelefone());
            }
            case 4 -> {
                return String.format("%s", usuario.getSenha());
            }
        }
        return null;
    }
    
    @Override
    public int getRowCount() {
        return dadosTabUsuarios3.size();
    }

    @Override
    public int getColumnCount() {
        return colunasTabUsuarios3.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return colunasTabUsuarios3[column];
    }
    
    // -------------------------------------------------------------------------
}
