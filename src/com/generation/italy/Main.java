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

        // 1. Creazione personaggio
        Player pg = CreatePlayer.crea();
        Stats.assegna(pg);

        RaceEntity razzaPg = RaceEntity.da(pg.specie);
        razzaPg.applicaBonus(pg);
        OutputUtils.print("Razza: " + razzaPg.getNome() + " — " + razzaPg.trattoSpeciale);

        ClassEntity classePg = ClassEntity.da(pg.classe);
        pg.classeArmatura = classePg.calcolaCA(pg);
        pg.setMaxHp(classePg.calcolaHP(pg));

        OutputUtils.print();
        OutputUtils.print("=== Equipaggiamento Iniziale ===");
        classePg.equipaggioIniziale(pg);
        OutputUtils.print("================================");
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
        Location loc = CreateLocation.scegli(pg);  // scelta location FUORI dal while
        int stanzaDaRiprendere = 1;

        while (continua && pg.getPuntiFerita() > 0) {

            Location.RisultatoDungeon risultato = loc.eseguiDungeon(pg, stanzaDaRiprendere);

            switch (risultato.stato) {

                case MORTO:
                    // il while si ferma da solo
                    break;

                case MERCANTE:
                    // torna dal mercante e RIPRENDE lo stesso dungeon dalla stanza salvata
                    OutputUtils.print("\nSei tornato all'avamposto dal mercante.");
                    Merchant.gestisciBottega(pg);
                    stanzaDaRiprendere = risultato.prossimaStanza;
                    OutputUtils.print("Torni nel dungeon " + loc.nome + " dalla stanza " + stanzaDaRiprendere + "...");
                    break;

                case RITIRATO:
                    // ritiro vero — reset completo, sceglie nuovo dungeon
                    OutputUtils.print("\nSei tornato all'avamposto.");
                    stanzaDaRiprendere = 1;
                    continua = CreateLocation.richiediNuovoViaggio();
                    if (continua) loc = CreateLocation.scegli(pg);
                    break;

                case COMPLETATO:
                    OutputUtils.print("\n*********************************************************");
                    OutputUtils.print(" IMPRESA COMPIUTA! Hai distrutto il Boss del Dungeon: " + loc.nome + "!");
                    OutputUtils.print("*********************************************************");
                    OutputUtils.print();
                    Merchant.gestisciBottega(pg);
                    stanzaDaRiprendere = 1;
                    continua = CreateLocation.richiediNuovoViaggio();
                    if (continua) loc = CreateLocation.scegli(pg);
                    break;
            }
        }

        // Conclusione
        if (pg.getPuntiFerita() <= 0) {
            OutputUtils.print("\nLa tua avventura finisce qui... Le canzoni ricorderanno il tuo sacrificio.");
        } else {
            OutputUtils.print("\nHai deciso di ritirarti dalle leggende. Ti godi le tue " + pg.oro + " monete d'oro in taverna!");
        }


        // Conclusione
        if (pg.getPuntiFerita() <= 0) {
            OutputUtils.print("\nLa tua avventura finisce qui... Le canzoni ricorderanno il tuo sacrificio.");
        } else {
            OutputUtils.print("\nHai deciso di ritirarti dalle leggende. Ti godi le tue " + pg.oro + " monete d'oro in taverna!");
        }
    }
}