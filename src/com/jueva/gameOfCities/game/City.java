package com.jueva.gameOfCities.game;

public class City {

    private final String name;
    private final char[] charsOfCity;

    public City(String name) {
        this.name = name;
        charsOfCity = name.toLowerCase().toCharArray();
    }

    public String getName() {
        return name;
    }

    public char getFirstSymbol() {
        return charsOfCity[0];
    }

    public char getLastSybol(){
        return charsOfCity[charsOfCity.length - 1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        return name.equals(city.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
