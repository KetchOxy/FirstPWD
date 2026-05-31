package com.generation.italy.utils;

import com.generation.italy.domain.Player;
import com.generation.italy.domain.Item;
import com.generation.italy.domain.Weapon;
import com.generation.italy.domain.Armor;

public class ClassEntity extends Entity {

    public final int caBase;
    public final int dadoVita;
    public final String abilitaSpeciale;

    private ClassEntity(int caBase, int dadoVita, String nome, String abilitaSpeciale) {
        super(caBase, nome, 1);
        this.caBase = caBase;
        this.dadoVita = dadoVita;
        this.abilitaSpeciale = abilitaSpeciale;
    }

    public int calcolaHP(Player pg) {
        return this.dadoVita + Player.calcolaModificatore(pg.costituzione);
    }

    public int calcolaCA(Player pg) {
        return 10 + Player.calcolaModificatore(pg.destrezza);
    }

    public int calcolaHPNemico(int livello, int costituzione) {
        int modCos = Player.calcolaModificatore(costituzione);
        return (dadoVita + modCos) * livello;
    }

    public static final ClassEntity BARBARO   = new ClassEntity(12, 12, "Barbaro",   "Furia: +2 danni per 1 round");
    public static final ClassEntity BARDO     = new ClassEntity( 8,  8, "Bardo",     "Ispirazione: +1d6 a un alleato");
    public static final ClassEntity CHIERICO  = new ClassEntity( 8,  8, "Chierico",  "Guarigione: cura 1d8 PF");
    public static final ClassEntity DRUIDO    = new ClassEntity( 8,  8, "Druido",    "Forma Selvatica: diventa animale");
    public static final ClassEntity GUERRIERO = new ClassEntity(10, 10, "Guerriero", "Secondo Vento: recupera 1d10 PF");
    public static final ClassEntity MONACO    = new ClassEntity( 8,  8, "Monaco",    "Arti Marziali: attacco bonus");
    public static final ClassEntity PALADINO  = new ClassEntity(10, 10, "Paladino",  "Imposizione delle Mani: cura 5 PF");
    public static final ClassEntity RANGER    = new ClassEntity(10, 10, "Ranger",    "Nemico Prescelto: +2 contro un tipo");
    public static final ClassEntity LADRO     = new ClassEntity( 8,  8, "Ladro",     "Attacco Furtivo: +1d6 da vantaggio");
    public static final ClassEntity STREGONE  = new ClassEntity( 6,  6, "Stregone",  "Punti Stregoneria: potenzia incantesimi");
    public static final ClassEntity WARLOCK   = new ClassEntity( 8,  8, "Warlock",   "Maledizione: -1d4 al nemico");
    public static final ClassEntity MAGO      = new ClassEntity( 6,  6, "Mago",      "Recupero Arcano: recupera slot");

    private static final ClassEntity[] TUTTE = {
            BARBARO, BARDO, CHIERICO, DRUIDO, GUERRIERO, MONACO,
            PALADINO, RANGER, LADRO, STREGONE, WARLOCK, MAGO
    };

    public static ClassEntity da(String nome) {
        for (ClassEntity c : TUTTE) {
            if (c.getNome().equalsIgnoreCase(nome)) return c;
        }
        throw new IllegalArgumentException("Classe non trovata: " + nome);
    }

