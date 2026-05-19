package com.generation.italy.utils;

import com.generation.library.*;

// CLASSE DADO
public class Dices {

    // TIRA - riceve le facce come parametro e restituisce il risultato
    public static int tira(int facce) {
        return (int)(Math.random() * facce) + 1;
    }

    // CHIEDI FACCE - chiede le facce all'utente, valida e le restituisce
    public static int chiedigFacce() {
        System.out.print("Quante facce ha il dado? ");
        int facce = Console.readInt();
        while (dadoValido(facce) == false) {
            System.out.println("Dado non valido! Scegli tra: 4, 6, 8, 10, 12, 20, 100");
            System.out.print("Quante facce ha il dado? ");
            facce = Console.readInt();
        }
        return facce;
    }

    // DADO VALIDO - controlla che il dado esista in D&D
    public static boolean dadoValido(int facce) {
        return facce == 4  || facce == 6  || facce == 8 ||
                facce == 10 || facce == 12 || facce == 20 || facce == 100;
    }
}
