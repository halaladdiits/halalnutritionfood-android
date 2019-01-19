package id.ac.its.is.addi.halal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahm on 29/12/2017.
 */

public class EntityDatum {
    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("statsScore")
    @Expose
    private String statsScore;
    @SerializedName("atribute")
    @Expose
    private Atribute atribute;

    public String getStatsScore() {
        return statsScore;
    }

    public void setStatsScore(String statsScore) {
        this.statsScore = statsScore;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Atribute getAtribute() {
        return atribute;
    }

    public void setAtribute(Atribute atribute) {
        this.atribute = atribute;
    }
}
