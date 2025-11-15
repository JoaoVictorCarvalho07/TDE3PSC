package br.com.jv.filosofos;

import java.util.concurrent.locks.ReentrantLock;


public class Garfos {

    public static final int N = 5;
    public static final ReentrantLock[] garfos = new ReentrantLock[N];

    static {
        for (int i = 0; i < N; i++) {
            garfos[i] = new ReentrantLock();
        }
    }
}
