package com.clientes.apirest.model;


import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Cliente implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long id;

    private String nome;

    private long cpf;

    private long telefone;

    private long contaCorrente;

    private long saldo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCpf() {

        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public long getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(long contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }
}
