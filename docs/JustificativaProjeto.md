# Programação 3 - Universidade Federal de Pernambuco - Leopoldo Teixeira
### Alunos: Arthur Justino, David Francisco

### Qual é a sua ideia de aplicativo? Inclua uma breve justificativa.
Uma boa organização do estoque em qualquer tipo de negócio evita a perda de produtos, clientes e dinheiro.  Sérios problemas administrativos podem ser causados por conta da falta de controle desses locais onde se armazenam os produtos que as empresas oferecem. Visto isso, pensamos em criar este app, que tem como objetivo, por meio, da galeria do celular, gerenciar o estoque dos produtos com suas respectivas validades. 

### Quem usará seu aplicativo e por que eles o usarão?
Pequenos comerciantes ou que por motivo de deslocamento não poderiam usar um ‘computador normal’, que gostariam de se beneficiar de uma gerência eficiente de seus produtos, ou até mesmo qualquer cidadão que gostaria de ter um melhor controle sobre os produtos de casa.

### Existe um aplicativo similar? Se sim, como o seu será diferente?
Essa ideia não é nova, na verdade existem um número razoável delas no mercado, um exemplo é o app “Controle de Vendas Venda Fácil” da google play, ele possui mais features, porém pretendemos trabalhar com uma visão muito mais direta, tentando simular ao máximo a realidade desse tipo de usuário, adicionando nossa tecnologia. O app terá uma conexão com a galeria do usuário, as fotos dos produtos poderão ser persistidas para o app. Em vez do usuário se moldar ao aplicativo, vamos fazer o oposto, criar um app que já se pareça com o que eles já usam.
### Como sua aplicação será estruturada? Quais telas o usuário irá interagir, e o que elas fazem? Qual é o fluxo de trabalho?Quais componentes Android serão utilizados, além de classes, bibliotecas de terceiros, paradigmas de design, etc? Sua aplicação deve usar pelo menos 3 componentes básicos de Android.
Um BroadcastReceiver será registrado para exibir uma notificação ao usuário, sobre  quais itens estão com a validade ultrapassada (Uma vez por dia, assim que virar o dia).
Um service que ficará responsável por fazer a leitura da imagem selecionada da galeria e convertê-la para ser salva no servidor.
Seis activities: Cadastro, CadastroProduto, DetalhesProduto, ListaProduto, Login, Repeating.
	Cadastro: Tela de cadastro de usuário, usuário fornece username e password
	Cadastro Produto: Tela de registro dos produtos para o estoque, usuário fornece, nome do produto, quantidade, validade e a foto do produto, o service fica responsável por guardar esses registros no servidor.
	Detalhes Produto: Tela é chamada ao clicar em um item da lista de produtos, exibe para o usuário o nome do produto, quantidade em estoque, foto do produto e a sua validade.
	Lista Produto: Tela que exibe um listview com todos os produtos do estoque do usuário logado ordenados em ordem crescente de validade.
	Login: Tela principal da aplicação onde o usuário fornece seus dados (username e password) para entrar na aplicação e ter acesso aos seus registros do servidor.
Repeating: Tela onde é exibido todos os produtos fora da validade ordenados em ordem crescente da data de validade, pode ser chamada tanto pela notificação quanto por um botão da lista de produtos.
A aplicação utilizou a parse api para a persistência e recuperação dos dados do servidor, além da Picasso library para a exibição das fotos dos produtos na tela.
Descrição de classes principais: Na classe de listagem de produtos foi utilizada uma AsyncTask para carregamento da listview em background, além de um progressdialog para efeito visual de carregamento. Na classe de cadastro de produtos são por onde são passados os dados do produto para o início da execução do Intentservice, que é responsável por tratar os dados e salvar no servidor. Na tela de login, assim que o usuário pressionar o botão de login, é registrado o broadcastreceiver, com a hora e os minutos desejados para o recebimento da notificação dos itens fora da validade.


### Como foi dividido o trabalho?
Trabalhamos usando um sistema de sprint’s com um kanban, tendo leve semelhança com o modelo da scrum, a partir de stories criamos pequenas e unitárias tarefas onde cada um foi responsável por trabalhar em sua própria tarefa, não seria bom separar o trabalho por tipo de atividade pois não faria sentido no nosso contexto, nem separar por componente pois a carga de trabalho em cima de cada um seria desproporcional.

### Link do youtube
https://youtu.be/-PG0LN-V5Pg
