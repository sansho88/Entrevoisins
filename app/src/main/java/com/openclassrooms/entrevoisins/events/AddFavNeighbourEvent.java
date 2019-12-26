package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

public class AddFavNeighbourEvent implements NeighbourApiService {

    public Neighbour mNeighbour;
//TODO: Appeler la méthode de l'api NeighbourApiService: addFavNeighbour()
    public void AddFavNeighbourEvent(Neighbour mNeighbour){
        this.mNeighbour = mNeighbour;
        addFavNeighbour(mNeighbour);

    }

    @Override
    public List<Neighbour> getNeighbours() {
        return null;
    }

    @Override
    public void deleteNeighbour(Neighbour neighbour) {

    }

    @Override
    public void addFavNeighbour(Neighbour neighbour) {
        mNeighbour.setFavoris(true);
        System.out.println(mNeighbour + " ajouté à la liste des favoris");
    }

    @Override
    public List<Neighbour> getFav() {
        return null;
    }

    @Override
    public void deleteFav(Neighbour neighbour) {
        mNeighbour.setFavoris(false);
        System.out.println(mNeighbour + " supprimé de la liste des favoris");
    }
}
