package com.example.poketracker;

public class Pokemon {

    String natnum;
    String name;
    String species;
    String gender;
    String height;
    String weight;
    String level;
    String hp;
    String atk;
    String def;

    public Pokemon(String natnum, String name, String species, String gender, String height,
                   String weight, String level, String hp, String atk, String def) {
        this.natnum = natnum;
        this.name = name;
        this.species = species;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.level = level;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
    }

    public String getNatNum() {
        return natnum;
    }

    public void setNatNum(String natnum) {
        this.natnum = natnum;
    }

    public String getPokeName() {
        return name;
    }

    public void setPokeName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getAtk() {
        return atk;
    }

    public void setAtk(String atk) {
        this.atk = atk;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }
}
