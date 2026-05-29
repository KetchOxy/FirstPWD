package com.generation.italy.domain;

/**
 * ENTITY - Classe base per Player e Enemy (ispirata al progetto del professore).
 * Usa l'incapsulamento: tutti i campi sono private, accessibili solo via getter/setter.
 */
public abstract class Entity {
    private String nome;
    private int puntiFerita;
    private int puntiFeritaMax;
    private int classeArmatura;
    private int forza;
    private int destrezza;
    private int costituzione;

    public Entity(String nome, int puntiFerita, int classeArmatura,
                  int forza, int destrezza, int costituzione) {
        this.nome = nome;
        this.puntiFerita = puntiFerita;
        this.puntiFeritaMax = puntiFerita;
        this.classeArmatura = classeArmatura;
        this.forza = forza;
        this.destrezza = destrezza;
        this.costituzione = costituzione;
    }

    // --- GETTER ---
    public String getNome()           { return nome; }
    public int getPuntiFerita()       { return puntiFerita; }
    public int getPuntiFeritaMax()    { return puntiFeritaMax; }
    public int getClasseArmatura()    { return classeArmatura; }
    public int getForza()             { return forza; }
    public int getDestrezza()         { return destrezza; }
    public int getCostituzione()      { return costituzione; }

    // --- SETTER controllati ---
    public void setPuntiFerita(int pf) {
        this.puntiFerita = Math.max(0, Math.min(pf, puntiFeritaMax));
    }
    public void setPuntiFeritaMax(int pfMax) { this.puntiFeritaMax = pfMax; }
    public void setForza(int v)        { this.forza = v; }
    public void setDestrezza(int v)    { this.destrezza = v; }
    public void setCostituzione(int v) { this.costituzione = v; }
    public void setClasseArmatura(int v) { this.classeArmatura = v; }
    public void setNome(String n)      { this.nome = n; }

    // --- METODI ---
    public boolean isVivo() { return puntiFerita > 0; }

    public void riceviDanno(int danno) {
        this.puntiFerita = Math.max(0, this.puntiFerita - danno);
    }

    public void riceviCura(int cura) {
        this.puntiFerita = Math.min(puntiFeritaMax, this.puntiFerita + cura);
    }

    /** Formula D&D standard: (stat - 10) / 2 */
    public static int calcolaModificatore(int punteggio) {
        return (punteggio - 10) / 2;
    }

    public int getModForza()       { return calcolaModificatore(forza); }
    public int getModDestrezza()   { return calcolaModificatore(destrezza); }
    public int getModCostituzone() { return calcolaModificatore(costituzione); }

    @Override
    public String toString() {
        return nome + " [PF: " + puntiFerita + "/" + puntiFeritaMax + " | CA: " + classeArmatura + "]";
    }
}
