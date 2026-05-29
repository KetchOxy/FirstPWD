package com.generation.italy.utils;

import com.generation.italy.domain.Player;
import com.generation.library.Console;

/**
 * CREATE PLAYER - Gestisce la creazione del personaggio (nome, specie, classe, arma, PF, CA).
 */
public class CreatePlayer {

    public static Player crea() {
        Player pg;
        OutputUtils.print("=== Creazione Personaggio ===");

        System.out.print("Nome: ");
        String nome = Console.readString();

        System.out.print("Specie (Umano/Elfo/Nano/Halfling/Dragonborn/Gnomo/Tiefling/Orco/Goliath/Aasimar): ");
        String specie = Console.readString();
        while (!specieValida(specie)) {
            OutputUtils.print("Specie non valida! Riprova.");
            System.out.print("Specie: ");
            specie = Console.readString();
        }

        System.out.print("Classe (Barbaro/Bardo/Chierico/Druido/Guerriero/Monaco/Paladino/Ranger/Ladro/Stregone/Warlock/Mago): ");
        String classe = Console.readString();
        while (!classeValida(classe)) {
            OutputUtils.print("Classe non valida! Riprova.");
            System.out.print("Classe: ");
            classe = Console.readString();
        }

        OutputUtils.print("Arma (Pugnale/Scimitarra/SpadaCorta/AsciaGuerra/Flagello/Lancia/SpadaLunga/MartelloGuerra/PicconeGuerra/Stocco/Alabarda/Spadone/Falcione): ");
        String arma = Console.readString();
        while (!armaValida(arma)) {
            OutputUtils.print("Arma non valida! Riprova.");
            System.out.print("Arma: ");
            arma = Console.readString();
        }

        System.out.print("Punti Ferita iniziali: ");
        int pf = Console.readInt();

        System.out.print("Classe Armatura (CA): ");
        int ca = Console.readInt();

        // Le stat vengono assegnate dopo via Stats.assegna()
        pg = new Player(nome, specie, classe, arma, pf, ca, 8, 8, 8, 8, 8, 8);
        return pg;
    }

    public static boolean armaValida(String a) {
        return a.equalsIgnoreCase("Pugnale") || a.equalsIgnoreCase("Scimitarra") ||
                a.equalsIgnoreCase("SpadaCorta") || a.equalsIgnoreCase("AsciaGuerra") ||
                a.equalsIgnoreCase("Flagello") || a.equalsIgnoreCase("Lancia") ||
                a.equalsIgnoreCase("SpadaLunga") || a.equalsIgnoreCase("MartelloGuerra") ||
                a.equalsIgnoreCase("PicconeGuerra") || a.equalsIgnoreCase("Stocco") ||
                a.equalsIgnoreCase("Alabarda") || a.equalsIgnoreCase("Spadone") ||
                a.equalsIgnoreCase("Falcione");
    }

    public static boolean specieValida(String s) {
        return s.equalsIgnoreCase("Umano") || s.equalsIgnoreCase("Elfo") ||
                s.equalsIgnoreCase("Nano") || s.equalsIgnoreCase("Halfling") ||
                s.equalsIgnoreCase("Dragonborn") || s.equalsIgnoreCase("Gnomo") ||
                s.equalsIgnoreCase("Tiefling") || s.equalsIgnoreCase("Orco") ||
                s.equalsIgnoreCase("Goliath") || s.equalsIgnoreCase("Aasimar");
    }

    public static boolean classeValida(String c) {
        return c.equalsIgnoreCase("Barbaro") || c.equalsIgnoreCase("Bardo") ||
                c.equalsIgnoreCase("Chierico") || c.equalsIgnoreCase("Druido") ||
                c.equalsIgnoreCase("Guerriero") || c.equalsIgnoreCase("Monaco") ||
                c.equalsIgnoreCase("Paladino") || c.equalsIgnoreCase("Ranger") ||
                c.equalsIgnoreCase("Ladro") || c.equalsIgnoreCase("Stregone") ||
                c.equalsIgnoreCase("Warlock") || c.equalsIgnoreCase("Mago");
    }
}