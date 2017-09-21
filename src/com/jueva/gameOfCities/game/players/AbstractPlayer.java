package com.jueva.gameOfCities.game.players;

public abstract class AbstractPlayer implements Player, AutoCloseable {

    private String name = "";

    protected AbstractPlayer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void close() throws Exception {
    }

    @Override
    public String toString() {
        return "Player{" + name + '}';
    }
}
