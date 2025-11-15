package br.com.jv.filosofos;

public class FilosofosComImpasse implements Runnable {

    private final int id;

    public FilosofosComImpasse(int id) {
        this.id = id;
    }

    private void log(String msg) {
        System.out.println("Filósofo "+id+": " +msg );
    }

    @Override
    public void run() {
        try {
            while (true) {
                log("pensando...");
                Thread.sleep(300);

                log("com fome...");

                int esquerdo = id;
                int direito = (id + 1) % Garfos.N;

                // aqui ele pega o garfo da esquerda primeiro
                Garfos.garfos[esquerdo].lock();
                log("pegou o garfo ESQUERDO " + esquerdo);

                Thread.sleep(100);

                Garfos.garfos[direito].lock(); // Aqui ocorre o deadlock quando todos fazem isso
                log("pegou o garfo DIREITO " + direito);

                log("comendo...");
                Thread.sleep(300);

                Garfos.garfos[direito].unlock();
                Garfos.garfos[esquerdo].unlock();
                log("terminou de comer e liberou os garfos.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
