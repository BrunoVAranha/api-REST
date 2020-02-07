package com.clientes.apirest.resources;


import com.clientes.apirest.model.Cliente;
import com.clientes.apirest.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.*;

import java.util.List;

@RestController
@RequestMapping(value = "api")
public class ClienteResource {

    @Autowired
    ClienteRepository clienteRepository;


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
            txtWrite(cliente, "cliente.txt ");
        }
    }


    @DeleteMapping("/cliente")
    public void deletacliente(@RequestBody Cliente cliente) throws IOException {
        clienteRepository.delete(cliente);
    }

    @DeleteMapping("/clientes/deleteTodos")
    public void deleteAll() throws FileNotFoundException {
        clienteRepository.deleteAll();
        clienteRepository.deleteTodosSQL();
        PrintWriter w = new PrintWriter("cliente.txt");
        w.close();


    };

    @PutMapping ("/cliente")
    public Cliente atualizacliente(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void txtWrite(Cliente cliente, String pathName) throws FileNotFoundException {

        PrintWriter writer = new PrintWriter(new FileOutputStream(new File(pathName), true));

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
