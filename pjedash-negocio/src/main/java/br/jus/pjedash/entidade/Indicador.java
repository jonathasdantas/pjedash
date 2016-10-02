package br.jus.pjedash.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.jus.pjedash.comum.BaseEntity;

/**
 * Indicador generated by Edilson
 */
@Entity
@Table(name = Indicador.TABLE_NAME)
@SequenceGenerator(allocationSize = 1, name = "gen_indicador", sequenceName = "secao_id_indicador_seq")
public class Indicador extends BaseEntity<Integer> {

	private static final long serialVersionUID = 4594625642635423343L;

	public static final String TABLE_NAME = "indicador";

	private Integer id;
	private String nome;
	private String descricao;
	private String meta;

	public Indicador() {

	}

	@Id
	@GeneratedValue(generator = "gen_indicador", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_indicador", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "nome", unique = true)
	@NotNull(message="Nome do Indicador obrigatório")
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "descricao")
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Column(name = "meta")
	public String getMeta() {
		return this.meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}
}