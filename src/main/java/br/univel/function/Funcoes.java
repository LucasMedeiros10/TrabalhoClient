package br.univel.function;

import javax.swing.JOptionPane;

/**
 * Biblioteca de funções de uso no sistema visando adiantar 
 * a produtividade do código 
 * 
 * @author LucasMedeiros
 *
 */

public class Funcoes {
	

	/*
	 * Função para mostrar uma mensagem formatada com ícone de aviso
	 */
	public static void msgAviso(String mensagem){
		JOptionPane.showMessageDialog(null, mensagem, "Aviso", JOptionPane.WARNING_MESSAGE);		
	}

	/*
	 * Função para mostrar uma mensagem formatada com ícone de informação
	 */
	public static void msgInforma(String mensagem){
		JOptionPane.showMessageDialog(null, mensagem, "Informação", JOptionPane.INFORMATION_MESSAGE);		
	}
	
	/*
	 * Função para mostrar uma mensagem formatada com ícone de erro
	 */
	public static void msgErro(String mensagem){
		JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);		
	}
	
	/*
	 *Função para mostrar uma pergunta formatada com ícone de interrogação
	 *
	 * Retorna true se o usuário clicar em "Sim"	
	 */
	public static boolean msgConfirma(String mensagem){
		int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Pergunta", JOptionPane.YES_NO_OPTION);
		return opcao == 0;
	}
	
	
}
