package at.campus02.asy.helloworld.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by peskandar on 21.05.2016.
 */
public class Question {
    @SerializedName("FrageID")
    @Expose
    public String FrageID;
    @SerializedName("Fragetext")
    @Expose
    public String Fragetext;
    @SerializedName("Antwort")
    @Expose
    public String Antwort;
    @SerializedName("Schwierigkeitsgrad")
    @Expose
    public String Schwierigkeitsgrad;
    @SerializedName("Kategorie")
    @Expose
    public String Kategorie;
    @SerializedName("LaengenUndBreitengrad")
    @Expose
    public String LaengenUndBreitengrad;
    @SerializedName("Bild")
    @Expose
    public String Bild;
}
