package com.generation.italy.fight;

import com.generation.italy.domain.Enemy;
import com.generation.italy.domain.Inventory;
import com.generation.italy.domain.Location;
import com.generation.italy.domain.Player;
import com.generation.italy.utils.Dices;
import com.generation.italy.utils.InfoDices;
import com.generation.italy.utils.OutputUtils;
import com.generation.italy.utils.Weapons;
import com.generation.library.Console;

// COMBAT - gestisce il combattimento a turni tra giocatore e nemico
public class Combat {

    public static void avvia(Player pg, Enemy nemico, Location loc) {

        OutputUtils.print("=== Simulatore dadi D&D ===");
        InfoDices.mostra();

        // COMBATTIMENTO A TURNI - continua finche' uno dei due arriva a 0 PF
        while (pg.puntiFerita > 0 && nemico.puntiFerita > 0) {

            OutputUtils.print("----------------------------------");
            OutputUtils.print(pg.nome + " PF: " + pg.puntiFerita + "  |  " + nemico.nome + " PF: " + nemico.puntiFerita);
            OutputUtils.print("----------------------------------");

            // TURNO DEL GIOCATORE
            OutputUtils.print(">>> Il tuo turno <<<");
            OutputUtils.print("Che tipo di tiro vuoi fare?");
            OutputUtils.print("1. Tiro per colpire");
            OutputUtils.print("2. Tiro di abilita'");
            int tipoTiro = Console.readInt();

            int modificatore = Inventory.scegliModificatore(pg);

            // CONTROLLO BONUS LOCATION - vantaggio al tiro per colpire
            boolean haBonus = pg.classe.equalsIgnoreCase(loc.bonusClasse) && loc.bonusColpire;

            if (tipoTiro == 2) {
                TiroAbilita.esegui(modificatore);
            } else {
                // PASSA IL BONUS LOCATION A TIRO COLPIRE
                int dannoInflitto = TiroColpire.esegui(modificatore, pg, nemico.classeArmatura, haBonus);
                if (dannoInflitto > 0) {
                    // BONUS DANNO DA LOCATION
                    if (pg.classe.equalsIgnoreCase(loc.bonusClasse) && loc.bonusDanno) {
                        dannoInflitto += 2;
                        OutputUtils.print("*** Bonus location +2 al danno! Danno totale: " + dannoInflitto + " ***");
                    }
                    nemico.puntiFerita -= dannoInflitto;
                    OutputUtils.print(nemico.nome + " ha ora " + nemico.puntiFerita + " PF.");
                }
            }

            // CONTROLLO FINE COMBATTIMENTO
            if (nemico.puntiFerita <= 0) break;

            // TURNO DEL NEMICO
            OutputUtils.print(">>> Turno di " + nemico.nome + " <<<");
            int tiroNemico = Dices.tira(20);
            OutputUtils.print(nemico.nome + " tira D20: " + tiroNemico);

            if (tiroNemico == 1) {
                OutputUtils.print("*** FUMBLE del nemico! Ha mancato miseramente! ***");
            } else {
                int modNemico = Enemy.calcolaModificatore(nemico.forza);
                int tiroFinaleNemico = tiroNemico + modNemico;
                OutputUtils.print("Tiro finale: " + tiroNemico + " + mod(" + modNemico + ") = " + tiroFinaleNemico);

                if (tiroFinaleNemico < pg.classeArmatura && tiroNemico != 20) {
                    OutputUtils.print(nemico.nome + " ha mancato! La tua CA e': " + pg.classeArmatura);
                } else {
                    if (tiroNemico == 20) OutputUtils.print("*** CRITICO del nemico! ***");
                    int dannoNemico = Weapons.tiraDannoArma(nemico.arma);
                    if (tiroNemico == 20) dannoNemico *= 2;
                    pg.puntiFerita -= dannoNemico;
                    OutputUtils.print(nemico.nome + " ti ha colpito per " + dannoNemico + " danni!");
                    OutputUtils.print("I tuoi PF: " + pg.puntiFerita);
                }
            }
        }

        // FINE COMBATTIMENTO
        OutputUtils.print("==================================");
        if (pg.puntiFerita <= 0) {
            OutputUtils.print("Sei stato sconfitto da " + nemico.nome + "...");
            OutputUtils.print("GAME OVER");
        } else {
            OutputUtils.print("Hai sconfitto " + nemico.nome + "!");
            OutputUtils.print("PF rimasti: " + pg.puntiFerita);
        }
        OutputUtils.print("==================================");
    }
}