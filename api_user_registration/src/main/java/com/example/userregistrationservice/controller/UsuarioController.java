package com.example.userregistrationservice.controller;

import com.example.userregistrationservice.config.Logger;
import com.example.userregistrationservice.dto.UsuarioRequest;
import com.example.userregistrationservice.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "API de gerenciamento de usuários")
@CrossOrigin(value = "http://localhost:4200")
public class UsuarioController {

    Logger logger = Logger.getInstancia();
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @Operation(summary = "Criar novo usuário", description = "Cria um novo usuário com dados pessoais e endereço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> criarUsuario(@RequestBody UsuarioRequest usuarioRequest){
        logger.info("Criando usuário: " + usuarioRequest.nome());
        usuarioService.salvarUsuario(usuarioRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
