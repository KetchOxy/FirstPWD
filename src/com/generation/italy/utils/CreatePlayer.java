package com.generation.italy.utils;

import com.generation.italy.domain.Player;
import com.generation.italy.domain.Weapon;
import com.generation.library.*;

public class CreatePlayer {

    public static Player crea() {

        OutputUtils.print("=== Creazione Personaggio ===");

        System.out.print("Nome del personaggio: ");
        String nome = Console.readString();
        Player pg = new Player(nome, 1, 1);

        // SPECIE CON VALIDAZIONE
        System.out.print("Specie (Umano, Elfo, Nano, Halfling, Dragonborn, Gnomo, Tiefling, Orco, Goliath, Aasimar): ");
        pg.specie = Console.readString();
        while (specieValida(pg.specie) == false) {
            OutputUtils.print("Specie non valida! Riprova.");
            System.out.print("Specie: ");
            pg.specie = Console.readString();
        }

        // CLASSE CON VALIDAZIONE
        System.out.print("Classe (Barbaro, Bardo, Chierico, Druido, Guerriero, Monaco, Paladino, Ranger, Ladro, Stregone, Warlock, Mago): ");
        pg.classe = Console.readString();
        while (classeValida(pg.classe) == false) {
            OutputUtils.print("Classe non valida! Riprova.");
            System.out.print("Classe: ");
            pg.classe = Console.readString();
        }
        return pg;
    }

    public static boolean armaValida(String arma) {
        return arma.equalsIgnoreCase("Pugnale")        ||
                arma.equalsIgnoreCase("Scimitarra")     ||
                arma.equalsIgnoreCase("SpadaCorta")     ||
                arma.equalsIgnoreCase("AsciaGuerra")    ||
                arma.equalsIgnoreCase("Flagello")       ||
                arma.equalsIgnoreCase("Lancia")         ||
                arma.equalsIgnoreCase("SpadaLunga")     ||
                arma.equalsIgnoreCase("MartelloGuerra") ||
                arma.equalsIgnoreCase("PicconeGuerra")  ||
                arma.equalsIgnoreCase("Stocco")         ||
                arma.equalsIgnoreCase("Alabarda")       ||
                arma.equalsIgnoreCase("Spadone")        ||
                arma.equalsIgnoreCase("Falcione");
    }

    public static boolean specieValida(String specie) {
        return specie.equalsIgnoreCase("Umano")      ||
                specie.equalsIgnoreCase("Elfo")       ||
                specie.equalsIgnoreCase("Nano")       ||
                specie.equalsIgnoreCase("Halfling")   ||
                specie.equalsIgnoreCase("Dragonborn") ||
                specie.equalsIgnoreCase("Gnomo")      ||
                specie.equalsIgnoreCase("Tiefling")   ||
                specie.equalsIgnoreCase("Orco")       ||
                specie.equalsIgnoreCase("Goliath")    ||
                specie.equalsIgnoreCase("Aasimar");
    }

    public static boolean classeValida(String classe) {
        return classe.equalsIgnoreCase("Barbaro")   ||
                classe.equalsIgnoreCase("Bardo")     ||
                classe.equalsIgnoreCase("Chierico")  ||
                classe.equalsIgnoreCase("Druido")    ||
                classe.equalsIgnoreCase("Guerriero") ||
                classe.equalsIgnoreCase("Monaco")    ||
                classe.equalsIgnoreCase("Paladino")  ||
                classe.equalsIgnoreCase("Ranger")    ||
                classe.equalsIgnoreCase("Ladro")     ||
                classe.equalsIgnoreCase("Stregone")  ||
                classe.equalsIgnoreCase("Warlock")   ||
                classe.equalsIgnoreCase("Mago");
    }
}