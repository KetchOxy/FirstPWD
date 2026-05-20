package com.generation.italy.fight;

import com.generation.italy.utils.Dices;
import com.generation.italy.utils.OutputUtils;

public class TiroAbilita {

    // Esegue il tiro e restituisce true se supera la CD, altrimenti false
    public static boolean eseguiControCD(int modificatore, int cd) {

        OutputUtils.print("--- Tiro di Abilita' (Contro CD " + cd + ") ---");

        int tiroBase = Dices.tira(20);
        OutputUtils.print("Lancio del dado base: " + tiroBase);

        if (tiroBase == 1) {
            OutputUtils.print("*** FUMBLE! Fallimento automatico! ***");
            return false;
        } else if (tiroBase == 20) {
            OutputUtils.print("*** CRITICO! Successo automatico spettacolare! ***");
            return true;
        } else {
            int tiroFinale = tiroBase + modificatore;
            OutputUtils.print("Tiro finale: " + tiroBase + " + mod(" + modificatore + ") = " + tiroFinale);

            if (tiroFinale >= cd) {
                OutputUtils.print("[SUCCESSO] Hai superato la prova!");
                return true;
            } else {
                OutputUtils.print("[FALLIMENTO] Non ci sei riuscito...");
                return false;
            }
        }
    }
}