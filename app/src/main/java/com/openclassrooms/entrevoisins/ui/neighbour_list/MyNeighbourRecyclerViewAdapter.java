package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.content.Context;
import android.content.Intent;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.ProfileNeighbourActivity;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.AddFavNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v4.content.ContextCompat.startActivity;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder>  {

    private final List<Neighbour> mNeighbours;
    public static final String CONTACT_BUNDLE = "$CONTACT$";


    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mNeighbours = items;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        //System.out.println("[DEBUG] MyNeighVA créé");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);
        // holder.mFavButton.(neighbour.getFavoris());
       /* Glide.with(holder.mFavButton.getContext()) //Touche perso'
                .load(neighbour.getFavoris())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mFavButton); */

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
            }
        });
        holder.mFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View bouton) {
                Intent favorisIntent = new Intent();
                EventBus.getDefault().post(new AddFavNeighbourEvent());

                //TODO : Add to Liste Favoris
                System.out.println("---------------------------");
                if(!neighbour.getFavoris()) {

                    neighbour.setFavoris(true);
                    System.out.println(holder.mNeighbourName.getText() + " ajouté aux favoris" );
                    holder.mFavButton.setChecked(true);
                    System.out.println("case cochée? " + holder.mFavButton.isChecked());

                    favorisIntent.putExtra(neighbour.getId().toString(),0);


                }
                else{
                    neighbour.setFavoris(false);
                    System.out.println(holder.mNeighbourName.getText() + " est supprimé des favoris" );
                    holder.mFavButton.setChecked(false);
                    System.out.println("case cochée? " + holder.mFavButton.isChecked());
                    favorisIntent.removeExtra(neighbour.getId().toString());
                }
               // EventBus.getDefault().post(new AddFavNeighbourEvent());

                System.out.println("----------------------------");



            }
        });

        /**
         * Quand cliqué, le profil détaillé correspondant à la personne s'affichera
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Profil cliqué!: " + holder.mNeighbourName.getText() + "\nID du profil:  ");

               /* Intent contactIntent = new Intent(this, ProfileNeighbourActivity.class); //paramétrer l'activité
                //TODO Afficher le profil
                contactIntent.putExtra(CONTACT_BUNDLE, neighbour.getId());
                startActivity(contactIntent); */

            }
        });
    }




    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;
        @BindView (R.id.item_list_fav_button)
        public CheckBox mFavButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
