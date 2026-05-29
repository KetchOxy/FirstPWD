package com.generation.italy.domain;

/**
 * ENEMY - Un nemico nel dungeon. Estende Entity con un'arma e un flag boss.
 */
public class Enemy extends Entity {
    private String arma;
    private boolean isBoss;
    private int ricompensaOro;

    public Enemy(String nome, int puntiFerita, int classeArmatura,
                 int forza, int destrezza, int costituzione,
                 String arma, boolean isBoss, int ricompensaOro) {
        super(nome, puntiFerita, classeArmatura, forza, destrezza, costituzione);
        this.arma = arma;
        this.isBoss = isBoss;
        this.ricompensaOro = ricompensaOro;
    }

    public String getArma()        { return arma; }
    public boolean isBoss()        { return isBoss; }
    public int getRicompensaOro()  { return ricompensaOro; }

    @Override
    public String toString() {
        return (isBoss ? "[BOSS] " : "") + getNome() +
                " [PF: " + getPuntiFerita() + "/" + getPuntiFeritaMax() +
                " | CA: " + getClasseArmatura() + " | Arma: " + arma + "]";
    }
}