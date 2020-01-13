package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public void addFavNeighbour(Neighbour neighbour) {
        neighbour.setFavoris(true);
    }

    @Override
    public void deleteFav(Neighbour neighbour) {
        neighbour.setFavoris(false);
    }

    @Override
    public List<Neighbour> getFavNeighbours() {
        ArrayList<Neighbour> listFav = new ArrayList<>();
        // Doit récupérer tous les neighbours avec Favoris == true;
        for (Neighbour voisin : getNeighbours()) {

            if (voisin.getFavoris() && !listFav.contains(voisin))
                 listFav.add(voisin);
            else deleteFav(voisin);
        }

        return listFav;
    }


    @Override
    public Neighbour get1Neighbour(int numId) {

        System.out.println("DummyNeighbourApiService | numId= " + numId);
        return neighbours.get(numId);
    }


}
