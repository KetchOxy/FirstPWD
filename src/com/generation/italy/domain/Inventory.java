package com.generation.italy.domain;

import com.generation.italy.utils.OutputUtils;
import com.generation.library.*;

// CARATTERISTICHE PERSONAGGIO - inserimento FOR, DES, COS, INT, SAG, CAR
public class Inventory {

    public static void assegna(Player pg) {

        int PuntiTotali = 27;

        OutputUtils.print("Hai tutte le caratteristiche ad 8, e 27 punti disponibili.");
        OutputUtils.print("Incrementa le tue caratteristiche fino ad un massimo di 15.");
        OutputUtils.print();

        // FORZA
        do {
            System.out.print("Forza (8-15, punti rimasti: " + PuntiTotali + "): ");
            pg.forza = Console.readInt();
            if (pg.forza < 8 || pg.forza > 15) OutputUtils.print("Valore non valido! Scegli tra 8 e 15.");
            else if (pg.forza - 8 > PuntiTotali) OutputUtils.print("Punti insufficienti!");
        } while (pg.forza < 8 || pg.forza > 15 || pg.forza - 8 > PuntiTotali);
        PuntiTotali -= (pg.forza - 8);

        // DESTREZZA
        do {
            System.out.print("Destrezza (8-15, punti rimasti: " + PuntiTotali + "): ");
            pg.destrezza = Console.readInt();
            if (pg.destrezza < 8 || pg.destrezza > 15) OutputUtils.print("Valore non valido! Scegli tra 8 e 15.");
            else if (pg.destrezza - 8 > PuntiTotali) OutputUtils.print("Punti insufficienti!");
        } while (pg.destrezza < 8 || pg.destrezza > 15 || pg.destrezza - 8 > PuntiTotali);
        PuntiTotali -= (pg.destrezza - 8);

        // COSTITUZIONE
        do {
            System.out.print("Costituzione (8-15, punti rimasti: " + PuntiTotali + "): ");
            pg.costituzione = Console.readInt();
            if (pg.costituzione < 8 || pg.costituzione > 15) OutputUtils.print("Valore non valido! Scegli tra 8 e 15.");
            else if (pg.costituzione - 8 > PuntiTotali) OutputUtils.print("Punti insufficienti!");
        } while (pg.costituzione < 8 || pg.costituzione > 15 || pg.costituzione - 8 > PuntiTotali);
        PuntiTotali -= (pg.costituzione - 8);

        // INTELLIGENZA
        do {
            System.out.print("Intelligenza (8-15, punti rimasti: " + PuntiTotali + "): ");
            pg.intelligenza = Console.readInt();
            if (pg.intelligenza < 8 || pg.intelligenza > 15) OutputUtils.print("Valore non valido! Scegli tra 8 e 15.");
            else if (pg.intelligenza - 8 > PuntiTotali) OutputUtils.print("Punti insufficienti!");
        } while (pg.intelligenza < 8 || pg.intelligenza > 15 || pg.intelligenza - 8 > PuntiTotali);
        PuntiTotali -= (pg.intelligenza - 8);

        // SAGGEZZA
        do {
            System.out.print("Saggezza (8-15, punti rimasti: " + PuntiTotali + "): ");
            pg.saggezza = Console.readInt();
            if (pg.saggezza < 8 || pg.saggezza > 15) OutputUtils.print("Valore non valido! Scegli tra 8 e 15.");
            else if (pg.saggezza - 8 > PuntiTotali) OutputUtils.print("Punti insufficienti!");
        } while (pg.saggezza < 8 || pg.saggezza > 15 || pg.saggezza - 8 > PuntiTotali);
        PuntiTotali -= (pg.saggezza - 8);

        // CARISMA
        do {
            System.out.print("Carisma (8-15, punti rimasti: " + PuntiTotali + "): ");
            pg.carisma = Console.readInt();
            if (pg.carisma < 8 || pg.carisma > 15) OutputUtils.print("Valore non valido! Scegli tra 8 e 15.");
            else if (pg.carisma - 8 > PuntiTotali) OutputUtils.print("Punti insufficienti!");
        } while (pg.carisma < 8 || pg.carisma > 15 || pg.carisma - 8 > PuntiTotali);
        PuntiTotali -= (pg.carisma - 8);

        // RIASSEGNAZIONE PUNTI RIMASTI
        while (PuntiTotali > 0) {
            OutputUtils.print("Ti sono rimasti " + PuntiTotali + " punti! A quale caratteristica vuoi aggiungerli?");
            OutputUtils.print("1. Forza        (attuale: " + pg.forza + ")");
            OutputUtils.print("2. Destrezza    (attuale: " + pg.destrezza + ")");
            OutputUtils.print("3. Costituzione (attuale: " + pg.costituzione + ")");
            OutputUtils.print("4. Intelligenza (attuale: " + pg.intelligenza + ")");
            OutputUtils.print("5. Saggezza     (attuale: " + pg.saggezza + ")");
            OutputUtils.print("6. Carisma      (attuale: " + pg.carisma + ")");
            int scelta = Console.readInt();

            switch (scelta) {
                case 1:
                    if (pg.forza < 15) { pg.forza++; PuntiTotali--; }
                    else OutputUtils.print("Caratteristica gia' al massimo!");
                    break;
                case 2:
                    if (pg.destrezza < 15) { pg.destrezza++; PuntiTotali--; }
                    else OutputUtils.print("Caratteristica gia' al massimo!");
                    break;
                case 3:
                    if (pg.costituzione < 15) { pg.costituzione++; PuntiTotali--; }
                    else OutputUtils.print("Caratteristica gia' al massimo!");
                    break;
                case 4:
                    if (pg.intelligenza < 15) { pg.intelligenza++; PuntiTotali--; }
                    else OutputUtils.print("Caratteristica gia' al massimo!");
                    break;
                case 5:
                    if (pg.saggezza < 15) { pg.saggezza++; PuntiTotali--; }
                    else OutputUtils.print("Caratteristica gia' al massimo!");
                    break;
                case 6:
                    if (pg.carisma < 15) { pg.carisma++; PuntiTotali--; }
                    else OutputUtils.print("Caratteristica gia' al massimo!");
                    break;
                default:
                    OutputUtils.print("Scelta non valida!");
            }
        }

        // RIEPILOGO PERSONAGGIO - BUG FIX: COS usava pg.intelligenza
        OutputUtils.print();
        OutputUtils.print("=== Scheda Personaggio ===");
        OutputUtils.print("Nome:   " + pg.nome);
        OutputUtils.print("Specie: " + pg.specie);
        OutputUtils.print("Classe: " + pg.classe);
        OutputUtils.print("PF:     " + pg.puntiFerita);
        OutputUtils.print("CA:     " + pg.classeArmatura);
        OutputUtils.print("FOR: " + pg.forza + " (mod: " + Player.calcolaModificatore(pg.forza) + ")");
        OutputUtils.print("DES: " + pg.destrezza + " (mod: " + Player.calcolaModificatore(pg.destrezza) + ")");
        OutputUtils.print("COS: " + pg.costituzione + " (mod: " + Player.calcolaModificatore(pg.costituzione) + ")");
        OutputUtils.print("INT: " + pg.intelligenza + " (mod: " + Player.calcolaModificatore(pg.intelligenza) + ")");
        OutputUtils.print("SAG: " + pg.saggezza + " (mod: " + Player.calcolaModificatore(pg.saggezza) + ")");
        OutputUtils.print("CAR: " + pg.carisma + " (mod: " + Player.calcolaModificatore(pg.carisma) + ")");
        OutputUtils.print();
    }

