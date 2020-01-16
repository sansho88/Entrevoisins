package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.content.Context;
import android.content.Intent;
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
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder>  {

    private List<Neighbour> mNeighbours;
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

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));

            }
        });
        /**
         * Ajoute le contact dans l'onglet "Favorite"
         */
        holder.mFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View bouton) {

                //Add to Liste Favoris
                EventBus.getDefault().post(new AddFavNeighbourEvent(neighbour));


                System.out.println("----------------------------");
                System.out.println(neighbour.getName() + " est dans les favoris? " + neighbour.getFavoris() );

                System.out.println("----------------------------");



            }
        });

        /**
         * Quand cliqué, le profil détaillé correspondant à la personne s'affiche
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context mContext = holder.itemView.getContext();
                int neighbourId = neighbour.getId();

                System.out.println("Profil cliqué!: " + holder.mNeighbourName.getText() + ";(" + neighbour.getName() +", ID: " + neighbourId +")");
                Intent contactIntent = new Intent(mContext, ProfileNeighbourActivity.class);
                contactIntent.putExtra("$CONTACT$", neighbourId);

                System.out.println("ID du contact: " + contactIntent.getIntExtra("$CONTACT$",0));
                mContext.startActivity(contactIntent);

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
