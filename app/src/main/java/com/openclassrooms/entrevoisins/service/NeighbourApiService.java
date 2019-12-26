package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();


    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Ajoute le voisin aux favoris
     * @param neighbour
     */
    void addFavNeighbour (Neighbour neighbour);

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getFav();

    /**
     * Deletes a neighbour from favorites
     * @param neighbour
     */
    void deleteFav(Neighbour neighbour);


}
