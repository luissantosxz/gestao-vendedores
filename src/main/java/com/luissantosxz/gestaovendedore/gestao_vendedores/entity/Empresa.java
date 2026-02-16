package com.luissantosxz.gestaovendedore.gestao_vendedores.entity;

import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.EmpresaRequestDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.enums.ESituacao;
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
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @Column(name = "razao_social")
    @NotBlank(message = "Razão social é obrigatória")
    public String razaoSocial;

    @NotBlank(message = "CNPJ é obrigatório")
    @Column(unique = true)
    public String cnpj;

    @OneToMany(mappedBy = "empresa")
    private List<Vendedor> vendedores;

    @Column(name = "situacao")
    @Enumerated(EnumType.STRING)
    private ESituacao situacao;

    public static Empresa of(EmpresaRequestDTO dto){
        return Empresa.builder()
                .razaoSocial(dto.getRazaoSocial())
                .cnpj(dto.getCnpj().replaceAll("\\D", ""))
                .situacao(ESituacao.ATIVO)
                .build();
    }


}
