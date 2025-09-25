# Prova Presencial — Java Básico (Console)
## Aplicativo TODO List

**Cenário:** Você deve implementar, em Java **básico** e **console**, uma lista de tarefas (TODO list) simples para uso local durante a sessão da prova, sem persistência em disco. O foco é domínio de estruturas básicas, controle de fluxo e interação via terminal.

---

## Objetivos de aprendizagem
- Usar classes simples para modelar dados.
- Manipular coleções em memória (ArrayList).
- Ler entradas do usuário com `Scanner` e validar entradas.
- Implementar um menu interativo com laço (`while`) e `switch`/`if`.
- Aplicar boas mensagens de feedback ao usuário.

---

## Requisitos mínimos (obrigatórios)
1. **Modelo de dados**
   - Criar a classe `Tarefa` **com campos (não usar getters/setters)**:
     - `public int id;`
     - `public String titulo;`
     - `public boolean concluida;`
   - **Construtor opcional** (pode usar apenas campos públicos para simplificar).
   - Métodos utilitários opcionais: `marcarConcluida()` e `desmarcarConcluida()`.
2. **Aplicação console**
   - Usar `ArrayList<Tarefa>` para armazenar as tarefas em memória.
   - **Menu em loop** com as operações:
     1. Adicionar tarefa
     2. Listar tarefas
     3. Marcar tarefa como concluída
     4. Desmarcar tarefa
     5. Remover tarefa
     6. Sair
   - **IDs** devem ser gerados incrementalmente (ex.: `int proximoId = 1;` e incrementar a cada nova tarefa).
   - **Listagem** deve mostrar status `[X]`/`[ ]`, `id` e `titulo`.
   - **Validações** mínimas:
     - Não aceitar título vazio ou apenas espaços.
     - Ao operar por `id`, avisar se o `id` não existir.
   - **Restrições:**
     - Console puro (`System.out/Scanner`), **sem** bibliotecas externas.
     - **Sem** persistência (não salvar em arquivo ou banco).
     - Sem uso de frameworks; **Java básico**.
3. **Interação com o usuário**
   - Exibir mensagens claras (sucesso/erro) após cada operação.
   - Proteger o programa contra entradas inválidas (ex.: `id` não numérico).

---

## Exemplo de interface (somente para visualização)
```
===== TODO LIST =====
1) Adicionar tarefa
2) Listar tarefas
3) Marcar tarefa como concluída
4) Desmarcar tarefa
5) Remover tarefa
6) Sair
Escolha uma opção: 2

[ ] 1 - Comprar pão
[X] 2 - Estudar Java
```
> Observação: O exemplo é **ilustrativo**. A implementação e a formatação podem variar, desde que atendam os requisitos.

---

## Entregáveis
- Arquivos **`.java`**:
  - `Tarefa.java`
  - `Main.java` (ou outro nome de classe com `public static void main(String[] args)`)
- O código deve **compilar** e **executar** via terminal.

---

## Critérios de avaliação (rubrica)
| Critério | Descrição | Pontos            |
|---|---|-------------------|
| Modelo de dados | Classe `Tarefa` com campos corretos. | 3,0               |
| Menu e fluxo | Laço principal, leitura com `Scanner`, tratamento de opção inválida. | 3,0               |
| Operações CRUD | Adicionar, listar, marcar/desmarcar, remover funcionando. | 3,0               |
| Validações e mensagens | Título não vazio, `id` inexistente, feedback claro ao usuário. | 2,0 (Bônus)       |
| Organização e clareza | Nomes de variáveis, mensagens e estrutura do código. | 1,0               |
| **Total** |  | **10,0** + 02,0 de Bônus |

---

## Desafios (bônus até +2,0)
- **(+)** Editar título de uma tarefa pelo `id` (validando título).
- **(+)** Listar tarefas com filtros: somente concluídas / somente pendentes.
- **(+)** Ordenar listagem (ex.: pendentes primeiro).
- **(+)** Confirmar remoção (pedir `S/N`).

> Bônus só contam se os requisitos mínimos estiverem corretos.

---

## Roteiro sugerido (passo a passo)
1. Criar `Tarefa.java` com os **campos** obrigatórios.
2. Em `Main.java`, declarar `ArrayList<Tarefa>` e um `Scanner`.
3. Implementar o **menu** dentro de um `while (true)` com `switch`/`if`.
4. Implementar **Adicionar**:
   - Ler título → validar → criar `Tarefa` com `id` incremental → adicionar na lista.
5. Implementar **Listar**:
   - Exibir todas as tarefas com formato `[X]/[ ] id - titulo`.
6. Implementar **Marcar/Desmarcar**:
   - Ler `id` → localizar na lista → alterar `concluida` → mensagem.
7. Implementar **Remover**:
   - Ler `id` → localizar → remover → mensagem.
8. Testar com casos de uso: entradas válidas e inválidas.
9. (Opcional) Implementar um ou mais **desafios**.

---

## Dicas e erros comuns
- **`Scanner` e `nextLine()`**: quando misturar `nextInt()` e `nextLine()`, consuma a quebra de linha pendente com um `nextLine()` extra.
- **Busca por `id`**: percorra a lista e compare `t.id == desejado`.
- **Mensagem após operação**: sempre informe o resultado ao usuário.
- **Formatação**: um método utilitário que monta a string de exibição ajuda a manter padrão.

---

## Como compilar e executar (terminal)
```bash
javac Tarefa.java Main.java
java Main
```

> Garanta que os arquivos `.java` estejam na **mesma pasta** (sem `package`) para simplificar a prova.

---

### Checklist rápido antes de entregar
- [ ] Compila sem erros (`javac`).
- [ ] Menu e todas as opções funcionam.
- [ ] Mensagens claras e validações aplicadas.
- [ ] Código organizado e legível.

Boa prova! :)
