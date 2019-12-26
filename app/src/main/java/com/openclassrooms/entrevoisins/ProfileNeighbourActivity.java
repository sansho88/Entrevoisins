package com.openclassrooms.entrevoisins;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        private Neighbour neighbour;
        private NeighbourApiService neighbourApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_neighbour);
        ButterKnife.bind(this);

        neighbourApiService = DI.getNeighbourApiService();



        int idIntent = getIntent().getIntExtra("$CONTACT$", 0);

         neighbour = neighbourApiService.get1Neighbour(idIntent);

        System.out.println("Profile appartenant à : " + neighbour);
        // Retrouver le contactt entier depuis l'ID entier

        //int id = idIntent.getIntExtra(MyNeighbourRecyclerViewAdapter.CONTACT_BUNDLE,0);

       // nomInfos_txt.setText(idIntent.getStringExtra(MyNeighbourRecyclerViewAdapter.CONTACT_BUNDLE));

        //avatar_img.setImageResource(...);
    }
}
