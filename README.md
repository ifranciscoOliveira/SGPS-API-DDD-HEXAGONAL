# SGPS-API
Sistema de Gestão de Processos Seletivos (SGPS)  
API REST desenvolvida para demonstrar arquitetura, boas práticas e escalabilidade.

---

## 📌 Sobre o Projeto

O **SGPS — Sistema de Gestão de Processos Seletivos** é uma plataforma moderna para gerenciamento de processos seletivos, incluindo:

- Cadastro de candidatos
- Cadastro de vagas
- Inscrições com regras de negócio
- Etapas
- Notificações

O objetivo do projeto é mostrar uma evolução arquitetural real:

1. **API Monolítica Tradicional**
2. **API Abordagem Domain Driven Design (DDD)**
3. **API Abordagem Domain Driven Design (DDD) com Ports & Adapters (Hexagonal Architecture)**

Esse repositório representa a **Etapa 3**: API com abordagem Domain Driven Design (DDD) e Ports & Adapters (Hexagonal Architecture).

---

## 🧠 Arquitetura do Projeto (Etapa 3)

O projeto segue os princípios de **DDD (Domain Driven Design)** e **Clean Architecture**, 
este projeto é uma evolução do projeto SGPS-API-DDD, aplicando Ports & Adapters (Hexagonal Architecture), mantendo os conceitos de DDD.


### Estrutura

- **Presentation** → Controllers e entrada da API
- **Application** → Orquestra os casos de uso
- **Domain** → Regras de negócio
- **Infrastructure** → Implementações técnicas (JPA, banco, etc.)

## 🚀 Tecnologias (Etapa 3)

- **Java 21**
- **Spring Boot 3.5.9**
- Spring Web
- Spring Data JPA
- Bean Validation
- PostgreSQL
- Lombok
