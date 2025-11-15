
## PUCPR – Performance em Sistemas Ciberfísicos  
**Professor:** Andrey Cabral Meira  
**Integrantes:** joao Victor Carvalho de Freitas, Matheus Henrique Heinzen , Vinicius Lima Teider, Edmund Soares De Sousa  
**Ano:** 2025
---
## Condição de Corrida e Correção com Semáforo Binário

##  Dinâmica do problema

Nesta atividade, demonstramos uma **condição de corrida (race condition)** ao utilizar diversas threads que incrementam um contador compartilhado sem qualquer mecanismo de sincronização.  
A operação `count++`, embora simples, **não é atômica**: ela envolve leitura, soma e escrita. Quando múltiplas threads executam essa operação ao mesmo tempo, valores são perdidos.

A atividade também demonstra como corrigir o problema usando um **semáforo binário**, garantindo exclusão mútua e justiça (fairness) entre as threads.

---

## Por que ocorre condição de corrida?

Quando várias threads executam:

```
count++
```

simultaneamente, surgem três operações separadas:

1. leitura do valor atual
2. soma em registrador local
3. gravação na memória

Duas ou mais threads podem ler o mesmo valor antes dele ser atualizado, causando **perda de incrementos**.

### Efeitos observados:

- o valor final sempre é **menor que o esperado**
- execuções diferentes produzem resultados diferentes
- comportamento não determinístico

---

##  Lógica do experimento

- Criamos um contador estático `count`
- Criamos **T threads**, cada uma executando **M incrementos**
- Valor esperado: `T × M`

### Sem sincronização

Obtém-se um valor **menor** devido à condição de corrida.

---

##  Pseudocódigo – Versão com condição de corrida

```
count = 0

Criar T threads

Para cada thread:
    repetir M vezes:
        count = count + 1   // operação não atômica

Aguardar threads finalizarem

Exibir valor final e tempo
```

---

##  Resultados esperados – Sem controle

```
Esperado: 2.000.000
Obtido: 456.761 (varia bastante)
Motivo: race condition
```

---

##  Solução proposta: Semáforo Binário

Um **semáforo binário** (`Semaphore(1)`) garante que apenas **uma thread por vez** execute a seção crítica.  
Ao habilitar fairness (`Semaphore(1, true)`), garantimos acesso justo às threads em ordem FIFO.

### Benefícios:

- elimina a condição de corrida
- garante exclusão mútua
- garante visibilidade de memória via relação **happens-before**
- evita inanição (starvation)

---

##  Pseudocódigo – Versão corrigida com semáforo

```
count = 0
sem = novo_semaforo(1, justo = true)

Criar T threads

Para cada thread:
    repetir M vezes:
        sem.acquire()
        count = count + 1
        sem.release()

Aguardar threads finalizarem
Exibir valor final e tempo
```

---

##  Resultados esperados – Com semáforo

```
Esperado: 2.000.000
Obtido: 2.000.000 (sempre correto)
Tempo: maior devido à serialização
```

---

##  Comparação final

| Aspecto        | Sem Controle        | Com Semáforo           |
| -------------- | ------------------- | ---------------------- |
| Correção       |  incorreto        |  correto              |
| Velocidade     |  mais rápido      |  mais lento          |
| Consistência   |  não confiável    |  totalmente confiável |
| Fairness       |  nenhuma garantia |  FIFO                 |
| Race Condition |  presente          |  eliminada           |

---

##  Conclusão

A atividade demonstra que:

- Operações aparentemente simples podem falhar em ambientes concorrentes.
- A falta de sincronização leva a condições de corrida reais.
- Um semáforo binário é suficiente para garantir exclusão mútua e correção.
- Há um custo de desempenho devido à serialização das threads.

A solução com semáforo garante segurança, previsibilidade e consistência, eliminando totalmente o problema observado na versão sem controle.

---

