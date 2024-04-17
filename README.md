# Gerenciador de Tarefas Java Web <img src="https://skillicons.dev/icons?i=java&theme=light" alt="JAVA" width="35" />

> Este é um projeto de aplicação Java web para gerenciamento de tarefas, desenvolvido com Java Server Faces (JSF). O objetivo desta aplicação é fornecer uma interface para criar, listar, editar, excluir e concluir tarefas.

## Funcionalidades

A aplicação oferece as seguintes funcionalidades:

- **Criar uma tarefa (cadastrar uma tarefa):**
  - Título
  - Descrição
  - Responsável
  - Prioridade (baixa, média e alta)
  - Deadline (data)

- **Listagem de tarefas por:**
  - Número
  - Título/Descrição
  - Responsável
  - Situação (Em andamento/Concluído)

## Tecnologias Utilizadas

- Java 17
- Java Server Faces (JSF)
- PrimeFaces
- Weld Servlet
- PostgreSQL
- JPA com Hibernate
- [Apache Tomcat v9.0](https://tomcat.apache.org/download-90.cgi)

## Instruções para Execução Local

### Pré-requisitos

- Eclipse IDE (ou qualquer outra IDE de sua preferência) com suporte a Java e JSF.
- Servidor de aplicação Java (por exemplo, Apache Tomcat).
- Banco de dados PostgreSQL instalado e configurado localmente.

### Passos

1. Clone este repositório em sua máquina local.
2. Abra o Eclipse e importe o projeto selecionando a opção: _“File >> Open Projects from File System...”_.
3. Configure o servidor de aplicação Java (por exemplo, Apache Tomcat) no Eclipse.
4. Aguarde a importação e a configuração do projeto serem concluídas.
5. Abra o arquivo `src/main/resources/META-INF/persistence.xml` e atualize as configurações de conexão com o banco de dados PostgreSQL de acordo com o seu ambiente.
6. Inicie o servidor de aplicação no Eclipse.
7. O sistema será carregado e criará automaticamente as tabelas necessárias no banco de dados configurado.
8. Após configurar tudo corretamente, você poderá começar a utilizar as funcionalidades de gerenciamento de tarefas.
   - Utilize `http://localhost:8080/gerenciador-de-tarefas-jsf/CadastrarTarefa.xhtml` para a aba de cadastro de tarefas.
   - Utilize `http://localhost:8080/gerenciador-de-tarefas-jsf/ListagemTarefas.xhtml` para a aba de listagem de tarefas.

### Observações

Certifique-se de que você tenha configurado corretamente todas as dependências, bibliotecas e drivers necessários para a conexão com o banco de dados PostgreSQL. Além disso, verifique se o banco de dados PostgreSQL está em execução e acessível.

Agora você está pronto para executar o projeto de gerenciamento de tarefas com banco de dados PostgreSQL localmente!
