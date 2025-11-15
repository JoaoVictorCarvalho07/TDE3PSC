package br.com.jv.corrida;

import java.util.concurrent.*;

public class CorridaSemControle {

    static int contador = 0;

    public static void main(String[] args) throws Exception {

        int T = 8;           // número de threads
        int M = 250_000;     // incrementos por thread
        int esperado = T * M;

        ExecutorService pool = Executors.newFixedThreadPool(T);

        Runnable tarefa = () -> {
            for (int i = 0; i < M; i++) {
                contador++;   // não é atômico  causa race condition
            }
        };

        long inicio = System.nanoTime();

        for (int i = 0; i < T; i++) {
            pool.submit(tarefa);
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);

        long fim = System.nanoTime();

        System.out.printf("\n=== VERSÃO SEM CONTROLE ===\n");
        System.out.printf("Esperado: %d\n", esperado);
        System.out.printf("Obtido:   %d\n", contador);
        System.out.printf("Tempo:    %.3f segundos\n",
                (fim - inicio) / 1e9);
    }
}
