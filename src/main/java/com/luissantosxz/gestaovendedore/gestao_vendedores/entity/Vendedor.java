package com.luissantosxz.gestaovendedore.gestao_vendedores.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @NotBlank
    public String nome;

    @Email
    @NotBlank
    public String email;

    @NotBlank
    public String cpf;

    @Size(min = 5)
    public String senha;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empresa_id")
    private  Empresa empresa;

}
