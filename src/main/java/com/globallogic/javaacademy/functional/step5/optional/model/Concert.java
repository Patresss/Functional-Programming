package com.globallogic.javaacademy.functional.step5.optional.model;

public class Concert {

    private final String name;
    private final MusicBand musicBand;

    public Concert(String name, MusicBand musicBand) {
        this.name = name;
        this.musicBand = musicBand;
    }

    public String getName() {
        return name;
    }

    public MusicBand getMusicBand() {
        return musicBand;
    }
}
