package com.example.userregistrationservice.service.impl;

import com.example.userregistrationservice.client.ViaCepClient;
import com.example.userregistrationservice.client.response.ViaCepResponse;
import com.example.userregistrationservice.model.Endereco;
import com.example.userregistrationservice.service.EnderecoService;
import org.springframework.stereotype.Service;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private final ViaCepClient cepClient;


    public EnderecoServiceImpl(ViaCepClient cepClient) {
        this.cepClient = cepClient;
    }

    public Endereco getCepClient(String cep) {
        var enderencoEncontrado = cepClient.getAddressByCep(cep);

        if(enderencoEncontrado.isEmpty()) {
            throw new RuntimeException("Endereço não encontrado para o CEP: " + cep);
        }

        var enderecoOptional = enderencoEncontrado.get();
        return Endereco.builder()
                .cep(enderecoOptional.cep())
                .logradouro(enderecoOptional.logradouro())
                .localidade(enderecoOptional.localidade())
                .uf(enderecoOptional.uf())
                .estado(enderecoOptional.estado())
                .build();
    }
}
