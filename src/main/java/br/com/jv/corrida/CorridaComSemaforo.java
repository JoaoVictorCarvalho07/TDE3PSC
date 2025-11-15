package br.com.jv.corrida;

import java.util.concurrent.*;

public class CorridaComSemaforo {

    static int contador = 0;
    static final Semaphore sem = new Semaphore(1, true); // justo (FIFO)

    public static void main(String[] args) throws Exception {

        int T = 8;
        int M = 25000;
        int esperado = T * M;

        ExecutorService pool = Executors.newFixedThreadPool(T);

        Runnable tarefa = () -> {
            for (int i = 0; i < M; i++) {
                try {
                    sem.acquire();  //  garante exclusão mútua
                    contador++;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    sem.release();
                }
            }
        };

        long inicio = System.nanoTime();

        for (int i = 0; i < T; i++) {
            pool.submit(tarefa);
        }

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);

        long fim = System.nanoTime();

        System.out.printf("\n=== VERSÃO COM SEMÁFORO ===\n");
        System.out.printf("Esperado: %d\n", esperado);
        System.out.printf("Obtido:   %d\n", contador);
        System.out.printf("Tempo:    %.3f segundos\n",
                (fim - inicio) / 1e9);
    }
}
