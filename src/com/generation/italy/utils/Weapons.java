package com.generation.italy.utils;

import com.generation.italy.*;
import com.generation.library.*;

// ARMI - contiene i metodi per ogni arma marziale da mischia D&D 2024
public class Weapons {

    // --- ARMI CON D4 ---
    public static int pugnale() {
        int risultato = Dices.tira(4);
        System.out.println("Dadi: D4: " + risultato);
        return risultato;
    }

    // --- ARMI CON D6 ---
    public static int scimitarra() {
        int risultato = Dices.tira(6);
        System.out.println("Dadi: D6: " + risultato);
        return risultato;
    }

    public static int spadaCorta() {
        int risultato = Dices.tira(6);
        System.out.println("Dadi: D6: " + risultato);
        return risultato;
    }

    // --- ARMI VERSATILI ---
    public static int asciaGuerra() {
        System.out.print("Usi l'ascia a una mano o due mani? (1/2): ");
        int mani = Console.readInt();
        int risultato;
        if (mani == 2) {
            risultato = Dices.tira(10);
            System.out.println("Dadi: D10: " + risultato);
        } else {
            risultato = Dices.tira(8);
            System.out.println("Dadi: D8: " + risultato);
        }
        return risultato;
    }

    public static int lancia() {
        System.out.print("Usi la lancia a una mano o due mani? (1/2): ");
        int mani = Console.readInt();
        int risultato;
        if (mani == 2) {
            risultato = Dices.tira(8);
            System.out.println("Dadi: D8: " + risultato);
        } else {
            risultato = Dices.tira(6);
            System.out.println("Dadi: D6: " + risultato);
        }
        return risultato;
    }

    public static int spadaLunga() {
        System.out.print("Usi la spada a una mano o due mani? (1/2): ");
        int mani = Console.readInt();
        int risultato;
        if (mani == 2) {
            risultato = Dices.tira(10);
            System.out.println("Dadi: D10: " + risultato);
        } else {
            risultato = Dices.tira(8);
            System.out.println("Dadi: D8: " + risultato);
        }
        return risultato;
    }

    public static int martelloGuerra() {
        System.out.print("Usi il martello a una mano o due mani? (1/2): ");
        int mani = Console.readInt();
        int risultato;
        if (mani == 2) {
            risultato = Dices.tira(10);
            System.out.println("Dadi: D10: " + risultato);
        } else {
            risultato = Dices.tira(8);
            System.out.println("Dadi: D8: " + risultato);
        }
        return risultato;
    }

    // --- ARMI CON D8 FISSO ---
    public static int flagello() {
        int risultato = Dices.tira(8);
        System.out.println("Dadi: D8: " + risultato);
        return risultato;
    }

    public static int picconeGuerra() {
        int risultato = Dices.tira(8);
        System.out.println("Dadi: D8: " + risultato);
        return risultato;
    }

    public static int stocco() {
        int risultato = Dices.tira(8);
        System.out.println("Dadi: D8: " + risultato);
        return risultato;
    }

    // --- ARMI CON D10 ---
    public static int alabarda() {
        int risultato = Dices.tira(10);
        System.out.println("Dadi: D10: " + risultato);
        return risultato;
    }

    public static int picca() {
        int risultato = Dices.tira(10);
        System.out.println("Dadi: D10: " + risultato);
        return risultato;
    }

    public static int lanciaGiostra() {
        int risultato = Dices.tira(10);
        System.out.println("Dadi: D10: " + risultato);
        return risultato;
    }

    // --- ARMI CON D12 ---
    public static int asciaDueMani() {
        int risultato = Dices.tira(12);
        System.out.println("Dadi: D12: " + risultato);
        return risultato;
    }

    // --- ARMI CON 2D6 ---
    public static int spadone() {
        int d1 = Dices.tira(6);
        int d2 = Dices.tira(6);
        System.out.println("Dadi: D6: " + d1 + "  D6: " + d2);
        return d1 + d2;
    }

    public static int falcione() {
        int d1 = Dices.tira(6);
        int d2 = Dices.tira(6);
        System.out.println("Dadi: D6: " + d1 + "  D6: " + d2);
        return d1 + d2;
    }

    // TIRA DANNO ARMA - legge l'arma dal personaggio e chiama il metodo giusto
    public static int tiraDannoArma(String arma) {
        switch (arma.toLowerCase()) {
            case "pugnale":        return pugnale();
            case "scimitarra":     return scimitarra();
            case "spadacorta":     return spadaCorta();
            case "asciaguerra":    return asciaGuerra();
            case "flagello":       return flagello();
            case "lancia":         return lancia();
            case "spadalunga":     return spadaLunga();
            case "martelloguerra": return martelloGuerra();
            case "picconeguerra":  return picconeGuerra();
            case "stocco":         return stocco();
            case "alabarda":       return alabarda();
            case "picca":          return picca();
            case "lanciagiostra":  return lanciaGiostra();
            case "asciaduemani":   return asciaDueMani();
            case "spadone":        return spadone();
            default:               return falcione();
        }
    }
}