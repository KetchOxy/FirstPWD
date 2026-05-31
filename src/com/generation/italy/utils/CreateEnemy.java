package com.generation.italy.utils;

import com.generation.italy.domain.Enemy;
import com.generation.italy.domain.Location;
import com.generation.italy.domain.Weapon;

public class CreateEnemy {

    // ── Nemici normali ───────────────────────────────────────────────────────

    public static Enemy scheletro() {
        Enemy e = new Enemy("Scheletro", 13, 1);
        e.classeArmatura = 13;
        e.forza = 10;
        e.destrezza = 14;
        e.costituzione = 15;
        e.arma = Weapon.spadaCorta();
        return e;
    }

    public static Enemy lupo() {
        Enemy e = new Enemy("Lupo", 11, 1);
        e.classeArmatura = 13;
        e.forza = 12;
        e.destrezza = 15;
        e.costituzione = 12;
        e.arma = null;
        return e;
    }

    public static Enemy bandito() {
        Enemy e = new Enemy("Bandito", 11, 1);
        e.classeArmatura = 12;
        e.forza = 11;
        e.destrezza = 12;
        e.costituzione = 12;
        e.arma = Weapon.scimitarra();
        return e;
    }

    public static Enemy zombi() {
        Enemy e = new Enemy("Zombi", 22, 1);
        e.classeArmatura = 8;
        e.forza = 13;
        e.destrezza = 6;
        e.costituzione = 16;
        e.arma = Weapon.flagello();
        return e;
    }

    public static Enemy orco() {
        Enemy e = new Enemy("Orco", 15, 1);
        e.classeArmatura = 13;
        e.forza = 16;
        e.destrezza = 12;
        e.costituzione = 16;
        e.arma = Weapon.asciaGuerra();
        return e;
    }

    // ── Boss ─────────────────────────────────────────────────────────────────

    public static Enemy reScheletro() {
        Enemy e = new Enemy("Il Signore delle Ossa (Re Scheletro)", 32, 1);
        e.classeArmatura = 15;
        e.forza = 14;
        e.destrezza = 14;
        e.costituzione = 16;
        e.arma = Weapon.spadone();
        return e;
    }

    public static Enemy fenrir() {
        Enemy e = new Enemy("Fenrir, il Lupo Alfa", 28, 1);
        e.classeArmatura = 14;
        e.forza = 14;
        e.destrezza = 16;
        e.costituzione = 14;
        e.arma = Weapon.spadaLunga();
        return e;
    }

    public static Enemy garrick() {
        Enemy e = new Enemy("Garrick, il Capo dei Tagliagole", 30, 1);
        e.classeArmatura = 15;
        e.forza = 12;
        e.destrezza = 16;
        e.costituzione = 14;
        e.arma = Weapon.stocco();
        return e;
    }

    public static Enemy cadavereGigante() {
        Enemy e = new Enemy("Il Cadavere Rianimato Gigante", 45, 1);
        e.classeArmatura = 9;
        e.forza = 16;
        e.destrezza = 5;
        e.costituzione = 18;
        e.arma = Weapon.martelloGuerra();
        return e;
    }

    public static Enemy grommash() {
        Enemy e = new Enemy("Grommash, il Capoguerra Orco", 40, 1);
        e.classeArmatura = 15;
        e.forza = 18;
        e.destrezza = 12;
        e.costituzione = 16;
        e.arma = Weapon.asciaGuerra();
        return e;
    }

    // ── Crea una nuova istanza fresca dal nome — usato per resettare i nemici ──

    public static Enemy creaFresco(String nome) {
        switch (nome.toLowerCase()) {
            // Nemici normali
            case "scheletro":                          return scheletro();
            case "lupo":                               return lupo();
            case "bandito":                            return bandito();
            case "zombi":                              return zombi();
            case "orco":                               return orco();
            // Boss
            case "il signore delle ossa (re scheletro)": return reScheletro();
            case "fenrir, il lupo alfa":               return fenrir();
            case "garrick, il capo dei tagliagole":    return garrick();
            case "il cadavere rianimato gigante":      return cadavereGigante();
            case "grommash, il capoguerra orco":       return grommash();
            default:
                throw new IllegalArgumentException("Nemico non trovato: " + nome);
        }
    }

    // ── Collegamento con Location — restituisce SEMPRE un'istanza fresca ─────

    public static Enemy crea(Location loc, String tipoIncontro) {
        // Prende il template solo per leggere il nome, poi crea un nemico nuovo
        Enemy template = tipoIncontro.equalsIgnoreCase("boss") ? loc.boss : loc.nemico;
        Enemy nemico = creaFresco(template.getNome());

        OutputUtils.print();
        OutputUtils.print("=== " + (tipoIncontro.equalsIgnoreCase("boss") ? "ATTENZIONE! BOSS" : "NEMICO") + " APPARSO! ===");
        OutputUtils.print("Nome:  " + nemico.getNome());
        OutputUtils.print("PF:    " + nemico.getCurrentHp());
        OutputUtils.print("CA:    " + nemico.classeArmatura);
        OutputUtils.print("FOR:   " + nemico.forza + " (mod: " + Enemy.calcolaModificatore(nemico.forza) + ")");
        OutputUtils.print("DES:   " + nemico.destrezza + " (mod: " + Enemy.calcolaModificatore(nemico.destrezza) + ")");
        OutputUtils.print("Arma:  " + (nemico.arma != null ? nemico.arma.getNome() : "Mani nude"));
        OutputUtils.print();

        return nemico;
    }
}