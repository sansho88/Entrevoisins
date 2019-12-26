package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

public class AddFavNeighbourEvent implements NeighbourApiService {

    public Neighbour mNeighbour;
//TODO: Appeler la méthode de l'api NeighbourApiService: addFavNeighbour()
    public AddFavNeighbourEvent(Neighbour voisin){
        this.mNeighbour = voisin;
        addFavNeighbour(voisin);

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
        if (!neighbour.getFavoris()){
            neighbour.setFavoris(true);
            System.out.println(neighbour.getName() + " a été ajouté à la liste des favoris");
        }else{
           deleteFav(neighbour);
        }
    }

    @Override
    public List<Neighbour> getFav() {
        return null;
    }

    @Override
    public void deleteFav(Neighbour neighbour) {
        neighbour.setFavoris(false);
        System.out.println(neighbour.getName() + " a été supprimé de la liste des favoris");
    }

    @Override
    public Neighbour get1Neighbour(int numId) {
        return null;
    }
}
