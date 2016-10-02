package br.jus.pjedash.servico;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.pjedash.entidade.ProcessoTag;
import br.jus.pjedash.interfaces.remote.IProcessoTagSrvRemote;
import br.jus.pjedash.repositorio.ProcessoTagDAO;

@Stateless
public class ProcessoTagSrv implements IProcessoTagSrvRemote {

	@Inject
	private ProcessoTagDAO processoTagDAO;


	public List<ProcessoTag> recuperarEntidades() {
		return processoTagDAO.recuperarEntidades();
	}

	public ProcessoTag recuperarPorId(Integer id) {
		return processoTagDAO.recuperarEntidadePorId(id);
	}
	
	public ProcessoTag inserir(ProcessoTag processoTag) {
		return processoTagDAO.inserir(processoTag);
	}

	public ProcessoTag alterar(ProcessoTag processoTag) {
		return processoTagDAO.alterar(processoTag);
	}

	public void excluirPorId(Integer id) {
		processoTagDAO.excluirPorId(id);
	}

	public List<Integer> recuperarProcessosTagPorTagUsuario(Integer idTag, Integer idUsuario) {
		return processoTagDAO.recuperarProcessosTagPorTagUsuario(idTag, idUsuario);
	}

	public List<String> recuperarProcessosPorTagOrgaoJulgador(Integer idTag, Integer idOrgaoJulgador) {
		return processoTagDAO.recuperarProcessosPorTagOrgaoJulgador(idTag, idOrgaoJulgador);
	}

	public Integer recuperarProcessosTagPorTagProcessoUsuario(Integer idTag, Integer idUsuario, String npuProcesso) {
		return processoTagDAO.recuperarProcessosTagPorTagProcessoUsuario(idTag, idUsuario, npuProcesso);
	}

    public List<Object[]> recuperarAssociacoesTagsProcessosPorOrgao(Integer idOrgaoJulgador) {
        return processoTagDAO.recuperarAssociacoesTagsProcessosPorOrgao(idOrgaoJulgador);
    }

}
