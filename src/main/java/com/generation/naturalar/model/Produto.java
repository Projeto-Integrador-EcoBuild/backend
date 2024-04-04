package com.generation.naturalar.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "É necessario inserir o nome")
	@Size(min = 3, max = 100, message = "o nome precisa ter no minimo 3 letras e max 100")
	private String nome;
	
	@NotBlank(message = "É necessario inserir a descrição")
	@Size(min = 5, max = 200, message = "a descrição precisa ter no minimo 5 letras e max 200")
	private String descricao;
	
	@NotBlank(message = "É necessario inserir a foto")
	private String foto;
	
	@NotNull(message = "O preço do produto é obrigatório.")
    @Column(precision = 8, scale = 2)
	private BigDecimal preco;
	
	@NotNull(message = "A quantidade do produto é obrigatória.")
	private int quantidade;
	
	@JsonIgnoreProperties("produto")
    @ManyToOne
	private Categoria categoria;
	
	@JsonIgnoreProperties("produto")
	@ManyToOne
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
