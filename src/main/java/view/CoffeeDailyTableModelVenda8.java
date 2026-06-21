/*
    
*/
package view;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;
import model.Pagamento;


public class CoffeeDailyTableModelVenda8 extends AbstractTableModel {
    
    // ---------- Interface 8: Consultar Vendas ----------
    
    // Atributos para manipular a tabela tabVendas8 de Consultar Vendas na Interface 8
    private final String[] colunasTabVendas8 = {"ID", "Email", "Forma de Pagamento", "Parcelas", "Valor Pago"};
    private final List<Pagamento> dadosTabVendas8Pagamento = new ArrayList<>();
    private final List<Usuario> dadosTabVendas8Usuario = new ArrayList<>();
    
    // Método para atualizar a tabela tabVendas8 toda vez que uma consulta for feita antes ou depois da inserção de novos dados
    public void atualizarTabVendas8(List<Pagamento> novosDados1, List<Usuario> novosDados2) {
        // Limpar tudo que há na tabela
        this.dadosTabVendas8Pagamento.clear();
        this.dadosTabVendas8Usuario.clear();
        // Carregar dados atualizados
        this.dadosTabVendas8Pagamento.addAll(novosDados1);
        this.dadosTabVendas8Usuario.addAll(novosDados2);
        // Atualizar a tabela
        this.fireTableDataChanged();
    }
    
    // Implementação dos métodos abstratos para Consultar Vendas
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Pagamento pagamento = dadosTabVendas8Pagamento.get(rowIndex);
        Usuario usuario = dadosTabVendas8Usuario.get(rowIndex);
        
        switch (columnIndex) {
            case 0 -> {
                return String.format("%d", usuario.getIDUsuario());
            }
            case 1 -> {
                return String.format("%s", usuario.getEmail());
            }
            case 2 -> {
                return String.format("%s", pagamento.getForma_pagamento());
            }
            case 3 -> {
                return String.format("%d", pagamento.getParcelas());
            }
            case 4 -> {
                return String.format("%.2f", pagamento.getValor_pago());
            }
        }
        return null;
    }
    
    @Override
    public int getRowCount() {
        return dadosTabVendas8Pagamento.size();
    }

    public int getRowCount2() {
        return dadosTabVendas8Usuario.size();
    }
    
    @Override
    public int getColumnCount() {
        return colunasTabVendas8.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return colunasTabVendas8[column];
    }

    // -------------------------------------------------------------------------
}
