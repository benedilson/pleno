package com.noslideneb.pleno.controller;

import com.noslideneb.pleno.model.Cliente;
import com.noslideneb.pleno.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api", produces = {"application/json"})
public class ClienteController {

    private final Logger log = LoggerFactory.getLogger(ClienteController.class);
    private ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/clientes")
    Collection<Cliente> clientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/cliente/{id}")
    ResponseEntity<?> obterCliente(@PathVariable String id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/cliente")
    ResponseEntity<Cliente> salvaCliente(@RequestBody Cliente cliente) throws URISyntaxException {
        log.info("Requisição para criar cliente: {}", cliente);
        Cliente resultado = clienteRepository.save(cliente);
        return ResponseEntity.created(new URI("/api/cliente/" + resultado.getId())).body(resultado);
    }

    @PutMapping("/cliente/{id}")
    ResponseEntity<Cliente> atualizarCliente(@RequestBody Cliente cliente) {
        log.info("Requisição para atualizar cliente: {}", cliente);
        Cliente resultado = clienteRepository.save(cliente);
        return ResponseEntity.ok().body(resultado);
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable String id) {
        log.info("Requisição para Deletar cliente: {}", id);
        clienteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
