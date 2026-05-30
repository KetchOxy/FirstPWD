package com.generation.italy.domain;

import com.generation.italy.utils.OutputUtils;

public class Weapon extends Item {

    private boolean indossata;
    private int potenza;

    public Weapon(double peso, int valore, String nome, int potenza) {
        super(peso, valore, nome);
        this.indossata = false;
        this.potenza   = potenza;
    }

    public boolean isIndossata() { return indossata; }
    public int getPotenza()      { return potenza; }
    public void indossa()        { this.indossata = true; }
    public void rimuovi()        { this.indossata = false; }

    @Override
    public boolean usa(Player pg) {
        pg.indossaArma(this);
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s (%.1f kg, %d oro) POW:%d [%s]",
                getNome(), getPeso(), getValore(), potenza,
                indossata ? "equipaggiata" : "nello zaino");
    }

    // Factory
    public static Weapon artigliManuel()  { return new Weapon(6.0,   40, "Artigli della Giustizia di Manuel", 10); }
    public static Weapon asciaKonrad()    { return new Weapon(10.0,  35, "Ascia Oscura di Konrad",            15); }
    public static Weapon alabardaAndrea() { return new Weapon(15.0,  55, "Alabarda della Distruzione di Andrea", 20); }
    public static Weapon cortellino()     { return new Weapon(1.0,    1, "Cortellino Svizzero",                2); }
    public static Weapon bastone()        { return new Weapon(10.0,   3, "Bastone",                            5); }
    public static Weapon spadoneRiccardo(){ return new Weapon(30.0,1000, "Spadone dell'Onniscienza di Riccardo", 250); }

    public static Weapon daStringa(String nome) {
        switch (nome.toLowerCase()) {
            case "pugnale":        return new Weapon(0.5,  5,  "Pugnale",        4);
            case "scimitarra":     return new Weapon(1.5,  15, "Scimitarra",     6);
            case "spadacorta":     return new Weapon(1.0,  10, "SpadaCorta",     6);
            case "asciaguerra":    return new Weapon(3.0,  20, "AsciaGuerra",    8);
            case "flagello":       return new Weapon(2.0,  15, "Flagello",       8);
            case "lancia":         return new Weapon(2.5,  10, "Lancia",         6);
            case "spadalunga":     return new Weapon(2.0,  20, "SpadaLunga",     8);
            case "martelloguerra": return new Weapon(3.0,  20, "MartelloGuerra", 8);
            case "picconeguerra":  return new Weapon(3.0,  15, "PicconeGuerra",  8);
            case "stocco":         return new Weapon(1.5,  15, "Stocco",         8);
            case "alabarda":       return new Weapon(4.0,  25, "Alabarda",       10);
            case "spadone":        return new Weapon(5.0,  30, "Spadone",        12);
            default:               return new Weapon(4.0,  25, "Falcione",       12);
        }
    }
}