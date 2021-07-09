package com.example.ProgettoPennaWeb.model;

public class Sottoscrizione {
    private String email;
    private String canali; //lista di numeri separati dalla virgola (esempio: "1,2,4,7,11,63")
    private String fasceOrarie; //lista di fasce orarie (coppie di LocalTime separate da "-") separate da virgola (esempio: "7:00-8:00,12:00-13:00")

    public Sottoscrizione(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCanali() {
        return canali;
    }

    public void setCanali(String canali) {
        this.canali = canali;
    }

    public String getFasceOrarie() {
        return fasceOrarie;
    }

    public void setFasceOrarie(String fasceOrarie) {
        this.fasceOrarie = fasceOrarie;
    }
}
