package br.com.jv.filosofos;

public class JantarOrdenado {

    public static void main(String[] args) {

        System.out.println("=== Execução da versão COM ORDEM GLOBAL (sem deadlock) ===");

        for (int i = 0; i < Garfos.N; i++) {
            new Thread(new FilosofosEmOrdem(i)).start();
        }
    }
}
