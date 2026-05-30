package com.generation.italy.utils;

import com.generation.italy.domain.Player;

public class RaceEntity extends Entity {

    public final int bonusForza;
    public final int bonusDestrezza;
    public final int bonusCostituzione;
    public final int bonusIntelligenza;
    public final int bonusSaggezza;
    public final int bonusCarisma;
    public final String trattoSpeciale;

    private RaceEntity(String nome,
                       int bonusForza, int bonusDestrezza, int bonusCostituzione,
                       int bonusIntelligenza, int bonusSaggezza, int bonusCarisma,
                       String trattoSpeciale) {
        super(0, nome, 1);
        this.bonusForza        = bonusForza;
        this.bonusDestrezza    = bonusDestrezza;
        this.bonusCostituzione = bonusCostituzione;
        this.bonusIntelligenza = bonusIntelligenza;
        this.bonusSaggezza     = bonusSaggezza;
        this.bonusCarisma      = bonusCarisma;
        this.trattoSpeciale    = trattoSpeciale;
    }

    // Applica i bonus razziali al Player
    public void applicaBonus(Player pg) {
        pg.forza        += this.bonusForza;
        pg.destrezza    += this.bonusDestrezza;
        pg.costituzione += this.bonusCostituzione;
        pg.intelligenza += this.bonusIntelligenza;
        pg.saggezza     += this.bonusSaggezza;
        pg.carisma      += this.bonusCarisma;
    }

    public static final RaceEntity UMANO      = new RaceEntity("Umano",      1, 1, 1, 1, 1, 1, "Versatile: +1 dado competenza a una skill a scelta");
    public static final RaceEntity ELFO       = new RaceEntity("Elfo",       0, 2, 0, 0, 0, 0, "Sensi Acuti: vantaggio ai tiri percezione");
    public static final RaceEntity NANO       = new RaceEntity("Nano",       0, 0, 2, 0, 0, 0, "Resistenza: vantaggio ai tiri salvezza contro veleno");
    public static final RaceEntity HALFLING   = new RaceEntity("Halfling",   0, 2, 0, 0, 0, 0, "Fortunato: ritira i fumble sul d20");
    public static final RaceEntity DRAGONBORN = new RaceEntity("Dragonborn", 2, 0, 0, 0, 0, 1, "Soffio del Drago: 2d6 danni elementali ad area");
    public static final RaceEntity GNOMO      = new RaceEntity("Gnomo",      0, 0, 0, 2, 0, 0, "Astuzia Gnoma: vantaggio ai tiri INT contro magia");
    public static final RaceEntity TIEFLING   = new RaceEntity("Tiefling",   0, 0, 0, 1, 0, 2, "Resistenza Infernale: resistenza al fuoco");
    public static final RaceEntity ORCO       = new RaceEntity("Orco",       2, 0, 1, 0, 0, 0, "Implacabile: scendi a 1 PF invece di 0 una volta");
    public static final RaceEntity GOLIATH    = new RaceEntity("Goliath",    2, 0, 1, 0, 0, 0, "Costituzione di Pietra: riduce 1d12 danni una volta");
    public static final RaceEntity AASIMAR    = new RaceEntity("Aasimar",    0, 0, 0, 0, 1, 2, "Guarigione Radiosa: cura 1d4 PF a un alleato");

    private static final RaceEntity[] TUTTE = {
            UMANO, ELFO, NANO, HALFLING, DRAGONBORN,
            GNOMO, TIEFLING, ORCO, GOLIATH, AASIMAR
    };

    public static RaceEntity da(String nome) {
        for (RaceEntity r : TUTTE) {
            if (r.getNome().equalsIgnoreCase(nome)) return r;
        }
        throw new IllegalArgumentException("Razza non trovata: " + nome);
    }
}