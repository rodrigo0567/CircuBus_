UNIVERSIDADE FEDERAL DA PARAÍBA
Centro de Informática - Departamento de Informática
Ciências da Computação - 22.2

Projeto de Programação Orientada a Objetos

Docente: Danielle Rousy Dias Ricarte

  *CircuBus*
  
Programa que rastreia posição do ônibus circular da UFPB.

Discentes:

Davi Henrique Teixeira Barros 

José Samuel Alves da Silva

Rhonnye Wendell Lourenço Soares Martins

Rodrigo Veríssimo da Silva

GitHub:
GitHub - rodrigo0567/CircuBus_

João Pessoa – PB

08 de novembro de 2023

• Sobre o aplicativo

O aplicativo tem como objetivo informar os estudantes da UFPB sobre a localização do ônibus circular CCHLA – CI. Muitas vezes não fica claro para os, 
alunos onde o ônibus está e para onde está indo, por isso, pretendemos criar 
um programa que auxilie na passagem dessas informações, com
funcionalidades como uma tela para login, o rastreio do circular e um chat para
conversa com o motorista.

• Modelagem do problema

Nosso código é formado por um conjunto de pacotes e classes, sendo um
pacote principal (CircuBus_-main), e um auxiliar (Complementos) para
armazenar elementos gráficos, como arquivos de imagem .png e .gif.
Em relação ao pacote main, estão presentes diversos elementos utilizados em
POO, lá estão as classes responsáveis pela execução do programa,
inicializando novos objetos, incluindo herança entre elas como pode ser
observado na classe TelaDeLogin que estende JFrame e com aplicação de
subclasses como a classe TratadorCliente. Além disso, é possível perceber a
utilização de polimorfismo em trechos que apresentam, por exemplo, a
operação Override e, há também, presença de aplicação da classe abstrata
controleTela que implemente a interface Relógio. Quanto ao pacote auxiliar,
nele encontram-se elementos para melhoria visual do programa, por exemplo,
arquivos de imagem e som que são utilizados durante a execução.

• Diagrama de Classes

![Captura de tela 2023-11-09 074331](https://github.com/rodrigo0567/CircuBus_/assets/125127942/e92f0a92-7334-4552-8d1c-28738300ee94)


• Ferramentas utilizadas

Foi utilizado como IDE o programa IntelliJ, além disso, foi aplicada, como
ferramenta para melhorar a experiência do usuário, a implementação de uma
interface gráfica feita a partir do Swing, tornando o aplicativo mais explícito e
atrativo.

• Resultados e considerações finais

A realização do projeto permitiu um aprofundamento na disciplina de
Programação Orientada a Objetos, com a utilização de diversos elementos
apresentados em sala de aula e de ferramentas implementadas por fora,
pudemos construir uma base muito melhor para projetos futuros. Entretanto, o
grupo encontrou grandes dificuldades em relação à aplicação de APIs e seu
acesso no código. Por fim, agradecemos pelas aulas de alta qualidade e com a
preocupação com os alunos, elogiamos, também, a didática apresentada pela
professora Danielle e sua compreensão quanto as dificuldades enfrentadas
pelos alunos.
