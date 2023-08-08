package br.com.gubee.interview.application.domain;

public class ErrorMessagesConstants {
		public static final String GENERIC_EXCEPTION_MESSAGE = "Erro inesperado, favor contatar suporte.";
		public static final String TIMEOUT_EXCEPTION_MESSAGE = "A aplicação excedeu o tempo limite para resposta, tente novamente em alguns instantes.";
		public static final String INVALID_DATA_MESSAGE = "Erro na requisição, favor validar o payload e o endpoint (erro de digitacao ou tipagem).";
		public static final String NOT_FOUND_MESSAGE = "Caminho inválido, endpoint não encontrado.";
		public static final String INVALID_HERO_ID = "Invalid hero identifier!";
		public static final String NAME_IS_TOO_LONG = "Nome muito grande";
		public static final String NAME_IS_MANDATORY = "Nome deve ser informado";
		public static final String RACE_IS_MANDATORY = "Raça deve ser informada";
		public static final String RACE_IS_INVALID = "Deve-se informar uma raça válida";
		public static final String POWER_STATS_IS_MANDATORY = "Deve-se informar os atributos do herói";
}