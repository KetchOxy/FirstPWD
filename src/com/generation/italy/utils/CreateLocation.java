package com.generation.italy.utils;

import com.generation.italy.domain.Player;
import com.generation.italy.world.DungeonBuilder;
import com.generation.italy.world.Room;
import com.generation.library.Console;

/**
 * CREATE LOCATION - Permette al giocatore di scegliere il prossimo dungeon.
 */
public class CreateLocation {

    public static Room[] scegli(Player pg) {
        OutputUtils.print("\n=== Scegli la Prossima Location ===");
        OutputUtils.print("1. Dungeon  - Corridoi bui. Nemico: Scheletro. Bonus: Nano");
        OutputUtils.print("2. Foresta  - Alberi fitti. Nemico: Lupo.      Bonus: Ranger");
        OutputUtils.print("3. Taverna  - Caos e fumo.  Nemico: Bandito.   Bonus: Ladro");
        OutputUtils.print("4. Cripta   - Silenzio.     Nemico: Zombi.     Bonus: Chierico");
        OutputUtils.print("5. Montagna - Vento gelido. Nemico: Orco.      Bonus: Barbaro");
        System.out.print("Scelta: ");
        int scelta = Console.readInt();

        String nomeDungeon, nomeNemico, bonusClasse;
        switch (scelta) {
            case 1: nomeDungeon = "Dungeon";  nomeNemico = "Scheletro"; bonusClasse = "Nano";     break;
            case 2: nomeDungeon = "Foresta";  nomeNemico = "Lupo";      bonusClasse = "Ranger";   break;
            case 3: nomeDungeon = "Taverna";  nomeNemico = "Bandito";   bonusClasse = "Ladro";    break;
            case 4: nomeDungeon = "Cripta";   nomeNemico = "Zombi";     bonusClasse = "Chierico"; break;
            default: nomeDungeon = "Montagna"; nomeNemico = "Orco";     bonusClasse = "Barbaro";  break;
        }

        if (pg.getClasse().equalsIgnoreCase(bonusClasse)) {
            OutputUtils.print("\n*** BONUS! La tua classe " + pg.getClasse() + " ha familiarità con " + nomeDungeon + "! +2 danni in combattimento! ***");
        }

        Room ingresso = DungeonBuilder.costruisci(nomeDungeon, nomeNemico, bonusClasse);
        return new Room[]{ ingresso };  // [0] = ingresso; bonusClasse via World
    }

    public static String scegliGetBonus(Player pg) {
        // Metodo helper per recuperare il bonusClasse dalla scelta
        OutputUtils.print("\n=== Scegli la Prossima Location ===");
        OutputUtils.print("1.Dungeon(Nano) 2.Foresta(Ranger) 3.Taverna(Ladro) 4.Cripta(Chierico) 5.Montagna(Barbaro)");
        System.out.print("Scelta: ");
        int scelta = Console.readInt();
        switch (scelta) {
            case 1: return "Nano";
            case 2: return "Ranger";
            case 3: return "Ladro";
            case 4: return "Chierico";
            default: return "Barbaro";
        }
    }

    public static boolean richiediNuovoViaggio() {
        System.out.print("Sei pronto a partire verso una NUOVA LOCATION? (s/n): ");
        return Console.readString().equalsIgnoreCase("s");
    }
}