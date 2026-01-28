package com.luissantosxz.gestaovendedore.gestao_vendedores.entity;

import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.VendedorRequestDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.enums.ESituacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Entity
@Builder
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

    @Column(name = "situacao")
    @Enumerated(EnumType.STRING)
    private ESituacao situacao;

    public static Vendedor of(VendedorRequestDTO request) {
        return Vendedor.builder()
                .nome(request.getNome())
                .cpf(request.getCpf())
                .email(request.getEmail())
                .senha(request.getSenha())
                .situacao(ESituacao.ATIVO)
                .build();
    }

    public void update(VendedorRequestDTO request) {
        this.nome = request.getNome();
        this.cpf = request.getCpf();
        this.email = request.getEmail();
        this.senha = request.getSenha();
    }
}
