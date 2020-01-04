package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

public class AddFavNeighbourEvent  {

    public Neighbour mNeighbour;
//TODO: Appeler la méthode de l'api NeighbourApiService: addFavNeighbour()
    public AddFavNeighbourEvent(Neighbour voisin){
        this.mNeighbour = voisin;
        if (!mNeighbour.getFavoris())
            DI.getNeighbourApiService().addFavNeighbour(mNeighbour);
        else
            DI.getNeighbourApiService().deleteFav(mNeighbour);


    }

}
