package br.jus.pjedash.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.jus.pjedash.comum.BaseEntity;

/**
 * Tag generated by Edilson
 */
@Entity
@Table(name = Tag.TABLE_NAME)
@SequenceGenerator(allocationSize = 1, name = "gen_tag", sequenceName = "tag_id_tag_seq")
public class Tag extends BaseEntity<Integer> {

	private static final long serialVersionUID = 4594625642635423343L;

	public static final String TABLE_NAME = "tag";

	private Integer id;
	private String descricao;
	private String cor;
	private List<ProcessoTag> processosTag;
	private Integer idOrgaoJulgador;
	
	public Tag() {
		
	}
	
	@Id
	@GeneratedValue(generator = "gen_tag", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_tag", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
		
	@Column(name = "descricao", length = 100, unique = true)
	@Size.List({
		@Size(min=1, message="Descri��o da Tag n�o deve ser vazio"),
		@Size(max=100, message="Descri��o da Tag n�o deve ser maior que {max} caracteres")
		})
	@NotNull(message="Descri��o da Tag obrigat�ria")
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "cor", length = 100)
	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tag")
	public List<ProcessoTag> getProcessosTag() {
		return processosTag;
	}

	public void setProcessosTag(List<ProcessoTag> processosTag) {
		this.processosTag = processosTag;
	}

	@Column(name = "id_orgaojulgador")
	public Integer getIdOrgaoJulgador() {
		return idOrgaoJulgador;
	}

	public void setIdOrgaoJulgador(Integer idOrgaoJulgador) {
		this.idOrgaoJulgador = idOrgaoJulgador;
	}

}