package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

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
    public List<Neighbour> getFav() {
        // Doit récupérer tous les neighbours avec Favoris == true;
        for (Neighbour voisin : getNeighbours()){
            if (voisin.getFavoris())
                getFav().add(voisin);
        }
        return getFav();
    }

    @Override
    public void deleteFav(Neighbour neighbour) {
        neighbour.setFavoris(false);
    }

    @Override
    public Neighbour get1Neighbour(int numId) {
        //TODO: Ne doit pas retourner "null" ?
        System.out.println("DummyNeighbourApiService | numId= " + numId);
        return null;
    }


}
