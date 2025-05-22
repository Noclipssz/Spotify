# 🎧 Projeto RadioSpotify - 3ª Entrega da API

## ✅ Entrega referente ao dia 20/05/2025

Repositório da equipe com as **classes de mapeamento do Banco de Dados**, conforme solicitado para a 3ª entrega do projeto de API.

---

## 📁 Estrutura do Projeto

O projeto está organizado no padrão MVC com os seguintes pacotes principais:

- `controller`: contém os controladores da API (`RadioController`, `SearchController`)
- `model`: contém as entidades JPA mapeadas com o banco de dados:
  - `Musica`
  - `Playlist`
  - `Radio`
- `repository`: interfaces que estendem `JpaRepository` para acesso aos dados

---

## 🗃️ Entidades mapeadas (pacote `model`)

As seguintes classes estão anotadas com JPA e representam as tabelas do banco de dados:

### 🎵 Musica
- Campos como `id`, `titulo`, `artista`, etc.
- Relacionamento com `Playlist`

### 📂 Playlist
- Campos como `id`, `nome`, `descricao`, `capaUrl`
- Relacionamentos com `Radio` e com lista de `Musica`

### 📻 Radio
- Campos como `id`, `nome`, `descricao`, etc.
- Relacionamento com listas de `Playlist`

---

## 🚫 Observação

O pacote `DTOs` ainda **não foi incluído** nesta entrega, pois o foco são as **entidades de mapeamento** solicitadas.

---
---

Qualquer dúvida, estamos à disposição!

