package com.clothing_store.Service;

import com.clothing_store.Entity.ProdutoEntity;
import com.clothing_store.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoEntity save(ProdutoEntity produtoEntity) {
        try {
            return this.produtoRepository.save(produtoEntity);
        } catch (Exception e) {
            System.out.println("Erro ao salvar o produto: " + e.getMessage());
            return new ProdutoEntity();
        }
    }

    public ProdutoEntity update(ProdutoEntity produtoEntity, Long id) {
        try {
            produtoEntity.setId(id);
            return this.produtoRepository.save(produtoEntity);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o produto: " + e.getMessage());
            return new ProdutoEntity();
        }
    }

    public void delete(Long id) {
        try {
            this.produtoRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro ao deletar o produto com ID " + id + ": " + e.getMessage());
        }
    }

    public ProdutoEntity findById(Long id) {
        try {
            Optional<ProdutoEntity> produto = this.produtoRepository.findById(id);
            return produto.orElse(null);
        } catch (Exception e) {
            System.out.println("Erro ao procurar o produto com ID " + id + ": " + e.getMessage());
            return new ProdutoEntity();
        }
    }

    public List<ProdutoEntity> findAll() {
        try {
            return this.produtoRepository.findAll();
        } catch (Exception e) {
            System.out.println("Erro ao buscar todos os produtos: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
