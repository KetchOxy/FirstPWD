package com.generation.italy.fight;

import com.generation.italy.domain.Player;
import com.generation.italy.domain.Weapon;
import com.generation.italy.utils.Dices;
import com.generation.italy.utils.OutputUtils;

public class TiroColpire {

    public static int eseguiAutomatico(int modificatore, Player pg, int caNemico, String statoTiro) {

        OutputUtils.print("--- Tiro per colpire ---");

        int tiroBase;

        if (statoTiro.equals("v")) {
            int dado1 = Dices.tira(20);
            int dado2 = Dices.tira(20);
            OutputUtils.print("Lancio con VANTAGGIO. Dadi: " + dado1 + " e " + dado2);
            tiroBase = Math.max(dado1, dado2);
            OutputUtils.print("Tieni il piu' alto: " + tiroBase);
        } else if (statoTiro.equals("s")) {
            int dado1 = Dices.tira(20);
            int dado2 = Dices.tira(20);
            OutputUtils.print("Lancio con SVANTAGGIO. Dadi: " + dado1 + " e " + dado2);
            tiroBase = Math.min(dado1, dado2);
            OutputUtils.print("Tieni il piu' basso: " + tiroBase);
        } else {
            tiroBase = Dices.tira(20);
            OutputUtils.print("Lancio Normale. Dado: " + tiroBase);
        }

        if (tiroBase == 1) {
            OutputUtils.print("*** FUMBLE! Hai mancato! ***");
            int effetto = Dices.tira(3);
            if (effetto == 1) OutputUtils.print("Hai colpito te stesso!");
            if (effetto == 2) OutputUtils.print("Hai perso l'arma!");
            if (effetto == 3) OutputUtils.print("Sei caduto a terra!");
            return 0;
        } else {
            if (tiroBase == 20) OutputUtils.print("*** CRITICO! ***");

            int tiroFinale = tiroBase + modificatore;
            OutputUtils.print("Tiro finale: " + tiroBase + " + mod(" + modificatore + ") = " + tiroFinale);
            OutputUtils.print("CA nemica: " + caNemico);

            if (tiroFinale < caNemico && tiroBase != 20) {
                OutputUtils.print("Hai mancato!");
                return 0;
            } else {
                OutputUtils.print("Hai colpito! Arma: " + pg.getArma());

                // Danno = dado dell'arma (D&D standard). Senza arma: 1 danno (mani nude)
                Weapon arma = pg.getArmaIndossata();
                int danno;

                if (arma != null) {
                    danno = arma.tiraDanno();
                    OutputUtils.print("Danno: " + danno + " (d" + arma.getDado() + ")");
                } else {
                    int modForza = Player.calcolaModificatore(pg.forza);
                    danno = 1 + modForza;
                    OutputUtils.print("Mani nude! Danno: 1 + mod FOR(" + modForza + ") = " + danno);
                }

                if (tiroBase == 20) {
                    danno = danno * 2;
                    OutputUtils.print("*** Danno raddoppiato dal Critico! Totale: " + danno + " ***");
                }
                return danno;
            }
        }
    }
}