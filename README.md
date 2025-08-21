# 🚀 Desafio: Consulta Vendas

## 📋 Sobre o Projeto

Este projeto foi desenvolvido como parte do **Desafio: Consulta Vendas** do módulo JPA, consultas SQL e JPQL da **Formação Java Spring Professional** da DevSuperior.

O sistema implementa um conjunto de APIs REST para consulta de vendas e vendedores, utilizando **Spring Boot**, **JPA** e consultas **SQL/JPQL**.

## 🏗️ Arquitetura do Sistema

O sistema é baseado em duas entidades principais:

- **Sale (Venda)**: Representa uma venda realizada
- **Seller (Vendedor)**: Representa um vendedor

**Relacionamento**: Cada venda pertence a um vendedor, e um vendedor pode ter várias vendas.

## 🎯 Funcionalidades Implementadas

### 1. Relatório de Vendas
**Endpoint**: `GET /sales/report`

**Parâmetros opcionais**:
- `minDate`: Data inicial (formato: YYYY-MM-DD)
- `maxDate`: Data final (formato: YYYY-MM-DD)
- `name`: Trecho do nome do vendedor

**Retorna**: Listagem paginada contendo:
- ID da venda
- Data da venda
- Quantia vendida
- Nome do vendedor

### 2. Sumário de Vendas por Vendedor
**Endpoint**: `GET /sales/summary`

**Parâmetros opcionais**:
- `minDate`: Data inicial (formato: YYYY-MM-DD)
- `maxDate`: Data final (formato: YYYY-MM-DD)

**Retorna**: Lista contendo:
- Nome do vendedor
- Soma total das vendas no período

## 📝 Regras de Negócio

- **Data final não informada**: Considera a data atual do sistema
- **Data inicial não informada**: Considera 1 ano antes da data final
- **Nome não informado**: Considera texto vazio (busca todos os vendedores)

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **SQL**
- **H2 Database** (para desenvolvimento)
- **Maven**

## 🧪 Testando as APIs

### Collection Postman
Utilize a collection oficial do desafio: https://www.getpostman.com/collections/dea7904f994cb87c3d12

### Exemplos de Requisições

#### 1. Sumário de vendas por vendedor (período específico)
```http
GET /sales/summary?minDate=2022-01-01&maxDate=2022-06-30
```

**Resposta esperada**:
```json
[
  {
    "sellerName": "Anakin",
    "total": 110571.0
  },
  {
    "sellerName": "Logan", 
    "total": 83587.0
  },
  {
    "sellerName": "Loki Odinson",
    "total": 150597.0
  },
  {
    "sellerName": "Padme",
    "total": 135902.0
  },
  {
    "sellerName": "Thor Odinson",
    "total": 144896.0
  }
]
```

#### 2. Sumário de vendas por vendedor (últimos 12 meses)
```http
GET /sales/summary
```

#### 3. Relatório de vendas (últimos 12 meses)
```http
GET /sales/report
```

#### 4. Relatório de vendas (filtrado)
```http
GET /sales/report?minDate=2022-05-01&maxDate=2022-05-31&name=odinson
```

**Resposta esperada**:
```json
{
  "content": [
    {
      "id": 9,
      "date": "2022-05-22",
      "amount": 19476.0,
      "sellerName": "Loki Odinson"
    },
    {
      "id": 10,
      "date": "2022-05-18", 
      "amount": 20530.0,
      "sellerName": "Thor Odinson"
    },
    {
      "id": 12,
      "date": "2022-05-06",
      "amount": 21753.0,
      "sellerName": "Loki Odinson"
    }
  ]
}
```


## 🎓 Aprendizados

Este projeto demonstra conhecimentos em:

- **Spring Boot**: Configuração e desenvolvimento de APIs REST
- **JPA/Hibernate**: Mapeamento objeto-relacional e consultas
- **JPQL**: Consultas personalizadas
- **DTOs**: Transferência de dados entre camadas
- **Paginação**: Implementação de consultas paginadas
- **Tratamento de datas**: Manipulação de LocalDate e Instant

## 👨‍💻 Autor

**João Froes**
- GitHub: [@jfroes](https://github.com/jfroes)

## 📚 Referências

- [DevSuperior - Formação Desenvolvedor Java Spring](https://devsuperior.com.br/colecao-java-spring)
- [Repositório Original do Desafio](https://github.com/devsuperior/desafio-consulta-vendas)