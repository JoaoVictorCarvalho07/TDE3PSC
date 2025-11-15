package br.com.jv.deadlock;

import br.com.jv.deadlock.Locks;

import java.util.concurrent.*;
public class DeadlockDemo {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println("T1: tentando LOCK_A");
            synchronized (Locks.LOCK_A) {
                System.out.println("T1: pegou LOCK_A");

                dormir(100);

                System.out.println("T1: tentando LOCK_B");
                synchronized (Locks.LOCK_B) {
                    System.out.println("T1: pegou LOCK_B");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("T2: tentando LOCK_B");
            synchronized (Locks.LOCK_B) {
                System.out.println("T2: pegou LOCK_B");

                dormir(100);

                System.out.println("T2: tentando LOCK_A");
                synchronized (Locks.LOCK_A) {
                    System.out.println("T2: pegou LOCK_A");
                }
            }
        });

        t1.start();
        t2.start();
    }

    private static void dormir(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
