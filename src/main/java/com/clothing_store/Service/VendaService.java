package com.clothing_store.Service;

import com.clothing_store.Entity.VendaEntity;
import com.clothing_store.Repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public VendaEntity save(VendaEntity vendaEntity) {
        try {
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

    public void delete(Long id) {
        try {
            this.vendaRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Erro ao deletar a venda com ID " + id + ": " + e.getMessage());
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
