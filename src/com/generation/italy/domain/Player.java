package com.generation.italy.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * PLAYER - Il personaggio del giocatore. Estende Entity con statistiche D&D aggiuntive,
 * inventario, oro e classe/specie.
 */
public class Player extends Entity {
    private String specie;
    private String classe;
    private String arma;
    private int intelligenza;
    private int saggezza;
    private int carisma;
    private int oro;
    private int numeroPozioni;
    private List<Item> inventario;

    public Player(String nome, String specie, String classe, String arma,
                  int puntiFerita, int classeArmatura,
                  int forza, int destrezza, int costituzione,
                  int intelligenza, int saggezza, int carisma) {
        super(nome, puntiFerita, classeArmatura, forza, destrezza, costituzione);
        this.specie = specie;
        this.classe = classe;
        this.arma = arma;
        this.intelligenza = intelligenza;
        this.saggezza = saggezza;
        this.carisma = carisma;
        this.oro = 0;
        this.numeroPozioni = 2;
        this.inventario = new ArrayList<>();
    }

    // --- GETTER ---
    public String getSpecie()       { return specie; }
    public String getClasse()       { return classe; }
    public String getArma()         { return arma; }
    public int getIntelligenza()    { return intelligenza; }
    public int getSaggezza()        { return saggezza; }
    public int getCarisma()         { return carisma; }
    public int getOro()             { return oro; }
    public int getNumeroPozioni()   { return numeroPozioni; }
    public List<Item> getInventario() { return inventario; }

    // --- SETTER ---
    public void setArma(String arma)       { this.arma = arma; }
    public void setIntelligenza(int v)     { this.intelligenza = v; }
    public void setSaggezza(int v)         { this.saggezza = v; }
    public void setCarisma(int v)          { this.carisma = v; }
    public void setOro(int oro)            { this.oro = Math.max(0, oro); }
    public void aggiungiOro(int quantita)  { this.oro += quantita; }
    public void rimuoviOro(int quantita)   { this.oro = Math.max(0, oro - quantita); }
    public void setNumeroPozioni(int n)    { this.numeroPozioni = Math.max(0, n); }
    public void aggiungiPozione()          { this.numeroPozioni++; }
    public void rimuoviPozione()           { this.numeroPozioni = Math.max(0, numeroPozioni - 1); }

    public void aggiungiItem(Item item) { inventario.add(item); }

    public int getModIntelligenza() { return calcolaModificatore(intelligenza); }
    public int getModSaggezza()     { return calcolaModificatore(saggezza); }
    public int getModCarisma()      { return calcolaModificatore(carisma); }

    @Override
    public String toString() {
        return getNome() + " [" + specie + " " + classe + "]" +
                " PF: " + getPuntiFerita() + "/" + getPuntiFeritaMax() +
                " CA: " + getClasseArmatura() +
                " Oro: " + oro + " Pozioni: " + numeroPozioni;
    }
}