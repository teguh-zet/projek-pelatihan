package com.project.project.controllers;

import java.util.*;

import com.project.project.model.Song;

public class SongFromJS {
    public static List<Song> panggilMusic() {
        ArrayList<Song> lagu = new ArrayList<>();
        lagu.add(new Song(1, "7Rings", "Ariana Grande", "/image/7ringsss.jpg"));
        lagu.add(new Song(2, "Angel Like You ", "Miley Cyrus", "/image/angel_like_you.jpg"));
        lagu.add(new Song(3, "Back To You", "Selena Gomez", "/image/back to you.jpg"));
        lagu.add(new Song(4, "Born Without A Heart", "faouzia", "/image/bornwithoutaheart.jpeg"));
        lagu.add(new Song(5, "Million To One", "Camila Cabello", "/image/camila_cabello.jpg"));
        lagu.add(new Song(6, "Cartel", "Whisnu Shantika", "/image/cartel.jpg"));
        lagu.add(new Song(7, "Counting Star", "One Republic", "/image/counting_star.jpg"));
        lagu.add(new Song(8, "Fire Work", "Katty Pery", "/image/firework.jpg"));
        lagu.add(new Song(9, "Girls Like You", "Maroon 5", "/image/girl_like_you.jpg"));
        lagu.add(new Song(10, "Good Bye", "Jason Derulo", "/image/goodbye.jpg"));
        lagu.add(new Song(11, "HAPPIER", "Ed Sharren", "/image/happier.jpeg"));
        lagu.add(new Song(12, "Idgaf", "Dua Lipa", "/image/dualipaku.jpg"));
        lagu.add(new Song(13, "I'm A Mess", "Bebe Rexha", "/image/im a mess.jpg"));
        lagu.add(new Song(14, "I Like Me Better", "Lauv", "/image/populerartis1.jpg"));
        lagu.add(new Song(15, "Million Dreams", "Greatest Showman", "/image/million drams.jpg"));
        lagu.add(new Song(16, "Never Enough", "Greatest Showman", "/image/neverenough.jpg"));
        lagu.add(new Song(17, "Wake Me Up", "Avici", "/image/poster_avici_wakemeup.jpg"));
        lagu.add(new Song(18, "We Found Love", "Rihanna", "image/we found a love.jpg"));
        lagu.add(new Song(19, "Without You ", "Avici", "/image/without_you.jpg"));
        lagu.add(new Song(20, "Wolf", "Selena Gomez", "/image/wolf.jpg"));
        return lagu;
    }
}
