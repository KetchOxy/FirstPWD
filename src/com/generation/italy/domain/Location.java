package com.generation.italy.domain;

// LOCATION - rappresenta un ambiente di combattimento
public class Location {
    public String nome;
    public String descrizione;
    public String nomeNemico;
    public String bonusClasse;   // classe che riceve il bonus in questa location
    public boolean bonusColpire; // vantaggio al tiro per colpire
    public boolean bonusDanno;   // bonus ai danni
}
