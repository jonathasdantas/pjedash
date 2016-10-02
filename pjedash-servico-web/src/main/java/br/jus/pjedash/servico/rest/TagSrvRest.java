package br.jus.pjedash.servico.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.jus.pjedash.comum.EJBServiceLocator;
import br.jus.pjedash.dto.TagDTO;
import br.jus.pjedash.entidade.ProcessoTag;
import br.jus.pjedash.entidade.Tag;
import br.jus.pjedash.interceptor.TratamentoExcecao;
import br.jus.pjedash.interfaces.remote.IProcessoTagSrvRemote;
import br.jus.pjedash.interfaces.remote.ITagSrvRemote;

@Path("tags")
@TratamentoExcecao
@PermitAll
public class TagSrvRest {

	private ITagSrvRemote tagService = EJBServiceLocator.locateEJBStateless(ITagSrvRemote.class);
	private IProcessoTagSrvRemote processoTagService = EJBServiceLocator.locateEJBStateless(IProcessoTagSrvRemote.class);
	
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluir(@PathParam("id") Integer id) {
		Tag tag = tagService.recuperarPorId(id);
		if (tag != null) {
			tagService.excluirPorId(id);
			return Response.ok(tag).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarEntidades() {
		return Response.ok(tagService.recuperarEntidades()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarPorId(@PathParam("id") Integer id) {
		return Response.ok(tagService.recuperarPorId(id)).build();
	}
	
	@GET
	@Path("/usuario/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarPorIdUsuario(@PathParam("idUsuario") Integer idUsuario) {
		return Response.ok(tagService.recuperarTagsPorIdUsuario(idUsuario)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserir(Tag parametro) {
		Tag tag = tagService.inserir(parametro);
		return Response.ok(tag).build();
	}
	
	@GET
	@Path("/processo/{npuProcesso}/usuario/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarTagsPorProcessoUsuario(@PathParam("npuProcesso") String npuProcesso, @PathParam("idUsuario") Integer idUsuario) {
		return Response.ok(tagService.recuperarTagsPorProcessoUsuario(npuProcesso, idUsuario)).build();
	}
	
	@GET
	@Path("{id}/processos/orgao/{idOrgaoJulgador}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarProcessosPorTagOrgaoJulgador(@PathParam("id") Integer id, @PathParam("idOrgaoJulgador") Integer idOrgaoJulgador) {
		Map<String, List<String>> resultado = new HashMap<String, List<String>>();
		resultado.put("npus", processoTagService.recuperarProcessosPorTagOrgaoJulgador(id, idOrgaoJulgador));
		return Response.ok(resultado).build();
	}

	@GET
	@Path("/associacoes/orgao/{idOrgaoJulgador}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recuperarAssociacoesTagsProcessosPorOrgao(@PathParam("idOrgaoJulgador") Integer idOrgaoJulgador) {
		List<Object[]> associacoes = processoTagService.recuperarAssociacoesTagsProcessosPorOrgao(idOrgaoJulgador);
		List<TagDTO> tagsDTO = new ArrayList<TagDTO>();
		Tag tag;
		Integer tagId;
		Long tagHits;
		for (Object[] tagHit : associacoes) {
			tagId = ((Number) tagHit[0]).intValue();
			tagHits = ((Number) tagHit[1]).longValue();
			
			tag = tagService.recuperarPorId(tagId);
			
			tagsDTO.add(new TagDTO(tag, tagHits));
		}
		return Response.ok(tagsDTO).build();
	}

	@DELETE
	@Path("/{id}/processo/{npuProcesso}/usuario/{idUsuario}")
	public Response excluirTagRelacionada(@PathParam("id") Integer idTag, @PathParam("npuProcesso") String npuProcesso, @PathParam("idUsuario") Integer idUsuario) {
		List<Integer> processosTag = processoTagService.recuperarProcessosTagPorTagUsuario(idTag, idUsuario);
		Integer processoTag = processoTagService.recuperarProcessosTagPorTagProcessoUsuario(idTag, idUsuario, npuProcesso);
		
		Tag tag = tagService.recuperarPorId(idTag);
		boolean tagRemovida = false;
		
		if (processosTag != null && !processosTag.isEmpty()) {
			processoTagService.excluirPorId(processoTag);
			
			if (processosTag.size() == 1) {
				tagService.excluirPorId(idTag);
				tagRemovida = true;
			}
		}
		
		if (tagRemovida) {
			return Response.ok(tag).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	@POST
	@Path("/processo/{npuProcesso}/usuario/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserirProcessoTag(Tag tag, @PathParam("npuProcesso") String npuProcesso, @PathParam("idUsuario") Integer idUsuario) {
		ProcessoTag processoTag = new ProcessoTag();
		processoTag.setNpuProcesso(npuProcesso);
		processoTag.setUsuarioId(idUsuario);
		processoTag.setTag(tag);
		
		ProcessoTag novoProcessoTag = processoTagService.inserir(processoTag);
		
		return Response.ok(novoProcessoTag).build();
	}

	@PUT
	@Path("/{id}/processo/{npuProcesso}/usuario/{idUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarProcessoTag(Tag tag, @PathParam("id") Integer id, @PathParam("npuProcesso") String npuProcesso, @PathParam("idUsuario") Integer idUsuario) {
		ProcessoTag processoTag = new ProcessoTag();
		processoTag.setNpuProcesso(npuProcesso);
		processoTag.setUsuarioId(idUsuario);
		processoTag.setTag(tag);
		
		ProcessoTag novoProcessoTag = processoTagService.alterar(processoTag);
		
		return Response.ok(novoProcessoTag).build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response alterar(Tag parametro, @PathParam("id") Integer idProcesso) {
		return Response.ok(tagService.alterar(parametro)).build();
	}


}