    public void equipaggioIniziale(Player pg) {
        switch (this.getNome().toLowerCase()) {

            case "barbaro":
                Weapon asciaBarbaro = Weapon.asciaGuerra();
                pg.aggiungiItem(asciaBarbaro);
                pg.indossaArma(asciaBarbaro);
                pg.aggiungiItem(Weapon.pugnale());
                pg.aggiungiItem(Item.ramoAppuntito());
                break;

            case "bardo":
                Weapon stoccoBardo = Weapon.stocco();
                pg.aggiungiItem(stoccoBardo);
                pg.indossaArma(stoccoBardo);
                Armor giubboBardo = Armor.giubboCuoio();
                pg.aggiungiItem(giubboBardo);
                pg.indossaArmatura(giubboBardo);
                pg.aggiungiItem(Item.amuletoFulmine());
                pg.aggiungiItem(Weapon.pugnale());
                break;

            case "chierico":
                Weapon martelloChierico = Weapon.martelloGuerra();
                pg.aggiungiItem(martelloChierico);
                pg.indossaArma(martelloChierico);
                Armor corazzaChierico = Armor.corazzaSpezzata();
                pg.aggiungiItem(corazzaChierico);
                pg.indossaArmatura(corazzaChierico);
                pg.aggiungiItem(Item.amuletoFulmine());
                break;

            case "druido":
                Weapon falcioneDruido = Weapon.falcione();
                pg.aggiungiItem(falcioneDruido);
                pg.indossaArma(falcioneDruido);
                pg.aggiungiItem(Armor.scudoFerro());
                pg.aggiungiItem(Item.ramoAppuntito());
                break;

            case "guerriero":
                Weapon spadaGuerriero = Weapon.spadaLunga();
                pg.aggiungiItem(spadaGuerriero);
                pg.indossaArma(spadaGuerriero);
                Armor giubboGuerriero = Armor.giubboCuoio();
                pg.aggiungiItem(giubboGuerriero);
                pg.indossaArmatura(giubboGuerriero);
                pg.aggiungiItem(Armor.scudoFerro());
                break;

            case "monaco":
                Weapon bastonMonaco = Weapon.bastone();
                pg.aggiungiItem(bastonMonaco);
                pg.indossaArma(bastonMonaco);
                pg.aggiungiItem(Weapon.pugnale());
                break;

            case "paladino":
                Weapon spadaPaladino = Weapon.spadaLunga();
                pg.aggiungiItem(spadaPaladino);
                pg.indossaArma(spadaPaladino);
                Armor scudoPaladino = Armor.scudoFerro();
                pg.aggiungiItem(scudoPaladino);
                pg.indossaArmatura(scudoPaladino);
                pg.aggiungiItem(Item.amuletoFulmine());
                pg.aggiungiItem(Weapon.lancia());
                break;

            case "ranger":
                Weapon spadaRanger = Weapon.spadaCorta();
                pg.aggiungiItem(spadaRanger);
                pg.indossaArma(spadaRanger);
                pg.aggiungiItem(Weapon.spadaCorta());
                Armor giubboRanger = Armor.giubboCuoio();
                pg.aggiungiItem(giubboRanger);
                pg.indossaArmatura(giubboRanger);
                break;

            case "ladro":
                Weapon stoccoLadro = Weapon.stocco();
                pg.aggiungiItem(stoccoLadro);
                pg.indossaArma(stoccoLadro);
                pg.aggiungiItem(Weapon.spadaCorta());
                pg.aggiungiItem(Item.picconeIncantato());
                break;

            case "stregone":
                Weapon bastonStregone = Weapon.bastone();
                pg.aggiungiItem(bastonStregone);
                pg.indossaArma(bastonStregone);
                pg.aggiungiItem(Item.amuletoFulmine());
                break;

            case "warlock":
                Weapon armaWarlock = Weapon.pugnale();
                pg.aggiungiItem(armaWarlock);
                pg.indossaArma(armaWarlock);
                Armor giubboWarlock = Armor.giubboCuoio();
                pg.aggiungiItem(giubboWarlock);
                pg.indossaArmatura(giubboWarlock);
                pg.aggiungiItem(Item.amuletoFulmine());
                break;

            case "mago":
                Weapon bastoneMago = Weapon.bastone();
                pg.aggiungiItem(bastoneMago);
                pg.indossaArma(bastoneMago);
                pg.aggiungiItem(Item.libroIncantesimi());
                pg.aggiungiItem(Item.amuletoFulmine());
                break;
        }
    }
}