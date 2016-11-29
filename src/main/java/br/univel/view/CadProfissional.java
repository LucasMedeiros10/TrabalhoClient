package br.univel.view;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.univel.dto.Profissional;
import br.univel.dto.TipoOperacao;
import br.univel.function.Funcoes;
import br.univel.socket.ClientSocket;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

/**
 * Classe visual Swing de cadastro Profissional
 * 
 * @author LucasMedeiros
 *
 */

public class CadProfissional extends JFrame{
	private JTextField txtNome;
	private JTextField txtDataNasc;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private PsqProfissionais telaPesquisa;
	private Profissional profissional = new Profissional();
	
	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
		txtNome.setText(this.profissional.getNome());
		txtLogin.setText(this.profissional.getLogin());
		txtDataNasc.setText(new SimpleDateFormat("dd/MM/yyyy").format(this.profissional.getDataNascimento()));
	}

	public PsqProfissionais getTelaPesquisa() {
		return telaPesquisa;
	}

	public void setTelaPesquisa(PsqProfissionais telaPesquisa) {
		this.telaPesquisa = telaPesquisa;
	}

	public CadProfissional() {
		setResizable(false);
		
		JLabel lblCadastroDeProfissional = new JLabel("Cadastro de Profissional");
		lblCadastroDeProfissional.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeProfissional.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblNome = new JLabel("Nome:");
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		
		JLabel lblDataNasc = new JLabel("Data Nasc.");
		
		txtDataNasc = new JTextField();
		txtDataNasc.setColumns(10);
		
		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		
		JLabel lblLogin = new JLabel("Login");
		
		JLabel lblSenha = new JLabel("Senha");
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				
				if(profissional.getId() == null){
					profissional.setTipoOperacao(TipoOperacao.INSERCAO);
					profissional.setId(0);
				}else{
					profissional.setTipoOperacao(TipoOperacao.EDICAO);
				}
				profissional.setNome(txtNome.getText());
				profissional.setLogin(txtLogin.getText());
				profissional.setSenha(txtSenha.getText());
				try {
					profissional.setDataNascimento((Date)format.parse(txtDataNasc.getText()));
				} catch (ParseException e1) {
					profissional.setDataNascimento(null);
				}
				
				Boolean resultado = false;
				ClientSocket socket = new ClientSocket();
				Object object = socket.enviarRequisicao(profissional);
				
				if(object.getClass().equals(Boolean.class)){
					resultado = (Boolean) object;
				
					if(resultado){
						Funcoes.msgInforma("Profissional gravado com sucesso!");
						telaPesquisa.montarConsulta();
						dispose();
					}else{
						Funcoes.msgErro("Falha ao gravar o profissional.");
					}
					
				}else if(object.getClass().equals(String.class)){
					Funcoes.msgAviso((String) object);					
				}				
			}
		});
		
		txtSenha = new JPasswordField();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblCadastroDeProfissional, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblSenha)
						.addComponent(lblDataNasc)
						.addComponent(lblNome)
						.addComponent(lblLogin))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGravar)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(txtSenha, Alignment.LEADING)
							.addComponent(txtLogin, Alignment.LEADING)
							.addComponent(txtDataNasc, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)))
					.addContainerGap(91, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblCadastroDeProfissional)
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
						.addComponent(txtLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLogin))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSenha)
						.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnGravar)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(461, 271);
		setLocationRelativeTo(null);
	}

}
