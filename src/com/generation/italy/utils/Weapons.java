package com.generation.italy.utils;

import com.generation.library.Console;

/**
 * WEAPONS - Tira i dadi danno per ogni arma D&D 2024.
 */
public class Weapons {

    public static int tiraDannoArma(String arma) {
        switch (arma.toLowerCase()) {
            case "pugnale":        return danno1d(4, "D4");
            case "scimitarra":     return danno1d(6, "D6");
            case "spadacorta":     return danno1d(6, "D6");
            case "flagello":       return danno1d(8, "D8");
            case "picconeduerra":  return danno1d(8, "D8");
            case "stocco":         return danno1d(8, "D8");
            case "alabarda":       return danno1d(10, "D10");
            case "asciaguerra":    return dannoVersatile("ascia", 8, 10);
            case "lancia":         return dannoVersatile("lancia", 6, 8);
            case "spadalunga":     return dannoVersatile("spada", 8, 10);
            case "martelloguerra": return dannoVersatile("martello", 8, 10);
            case "spadone":        return danno2d(6, "2D6");
            case "falcione":       return danno2d(6, "2D6");
            default:               return danno1d(6, "D6"); // fallback
        }
    }

    private static int danno1d(int facce, String label) {
        int r = Dices.tira(facce);
        OutputUtils.print("Dado danno [" + label + "]: " + r);
        return r;
    }

    private static int danno2d(int facce, String label) {
        int d1 = Dices.tira(facce);
        int d2 = Dices.tira(facce);
        OutputUtils.print("Dadi danno [" + label + "]: " + d1 + " + " + d2 + " = " + (d1 + d2));
        return d1 + d2;
    }

    private static int dannoVersatile(String nomeArma, int facce1, int facce2) {
        System.out.print("Usi " + nomeArma + " a una o due mani? (1/2): ");
        int mani = Console.readInt();
        if (mani == 2) return danno1d(facce2, "D" + facce2);
        else           return danno1d(facce1, "D" + facce1);
    }

    public static boolean armaValida(String arma) {
        return arma.equalsIgnoreCase("Pugnale")        ||
                arma.equalsIgnoreCase("Scimitarra")     ||
                arma.equalsIgnoreCase("SpadaCorta")     ||
                arma.equalsIgnoreCase("AsciaGuerra")    ||
                arma.equalsIgnoreCase("Flagello")       ||
                arma.equalsIgnoreCase("Lancia")         ||
                arma.equalsIgnoreCase("SpadaLunga")     ||
                arma.equalsIgnoreCase("MartelloGuerra") ||
                arma.equalsIgnoreCase("PicconeGuerra")  ||
                arma.equalsIgnoreCase("Stocco")         ||
                arma.equalsIgnoreCase("Alabarda")       ||
                arma.equalsIgnoreCase("Spadone")        ||
                arma.equalsIgnoreCase("Falcione");
    }
}