package com.clothing_store.Service;

import com.clothing_store.Entity.ClienteEntity;
import com.clothing_store.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteEntity save(ClienteEntity clienteEntity) {
        try {
            return this.clienteRepository.save(clienteEntity);
        } catch (Exception e) {
            System.out.println("Erro ao salvar o cliente: " + e.getMessage());
            return new ClienteEntity();
        }
    }

    public ClienteEntity update(ClienteEntity clienteEntity, Long id) {
        try {
            clienteEntity.setId(id);
            return this.clienteRepository.save(clienteEntity);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o cliente: " + e.getMessage());
            return new ClienteEntity();
        }
    }

    public String delete(Long id) {
        try {
            this.clienteRepository.deleteById(id);
            return "Deletado";
        } catch (Exception e) {
            return "Erro ao deletar cliente";
        }
    }

    public ClienteEntity findById(Long id) {
        try {
            Optional<ClienteEntity> cliente = this.clienteRepository.findById(id);
            return cliente.orElse(null);
        } catch (Exception e) {
            System.out.println("Erro ao procurar o cliente com ID " + id + ": " + e.getMessage());
            return new ClienteEntity();
        }
    }

    public List<ClienteEntity> findAll() {
        try {
            return this.clienteRepository.findAll();
        } catch (Exception e) {
            System.out.println("Erro ao buscar todos os clientes: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<ClienteEntity> clienteBetween18And35() {
        try {
            return this.clienteRepository.clienteBetween18And35();
        } catch (Exception e) {
            System.out.println("Erro ao buscar clientes entre 18 a 35 anos: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}
