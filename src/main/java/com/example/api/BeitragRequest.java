package com.example.api;

public class BeitragRequest {
    private String versicherung;
    private int alter;
    private String plz;
    private String deckung;



    //Getter, Setter

    public String getVersicherung(){
        return versicherung;
    }

    public void  setVersicherung(String versicherung){
        this.versicherung = versicherung;
    }

    public int getAlter(){
        return alter;
    }

    public void setAlter(int alter){
        this.alter = alter;
    }

    public String getDeckung(){
        return deckung;
    }

    public void setDeckung(String deckung){
        this.deckung = deckung;
    }

    public String getPlz(){
        return plz;

    }

    public void setPlz(String plz){
        this.plz = plz;
    }
}
