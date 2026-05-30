package com.generation.italy;

import com.generation.italy.domain.Stats;
import com.generation.italy.domain.Player;
import com.generation.italy.domain.Location;
import com.generation.italy.utils.CreatePlayer;
import com.generation.italy.utils.CreateLocation;
import com.generation.italy.utils.OutputUtils;
import com.generation.italy.utils.RaceEntity;
import com.generation.italy.utils.ClassEntity;
import com.generation.italy.domain.Merchant;

public class Main {

    public static void main(String[] args) {

        OutputUtils.printTitle();

        // 1. Inizializzazione Personaggio e Caratteristiche
        Player pg = CreatePlayer.crea();
        Stats.assegna(pg);

        // Applica bonus razziali — PRIMA di ClassEntity
        RaceEntity razzaPg = RaceEntity.da(pg.specie);
        razzaPg.applicaBonus(pg);
        OutputUtils.print("Razza: " + razzaPg.getNome() + " — " + razzaPg.trattoSpeciale);

        // CA e PF calcolati da ClassEntity
        ClassEntity classePg = ClassEntity.da(pg.classe);
        pg.classeArmatura = classePg.calcolaCA(pg);
        pg.setMaxHp(classePg.calcolaHP(pg));
        classePg.equipaggioIniziale(pg);

        // RIEPILOGO PERSONAGGIO
        OutputUtils.print();
        OutputUtils.print("=== Scheda Personaggio ===");
        OutputUtils.print("Nome:   " + pg.getNome());
        OutputUtils.print("Specie: " + pg.specie);
        OutputUtils.print("Classe: " + pg.classe);
        OutputUtils.print("PF:     " + pg.getPuntiFerita() + "/" + pg.getPuntiFeritaMax());
        OutputUtils.print("CA:     " + pg.classeArmatura);
        OutputUtils.print("FOR: " + pg.forza + " (mod: " + Player.calcolaModificatore(pg.forza) + ")");
        OutputUtils.print("DES: " + pg.destrezza + " (mod: " + Player.calcolaModificatore(pg.destrezza) + ")");
        OutputUtils.print("COS: " + pg.costituzione + " (mod: " + Player.calcolaModificatore(pg.costituzione) + ")");
        OutputUtils.print("INT: " + pg.intelligenza + " (mod: " + Player.calcolaModificatore(pg.intelligenza) + ")");
        OutputUtils.print("SAG: " + pg.saggezza + " (mod: " + Player.calcolaModificatore(pg.saggezza) + ")");
        OutputUtils.print("CAR: " + pg.carisma + " (mod: " + Player.calcolaModificatore(pg.carisma) + ")");
        OutputUtils.print("Abilita': " + classePg.abilitaSpeciale);
        OutputUtils.print();

        // CICLO GENERALE DEL GIOCO
        boolean continua = true;
        while (continua && pg.getPuntiFerita() > 0) {

            Location loc = CreateLocation.scegli(pg);
            loc.eseguiDungeon(pg);

            if (pg.getPuntiFerita() > 0) {
                OutputUtils.print("\n*********************************************************");
                OutputUtils.print(" IMPRESA COMPIUTA! Hai distrutto il Boss del Dungeon: " + loc.nome + "!");
                OutputUtils.print("*********************************************************");
                OutputUtils.print();

                Merchant.gestisciBottega(pg);
                continua = CreateLocation.richiediNuovoViaggio();
            }
        }

        // 5. Conclusione
        if (pg.getPuntiFerita() <= 0) {
            OutputUtils.print("\nLa tua avventura finisce qui... Le canzoni ricorderanno il tuo sacrificio.");
        } else {
            OutputUtils.print("\nHai deciso di ritirarti dalle leggende. Ti godi le tue " + pg.oro + " monete d'oro in taverna!");
        }
    }
}