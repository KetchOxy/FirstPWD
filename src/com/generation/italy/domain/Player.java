package com.generation.italy.domain;

import com.generation.italy.utils.Entity;
import com.generation.italy.utils.OutputUtils;
import java.util.ArrayList;

public class Player extends Entity {

    public String specie;
    public String classe;
    public int forza;
    public int destrezza;
    public int costituzione;
    public int intelligenza;
    public int saggezza;
    public int carisma;
    public int classeArmatura;

    // Economia
    public int oro         = 0;
    public int numeroPozioni = 2; // pozioni veloci, compatibili con Combat e Merchant

    // Inventario
    private ArrayList<Item> inventario   = new ArrayList<>();
    private Weapon armaIndossata         = null;
    private Armor  armaturaIndossata     = null;

    public Player(String nome, int hp, int level) {
        super(hp, nome, level);
    }

    // ── Compatibilità con il codice esistente ────────────────────────────────

    public int getPuntiFerita()    { return getCurrentHp(); }
    public int getPuntiFeritaMax() { return getMaxHp(); }

    // pg.arma usato in Combat e TiroColpire come stringa
    public String getArma() {
        return armaIndossata != null ? armaIndossata.getNome() : "Mani nude";
    }

    // ── Cura ─────────────────────────────────────────────────────────────────

    public void riceviCura(int ammontare) {
        if (getCurrentHp() >= getMaxHp()) {
            OutputUtils.print("Sei gia' al massimo delle tue forze! La cura non ha effetto.");
            return;
        }
        setCurrentHp(Math.min(getCurrentHp() + ammontare, getMaxHp()));
        OutputUtils.print("PF attuali: " + getCurrentHp() + "/" + getMaxHp());
    }

    // ── Inventario ───────────────────────────────────────────────────────────

    public ArrayList<Item> getInventario() { return inventario; }
    public Weapon getArmaIndossata()        { return armaIndossata; }
    public Armor  getArmaturaIndossata()    { return armaturaIndossata; }

    public void aggiungiItem(Item item) {
        inventario.add(item);
        OutputUtils.print("Hai raccolto: " + item);
    }

    public boolean rimuoviItem(Item item) {
        if (!inventario.contains(item)) {
            OutputUtils.print("Non hai " + item.getNome() + " nell'inventario.");
            return false;
        }
        inventario.remove(item);
        return true;
    }

    public boolean usaItem(Item item) {
        if (!inventario.contains(item)) {
            OutputUtils.print("Non hai " + item.getNome() + " nell'inventario.");
            return false;
        }
        return item.usa(this);
    }

    public boolean indossaArma(Weapon arma) {
        if (!inventario.contains(arma)) {
            OutputUtils.print("Non hai " + arma.getNome() + " nell'inventario.");
            return false;
        }
        if (armaIndossata != null) {
            armaIndossata.rimuovi();
            OutputUtils.print("Riponi " + armaIndossata.getNome() + ".");
        }
        armaIndossata = arma;
        arma.indossa();
        OutputUtils.print("Equipaggi " + arma.getNome() + " (POW:" + arma.getPotenza() + ").");
        return true;
    }

    public boolean indossaArmatura(Armor armatura) {
        if (!inventario.contains(armatura)) {
            OutputUtils.print("Non hai " + armatura.getNome() + " nell'inventario.");
            return false;
        }
        if (armaturaIndossata != null) {
            armaturaIndossata.rimuovi();
            OutputUtils.print("Togli " + armaturaIndossata.getNome() + ".");
        }
        armaturaIndossata = armatura;
        armatura.indossa();
        OutputUtils.print("Indossi " + armatura.getNome() + " (DEF:" + armatura.getDifesa() + ").");
        return true;
    }

    public void mostraInventario() {
        OutputUtils.print("── Inventario ──────────────────────────");
        OutputUtils.print("  PF:       " + getHpBar());
        OutputUtils.print("  CA:       " + classeArmatura);
        OutputUtils.print("  Oro:      " + oro);
        OutputUtils.print("  Pozioni:  " + numeroPozioni);
        OutputUtils.print("  Arma:     " + (armaIndossata     != null ? armaIndossata.toString()     : "nessuna"));
        OutputUtils.print("  Armatura: " + (armaturaIndossata != null ? armaturaIndossata.toString() : "nessuna"));
        if (inventario.isEmpty()) {
            OutputUtils.print("  Zaino:    vuoto");
        } else {
            OutputUtils.print("  Zaino:");
            for (int i = 0; i < inventario.size(); i++) {
                OutputUtils.print("    " + (i + 1) + ". " + inventario.get(i));
            }
        }
        OutputUtils.print("────────────────────────────────────────");
    }

    // ── Modificatore D&D ─────────────────────────────────────────────────────

    public static int calcolaModificatore(int punteggio) {
        return (punteggio - 10) / 2;
    }
}