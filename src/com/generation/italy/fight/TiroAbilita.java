package com.generation.italy.fight;

import com.generation.italy.utils.Dices;
import com.generation.italy.utils.OutputUtils;
import com.generation.library.*;

// TIRO ABILITA' - gestisce il tiro di abilita' con vantaggio/svantaggio
public class TiroAbilita {

    public static void esegui(int modificatore) {

        OutputUtils.print("--- Tiro di abilita' ---");

        int tiroBase = lanciaD20();

        if (tiroBase == 1) {
            // FUMBLE - fallimento automatico, modificatore non applicato
            OutputUtils.print("*** FUMBLE! ***");
            int effetto = Dices.tira(3);
            if (effetto == 1) OutputUtils.print("FALLIMENTO TOTALE!");
            if (effetto == 2) OutputUtils.print("Fallimento Critico!");
            if (effetto == 3) OutputUtils.print("Fallimento critico");
        } else {
            // CRITICO O NORMALE - applico il modificatore
            if (tiroBase == 20) OutputUtils.print("*** CRITICO! ***");
            int tiroFinale = tiroBase + modificatore;
            OutputUtils.print("Tiro finale: " + tiroBase + " + mod(" + modificatore + ") = " + tiroFinale);
        }
    }

    // LANCIA D20 - gestisce vantaggio/svantaggio e restituisce il tiroBase
    public static int lanciaD20() {
        System.out.print("Hai vantaggio, svantaggio, o nessuno dei due? (v/s/no): ");
        String vs = Console.readString();

        int tiroBase;
        if (vs.equals("v")) {
            int dado1 = Dices.tira(20);
            int dado2 = Dices.tira(20);
            OutputUtils.print("Dadi: " + dado1 + " e " + dado2);
            tiroBase = dado1 > dado2 ? dado1 : dado2;
            OutputUtils.print("Tieni il piu' alto: " + tiroBase);
        } else if (vs.equals("s")) {
            int dado1 = Dices.tira(20);
            int dado2 = Dices.tira(20);
            OutputUtils.print("Dadi: " + dado1 + " e " + dado2);
            tiroBase = dado1 < dado2 ? dado1 : dado2;
            OutputUtils.print("Tieni il piu' basso: " + tiroBase);
        } else {
            tiroBase = Dices.tira(20);
            OutputUtils.print("D20: " + tiroBase);
        }
        return tiroBase;
    }
}