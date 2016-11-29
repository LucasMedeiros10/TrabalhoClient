package br.univel.models;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import br.univel.dto.Profissional;

/**
 * AbstractTableModel para representar um modelo de JTable de Profissionais
 * 
 * @author LucasMedeiros
 *
 */
public class ModeloProfissional extends AbstractTableModel{

	private List<Profissional> lista;
	private SimpleDateFormat formatDate;
	
	public ModeloProfissional(List<Profissional> lista) {
		formatDate = new SimpleDateFormat("dd/MM/yyyy");
		this.lista = lista;
	}

	@Override
	public int getColumnCount() {
		return 3;
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
				return "Login";
			default:
				return super.getColumnName(column);
		}
	}		

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Profissional profissional = lista.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return profissional.getNome();
		case 1:
			if(profissional.getDataNascimento() != null){
				return formatDate.format(profissional.getDataNascimento());
			}else{
				return null;
			}
		case 2:
			return profissional.getLogin();
		default:
			return "erro";
		}
	}
	
	public Profissional getProfissional(int selecionado){
		return lista.get(selecionado);
	}

}
