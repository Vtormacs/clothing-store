package com.clothing_store.Repository;

import com.clothing_store.Entity.VendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<VendaEntity, Long> {

    @Query(value = "SELECT v.* FROM tb_venda v JOIN tb_cliente c ON v.cliente_id = c.id WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :nomeCliente, '%'))", nativeQuery = true)
    List<VendaEntity> findByClienteNome(@Param("nomeCliente") String nomeCliente);
}
