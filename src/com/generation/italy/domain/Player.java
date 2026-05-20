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
    public int puntiFeritaMax; // <-- NUOVA: Fissa il tetto massimo di salute

    // VARIABILI PER L'ECONOMIA DI GIOCO
    public int oro = 0;
    public int numeroPozioni = 2; // Parte con 2 pozioni in omaggio

    // Metodo sicuro per curare il giocatore senza superare il massimo
    public void riceviCura(int ammontare) {
        if (this.puntiFerita >= this.puntiFeritaMax) {
            System.out.println("Sei gia' al massimo delle tue forze! La cura non ha effetto.");
            return;
        }

        this.puntiFerita += ammontare;

        // Se la cura supera il massimale, ci fermiamo al tetto massimo
        if (this.puntiFerita > this.puntiFeritaMax) {
            this.puntiFerita = this.puntiFeritaMax;
        }
    }

    // CALCOLO MODIFICATORE - formula ufficiale D&D: (punteggio - 10) / 2
    public static int calcolaModificatore(int punteggio) {
        return (punteggio - 10) / 2;
    }
}