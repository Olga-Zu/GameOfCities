package com.jueva.gameOfCities.game.players;

import com.jueva.gameOfCities.game.City;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RealPlayer extends AbstractPlayer {

    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public RealPlayer(String name) {
        super(name);
    }

    public City getCity() {
        try {
            String tempCity = input.readLine();
            return new City( tempCity );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void close() throws Exception {
        super.close();
        input.close();
    }
}
