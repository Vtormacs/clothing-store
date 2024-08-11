package com.clothing_store.Repository;

import com.clothing_store.Entity.ProdutoEntity;
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

    @Query(value = "SELECT v.* FROM tb_venda v JOIN tb_funcionario f ON v.funcionario_id = f.id WHERE LOWER(f.nome) LIKE LOWER(CONCAT('%', :nomeFuncionario, '%'))", nativeQuery = true)
    List<VendaEntity> findByFuncionarioNome(@Param("nomeFuncionario") String nomeFuncionario);

    @Query(value = "SELECT * FROM tb_venda ORDER BY total DESC LIMIT 10", nativeQuery = true)
    List<VendaEntity> find10ComTotalMaisAlto();
}
