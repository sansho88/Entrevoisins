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
        FloatingActionButton favButton;
        @BindView(R.id.activity_profile_back)
        FloatingActionButton backButton;


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
         neighbour = neighbourApiService.get1Neighbour(idIntent);

        //System.out.println("Profil appartenant à : " + neighbour.getName()+ "; ID: " + neighbour.getId() + "; idIntent: " + idIntent);

        // Affecte chaque view à son élément du contact correspondant
        nomAvatar_txt.setText(neighbour.getName());
        nomInfos_txt.setText(nomAvatar_txt.getText());
        description_txt.setText("ID du profil: " + neighbour.getId() + "\nDans les favoris?: " + neighbour.getFavoris());

        //Affiche la photo du profil
        Glide.with(this).load(neighbour.getAvatarUrl()).into(avatar_img);

        //Ajuste le visuel du bouton Favoris
        if (neighbour.getFavoris())
            favButton.setImageResource(R.drawable.ic_star_white_24dp);

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new AddFavNeighbourEvent(neighbour));

                finish();
                overridePendingTransition(0,0);
                startActivity(getIntent());
                overridePendingTransition(0,0);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }



}
