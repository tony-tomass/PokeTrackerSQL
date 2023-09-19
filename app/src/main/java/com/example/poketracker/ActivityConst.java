package com.example.poketracker;

import androidx.appcompat.app.AppCompatActivity;

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

public class ActivityConst extends AppCompatActivity {

    Button reset_bt, save_bt;
    EditText natnum_et, name_et, species_et, height_et, weight_et, stat_hp_et, stat_atk_et, stat_def_et;
    RadioGroup gender_rg;
    RadioButton male_rb, female_rb, no_gen_rb;
    Spinner level_sp;

    View.OnClickListener reset_bt_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Resets all fields to their default values (according to the assignment sheet)
            //natnum_et.setText("896") => Hardcoded boo hoo
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
            if (!checkNameLength()) {
                Toast.makeText(getApplicationContext(),
                        "ERROR: Invalid inputs. Please check your inputs again.",
                        Toast.LENGTH_SHORT).show();
            }
            if (!checkNumber(height_et, 0.3, 19.99 )) {
                Toast.makeText(getApplicationContext(),
                        "ERROR: Invalid inputs. Please check your inputs again.",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(),
                        "Your Pokemon has been successfully recorded into our database.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_const);

        //Button ref
        reset_bt = findViewById(R.id.reset_BT);
        save_bt = findViewById(R.id.save_BT);
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
    }

    public boolean checkNameLength() {
        String text = name_et.getText().toString();
        if (text.length() < 3 || text.length() > 12) {
            return false;
        }
        return true;
    }

    public boolean checkNumber(EditText id, double max, double min) {
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

}