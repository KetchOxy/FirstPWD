package com.generation.italy.fight;

import com.generation.italy.domain.Player;
import com.generation.italy.utils.Dices;
import com.generation.italy.utils.OutputUtils;
import com.generation.italy.utils.Weapons;
import com.generation.library.*;

// TIRO COLPIRE - gestisce il tiro per colpire, CA e dadi danno
public class TiroColpire {

    public static void esegui(int modificatore, Player pg) {

        OutputUtils.print("--- Tiro per colpire ---");

        int tiroBase = TiroAbilita.lanciaD20();

        if (tiroBase == 1) {
            // FUMBLE - mancato automatico, modificatore non applicato
            OutputUtils.print("*** FUMBLE! Hai mancato! ***");
            int effetto = Dices.tira(3);
            if (effetto == 1) OutputUtils.print("Hai colpito te stesso!");
            if (effetto == 2) OutputUtils.print("Hai perso l'arma!");
            if (effetto == 3) OutputUtils.print("Sei caduto a terra!");
        } else {
            if (tiroBase == 20) OutputUtils.print("*** CRITICO! ***");

            int tiroFinale = tiroBase + modificatore;
            OutputUtils.print("Tiro finale: " + tiroBase + " + mod(" + modificatore + ") = " + tiroFinale);

            System.out.print("Classe Armatura del nemico? ");
            int ca = Console.readInt();

            if (tiroFinale < ca && tiroBase != 20) {
                OutputUtils.print("Hai mancato! CA nemica: " + ca);
            } else {
                OutputUtils.print("Hai colpito! Arma: " + pg.arma);
                int danno = Weapons.tiraDannoArma(pg.arma);

                if (tiroBase == 20) {
                    danno = danno * 2;
                    OutputUtils.print("*** Danno raddoppiato: " + danno + " ***");
                }

                OutputUtils.print("Totale danno: " + danno);
            }
        }
    }
}