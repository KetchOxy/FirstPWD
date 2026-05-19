package com.generation.italy.fight;

import com.generation.italy.*;
import com.generation.italy.domain.Player;
import com.generation.italy.utils.Dices;
import com.generation.italy.utils.Weapons;
import com.generation.library.*;

// TIRO COLPIRE - gestisce il tiro per colpire, CA e dadi danno
public class TiroColpire {

    // RICEVE IL PERSONAGGIO per leggere l'arma equipaggiata
    public static void esegui(int modificatore, Player pg) {

        System.out.println("--- Tiro per colpire ---");

        // LANCIO D20 - riutilizzo il metodo di Tiroabilita
        int tiroBase = TiroAbilita.lanciaD20();

        if (tiroBase == 1) {
            // FUMBLE - mancato automatico, modificatore non applicato
            System.out.println("*** FUMBLE! Hai mancato! ***");
            int effetto = Dices.tira(3);
            if (effetto == 1) System.out.println("Hai colpito te stesso!");
            if (effetto == 2) System.out.println("Hai perso l'arma!");
            if (effetto == 3) System.out.println("Sei caduto a terra!");
        } else {
            if (tiroBase == 20) {
                // CRITICO - colpisce sempre indipendentemente dalla CA
                System.out.println("*** CRITICO! ***");
            }

            // APPLICO IL MODIFICATORE
            int tiroFinale = tiroBase + modificatore;
            System.out.println("Tiro finale: " + tiroBase + " + mod(" + modificatore + ") = " + tiroFinale);

            // CONFRONTO CON LA CLASSE ARMATURA
            System.out.print("Classe Armatura del nemico? ");
            int ca = Console.readInt();

            if (tiroFinale < ca && tiroBase != 20) {
                System.out.println("Hai mancato! CA nemica: " + ca);
            } else {

                // COLPO ANDATO A SEGNO - usa l'arma del personaggio
                System.out.println("Hai colpito! Arma: " + pg.arma);
                int danno = Weapons.tiraDannoArma(pg.arma);

                // SE CRITICO - il danno viene raddoppiato
                if (tiroBase == 20) {
                    danno = danno * 2;
                    System.out.println("*** Danno raddoppiato: " + danno + " ***");
                }

                System.out.println("Totale danno: " + danno);
            }
        }
    }
}
