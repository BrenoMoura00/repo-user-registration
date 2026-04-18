import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { catchError, EMPTY, timeout } from 'rxjs';
import { UsuarioService } from '../../services/usuario.service';
import { Usuario } from '../../models/usuario.model';

@Component({
  selector: 'app-usuario-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './usuario-form.component.html',
  styleUrl: './usuario-form.component.scss',
})
export class UsuarioFormComponent implements OnInit {
  form: FormGroup;
  mensagemSucesso = '';
  mensagemErro = '';
  carregando = false;
  usuarios: Usuario[] = [];
  usuarioParaExcluir: Usuario | null = null;

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService
  ) {
    this.form = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      cep: ['', [Validators.required, Validators.pattern(/^\d{8}$/)]],
    });
  }

  ngOnInit(): void {
    this.carregarUsuarios();
  }

  carregarUsuarios(): void {
    this.usuarioService.listar().subscribe({
      next: (usuarios) => {
        this.usuarios = usuarios;
      },
      error: () => {
        this.mensagemErro = 'Erro ao carregar a lista de usuários.';
      }
    });
  }

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.carregando = true;
    this.mensagemSucesso = '';
    this.mensagemErro = '';

    const usuario: Usuario = this.form.value;

    this.usuarioService.cadastrar(usuario).subscribe({
      next: () => {
        this.mensagemSucesso = 'Usuário cadastrado com sucesso!';
        this.form.reset();
        this.carregarUsuarios();
        this.carregando = false;
      },
      error: (err) => {
        this.mensagemErro = err.error?.message || 'Erro ao cadastrar usuário.';
        this.carregando = false;
      },
    });
  }

  abrirConfirmacaoExclusao(usuario: Usuario): void {
    if (usuario.id == null) {
      return;
    }
    this.mensagemSucesso = '';
    this.mensagemErro = '';
    this.usuarioParaExcluir = usuario;
  }

  fecharConfirmacaoExclusao(): void {
    this.usuarioParaExcluir = null;
  }

  confirmarExclusao(): void {
    const id = this.usuarioParaExcluir?.id;
    if (id == null) {
      return;
    }

    this.usuarioParaExcluir = null;
    this.mensagemErro = '';
    this.usuarios = this.usuarios.filter((u) => u.id !== id);
    this.mensagemSucesso = 'Usuário excluído com sucesso.';

    this.usuarioService
      .excluir(id)
      .pipe(
        timeout(20000),
        catchError(() => {
          this.mensagemSucesso = '';
          this.mensagemErro =
            'Não foi possível concluir a exclusão no servidor. Recarregamos a lista.';
          this.carregarUsuarios();
          return EMPTY;
        })
      )
      .subscribe({
        next: () => {
          this.carregarUsuarios();
        },
      });
  }
}
