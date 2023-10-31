package com.example.poketracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
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
            R.id.natnum_row_TV,
            R.id.name_row_TV,
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
            PokeTrackerDBProvider.COLUMN1_NAME,
            PokeTrackerDBProvider.COLUMN2_NAME,
            PokeTrackerDBProvider.COLUMN3_NAME,
            PokeTrackerDBProvider.COLUMN4_NAME,
            PokeTrackerDBProvider.COLUMN5_NAME,
            PokeTrackerDBProvider.COLUMN6_NAME,
            PokeTrackerDBProvider.COLUMN7_NAME,
            PokeTrackerDBProvider.COLUMN8_NAME,
            PokeTrackerDBProvider.COLUMN9_NAME,
            PokeTrackerDBProvider.COLUMN10_NAME,
    };

    TextView natnum_row_tv, name_row_tv, species_row_tv, gender_row_tv, height_row_tv, weight_row_tv, level_row_tv, stat_hp_row_tv, stat_atk_row_tv, stat_def_row_tv;

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

        my_cursor = getContentResolver().query(PokeTrackerDBProvider.CONTENT_URI, col_names,
                null, null, null);

        Log.i("test", "Testing to see where I went wrong");

        pokelist_lv = findViewById(R.id.pokelist_LV);
        registerForContextMenu(pokelist_lv);

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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.db_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        /*
        String selected_clause = PokeTrackerDBProvider.COLUMN1_NAME + " = ? " + " AND " +
                PokeTrackerDBProvider.COLUMN2_NAME + " = ? " + " AND " +
                PokeTrackerDBProvider.COLUMN3_NAME + " = ? " + " AND " +
                PokeTrackerDBProvider.COLUMN4_NAME + " = ? " + " AND " +
                PokeTrackerDBProvider.COLUMN5_NAME + " = ? " + " AND " +
                PokeTrackerDBProvider.COLUMN6_NAME + " = ? " + " AND " +
                PokeTrackerDBProvider.COLUMN7_NAME + " = ? " + " AND " +
                PokeTrackerDBProvider.COLUMN8_NAME + " = ? " + " AND " +
                PokeTrackerDBProvider.COLUMN9_NAME + " = ? " + " AND " +
                PokeTrackerDBProvider.COLUMN10_NAME + " = ? "
                ;

        String[] selected_arg = {
                natnum_row_tv.getText().toString().trim(),
                name_row_tv.getText().toString().trim(),
                species_row_tv.getText().toString().trim(),
                gender_row_tv.getText().toString().trim(),
                height_row_tv.getText().toString().trim(),
                weight_row_tv.getText().toString().trim(),
                level_row_tv.getText().toString().trim(),
                stat_hp_row_tv.getText().toString().trim(),
                stat_atk_row_tv.getText().toString().trim(),
                stat_def_row_tv.getText().toString().trim(),
        };

         */

        String selected_clause = PokeTrackerDBProvider.COLUMN1_NAME + " = ? ";

        natnum_row_tv = findViewById(R.id.natnum_row_TV);

        String[] selected_arg = {
                natnum_row_tv.getText().toString().trim()
        };


        getContentResolver().delete(PokeTrackerDBProvider.CONTENT_URI, selected_clause, selected_arg);
        getContentResolver().query(PokeTrackerDBProvider.CONTENT_URI, null, null, null);
        //getContentResolver().query(PokeTrackerDBProvider.CONTENT_URI, null, null,null,null);

        Toast.makeText(getApplicationContext(), "Entry has been deleted", Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }
}