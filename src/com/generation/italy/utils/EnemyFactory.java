package com.generation.italy.utils;

import com.generation.italy.domain.Enemy;

/**
 * ENEMY FACTORY - Crea nemici in base al tema del dungeon.
 * Versione refactored con Enemy come oggetto con costruttore e getter.
 */
public class EnemyFactory {

    public static Enemy crea(String nomeNemico, boolean isBoss) {
        if (isBoss) return creaBoss(nomeNemico);
        else        return creaNormale(nomeNemico);
    }

    private static Enemy creaNormale(String nomeNemico) {
        switch (nomeNemico) {
            case "Scheletro":
                return new Enemy("Scheletro", 13, 13, 10, 14, 15, "SpadaCorta", false, 10);
            case "Lupo":
                return new Enemy("Lupo", 11, 13, 12, 15, 12, "Pugnale", false, 8);
            case "Bandito":
                return new Enemy("Bandito", 11, 12, 11, 12, 12, "Scimitarra", false, 12);
            case "Zombi":
                return new Enemy("Zombi", 22, 8, 13, 6, 16, "Flagello", false, 10);
            default: // Orco
                return new Enemy("Orco", 15, 13, 16, 12, 16, "AsciaGuerra", false, 14);
        }
    }

    private static Enemy creaBoss(String nomeNemico) {
        switch (nomeNemico) {
            case "Scheletro":
                return new Enemy("Il Signore delle Ossa", 32, 15, 14, 14, 16, "Spadone", true, 50);
            case "Lupo":
                return new Enemy("Fenrir, il Lupo Alfa", 28, 14, 14, 16, 14, "SpadaLunga", true, 45);
            case "Bandito":
                return new Enemy("Garrick, il Capo dei Tagliagole", 30, 15, 12, 16, 14, "Stocco", true, 55);
            case "Zombi":
                return new Enemy("Il Cadavere Gigante", 45, 9, 16, 5, 18, "MartelloGuerra", true, 50);
            default: // Orco
                return new Enemy("Grommash, il Capoguerra", 40, 15, 18, 12, 16, "AsciaGuerra", true, 60);
        }
    }
}