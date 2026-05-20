package com.generation.italy.fight;

import com.generation.italy.domain.Enemy;
import com.generation.italy.domain.Location;
import com.generation.italy.domain.Player;
import com.generation.italy.utils.Dices;
import com.generation.italy.utils.OutputUtils;
import com.generation.italy.utils.Weapons;
import com.generation.library.Console;

public class Combat {

    public static void avvia(Player pg, Enemy nemico, Location loc, int pfMostroIniziali) {

        OutputUtils.print("=== Simulatore dadi D&D ===");

        while (pg.puntiFerita > 0 && nemico.puntiFerita > 0) {

            OutputUtils.print("----------------------------------");
            OutputUtils.print(pg.nome + " PF: " + pg.puntiFerita + "/" + pg.puntiFeritaMax + " (Pozioni: " + pg.numeroPozioni + ") | " + nemico.nome + " PF: " + nemico.puntiFerita + "/" + pfMostroIniziali);
            OutputUtils.print("----------------------------------");

            // TURNO DEL GIOCATORE
            OutputUtils.print(">>> Il tuo turno <<<");
            OutputUtils.print("Che tipo di azione vuoi fare?");
            OutputUtils.print("1. Tiro per colpire (Attacca con " + pg.arma + ")");
            OutputUtils.print("2. Usa Pozione di Cura (Rimanenti: " + pg.numeroPozioni + ")");
            System.out.print("Scelta: ");
            int sceltaAzione = Console.readInt();

            if (sceltaAzione == 1) {
                // DETERMINAZIONE AUTOMATICA DEL TIPO DI TIRO (Ferite)
                String statoTiro = "no"; // Di base tiro normale

                // Condizione 1: Il giocatore è sotto la metà dei suoi PF max -> SVANTAGGIO
                if (pg.puntiFerita < (pg.puntiFeritaMax / 2.0)) {
                    OutputUtils.print(" Sei ferito gravemente e perdi sangue! Attacchi con SVANTAGGIO!");
                    statoTiro = "s";
                }
                // Condizione 2: Il nemico è sotto la metà dei suoi PF iniziali -> VANTAGGIO
                else if (nemico.puntiFerita < (pfMostroIniziali / 2.0)) {
                    OutputUtils.print(" Il nemico e' stremato e barcolla! Approfitti del VANTAGGIO!");
                    statoTiro = "v";
                }

                int modAttacco = Player.calcolaModificatore(pg.forza);

                // Usiamo il nuovo metodo modificato che calcola il vantaggio/svantaggio senza fare domande
                int dannoInflitto = TiroColpire.eseguiAutomatico(modAttacco, pg, nemico.classeArmatura, statoTiro);

                if (dannoInflitto > 0) {
                    if (pg.classe.equalsIgnoreCase(loc.bonusClasse) && loc.bonusDanno) {
                        dannoInflitto += 2;
                        OutputUtils.print("*** Furia della Location! +2 danni bonus! ***");
                    }
                    nemico.puntiFerita -= dannoInflitto;
                    OutputUtils.print("Hai fatto " + dannoInflitto + " danni a " + nemico.nome + "!");
                }
            } else if (sceltaAzione == 2) {
                if (pg.numeroPozioni > 0) {
                    int cura = Dices.tira(4) + Dices.tira(4) + 2; // 2d4 + 2
                    OutputUtils.print("Bevi una pozione di cura!");
                    pg.riceviCura(cura); // <-- Chiamata al metodo sicuro (No sovracura)
                    pg.numeroPozioni--;
                    OutputUtils.print("I tuoi nuovi PF: " + pg.puntiFerita + "/" + pg.puntiFeritaMax);
                } else {
                    OutputUtils.print("Frughi nello zaino, ma non hai più pozioni! Perdi il turno!");
                }
            } else {
                OutputUtils.print("Scelta non valida! Esiti e perdi il turno.");
            }

            // TURNO DEL NEMICO
            if (nemico.puntiFerita > 0) {
                OutputUtils.print();
                OutputUtils.print(">>> Turno di " + nemico.nome + " <<<");
                int tiroNemico = Dices.tira(20);
                OutputUtils.print(nemico.nome + " tira il d20 per colpire... Risultato base: " + tiroNemico);

                if (tiroNemico == 1) {
                    OutputUtils.print("*** CLAMOROSO FUMBLE del nemico! Inciampa da solo! ***");
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
                        OutputUtils.print("I tuoi PF: " + pg.puntiFerita + "/" + pg.puntiFeritaMax);
                    }
                }
            }
        }

        // FINE COMBATTIMENTO
        OutputUtils.print("==================================");
        if (pg.puntiFerita <= 0) {
            OutputUtils.print("Sei stato sconfitto da " + nemico.nome + "...");
            OutputUtils.print("GAME OVER");
        } else {
            OutputUtils.print("Hai sconfitto " + nemico.nome + "! Vittoria!");
        }
    }
}