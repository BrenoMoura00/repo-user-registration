# Documentação do Swagger - User Registration Service

## Integração do Swagger (OpenAPI 3.0)

O projeto foi configurado com **SpringDoc OpenAPI** para fornecer documentação automática da API usando o padrão OpenAPI 3.0 (Swagger 3.0).

## Acessando a Documentação

### Swagger UI
Após iniciar a aplicação, acesse a interface gráfica do Swagger em:
```
http://localhost:8080/swagger-ui.html
```

### Documentação JSON (OpenAPI)
A especificação OpenAPI em formato JSON está disponível em:
```
http://localhost:8080/api-docs
```

### Documentação YAML
A especificação OpenAPI em formato YAML está disponível em:
```
http://localhost:8080/api-docs.yaml
```

## Funcionalidades

- **API Documentation**: Documentação automática e interativa de todos os endpoints
- **Try It Out**: Teste os endpoints diretamente pela interface do Swagger
- **Schema Documentation**: Documentação automática dos modelos de requisição e resposta
- **OpenAPI Specification**: Seguindo o padrão OpenAPI 3.0

## Dependências Adicionadas

- **SpringDoc OpenAPI**: `org.springdoc:springdoc-openapi-starter-webmvc-ui:2.4.0`
- **Lombok**: `org.projectlombok:lombok:1.18.40` (para reduzir boilerplate code)

## Configurações no application.properties

```properties
# Swagger/OpenAPI
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.enabled=true
springdoc.swagger-ui.operations-sorter=method
springdoc.swagger-ui.tags-sorter=alpha
```

## Anotações Swagger Utilizadas

### Classe Controller
- `@Tag(name="...", description="...")`: Define as tags para agrupar operações

### Métodos
- `@Operation(summary="...", description="...")`: Descrição da operação
- `@ApiResponses`: Define as possíveis respostas (201, 400, 500, etc.)
- `@ApiResponse`: Detalha cada resposta com código, descrição e conteúdo

## Exemplo de Uso

O endpoint `POST /usuarios` está documentado no Swagger com:

```bash
curl -X POST http://localhost:8080/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "email": "joao@example.com",
    "cep": "01310100"
  }'
```

Você pode testar este endpoint direto pela interface do Swagger!

## Próximos Passos

Para adicionar mais documentação aos seus endpoints:

1. Use `@Operation` para descrever o que cada método faz
2. Use `@ApiResponse` para documentar os códigos HTTP possíveis
3. Use `@Parameter` para documentar parâmetros
4. Use `@Schema` nas DTOs para documentar os campos

## Console H2

O console H2 continua acessível em:
```
http://localhost:8080/h2-console
```

Credenciais:
- URL: `jdbc:h2:mem:teste`
- Usuário: `sa`
- Senha: (deixe em branco)

