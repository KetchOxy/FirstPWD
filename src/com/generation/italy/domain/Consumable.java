package com.generation.italy.domain;

import com.generation.italy.utils.OutputUtils;

public class Consumable extends Item {

    public enum Effetto { CURA, ANTIDOTO, BONUS_ATTACCO }

    private Effetto effetto;
    private int potenza;

    public Consumable(double peso, int valore, String nome, Effetto effetto, int potenza) {
        super(peso, valore, nome);
        this.effetto = effetto;
        this.potenza = potenza;
    }

    public Effetto getEffetto() { return effetto; }
    public int getPotenza()     { return potenza; }

    @Override
    public boolean usa(Player pg) {
        switch (effetto) {
            case CURA:
                int hpPrima = pg.getPuntiFerita();
                int hpDopo  = Math.min(pg.getPuntiFeritaMax(), hpPrima + potenza);
                pg.setCurrentHp(hpDopo);
                int curati = hpDopo - hpPrima;
                if (curati == 0) {
                    OutputUtils.print("Sei gia' al massimo! " + getNome() + " non ha effetto.");
                    return false;
                }
                OutputUtils.print("Usi " + getNome() + " e recuperi " + curati + " PF! " + pg.getHpBar());
                break;
            case ANTIDOTO:
                OutputUtils.print("Usi " + getNome() + " — il veleno svanisce dal tuo corpo!");
                break;
            case BONUS_ATTACCO:
                OutputUtils.print("Usi " + getNome() + " — ti senti piu' forte per qualche istante!");
                break;
        }
        pg.rimuoviItem(this);
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s (consumabile, effetto: %s +%d)", getNome(), effetto, potenza);
    }

    // Factory
    public static Consumable potioneCura()   { return new Consumable(0.5, 20, "Pozione di Cura", Effetto.CURA,         30); }
    public static Consumable bendaSacra()    { return new Consumable(0.3, 15, "Benda Sacra",     Effetto.CURA,         15); }
    public static Consumable antidoto()      { return new Consumable(0.3, 18, "Antidoto",        Effetto.ANTIDOTO,      0); }
    public static Consumable fungoVelenoso() { return new Consumable(0.2,  5, "Fungo Velenoso",  Effetto.CURA,          5); }
    public static Consumable elisirForza()   { return new Consumable(0.5, 80, "Elisir di Forza", Effetto.BONUS_ATTACCO,20); }
}