    public static int scegliModificatore(Player pg) {
        OutputUtils.print("Quale caratteristica usi?");
        OutputUtils.print("1. Forza        (mod: " + Player.calcolaModificatore(pg.forza) + ")");
        OutputUtils.print("2. Destrezza    (mod: " + Player.calcolaModificatore(pg.destrezza) + ")");
        OutputUtils.print("3. Costituzione (mod: " + Player.calcolaModificatore(pg.costituzione) + ")");
        OutputUtils.print("4. Intelligenza (mod: " + Player.calcolaModificatore(pg.intelligenza) + ")");
        OutputUtils.print("5. Saggezza     (mod: " + Player.calcolaModificatore(pg.saggezza) + ")");
        OutputUtils.print("6. Carisma      (mod: " + Player.calcolaModificatore(pg.carisma) + ")");
        int sceltaStat = Console.readInt();

        switch (sceltaStat) {
            case 1: return Player.calcolaModificatore(pg.forza);
            case 2: return Player.calcolaModificatore(pg.destrezza);
            case 3: return Player.calcolaModificatore(pg.costituzione);
            case 4: return Player.calcolaModificatore(pg.intelligenza);
            case 5: return Player.calcolaModificatore(pg.saggezza);
            default: return Player.calcolaModificatore(pg.carisma);
        }
    }
}