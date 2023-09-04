<h1>
   <p> 
      <img src="https://user-images.githubusercontent.com/92659173/208775299-8a384f52-7cbe-4af6-8d11-2de7d61b3a1e.svg" width="100" align="center" />
      <img src="https://user-images.githubusercontent.com/92659173/208777593-0b0906f0-2e13-420f-a435-b78f0151671e.png" width="100" align="center" />
     
      AA1 - Sistema para locação de bicicletas (Servlets)
   </p>
   <img src="https://img.shields.io/github/license/vinimrs/VinChat?color=black" align="center" />
</h1>

Projeto de implementação de uma aplicação web de um sistema para locações de bicicletas online utilizando as tecnologias **Java servlets e JSP**, com objetivo de contemplar as principais responsabilidades de uma locação de bicicleta explorando e conhecendo melhor como funciona o desenvolvimento de sistemas web.

<p align="center">

   <img src="https://github.com/vinimrs/AA1/assets/92659173/2230d929-5879-4991-b722-72c63c5e8445"  width="800"/>
   <p align="center">
      <i>Sistema de locações - Página inicial
</i>
   </p>
</p>

Tal sistema foi projetado e desenvolvido durante o curso de _Desenvolvimento de Software para a Web 1_ da _Universidade Federal de São Carlos (UFSCar)_, ministrado pelo Prof. Alan Demetrius Baria Valejo, cujo objetivos acadêmicos gerais são:

> Familiarizar o estudante com os principais conceitos do desenvolvimento de software para web; capacitar o estudante a desenvolver aplicações web pelo lado do servidor (back-end).

## :hammer: Funcionalidades

As funcinalidades deste sistema foram baseadas no [documento de requisitos](https://github.com/vinimrs/AA1/blob/master/requisitos.pdf) proposto pelo professor durante a disciplina.

- `Funcionalidade 1` `Gerenciamento de usuários`: O sistema permite que um super usuário (administrador) gerencie (CRUD) todos os usuários do sistema, tais como locaodoras e clientes.
- `Funcionalidade 2` `Locação de bicicletas`: O sistema permite que clientes aluguem bicicletas em certa data das locadoras disponíveis no sistema.
- `Funcionalidade 3` `Navegação sem login`: O sistema fornece interfaces para usuários não logados conseguirem utilizar o sistema sem um cadastro.
- `Entre outras...`.

<!--
<p align="center">
   <img src="https://user-images.githubusercontent.com/92659173/208769107-f9583f61-f9dd-45d7-bc2c-fbfdb548e14f.gif" width="300px" />
</p>
-->

## :electric_plug: Testando o projeto

**Requisitos do sistema:**

- Java 11;
- Tomcat 9;
- IDE Java.

Para testar o projeto, você deve primeiramente cloná-lo em sua máquina:

```bash
git clone https://github.com/vinimrs/AA1.git
cd AA1/
```
Será necessário criar um banco de dados local chamado `locadora` no SGBD postgresql com super usuário `postgres` e senha `postgres`.

Após isso, abra o projeto em sua IDE java de preferência (foi utilizado a IntelliJ IDEA para o desenvolvimento). E inicialize a aplicação na versão 11 do Javasubindo um servidor Tomcat.

Assim que for inicializado será aberto o seu projeto no navegador podendo utilizar o sistema.

## :zap: Tecnologias

- [Servlets]
- [JSP]

## ✅ Melhorias

Ideias de melhorias para o projeto:
- Verificar as relações quando for remover, por exemplo ao remover um Cliente ou Locadora deve-se remover as locacoes associadas.
- A internazionalizacao deveria guardar a linguagem escolhida em outras páginas (parece estar re-colocando a lingua a cada carregamento).
- Mensagens de erro mais inteligentes (erros de CPF usado ou de E-nail usado).


[Servlets]: https://en.wikipedia.org/wiki/Jakarta_Servlet
[JSP]: https://pt.wikipedia.org/wiki/JavaServer_Pages


## Observações apresentação 03/07
