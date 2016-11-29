package br.univel.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.univel.dto.Cliente;
import br.univel.dto.TipoOperacao;
import br.univel.function.Funcoes;
import br.univel.models.ModeloCliente;
import br.univel.socket.ClientSocket;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

/**
 * Classe visual Swing de pesquisa e listagem de clientes
 * 
 * @author LucasMedeiros
 *
 */

public class PsqClientes extends JFrame{
	
	private JTable tbGrid;
	private List<Cliente> lista = new ArrayList<Cliente>();
	private CadCliente telaCadastro;
	
	public CadCliente getTelaCadastro() {
		telaCadastro = null;
		this.telaCadastro = new CadCliente();
		this.telaCadastro.setTelaPesquisa(this);
		return this.telaCadastro;		
	}

	public void setTelaCadastro(CadCliente telaCadastro) {
		this.telaCadastro = telaCadastro;
	}

	public PsqClientes(){
		setResizable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblTitulo = new JLabel("Pesquisa de Clientes");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTelaCadastro();
				telaCadastro.setVisible(true);
			}
		});
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lista.isEmpty()){
					Funcoes.msgAviso("Nenhum registro a ser alterado.");					
				}else{					
					if(tbGrid.getSelectedRow() == -1){
						Funcoes.msgAviso("Selecione um registro para ser alterado.");
					}else{			
						getTelaCadastro();
						Cliente cliente = ((ModeloCliente) tbGrid.getModel()).getCliente(tbGrid.getSelectedRow());
						telaCadastro.setCliente(cliente);
						telaCadastro.setVisible(true);
					}
				}								
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(lista.isEmpty()){
					Funcoes.msgAviso("Nenhum registro a ser excluído.");					
				}else{					
					if(tbGrid.getSelectedRow() == -1){
						Funcoes.msgAviso("Selecione um registro para ser excluído.");
					}else{					
						if(Funcoes.msgConfirma("Deseja excluir este cliente?")){
							
							Cliente cliente = ((ModeloCliente) tbGrid.getModel()).getCliente(tbGrid.getSelectedRow());
							cliente.setTipoOperacao(TipoOperacao.EXCLUSAO);
							ClientSocket socket = new ClientSocket();
							Object object = socket.enviarRequisicao(cliente);
							
							Boolean resultado = (Boolean) object;
							
							if(resultado){
								Funcoes.msgInforma("Cliente excluído com sucesso!");
								montarConsulta();
							}else{
								Funcoes.msgErro("Falha ao excluir o cliente.");
							}
						}	
					}
				}				
										
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAdicionar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExcluir, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 535, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(52, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblTitulo)
					.addGap(43)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdicionar)
						.addComponent(btnEditar)
						.addComponent(btnExcluir))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		tbGrid = new JTable();
		scrollPane.setViewportView(tbGrid);
		getContentPane().setLayout(groupLayout);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(620, 361);
		setLocationRelativeTo(null);
		setVisible(true);					
		
		// $hide>>$
		montarConsulta();
		// $hide<<$			
	}
	
	public void montarConsulta(){	
		lista.clear();	
		
		
		Cliente requisicao = new Cliente();
		requisicao.setTipoOperacao(TipoOperacao.LISTARTODOS);
		
		ClientSocket socket = new ClientSocket();
		Object object = socket.enviarRequisicao(requisicao);
		lista = (ArrayList<Cliente>) object;
		
		ModeloCliente modelo = new ModeloCliente(lista);//instancia um modelo de tabela

		tbGrid.setRowSorter(null);
		tbGrid.setModel(modelo);//seta a tabela		
	}		
}

