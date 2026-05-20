package com.generation.italy;

import com.generation.italy.fight.Combat;
import com.generation.italy.domain.Inventory;
import com.generation.italy.domain.Player;
import com.generation.italy.domain.Location;
import com.generation.italy.domain.Enemy;
import com.generation.italy.utils.CreatePlayer;
import com.generation.italy.utils.CreateLocation;
import com.generation.italy.utils.CreateEnemy;
import com.generation.italy.utils.OutputUtils;
import com.generation.italy.utils.Dices;
import com.generation.library.Console;

public class Main {

    public static void main(String[] args) {

        // TITOLO DEL GIOCO
        OutputUtils.printTitle();

        // 1. Crea il personaggio
        Player pg = CreatePlayer.crea();

        // 2. Assegna le caratteristiche
        Inventory.assegna(pg);

        // CICLO AVVENTURA DI DUNGEON IN DUNGEON
        boolean continua = true;
        while (continua && pg.puntiFerita > 0) {

            Location loc = CreateLocation.scegli();

            if (pg.classe.equalsIgnoreCase(loc.bonusClasse)) {
                OutputUtils.print("\n*** BONUS! La tua classe " + pg.classe + " ha una grande familiarità con " + loc.nome + "! ***");
            }

            OutputUtils.print("\n=============================================");
            OutputUtils.print(" SEI ENTRATO NEL DUNGEON: " + loc.nome.toUpperCase());
            OutputUtils.print(" Supera le 3 stanze per sconfiggere il Boss!");
            OutputUtils.print("=============================================");

            // CICLO DELLE 3 STANZE
            for (int stanza = 1; stanza <= 3; stanza++) {
                if (pg.puntiFerita <= 0) break;

                OutputUtils.print("\n-------------------------------------------");
                if (stanza < 3) {
                    OutputUtils.print(">>> STANZA " + stanza + ": Mostro Comune <<<");
                    Enemy nemico = CreateEnemy.crea(loc, "normale");

                    // Salviamo i PF iniziali del nemico prima del fight per calcolare il vantaggio
                    int pfInizialiMostro = nemico.puntiFerita;
                    Combat.avvia(pg, nemico, loc, pfInizialiMostro);
                } else {
                    OutputUtils.print(">>> !!! STANZA 3: IL BOSS DEL DUNGEON !!! <<<");
                    Enemy boss = CreateEnemy.crea(loc, "boss");

                    int pfInizialiBoss = boss.puntiFerita;
                    Combat.avvia(pg, boss, loc, pfInizialiBoss);
                }

                // Razzia dell'oro dopo aver vinto
                if (pg.puntiFerita > 0) {
                    int moneteTrovate = Dices.tira(10) + 5;
                    pg.oro += moneteTrovate;
                    OutputUtils.print("Razziando la stanza trovi " + moneteTrovate + " monete d'oro! (Oro totale: " + pg.oro + ")");

                    // Riposo controllato tra stanza 1 e stanza 2
                    if (stanza < 3) {
                        OutputUtils.print("\nPrendi fiato un istante.");
                        pg.riceviCura(3); // <-- Usa il metodo sicuro (Evita la sovracura)

                        System.out.print("Vuoi addentrarti nella prossima stanza del dungeon? (s/n): ");
                        String sceltaAvanti = Console.readString();
                        if (!sceltaAvanti.equalsIgnoreCase("s")) {
                            OutputUtils.print("Scappi terrorizzato dal dungeon abbandonando la missione!");
                            continua = false;
                            break;
                        }
                    }
                }
            } // FINE DUNGEON

            // FASE INTER-DUNGEON: IL MERCANTE
            if (pg.puntiFerita > 0 && continua) {
                OutputUtils.print("\n*********************************************************");
                OutputUtils.print(" IMPRESA COMPIUTA! Hai distrutto il Boss del Dungeon: " + loc.nome + "!");
                OutputUtils.print("*********************************************************");

                OutputUtils.print("\nTorni all'avamposto per riposare e incontri il Mercante.");

                boolean inNegozio = true;
                while (inNegozio) {
                    OutputUtils.print("--- BOTTEGA DEL MERCANTE ---");
                    OutputUtils.print("Oro attuale: " + pg.oro + " | Pozioni possedute: " + pg.numeroPozioni + " | PF: " + pg.puntiFerita + "/" + pg.puntiFeritaMax);
                    OutputUtils.print("1. Compra Pozione di Cura (Costo: 20 monete)");
                    OutputUtils.print("2. Lascia il negozio e pianifica il prossimo Dungeon");
                    System.out.print("Cosa vuoi fare? ");
                    int sceltaMercante = Console.readInt();

                    if (sceltaMercante == 1) {
                        if (pg.oro >= 20) {
                            pg.oro -= 20;
                            pg.numeroPozioni++;
                            OutputUtils.print("Hai comprato una pozione di cura!");
                        } else {
                            OutputUtils.print("\"Niente oro, niente merci amico!\"");
                        }
                    } else {
                        OutputUtils.print("Ti prepari a consultare le mappe...");
                        inNegozio = false;
                    }
                    OutputUtils.print();
                }

                System.out.print("Sei pronto a metterti in viaggio verso una NUOVA LOCATION? (s/n): ");
                String risposta = Console.readString();
                continua = risposta.equalsIgnoreCase("s");
            }
        }

        // FINE GIOCO ASSOLUTO
        if (pg.puntiFerita <= 0) {
            OutputUtils.print("\nLa tua avventura finisce qui... Le canzoni ricorderanno il tuo sacrificio.");
        } else {
            OutputUtils.print("\nHai deciso di ritirarti dalle leggende. Ti godi le tue " + pg.oro + " monete d'oro in taverna!");
        }
    }
}