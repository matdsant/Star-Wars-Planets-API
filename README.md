<h1 align="center">
  ✨ StarWars Planet API 🚀
</h1>

<p align="center">
  <a href="#-technologies">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-project">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-configuration">Configuração</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-build & run">Construir e Executar</a>
</p>

<p align="center">
  <img alt="Licence" src="https://img.shields.io/static/v1?label=Licence&message=MIT&color=8257E5&labelColor=000000">
  <img src="https://img.shields.io/static/v1?label=Curso na Udemy&message=Testes automatizados com Spring Boot&color=8257E5&labelColor=000000" alt="Testes automatizados na prática com Spring Boot" />
</p>

## 💻 Projeto

SW-PLANET-API é um serviço web que provê dados sobre a franquia de Star Wars, mais especificamente sobre os planetas que 
aparecem nos filmes. Esse projeto foi elaborado durante o curso [Testes automatizados na prática com Spring Boot](https://www.udemy.com/course/testes-automatizados-na-pratica-com-spring-boot/?referralCode=7F6C5AA14AE558497FE0), 
em que o foco foi a criação de testes automatizados.

## 📜 Diagrama
![img.png](img.png)

## 🧪 Tipos de Testes

### 🧟 Testes Mutantes
**Pergunta:** O que são so testes mutantes?

**Resposta:** A ideia é criar mutações no código, para avaliar e verificar se o código quebra, se o código for alterado 
e o teste não quebrar, então ele está errado, pois o teste deve sim quebrar.

Para avaliar isso, podemos usar o teste mutante, com ele podemos realizar uma execução e verificar se o teste detecta 
essa mutação, ou seja se quando eu mudo o código, se o teste quebra e se ele realmente está “testando” algo.

É um teste mais lento e não deve rodar junto ao teste de integração, pois o teste mutante usa a dependência do Pitest, 
que deve ser configurado no pom.xml especificando versão, apontando os parâmetros e classes.

## 🛠️ Configuração

O projeto requer um banco de dados MySql, então é necessário criar uma base de dados com os seguintes comandos:

```
$ sudo mysql

CREATE USER 'user'@'%' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON *.* TO 'user'@'%' WITH GRANT OPTION;

exit

$ mysql -u user -p

CREATE DATABASE starwars;

exit
```

Durante os testes, as tabelas de banco já serão criadas automaticamente no banco de dados.

## 🚀 Construir e Executar

1 - Para construir e testar, execute o comando:
```sh
$ ./mvnw clean verify
```

2 - Para executar o Teste Mutante, foi necessário executar o comando abaixo:
```
C:\Star-Wars-Planets-API\Testes\sw-planet-api> ./mvnw test-compile org.pitest:pitest-maven:mutationCoverage
```

3 - Para executar o Teste em Contâiner (Test Container), é necessário realizar a instalação do Docker na sua máquina local.

## ✨ Technologias

- [Mysql](https://dev.mysql.com/downloads/mysql/)
- [Java](https://www.oracle.com/java/technologies/downloads/)
- [Maven](https://maven.apache.org/download.cgi)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Testing](https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html#testing-introduction)
- [JUnit 5](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito](https://site.mockito.org)
- [AssertJ](https://github.com/assertj/assertj)
- [Hamcrest](http://hamcrest.org/JavaHamcrest/)
- [Jacoco](https://github.com/jacoco/jacoco)
- [Pitest](https://pitest.org)
