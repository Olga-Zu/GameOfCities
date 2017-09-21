package com.jueva.gameOfCities.game;

import com.jueva.gameOfCities.game.players.Player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Game {
    private static final String PATH_TO_AVAILABLE_CITIES = "resources\\listOfCities.txt";

    // class for keep messages of state for the game
    enum StateOfGame {
        TO_LOW_COUNT_OF_PLAYERS("Count of of players should be not less one"),
        PLAYER_LIST_CANT_BE_NULL("PlayerList can not be null!"),
        PLAYER_LIST_CANT_BE_EMPTY("PlayerList can not be empty!"),
        CANT_CHANGE_PLAYER_LIST("All players already registered!"),

        CITY_IS_NOT_AVAILABLE("Such city is not available for choice!\n"),
        YOURS_CITY_IS_ADOPTED("Yours city is accepted, another player's turn now.\n"),
        CITY_SHOULD_START_FROM("The city should start from letter "),

        HAVE_NO_MOVES_AVAILABLE("There are no more available moves."),
        WINNING_PLAYER("Winning player is ");

        StateOfGame(String state) {
            this.state = state;
        }

        private String state;

        public String getState() {
            return state;
        }

        @Override
        public String toString() {
            return state;
        }
    }

    private final Set<City> famousCities = new HashSet<>();

    // use it like infinite cyclic list
    private LinkedList<Player> allPlayers;
    private Player currentPlayer;

    private boolean gameIsOver = false;
    private City lastCity = null;

    public Game() {
        readAvailableCities();
    }

    public void start() {
        if ( allPlayers == null ) {
            throw new IllegalStateException(StateOfGame.TO_LOW_COUNT_OF_PLAYERS.getState());
        }
        currentPlayer = allPlayers.pop();
        allPlayers.add(currentPlayer);
    }

    public String chekCity(City tempCity) {

        if ( tempCity == null ) {
            gameIsOver = true;
            isGameOver();
            return "";
        }

        if (!famousCities.contains(tempCity)) {
            return StateOfGame.CITY_IS_NOT_AVAILABLE.getState();
        }

        if (lastCity == null) {
            aceptNewLastCity(tempCity);
            if ( isGameOver() ) {
                return "";
            }
            return StateOfGame.YOURS_CITY_IS_ADOPTED.getState();
        }

        char actualCharacter = lastCity.getLastSybol();
        char tempCharacter = tempCity.getFirstSymbol();

        if (actualCharacter != tempCharacter) {
            return StateOfGame.CITY_SHOULD_START_FROM.getState() + lastCity.getLastSybol() + "\n";
        }

        aceptNewLastCity(tempCity);
        if ( isGameOver() ) {
            return "";
        }
        return StateOfGame.YOURS_CITY_IS_ADOPTED.getState();
    }

    private void nextPlayer() {
        currentPlayer = allPlayers.pop();
        allPlayers.add(currentPlayer);
    }

    public boolean isGameOver() {

        if (gameIsOver) {
            endOfGame();
            return true;
        }

        for (City tempCity : famousCities) {
            if ( lastCity.getLastSybol() == tempCity.getFirstSymbol() ) {
                return false;
            }
        }

        endOfGame();
        return true;
    }

    private void endOfGame() {
        gameIsOver = true;
        System.out.println(StateOfGame.HAVE_NO_MOVES_AVAILABLE.getState());
        String playerName;
        if ( allPlayers.size() == 1) {
            playerName = allPlayers.getFirst().getName();
        } else {
            ListIterator<Player> iterator = allPlayers.listIterator(allPlayers.size() - 1);
            playerName = iterator.previous().getName();
        }
        System.out.print( StateOfGame.WINNING_PLAYER.getState());
        System.out.println("\"" + playerName + "\"");
    }

    private void aceptNewLastCity(City newCity) {
        lastCity = newCity;
        nextPlayer();
        famousCities.remove(newCity);
    }

    public boolean isGameIsOver() {
        return gameIsOver;
    }

    public City getLastCity() {
        return lastCity;
    }

    public Set<City> getFamousCities() {
        return famousCities;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setAllPlayers(LinkedList<Player> allPlayers) {

        if (allPlayers == null) {
            throw new IllegalArgumentException(StateOfGame.PLAYER_LIST_CANT_BE_NULL.getState());
        }

        if (allPlayers.isEmpty()) {
            throw new IllegalArgumentException(StateOfGame.PLAYER_LIST_CANT_BE_EMPTY.getState());
        }

        if (this.allPlayers == null) {
            this.allPlayers = allPlayers;
        } else {
            throw new IllegalStateException(StateOfGame.CANT_CHANGE_PLAYER_LIST.getState());
        }
    }

    private void readAvailableCities() {
        try ( BufferedReader reader = new BufferedReader( new FileReader(PATH_TO_AVAILABLE_CITIES)) ){
            while (reader.ready()) {
                String city = reader.readLine();
                famousCities.add(new City(city));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
