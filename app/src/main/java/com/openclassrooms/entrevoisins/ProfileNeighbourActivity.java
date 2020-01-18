package com.openclassrooms.entrevoisins;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.AddFavNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileNeighbourActivity extends AppCompatActivity {
    @BindView(R.id.activity_profile_avatar_image)
    ImageView avatar_img;
    @BindView(R.id.activity_profile_nomAvatar_text)
    TextView nomAvatar_txt;
    @BindView(R.id.activity_profile_nomInfos_text)
    TextView nomInfos_txt;
    @BindView(R.id.activity_profile_adresse_text)
    TextView adresse_txt;
    @BindView(R.id.activity_profile_tph_text)
    TextView tph_text;
    @BindView(R.id.activity_profile_social_text)
    TextView social_text;
    @BindView(R.id.aPropos_txt)
    TextView aPropos_txt;
    @BindView(R.id.activity_profile_favoris_floatingButton)
    ImageButton favButton;
    @BindView(R.id.activity_profile_back)
    ImageButton backButton;

    public static final String CONTACTID = "$CONTACT$";


    private Neighbour neighbour;
    private NeighbourApiService neighbourApiService;
    private int idIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_neighbour);
        ButterKnife.bind(this);

        neighbourApiService = DI.getNeighbourApiService();
        idIntent = getIntent().getIntExtra(CONTACTID, 0);
        neighbour = neighbourApiService.get1Neighbour(idIntent);


        System.out.println("Profil appartenant à : " + neighbour.getName() + "; ID: " + neighbour.getId() + "; idIntent: " + idIntent + "; Place dans la liste: " + neighbourApiService.getNeighbours().indexOf(neighbour) );

        /**
         * Affecte chaque view à son élément du contact correspondant
         */
        nomAvatar_txt.setText(neighbour.getName());
        nomInfos_txt.setText(nomAvatar_txt.getText());
        adresse_txt.setText(neighbour.getAdresse());
        tph_text.setText(neighbour.getNumTph());
        social_text.setText(neighbour.getFbUrl());

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
                onAddFavNeighbour(neighbour);

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

    @Subscribe
    public void onAddFavNeighbour(AddFavNeighbourEvent event) {

        this.neighbour=event.mNeighbour;
        favState();
    }
    public void onAddFavNeighbour(Neighbour neighbour) {
        EventBus.getDefault().post(new AddFavNeighbourEvent(neighbour));

        //TODO: Actualiser l'onglet "Favorites" (actuallement rafraichi après une suppression de contact)
        System.out.println("Modification du favoris depuis le profil");


    }


    void favState() {
        if (neighbour.getFavoris()) {
            favButton.setImageResource(R.drawable.ic_star_white_24dp); //Etoile pleine
        } else favButton.setImageResource(R.drawable.ic_star_border_white_24dp); //Etoile vide

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this); //(crash de l'appli')
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);  //(crash de l'appli')
    }

    //@Subscribe
    //AddFavNeighbourEvent


}
