## Lumen Language :bulb: :bulb:

## Instalação

## 1 Executando o projeto sem utilizar docker

### Requisitos
- [Gradle 4.4.1 +](https://gradle.org/install/)
- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

### Linux
Na raiz do projeto, execute no terminal:

```shell
./gradlew runApplication
```

#### 1.1 Verifique a compilação:

Se a tiver compilado sem problemas,o arquivo GeneratedJavaClass.java será gerado automaticamente com base no input.lumen.
Compile o arquivo Java gerado:

```shell
javac GeneratedJavaClass.java
```

##### 1.2 Execute a classe compilada:

```shell
java GeneratedJavaClass
```

### Windows
- Utilize como apoio a seguinte [documentação](https://nus-cs2103-ay1718s2.github.io/addressbook-level4/UsingGradle.html#:~:text=To%20run%20a%20Gradle%20command,%E2%80%8B%20e.g.%20gradlew%20clean%20allTests) para interagir com o gradle.

##### Observações Importantes:
- Certifique-se de que o Gradle está instalado e corretamente configurado no seu ambiente de desenvolvimento.
- Verifique se o JDK está instalado e se as variáveis de ambiente (JAVA_HOME e PATH) estão configuradas adequadamente para utilizar os comandos javac e java.
- Caso ocorra algum erro durante a compilação ou execução, revise as mensagens de erro no terminal para identificar e corrigir possíveis problemas no código ou na configuração do projeto.

## Executando o projeto utilizando o docker

### Requisitos
- Docker 

### Linux
Na raiz do projeto, execute no terminal:

```shell
sudo docker run --rm -u gradle -v "$PWD":/home/gradle/project -w /home/gradle/project gradle gradle runApplication
```

#### Verifique a compilação:

Se tiver compilado sem problemas,o arquivo GeneratedJavaClass.java será gerado automaticamente com base no input.lumen.
Compile o arquivo Java gerado:

```shell
sudo docker run --rm -v "$PWD":/usr/src/myapp -w /usr/src/myapp openjdk:17 javac GeneratedJavaClass.java
```

##### Execute a classe compilada:

```shell
sudo docker run --rm -it -v "$PWD":/usr/src/myapp -w /usr/src/myapp openjdk:17 java GeneratedJavaClass.java
```
## 2 Varíaveis

* [2.1 Tipos](#21-tipos)
* [2.2 Declarando variaveis](#22-declarando-variaveis)

## 2.1 Tipos:
- inteiro : Número inteiro(ex: 1, 2)
- decimal : Número decimal(ex: 3.5, 4)


## 2.2 Declarando variaveis:
**Exemplo:**

```java
inicio

inteiro a;
decimal b;

a = 2;
b = 3.5;

fim;
```

## 3 Principais comandos
* [3.1 escreva(a)](#31-escrevaa)
* [3.2 leia(a)](#32-leiaa)
* [3.3 enquanto(condição...)](#33-enquantocondição)
* [3.4 faça...enquanto(condição)](#34-façaenquantocondição)
* [3.5 se... se nao](#35-se-se-nao)

### 3.1 escreva(a)

**Descrição:**

Loga o valor no console.

**Argumentos:**

* `a`: numero

**Retorno:**

O valor de `a`.

**Exemplo:**

```java
inicio

inteiro a;
a = 2;

escreva(a);

fim;
```
Saida no console:
```
2
```

### 3.2 leia(a)

**Descrição:**

Recebe o input do usuario.

**Argumentos:**

* `a`: numero

**Exemplo:**

```java
inicio

inteiro a;

leia(a);

escreva(a);

fim;
```
Saida no console se o usuario digitar 10:
```
10
```


### 3.3 enquanto(condição)

**Descrição:**

Semelhante ao bloco while da linguagem JAVA.

**Exemplo:**

```java
inicio

inteiro a,b;

a=1;
b = 4;

enquanto(a<b){
  escreva(a);
 a = a+1;
};

fim;
```
Saida no console:
```
1
2
3
```

### 3.4 faça...enquanto(condição)

**Descrição:**

Semelhante ao bloco do...while(); da linguagem JAVA.

**Exemplo:**

```java
inicio

inteiro a,b;

a=1;
b = 4;

faça{
escreva(a);
a = a+ 1;
} enquanto(a<b);

fim;
```
Saida no console:
```
1
2
3
```

### 3.5 se... se nao
**Descrição:**

Dada uma determinada condição, realiza operações.

**Exemplo:**

```java
inicio

inteiro a,b,c;

a=1;
b = 4;
c= 2;

se(a<b){
 escreva(a);
}

se(b<a){
 escreva(b);
} senao{
 escreva(c);
}

fim;
```
Saida no console:
```
1
2
```


## 4 Particularidades da Lumen Language
### 4.1 Estrutura do Arquivo:
- Todo arquivo com a extensão .lumen deve conter o código principal do programa dentro dos blocos inicio e fim.

### 4.2 Declaração de Variáveis:
- Todas as variáveis devem ser declaradas antes de qualquer outra instrução no código. Atualmente, não é possível declarar variáveis no meio do código.


## 5 Licença

Lumen Language é um software livre, licenciado sob a MIT License. Veja o arquivo LICENSE para mais detalhes.