package br.com.jv.filosofos;

public class FilosofosEmOrdem implements Runnable {

    private final int id;

    public FilosofosEmOrdem(int id) {
        this.id = id;
    }

    private void log(String msg) {
        System.out.println("Filósofo "+id+": " +msg );
    }

    @Override
    public void run() {
        try {
                log("pensando...");
                Thread.sleep(300);

                log("com fome...");

                int esquerdo = id;
                int direito = (id + 1) % Garfos.N;

                // Definindo a ordem global
                int primeiro = Math.min(esquerdo, direito);
                int segundo = Math.max(esquerdo, direito);

                Garfos.garfos[primeiro].lock();
                log("pegou o garfo " + primeiro + " (primeiro)");

                Thread.sleep(100);

                Garfos.garfos[segundo].lock();
                log("pegou o garfo " + segundo + " (segundo)");

                log("comendo...");
                Thread.sleep(300);

                Garfos.garfos[segundo].unlock();
                Garfos.garfos[primeiro].unlock();
                log("liberou os garfos e voltou a pensar.");


        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
