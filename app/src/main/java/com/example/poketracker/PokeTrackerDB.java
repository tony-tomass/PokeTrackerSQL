package com.example.poketracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;

public class PokeTrackerDB extends AppCompatActivity {

    ListView pokelist_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_tracker_bd);

        pokelist_lv = findViewById(R.id.pokelist_LV);
        LinkedList<String> placeholder = new LinkedList<>();
        placeholder.add("pizza");
        placeholder.add("tomato");
        placeholder.add("cheese");
        ArrayAdapter<String> adapter_placeholder = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, placeholder);
        pokelist_lv.setAdapter(adapter_placeholder);
    }
}