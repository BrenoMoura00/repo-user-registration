package com.example.userregistrationservice.service.impl;

import com.example.userregistrationservice.dto.UsuarioRequest;
import com.example.userregistrationservice.model.Usuario;
import com.example.userregistrationservice.repository.EnderecoRepository;
import com.example.userregistrationservice.repository.UsuarioRepository;
import com.example.userregistrationservice.service.EnderecoService;
import com.example.userregistrationservice.service.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final EnderecoRepository enderecoRepository;
    private final EnderecoService enderecoService;

    public UsuarioServiceImpl(UsuarioRepository repository, EnderecoRepository enderecoRepository, EnderecoService enderecoService) {
        this.repository = repository;
        this.enderecoRepository = enderecoRepository;
        this.enderecoService = enderecoService;
    }

    @Override
    public void salvarUsuario(UsuarioRequest usuarioRequest) {
        var usuario = Usuario.builder()
                .nome(usuarioRequest.nome())
                .email(usuarioRequest.email())
                .build();

        var usuarioSalvo = this.repository.save(usuario);
        salvarEndereco(usuarioRequest, usuarioSalvo);
    }

    private void salvarEndereco(UsuarioRequest usuarioRequest, Usuario usuarioSalvo) {
        var enderecoEncontrado = enderecoService.getCepClient(usuarioRequest.cep());

        enderecoEncontrado.setUsuario(usuarioSalvo);
        enderecoRepository.save(enderecoEncontrado);
        repository.save(usuarioSalvo);
    }
}
