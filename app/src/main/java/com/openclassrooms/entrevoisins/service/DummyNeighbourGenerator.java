package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {

    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "Saint pierre du mont à 5km", "+33 6 86 57 90 14", "www.facebook.fr/caroline"),
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e", "Mazerolles à 6.2km", "+33 6 74 52 87 96", "www.facebook.fr/jackouille.fripouille"),
            new Neighbour(3, "Chloé", "http://i.pravatar.cc/150?u=a042581f4e29026704f", "Grand Moun à 5.4km", "+33 7 52 36 98 56", "www.facebook.fr/chlochette"),
            new Neighbour(4, "Vincent", "http://i.pravatar.cc/150?u=a042581f4e29026704a", "Saint pierre du mont à 5km", "+33 6 87 52 41 36", "www.facebook.fr/vincent"),
            new Neighbour(5, "Elodie", "https://i.pravatar.cc/150?img=9", "Campet et Lamolere à 8.1km", "+33 6 96 36 52 25", "www.facebook.fr/elodie"),
            new Neighbour(6, "Sylvain", "http://i.pravatar.cc/150?u=a042581f4e29026704c", "Saint Perdon à 6.3km", "+33 7 82 64 78 94", "www.facebook.fr/sylvain"),
            new Neighbour(7, "Laetitia", "http://i.pravatar.cc/150?u=a042581f4e29026703d", "Paix Taoushnok à +∞km", "+33 6 02 40 56 81", "www.facebook.fr/laetitia.nddl"),
            new Neighbour(8, "Ken", "http://i.pravatar.cc/150?u=a042581f4e29026703b", "Saint Vivant de l'Enfer à 666km", "+33 6 23 40 52 12", "www.facebook.fr/omaewamou.shindeiru"),
            new Neighbour(9, "Delphine", "http://i.pravatar.cc/150?img=40", "Sainte Baignoire à 18km", "+33 6 12 50 13 45", "www.facebook.fr/delphine.jolie"),
            new Neighbour(10, "Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d", "Benquet à 15km", "+33 6 96 30 25 40", "www.facebook.fr/emmaeva"),
            new Neighbour(11, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d", "Camping Les Flots Bleus à 85km", "+33 6 87 52 14 10", "www.facebook.fr/patrick"),
            new Neighbour(12, "Ludovic", "http://i.pravatar.cc/150?u=a042581f3e39026702d", "Bascons à 12.6km", "+33 6 20 06 98 49", "www.facebook.fr/ludovic")
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }
}
