package com.nonsobiose.medic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private CardView mProfileCard;
    private CardView mAddMedCard;
    private CardView mViewMedCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProfileCard = findViewById(R.id.profile_card);
        mAddMedCard = findViewById(R.id.add_med_card);
        mViewMedCard = findViewById(R.id.view_med_card);

        mAddMedCard.setOnClickListener(view -> startActivity(new Intent(this, AddMedicationActivity.class)));
        mViewMedCard.setOnClickListener(view -> startActivity(new Intent(this, ViewMedActivity.class)));
    }
}
