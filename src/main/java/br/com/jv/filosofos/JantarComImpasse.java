package br.com.jv.filosofos;

public class JantarComImpasse {

    public static void main(String[] args) {

        System.out.println("aqui ele inicia a execussao do jantar onde os filosofos pegam um garfo a direita ao mesmo tempo");

        for (int i = 0; i < Garfos.N; i++) {
            new Thread(new FilosofosComImpasse(i)).start();
        }
    }
}

