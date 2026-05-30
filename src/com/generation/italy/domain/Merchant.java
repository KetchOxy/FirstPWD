package com.generation.italy.domain;

import com.generation.italy.utils.OutputUtils;
import com.generation.library.Console;

public class Merchant {

    public static void gestisciBottega(Player pg) {
        OutputUtils.print("Torni all'avamposto per riposare e incontri il Mercante.");

        boolean inNegozio = true;
        while (inNegozio) {
            OutputUtils.print("--- BOTTEGA DEL MERCANTE ---");
            OutputUtils.print("Oro attuale: " + pg.oro + " | Pozioni possedute: " + pg.numeroPozioni + " | PF: " + pg.getPuntiFerita() + "/" + pg.getPuntiFeritaMax());
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
            } else if (sceltaMercante == 2) {
                OutputUtils.print("Ti prepari a consultare le mappe...");
                inNegozio = false;
            } else {
                OutputUtils.print("Scelta non valida! Il mercante ti guarda confuso.");
            }
            OutputUtils.print();
        }
    }
}