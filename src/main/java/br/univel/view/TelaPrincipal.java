package br.univel.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Classe visual Swing, contém os menus que instanciam as 
 * pesquisas
 * 
 * @author LucasMedeiros
 *
 */

public class TelaPrincipal extends JFrame{
	public TelaPrincipal() {		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Cadastro");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PsqClientes();
			}
		});
		mnNewMenu.add(mntmClientes);
		
		JMenuItem mntmProfissionais = new JMenuItem("Profissionais");
		mntmProfissionais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new PsqProfissionais();
			}
		});		
		mnNewMenu.add(mntmProfissionais);
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setSize(448, 262);
		setLocationRelativeTo(null);
		setVisible(true);	
		
	}

}
