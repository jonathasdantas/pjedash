package br.jus.pjedash.servico.rest.cliente;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import br.jus.pjedash.entidade.Usuario;
import br.jus.pjedash.servico.util.Constants;
import br.jus.pjedash.servico.util.RestClientUtil;

public class UsuarioSrvRestClient {

	public Response validarUsuario(Usuario usuario) {
		return RestClientUtil.doPost(Constants.PJE2_REST_URL, Constants.REST_PATH_USUARIOS_VALIDAR, Entity.json(usuario));
	}

	public Response recuperarUsuarioPorId(Integer idUsuario) {
		return RestClientUtil.doGet(Constants.PJE2_REST_URL, Constants.REST_PATH_USUARIOS_BUSCAR_POR_ID, "id", idUsuario);
	}

}
