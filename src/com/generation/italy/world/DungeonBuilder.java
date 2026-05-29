package com.generation.italy.world;

import com.generation.italy.domain.Enemy;
import com.generation.italy.domain.Item;
import com.generation.italy.utils.EnemyFactory;

/**
 * DUNGEON BUILDER - Genera dungeon con mappa ramificata.
 * Ogni dungeon ha un tema (nome nemico principale) e produce
 * una griglia di stanze collegate N/S/E/W con contenuti variati.
 *
 * Layout fisso ma con contenuti randomizzati in base al tema:
 *
 *         [SICURA]
 *            |N
 *         [TESORO] - E - [COMBATTIMENTO]
 *            |N
 *         [INGRESSO/START]
 *            |N
 *         [TRAPPOLA] - E - [COMBATTIMENTO]
 *            |N
 *         [COMBATTIMENTO] - E - [TESORO]
 *            |N
 *          [BOSS]
 */
public class DungeonBuilder {

    public static Room costruisci(String nomeDungeon, String nomeNemico, String bonusClasse) {

        // --- STANZA DI INGRESSO ---
        Room ingresso = new Room(
                "Ingresso: " + nomeDungeon,
                "Varcate le porte di " + nomeDungeon + ". L'aria è pesante. Scegliete bene la direzione.",
                Room.TipoStanza.INGRESSO
        );

        // --- CORRIDOIO NORD 1 (Trappola) ---
        Room trappola = new Room(
                "Corridoio delle Lame",
                "Senti un click sotto lo stivale. Lame retrattili spuntano dai muri!\nUsa [F]orza, [D]estrezza o [C]ostituzione per superarla.",
                Room.TipoStanza.TRAPPOLA
        );

        // --- DIRAMAZIONE EST 1 (Combattimento) ---
        Room combEst1 = new Room(
                "Sala delle Guardie",
                "Un " + nomeNemico + " di guardia ti blocca la strada!",
                Room.TipoStanza.COMBATTIMENTO
        );
        combEst1.aggiungiNemico(EnemyFactory.crea(nomeNemico, false));
        combEst1.aggiungiOggetto(new Item("Monete sparse", 0.1, 8));

        // --- CORRIDOIO NORD 2 (Combattimento) ---
        Room comb1 = new Room(
                "Sala dei Guardiani",
                "Due " + nomeNemico + "i presidiano questa sala. Non passi senza combattere.",
                Room.TipoStanza.COMBATTIMENTO
        );
        comb1.aggiungiNemico(EnemyFactory.crea(nomeNemico, false));

        // --- DIRAMAZIONE EST 2 (Tesoro) ---
        Room tesoroEst = new Room(
                "Stanza del Bottino",
                "Una stanza laterale nascosta. Polvere e ragnatele... ma anche qualcosa di prezioso.",
                Room.TipoStanza.TESORO
        );
        tesoroEst.aggiungiOggetto(new Item("Borsa d'oro", 1.0, 25));
        tesoroEst.aggiungiOggetto(new Item("Pozione di Cura", 0.3, 20));

        // --- SALA TESORO PRINCIPALE ---
        Room tesoro = new Room(
                "Sala del Tesoro Perduto",
                "Le pareti luccicano di gemme incastonate. Ma qualcuno sorveglia questo luogo...",
                Room.TipoStanza.TESORO
        );
        tesoro.aggiungiNemico(EnemyFactory.crea(nomeNemico, false));
        tesoro.aggiungiOggetto(new Item("Gemma Preziosa", 0.5, 40));

        // --- STANZA SICURA (riposo) ---
        Room sicura = new Room(
                "Cappella Abbandonata",
                "Un vecchio santuario. L'energia sacra respinge i mostri. Puoi rifiatare qui.",
                Room.TipoStanza.SICURA
        );
        sicura.aggiungiOggetto(new Item("Erbe Curative", 0.2, 5));

        // --- SALA DEL BOSS ---
        Room boss = new Room(
                "Trono di " + nomeDungeon,
                "Le porte si spalancano su un'enorme sala. Al centro del trono ti fissa il Boss.\n" +
                        "*** " + nomeNemico.toUpperCase() + " SUPREMO ***",
                Room.TipoStanza.BOSS
        );
        boss.aggiungiNemico(EnemyFactory.crea(nomeNemico, true));
        boss.aggiungiOggetto(new Item("Tesoro del Boss", 2.0, 60));

        // --- COLLEGA LE STANZE ---
        // Verticale principale: ingresso → trappola → comb1 → boss
        ingresso.aggiungiUscita(trappola,  Room.NORTH);
        trappola.aggiungiUscita(ingresso,  Room.SOUTH);
        trappola.aggiungiUscita(comb1,     Room.NORTH);
        comb1.aggiungiUscita(trappola,     Room.SOUTH);
        comb1.aggiungiUscita(boss,         Room.NORTH);
        boss.aggiungiUscita(comb1,         Room.SOUTH);

        // Diramazioni est dalla trappola e da comb1
        trappola.aggiungiUscita(combEst1,  Room.EAST);
        combEst1.aggiungiUscita(trappola,  Room.WEST);

        comb1.aggiungiUscita(tesoroEst,    Room.EAST);
        tesoroEst.aggiungiUscita(comb1,    Room.WEST);

        // Diramazione nord dal tesoro principale → sala sicura
        tesoro.aggiungiUscita(sicura,      Room.NORTH);
        sicura.aggiungiUscita(tesoro,      Room.SOUTH);
        // tesoro accessibile da combEst1 andando nord
        combEst1.aggiungiUscita(tesoro,    Room.NORTH);
        tesoro.aggiungiUscita(combEst1,    Room.SOUTH);

        return ingresso; // restituisce il punto di partenza
    }
}
