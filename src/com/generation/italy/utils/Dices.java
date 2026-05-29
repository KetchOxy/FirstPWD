package com.generation.italy.utils;

/**
 * DICES - Lancia dadi D&D di qualsiasi tipo.
 */
public class Dices {

    public static int tira(int facce) {
        return (int) (Math.random() * facce) + 1;
    }

    public static boolean dadoValido(int facce) {
        return facce == 4 || facce == 6 || facce == 8 ||
                facce == 10 || facce == 12 || facce == 20 || facce == 100;
    }
}
