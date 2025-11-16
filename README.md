
## PUCPR – Performance em Sistemas Ciberfísicos  
**Professor:** Andrey Cabral Meira  
**Integrantes:** joao Victor Carvalho de Freitas, Matheus Henrique Heinzen , Vinicius Lima Teider, Edmund Soares De Sousa  
**Ano:** 2025
---

## Vídeo de Apresentação
Link para o vídeo:  
*https://youtu.be/ROVemfVQZPk*

## Descrição Geral do Trabalho

Este repositório reúne três implementações completas em Java que simulam problemas clássicos da programação concorrente, acompanhados de suas respectivas soluções e relatórios explicativos.

O objetivo do trabalho é demonstrar, analisar e resolver três fenômenos fundamentais:

- Impasse (deadlock) no problema do Jantar dos Filósofos  
- Condição de corrida (race condition) em variável compartilhada  
- Deadlock entre duas threads competindo por dois locks  

Cada parte inclui o código-fonte, logs de execução, análise baseada nas condições de Coffman, pseudocódigo ou fluxogramas, e relatórios em formato

---

## Parte 1 — Jantar dos Filósofos

### Conteúdo
- Implementação do protocolo ingênuo, que leva ao impasse.  
- Simulação dos estados dos filósofos: pensando, com fome e comendo.  
- Demonstração do ciclo de dependências que causa a espera circular.  
- Solução utilizando ordenação global de recursos (hierarquia de garfos).  
- Discussão sobre qual condição de Coffman é removida para evitar o impasse.

### Arquivos
- [`FilosofosComImpasse.java`](src/main/java/br/com/jv/filosofos/FilosofosComImpasse.java)  
- [`FilosofosEmOrdem.java`](src/main/java/br/com/jv/filosofos/FilosofosEmOrdem.java)
- [`Relatorio_Filosofos.md`](relatorios/Relatorio_Filosofos.md)

### Resultado
A hierarquia de recursos elimina a condição de espera circular, impedindo o deadlock e assegurando progresso para todos os filósofos.

---

## Parte 2 — Condição de Corrida e Semáforo Binário

### Conteúdo
- Criação de um contador compartilhado acessado por diversas threads.  
- Demonstração de inconsistências no valor final sem sincronização.  
- Uso de `Semaphore(1, true)` para garantir exclusão mútua e fairness.  
- Explicação da relação happens-before para assegurar visibilidade de memória.  
- Comparação entre as execuções com e sem controle.

### Arquivos
- [`CorridaSemSemaforo.java` ](src/main/java/br/com/jv/corrida/CorridaComSemaforo.java)
- [`CorridaComSemaforo.java`](src/main/java/br/com/jv/corrida/CorridaComSemaforo.java)
- [`Relatorio_CondicaoCorrida.md`](relatorios/Relatorio_CondicaoCorrida.md)

### Resultado
O semáforo impede que duas threads modifiquem o contador simultaneamente, eliminando a condição de corrida e garantindo um resultado consistente.

---

## Parte 3 — Deadlock com Dois Locks

### Conteúdo
- Implementação de deadlock entre duas threads competindo por dois recursos.  
- Logs demonstrando o bloqueio circular entre T1 e T2.  
- Análise detalhada com base nas quatro condições de Coffman.  
- Solução baseada na mesma estratégia da Parte 1: ordenação fixa dos locks.

### Arquivos
- [`DeadlockDemo.java`](src/main/java/br/com/jv/deadlock/DeadlockDemo.java) 
- [`DeadlockCorrigido.java`](src/main/java/br/com/jv/deadlock/DeadLockCorrigida.java)
- [`Relatorio_Deadlock.md`](relatorios/Relatorio_Deadlock.md)

### Resultado
A adoção de uma ordem fixa de aquisição de locks elimina a espera circular e impede o deadlock, garantindo o correto funcionamento do sistema.

---