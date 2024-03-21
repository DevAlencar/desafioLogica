<h1 align="center">Leitor e decodificador de sentenças lógicas proposicionais</h1>

<p align="center">
Projeto realizado para Lógica aplicada à computação. <br/>
</p>

<p align="center">
   <a href="#-como_utilizar">Como utilizar</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-tecnologias">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-licença">Licença</a>
</p>

<p align="center">
  <img alt="License" src="https://img.shields.io/static/v1?label=license&message=MIT&color=49AA26&labelColor=000000">
</p>

<br>

---
## 💬 Como_utilizar

- O programa necessíta a ultilização correta de parênteses, informando quando há um erro;
- Deve ser utilizado os símbolos P, Q, R e S, se necessários adicionar mais é só adicionar na função "isSymbol";
- Deve ser utilizado os conectivos: '^'(com o significado 'e'), '|'(com o significado 'ou'), '>'(com o significado 'implica'), '='(com o significado 'bi-implica') e '~'(com o significado 'negado');
- Exemplo de expressões que podem ser utilizadas: (~(P|Q)=(~P^~Q)), ~~~(P|Q), ((P^Q)|(~R>S)) e entre outros...;
- Após isto, o programa deve retonar se a expressão lógica é válida, a tabela verdade daquela expressão, o tipo que ela é e a sua FND e FNC;

## 🚀 Tecnologias

Esse projeto foi desenvolvido com as seguintes tecnologias:

- Java

## 📝 Licença

Esse projeto está sob a licença MIT.

---
