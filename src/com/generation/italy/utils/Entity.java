package com.generation.italy.utils;

public class Entity {
    private int maxHp;
    private int currentHp;
    private String name;
    private int level;

    public Entity(int baseHp, String name, int level) {
        this.name = name;
        this.level = level;
        this.maxHp = baseHp * level;      // PF scalano col livello
        this.currentHp = this.maxHp;
    }

    public String getNome()      { return name; }
    public int getLevel()        { return level; }
    public int getMaxHp()        { return maxHp; }
    public int getCurrentHp()    { return currentHp; }
    public boolean isAlive()     { return currentHp > 0; }
    public String getHpBar()     { return currentHp + "/" + maxHp + " HP"; }

    public void setCurrentHp(int currentHp) {
        this.currentHp = Math.max(0, currentHp);
    }
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
        this.currentHp = maxHp;
    }

    // Sali di livello — ricalcola i PF massimi
    public void levelUp() {
        this.level++;
        this.maxHp = (this.maxHp / (this.level - 1)) * this.level; // riscala
        this.currentHp = this.maxHp; // recupera tutti i PF al level up
    }
}