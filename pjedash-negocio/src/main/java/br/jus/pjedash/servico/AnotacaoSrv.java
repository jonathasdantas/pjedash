package br.jus.pjedash.servico;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.jus.pjedash.entidade.Anotacao;
import br.jus.pjedash.interfaces.remote.IAnotacaoSrvRemote;
import br.jus.pjedash.repositorio.AnotacaoDAO;

@Stateless
public class AnotacaoSrv implements IAnotacaoSrvRemote {

    @Inject
    private AnotacaoDAO anotacaoDAO;

    public List<Anotacao> recuperarEntidades() {
        return anotacaoDAO.recuperarEntidades();
    }

    public Anotacao recuperarPorId(Integer id) {
        return anotacaoDAO.recuperarEntidadePorId(id);
    }

    public Anotacao inserir(Anotacao anotacao) {
        return anotacaoDAO.inserir(anotacao);
    }

    public Anotacao alterar(Anotacao anotacao) {
        anotacao.setDataUltimaAlteracao(new Date());
        return anotacaoDAO.alterar(anotacao);
    }

    public void excluirPorId(Integer id) {
        anotacaoDAO.excluirPorId(id);
    }

    public List<Anotacao> recuperarAnotacoesPorNpuProcesso(String npuProcesso) {
        return anotacaoDAO.recuperarAnotacoesPorNpuProcesso(npuProcesso);
    }

    public List<Anotacao> buscarAnotacoesOrgaoPorTexto(Integer idOrgaoJulgador, String texto) {
        return anotacaoDAO.buscarAnotacoesOrgaoPorTexto(idOrgaoJulgador, texto);
    }

    public List<Anotacao> recuperarUltimasAnotacoesAlteradas(Integer quantidadeAnotacoes, Integer idOrgaoJulgador) {
        return anotacaoDAO.recuperarUltimasAnotacoesAlteradas(quantidadeAnotacoes, idOrgaoJulgador);
    }
}
