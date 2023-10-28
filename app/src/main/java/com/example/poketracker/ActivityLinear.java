package com.example.poketracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.*;

import java.util.LinkedList;

public class ActivityLinear extends AppCompatActivity {

    Button reset_bt, save_bt, switch_table_bt, switch_const_bt, view_db_bt;
    TextView natnum_tv, name_tv, species_tv, height_tv, weight_tv, stat_hp_tv, stat_atk_tv, stat_def_tv, gender_tv, level_tv;
    EditText natnum_et, name_et, species_et, height_et, weight_et, stat_hp_et, stat_atk_et, stat_def_et;
    RadioGroup gender_rg;
    RadioButton male_rb, female_rb, no_gen_rb;
    Spinner level_sp;
    int[] ids_et = new int[] {
            R.id.natnum_ET,
            R.id.name_ET,
            R.id.species_ET,
            R.id.gender_RG,
            R.id.height_ET,
            R.id.weight_ET,
            R.id.level_SP,
            R.id.stat_hp_ET,
            R.id.stat_atk_ET,
            R.id.stat_def_ET,
    };
    int[] ids_tv = new int[] {
            R.id.natnum_TV,
            R.id.name_TV,
            R.id.species_TV,
            R.id.gender_TV,
            R.id.height_TV,
            R.id.weight_TV,
            R.id.stat_hp_TV,
            R.id.stat_atk_TV,
            R.id.stat_def_TV,
    };

    View.OnClickListener reset_bt_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            resetTextColor();
            natnum_et.setText(R.string.natnum_default); //See strings.xml
            name_et.setText(R.string.name_default);
            species_et.setText(R.string.species_default);
            gender_rg.check(R.id.no_gen_RB);
            height_et.setText(R.string.height_default);
            weight_et.setText(R.string.weight_default);
            level_sp.setSelection(0);
            stat_hp_et.setText(R.string.stat_default);
            stat_atk_et.setText(R.string.stat_default);
            stat_def_et.setText(R.string.stat_default);

