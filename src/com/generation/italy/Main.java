package com.generation.italy;

import com.generation.italy.fight.Combat;
import com.generation.italy.domain.Inventory;
import com.generation.italy.domain.Player;
import com.generation.italy.domain.Location;
import com.generation.italy.domain.Enemy;
import com.generation.italy.utils.CreatePlayer;
import com.generation.italy.utils.CreateLocation;
import com.generation.italy.utils.CreateEnemy;
import com.generation.italy.utils.OutputUtils;
import com.generation.library.Console;

public class Main {

    public static void main(String[] args) {

        // TITOLO DEL GIOCO
        OutputUtils.printTitle();

        // 1. Crea il personaggio
        Player pg = CreatePlayer.crea();

        // 2. Assegna le caratteristiche
        Inventory.assegna(pg);

        // LOOP AVVENTURA - continua finche' il giocatore vuole o muore
        boolean continua = true;
        while (continua) {

            // 3. Scegli la location
            Location loc = CreateLocation.scegli();

            // 4. Avvisa se il personaggio ha un bonus in questa location
            if (pg.classe.equalsIgnoreCase(loc.bonusClasse)) {
                OutputUtils.print("*** BONUS! La tua classe " + pg.classe + " ha un vantaggio in " + loc.nome + "! ***");
            }

            // 5. Genera il nemico in base alla location
            Enemy nemico = CreateEnemy.crea(loc);

            // 6. Avvia il combattimento
            Combat.avvia(pg, nemico, loc);

            // 7. GAME OVER se il giocatore e' morto
            if (pg.puntiFerita <= 0) break;

            // 8. Chiedo se vuole continuare l'avventura
            System.out.print("Vuoi continuare l'avventura? (s/n): ");
            String risposta = Console.readString();
            continua = risposta.equals("s");
        }

        // MESSAGGIO FINALE
        if (pg.puntiFerita <= 0) {
            OutputUtils.print("La tua avventura finisce qui...");
        } else {
            OutputUtils.print("Hai concluso la tua avventura con " + pg.puntiFerita + " PF rimasti. Glorioso!");
        }
    }
}