package com.generation.naturalar.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.naturalar.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	public List<Produto> findAllByPrecoLessThanEqual(@Param("preco") BigDecimal preco);

	public List<Produto> findAllByNomeLike(@Param("nome") String nome);
	
	public List<Produto> findAllByCategoria_id(@Param("categoria_id") Long id);
	
	public List<Produto> findAllByOrderByPrecoDesc();
	
	public List<Produto> findAllByOrderByPrecoAsc();
}
