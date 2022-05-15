package com.dmiiy.wayapp;

public class ItemObject {
    private String tripname;
    private String note;
    private int photo;
    private String edad;

    public ItemObject(String tripname, String note, int photo, String edad) {
        this.tripname = tripname;
        this.note = note;
        this.photo = photo;
        this.edad = edad;
    }

    public String getTripname() {
        return tripname;
    }

    public void setTripname(String tripname) {
        this.tripname = tripname;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
}
