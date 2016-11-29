package br.univel.view;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import br.univel.dto.Cliente;
import br.univel.dto.TipoOperacao;
import br.univel.function.Funcoes;
import br.univel.socket.ClientSocket;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

/**
 * Classe visual Swing para cadastro de cliente 
 * 
 * @author LucasMedeiros
 *
 */

public class CadCliente extends JFrame{
	private JTextField txtNome;
	private JTextField txtDataNasc;
	private JTextField txtCPF;
	private JTextField txtRG;
	private PsqClientes telaPesquisa;
	private Cliente cliente = new Cliente();
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		txtCPF.setText(this.cliente.getCpf());
		txtNome.setText(this.cliente.getNome());
		txtRG.setText(this.cliente.getRg());
		txtDataNasc.setText(new SimpleDateFormat("dd/MM/yyyy").format(this.cliente.getDataNascimento()));
	}

	public PsqClientes getTelaPesquisa() {
		return telaPesquisa;
	}

	public void setTelaPesquisa(PsqClientes telaPesquisa) {
		this.telaPesquisa = telaPesquisa;
	}

	public CadCliente() {
		setResizable(false);
		
		JLabel lblCadastroDeCliente = new JLabel("Cadastro de Cliente");
		lblCadastroDeCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeCliente.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblNome = new JLabel("Nome:");
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lblDataNasc = new JLabel("Data Nasc.");
		
		txtDataNasc = new JTextField();
		txtDataNasc.setColumns(10);
		
		txtCPF = new JTextField();
		txtCPF.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF");
		
		JLabel lblRg = new JLabel("RG");
		
		txtRG = new JTextField();
		txtRG.setColumns(10);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				
				if(cliente.getId() == null){
					cliente.setTipoOperacao(TipoOperacao.INSERCAO);					
					cliente.setId(0);
				}else{
					cliente.setTipoOperacao(TipoOperacao.EDICAO);					
				}
				cliente.setCpf(txtCPF.getText());
				cliente.setNome(txtNome.getText());
				cliente.setRg(txtRG.getText());
				try {
					cliente.setDataNascimento((Date)format.parse(txtDataNasc.getText()));
				} catch (ParseException e) {
					cliente.setDataNascimento(null);
				}
				
				Boolean resultado = false;
				ClientSocket socket = new ClientSocket();
				Object object = socket.enviarRequisicao(cliente);
				
				if(object.getClass().equals(Boolean.class)){
					resultado = (Boolean) object;
				
					if(resultado){
						Funcoes.msgInforma("Cliente gravado com sucesso!");
						telaPesquisa.montarConsulta();
						dispose();
					}else{
						Funcoes.msgErro("Falha ao gravar o cliente.");
					}
					
				}else if(object.getClass().equals(String.class)){
					Funcoes.msgAviso((String) object);					
				}
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblCadastroDeCliente, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblRg)
						.addComponent(lblDataNasc)
						.addComponent(lblNome)
						.addComponent(lblCpf))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGravar)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(txtRG, Alignment.LEADING)
							.addComponent(txtCPF, Alignment.LEADING)
							.addComponent(txtDataNasc, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)))
					.addContainerGap(135, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblCadastroDeCliente)
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataNasc)
						.addComponent(txtDataNasc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCpf))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRg)
						.addComponent(txtRG, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnGravar)
					.addContainerGap(43, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(461, 271);
		setLocationRelativeTo(null);
	}
	
}
