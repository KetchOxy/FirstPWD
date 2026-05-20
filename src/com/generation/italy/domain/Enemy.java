package com.generation.italy.domain;

// ENEMY - rappresenta un nemico con statistiche proprie
public class Enemy {
    public String nome;
    public String arma;
    public int puntiFerita;
    public int classeArmatura;
    public int forza;
    public int destrezza;
    public int costituzione;

    // CALCOLO MODIFICATORE - stessa formula di Player
    public static int calcolaModificatore(int punteggio) {
        return (punteggio - 10) / 2;
    }
}