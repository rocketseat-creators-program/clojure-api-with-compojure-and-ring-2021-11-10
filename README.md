
<img src="https://storage.googleapis.com/golden-wind/experts-club/capa-github.svg" />

# Web API clojure com Ring e compojure

Nesse vídeo vamos explorar a ideia de que nunca lembramos de nossas conquista quando precisamos e fazer uma Web API em clojure para gerenciar nossas conquistas. Usaremos Ring e Compojure aproveitando os conceitos de arquitetura exagonal para organizar nosso projeto.

![image](https://user-images.githubusercontent.com/11655576/137591520-1d823805-bd70-42b9-8ea9-0752f43f083a.png)

## Expert

| [<img src="https://user-images.githubusercontent.com/11655576/138782150-bee0b616-018f-4422-a700-02cd69a964e3.png" width="75px;"/>](https://github.com/gabipilegi) |
| :-: |
|[gabipilegi](https://github.com/gabipilegi)|

## Usage

### Run the application locally

`lein ring server`

### Packaging and running as standalone jar

```
lein do clean, ring uberjar
java -jar target/server.jar
```

### Packaging as war

`lein ring uberwar`

## License

Copyright ©  FIXME
