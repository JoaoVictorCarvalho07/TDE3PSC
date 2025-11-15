package br.com.jv.deadlock;

public class DeadLockCorrigida {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> executar("T1"));
        Thread t2 = new Thread(() -> executar("T2"));

        t1.start();
        t2.start();
    }

    private static void executar(String nome) {
        System.out.println(nome + ": tentando LOCK_A");
        synchronized (Locks.LOCK_A) {
            System.out.println(nome + ": pegou LOCK_A");

            dormir(100);

            System.out.println(nome + ": tentando LOCK_B");
            synchronized (Locks.LOCK_B) {
                System.out.println(nome + ": pegou LOCK_B");
            }
        }
    }

    private static void dormir(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
