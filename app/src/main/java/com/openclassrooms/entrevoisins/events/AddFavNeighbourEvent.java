package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

public class AddFavNeighbourEvent {

    /**
     * Voisin à ajouter aux favoris
     */
    public Neighbour mNeighbour;

    public AddFavNeighbourEvent(Neighbour voisin) {
        this.mNeighbour = voisin;
        if (!mNeighbour.getFavoris()) {
            DI.getNeighbourApiService().addFavNeighbour(mNeighbour);
            this.mNeighbour.setFavoris(true);
        } else {
            DI.getNeighbourApiService().deleteFav(mNeighbour);
            this.mNeighbour.setFavoris(false);
        }


    }

}
