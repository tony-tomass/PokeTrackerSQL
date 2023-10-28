package com.example.poketracker;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;

import java.util.LinkedList;

public class PokeTrackerDB extends AppCompatActivity {

    ListView pokelist_lv;
    Button back_bt;
    Cursor my_cursor;
    int[] ids_et = new int[] {
            R.id._id,
            R.id.name_row_TV,
            R.id.natnum_row_TV,
            R.id.species_row_TV,
            R.id.gender_row_TV,
            R.id.height_row_TV,
            R.id.weight_row_TV,
            R.id.level_row_TV,
            R.id.stat_hp_row_TV,
            R.id.stat_atk_row_TV,
            R.id.stat_def_row_TV,
    };

    String[] col_names = new String[] {
            "_id",
            PokeTrackerDBProvider.COLUMN2_NAME,
            PokeTrackerDBProvider.COLUMN1_NAME,
            PokeTrackerDBProvider.COLUMN3_NAME,
            PokeTrackerDBProvider.COLUMN4_NAME,
            PokeTrackerDBProvider.COLUMN5_NAME,
            PokeTrackerDBProvider.COLUMN6_NAME,
            PokeTrackerDBProvider.COLUMN7_NAME,
            PokeTrackerDBProvider.COLUMN8_NAME,
            PokeTrackerDBProvider.COLUMN9_NAME,
            PokeTrackerDBProvider.COLUMN10_NAME,
    };

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

        my_cursor = getContentResolver().query(PokeTrackerDBProvider.CONTENT_URI, null,
                null, null, null);

        pokelist_lv = findViewById(R.id.pokelist_LV);
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                getApplicationContext(),
                R.layout.row,
                my_cursor,
                col_names,
                ids_et,
                0
                );

        back_bt = findViewById(R.id.back_BT);
        back_bt.setOnClickListener(back_listener);

        /*
        LinkedList<String> placeholder = new LinkedList<>();
        placeholder.add("pizza");
        placeholder.add("tomato");
        placeholder.add("cheese");
        ArrayAdapter<String> adapter_placeholder = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, placeholder);
         */
        pokelist_lv.setAdapter(simpleCursorAdapter);
    }

    public void updateListUI() {
        my_cursor = getContentResolver().query(PokeTrackerDBProvider.CONTENT_URI, null,
                null, null, null);
        //pokemon = new LinkedList<>();
    }

}