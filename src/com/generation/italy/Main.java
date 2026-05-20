package com.generation.italy;

import com.generation.italy.domain.Stats;
import com.generation.italy.domain.Player;
import com.generation.italy.domain.Location;
import com.generation.italy.utils.CreatePlayer;
import com.generation.italy.utils.CreateLocation;
import com.generation.italy.utils.OutputUtils;
import com.generation.italy.domain.Merchant;

public class Main {

    public static void main(String[] args) {

        OutputUtils.printTitle();

        // 1. Inizializzazione Personaggio e Caratteristiche
        Player pg = CreatePlayer.crea();
        Stats.assegna(pg);

        // CICLO GENERALE DEL GIOCO
        boolean continua = true;
        while (continua && pg.puntiFerita > 0) {

            // 2. Scelta della Location ambientale
            Location loc = CreateLocation.scegli(pg);

            loc.eseguiDungeon(pg);

            // 4. Fase Inter-Dungeon (Mercante e Scelta se viaggiare ancora)
            if (pg.puntiFerita > 0) {
                OutputUtils.print("\n*********************************************************");
                OutputUtils.print(" IMPRESA COMPIUTA! Hai distrutto il Boss del Dungeon: " + loc.nome + "!");
                OutputUtils.print("*********************************************************");
                OutputUtils.print();

                // Bottega del mercante
                Merchant.gestisciBottega(pg);

                // Chiedi se vuole cambiare dungeon
                continua = CreateLocation.richiediNuovoViaggio();
            }
        }

        // 5. Conclusione della sessione di gioco
        if (pg.puntiFerita <= 0) {
            OutputUtils.print("\nLa tua avventura finisce qui... Le canzoni ricorderanno il tuo sacrificio.");
        } else {
            OutputUtils.print("\nHai deciso di ritirarti dalle leggende. Ti godi le tue " + pg.oro + " monete d'oro in taverna!");
        }
    }
}