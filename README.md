<h1 align="center">
  ✨ StarWars Planet API 🚀
</h1>

<p align="center">
  <a href="#-Projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-Diagrama">Diagrama</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-Tipos-de-Testes">Tipos de Testes</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-Construir-e-Executar">Construir e Executar</a>
</p>

<p align="center">
  <img alt="Licence" src="https://img.shields.io/static/v1?label=Licence&message=MIT&color=8257E5&labelColor=000000">
  <img src="https://img.shields.io/static/v1?label=Curso na Udemy&message=Testes automatizados com Spring Boot&color=8257E5&labelColor=000000" alt="Testes automatizados na prática com Spring Boot" />
</p>

## 💻 Projeto

SW-PLANET-API é um serviço web que provê dados sobre a franquia de Star Wars, mais especificamente sobre os planetas que aparecem nos filmes. Esse projeto foi elaborado durante o curso [Testes automatizados na prática com Spring Boot](https://www.udemy.com/course/testes-automatizados-na-pratica-com-spring-boot/?referralCode=7F6C5AA14AE558497FE0), em que o foco foi a criação de testes automatizados.</br>

## 📜 Diagrama
![img.png](img.png)

## 🧪 Tipos de Testes

### 🧟 Testes Mutantes
**Pergunta:** O que são os testes mutantes?

**Resposta:** A ideia é criar mutações no código, para avaliar e verificar se o código quebra. Se o código for alterado e o teste não quebrar, então ele está errado, pois o teste deve sim quebrar.

Para avaliar isso, podemos usar o teste mutante. Com ele podemos realizar uma execução e verificar se o teste detecta essa mutação, ou seja, se quando eu mudo o código, o teste quebra e se ele realmente está “testando” algo.

É um teste mais lento e não deve rodar junto ao teste de integração, pois o teste mutante usa a dependência do Pitest, que deve ser configurado no pom.xml especificando versão, apontando os parâmetros e classes.


## 🚀 Construir e Executar

Para construir e testar, execute o comando:

```sh
$ ./mvnw clean verify
```
