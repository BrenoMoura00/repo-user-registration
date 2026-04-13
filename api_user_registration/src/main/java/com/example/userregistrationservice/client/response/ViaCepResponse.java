package com.example.userregistrationservice.client.response;

public record ViaCepResponse(
        String cep,
        String logradouro,
        String localidade,
        String uf,
        String estado
) {
}
