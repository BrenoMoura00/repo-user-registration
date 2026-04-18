export interface Endereco {
  cep: string;
  logradouro: string;
  localidade: string;
  uf: string;
  estado: string;
}

export interface Usuario {
  nome: string;
  email: string;
  cep?: string;
  enderecos?: Endereco[];
}
