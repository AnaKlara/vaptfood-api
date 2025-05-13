package com.anaclara.vaptfood.domain.service;

import com.anaclara.vaptfood.domain.exception.PermissaoNaoEncontradaException;
import com.anaclara.vaptfood.domain.model.Permissao;
import com.anaclara.vaptfood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscarOuFalhar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
    }

}
