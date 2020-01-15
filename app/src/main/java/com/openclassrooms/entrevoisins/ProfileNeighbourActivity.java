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
    @BindView(R.id.activity_profile_description_text)
    TextView description_txt;
    @BindView(R.id.aPropos_txt)
    TextView aPropos_txt;
    @BindView(R.id.activity_profile_favoris_floatingButton)
    ImageButton favButton;
    @BindView(R.id.activity_profile_back)
    ImageButton backButton;


    private Neighbour neighbour;
    private NeighbourApiService neighbourApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_neighbour);
        ButterKnife.bind(this);

        neighbourApiService = DI.getNeighbourApiService();
        int idIntent = (getIntent().getIntExtra("$CONTACT$", 0));
        //TODO: Corretcion bug: après suppression contact, l'id du contact récupéré est égal à la position initiale avant suppression (mais l'iD intent est correct)
        neighbour = neighbourApiService.get1Neighbour(idIntent - 1);


        System.out.println("Profil appartenant à : " + neighbour.getName() + "; ID: " + neighbour.getId() + "; idIntent: " + idIntent);

        /**
         * Affecte chaque view à son élément du contact correspondant
         */
        nomAvatar_txt.setText(neighbour.getName());
        nomInfos_txt.setText(nomAvatar_txt.getText());
        description_txt.setText("ID du profil: " + neighbour.getId() + "\nDans les favoris? " + neighbour.getFavoris() + "\nID Intent: " + idIntent);

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
    public void onAddFavNeighbour(Neighbour neighbour) {
        EventBus.getDefault().post(new AddFavNeighbourEvent(neighbour));

        //TODO: Actualiser l'onglet "Favorites" (actuallement rafraichi après une suppression de contact)
        System.out.println("Modification du favoris depuis le profil");


    }

    //TODO: Faire en sorte que les Subscribe rafraichissent le statut du Favoris
    @Subscribe
    void favState() {
        if (neighbour.getFavoris()) {
            favButton.setImageResource(R.drawable.ic_star_white_24dp);
            System.out.println("Objets Neighbours en favoris: " + neighbourApiService.getFavNeighbours());
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
