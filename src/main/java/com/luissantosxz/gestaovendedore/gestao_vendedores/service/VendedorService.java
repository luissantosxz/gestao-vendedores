package com.luissantosxz.gestaovendedore.gestao_vendedores.service;

import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.VendedorRequestDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.VendedorResponseDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Empresa;
import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Vendedor;
import com.luissantosxz.gestaovendedore.gestao_vendedores.enums.ESituacao;
import com.luissantosxz.gestaovendedore.gestao_vendedores.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VendedorService {

    private final VendedorRepository repository;
    private final EmpresaService empresaService;
    private final PasswordEncoder passwordEncoder;

    public VendedorResponseDTO cadastrar(VendedorRequestDTO dto){

        String cnpj = dto.getEmpresaCnpj().replaceAll("\\D", "");

        Empresa empresa = empresaService.buscarPorCnpj(cnpj);

        if(empresa.getSituacao() == ESituacao.INATIVO){
            throw new RuntimeException("Nao e possivel cadastrar vendedor em empresa inativa");
        }

        if (repository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        if (repository.existsByCpf(dto.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        Vendedor vendedor = Vendedor.of(dto);
        vendedor.setSenha(passwordEncoder.encode(dto.getSenha()));
        vendedor.setEmpresa(empresa);
        repository.save(vendedor);

        return VendedorResponseDTO.of(vendedor);
    }

    public List<VendedorResponseDTO> vendedorResponseDTOList(){
        return repository.findAll().stream().map(VendedorResponseDTO::of).toList();
    }

    public VendedorResponseDTO update(VendedorRequestDTO requestDTO, UUID id){
        var vendedor = findVendedor(id);
        vendedor.update(requestDTO);
        vendedor.setSenha(passwordEncoder.encode(requestDTO.getSenha()));

        repository.save(vendedor);
        return VendedorResponseDTO.of(vendedor);
    }

    public void inativar(UUID id){
        var vendedor = findVendedor(id);
        vendedor.setSituacao(ESituacao.INATIVO);
        repository.save(vendedor);
    }

    public void ativar(UUID id){
        var vendedor = findVendedor(id);
        vendedor.setSituacao(ESituacao.ATIVO);
        repository.save(vendedor);
    }

    public Vendedor findVendedor(UUID id){
        return repository
                .findById(id).orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));
    }

}
