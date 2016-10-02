package br.jus.pjedash.interfaces.remote;

import java.util.List;

import javax.ejb.Remote;

import br.jus.pjedash.comum.IBaseSrvRemote;
import br.jus.pjedash.entidade.ProcessoTag;

@Remote
public interface IProcessoTagSrvRemote extends IBaseSrvRemote<ProcessoTag, Integer> {
	
	public List<Integer> recuperarProcessosTagPorTagUsuario(Integer idTag, Integer idUsuario);

	public List<String> recuperarProcessosPorTagOrgaoJulgador(Integer idTag, Integer idOrgaoJulgador);

	public Integer recuperarProcessosTagPorTagProcessoUsuario(Integer idTag, Integer idUsuario, String npuProcesso);

	public List<Object[]> recuperarAssociacoesTagsProcessosPorOrgao(Integer idOrgaoJulgador);

}
