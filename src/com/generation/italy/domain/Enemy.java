package com.generation.italy.domain;

import com.generation.italy.utils.Entity;

public class Enemy extends Entity {

    public Weapon arma;
    public int classeArmatura;
    public int forza;
    public int destrezza;
    public int costituzione;

    public Enemy(String nome, int hp, int level) {
        super(hp, nome, level);
    }

    public int getPuntiFerita() { return getCurrentHp(); }

    public static int calcolaModificatore(int punteggio) {
        return (punteggio - 10) / 2;
    }
}