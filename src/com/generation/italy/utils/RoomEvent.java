package com.generation.italy.utils;

import com.generation.italy.domain.Player;
import com.generation.italy.fight.TiroAbilita;
import com.generation.library.Console;
import com.generation.italy.utils.Dices;

public class RoomEvent {

    public static void gestisciTrappola(Player pg) {
        OutputUtils.print("\n*** STANZA 2: AMBIENTE PERICOLOSO ***");
        OutputUtils.print("Entrando nella stanza, senti un click sotto lo stivale... Le porte si sigillano!");
        OutputUtils.print("Una grata sul soffitto si apre e una pioggia di dardi d'acciaio sta per cadere!");
        OutputUtils.print("Come decidi di reagire prima che i dardi vengano sparati?");
        OutputUtils.print("1. Ti rannicchi dietro lo scudo usando i muscoli (Prova di FORZA)");
        OutputUtils.print("2. Fai un balzo felino di lato per trovare un angolo cieco (Prova di DESTREZZA)");
        OutputUtils.print("3. Stringi i denti e provi a incassare i colpi col fisico (Prova di COSTITUZIONE)");
        System.out.print("Scegli il tuo approccio (1-3): ");
        int scelta = Console.readInt();

        int modSelezionato = 0;
        int bonusCompetenza = 0;
        int classeDifficolta = 13; // CD fissa da battere col d20

        switch (scelta) {
            case 1:
                modSelezionato = Player.calcolaModificatore(pg.forza);
                OutputUtils.print("Usi la Forza bruta...");
                break;
            case 2:
                modSelezionato = Player.calcolaModificatore(pg.destrezza);
                bonusCompetenza = 2; // Bonus competenza se azzecca l'approccio ideale
                OutputUtils.print("Ottima intuizione! Ottieni +2 di Competenza per l'approccio agile!");
                break;
            case 3:
                modSelezionato = Player.calcolaModificatore(pg.costituzione);
                OutputUtils.print("Usi la tua pura Costituzione fisica...");
                break;
            default:
                OutputUtils.print("Esiti troppo! Il panico azzera i tuoi riflessi (Modificatore: +0)");
                break;
        }

        // Calcoliamo il modificatore totale (Statistica + eventuale Competenza)
        int modTotale = modSelezionato + bonusCompetenza;

        // Eseguiamo il tiro
        boolean successo = TiroAbilita.eseguiControCD(modTotale, classeDifficolta);

        if (successo) {
            OutputUtils.print("Hai schivato i dardi millimetricamente senza subire danni!");
        } else {
            int danniTrappola = Dices.tira(6) + 2; // 1d6 + 2 danni
            pg.setCurrentHp(pg.getPuntiFerita() - danniTrappola);
            OutputUtils.print("Sei stato colpito dai dardi! Subisci " + danniTrappola + " danni da trappola.");
            OutputUtils.print("I tuoi PF attuali: " + pg.getPuntiFerita() + "/" + pg.getPuntiFeritaMax());
        }
    }
}