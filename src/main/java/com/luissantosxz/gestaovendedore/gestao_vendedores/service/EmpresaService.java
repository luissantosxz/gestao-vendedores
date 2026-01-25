package com.luissantosxz.gestaovendedore.gestao_vendedores.service;

import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.EmpresaRequestDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.EmpresaResponseDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Empresa;
import com.luissantosxz.gestaovendedore.gestao_vendedores.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public EmpresaResponseDTO cadastrar(EmpresaRequestDTO dto) {
        String cnpj = dto.getCnpj().replaceAll("\\D", "");



        Empresa empresa = new Empresa();
        empresa.setRazaoSocial(dto.getRazaoSocial());
        empresa.setCnpj(dto.getCnpj());
        empresa.setAtivo(true);

        Empresa salva = empresaRepository.save(empresa);
        return EmpresaResponseDTO.of(salva);

    }

    public List<EmpresaResponseDTO> listar() {
        return empresaRepository.findAll().stream()
                .map(EmpresaResponseDTO::of)
                .toList();
    }

    public Empresa buscarPorCnpj(String cnpj) {
        return empresaRepository.findByCnpjAndAtivoTrue(cnpj)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada para o CNPJ informado"));
    }

    public void inativarPorCnpj(String cnpj) {
        Empresa empresa = empresaRepository.findByCnpjAndAtivoTrue(cnpj)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada ou já inativa"));

        empresa.setAtivo(false);
        empresaRepository.save(empresa);
    }
}
