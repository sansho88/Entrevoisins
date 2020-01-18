package com.openclassrooms.entrevoisins.model;

import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class Neighbour {
//NE PLUS MODIFIER
    /** Identifier */
    private Integer id;

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;

    private String adresse;

    private String numTph;

    private String fbUrl;

    /** Favoris */
    private Boolean favoris = false;

    /**
     * Constructor
     * @param id
     * @param name
     * @param avatarUrl
     *
     */
    public Neighbour(Integer id, String name, String avatarUrl, String adresse, String numTph, String fbUrl) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.adresse = adresse;
        this.numTph = numTph;
        this.fbUrl = fbUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumTph() {
        return numTph;
    }

    public void setNumTph(String numTph) {
        this.numTph = numTph;
    }

    public String getFbUrl() {
        return fbUrl;
    }

    public void setFbUrl(String fbUrl) {
        this.fbUrl = fbUrl;
    }

    public Boolean getFavoris() {
        return favoris;
    }

    public void setFavoris(Boolean favoris) {
        this.favoris = favoris;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
