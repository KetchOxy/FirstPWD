package com.generation.italy.domain;

// CLASSE PERSONAGGIO - contiene tutte le statistiche della scheda
public class Player {
    public String nome;
    public String specie;
    public String classe;
    public String arma;
    public int forza;
    public int destrezza;
    public int costituzione;
    public int intelligenza;
    public int saggezza;
    public int carisma;
    public int puntiFerita;
    public int classeArmatura;

    // CALCOLO MODIFICATORE - formula ufficiale D&D: (punteggio - 10) / 2
    public static int calcolaModificatore(int punteggio) {
        return (punteggio - 10) / 2;
    }
}
