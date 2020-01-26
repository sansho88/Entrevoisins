package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class AddFavNeighbourEvent {

    /**
     * Voisin à ajouter aux favoris
     */
    public final Neighbour mNeighbour;

    public AddFavNeighbourEvent(Neighbour voisin) {
        this.mNeighbour = voisin;
    }

}
