package com.davidb.kgkcarsearch.bean;

public class CarInfo {

    private String marke;
    private String modell;
    private String tillverkningsar;
    private String bransletyp;
    private String nybilspris;

    public CarInfo() {
    }

    public CarInfo(String marke, String modell, String tillverkningsar, String bransletyp, String nybilspris) {
        this.marke = marke;
        this.modell = modell;
        this.tillverkningsar = tillverkningsar;
        this.bransletyp = bransletyp;
        this.nybilspris = nybilspris;
    }

    public String getNybilspris() {
        return nybilspris;
    }

    public void setNybilspris(String nybilspris) {
        this.nybilspris = nybilspris;
    }

    public String getMarke() {
        return marke;
    }

    public String getModell() {
        return modell;
    }

    public String getBransletyp() {
        return bransletyp;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public void setBransletyp(String bransletyp) {
        this.bransletyp = bransletyp;
    }

    public String getTillverkningsar() {
        return tillverkningsar;
    }

    public void setTillverkningsar(String tillverkningsar) {
        this.tillverkningsar = tillverkningsar;
    }

}
