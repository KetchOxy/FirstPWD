package com.generation.italy.fight;

import com.generation.italy.domain.Inventory;
import com.generation.italy.domain.Player;
import com.generation.italy.fight.TiroAbilita;
import com.generation.italy.fight.TiroColpire;
import com.generation.italy.utils.CreatePlayer;
import com.generation.italy.utils.InfoDices;
import com.generation.library.Console;

// SIMULATORE DADI - coordinatore, richiama le altre classi
public class Combat {

    public static void avvia(Player pg) {

        System.out.println("=== Simulatore dadi D&D ===");

        // RICHIAMO INFORMAZIONE DADI
        InfoDices.mostra();

        // CICLO PRINCIPALE
        boolean rilancia = true;
        while (rilancia == true) {

            // SCELTA TIPO DI TIRO
            System.out.println("Che tipo di tiro vuoi fare?");
            System.out.println("1. Tiro per colpire");
            System.out.println("2. Tiro di abilita'");
            int tipoTiro = Console.readInt();

            // SCELTA CARATTERISTICA E CALCOLO MODIFICATORE
            int modificatore = Inventory.scegliModificatore(pg);

            // CHIAMO LA CLASSE GIUSTA - passa anche il personaggio a Tirocolpire
            switch (tipoTiro) {
                case 2:
                    TiroAbilita.esegui(modificatore);
                    break;
                default:
                    TiroColpire.esegui(modificatore, pg);
            }

            // CHIEDO SE VUOLE RILANCIARE
            System.out.print("Vuoi lanciare ancora? (s/n): ");
            String risposta = Console.readString();
            rilancia = risposta.equals("s");
        }
    }
}