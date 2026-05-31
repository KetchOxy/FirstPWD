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
    public String bonusClasse;
    public boolean bonusColpire;
    public boolean bonusDanno;
    public Enemy nemico;
    public Enemy boss;

    public enum RisultatoDungeon { COMPLETATO, RITIRATO, MORTO }

    public RisultatoDungeon eseguiDungeon(Player pg) {
        OutputUtils.print("\n=============================================");
        OutputUtils.print(" SEI ENTRATO NEL DUNGEON: " + this.nome.toUpperCase());
        OutputUtils.print(" " + this.descrizione);
        OutputUtils.print(" Supera le stanze per sconfiggere il Boss!");
        OutputUtils.print("=============================================");

        // INVENTARIO — solo all'inizio, prima della stanza 1
        apriInventarioSeRichiesto(pg, "prima di entrare nel dungeon");

        for (int stanza = 1; stanza <= 3; stanza++) {
            if (pg.getPuntiFerita() <= 0) return RisultatoDungeon.MORTO;

            OutputUtils.print("\n-------------------------------------------");

            if (stanza == 1) {
                OutputUtils.print(">>> STANZA 1: Mostro Comune <<<");
                Enemy nemicoStanza = CreateEnemy.crea(this, "normale");
                Combat.avvia(pg, nemicoStanza, this, nemicoStanza.getCurrentHp());

            } else if (stanza == 2) {
                RoomEvent.gestisciTrappola(pg);

            } else {
                OutputUtils.print(">>> !!! STANZA 3: IL BOSS DEL DUNGEON !!! <<<");
                Enemy bossStanza = CreateEnemy.crea(this, "boss");
                Combat.avvia(pg, bossStanza, this, bossStanza.getCurrentHp());
            }

            if (pg.getPuntiFerita() <= 0) return RisultatoDungeon.MORTO;

            int moneteTrovate = Dices.tira(10) + 5;
            pg.oro += moneteTrovate;
            OutputUtils.print("Trovi " + moneteTrovate + " monete d'oro nascoste nella stanza! (Oro totale: " + pg.oro + ")");

            if (stanza < 3) {
                OutputUtils.print("\nPrendi fiato un istante prima di proseguire.");
                pg.riceviCura(3);

                // INVENTARIO — dopo il combattimento, prima di avanzare
                apriInventarioSeRichiesto(pg, "prima di proseguire");

                System.out.print("Vuoi continuare ad addentrarti nel dungeon? (s/n): ");
                String sceltaAvanti = Console.readString();
                if (!sceltaAvanti.equalsIgnoreCase("s")) {
                    OutputUtils.print("Decidi di ritirarti dal dungeon. Torni all'avamposto.");
                    return RisultatoDungeon.RITIRATO;
                }
            }
        }
        return RisultatoDungeon.COMPLETATO;
    }

    private void apriInventarioSeRichiesto(Player pg, String momento) {
        System.out.print("\nVuoi aprire l'inventario " + momento + "? (s/n): ");
        String risposta = Console.readString();
        if (risposta.equalsIgnoreCase("s")) {
            pg.gestisciInventario();
        }
    }
}