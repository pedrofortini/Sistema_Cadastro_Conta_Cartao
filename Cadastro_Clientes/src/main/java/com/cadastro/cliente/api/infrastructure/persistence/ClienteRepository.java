package com.cadastro.cliente.api.infrastructure.persistence;

import com.cadastro.cliente.api.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
