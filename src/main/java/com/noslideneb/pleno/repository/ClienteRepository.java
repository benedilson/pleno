package com.noslideneb.pleno.repository;

import com.noslideneb.pleno.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    Cliente findByNome(String nome);
}
