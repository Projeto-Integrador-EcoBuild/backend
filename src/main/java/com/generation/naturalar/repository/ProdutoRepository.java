package com.generation.naturalar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.naturalar.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
}
