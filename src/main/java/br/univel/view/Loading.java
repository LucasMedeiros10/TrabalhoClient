package br.univel.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 * Jframe para mostrar ao usuário um Gif carregando,
 * como forma de demonstrar que a aplicação está processando
 * as informações
 * 
 * @author LucasMedeiros
 *
 */

public class Loading extends JFrame implements Runnable{

	private Boolean executando;
	
	public Boolean getExecutando() {
		return executando;
	}

	public void setExecutando(Boolean executando) {
		this.executando = executando;
	}

	public Loading(){
		setResizable(false);
		setType(Type.POPUP);
		setExecutando(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(333, 180);
		
		JLabel lblImagem = new JLabel("");
		lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
		
		String nomeArquivo  = this.getClass()
				   .getClassLoader()
				   .getResource("")
				   .getPath()
				   .concat("br/univel/view/loading.gif");
		
		lblImagem.setIcon(new ImageIcon(nomeArquivo));
		getContentPane().add(lblImagem, BorderLayout.CENTER);
		getContentPane().setBackground(Color.WHITE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	
	@Override
	public void run() {
		while(executando){
			try {
				Thread.currentThread().sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//fecha a tela
		dispose();
	}

}
