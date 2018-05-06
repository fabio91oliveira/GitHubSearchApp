# GitHubSearch

### **A aplicação contém** ##
* Desenvolvida em Kotlin
* Padrão MVP
* Sistema de build Gradle
* Mapeamento JSON
* Material Design
* Framework para comunicação com API
* Testes Unitários
* Suporte à mudanças de orientação das telas sem perder estado
* Endless Scroll
* View e tratamento de erros genéricos
* Splash Screen

### **Bibliotecas utilizadas** ##
* Facebook Shimmer (Loading PlaceHolder)
* Kotlin Serialization (Mapeamento Response Json)
* Rx (Reactive Programming API)
* Dagger (Injeção de Dependência)
* Retrofit (Cliente HTTP/Api Comunicator)
* Glide (Image Loading)
* Mockito e Junit para Testes

### **O projeto tem as seguintes funcionalidades** ###
- __Lista de repositórios__. Exemplo de chamada na API: `https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1`
  * Paginação na tela de lista, com endless scroll / scroll infinito (incrementando o parâmetro `page`).
  * Cada repositório deve exibir Nome do repositório, Descrição do Repositório, Nome / Foto do autor, Número de Stars, Número de Forks
  * Ao tocar em um item, deve levar a lista de Pull Requests do repositório
- __Pull Requests de um repositório__. Exemplo de chamada na API: `https://api.github.com/repos/<criador>/<repositório>/pulls`
  * Cada item da lista deve exibir Nome / Foto do autor do PR, Título do PR, Data do PR e Body do PR
  * Ao tocar em um item, deve abrir no browser a página do Pull Request em questão