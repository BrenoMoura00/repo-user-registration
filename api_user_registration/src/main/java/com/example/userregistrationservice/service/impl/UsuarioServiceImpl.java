package com.example.userregistrationservice.service.impl;

import com.example.userregistrationservice.dto.EnderecoResponse;
import com.example.userregistrationservice.dto.UsuarioRequest;
import com.example.userregistrationservice.dto.UsuarioResponse;
import com.example.userregistrationservice.model.Endereco;
import com.example.userregistrationservice.model.Usuario;
import com.example.userregistrationservice.repository.EnderecoRepository;
import com.example.userregistrationservice.repository.UsuarioRepository;
import com.example.userregistrationservice.service.EnderecoService;
import com.example.userregistrationservice.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

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
        this.salvarEndereco(usuarioRequest, usuarioSalvo);
    }

    @Override
    public List<UsuarioResponse> listarUsuarios() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(this::mapUsuarioToResponse)
                .toList();
    }

    private UsuarioResponse mapUsuarioToResponse(Usuario usuario) {
        var enderecos = enderecoRepository.findByUsuarioId(usuario.getId())
                .stream()
                .map(this::mapEnderecoToResponse)
                .toList();

        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail(), enderecos);
    }

    private EnderecoResponse mapEnderecoToResponse(Endereco endereco) {
        return new EnderecoResponse(
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getLocalidade(),
                endereco.getUf(),
                endereco.getEstado()
        );
    }

    private void salvarEndereco(UsuarioRequest usuarioRequest, Usuario usuarioSalvo) {
        var enderecoEncontrado = enderecoService.getCepClient(usuarioRequest.cep());

        enderecoEncontrado.setUsuario(usuarioSalvo);
        enderecoRepository.save(enderecoEncontrado);
        repository.save(usuarioSalvo);
    }

    @Override
    public void removerUsuario(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        var enderecos = enderecoRepository.findByUsuarioId(id);
        if (!enderecos.isEmpty()) {
            enderecoRepository.deleteAll(enderecos);
        }
        repository.deleteById(id);
    }
}

