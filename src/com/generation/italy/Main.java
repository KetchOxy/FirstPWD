package com.generation.italy;

import com.generation.italy.domain.Merchant;
import com.generation.italy.domain.Player;
import com.generation.italy.domain.Stats;
import com.generation.italy.utils.CreateLocation;
import com.generation.italy.utils.CreatePlayer;
import com.generation.italy.utils.OutputUtils;
import com.generation.italy.world.DungeonBuilder;
import com.generation.italy.world.Room;
import com.generation.italy.world.World;
import com.generation.library.Console;

public class Main {

    public static void main(String[] args) {

        OutputUtils.printTitle();

        // 1. Creazione personaggio
        Player pg = CreatePlayer.crea();
        Stats.assegna(pg);

        // CICLO PRINCIPALE
        boolean continua = true;
        while (continua && pg.isVivo()) {

            // 2. Scelta dungeon
            OutputUtils.print("\n=== SCEGLI IL PROSSIMO DUNGEON ===");
            OutputUtils.print("1. Dungeon  (Scheletri) - bonus Nano");
            OutputUtils.print("2. Foresta  (Lupi)      - bonus Ranger");
            OutputUtils.print("3. Taverna  (Banditi)   - bonus Ladro");
            OutputUtils.print("4. Cripta   (Zombi)     - bonus Chierico");
            OutputUtils.print("5. Montagna (Orchi)     - bonus Barbaro");
            System.out.print("Scelta: ");
            int scelta = Console.readInt();

            String nomeDungeon, nomeNemico, bonusClasse;
            switch (scelta) {
                case 1: nomeDungeon = "Dungeon";   nomeNemico = "Scheletro"; bonusClasse = "Nano";     break;
                case 2: nomeDungeon = "Foresta";   nomeNemico = "Lupo";      bonusClasse = "Ranger";   break;
                case 3: nomeDungeon = "Taverna";   nomeNemico = "Bandito";   bonusClasse = "Ladro";    break;
                case 4: nomeDungeon = "Cripta";    nomeNemico = "Zombi";     bonusClasse = "Chierico"; break;
                default: nomeDungeon = "Montagna"; nomeNemico = "Orco";      bonusClasse = "Barbaro";  break;
            }

            if (pg.getClasse().equalsIgnoreCase(bonusClasse)) {
                OutputUtils.print("*** BONUS CLASSE! +2 danni nel dungeon " + nomeDungeon + "! ***");
            }

            // 3. Costruisci il dungeon e avvia l'esplorazione
            Room ingresso = DungeonBuilder.costruisci(nomeDungeon, nomeNemico, bonusClasse);
            World world = new World(pg, ingresso, bonusClasse);
            boolean vittoria = world.esplora();

            // 4. Fase inter-dungeon
            if (pg.isVivo()) {
                if (vittoria) {
                    OutputUtils.print("\n***************************************");
                    OutputUtils.print(" IMPRESA COMPIUTA! Dungeon " + nomeDungeon + " conquistato!");
                    OutputUtils.print(" Oro totale: " + pg.getOro());
                    OutputUtils.print("***************************************");
                }

                // Riposo breve
                pg.riceviCura(5);
                OutputUtils.print("Riposi all'avamposto. Recuperi 5 PF. (PF: " + pg.getPuntiFerita() + "/" + pg.getPuntiFeritaMax() + ")");

                // Mercante
                Merchant.gestisciBottega(pg);

                // Nuovo viaggio?
                continua = CreateLocation.richiediNuovoViaggio();
            }
        }

        // 5. Fine partita
        OutputUtils.print();
        if (!pg.isVivo()) {
            OutputUtils.print("La tua avventura finisce qui... Le canzoni ricorderanno il tuo sacrificio.");
        } else {
            OutputUtils.print("Hai deciso di ritirarti. Ti godi le tue " + pg.getOro() + " monete d'oro in taverna!");
        }
    }
}