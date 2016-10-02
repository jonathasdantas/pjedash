package br.jus.pjedash.servico.util;

import br.jus.pjedash.util.ConfigurationProperties;

public class Constants {
	
	// Constantes das propriedades de configuração
	
	public static final String PJE2_REST_URL = ConfigurationProperties.getInstance().getProperty("pje2.rest.url");
	
	public static final String REST_PATH_ICNB_JN_ORGAO = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.icnb_jn_orgao");
	
	public static final String REST_PATH_ICNB_JN_ORGAO_ANO = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.icnb_jn_orgao_ano");
	
	public static final String REST_PATH_ICNB_JN_ORGAO_ANO_SEMESTRE = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.icnb_jn_orgao_ano_semestre");
	
	public static final String REST_PATH_ICNB_ORGAO = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.icnb_orgao");
	
	public static final String REST_PATH_ICNB_ORGAO_ANO = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.icnb_orgao_ano");
	
	public static final String REST_PATH_ICNB_ORGAO_ANO_MES = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.icnb_orgao_ano_mes");
	
	public static final String REST_PATH_IAD_JN_ORGAO = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.iad_jn_orgao");
	
	public static final String REST_PATH_IAD_JN_ORGAO_ANO = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.iad_jn_orgao_ano");
	
	public static final String REST_PATH_IAD_JN_ORGAO_ANO_SEMESTRE = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.iad_jn_orgao_ano_semestre");
	
	public static final String REST_PATH_IAD_ORGAO = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.iad_orgao");
	
	public static final String REST_PATH_IAD_ORGAO_ANO = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.iad_orgao_ano");
	
	public static final String REST_PATH_IAD_ORGAO_ANO_MES = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.iad_orgao_ano_mes");
	
	public static final String REST_PATH_MOVIMENTOS = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.movimentos");
	
	public static final String REST_PATH_PROCESSOS_CONCLUSOS = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.processos.conclusos");
	
	public static final String REST_PATH_PROCESSOS_PENDENTES = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.processos.pendentes");
	
	public static final String REST_PATH_PROCESSOS_PARTES = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.processos.partes");
	
	public static final String REST_PATH_PROCESSOS_DADOS = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.processos.dados");
	
	public static final String REST_PATH_TAXA_CONGEST_ORGAO = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.taxa_congestionamento_orgao");
	
	public static final String REST_PATH_TAXA_CONGEST_ORGAO_ANO = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.taxa_congestionamento_orgao_ano");
	
	public static final String REST_PATH_TAXA_CONGEST_ORGAO_ANO_MES = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.taxa_congestionamento_orgao_ano_mes");
	
	public static final String REST_PATH_USUARIOS_VALIDAR = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.usuarios.validar");
	
	public static final String REST_PATH_USUARIOS_BUSCAR_POR_ID = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.usuarios.buscar_por_id");
	
	public static final String REST_PATH_VARIAVEIS_TIPO_INDICADOR = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.variaveis.tipo_indicador");
	
	public static final String REST_PATH_VARIAVEIS_SIGLA = ConfigurationProperties.getInstance().getProperty("pje2.rest.path.variaveis.sigla");

}