            Toast.makeText(getApplicationContext(),
                    "Fields have been reset and given their default values.",
                    Toast.LENGTH_SHORT).show();
        }
    };
    View.OnClickListener save_bt_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            resetTextColor();
            if (checkAllErrors() > 0) {
                Toast.makeText(getApplicationContext(),
                        "ERROR: " + checkAllErrors() + " or more empty fields or " +
                                "numbers/character not within limits.",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                ContentValues new_values = new ContentValues();
                new_values.put(PokeTrackerDBProvider.COLUMN1_NAME, natnum_et.getText().toString().trim());
                new_values.put(PokeTrackerDBProvider.COLUMN2_NAME, name_et.getText().toString().trim());
                new_values.put(PokeTrackerDBProvider.COLUMN3_NAME, species_et.getText().toString().trim());

                //https://stackoverflow.com/questions/20138445/how-to-get-the-text-from-radio-button-in-a-radio-group-when-radio-button-checked
                int id = gender_rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(id);
                new_values.put(PokeTrackerDBProvider.COLUMN4_NAME, rb.getText().toString());

                new_values.put(PokeTrackerDBProvider.COLUMN5_NAME, height_et.getText().toString().trim());
                new_values.put(PokeTrackerDBProvider.COLUMN6_NAME, weight_et.getText().toString().trim());
                new_values.put(PokeTrackerDBProvider.COLUMN7_NAME, level_sp.getSelectedItem().toString().trim());
                new_values.put(PokeTrackerDBProvider.COLUMN8_NAME, stat_hp_et.getText().toString().trim());
                new_values.put(PokeTrackerDBProvider.COLUMN9_NAME, stat_atk_et.getText().toString().trim());
                new_values.put(PokeTrackerDBProvider.COLUMN10_NAME, stat_def_et.getText().toString().trim());

                getContentResolver().insert(PokeTrackerDBProvider.CONTENT_URI, new_values);

                Toast.makeText(getApplicationContext(),
                        "Your Pokemon has been successfully recorded into our database.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    View.OnClickListener view_db_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent view_db = new Intent(getApplicationContext(), PokeTrackerDB.class);
            startActivity(view_db);
        }
    };

    /*
    View.OnClickListener switch_table_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent switching = new Intent(getApplicationContext(), ActivityTable.class);
            startActivity(switching);
        }
    };

    View.OnClickListener switch_const_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent switching = new Intent(getApplicationContext(), ActivityConst.class);
            startActivity(switching);
        }
    };
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);

        //TextView ref
        natnum_tv = findViewById(R.id.natnum_TV);
        name_tv = findViewById(R.id.name_TV);
        species_tv = findViewById(R.id.species_TV);
        gender_tv = findViewById(R.id.gender_TV);
        height_tv = findViewById(R.id.height_TV);
        weight_tv = findViewById(R.id.weight_TV);
        level_tv = findViewById(R.id.level_TV);
        stat_hp_tv = findViewById(R.id.stat_hp_TV);
        stat_atk_tv = findViewById(R.id.stat_atk_TV);
        stat_def_tv = findViewById(R.id.stat_def_TV);
        //Button ref
        reset_bt = findViewById(R.id.reset_BT);
        save_bt = findViewById(R.id.save_BT);
        view_db_bt = findViewById(R.id.view_db_BT);
        //switch_const_bt = findViewById(R.id.switch_const_BT);
        //switch_table_bt = findViewById(R.id.switch_table_BT);

        //EditText ref
        natnum_et = findViewById(R.id.natnum_ET);
        name_et = findViewById(R.id.name_ET);
        species_et = findViewById(R.id.species_ET);
        height_et = findViewById(R.id.height_ET);
        weight_et = findViewById(R.id.weight_ET);
        stat_hp_et = findViewById(R.id.stat_hp_ET);
        stat_atk_et = findViewById(R.id.stat_atk_ET);
        stat_def_et = findViewById(R.id.stat_def_ET);
        //Radio ref
        gender_rg = findViewById(R.id.gender_RG);
        male_rb = findViewById(R.id.male_RB);
        female_rb = findViewById(R.id.female_RB);
        no_gen_rb = findViewById(R.id.no_gen_RB);
        //Spinner ref
        level_sp = findViewById(R.id.level_SP);

        //Click listeners
        reset_bt.setOnClickListener(reset_bt_listener);
        save_bt.setOnClickListener(save_bt_listener);
        view_db_bt.setOnClickListener(view_db_listener);
        //switch_table_bt.setOnClickListener(switch_table_listener);
        //switch_const_bt.setOnClickListener(switch_const_listener);

        //level_sp.setOnClickListener(reset_bt_listener);


    }

    public boolean checkNameLength(EditText id) {
        String text = id.getText().toString();
        if (text.length() < 3 || text.length() > 12) {
            return false;
        }
        return true;
    }

    public boolean checkNumber(EditText id, double min, double max) {
        String text = id.getText().toString();
        if (text.isEmpty()) {
            return false;
        }
        double num = Double.parseDouble(text);
        if (num < min || num > max) {
            return false;
        }
        return true;
    }

    public int checkAllErrors() {
        int count = 0;
        if (!checkNameLength(name_et)) {
            count++;
            name_tv.setTextColor(Color.RED);
        }
        if (species_et.getText().toString().isEmpty()) {
            count++;
            species_tv.setTextColor(Color.RED);
        }
        if (!checkNumber(natnum_et, 0, 1010)) {
            count++;
            natnum_tv.setTextColor(Color.RED);
        }
        if (!checkNumber(height_et, 0.3, 19.99 )) {
            count++;
            height_tv.setTextColor(Color.RED);
        }
        if (!checkNumber(weight_et, 0.1, 820.0 )) {
            count++;
            weight_tv.setTextColor(Color.RED);
        }
        if (!checkNumber(stat_hp_et, 1, 362 )) {
            count++;
            stat_hp_tv.setTextColor(Color.RED);
        }
        if (!checkNumber(stat_atk_et, 5, 526 )) {
            count++;
            stat_atk_tv.setTextColor(Color.RED);
        }
        if (!checkNumber(stat_def_et, 5, 614 )) {
            count++;
            stat_def_tv.setTextColor(Color.RED);
        }
        if (gender_rg.getCheckedRadioButtonId() == -1) {
            // -1 means nothing is checked
            count++;
            gender_tv.setTextColor(Color.RED);
        }

        return count;
    }

    public void resetTextColor() {
        for (int i = 0; i < ids_tv.length; i++) {
            TextView tv = findViewById(ids_tv[i]);
            tv.setTextColor(Color.BLACK);
        }
    }



}