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

    // ── Risultato dungeon con stanza salvata ─────────────────────────────────

    public static class RisultatoDungeon {
        public enum Stato { COMPLETATO, RITIRATO, MORTO, MERCANTE }
        public Stato stato;
        public int prossimaStanza;

        public RisultatoDungeon(Stato stato, int prossimaStanza) {
            this.stato = stato;
            this.prossimaStanza = prossimaStanza;
        }
    }

    // ── Esegui dungeon dalla stanza indicata ─────────────────────────────────

    public RisultatoDungeon eseguiDungeon(Player pg, int stanzaDaRiprendere) {
        OutputUtils.print("\n=============================================");
        OutputUtils.print(" SEI ENTRATO NEL DUNGEON: " + this.nome.toUpperCase());
        OutputUtils.print(" " + this.descrizione);
        OutputUtils.print(" Supera le stanze per sconfiggere il Boss!");
        OutputUtils.print("=============================================");

        apriInventarioSeRichiesto(pg, "prima di entrare nel dungeon");

        for (int stanza = stanzaDaRiprendere; stanza <= 3; stanza++) {
            if (pg.getPuntiFerita() <= 0) {
                return new RisultatoDungeon(RisultatoDungeon.Stato.MORTO, stanza);
            }

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

            if (pg.getPuntiFerita() <= 0) {
                return new RisultatoDungeon(RisultatoDungeon.Stato.MORTO, stanza);
            }

            int moneteTrovate = Dices.tira(10) + 5;
            pg.oro += moneteTrovate;
            OutputUtils.print("Trovi " + moneteTrovate + " monete d'oro! (Oro totale: " + pg.oro + ")");

            if (stanza < 3) {
                OutputUtils.print("\nPrendi fiato un istante prima di proseguire.");
                OutputUtils.print("PF attuali: " + pg.getPuntiFerita() + "/" + pg.getPuntiFeritaMax());

                apriInventarioSeRichiesto(pg, "prima di proseguire");

                OutputUtils.print("Cosa vuoi fare?");
                OutputUtils.print("  S - Continua nel dungeon");
                OutputUtils.print("  M - Vai dal Mercante (tornerai alla stanza " + (stanza + 1) + ")");
                OutputUtils.print("  N - Ritirati dal dungeon");
                System.out.print("-> ");
                String sceltaAvanti = Console.readString();

                if (sceltaAvanti.equalsIgnoreCase("m")) {
                    OutputUtils.print("Torni all'avamposto dal mercante...");
                    return new RisultatoDungeon(RisultatoDungeon.Stato.MERCANTE, stanza + 1);
                } else if (!sceltaAvanti.equalsIgnoreCase("s")) {
                    OutputUtils.print("Decidi di ritirarti dal dungeon. Torni all'avamposto.");
                    return new RisultatoDungeon(RisultatoDungeon.Stato.RITIRATO, 1);
                }
            }
        }
        return new RisultatoDungeon(RisultatoDungeon.Stato.COMPLETATO, 1);
    }

    private void apriInventarioSeRichiesto(Player pg, String momento) {
        System.out.print("\nVuoi aprire l'inventario " + momento + "? (s/n): ");
        String risposta = Console.readString();
        if (risposta.equalsIgnoreCase("s")) {
            pg.gestisciInventario();
        }
    }
}