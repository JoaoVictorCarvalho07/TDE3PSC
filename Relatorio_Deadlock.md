## PUCPR – Performance em Sistemas Ciberfísicos
**Professor:** Andrey Cabral Meira  
**Integrantes:** joao Victor Carvalho de Freitas, Matheus Henrique Heinzen , Vinicius Lima Teider, Edmund Soares De Sousa  
**Ano:** 2025


---
## Deadlock: Demonstração, Análise e Correção com Hierarquia de Recursos

##  Dinâmica do problema

Nesta atividade demonstramos, na prática, a ocorrência de um **deadlock** entre duas threads que competem por dois recursos (locks).  
O objetivo é:

- reproduzir um deadlock real  
- registrar logs que evidenciem o travamento  
- analisar o problema com base nas **Condições de Coffman**  
- corrigir o deadlock aplicando **hierarquia de recursos**, a mesma técnica usada no Jantar dos Filósofos  

---

## Demonstração do Deadlock

Criamos duas threads:

- **T1**: tenta adquirir LOCK_A → depois LOCK_B  
- **T2**: tenta adquirir LOCK_B → depois LOCK_A  

Com isso, ocorre:

```
T1 segura LOCK_A e espera LOCK_B
T2 segura LOCK_B e espera LOCK_A
```

Nenhuma delas libera o recurso que a outra precisa → **deadlock completo**.

---

## Logs do deadlock (exemplo)

```
T1: tentando adquirir LOCK_A
T1: adquiriu LOCK_A
T2: tentando adquirir LOCK_B
T2: adquiriu LOCK_B
T1: tentando adquirir LOCK_B
T2: tentando adquirir LOCK_A
-- trava aqui --
```

As threads ficam presas esperando indefinidamente.

---

##  Relação com as Condições de Coffman

O deadlock ocorre porque **todas as quatro condições necessárias** estão presentes:

### 1. Exclusão mútua  
Cada lock só pode ser adquirido por uma thread por vez.

### 2. Hold and wait  
Cada thread segura um lock e espera outro.

### 3. Não-preempção  
Locks não podem ser tomados à força.

### 4. Espera circular  
Forma-se o ciclo:

```
T1 → LOCK_A → esperando LOCK_B
T2 → LOCK_B → esperando LOCK_A
```

Quando as quatro condições são verdadeiras, **o deadlock é inevitável**.

---

## Pseudocódigo – Versão com Deadlock

```
LOCK_A e LOCK_B compartilhados

Thread T1:
    adquirir LOCK_A
    dormir
    adquirir LOCK_B
    liberar LOCK_B
    liberar LOCK_A

Thread T2:
    adquirir LOCK_B
    dormir
    adquirir LOCK_A
    liberar LOCK_A
    liberar LOCK_B
```

Deadlock surge quando ambas seguram um lock e esperam pelo outro.

---

## Solução: Hierarquia de Recursos

Para eliminar o deadlock, aplicamos a técnica de **ordenação global**, onde todas as threads seguem a mesma ordem ao adquirir recursos.

### Estratégia:
Sempre adquirir:

```
LOCK_A → depois LOCK_B
```

Nunca o contrário.

---

## Pseudocódigo – Versão corrigida

```
LOCK_A e LOCK_B compartilhados

Para qualquer thread:
    adquirir LOCK_A
    dormir
    adquirir LOCK_B

    executar operação crítica

    liberar LOCK_B
    liberar LOCK_A
```

Agora, **não existe espera circular**, pois todas as threads seguem a mesma ordem.

---

##  Qual condição de Coffman foi eliminada?

###  **Espera circular**

Sem um ciclo de dependências, o deadlock não pode ocorrer.  
As outras condições permanecem verdadeiras, mas como pelo menos uma foi removida, o sistema é seguro.

---

##  Conclusão

A atividade demonstra que:

- Deadlocks surgem quando quatro condições fundamentais ocorrem simultaneamente.  
- O exemplo com duas threads e dois locks reproduz um deadlock real e observável.  
- A solução mais simples e eficaz é **impor uma hierarquia de recursos**, eliminando a espera circular.  
- A mesma estratégia foi aplicada no Jantar dos Filósofos, mostrando sua universalidade.


