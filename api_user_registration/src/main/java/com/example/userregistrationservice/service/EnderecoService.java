package com.example.userregistrationservice.service;

import com.example.userregistrationservice.model.Endereco;

public interface EnderecoService {
    Endereco getCepClient(String cep);
}
