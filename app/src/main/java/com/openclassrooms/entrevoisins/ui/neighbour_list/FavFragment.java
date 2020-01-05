package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.AddFavNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class FavFragment extends Fragment {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private List<Neighbour> mFavNeighbours;
    private RecyclerView mRecyclerView;




    /**
     * Create and return a new instance
     * @return @{@link FavFragment}
     */
    public static FavFragment newInstance() {
        FavFragment fragment = new FavFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));


        initFavList();
         if(!mFavNeighbours.isEmpty())
                    initFavList();
        else System.out.println("Liste de favoris vide");
        //Neighbour neighbour = mNeighbours.get(view.getId());


        return view;
    }




    /**
     * Init the List of neighbours
     */
    private void initList() {
        mNeighbours = mApiService.getNeighbours();
        //mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(getContext(), mNeighbours));
    }

    private void initFavList() { // <!>A invoquer à condition que la liste mNeighbours !=null
        initList();
        mFavNeighbours= mNeighbours;
        mFavNeighbours.clear();


            for (Neighbour voisin : mFavNeighbours) {
                if (voisin.getFavoris()) mFavNeighbours.add(voisin);
            }
            mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(getContext(), mFavNeighbours));

            System.out.println("Nombre de favoris: " + mFavNeighbours.size());

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */

    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour);
        initList();
    }



    /**
     * Quand l'utilisateur clique sur le bouton "Favoris"
     * @param evennement
     */
    @Subscribe
    public void onAddFavNeighbour(AddFavNeighbourEvent evennement){
        mRecyclerView.getAdapter().notifyDataSetChanged();

        initFavList();
        System.out.println("onAddFavNeighbour utilisé");

    }

}
