package com.generation.italy;

import com.generation.italy.fight.Combat;
import com.generation.italy.domain.Inventory;
import com.generation.italy.domain.Player;
import com.generation.italy.utils.CreatePlayer;
import com.generation.italy.utils.OutputUtils;

public class Main {

    public static void main(String[] args) {

        // 1. Crea il personaggio (nome, specie, classe, PF, CA)
        Player pg = CreatePlayer.crea();

        // 2. Assegna le caratteristiche
        Inventory.assegna(pg);

        // 3. Avvia il simulatore di dadi
        Combat.avvia(pg);
    }
}