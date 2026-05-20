package com.generation.italy.utils;

import com.generation.italy.domain.Enemy;
import com.generation.italy.domain.Location;

// CREATE ENEMY - genera il nemico in base alla location scelta e al tipo di incontro
public class CreateEnemy {

    public static Enemy crea(Location loc, String tipoIncontro) {

        Enemy nemico = new Enemy();

        // GESTIONE BOSS O MOSTRO NORMALE
        if (tipoIncontro.equalsIgnoreCase("boss")) {
            nemico.nome = "IL GRANDE " + loc.nomeNemico.toUpperCase() + " SUPREMO";

            // Statistiche potenziate per i Boss del Dungeon
            switch (loc.nomeNemico) {
                case "Scheletro":
                    nemico.nome = "Il Signore delle Ossa (Re Scheletro)";
                    nemico.puntiFerita = 32;
                    nemico.classeArmatura = 15;
                    nemico.forza = 14;
                    nemico.destrezza = 14;
                    nemico.costituzione = 16;
                    nemico.arma = "Spadone"; // Fa 2d6 danni!
                    break;
                case "Lupo":
                    nemico.nome = "Fenrir, il Lupo Alfa";
                    nemico.puntiFerita = 28;
                    nemico.classeArmatura = 14;
                    nemico.forza = 14;
                    nemico.destrezza = 16;
                    nemico.costituzione = 14;
                    nemico.arma = "SpadaLunga";
                    break;
                case "Bandito":
                    nemico.nome = "Garrick, il Capo dei Tagliagole";
                    nemico.puntiFerita = 30;
                    nemico.classeArmatura = 15;
                    nemico.forza = 12;
                    nemico.destrezza = 16;
                    nemico.costituzione = 14;
                    nemico.arma = "Stocco";
                    break;
                case "Zombi":
                    nemico.nome = "Il Cadavere Rianimato Gigante";
                    nemico.puntiFerita = 45; // Tantissimi PF ma CA bassa!
                    nemico.classeArmatura = 9;
                    nemico.forza = 16;
                    nemico.destrezza = 5;
                    nemico.costituzione = 18;
                    nemico.arma = "MartelloGuerra";
                    break;
                default: // Orco
                    nemico.nome = "Grommash, il Capoguerra Orco";
                    nemico.puntiFerita = 40;
                    nemico.classeArmatura = 15;
                    nemico.forza = 18;
                    nemico.destrezza = 12;
                    nemico.costituzione = 16;
                    nemico.arma = "AsciaGuerra";
                    break;
            }
        } else {
            // Mostro normale (codice originale)
            nemico.nome = loc.nomeNemico;
            switch (loc.nomeNemico) {
                case "Scheletro":
                    nemico.puntiFerita = 13;
                    nemico.classeArmatura = 13;
                    nemico.forza = 10;
                    nemico.destrezza = 14;
                    nemico.costituzione = 15;
                    nemico.arma = "SpadaCorta";
                    break;
                case "Lupo":
                    nemico.puntiFerita = 11;
                    nemico.classeArmatura = 13;
                    nemico.forza = 12;
                    nemico.destrezza = 15;
                    nemico.costituzione = 12;
                    nemico.arma = "Pugnale";
                    break;
                case "Bandito":
                    nemico.puntiFerita = 11;
                    nemico.classeArmatura = 12;
                    nemico.forza = 11;
                    nemico.destrezza = 12;
                    nemico.costituzione = 12;
                    nemico.arma = "Scimitarra";
                    break;
                case "Zombi":
                    nemico.puntiFerita = 22;
                    nemico.classeArmatura = 8;
                    nemico.forza = 13;
                    nemico.destrezza = 6;
                    nemico.costituzione = 16;
                    nemico.arma = "Flagello";
                    break;
                default: // Orco
                    nemico.puntiFerita = 15;
                    nemico.classeArmatura = 13;
                    nemico.forza = 16;
                    nemico.destrezza = 12;
                    nemico.costituzione = 16;
                    nemico.arma = "AsciaGuerra";
                    break;
            }
        }

        OutputUtils.print();
        OutputUtils.print("=== " + (tipoIncontro.equalsIgnoreCase("boss") ? "ATTENZIONE! MINIBOSS" : "NEMICO") + " APPARSO! ===");
        OutputUtils.print("Nome:  " + nemico.nome);
        OutputUtils.print("PF:    " + nemico.puntiFerita);
        OutputUtils.print("CA:    " + nemico.classeArmatura);
        OutputUtils.print("FOR:   " + nemico.forza + " (mod: " + Enemy.calcolaModificatore(nemico.forza) + ")");
        OutputUtils.print("DES:   " + nemico.destrezza + " (mod: " + Enemy.calcolaModificatore(nemico.destrezza) + ")");
        OutputUtils.print("Arma:  " + nemico.arma);
        OutputUtils.print();

        return nemico;
    }
}
