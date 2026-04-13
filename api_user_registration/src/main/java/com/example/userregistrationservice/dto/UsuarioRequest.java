package com.example.userregistrationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UsuarioRequest", description = "Dados para criar um novo usuário")
public record UsuarioRequest(
        @Schema(description = "Nome completo do usuário", example = "João Silva")
        String nome,
        @Schema(description = "Email do usuário", example = "joao@example.com")
        String email,
        @Schema(description = "CEP para buscar endereço", example = "01310100")
        String cep
) {
}
