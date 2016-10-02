package br.jus.pjedash.interfaces.remote;

import java.util.List;

import javax.ejb.Remote;

import br.jus.pjedash.comum.IBaseSrvRemote;
import br.jus.pjedash.entidade.Anotacao;

@Remote
public interface IAnotacaoSrvRemote extends IBaseSrvRemote<Anotacao, Integer> {

	public List<Anotacao> recuperarAnotacoesPorNpuProcesso(String npuProcesso);

	public List<Anotacao> buscarAnotacoesOrgaoPorTexto(Integer idOrgaoJulgador, String texto);

	public List<Anotacao> recuperarUltimasAnotacoesAlteradas(Integer quantidadeAnotacoes, Integer idOrgaoJulgador);

}
