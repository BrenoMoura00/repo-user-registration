export interface Endereco {
  cep: string;
  logradouro: string;
  localidade: string;
  uf: string;
  estado: string;
}

export interface Usuario {
  id?: number;
  nome: string;
  email: string;
  cep?: string;
  enderecos?: Endereco[];
}
