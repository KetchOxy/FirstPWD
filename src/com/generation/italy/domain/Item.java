package com.generation.italy.domain;

/**
 * ITEM - Oggetto raccoglibile o acquistabile (dal progetto del professore, esteso).
 */
public class Item {
    private String nome;
    private String tipo;      // "ARMA", "POZIONE", "TESORO"
    private double peso;
    private int valore;       // in monete d'oro
    private String descrizione;

    public Item(String nome, String tipo, double peso, int valore, String descrizione) {
        this.nome = nome;
        this.tipo = tipo;
        this.peso = peso;
        this.valore = valore;
        this.descrizione = descrizione;
    }

    public Item(String nome, double peso, int valore) {
        this(nome, "TESORO", peso, valore, "Un oggetto di valore.");
    }

    public String getNome()        { return nome; }
    public String getTipo()        { return tipo; }
    public double getPeso()        { return peso; }
    public int getValore()         { return valore; }
    public String getDescrizione() { return descrizione; }

    @Override
    public String toString() {
        return nome + " [" + tipo + " | Valore: " + valore + " mo | Peso: " + peso + " kg]";
    }
}