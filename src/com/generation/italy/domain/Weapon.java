package com.generation.italy.domain;

import com.generation.italy.utils.Dices;

public class Weapon extends Item {

    private boolean indossata;
    private int dado;

    public Weapon(double peso, int valore, String nome, int dado) {
        super(peso, valore, nome);
        this.indossata = false;
        this.dado      = dado;
    }

    public boolean isIndossata() { return indossata; }
    public int getDado()         { return dado; }
    public void indossa()        { this.indossata = true; }
    public void rimuovi()        { this.indossata = false; }

    public int tiraDanno() {
        return Dices.tira(dado);
    }

    @Override
    public String toString() {
        return String.format("%s (%.1f kg, %d oro) DADO:d%d [%s]",
                getNome(), getPeso(), getValore(), dado,
                indossata ? "equipaggiata" : "nello zaino");
    }

    public static Weapon pugnale()        { return new Weapon(0.5,   5,  "Pugnale",         4); }
    public static Weapon scimitarra()     { return new Weapon(1.5,  15,  "Scimitarra",       6); }
    public static Weapon spadaCorta()     { return new Weapon(1.0,  10,  "SpadaCorta",       6); }
    public static Weapon asciaGuerra()    { return new Weapon(3.0,  20,  "AsciaGuerra",      8); }
    public static Weapon flagello()       { return new Weapon(2.0,  15,  "Flagello",          8); }
    public static Weapon lancia()         { return new Weapon(2.5,  10,  "Lancia",            6); }
    public static Weapon spadaLunga()     { return new Weapon(2.0,  20,  "SpadaLunga",        8); }
    public static Weapon martelloGuerra() { return new Weapon(3.0,  20,  "MartelloGuerra",    8); }
    public static Weapon picconeGuerra()  { return new Weapon(3.0,  15,  "PicconeGuerra",     8); }
    public static Weapon stocco()         { return new Weapon(1.5,  15,  "Stocco",            8); }
    public static Weapon alabarda()       { return new Weapon(4.0,  25,  "Alabarda",          10); }
    public static Weapon spadone()        { return new Weapon(5.0,  30,  "Spadone",           12); }
    public static Weapon falcione()       { return new Weapon(4.0,  25,  "Falcione",          12); }
    public static Weapon bastone()        { return new Weapon(1.0,   3,  "Bastone",            6); }
}