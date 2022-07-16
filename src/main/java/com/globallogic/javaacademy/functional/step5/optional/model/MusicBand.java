package com.globallogic.javaacademy.functional.step5.optional.model;

public class MusicBand {

    private final String name;
    private final MusicStar musicStar;

    public MusicBand(String name, MusicStar musicStar) {
        this.name = name;
        this.musicStar = musicStar;
    }

    public String getName() {
        return name;
    }

    public MusicStar getMusicStar() {
        return musicStar;
    }

    @Override
    public String toString() {
        return "MusicBand{" +
                "name='" + name + '\'' +
                ", musicStar=" + musicStar +
                '}';
    }
}
