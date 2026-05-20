package com.generation.italy.utils;

import com.generation.italy.domain.Location;
import com.generation.library.Console;
import com.generation.italy.domain.Player;
public class CreateLocation {

    public static Location scegli(Player pg) {
        OutputUtils.print("=== Scegli la Location ===");
        OutputUtils.print("1. Dungeon     - Corridoi bui e trappole. Nemico: Scheletro");
        OutputUtils.print("2. Foresta     - Alberi fitti e sentieri nascosti. Nemico: Lupo");
        OutputUtils.print("3. Taverna     - Ambiente caotico e rumoroso. Nemico: Bandito");
        OutputUtils.print("4. Cripta      - Silenzio e oscurita' totale. Nemico: Zombi");
        OutputUtils.print("5. Montagna    - Vento gelido e rocce scivolose. Nemico: Orco");
        System.out.print("Scelta: ");
        int scelta = Console.readInt();

        Location loc = new Location();

        switch (scelta) {
            case 1:
                loc.nome = "Dungeon";
                loc.descrizione = "Corridoi bui, trappole ovunque e l'eco di passi lontani.";
                loc.nomeNemico = "Scheletro";
                loc.bonusClasse = "Nano";
                loc.bonusColpire = true;
                loc.bonusDanno = false;
                break;
            case 2:
                loc.nome = "Foresta";
                loc.descrizione = "Alberi fitti, fitta vegetazione e ombre che si muovono rapide.";
                loc.nomeNemico = "Lupo";
                loc.bonusClasse = "Ranger";
                loc.bonusColpire = true;
                loc.bonusDanno = false;
                break;
            case 3:
                loc.nome = "Taverna";
                loc.descrizione = "Fumo, alcol e sguardi poco rassicuranti da ogni angolo.";
                loc.nomeNemico = "Bandito";
                loc.bonusClasse = "Ladro";
                loc.bonusColpire = true;
                loc.bonusDanno = false;
                break;
            case 4:
                loc.nome = "Cripta";
                loc.descrizione = "Silenzio assoluto, odore di muffa e ossa che scricchiolano.";
                loc.nomeNemico = "Zombi";
                loc.bonusClasse = "Chierico";
                loc.bonusColpire = false;
                loc.bonusDanno = true;
                break;
            default:
                loc.nome = "Montagna";
                loc.descrizione = "Vento gelido, rocce scivolose e un ruggito in lontananza.";
                loc.nomeNemico = "Orco";
                loc.bonusClasse = "Barbaro";
                loc.bonusColpire = false;
                loc.bonusDanno = true;
                break;
        }

        OutputUtils.print();
        OutputUtils.print("Sei entrato in: " + loc.nome);
        if (pg.classe.equalsIgnoreCase(loc.bonusClasse)) {
            OutputUtils.print("\n*** BONUS! La tua classe " + pg.classe + " ha una grande familiarita' con " + loc.nome + "! ***");
        }
        return loc;
    }

    // NUOVO METODO: Gestisce la domanda sul cambio di dungeon
    public static boolean richiediNuovoViaggio() {
        System.out.print("Sei pronto a metterti in viaggio verso una NUOVA LOCATION? (s/n): ");
        String risposta = Console.readString();
        return risposta.equalsIgnoreCase("s");
    }
}
