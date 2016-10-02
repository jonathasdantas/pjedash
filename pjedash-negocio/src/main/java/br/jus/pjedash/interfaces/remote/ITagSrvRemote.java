package br.jus.pjedash.interfaces.remote;

import java.util.List;

import javax.ejb.Remote;

import br.jus.pjedash.comum.IBaseSrvRemote;
import br.jus.pjedash.entidade.Tag;

@Remote
public interface ITagSrvRemote extends IBaseSrvRemote<Tag, Integer> {

	public List<Tag> recuperarTagsPorIdUsuario(Integer idUsuario);
	public List<Tag> recuperarTagsPorProcessoUsuario(String npuProcesso, Integer idUsuario);
	
}
