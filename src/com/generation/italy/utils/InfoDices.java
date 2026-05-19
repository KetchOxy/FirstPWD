package com.generation.italy.utils;

import com.generation.library.*;

// INFORMAZIONE DADI - stampa le informazioni sui dadi D&D
public class InfoDices {

    public static void mostra() {
        System.out.println("Servono informazioni sui dadi?");
        String ris = Console.readString();
        if (ris.equalsIgnoreCase("si")) {
            System.out.println("d4: danni piccoli, magie deboli, armi leggere come il pugnale");
            System.out.println("d6: armi comuni, incantesimi, danni medi");
            System.out.println("d8: armi piu' forti, cure, capacita' intermedie");
            System.out.println("d10: armi pesanti, alcune magie");
            System.out.println("d12: armi enormi, danni molto alti");
            System.out.println("d20: attaccare, prove abilita', salvezza, controlli generali");
            System.out.println("d100: probabilita', eventi casuali, effetti speciali");
        } else {
            System.out.println("Va bene, continuiamo.");
        }
    }
}