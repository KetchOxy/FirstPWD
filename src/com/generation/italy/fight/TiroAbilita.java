package com.generation.italy.fight;

import com.generation.italy.utils.Dices;
import com.generation.italy.utils.OutputUtils;

/**
 * TIRO ABILITA' - Tiro d20 contro una Classe di Difficoltà.
 */
public class TiroAbilita {

    public static boolean eseguiControCD(int modificatore, int cd) {
        OutputUtils.print("--- Tiro di Abilità (CD " + cd + ") ---");

        int tiro = Dices.tira(20);
        OutputUtils.print("Dado: " + tiro);

        if (tiro == 1) {
            OutputUtils.print("*** FUMBLE! Fallimento automatico! ***");
            return false;
        } else if (tiro == 20) {
            OutputUtils.print("*** CRITICO! Successo automatico! ***");
            return true;
        }

        int totale = tiro + modificatore;
        OutputUtils.print("Totale: " + tiro + " + mod(" + modificatore + ") = " + totale + " vs CD " + cd);

        if (totale >= cd) {
            OutputUtils.print("[SUCCESSO]");
            return true;
        } else {
            OutputUtils.print("[FALLIMENTO]");
            return false;
        }
    }
}