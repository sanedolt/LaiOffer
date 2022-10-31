package com.laioffer.OOD.BlackJack;

public class BlackJackGameSimulator {
    private Deck deck;
    private Player player;
    private Player hostPlayer;

    public BlackJackGameSimulator(){
        this.deck = new Deck();
        this.player = new Player("SongGeGe");
        this.hostPlayer = new HostPlayer("LongGeGe", 5);
        deck.shuffle();
        player.hit(deck);
        player.hit(deck);
        hostPlayer.hit(deck);
        hostPlayer.hit(deck);
        System.out.println("Game start, initial status:");
        System.out.println(this.player.getName()+"'s hand: ");
        player.currentStatus();
        System.out.println();
        System.out.println(this.hostPlayer.getName()+"'s hand: ");
        hostPlayer.currentStatus();
        System.out.println();
    }

    private void currentStats(){
        System.out.println(this.player.getName()+"'s hand: ");
        player.currentStatus();
        System.out.println();
        System.out.println(this.hostPlayer.getName()+"'s hand: ");
        hostPlayer.currentStatus();
        System.out.println();
    }

    public void simulate(){
        if(player.isBlackJack() && hostPlayer.isBlackJack()){
            System.out.println("----- Draw -----");
            currentStats();
            return;
        } else if(player.isBlackJack()){
            System.out.println("--- Player Win ---");
            currentStats();
            return;
        } else if(hostPlayer.isBlackJack()){
            System.out.println("--- Host Win ---");
            currentStats();
            return;
        }

        System.out.println("---------- Player Round ----------");
        while (player.decideAction(deck) == Action.Hit) {
            if (player.isBusted()) {
                player.currentStatus();
                System.out.println("---------- Host Win ----------");
                return;
            }
        }
        player.currentStatus();

        System.out.println("---------- Host Round ----------");
        while (hostPlayer.decideAction(deck) == Action.Hit) {
            if (hostPlayer.isBusted()) {
                hostPlayer.currentStatus();
                System.out.println("---------- Player Win ----------");
                return;
            }
        }
        hostPlayer.currentStatus();

        int ps = player.score();
        int hs = hostPlayer.score();
        if (ps < hs){
            System.out.println("---------- Host Win ----------");
        } else if (ps > hs) {
            System.out.println("---------- Player Win ----------");
        } else { // ps==hs
            System.out.println("---------- Draw ----------");
        }
    }

    public static void main(String[] args) {
        BlackJackGameSimulator simulator = new BlackJackGameSimulator();
        simulator.simulate();
    }
}
