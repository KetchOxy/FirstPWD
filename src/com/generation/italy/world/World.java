package com.generation.italy.world;

import com.generation.italy.domain.Enemy;
import com.generation.italy.domain.Item;
import com.generation.italy.domain.Player;
import com.generation.italy.fight.Combat;
import com.generation.italy.fight.TiroAbilita;
import com.generation.italy.utils.Dices;
import com.generation.italy.utils.OutputUtils;
import com.generation.library.Console;

import java.util.ArrayList;
import java.util.List;

/**
 * WORLD - Gestisce la navigazione nel dungeon e il game loop principale.
 * Ispirato al World del professore: comandi N/S/E/W + azioni contestuali.
 */
public class World {

    private Room currentRoom;
    private Player pg;
    private String bonusClasse;

    public World(Player pg, Room ingresso, String bonusClasse) {
        this.pg = pg;
        this.currentRoom = ingresso;
        this.bonusClasse = bonusClasse;
    }

    /** Loop di navigazione principale del dungeon */
    public boolean esplora() {
        OutputUtils.print("\n*** Entri nel dungeon. Usa N/S/E/W per muoverti. I=inventario, P=pozione, Q=fuggi. ***\n");

        while (pg.isVivo()) {
            currentRoom.setEsplorata(true);
            OutputUtils.print(currentRoom.toString());

            // Gestisci automaticamente l'evento della stanza quando entri
            gestisciEventoStanza();

            if (!pg.isVivo()) break;

            // Stanza boss completata = dungeon vinto!
            if (currentRoom.getTipo() == Room.TipoStanza.BOSS && currentRoom.isCompletata()) {
                OutputUtils.print("\n*** HAI SCONFITTO IL BOSS! Il dungeon è tuo! ***");
                return true;
            }

            // Input giocatore
            System.out.print("\nCosa fai? [N/S/E/W | I=Inventario | P=Pozione | Q=Fuggi]: ");
            String cmd = Console.readString().trim().toUpperCase();

            switch (cmd) {
                case "N": muoviti(Room.NORTH); break;
                case "S": muoviti(Room.SOUTH); break;
                case "E": muoviti(Room.EAST);  break;
                case "W": muoviti(Room.WEST);  break;
                case "I": mostraInventario();  break;
                case "P": usaPozione();        break;
                case "Q":
                    OutputUtils.print("Fuggi dal dungeon abbandonando l'impresa!");
                    return false;
                default:
                    OutputUtils.print("Comando non riconosciuto. Usa N/S/E/W, I, P o Q.");
            }
        }

        return false;
    }

    private void muoviti(int direzione) {
        if (currentRoom.haNemiciVivi()) {
            OutputUtils.print("Non puoi fuggire! Ci sono ancora nemici in questa stanza!");
            return;
        }

        Room destinazione = currentRoom.uscitaIn(direzione);
        if (destinazione != null) {
            OutputUtils.print("Ti dirigi verso " + nomeDirezione(direzione) + "...");
            currentRoom = destinazione;
        } else {
            OutputUtils.print("Non c'è nulla in quella direzione. Un muro solido blocca il passaggio.");
        }
    }

    private void gestisciEventoStanza() {
        if (currentRoom.isCompletata()) return;

        switch (currentRoom.getTipo()) {
            case COMBATTIMENTO:
                if (currentRoom.haNemiciVivi()) {
                    OutputUtils.print("--- COMBATTIMENTO! ---");
                    Enemy nemico = currentRoom.getPrimoNemicoVivo();
                    boolean vittoria = Combat.avvia(pg, nemico, bonusClasse);
                    if (vittoria) {
                        pg.aggiungiOro(nemico.getRicompensaOro());
                        OutputUtils.print("Guadagni " + nemico.getRicompensaOro() + " monete d'oro!");
                        raccogliOggetti();
                        if (!currentRoom.haNemiciVivi()) currentRoom.setCompletata(true);
                    }
                }
                break;

            case BOSS:
                if (currentRoom.haNemiciVivi()) {
                    OutputUtils.print("--- SCONTRO FINALE CON IL BOSS! ---");
                    Enemy boss = currentRoom.getPrimoNemicoVivo();
                    boolean vittoria = Combat.avvia(pg, boss, bonusClasse);
                    if (vittoria) {
                        pg.aggiungiOro(boss.getRicompensaOro());
                        OutputUtils.print("Il Boss cade! Guadagni " + boss.getRicompensaOro() + " monete d'oro!");
                        raccogliOggetti();
                        currentRoom.setCompletata(true);
                    }
                }
                break;

            case TRAPPOLA:
                gestisciTrappola();
                break;

            case TESORO:
                if (currentRoom.haOggetti()) {
                    OutputUtils.print("--- STANZA DEL TESORO ---");
                    raccogliOggetti();
                    currentRoom.setCompletata(true);
                }
                break;

            case SICURA:
                OutputUtils.print("--- LUOGO SICURO ---");
                OutputUtils.print("L'energia del santuario ti ristora. Recuperi 5 PF.");
                pg.riceviCura(5);
                raccogliOggetti();
                currentRoom.setCompletata(true);
                break;

            default:
                break;
        }
    }

