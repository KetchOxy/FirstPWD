package com.generation.italy.world;

import com.generation.italy.domain.Enemy;
import com.generation.italy.domain.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * ROOM - Una stanza del dungeon (basata sul progetto del professore, estesa).
 * Ogni stanza ha uscite direzionali N/S/E/W, un tipo (COMBAT, TRAP, ecc.)
 * e può contenere nemici e oggetti.
 */
public class Room {

    // Costanti direzioni (dal professore)
    public static final int NORTH = 0;
    public static final int EAST  = 1;
    public static final int WEST  = 2;
    public static final int SOUTH = 3;

    // Tipi di stanza
    public enum TipoStanza {
        INGRESSO,   // Stanza iniziale sicura
        COMBATTIMENTO,
        TRAPPOLA,
        TESORO,
        BOSS,
        SICURA      // Stanza di riposo
    }

    private String titolo;
    private String descrizione;
    private TipoStanza tipo;
    private Room[] uscite;
    private List<Enemy> nemici;
    private List<Item> oggetti;
    private boolean esplorata;
    private boolean completata;  // true = nemico sconfitto / trappola superata

    public Room(String titolo, String descrizione, TipoStanza tipo) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.uscite = new Room[4];
        this.nemici = new ArrayList<>();
        this.oggetti = new ArrayList<>();
        this.esplorata = false;
        this.completata = false;
    }

    // --- USCITE (dal professore) ---
    public boolean aggiungiUscita(Room destinazione, int direzione) {
        if (uscite[direzione] != null) return false;
        uscite[direzione] = destinazione;
        return true;
    }

    public Room uscitaIn(int direzione) {
        return uscite[direzione];
    }

    public boolean haUscita(int direzione) {
        return uscite[direzione] != null;
    }

    // --- NEMICI ---
    public void aggiungiNemico(Enemy e) { nemici.add(e); }
    public List<Enemy> getNemici()      { return nemici; }
    public boolean haNemiciVivi() {
        return nemici.stream().anyMatch(e -> e.isVivo());
    }
    public Enemy getPrimoNemicoVivo() {
        return nemici.stream().filter(e -> e.isVivo()).findFirst().orElse(null);
    }

    // --- OGGETTI ---
    public void aggiungiOggetto(Item item) { oggetti.add(item); }
    public List<Item> getOggetti()         { return oggetti; }
    public boolean haOggetti()             { return !oggetti.isEmpty(); }
    public Item rimuoviOggetto(int index)  { return oggetti.remove(index); }

    // --- GETTER ---
    public String getTitolo()      { return titolo; }
    public String getDescrizione() { return descrizione; }
    public TipoStanza getTipo()    { return tipo; }
    public boolean isEsplorata()   { return esplorata; }
    public boolean isCompletata()  { return completata; }

    // --- SETTER ---
    public void setEsplorata(boolean v)  { this.esplorata = v; }
    public void setCompletata(boolean v) { this.completata = v; }

    /** Mostra le uscite disponibili in forma leggibile */
    public String usciteDisponibili() {
        StringBuilder sb = new StringBuilder("Uscite: ");
        if (uscite[NORTH] != null) sb.append("[N] Nord  ");
        if (uscite[EAST]  != null) sb.append("[E] Est   ");
        if (uscite[WEST]  != null) sb.append("[W] Ovest ");
        if (uscite[SOUTH] != null) sb.append("[S] Sud   ");
        return sb.toString().trim();
    }

    /** Mappa ASCII minima della stanza e delle uscite vicine */
    public String miniMappa() {
        StringBuilder sb = new StringBuilder();
        sb.append("        ").append(uscite[NORTH] != null ? "[ N ]" : "  |  ").append("\n");
        sb.append(uscite[WEST] != null ? "[ W ]" : "  -  ");
        sb.append("--[").append(tipoIcona()).append("]--");
        sb.append(uscite[EAST] != null ? "[ E ]" : "  -  ").append("\n");
        sb.append("        ").append(uscite[SOUTH] != null ? "[ S ]" : "  |  ");
        return sb.toString();
    }

    private String tipoIcona() {
        switch (tipo) {
            case INGRESSO:      return "IN";
            case COMBATTIMENTO: return completata ? "ok" : "!!";
            case TRAPPOLA:      return completata ? "ok" : "??";
            case TESORO:        return completata ? "ok" : "$$";
            case BOSS:          return completata ? "ok" : "BX";
            case SICURA:        return "~~";
            default:            return "  ";
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== ").append(titolo).append(" ===\n");
        sb.append(descrizione).append("\n");
        sb.append(miniMappa()).append("\n");
        sb.append(usciteDisponibili()).append("\n");

        if (!nemici.isEmpty() && haNemiciVivi()) {
            sb.append("Nemici presenti: ");
            nemici.stream().filter(e -> e.isVivo())
                    .forEach(e -> sb.append(e.getNome()).append("  "));
            sb.append("\n");
        }
        if (!oggetti.isEmpty()) {
            sb.append("Oggetti visibili: ");
            oggetti.forEach(o -> sb.append(o.getNome()).append("  "));
            sb.append("\n");
        }
        return sb.toString();
    }
}