# Ideias de Projetos

## 1. Previewer de Markdown

Uma tela dividida onde você digita Markdown de um lado e vê o HTML renderizado do outro, em tempo real.

* **Backend (CLJ):** Criar uma rota `POST /api/markdown` que recebe um texto em Markdown, usa uma biblioteca (ex: `markdown-clj`) para convertê-lo para HTML e retorna o HTML.
* **Frontend (CLJS):** Uma `textarea` que envia o conteúdo para a API do backend a cada alteração. Uma `div` ao lado renderiza o HTML recebido do servidor.

## 2. Mural de Recados Simples (Guestbook)

Usuários podem deixar um nome e uma mensagem que aparece para todos.

* **Backend (CLJ):** Usar um `atom` para guardar as mensagens em memória. Criar rotas de API para `GET /api/messages` (retorna todas as mensagens) e `POST /api/messages` (armazena uma nova mensagem).
* **Frontend (CLJS):** Um formulário para "Nome" e "Mensagem". Uma área que lista todas as mensagens buscando-as da API. A lista deve ser atualizada após o envio de uma nova mensagem.

## 3. Gerador de Citações Aleatórias (Random Quotes)

Uma aplicação que busca e exibe uma citação aleatória do servidor.

* **Backend (CLJ):** Ter uma lista de citações em memória. Criar uma rota de API (ex: `/api/random-quote`) que seleciona uma citação aleatória e a retorna como JSON.
* **Frontend (CLJS):** Um botão "Nova Citação" que faz uma chamada `fetch` para a API do backend e exibe a citação recebida na tela.

## 4. Plataforma de Blog / Mini-CMS (Content Management System)

Uma plataforma onde um administrador pode escrever e publicar artigos, e visitantes podem ler e comentar.

* **Complexidade:** Requer banco de dados, controle de acesso (admin vs. público) e processamento de texto.
* **Backend (CLJ):** APIs públicas para buscar posts e comentários. APIs de administração (protegidas por autenticação) para criar, editar e deletar posts. Lógica para gerar URLs amigáveis (slugs).
* **Frontend (CLJS):** Uma área pública para leitura e uma área administrativa protegida por login, com um editor de texto para gerenciar os posts. Integrar um editor de Markdown ou um editor "What You See Is What You Get" (WYSIWYG)
