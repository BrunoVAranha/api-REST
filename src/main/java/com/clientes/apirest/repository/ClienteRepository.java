package com.clientes.apirest.repository;

import com.clientes.apirest.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public Cliente findById(long id);

    @Query("SELECT c FROM Cliente c WHERE c.cpf = :cpf")
    Cliente findClienteByCpf(
            @Param("cpf") Long cpf);
    @Transactional
    @Modifying
    @Query("DELETE FROM Cliente")
    void deleteTodosSQL();


}
