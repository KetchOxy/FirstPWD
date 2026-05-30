package com.generation.italy.utils;

import com.generation.italy.domain.Player;
import com.generation.italy.domain.Item;
import com.generation.italy.domain.Weapon;
import com.generation.italy.domain.Armor;
import com.generation.italy.domain.Player;

public class ClassEntity extends Entity {

    public final int caBase;
    public final int dadoVita;       // dado usato per i PF (d6, d8, d10, d12)
    public final String abilitaSpeciale;

    private ClassEntity(int caBase, int dadoVita, String nome, String abilitaSpeciale) {
        super(caBase, nome, 1);
        this.caBase = caBase;
        this.dadoVita = dadoVita;
        this.abilitaSpeciale = abilitaSpeciale;
    }

    // PF livello 1 = dadoVita (valore massimo) + modCOS
    public int calcolaHP(Player pg) {
        return this.dadoVita + Player.calcolaModificatore(pg.costituzione);
    }

    // CA senza armatura = 10 + modDES
    public int calcolaCA(Player pg) {
        return 10 + Player.calcolaModificatore(pg.destrezza);
    }

    // Versione per NPC/nemici senza Player — solo livello e COS flat
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
                pg.aggiungiItem(Weapon.daStringa("AsciaGuerra"));
                pg.indossaArma((Weapon) pg.getInventario().get(0));
                pg.aggiungiItem(Weapon.daStringa("Pugnale")); // 2 accette → pugnale come sostituto
                pg.aggiungiItem(Item.ramoAppuntito());        // Pacco esploratore (simbolico)
                break;

            case "bardo":
                Weapon stoccoBardo = Weapon.daStringa("Stocco");
                pg.aggiungiItem(stoccoBardo);
                pg.indossaArma(stoccoBardo);
                pg.aggiungiItem(Armor.giubboCuoio());
                pg.indossaArmatura((Armor) pg.getInventario().stream()
                        .filter(i -> i instanceof Armor).findFirst().get());
                pg.aggiungiItem(Item.amuletoFulmine());       // Strumento musicale (simbolico)
                pg.aggiungiItem(Weapon.daStringa("Pugnale"));
                break;

            case "chierico":
                Weapon martelloChierico = Weapon.daStringa("MartelloGuerra");
                pg.aggiungiItem(martelloChierico);
                pg.indossaArma(martelloChierico);
                pg.aggiungiItem(Armor.corazzaSpezzata());     // Armatura media
                pg.indossaArmatura((Armor) pg.getInventario().stream()
                        .filter(i -> i instanceof Armor).findFirst().get());
                pg.aggiungiItem(Item.amuletoFulmine());       // Simbolo sacro
                break;

            case "druido":
                Weapon falcettoDruido = Weapon.daStringa("Falcione");
                pg.aggiungiItem(falcettoDruido);
                pg.indossaArma(falcettoDruido);
                pg.aggiungiItem(Armor.scudoFerro());
                pg.aggiungiItem(Item.ramoAppuntito());        // Focus druidico
                break;

            case "guerriero":
                Weapon spadaGuerriero = Weapon.daStringa("SpadaLunga");
                pg.aggiungiItem(spadaGuerriero);
                pg.indossaArma(spadaGuerriero);
                pg.aggiungiItem(Armor.giubboCuoio());         // Cotta di maglia → giubbo cuoio
                pg.indossaArmatura((Armor) pg.getInventario().stream()
                        .filter(i -> i instanceof Armor).findFirst().get());
                pg.aggiungiItem(Armor.scudoFerro());
                break;

            case "monaco":
                Weapon bastonMonaco = Weapon.daStringa("Bastone");
                pg.aggiungiItem(bastonMonaco);
                pg.indossaArma(bastonMonaco);
                pg.aggiungiItem(Weapon.daStringa("Pugnale")); // Dardi → pugnale
                break;

            case "paladino":
                Weapon spadaPaladino = Weapon.daStringa("SpadaLunga");
                pg.aggiungiItem(spadaPaladino);
                pg.indossaArma(spadaPaladino);
                pg.aggiungiItem(Armor.scudoFerro());
                pg.indossaArmatura((Armor) pg.getInventario().stream()
                        .filter(i -> i instanceof Armor).findFirst().get());
                pg.aggiungiItem(Item.amuletoFulmine());       // Simbolo sacro
                pg.aggiungiItem(Weapon.daStringa("Lancia"));  // Giavellotti
                break;

            case "ranger":
                Weapon spadaRanger = Weapon.daStringa("SpadaCorta");
                pg.aggiungiItem(spadaRanger);
                pg.indossaArma(spadaRanger);
                pg.aggiungiItem(Weapon.daStringa("SpadaCorta")); // Due spade corte
                pg.aggiungiItem(Armor.giubboCuoio());
                pg.indossaArmatura((Armor) pg.getInventario().stream()
                        .filter(i -> i instanceof Armor).findFirst().get());
                break;

            case "ladro":
                Weapon stoccoLadro = Weapon.daStringa("Stocco");
                pg.aggiungiItem(stoccoLadro);
                pg.indossaArma(stoccoLadro);
                pg.aggiungiItem(Weapon.daStringa("SpadaCorta"));
                pg.aggiungiItem(Item.picconeIncantato());     // Attrezzi da scasso
                break;

            case "stregone":
                Weapon bacchettaStregone = Weapon.daStringa("Bastone");
                pg.aggiungiItem(bacchettaStregone);
                pg.indossaArma(bacchettaStregone);
                pg.aggiungiItem(Item.amuletoFulmine());       // Focus arcano
                break;

            case "warlock":
                Weapon armaWarlock = Weapon.daStringa("Pugnale");
                pg.aggiungiItem(armaWarlock);
                pg.indossaArma(armaWarlock);
                pg.aggiungiItem(Armor.giubboCuoio());
                pg.indossaArmatura((Armor) pg.getInventario().stream()
                        .filter(i -> i instanceof Armor).findFirst().get());
                pg.aggiungiItem(Item.amuletoFulmine());       // Focus arcano
                break;

            case "mago":
                Weapon bastoneMago = Weapon.daStringa("Bastone");
                pg.aggiungiItem(bastoneMago);
                pg.indossaArma(bastoneMago);
                pg.aggiungiItem(Item.libroIncantesimi());
                pg.aggiungiItem(Item.amuletoFulmine());       // Focus arcano
                break;
        }
    }
}