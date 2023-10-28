package com.example.poketracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import java.util.LinkedList;

public class PokeTrackerDB extends AppCompatActivity {

    ListView pokelist_lv;
    Button back_bt;

    View.OnClickListener back_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent switch_back = new Intent(getApplicationContext(), ActivityLinear.class);
            startActivity(switch_back);
            //overridePendingTransition();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_tracker_bd);

        pokelist_lv = findViewById(R.id.pokelist_LV);
        back_bt = findViewById(R.id.back_BT);

        back_bt.setOnClickListener(back_listener);

        LinkedList<String> placeholder = new LinkedList<>();
        placeholder.add("pizza");
        placeholder.add("tomato");
        placeholder.add("cheese");
        ArrayAdapter<String> adapter_placeholder = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, placeholder);
        pokelist_lv.setAdapter(adapter_placeholder);
    }


}