    private void gestisciTrappola() {
        OutputUtils.print("--- TRAPPOLA ---");
        OutputUtils.print("Come reagisci?");
        OutputUtils.print("1. [F] Forza bruta (blocchi le lame)");
        OutputUtils.print("2. [D] Destrezza   (schivi) +2 competenza");
        OutputUtils.print("3. [C] Costituzione (incassi e resisti)");
        System.out.print("Scelta (1/2/3): ");
        int scelta = Console.readInt();

        int mod = 0, bonus = 0, cd = 13;

        switch (scelta) {
            case 1: mod = pg.getModForza();       OutputUtils.print("Usi la Forza..."); break;
            case 2: mod = pg.getModDestrezza(); bonus = 2; OutputUtils.print("Usi la Destrezza! +2 competenza."); break;
            case 3: mod = pg.getModCostituzone(); OutputUtils.print("Usi la Costituzione..."); break;
            default: OutputUtils.print("Esiti! Nessun bonus.");
        }

        boolean successo = TiroAbilita.eseguiControCD(mod + bonus, cd);
        if (successo) {
            OutputUtils.print("Hai superato la trappola senza danni!");
        } else {
            int danno = Dices.tira(6) + 2;
            pg.riceviDanno(danno);
            OutputUtils.print("Colpito dalla trappola! Subisci " + danno + " danni. PF: " + pg.getPuntiFerita() + "/" + pg.getPuntiFeritaMax());
        }
        currentRoom.setCompletata(true);
    }

    private void raccogliOggetti() {
        if (!currentRoom.haOggetti()) return;

        OutputUtils.print("Oggetti trovati:");
        List<Item> oggetti = new ArrayList<>(currentRoom.getOggetti());
        for (Item item : oggetti) {
            OutputUtils.print("  • " + item);
            String nome = item.getNome().toLowerCase();
            if (nome.contains("pozione")) {
                pg.aggiungiPozione();
                OutputUtils.print("    → Pozione aggiunta!");
            } else if (nome.contains("monete") || nome.contains("oro") || nome.contains("borsa")
                    || nome.contains("gemma") || nome.contains("tesoro") || item.getTipo().equals("TESORO")) {
                pg.aggiungiOro(item.getValore());
                OutputUtils.print("    → +" + item.getValore() + " monete d'oro!");
            } else {
                pg.aggiungiItem(item);
                OutputUtils.print("    → Aggiunto all'inventario.");
            }
        }
        while (currentRoom.haOggetti()) currentRoom.rimuoviOggetto(0);
    }

    private void mostraInventario() {
        OutputUtils.print("\n=== INVENTARIO ===");
        OutputUtils.print(pg.toString());
        if (pg.getInventario().isEmpty()) {
            OutputUtils.print("L'inventario è vuoto (solo oro e pozioni).");
        } else {
            pg.getInventario().forEach(i -> OutputUtils.print("  - " + i));
        }
        OutputUtils.print("==================");
    }

    private void usaPozione() {
        if (pg.getNumeroPozioni() <= 0) {
            OutputUtils.print("Non hai pozioni!");
            return;
        }
        int cura = Dices.tira(4) + Dices.tira(4) + 2;
        pg.riceviCura(cura);
        pg.rimuoviPozione();
        OutputUtils.print("Bevi una pozione! +" + cura + " PF → " + pg.getPuntiFerita() + "/" + pg.getPuntiFeritaMax() + " | Pozioni rimaste: " + pg.getNumeroPozioni());
    }

    private String nomeDirezione(int dir) {
        switch (dir) {
            case Room.NORTH: return "Nord";
            case Room.SOUTH: return "Sud";
            case Room.EAST:  return "Est";
            case Room.WEST:  return "Ovest";
            default:         return "?";
        }
    }
}