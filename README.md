# Conversor de Moedas :currency_exchange:

Este conversor de moedas, foi criado para satisfazer à um challenger da Alura, indicado aos alunos que estão participando do programa: Alura + One G9.
O challenger foi desenvolvido para testar e praticar os conhecimentos dos alunos adquiridos durante o curso. Este em específico, testa a implementação
de APIs em Java, aplicação de classes, abstração de classes, uso de dependências, exceptions, lógica de programação e boas práticas da programação.

## Exchange Rate API :bank:

A API que está sendo usada neste código, é a Exchange Rate API. A qual é responsável por disponibilizar taxas de cotação de moedas ao redor do globo.
Pode ser acessada por meio do endereço: <https://www.exchangerate-api.com>.

## Da Codificação: :computer:

A codificação, foi criada por meio da seguinte estrutura de pastas de classes:

1. Actions
    - APIGetFromText
    - APIRequisition
    - Conversion
    - NumberConversion
2. Exception
    - APIKeyNotFoundException
    - APITextNotFoundException
    - InactiveAccountException
3. Main
    - Main
4. Models
    - ExchangeRate
    - SupportedExchange
5. Records
    - ExchangeRateAPI
    - SupportedConversionRateAPI
    - SupportedExchangesRateAPI

  ### Actions

  É a pasta onde as principais requisições para o funcionamento do código estão alocadas. Onde **APIGetFromText** recupera a chave API do text
  apiKey.txt, onde está armazenada a chave da API. **APIRequisition** realiza a requisição à API e retorna o valor Json da informação recuperada.
  **Conversion** chama as demais classes para realizar conversões entre: uma para uma, um para todas e mostrar todas as moedas disponíveis. 
  **NumberConversion** que realiza testes lógicos para transformar Strings em int ou double.

  ### Exception

  Pasta alocada que contém as exceções criadas para conter situações durante o sistema. **APIKeyNotFoundException**, caso a chave API não seja encontrada
  dentro do arquivo .txt. **APITextNotFoundException**, caso não tenha nenhum texto dentro do .txt. **InactiveAccountException**, caso a moeda pesquisa
  seja inválida ou não esteja dentro das moedas disponíveis.

  ### Main

  Onde o programa é executado e cria um retorno visual no terminal para escolha do usuário.

  ### Models

  Modelos de recepção de valores do Json da API. **ExchangeRate** que realiza a conversão de um par de moedas - também retorna consigo, as moedas e a
  taxa de conversão. **SupportedExchange**, suporta as taxas de conversões suportadas para amostragem geral ou amostragem de taxas de conversões gerais.
  Também contém um método de criação de páginas para visualização do usuário.

  ### Records

  Responsáveis por recuperar os parâmetros da API e retornar em seus respectivos objetos de Models como parte de construtores.


___

## Chave API :key:

O projeto requer uma chave API do usuário que for utilizar o código. Esta chave serve para acesso de dentro da Exchange Rate API para controle de fluxo 
de dados na página principal da API .Ela pode ser colocada dentro do arquivo txt "apiKey.txt" e será lida durante
o código para pesquisa na base do Exchange Rate API. Clique [aqui](https://github.com/Allisson0/conversor-de-moedas/blob/main/apiKey.txt) para visualizar
este .txt. Clique [aqui](https://www.exchangerate-api.com) para conseguir sua **chave API gratuita**.

<img width="1200" height="750" alt="image" src="https://github.com/user-attachments/assets/8fa276c0-3b28-49d8-94fc-2031080f1a11" />


# Tela do terminal:

#### Tela inicial

Tela inicial da aplicação com as escolhas de visualização de moedas disponíveis, taxas de conversões para uma moeda, conversão uma para uma
e sair.

<img width="474" height="249" alt="image" src="https://github.com/user-attachments/assets/a70b10ac-e7e1-4e43-8410-a1c0063aed42" />

#### Lista de moedas disponíveis

Exemplo de como fica a lógica de paginação em moedas disponíveis.

<img width="631" height="462" alt="image" src="https://github.com/user-attachments/assets/945180fd-85bc-4909-98d1-4b1798692d0b" />

#### Pag 5 ex:

Acesso à página 5.

<img width="662" height="396" alt="image" src="https://github.com/user-attachments/assets/dd427d3c-5804-4c07-8e19-3894d08fc93f" />

#### Lista de conversões de uma moeda ex: BRL

Exemplo de como fica a lógica de paginação em taxas de conversões gerais para uma moeda.

<img width="524" height="514" alt="image" src="https://github.com/user-attachments/assets/f2cc21cd-da86-4829-beac-ca930e391867" />

#### Conversão de $10 USD para BRL

Uso da aplicação para conversão de 10 doláres para a moeda "real" brasileira.

<img width="529" height="480" alt="image" src="https://github.com/user-attachments/assets/7f25102c-a018-4fa5-a112-8474a0cb8f0d" />


# Considerações finais

O uso de APIs em aplicações, é muito importante, pois com ele, podemos acessar arquivos armazenados em bancos de dados de sites
hospedados na web. O mesmo vale para o uso de aplicações Java em páginas web, para lógica mais avançada de gerenciamento de dados
e contato com o banco de dados. Entender a sua utilização, é de suma importância para pessoas que buscam se especializar em desenvolvimento
back-end ou fullstack. O challenger consegue treinar tudo isso e muito mais, uma vez que precisamos sempre buscar entender como a API
retorna suas informações e como podemos transformá-la em objetos do nosso código. Além de treinar a sintaxe do Java e outras configurações
ideias para profissionais da área, principalmente com as boas práticas de programação.

___

[Clique aqui](https://github.com/Allisson0/conversor-de-moedas/tree/main/README.md#Conversor-de-Moedas) para voltar ao topo da página.
