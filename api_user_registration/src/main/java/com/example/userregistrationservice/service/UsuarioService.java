package com.example.userregistrationservice.service;

import com.example.userregistrationservice.dto.UsuarioRequest;
import com.example.userregistrationservice.dto.UsuarioResponse;

import java.util.List;

public interface UsuarioService {
    void salvarUsuario(UsuarioRequest usuarioRequest);

    List<UsuarioResponse> listarUsuarios();
}
