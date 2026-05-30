package com.generation.italy.fight;

import com.generation.italy.domain.Player;
import com.generation.italy.utils.Dices;
import com.generation.italy.utils.OutputUtils;
import com.generation.italy.utils.Weapons;

public class TiroColpire {

    // METODO AGGIORNATO: Riceve lo stato del tiro calcolato automaticamente dalle ferite
    public static int eseguiAutomatico(int modificatore, Player pg, int caNemico, String statoTiro) {

        OutputUtils.print("--- Tiro per colpire ---");

        int tiroBase;

        if (statoTiro.equals("v")) {
            int dado1 = Dices.tira(20);
            int dado2 = Dices.tira(20);
            OutputUtils.print("Lancio con VANTAGGIO. Dadi: " + dado1 + " e " + dado2);
            tiroBase = dado1 > dado2 ? dado1 : dado2;
            OutputUtils.print("Tieni il piu' alto: " + tiroBase);
        } else if (statoTiro.equals("s")) {
            int dado1 = Dices.tira(20);
            int dado2 = Dices.tira(20);
            OutputUtils.print("Lancio con SVANTAGGIO. Dadi: " + dado1 + " e " + dado2);
            tiroBase = dado1 < dado2 ? dado1 : dado2;
            OutputUtils.print("Tieni il piu' basso: " + tiroBase);
        } else {
            tiroBase = Dices.tira(20);
            OutputUtils.print("Lancio Normale. Dado: " + tiroBase);
        }

        if (tiroBase == 1) {
            OutputUtils.print("*** FUMBLE! Hai mancato! ***");
            int effetto = Dices.tira(3);
            if (effetto == 1) OutputUtils.print("Hai colpito te stesso!");
            if (effetto == 2) OutputUtils.print("Hai perso l'arma!");
            if (effetto == 3) OutputUtils.print("Sei caduto a terra!");
            return 0;
        } else {
            if (tiroBase == 20) OutputUtils.print("*** CRITICO! ***");

            int tiroFinale = tiroBase + modificatore;
            OutputUtils.print("Tiro finale: " + tiroBase + " + mod(" + modificatore + ") = " + tiroFinale);
            OutputUtils.print("CA nemica: " + caNemico);

            if (tiroFinale < caNemico && tiroBase != 20) {
                OutputUtils.print("Hai mancato!");
                return 0;
            } else {
                OutputUtils.print("Hai colpito! Arma: " + pg.getArma());
                int danno = Weapons.tiraDannoArma(pg.getArma());

                if (tiroBase == 20) {
                    danno = danno * 2;
                    OutputUtils.print("*** Danno raddoppiato dal Critico! ***");
                }
                return danno;
            }
        }
    }
}