package com.generation.italy.utils;

import com.generation.italy.domain.Enemy;
import com.generation.italy.domain.Location;

// CREATE ENEMY - genera il nemico in base alla location scelta
public class CreateEnemy {

    public static Enemy crea(Location loc) {

        Enemy nemico = new Enemy();
        nemico.nome = loc.nomeNemico;

        // STATISTICHE DEL NEMICO IN BASE AL TIPO
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
                nemico.arma = "Pugnale"; // morso - usiamo pugnale come approssimazione
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
                nemico.arma = "Flagello"; // colpo - approssimazione
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

        OutputUtils.print();
        OutputUtils.print("=== Nemico apparso! ===");
        OutputUtils.print("Nome:  " + nemico.nome);
        OutputUtils.print("PF:    " + nemico.puntiFerita);
        OutputUtils.print("CA:    " + nemico.classeArmatura);
        OutputUtils.print("FOR:   " + nemico.forza + " (mod: " + Enemy.calcolaModificatore(nemico.forza) + ")");
        OutputUtils.print("DES:   " + nemico.destrezza + " (mod: " + Enemy.calcolaModificatore(nemico.destrezza) + ")");
        OutputUtils.print("COS:   " + nemico.costituzione + " (mod: " + Enemy.calcolaModificatore(nemico.costituzione) + ")");
        OutputUtils.print("Arma:  " + nemico.arma);
        OutputUtils.print();

        return nemico;
    }
}
