package com.jueva.gameOfCities.game.players;

import com.jueva.gameOfCities.game.City;
import com.jueva.gameOfCities.game.Game;

public class ComputerPlayer extends AbstractPlayer {
    private Game game;


    public ComputerPlayer(Game game, String name) {
        super(name);
        this.game = game;
    }

    public City getCity() {

        City currentLastCity = game.getLastCity();

        if ( currentLastCity == null ) {
            for (City tempCity : game.getFamousCities()) {
                return tempCity;
            }
        }

        char currentChar = currentLastCity.getLastSybol();

        for (City tempCity : game.getFamousCities()) {

            char tempChar = tempCity.getFirstSymbol();

            if ( currentChar == tempChar ) {
                return tempCity;
            }
        }

        return null;
    }

}
