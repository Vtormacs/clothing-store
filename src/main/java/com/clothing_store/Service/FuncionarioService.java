package com.clothing_store.Service;

import com.clothing_store.Entity.FuncionarioEntity;
import com.clothing_store.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioEntity save(FuncionarioEntity funcionarioEntity) {
        try {
            return this.funcionarioRepository.save(funcionarioEntity);
        } catch (Exception e) {
            System.out.println("Erro ao salvar o funcionário: " + e.getMessage());
            return new FuncionarioEntity();
        }
    }

    public FuncionarioEntity update(FuncionarioEntity funcionarioEntity, Long id) {
        try {
            funcionarioEntity.setId(id);
            return this.funcionarioRepository.save(funcionarioEntity);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o funcionário: " + e.getMessage());
            return new FuncionarioEntity();
        }
    }

    public String delete(Long id) {
        try {
            this.funcionarioRepository.deleteById(id);
            return "Deletado";
        } catch (Exception e) {
            return "Erro ao deletar funcionario";
        }
    }

    public FuncionarioEntity findById(Long id) {
        try {
            Optional<FuncionarioEntity> funcionario = this.funcionarioRepository.findById(id);
            return funcionario.orElse(null);
        } catch (Exception e) {
            System.out.println("Erro ao procurar o funcionário com ID " + id + ": " + e.getMessage());
            return new FuncionarioEntity();
        }
    }

    public List<FuncionarioEntity> findAll() {
        try {
            return this.funcionarioRepository.findAll();
        } catch (Exception e) {
            System.out.println("Erro ao buscar todos os funcionários: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
