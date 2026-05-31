package com.generation.italy.domain;

import com.generation.italy.utils.Entity;
import com.generation.italy.utils.OutputUtils;
import com.generation.library.Console;
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

    public int oro           = 0;
    public int numeroPozioni = 2;

    private ArrayList<Item> inventario = new ArrayList<>();
    private Weapon armaIndossata       = null;
    private Armor  armaturaIndossata   = null;

    public Player(String nome, int hp, int level) {
        super(hp, nome, level);
    }

    // ── Compatibilità ────────────────────────────────────────────────────────

    public int getPuntiFerita()    { return getCurrentHp(); }
    public int getPuntiFeritaMax() { return getMaxHp(); }

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

    // ── Inventario — getters ─────────────────────────────────────────────────

    public ArrayList<Item> getInventario()   { return inventario; }
    public Weapon getArmaIndossata()         { return armaIndossata; }
    public Armor  getArmaturaIndossata()     { return armaturaIndossata; }

    // ── Inventario — azioni ──────────────────────────────────────────────────

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

    public boolean lasciaItem(Item item) {
        if (!inventario.contains(item)) {
            OutputUtils.print("Non hai " + item.getNome() + " nell'inventario.");
            return false;
        }
        if (item == armaIndossata) {
            armaIndossata.rimuovi();
            armaIndossata = null;
            OutputUtils.print("Hai rimosso l'arma equipaggiata.");
        }
        if (item == armaturaIndossata) {
            armaturaIndossata.rimuovi();
            armaturaIndossata = null;
            classeArmatura = 10 + calcolaModificatore(destrezza);
            OutputUtils.print("Hai rimosso l'armatura. CA torna a: " + classeArmatura);
        }
        inventario.remove(item);
        OutputUtils.print("Hai lasciato a terra: " + item.getNome());
        return true;
    }

    public boolean usaItem(Item item) {
        if (!inventario.contains(item)) {
            OutputUtils.print("Non hai " + item.getNome() + " nell'inventario.");
            return false;
        }
        if (item instanceof Weapon)     return indossaArma((Weapon) item);
        if (item instanceof Armor)      return indossaArmatura((Armor) item);
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
        OutputUtils.print("Equipaggi " + arma.getNome() + " (DADO:d" + arma.getDado() + ").");
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
        classeArmatura = 10 + calcolaModificatore(destrezza) + armatura.getDifesa();
        OutputUtils.print("Indossi " + armatura.getNome() + " (DEF:+" + armatura.getDifesa() + ") — CA aggiornata: " + classeArmatura);
        return true;
    }

    // ── Menu inventario interattivo ───────────────────────────────────────────

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
                Item item = inventario.get(i);
                String tag = "";
                if      (item == armaIndossata)      tag = " [equipaggiata]";
                else if (item == armaturaIndossata)  tag = " [indossata]";
                else if (item instanceof Weapon)     tag = " [arma]";
                else if (item instanceof Armor)      tag = " [armatura]";
                else if (item instanceof Consumable) tag = " [consumabile]";
                OutputUtils.print("    " + (i + 1) + ". " + item.getNome() + tag);
            }
        }
        OutputUtils.print("────────────────────────────────────────");
    }

    public void gestisciInventario() {
        while (true) {
            mostraInventario();
            if (inventario.isEmpty()) {
                OutputUtils.print("Lo zaino e' vuoto. Premi I per uscire.");
            }

            OutputUtils.print("Cosa vuoi fare?");
            if (!inventario.isEmpty()) {
                OutputUtils.print("  U - Usa / Equipaggia oggetto");
                OutputUtils.print("  L - Lascia oggetto a terra");
            }
            OutputUtils.print("  I - Esci dall'inventario");
            System.out.print("-> ");
            String scelta = Console.readString();

            switch (scelta.toLowerCase()) {
                case "u":
                    if (!inventario.isEmpty()) menuUsa();
                    else OutputUtils.print("Non hai oggetti da usare.");
                    break;
                case "l":
                    if (!inventario.isEmpty()) menuLascia();
                    else OutputUtils.print("Non hai oggetti da lasciare.");
                    break;
                case "i":
                    return;
                default:
                    OutputUtils.print("Comando non riconosciuto.");
                    break;
            }
        }
    }

    private void menuUsa() {
        mostraInventario();
        OutputUtils.print("Quale oggetto vuoi usare/equipaggiare? (numero, 0 per annullare): ");
        int idx = Console.readInt() - 1;
        if (idx < 0 || idx >= inventario.size()) {
            OutputUtils.print("Scelta annullata.");
            return;
        }
        usaItem(inventario.get(idx));
    }

    private void menuLascia() {
        mostraInventario();
        OutputUtils.print("Quale oggetto vuoi lasciare a terra? (numero, 0 per annullare): ");
        int idx = Console.readInt() - 1;
        if (idx < 0 || idx >= inventario.size()) {
            OutputUtils.print("Scelta annullata.");
            return;
        }
        lasciaItem(inventario.get(idx));
    }

    // ── Modificatore D&D ─────────────────────────────────────────────────────

    public static int calcolaModificatore(int punteggio) {
        return (punteggio - 10) / 2;
    }

}