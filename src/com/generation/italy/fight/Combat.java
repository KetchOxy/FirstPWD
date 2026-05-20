package com.generation.italy.fight;

import com.generation.italy.domain.Inventory;
import com.generation.italy.domain.Player;
import com.generation.italy.utils.InfoDices;
import com.generation.italy.utils.OutputUtils;
import com.generation.library.Console;

// COMBAT - coordinatore, richiama le altre classi
public class Combat {

    public static void avvia(Player pg) {

        OutputUtils.print("=== Simulatore dadi D&D ===");

        // RICHIAMO INFORMAZIONE DADI
        InfoDices.mostra();

        // CICLO PRINCIPALE
        boolean rilancia = true;
        while (rilancia == true) {

            OutputUtils.print("Che tipo di tiro vuoi fare?");
            OutputUtils.print("1. Tiro per colpire");
            OutputUtils.print("2. Tiro di abilita'");
            int tipoTiro = Console.readInt();

            int modificatore = Inventory.scegliModificatore(pg);

            switch (tipoTiro) {
                case 2:
                    TiroAbilita.esegui(modificatore);
                    break;
                default:
                    TiroColpire.esegui(modificatore, pg);
            }

            System.out.print("Vuoi lanciare ancora? (s/n): ");
            String risposta = Console.readString();
            rilancia = risposta.equals("s");
        }
    }
}
