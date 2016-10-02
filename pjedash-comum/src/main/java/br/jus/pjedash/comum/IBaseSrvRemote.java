package br.jus.pjedash.comum;

import java.util.List;

@SuppressWarnings("rawtypes")
public interface IBaseSrvRemote<E extends BaseEntity, K> {

	public E recuperarPorId(K id);
	public List<E> recuperarEntidades();
	public E inserir(E tag);
	public E alterar(E tag);
	public void excluirPorId(K id);

}
