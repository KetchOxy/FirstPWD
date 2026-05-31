package com.generation.italy.fight;

import com.generation.italy.domain.Enemy;
import com.generation.italy.domain.Location;
import com.generation.italy.domain.Player;
import com.generation.italy.utils.Dices;
import com.generation.italy.utils.OutputUtils;
import com.generation.library.Console;

public class Combat {

    public static void avvia(Player pg, Enemy nemico, Location loc, int pfMostroIniziali) {

        OutputUtils.print("=== Simulatore dadi D&D ===");

        while (pg.getPuntiFerita() > 0 && nemico.getCurrentHp() > 0) {

            OutputUtils.print("----------------------------------");
            OutputUtils.print(pg.getNome() + " PF: " + pg.getPuntiFerita() + "/" + pg.getPuntiFeritaMax() + " (Pozioni: " + pg.numeroPozioni + ") | " + nemico.getNome() + " PF: " + nemico.getCurrentHp() + "/" + pfMostroIniziali);
            OutputUtils.print("----------------------------------");

            // TURNO DEL GIOCATORE
            OutputUtils.print(">>> Il tuo turno <<<");
            OutputUtils.print("Che tipo di azione vuoi fare?");
            OutputUtils.print("1. Tiro per colpire (Attacca con " + pg.getArma() + ")");
            OutputUtils.print("2. Usa Pozione di Cura (Rimanenti: " + pg.numeroPozioni + ")");
            System.out.print("Scelta: ");
            int sceltaAzione = Console.readInt();

            if (sceltaAzione == 1) {
                String statoTiro = "no";

                // Vantaggio e svantaggio automatici legati alle ferite
                if (pg.getPuntiFerita() < (pg.getPuntiFeritaMax() / 2.0)) {
                    OutputUtils.print("[STATO: FERITO GRAVEMENTE] Attacchi con SVANTAGGIO!");
                    statoTiro = "s";
                }
                else if (nemico.getCurrentHp() < (pfMostroIniziali / 2.0)) {
                    OutputUtils.print("[STATO: NEMICO STREMATO] Approfitti del VANTAGGIO!");
                    statoTiro = "v";
                }

                int modAttacco = Player.calcolaModificatore(pg.forza);
                int dannoInflitto = TiroColpire.eseguiAutomatico(modAttacco, pg, nemico.classeArmatura, statoTiro);

                if (dannoInflitto > 0) {
                    if (pg.classe.equalsIgnoreCase(loc.bonusClasse) && loc.bonusDanno) {
                        dannoInflitto += 2;
                        OutputUtils.print("*** Furia della Location! +2 danni bonus! ***");
                    }
                    nemico.setCurrentHp(nemico.getCurrentHp() - dannoInflitto);
                    OutputUtils.print("Hai fatto " + dannoInflitto + " danni a " + nemico.getNome() + "!");
                }
            } else if (sceltaAzione == 2) {
                if (pg.numeroPozioni > 0) {
                    int cura = Dices.tira(4) + Dices.tira(4) + 2;
                    OutputUtils.print("Bevi una pozione di cura!");
                    pg.riceviCura(cura);
                    pg.numeroPozioni--;
                    OutputUtils.print("I tuoi nuovi PF: " + pg.getPuntiFerita() + "/" + pg.getPuntiFeritaMax());
                } else {
                    OutputUtils.print("Frughi nello zaino, ma non hai più pozioni! Perdi il turno!");
                }
            } else {
                OutputUtils.print("Scelta non valida! Esiti e perdi il turno.");
            }

            // TURNO DEL NEMICO
            if (nemico.getCurrentHp() > 0) {
                OutputUtils.print();
                OutputUtils.print(">>> Turno di " + nemico.getNome() + " <<<");
                int tiroNemico = Dices.tira(20);
                OutputUtils.print(nemico.getNome() + " tira il d20 per colpire... Risultato base: " + tiroNemico);

                if (tiroNemico == 1) {
                    OutputUtils.print("*** CLAMOROSO FUMBLE del nemico! Inciampa da solo! ***");
                } else {
                    int modNemico = Enemy.calcolaModificatore(nemico.forza);
                    int tiroFinaleNemico = tiroNemico + modNemico;
                    OutputUtils.print("Tiro finale: " + tiroNemico + " + mod(" + modNemico + ") = " + tiroFinaleNemico);

                    if (tiroFinaleNemico < pg.classeArmatura && tiroNemico != 20) {
                        OutputUtils.print(nemico.getNome() + " ha mancato! La tua CA e': " + pg.classeArmatura);
                    } else {
                        if (tiroNemico == 20) OutputUtils.print("*** CRITICO del nemico! ***");
                        int dannoNemico = nemico.arma.tiraDanno();
                        if (tiroNemico == 20) dannoNemico *= 2;
                        pg.setCurrentHp(pg.getPuntiFerita() - dannoNemico);
                        OutputUtils.print(nemico.getNome() + " ti ha colpito per " + dannoNemico + " danni!");
                        OutputUtils.print("I tuoi PF: " + pg.getPuntiFerita() + "/" + pg.getPuntiFeritaMax());
                    }
                }
            }
        }

        // FINE COMBATTIMENTO
        OutputUtils.print("==================================");
        if (pg.getPuntiFerita() <= 0) {
            OutputUtils.print("Sei stato sconfitto da " + nemico.getNome() + "...");
            OutputUtils.print("GAME OVER");
        } else {
            OutputUtils.print("Hai sconfitto " + nemico.getNome() + "! Vittoria!");

        }
    }
}