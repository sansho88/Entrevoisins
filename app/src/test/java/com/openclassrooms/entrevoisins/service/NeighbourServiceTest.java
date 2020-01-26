package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }


    @Test
    public void upDateNeighbourToFavoritesWithSuccess() {
        Neighbour neighbour = service.get1Neighbour(0);
        service.addFavNeighbour(neighbour);
        assertTrue(service.get1Neighbour(0).getFavoris());
        service.deleteFav(neighbour);
        assertFalse(service.get1Neighbour(0).getFavoris());
    }

    @Test
    public void getOnlyFavNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        int nbFavNeighbours = 0;
        neighbours.get(0).setFavoris(true);
        neighbours.get(neighbours.size()-1).setFavoris(true);
        for (int i = neighbours.size()-1; i >= 0; i--) {
            if (neighbours.get(i).getFavoris()) nbFavNeighbours++;
        }
        assertFalse(nbFavNeighbours!=2);

    }


}
