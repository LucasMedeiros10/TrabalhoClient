package br.univel.models;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import br.univel.dto.Cliente;

/**
 * AbstractTableModel para representar um modelo de JTable de Clientes 
 * 
 * @author LucasMedeiros
 *
 */

public class ModeloCliente extends AbstractTableModel{

	private List<Cliente> lista;
	private SimpleDateFormat formatDate;
	
	public ModeloCliente(List<Cliente> lista) {
		this.lista = lista;
		formatDate = new SimpleDateFormat("dd/MM/yyyy");
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}
	
	@Override
	public String getColumnName(int column) {
		switch( column) {
			case 0:
				return "Nome";
			case 1:
				return "Nasc.";
			case 2:
				return "CPF";
			case 3:
				return "RG";
			default:
				return super.getColumnName(column);
		}
	}		
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Cliente cliente = lista.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return cliente.getNome();
		case 1:
			if(cliente.getDataNascimento() != null){
				return formatDate.format(cliente.getDataNascimento());
			}else{
				return null;
			}
		case 2:
			return cliente.getCpf();
		case 3:
			return cliente.getRg();
		default:
			return "erro";
		}
	}
	
	public Cliente getCliente(int selecionado){
		return lista.get(selecionado);
	}

}
