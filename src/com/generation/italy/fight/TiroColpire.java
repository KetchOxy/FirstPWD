package com.generation.italy.fight;

import com.generation.italy.domain.Player;
import com.generation.italy.utils.Dices;
import com.generation.italy.utils.OutputUtils;
import com.generation.italy.utils.Weapons;
import com.generation.library.*;

// TIRO COLPIRE - gestisce il tiro per colpire, CA e dadi danno
// restituisce il danno inflitto (0 se manca)
public class TiroColpire {

    // 3 PARAMETRI: modificatore, personaggio, CA del nemico, bonus location
    public static int esegui(int modificatore, Player pg, int caNemico, boolean haBonus) {

        OutputUtils.print("--- Tiro per colpire ---");

        // SE HA BONUS LOCATION - lancia con vantaggio automaticamente
        int tiroBase;
        if (haBonus) {
            OutputUtils.print("*** Bonus location attivo: tiri con vantaggio! ***");
            int dado1 = Dices.tira(20);
            int dado2 = Dices.tira(20);
            OutputUtils.print("Dadi: " + dado1 + " e " + dado2);
            tiroBase = dado1 > dado2 ? dado1 : dado2;
            OutputUtils.print("Tieni il piu' alto: " + tiroBase);
        } else {
            tiroBase = TiroAbilita.lanciaD20();
        }

        if (tiroBase == 1) {
            // FUMBLE - mancato automatico, modificatore non applicato
            OutputUtils.print("*** FUMBLE! Hai mancato! ***");
            int effetto = Dices.tira(3);
            if (effetto == 1) OutputUtils.print("Hai colpito te stesso!");
            if (effetto == 2) OutputUtils.print("Hai perso l'arma!");
            if (effetto == 3) OutputUtils.print("Sei caduto a terra!");
            return 0; // nessun danno
        } else {
            if (tiroBase == 20) OutputUtils.print("*** CRITICO! ***");

            int tiroFinale = tiroBase + modificatore;
            OutputUtils.print("Tiro finale: " + tiroBase + " + mod(" + modificatore + ") = " + tiroFinale);

            // USA LA CA DEL NEMICO passata come parametro invece di chiederla
            OutputUtils.print("CA nemica: " + caNemico);

            if (tiroFinale < caNemico && tiroBase != 20) {
                OutputUtils.print("Hai mancato! CA nemica: " + caNemico);
                return 0; // nessun danno
            } else {
                OutputUtils.print("Hai colpito! Arma: " + pg.arma);
                int danno = Weapons.tiraDannoArma(pg.arma);

                if (tiroBase == 20) {
                    danno = danno * 2;
                    OutputUtils.print("*** Danno raddoppiato: " + danno + " ***");
                }

                OutputUtils.print("Totale danno: " + danno);
                return danno; // restituisce il danno inflitto
            }
        }
    }
}