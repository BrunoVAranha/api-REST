package com.clientes.apirest.resources;


import com.clientes.apirest.model.Cliente;
import com.clientes.apirest.repository.ClienteRepository;
import com.clientes.apirest.sconnect.sqlConnect;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;


import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api")
public class ClienteResource {

    @Autowired
    ClienteRepository clienteRepository;

    sqlConnect sql = new sqlConnect();

    public ClienteResource() throws SQLException {
    }


    @GetMapping("/clientes")
    public List<Cliente> listacliente(){
        return clienteRepository.findAll();

    }

    @GetMapping("/cliente/{id}")
    public Cliente listaclienteUnico(@PathVariable(value = "id") long id){

        return clienteRepository.findById(id);
    }

    @PostMapping("/cliente")
    public void salvaCliente(@RequestBody Cliente cliente) throws FileNotFoundException{
        if(clienteRepository.findClienteByCpf(cliente.getCpf()) == null) {
            clienteRepository.save(cliente);
            txtWrite(cliente);
        }
    }


    @DeleteMapping("/cliente")
    public void deletacliente(@RequestBody Cliente cliente){
        clienteRepository.delete(cliente);
    }

    @DeleteMapping("/clientes/{id1}/{id2}/{id3}")
    public void deletaclientes
            (@PathVariable(value = "id1")long id1,
             @PathVariable(value = "id2") long id2,
             @PathVariable(value = "id3") long id3
             ){
        clienteRepository.delete(clienteRepository.findById(id1));
        clienteRepository.delete(clienteRepository.findById(id2));
        clienteRepository.delete(clienteRepository.findById(id3));

    }

    @PutMapping ("/cliente")
    public Cliente atualizacliente(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void txtWrite(Cliente cliente) throws FileNotFoundException {

        PrintWriter writer = new PrintWriter(new FileOutputStream(new File("cliente.txt"), true));

        writer.append("[");
        writer.append("\n");
        writer.append("Nome: " + cliente.getNome());
        writer.append("\n");
        writer.append("CPF: " + String.valueOf(cliente.getCpf()));
        writer.append("\n");
        writer.append("Telefone: " + String.valueOf(cliente.getTelefone()));
        writer.append("\n");
        writer.append("Saldo: " + String.valueOf(cliente.getSaldo()));
        writer.append("\n");
        writer.append("Conta Corrente: " + String.valueOf(cliente.getContaCorrente()));
        writer.append("\n");
        writer.append("]\n\n");
        writer.close();
    }
}
