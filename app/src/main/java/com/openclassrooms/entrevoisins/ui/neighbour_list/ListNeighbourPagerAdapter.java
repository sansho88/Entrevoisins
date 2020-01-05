package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        //TODO: Faire en sorte que l'onglet selectionné soit bien récupéré
        Fragment fragment;


        switch(position){
            default: fragment= NeighbourFragment.newInstance();
                System.out.println("Onglet My Neighbours  selectionné"); break;
            case 2: fragment= FavFragment.newInstance();
                System.out.println("Onglet Favoris selectionné");break;
        }
        return fragment;
    }

    /**
     * get the number of tabs
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }
}