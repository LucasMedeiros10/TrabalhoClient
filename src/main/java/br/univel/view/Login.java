package br.univel.view;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.univel.dto.Profissional;
import br.univel.dto.TipoOperacao;
import br.univel.function.Funcoes;
import br.univel.socket.ClientSocket;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Classe visual Swing para realização de login na aplicação
 * com comunicação direto ao servidor via socket
 * 
 * @author LucasMedeiros
 *
 */

public class Login extends JFrame{
	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	public Login() {
		
		JLabel lblUsurio = new JLabel("Usuário");
		
		JLabel lblSenha = new JLabel("Senha");
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		
		txtSenha = new JPasswordField();
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Profissional requisicao = new Profissional();
				requisicao.setLogin(txtUsuario.getText());
				requisicao.setSenha(txtSenha.getText());
				requisicao.setTipoOperacao(TipoOperacao.LOGIN);
		
			
				Boolean resultado = false;
				ClientSocket socket = new ClientSocket();
				Object object = socket.enviarRequisicao(requisicao);
				
				if(object.getClass().equals(Boolean.class)){
					
					resultado = (Boolean) object;
				
					if(resultado){
						new TelaPrincipal();			
						dispose();
					}else{
						Funcoes.msgErro("Login/Senha inválidos.");
					}
					
				}				
				

			}
		});
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(106)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSenha)
								.addComponent(lblUsurio)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(txtUsuario, Alignment.LEADING)
									.addComponent(txtSenha, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(87)
							.addComponent(btnEntrar, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(90, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(46)
					.addComponent(lblUsurio)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSenha)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEntrar)
						.addComponent(btnSair))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(361, 246);
		setLocationRelativeTo(null);
		setVisible(true);			
	}
}
