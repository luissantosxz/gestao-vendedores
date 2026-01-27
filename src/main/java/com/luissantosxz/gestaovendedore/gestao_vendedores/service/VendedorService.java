package com.luissantosxz.gestaovendedore.gestao_vendedores.service;

import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.VendedorRequestDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.VendedorResponseDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Empresa;
import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Vendedor;
import com.luissantosxz.gestaovendedore.gestao_vendedores.repository.EmpresaRepository;
import com.luissantosxz.gestaovendedore.gestao_vendedores.repository.VendedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VendedorService {
    private final VendedorRepository vendedorRepository;
    private final EmpresaRepository empresaRepository;

    public VendedorService(VendedorRepository vendedorRepository, EmpresaRepository empresaRepository) {
        this.vendedorRepository = vendedorRepository;
        this.empresaRepository = empresaRepository;
    }

    public VendedorResponseDTO cadastrar(VendedorRequestDTO dto){
        String cnpj = dto.getEmpresaCnpj().replaceAll("\\D", "");

        Empresa empresa = empresaRepository.findByCnpj(cnpj)
                .orElseThrow(() ->
                        new RuntimeException("Empresa não encontrada para o CNPJ informado")
                );

        if(!empresa.getAtivo()){
            new RuntimeException("Nao e possivel cadastrar vendedor em empresa inativa");
        }

        if (vendedorRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        if (vendedorRepository.existsByCpf(dto.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        Vendedor vendedor = Vendedor.of(dto);
        vendedor.setEmpresa(empresa);
        vendedorRepository.save(vendedor);

        return VendedorResponseDTO.of(vendedor);
    }

    public List<VendedorResponseDTO> vendedorResponseDTOList(){
        return vendedorRepository.findAll().stream().map(VendedorResponseDTO::of).toList();
    }

    public VendedorResponseDTO update(VendedorRequestDTO requestDTO, UUID id){
        var vendedor = findVendedor(id);
        vendedor.update(requestDTO);

        vendedorRepository.save(vendedor);
        return VendedorResponseDTO.of(vendedor);
    }

    public void inativar(UUID id){

    }

    public Vendedor findVendedor(UUID id){
        return vendedorRepository
                .findById(id).orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));
    }

}
