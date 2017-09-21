package com.jueva.gameOfCities;

import com.jueva.gameOfCities.game.City;
import com.jueva.gameOfCities.game.Game;
import com.jueva.gameOfCities.game.players.ComputerPlayer;
import com.jueva.gameOfCities.game.players.Player;
import com.jueva.gameOfCities.game.players.RealPlayer;

import java.util.LinkedList;

public class Start {
    public static void main(String[] args) {

        // creating new game
        Game game = new Game();

        // creating few players (we can play with different count of players from one to unlimited and mix them between computers and real players)
        Player computerPlayer1 = new ComputerPlayer(game, "ComputerPlayer_1");
        Player computerPlayer2 = new ComputerPlayer(game, "ComputerPlayer_2");
        Player realPlayer1 = new RealPlayer("realPlayer_1");
        Player realPlayer2 = new RealPlayer("realPlayer_2");

        // all players are add to list (we can play with more then 2 players)
        LinkedList<Player> allPlayers = new LinkedList<>();
        allPlayers.add(realPlayer1);
        allPlayers.add(computerPlayer1);
        allPlayers.add(computerPlayer2);
        // allPlayers.add(realPlayer2);

        // setting the player list to game
        game.setAllPlayers(allPlayers);
        game.start();

        // while game is not ended, we can continue
        while (!game.isGameIsOver()) {
            City tempCity;

            // get the current player
            Player currentPlayer = game.getCurrentPlayer();
            System.out.println("Player " + currentPlayer.getName() + " calls the city:");
            // try to get city. You can notice, that different players (computer / human) have different implementation of getCity() method
            tempCity = currentPlayer.getCity();
            System.out.println("> " + tempCity);

            //
            System.out.println(game.chekCity(tempCity));

        }

    }
}