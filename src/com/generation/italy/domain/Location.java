package com.generation.italy.domain;

import com.generation.italy.fight.Combat;
import com.generation.italy.utils.CreateEnemy;
import com.generation.italy.utils.Dices;
import com.generation.italy.utils.OutputUtils;
import com.generation.italy.utils.RoomEvent;
import com.generation.library.Console;

public class Location {
    public String nome;
    public String descrizione;
    public String nomeNemico;
    public String bonusClasse;   // classe che riceve il bonus in questa location
    public boolean bonusColpire; // vantaggio al tiro per colpire
    public boolean bonusDanno;   // bonus ai danni

    // Metodo che gestisce l'intero loop interno del dungeon (le 3 stanze)
    public void eseguiDungeon(Player pg) {
        OutputUtils.print("\n=============================================");
        OutputUtils.print(" SEI ENTRATO NEL DUNGEON: " + this.nome.toUpperCase());
        OutputUtils.print(" Supera le stanze per sconfiggere il Boss!");
        OutputUtils.print("=============================================");

        for (int stanza = 1; stanza <= 3; stanza++) {
            if (pg.getPuntiFerita() <= 0) break;

            // INVENTARIO — sempre disponibile prima di ogni stanza
            OutputUtils.print("\nVuoi aprire l'inventario prima di entrare? (s/n): ");
            String apriInv = Console.readString();
            if (apriInv.equalsIgnoreCase("s")) {
                pg.mostraInventario();
            }

            OutputUtils.print("\n-------------------------------------------");

            if (stanza == 1) {
                OutputUtils.print(">>> STANZA 1: Mostro Comune <<<");
                Enemy nemico = CreateEnemy.crea(this, "normale");
                int pfInizialiMostro = nemico.puntiFerita;
                Combat.avvia(pg, nemico, this, pfInizialiMostro);

            } else if (stanza == 2) {
                RoomEvent.gestisciTrappola(pg);

            } else {
                OutputUtils.print(">>> !!! STANZA 3: IL BOSS DEL DUNGEON !!! <<<");
                Enemy boss = CreateEnemy.crea(this, "boss");
                int pfInizialiBoss = boss.puntiFerita;
                Combat.avvia(pg, boss, this, pfInizialiBoss);
            }

            // Ricompensa in oro e riposo intermedio (Gestito all'interno della stanza)
            if (pg.getPuntiFerita() > 0) {
                int moneteTrovate = Dices.tira(10) + 5;
                pg.oro += moneteTrovate;
                OutputUtils.print("Trovi comunque " + moneteTrovate + " monete d'oro nascoste nella stanza! (Oro totale: " + pg.oro + ")");

                if (stanza < 3) {
                    OutputUtils.print("\nPrendi fiato un istante prima di proseguire.");
                    pg.riceviCura(3);

                    System.out.print("Vuoi continuare ad addentrarti nel dungeon? (s/n): ");
                    String sceltaAvanti = Console.readString();
                    if (!sceltaAvanti.equalsIgnoreCase("s")) {
                        OutputUtils.print("Scappi terrorizzato dal dungeon abbandonando la missione!");
                        pg.setCurrentHp(0); // Escamotage per interrompere l'avventura o puoi gestire un flag
                        break;
                    }
                }
            }
        }
    }
}
