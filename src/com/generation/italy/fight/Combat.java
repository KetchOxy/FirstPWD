package com.generation.italy.fight;

import com.generation.italy.domain.Enemy;
import com.generation.italy.domain.Player;
import com.generation.italy.utils.Dices;
import com.generation.italy.utils.OutputUtils;
import com.generation.italy.utils.Weapons;
import com.generation.library.Console;

/**
 * COMBAT - Gestisce il loop di combattimento Player vs Enemy.
 * Restituisce true se il giocatore vince, false se viene sconfitto.
 */
public class Combat {

    public static boolean avvia(Player pg, Enemy nemico, String bonusClasse) {
        int pfNemicoIniziali = nemico.getPuntiFeritaMax();

        OutputUtils.print("\n⚔ COMBATTIMENTO: " + pg.getNome() + " vs " + nemico.getNome());
        OutputUtils.print(nemico.toString());

        while (pg.isVivo() && nemico.isVivo()) {
            OutputUtils.print("\n----------------------------------");
            OutputUtils.print(pg.getNome() + " PF: " + pg.getPuntiFerita() + "/" + pg.getPuntiFeritaMax()
                    + " | " + nemico.getNome() + " PF: " + nemico.getPuntiFerita() + "/" + pfNemicoIniziali);
            OutputUtils.print("----------------------------------");

            // --- TURNO GIOCATORE ---
            OutputUtils.print(">>> Il tuo turno <<<");
            OutputUtils.print("1. Attacca con " + pg.getArma());
            OutputUtils.print("2. Usa Pozione di Cura (Rimaste: " + pg.getNumeroPozioni() + ")");
            System.out.print("Scelta: ");
            int scelta = Console.readInt();

            if (scelta == 1) {
                String statoTiro = calcolaStatoTiro(pg, nemico, pfNemicoIniziali);
                int mod = Player.calcolaModificatore(pg.getForza());
                int danno = TiroColpire.esegui(mod, pg, nemico.getClasseArmatura(), statoTiro);

                if (danno > 0) {
                    // Bonus classe/location
                    if (pg.getClasse().equalsIgnoreCase(bonusClasse)) {
                        danno += 2;
                        OutputUtils.print("*** Bonus di Classe! +2 danni! ***");
                    }
                    nemico.riceviDanno(danno);
                    OutputUtils.print("Infliggi " + danno + " danni a " + nemico.getNome() + "!");
                }

            } else if (scelta == 2) {
                if (pg.getNumeroPozioni() > 0) {
                    int cura = Dices.tira(4) + Dices.tira(4) + 2;
                    pg.riceviCura(cura);
                    pg.rimuoviPozione();
                    OutputUtils.print("Bevi una pozione! +" + cura + " PF → " + pg.getPuntiFerita() + "/" + pg.getPuntiFeritaMax());
                } else {
                    OutputUtils.print("Nessuna pozione! Perdi il turno.");
                }
            } else {
                OutputUtils.print("Scelta non valida. Perdi il turno.");
            }

            // --- TURNO NEMICO ---
            if (nemico.isVivo()) {
                OutputUtils.print("\n>>> Turno di " + nemico.getNome() + " <<<");
                int tiroNemico = Dices.tira(20);
                OutputUtils.print(nemico.getNome() + " tira: " + tiroNemico);

                if (tiroNemico == 1) {
                    OutputUtils.print("*** FUMBLE del nemico! Inciampa da solo! ***");
                } else {
                    int modNemico = Enemy.calcolaModificatore(nemico.getForza());
                    int totaleNemico = tiroNemico + modNemico;
                    OutputUtils.print("Totale: " + tiroNemico + " + mod(" + modNemico + ") = " + totaleNemico + " vs CA " + pg.getClasseArmatura());

                    if (totaleNemico < pg.getClasseArmatura() && tiroNemico != 20) {
                        OutputUtils.print(nemico.getNome() + " manca!");
                    } else {
                        if (tiroNemico == 20) OutputUtils.print("*** CRITICO DEL NEMICO! ***");
                        int dannoNemico = Weapons.tiraDannoArma(nemico.getArma());
                        if (tiroNemico == 20) dannoNemico *= 2;
                        pg.riceviDanno(dannoNemico);
                        OutputUtils.print(nemico.getNome() + " ti colpisce per " + dannoNemico + " danni! PF: " + pg.getPuntiFerita() + "/" + pg.getPuntiFeritaMax());
                    }
                }
            }
        }

        // --- RISULTATO ---
        OutputUtils.print("\n==================================");
        if (!pg.isVivo()) {
            OutputUtils.print("Sei stato sconfitto da " + nemico.getNome() + "...");
            OutputUtils.print("GAME OVER");
            return false;
        } else {
            OutputUtils.print("Hai sconfitto " + nemico.getNome() + "! Vittoria!");
            return true;
        }
    }

    private static String calcolaStatoTiro(Player pg, Enemy nemico, int pfNemicoIniziali) {
        if (pg.getPuntiFerita() < pg.getPuntiFeritaMax() / 2.0) {
            OutputUtils.print("[FERITO GRAVEMENTE] Attacchi con SVANTAGGIO!");
            return "s";
        } else if (nemico.getPuntiFerita() < pfNemicoIniziali / 2.0) {
            OutputUtils.print("[NEMICO STREMATO] Attacchi con VANTAGGIO!");
            return "v";
        }
        return "no";
    }
}