package com.example.userregistrationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "MensagemResponse", description = "Resposta padrão com mensagem")
public record MensagemResponse(
        @Schema(description = "Mensagem de resposta", example = "Usuário removido com sucesso")
        String mensagem
) {
}
