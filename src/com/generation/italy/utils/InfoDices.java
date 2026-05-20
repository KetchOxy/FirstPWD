package com.generation.italy.utils;

import com.generation.library.*;

// INFORMAZIONE DADI - stampa le informazioni sui dadi D&D
public class InfoDices {

    public static void mostra() {
        System.out.print("Servono informazioni sui dadi? ");
        String ris = Console.readString();
        if (ris.equalsIgnoreCase("si")) {
            OutputUtils.print("d4: danni piccoli, magie deboli, armi leggere come il pugnale");
            OutputUtils.print("d6: armi comuni, incantesimi, danni medi");
            OutputUtils.print("d8: armi piu' forti, cure, capacita' intermedie");
            OutputUtils.print("d10: armi pesanti, alcune magie");
            OutputUtils.print("d12: armi enormi, danni molto alti");
            OutputUtils.print("d20: attaccare, prove abilita', salvezza, controlli generali");
            OutputUtils.print("d100: probabilita', eventi casuali, effetti speciali");
        } else {
            OutputUtils.print("Va bene, continuiamo.");
        }
    }
}