package com.generation.naturalar.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;

import com.generation.naturalar.model.Produto;
import com.generation.naturalar.repository.CategoriaRepository;
import com.generation.naturalar.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	

	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll());
	}
	
	@GetMapping("/preco/{preco}")
	public ResponseEntity<List<Produto>> getByPrice(@PathVariable BigDecimal preco){
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAllByPrecoLessThanEqual(preco));
	}
	
	
	@GetMapping("/preco/asc")
	public ResponseEntity<List<Produto>> getByPriceAsc(){
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAllByOrderByPrecoAsc());
	}
	
	  @GetMapping("/preco/desc")
    public ResponseEntity<List<Produto>> getAllByPriceDesc() {
        List<Produto> produtos = produtoRepository.findAllByOrderByPrecoDesc();
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByName(@PathVariable String nome ){
		 String nomePattern = "%" + nome + "%";
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAllByNomeLike(nomePattern));
	}
	
	@GetMapping("/categoria/{id}")
	public ResponseEntity<List<Produto>> getByCategoria(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAllByCategoria_id(id));
	}
 	
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return produtoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.OK).build());
				
				
	}
	
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
		if (categoriaRepository.existsById(produto.getCategoria().getId())) {
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}
	
	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto p){
		if (produtoRepository.existsById(p.getId())) {
			if(categoriaRepository.existsById(p.getCategoria().getId())) {
				return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(p));
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"CATEGORIA NAO EXISTE",null);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Produto> produtoBanco = produtoRepository.findById(id);
		if(produtoBanco.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		produtoRepository.deleteById(id);
	}
	
	
}
