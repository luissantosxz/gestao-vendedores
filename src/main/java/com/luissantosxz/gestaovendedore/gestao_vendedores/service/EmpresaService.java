package com.luissantosxz.gestaovendedore.gestao_vendedores.service;

import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.EmpresaRequestDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.dto.EmpresaResponseDTO;
import com.luissantosxz.gestaovendedore.gestao_vendedores.entity.Empresa;
import com.luissantosxz.gestaovendedore.gestao_vendedores.enums.ESituacao;
import com.luissantosxz.gestaovendedore.gestao_vendedores.exceptionhandler.BadRequest;
import com.luissantosxz.gestaovendedore.gestao_vendedores.exceptionhandler.NotFound;
import com.luissantosxz.gestaovendedore.gestao_vendedores.repository.EmpresaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public EmpresaResponseDTO cadastrar(EmpresaRequestDTO dto) {

        var empresa = Empresa.of(dto);

        if(empresaRepository.findByCnpj(dto.getCnpj()).isPresent()){
            throw new BadRequest("Empresa ja cadastrada");
        }

        Empresa salva = empresaRepository.save(empresa);
        return EmpresaResponseDTO.of(salva);

    }

    public List<EmpresaResponseDTO> listar() {
        return empresaRepository.findAll().stream()
                .map(EmpresaResponseDTO::of)
                .toList();
    }

    public Empresa buscarPorCnpj(String cnpj) {
        return empresaRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new NotFound("Empresa nao encontrada")
                );
    }

    public void inativarPorCnpj(String cnpj) {

        var empresa = buscarPorCnpj(cnpj);

        empresa.setSituacao(ESituacao.INATIVO);

        empresaRepository.save(empresa);
    }

    
    public void ativarPorCnpj(String cnpj){
        
        var empresa = buscarPorCnpj(cnpj);
        empresa.setSituacao(ESituacao.ATIVO);

        empresaRepository.save(empresa);
        
    }
}
