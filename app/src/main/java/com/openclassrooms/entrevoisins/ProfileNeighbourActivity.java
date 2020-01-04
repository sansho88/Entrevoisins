package com.openclassrooms.entrevoisins;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.AddFavNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileNeighbourActivity extends AppCompatActivity {
        @BindView(R.id.activity_profile_avatar_image)
         ImageView avatar_img;
        @BindView(R.id.activity_profile_nomAvatar_text)
         TextView  nomAvatar_txt;
        @BindView(R.id.activity_profile_nomInfos_text)
         TextView nomInfos_txt;
        @BindView(R.id.activity_profile_description_text)
        TextView description_txt;
        @BindView(R.id.aPropos_txt)
        TextView aPropos_txt;
        @BindView(R.id.activity_profile_favoris_floatingButton)
        ImageButton favButton;
        @BindView(R.id.activity_profile_back)
        ImageButton backButton;


        //TODO:Créer le bouton de retour en arrière

        private Neighbour neighbour;
        private NeighbourApiService neighbourApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_neighbour);
        ButterKnife.bind(this);

        neighbourApiService = DI.getNeighbourApiService();
        int idIntent = (getIntent().getIntExtra("$CONTACT$", 0))-1;
        neighbour = neighbourApiService.get1Neighbour(idIntent); //En résumé (un peu dégueu'): get1neighbour(int id)  = List<Neighbour>.get(id);


        //System.out.println("Profil appartenant à : " + neighbour.getName()+ "; ID: " + neighbour.getId() + "; idIntent: " + idIntent);

        /**
         * Affecte chaque view à son élément du contact correspondant
          */
        nomAvatar_txt.setText(neighbour.getName());
        nomInfos_txt.setText(nomAvatar_txt.getText());
        description_txt.setText("ID du profil: " + neighbour.getId() + "\nDans les favoris?: " + neighbour.getFavoris());

        /**
         * Affiche la photo du profil
         */
        Glide.with(this).load(neighbour.getAvatarUrl()).into(avatar_img);


        /**
         *Ajuste le visuel du bouton Favoris
         */
        favState();

        /**
         * Ajoute le contact aux favoris grace à l'étoile jaune
         */
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddFavNeighbour();
            }
        });

        /**
         * Retour en arrière
         */
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
    //TODO: Faire en sorte que les Subscribe rafraichissent le statut du Favoris
    @Subscribe
    void onAddFavNeighbour() {
        EventBus.getDefault().post(new AddFavNeighbourEvent(neighbour));

    }

    @Subscribe
    void favState(){
        if (neighbour.getFavoris()) {
            favButton.setImageResource(R.drawable.ic_star_white_24dp);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
 //       EventBus.getDefault().register(this); //(crash de l'appli')
    }

    @Override
    public void onStop() {
        super.onStop();
   //     EventBus.getDefault().unregister(this);  //(crash de l'appli')
    }

    //@Subscribe
    //AddFavNeighbourEvent



}
