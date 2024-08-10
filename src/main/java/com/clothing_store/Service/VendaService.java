package com.clothing_store.Service;

import com.clothing_store.Entity.ClienteEntity;
import com.clothing_store.Entity.FuncionarioEntity;
import com.clothing_store.Entity.ProdutoEntity;
import com.clothing_store.Entity.VendaEntity;
import com.clothing_store.Repository.ClienteRepository;
import com.clothing_store.Repository.FuncionarioRepository;
import com.clothing_store.Repository.ProdutoRepository;
import com.clothing_store.Repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public VendaEntity save(VendaEntity vendaEntity) {
        try {
            if (vendaEntity.getCliente() != null && vendaEntity.getCliente().getId() != null) {
                Optional<ClienteEntity> clienteOptional = clienteRepository.findById(vendaEntity.getCliente().getId());
                if (clienteOptional.isPresent()) {
                    vendaEntity.setCliente(clienteOptional.get());
                } else {
                    throw new Exception("Cliente não encontrado");
                }
            }

            if (vendaEntity.getFuncionario() != null && vendaEntity.getFuncionario().getId() != null) {
                Optional<FuncionarioEntity> funcionarioOptional = funcionarioRepository.findById(vendaEntity.getFuncionario().getId());
                if (funcionarioOptional.isPresent()) {
                    vendaEntity.setFuncionario(funcionarioOptional.get());
                } else {
                    throw new Exception("Funcionário não encontrado");
                }
            }

            double total = 0.0;
            if (vendaEntity.getProdutos() != null && !vendaEntity.getProdutos().isEmpty()) {
                List<ProdutoEntity> produtosAssociados = new ArrayList<>();
                for (ProdutoEntity produto : vendaEntity.getProdutos()) {
                    Optional<ProdutoEntity> produtoOptional = produtoRepository.findById(produto.getId());
                    if (produtoOptional.isPresent()) {
                        ProdutoEntity produtoEncontrado = produtoOptional.get();
                        produtosAssociados.add(produtoEncontrado);
                        total += produtoEncontrado.getPreco();
                    } else {
                        throw new Exception("Produto com ID " + produto.getId() + " não encontrado");
                    }
                }
                vendaEntity.setProdutos(produtosAssociados);
            }
            vendaEntity.setTotal(total);

            return this.vendaRepository.save(vendaEntity);
        } catch (Exception e) {
            System.out.println("Erro ao salvar a venda: " + e.getMessage());
            return new VendaEntity();
        }
    }

    public VendaEntity update(VendaEntity vendaEntity, Long id) {
        try {
            vendaEntity.setId(id);
            return this.vendaRepository.save(vendaEntity);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar a venda: " + e.getMessage());
            return new VendaEntity();
        }
    }

    public String delete(Long id) {
        try {
            this.vendaRepository.deleteById(id);
            return "Deletado";
        } catch (Exception e) {
            return "Erro ao deletar venda";
        }
    }

    public VendaEntity findById(Long id) {
        try {
            Optional<VendaEntity> venda = this.vendaRepository.findById(id);
            return venda.orElse(null);
        } catch (Exception e) {
            System.out.println("Erro ao procurar a venda com ID " + id + ": " + e.getMessage());
            return new VendaEntity();
        }
    }

    public List<VendaEntity> findAll() {
        try {
            return this.vendaRepository.findAll();
        } catch (Exception e) {
            System.out.println("Erro ao buscar todas as vendas: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
