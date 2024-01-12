# Configuração JPA no Intellij IDEA Ultimate

Na cofiguração do novo projeto
- Selecionar Java Enterprise (Jakarta EE)
- Java 19
- Project Template -> Library

Implementações
- Hibernate

No projeto baixe as dependencias no arquivo pom.xml (``ctrl + shift + O`` para atualizar as dependências) 
- myslq-connector
- postgresql

## Criando o Data Source

No icone Database
- adicionar novo
  - Data Source from URL
  - URL é o fornecido no arquivo persistence
  - logar com usuario e senha do database

## Gerando Entities do Database Schema
- View -> Tool Windows -> Persistence
- selecionar a pasta -> By Database Schema -> 

configurar Import Database Schema (quando já tiver criado as tabelas no database)
- Escolher a data base
- selecione as tabelas
- escolher o nome Package
- entity suffix -> class
- selecionar a pasta src/main

###### referência
[IntelliJ IDEA. Working with Hibernate/JPA on YouTube](https://youtu.be/QJddHc41xrM?si=XxWFAxiuAtPf13lA)