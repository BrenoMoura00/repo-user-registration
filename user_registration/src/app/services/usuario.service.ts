import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../models/usuario.model';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  private readonly apiUrl = 'http://localhost:8080/usuarios';

  constructor(private http: HttpClient) {}

  cadastrar(usuario: Usuario): Observable<void> {
    return this.http.post<void>(this.apiUrl, usuario);
  }

  listar(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(this.apiUrl);
  }

  /**
   * Usa responseType 'text' para o DELETE sempre concluir o observable mesmo quando
   * o Spring devolve JSON ({ "mensagem": "..." }), evitando resposta pendente com delete<void>.
   */
  excluir(id: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/${id}`, { responseType: 'text' });
  }
}
