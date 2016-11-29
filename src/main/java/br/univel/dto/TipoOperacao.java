package br.univel.dto;

/**
 * Enum para representar os tipos de requisições possíveis
 * que podem ser realizadas no socket
 * 
 * @author LucasMedeiros
 *
 */

public enum TipoOperacao {

	INSERCAO,
	EDICAO,
	EXCLUSAO,
	LISTAR,
	LISTARTODOS,
	LOGIN
}
