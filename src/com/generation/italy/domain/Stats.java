package com.generation.italy.domain;

import com.generation.italy.utils.OutputUtils;
import com.generation.library.Console;

/**
 * STATS - Gestisce l'assegnazione delle statistiche del personaggio (Point Buy).
 */
public class Stats {

    public static void assegna(Player pg) {
        int puntiTotali = 27;

        OutputUtils.print("\n=== Assegna le Caratteristiche (Point Buy) ===");
        OutputUtils.print("Tutte le stat partono da 8. Hai " + puntiTotali + " punti da distribuire (max 15 per stat).");
        OutputUtils.print();

        pg.setForza(chiediStat("Forza",        puntiTotali, pg)); puntiTotali -= (pg.getForza() - 8);
        pg.setDestrezza(chiediStat("Destrezza",    puntiTotali, pg)); puntiTotali -= (pg.getDestrezza() - 8);
        pg.setCostituzione(chiediStat("Costituzione", puntiTotali, pg)); puntiTotali -= (pg.getCostituzione() - 8);
        pg.setIntelligenza(chiediStat("Intelligenza", puntiTotali, pg)); puntiTotali -= (pg.getIntelligenza() - 8);
        pg.setSaggezza(chiediStat("Saggezza",      puntiTotali, pg)); puntiTotali -= (pg.getSaggezza() - 8);
        pg.setCarisma(chiediStat("Carisma",       puntiTotali, pg)); puntiTotali -= (pg.getCarisma() - 8);

        // Redistribuisci eventuali punti rimasti
        while (puntiTotali > 0) {
            OutputUtils.print("\nHai ancora " + puntiTotali + " punti! A quale stat aggiungerli?");
            OutputUtils.print("1.Forza(" + pg.getForza() + ") 2.Des(" + pg.getDestrezza() + ") 3.Cos(" + pg.getCostituzione()
                    + ") 4.Int(" + pg.getIntelligenza() + ") 5.Sag(" + pg.getSaggezza() + ") 6.Car(" + pg.getCarisma() + ")");
            int s = Console.readInt();
            switch (s) {
                case 1: if (pg.getForza() < 15)       { pg.setForza(pg.getForza() + 1);             puntiTotali--; } else OutputUtils.print("Già al massimo!"); break;
                case 2: if (pg.getDestrezza() < 15)   { pg.setDestrezza(pg.getDestrezza() + 1);     puntiTotali--; } else OutputUtils.print("Già al massimo!"); break;
                case 3: if (pg.getCostituzione() < 15){ pg.setCostituzione(pg.getCostituzione()+1); puntiTotali--; } else OutputUtils.print("Già al massimo!"); break;
                case 4: if (pg.getIntelligenza() < 15){ pg.setIntelligenza(pg.getIntelligenza()+1); puntiTotali--; } else OutputUtils.print("Già al massimo!"); break;
                case 5: if (pg.getSaggezza() < 15)    { pg.setSaggezza(pg.getSaggezza() + 1);       puntiTotali--; } else OutputUtils.print("Già al massimo!"); break;
                case 6: if (pg.getCarisma() < 15)     { pg.setCarisma(pg.getCarisma() + 1);         puntiTotali--; } else OutputUtils.print("Già al massimo!"); break;
                default: OutputUtils.print("Scelta non valida.");
            }
        }

        stampaScheda(pg);
    }

    private static int chiediStat(String nomeStat, int puntiRimasti, Player pg) {
        int valore;
        do {
            System.out.print(nomeStat + " (8-15, punti rimasti: " + puntiRimasti + "): ");
            valore = Console.readInt();
            if (valore < 8 || valore > 15)     OutputUtils.print("Valore non valido! Scegli tra 8 e 15.");
            else if (valore - 8 > puntiRimasti) OutputUtils.print("Punti insufficienti!");
        } while (valore < 8 || valore > 15 || valore - 8 > puntiRimasti);
        return valore;
    }

    public static void stampaScheda(Player pg) {
        OutputUtils.print();
        OutputUtils.print("=== Scheda Personaggio ===");
        OutputUtils.print("Nome:   " + pg.getNome());
        OutputUtils.print("Specie: " + pg.getSpecie());
        OutputUtils.print("Classe: " + pg.getClasse());
        OutputUtils.print("Arma:   " + pg.getArma());
        OutputUtils.print("PF:     " + pg.getPuntiFerita() + "/" + pg.getPuntiFeritaMax());
        OutputUtils.print("CA:     " + pg.getClasseArmatura());
        OutputUtils.print("FOR: " + pg.getForza() + " (mod: " + pg.getModForza() + ")");
        OutputUtils.print("DES: " + pg.getDestrezza() + " (mod: " + pg.getModDestrezza() + ")");
        OutputUtils.print("COS: " + pg.getCostituzione() + " (mod: " + pg.getModCostituzone() + ")");
        OutputUtils.print("INT: " + pg.getIntelligenza() + " (mod: " + pg.getModIntelligenza() + ")");
        OutputUtils.print("SAG: " + pg.getSaggezza() + " (mod: " + pg.getModSaggezza() + ")");
        OutputUtils.print("CAR: " + pg.getCarisma() + " (mod: " + pg.getModCarisma() + ")");
        OutputUtils.print();
    }

    public static int scegliModificatore(Player pg) {
        OutputUtils.print("Quale caratteristica usi?");
        OutputUtils.print("1.FOR(" + pg.getModForza() + ") 2.DES(" + pg.getModDestrezza()
                + ") 3.COS(" + pg.getModCostituzone() + ") 4.INT(" + pg.getModIntelligenza()
                + ") 5.SAG(" + pg.getModSaggezza() + ") 6.CAR(" + pg.getModCarisma() + ")");
        int s = Console.readInt();
        switch (s) {
            case 1: return pg.getModForza();
            case 2: return pg.getModDestrezza();
            case 3: return pg.getModCostituzone();
            case 4: return pg.getModIntelligenza();
            case 5: return pg.getModSaggezza();
            default: return pg.getModCarisma();
        }
    }
}