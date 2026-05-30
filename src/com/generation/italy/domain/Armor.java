package com.generation.italy.domain;

import com.generation.italy.utils.OutputUtils;

public class Armor extends Item {

    private boolean indossata;
    private int difesa;

    public Armor(double peso, int valore, String nome, int difesa) {
        super(peso, valore, nome);
        this.indossata = false;
        this.difesa    = difesa;
    }

    public boolean isIndossata() { return indossata; }
    public int getDifesa()       { return difesa; }
    public void indossa()        { this.indossata = true; }
    public void rimuovi()        { this.indossata = false; }

    @Override
    public boolean usa(Player pg) {
        pg.indossaArmatura(this);
        // Ricalcola CA: base 10 + modDES + difesa armatura
        pg.classeArmatura = 10 + Player.calcolaModificatore(pg.destrezza) + this.difesa;
        OutputUtils.print("CA aggiornata: " + pg.classeArmatura);
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s (%.1f kg, %d oro) DEF:%d [%s]",
                getNome(), getPeso(), getValore(), difesa,
                indossata ? "indossata" : "nello zaino");
    }

    // Factory
    public static Armor corazzaSpezzata() { return new Armor(6.0,   40, "Corazza Spezzata",  2); }
    public static Armor elmoFerraglia()   { return new Armor(3.0,   35, "Elmo di Ferraglia",  1); }
    public static Armor giubboCuoio()     { return new Armor(4.0,   55, "Giubbo di Cuoio",    3); }
    public static Armor scudoFerro()      { return new Armor(5.0,   70, "Scudo di Ferro",      4); }
    public static Armor corazzaReale()    { return new Armor(10.0, 300, "Armatura Reale",     10); }
    public static Armor scagliaDrago()    { return new Armor(15.0, 800, "Scaglia di Drago",   18); }
}