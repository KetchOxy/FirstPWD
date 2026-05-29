package com.generation.italy.fight;

import com.generation.italy.domain.Player;
import com.generation.italy.utils.Dices;
import com.generation.italy.utils.OutputUtils;
import com.generation.italy.utils.Weapons;

/**
 * TIRO PER COLPIRE - Gestisce l'attacco del giocatore con vantaggio/svantaggio automatico.
 */
public class TiroColpire {

    public static int esegui(int modificatore, Player pg, int caNemico, String statoTiro) {
        OutputUtils.print("--- Tiro per Colpire ---");

        int tiroBase;

        if (statoTiro.equals("v")) {
            int d1 = Dices.tira(20), d2 = Dices.tira(20);
            OutputUtils.print("VANTAGGIO: " + d1 + " e " + d2);
            tiroBase = Math.max(d1, d2);
            OutputUtils.print("Tieni il più alto: " + tiroBase);
        } else if (statoTiro.equals("s")) {
            int d1 = Dices.tira(20), d2 = Dices.tira(20);
            OutputUtils.print("SVANTAGGIO: " + d1 + " e " + d2);
            tiroBase = Math.min(d1, d2);
            OutputUtils.print("Tieni il più basso: " + tiroBase);
        } else {
            tiroBase = Dices.tira(20);
            OutputUtils.print("Dado: " + tiroBase);
        }

        if (tiroBase == 1) {
            OutputUtils.print("*** FUMBLE! Hai mancato clamorosamente! ***");
            int effetto = Dices.tira(3);
            if (effetto == 1) OutputUtils.print("Colpisci te stesso! -1 PF");
            if (effetto == 2) OutputUtils.print("Perdi l'arma per un turno!");
            if (effetto == 3) OutputUtils.print("Sei caduto a terra!");
            return 0;
        }

        if (tiroBase == 20) OutputUtils.print("*** COLPO CRITICO! ***");

        int totale = tiroBase + modificatore;
        OutputUtils.print("Totale: " + tiroBase + " + mod(" + modificatore + ") = " + totale + " vs CA " + caNemico);

        if (totale < caNemico && tiroBase != 20) {
            OutputUtils.print("Hai mancato!");
            return 0;
        }

        OutputUtils.print("Hai colpito! [" + pg.getArma() + "]");
        int danno = Weapons.tiraDannoArma(pg.getArma());
        if (tiroBase == 20) {
            danno *= 2;
            OutputUtils.print("Danno critico raddoppiato: " + danno);
        }
        return danno;
    }
}