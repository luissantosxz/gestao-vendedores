package com.luissantosxz.gestaovendedore.gestao_vendedores.repository;

import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VendedorRepository extends JpaRepository<Vendedor, UUID> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}
