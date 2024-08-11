package com.clothing_store.Repository;

import com.clothing_store.Entity.ProdutoEntity;
import com.clothing_store.Entity.VendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    @Query(value = "SELECT * FROM tb_produto ORDER BY preco DESC LIMIT 10", nativeQuery = true)
    List<ProdutoEntity> find10ProdutosMaisCaros();

}
