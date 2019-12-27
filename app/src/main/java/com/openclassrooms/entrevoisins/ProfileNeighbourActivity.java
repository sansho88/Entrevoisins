package com.openclassrooms.entrevoisins;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourFragment;

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
        @BindView(R.id.activity_profile_favoris_imageButton)
         ImageButton favButton;

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


         //Erreur: retourne id+1
        System.out.println("Profil appartenant à : " + neighbour.getName()+ "; ID: " + neighbour.getId() + "; idIntent: " + idIntent); //TODO: Doit afficher le bon contact

        //TODO: Affecter chaque view à son élément du contact correspondant

        nomAvatar_txt.setText(neighbour.getName());
        nomInfos_txt.setText(nomAvatar_txt.getText());
        description_txt.setText("ID du profil: " + neighbour.getId() + "\nDans les favoris?: " + neighbour.getFavoris());
        //TODO: Afficher la photo du profil
        //avatar_img.setImageResource(...);

        //Todo: faire en sorte que le bouton Favoris soit fonctionnel à 100% (ajouter aux favoris une fois le bouton cliqué)
        if (neighbour.getFavoris())
            favButton.setBackgroundColor(0xFF00AAAA);
        else
            favButton.setBackgroundColor(0x00);



    }


}
