package com.generation.italy.domain;

public class Item {
    private double peso;
    private int valore;
    private String nome;

    public Item(double peso, int valore, String nome) {
        this.peso   = peso;
        this.valore = valore;
        this.nome   = nome;
    }

    public String getNome()    { return nome; }
    public double getPeso()    { return peso; }
    public int getValore()     { return valore; }

    // Ogni sottoclasse sa come usarsi — di default non fa nulla
    public boolean usa(Player pg) {
        com.generation.italy.utils.OutputUtils.print("Non puoi usare " + nome + " in questo modo.");
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s (%.1f kg, %d oro)", nome, peso, valore);
    }

    // Factory oggetti generici
    public static Item pepitaOro()        { return new Item(5.0,   80, "Pepita d'Oro"); }
    public static Item mappaSgualcita()   { return new Item(0.1,   25, "Mappa Sgualcita"); }
    public static Item libroIncantesimi() { return new Item(0.5,  200, "Libro degli Incantesimi"); }
    public static Item amuletoFulmine()   { return new Item(1.0,  150, "Amuleto del Fulmine"); }
    public static Item coronaArgento()    { return new Item(2.0,  250, "Corona d'Argento"); }
    public static Item uovoDrago()        { return new Item(1.0, 1000, "Uovo di Drago"); }
    public static Item picconeIncantato() { return new Item(3.0,   60, "Piccone Incantato"); }
    public static Item ramoAppuntito()    { return new Item(1.0,   12, "Ramo Appuntito"); }
}