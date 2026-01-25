package com.luissantosxz.gestaovendedore.gestao_vendedores.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @NotBlank
    public String razaoSocial;

    @NotBlank
    @Column(unique = true)
    public String cnpj;

    @OneToMany(mappedBy = "empresa")
    private List<Vendedor> vendedores;

    @Column(nullable = false)
    private Boolean ativo = true;

}
