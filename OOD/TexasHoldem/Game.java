package com.laioffer.OOD.TexasHoldem;

import java.util.List;

public class Game {
    private List<Player> players;
    private Deck deck;
    private int totalBet;
    private int minimumBet;
    private int currentBet;
    private int numCards;
    private int numActivePlayers;

    public Game(List<Player> players, int minBet) {
        this.players = players;
        this.deck = new Deck();
        this.numActivePlayers = players.size();
        this.minimumBet = minBet;
    }
    public void addPlayer(Player player) {
        this.players.add(player);
        this.numActivePlayers++;
    }
    public void startGame() {
        numCards = 0;
        while (numCards < 5) {
            currentBet = 0;
            Card c = null;
            if (numCards>=2) {c=deck.dealCard();}
            boolean first = true;
            for (Player player: players) {
                if (player.getIsFold()) {
                    continue;
                }
                if (numCards < 2) {
                    player.getHand().addCard(deck.dealCard());
                } else { // common card
                    player.getHand().addCard(c);
                }
                Action act = player.getPlayAction(first);
                playAction(player, act);
                first=false;
            }
            if (numActivePlayers == 1) {
                // quite the while loop
                // the last player is the winner;
                break;
            }
            numCards++;
        }
        Player winner = decideWinner(players);
        awardWinner(totalBet, winner);
    }
    private Player decideWinner(List<Player> players) {
        // compare the hands from active players
        // and find the highest score
    }
    private void awardWinner(int totalBet, Player winner) {
        winner.addBalance(totalBet);
    }
    private void playAction(Player player, Action act) {
        switch(act) {
            case Fold: {
                player.fold();
                numActivePlayers--;
                break;
            }
            case Check: {
                break;
            }
            case Bet: {
                currentBet = player.getBetInput(minimumBet);
                player.playBet(currentBet);
                totalBet += currentBet;
                break;
            }
            case Match: {
                player.match(currentBet);
                break;
            }
            case Raise: {
                int raise = player.getBetInput(currentBet*0.5);
                currentBet += raise;
                totalBet += raise;
                // askForMatch(players, raise);
                break;
            }
            case Allin: {
                // player.playBet(player.balance)
            }
        }
    }
}

