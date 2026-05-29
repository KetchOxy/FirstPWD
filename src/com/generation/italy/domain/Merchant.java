package com.generation.italy.domain;

import com.generation.italy.utils.OutputUtils;
import com.generation.library.Console;

/**
 * MERCHANT - Bottega tra un dungeon e il prossimo.
 */
public class Merchant {

    public static void gestisciBottega(Player pg) {
        OutputUtils.print("\nTorni all'avamposto. Il mercante ti saluta.");

        boolean inNegozio = true;
        while (inNegozio) {
            OutputUtils.print("\n--- BOTTEGA DEL MERCANTE ---");
            OutputUtils.print("Oro: " + pg.getOro() + " | Pozioni: " + pg.getNumeroPozioni() + " | PF: " + pg.getPuntiFerita() + "/" + pg.getPuntiFeritaMax());
            OutputUtils.print("1. Compra Pozione di Cura (20 mo)");
            OutputUtils.print("2. Esci dal negozio");
            System.out.print("Scelta: ");
            int scelta = Console.readInt();

            if (scelta == 1) {
                if (pg.getOro() >= 20) {
                    pg.rimuoviOro(20);
                    pg.aggiungiPozione();
                    OutputUtils.print("Pozione acquistata! Pozioni totali: " + pg.getNumeroPozioni());
                } else {
                    OutputUtils.print("\"Niente oro, niente merci, amico!\"");
                }
            } else if (scelta == 2) {
                inNegozio = false;
                OutputUtils.print("Consulti le mappe per il prossimo viaggio...");
            } else {
                OutputUtils.print("Il mercante ti guarda confuso.");
            }
        }
    }
}