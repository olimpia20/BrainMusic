package music.model;

import java.io.Serializable;

public class Content implements Identifiable<Integer>, Serializable {

   private Integer id;
   private String text;
   private int nb_cours;  //number of cours
   private int part;   //the part of the cours

    public Content(){}

    public Content(String text, int nb_cours, int part) {
        this.text = text;
        this.nb_cours = nb_cours;
        this.part = part;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNb_cours() {
        return nb_cours;
    }

    public void setNb_cours(int nb_cours) {
        this.nb_cours = nb_cours;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }
}
