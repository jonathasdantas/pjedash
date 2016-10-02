package br.jus.pjedash.mensagem;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public enum CoreMessages implements Messages {
	
	LOGGER_ERRO_INICIALIZACAO("logger.erro.inicializacao"),
	LOGGER_ERRO_PRINT_LOGGER("logger.erro.printlogger"),
	LOGGER_LABEL_DATA("logger.label.data"),
	LOGGER_LABEL_NIVEL("logger.label.nivel"),
	LOGGER_LABEL_MENSAGEM("logger.label.mensagem"),
	LOGGER_LABEL_SERVIDOR("logger.label.servidor"),
	LOGGER_LABEL_THREAD("logger.label.thread"),
	LOGGER_LABEL_DESCONHECIDO("logger.label.desconhecido"),
	
	TRATAMENTO_EXCECAO_AVISO_MENSAGEM_LOG("tratamentoexcecao.aviso.mensagemlog"),
	TRATAMENTO_EXCECAO_AVISO_ENTIDADE("tratamentoexcecao.aviso.entidade"),
	
	PAGINACAO_ERRO_PAGINA_NEGATIVA("paginacao.erro.valornegativo"),
	PAGINACAO_ERRO_INDICE_INVALIDO("paginacao.erro.indiceinvalido"),
	
	GENERICO_ERRO_ORIGEM_NAO_DIRETORIO("generico.erro.origemnaoediretorio"),
	GENERICO_ERRO_DESTINO_NAO_DIRETORIO("generico.erro.destinonaoediretorio"),
	
	GENERICO_AVISO_FORMULARIO_LIMPO("generico.aviso.formulariolimpo"),
	GENERICO_AVISO_SUCESSO_CADASTRO("generico.aviso.sucessocadastro"),
	GENERICO_AVISO_SUCESSO_EDICAO("generico.aviso.sucessoedicao"),
	GENERICO_AVISO_SUCESSO_INATIVACAO("generico.aviso.sucessoinativacao"),
	
	CONSULTA_PUBLICA_AVISO_MAIOR_QUE_LIMITE("consultapublica.aviso.maiorquelimite"),
	CONSULTA_PUBLICA_AVISO_NAO_HA_PROCESSOS("consultapublica.aviso.naohaprocessos"),
	CONSULTA_PUBLICA_AVISO_TOTAL_DE_REGISTROS("consultapublica.aviso.totalregistros"),
	CONSULTA_PUBLICA_ERRO_FILTRO_NOME("consultapublica.erro.filtronome"),
	CONSULTA_PUBLICA_ERRO_FALTA_PARAMETRO("consultapublica.erro.faltaparametro"),
	CONSULTA_PUBLICA_ERRO_CARREGAR_PROCESSO("consultapublica.erro.carregarprocesso"),
	CONSULTA_PUBLICA_ERRO_CARREGAR_MOVIMENTACOES("consultapublica.erro.carregarmovimentacoes"),
	CONSULTA_PUBLICA_ERRO_CAPTCHA("consultapublica.erro.captcha"),	
	;

	private String key;

	private static ResourceBundle messages = ResourceBundle.getBundle("core_messages");

	private CoreMessages(String key) {
		this.key = key;
	}

	public String getMessage(Object... params) {
		return MessageFormat.format(CoreMessages.messages.getString(this.key), params);
	}

	public static String getAnotherMessage(String anotherKey, Object... params) {
		return MessageFormat.format(CoreMessages.messages.getString(anotherKey), params);
	}
	
}