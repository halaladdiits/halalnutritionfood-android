package id.ac.its.is.addi.halal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahm on 01/01/2018.
 */

public class Related {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("fName")
    @Expose
    private String fName;
    @SerializedName("cosine")
    @Expose
    private String cosine;
    @SerializedName("euclidean")
    @Expose
    private String euclidean;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getCosine() {
        return cosine;
    }

    public void setCosine(String cosine) {
        this.cosine = cosine;
    }

    public String getEuclidean() {
        return euclidean;
    }

    public void setEuclidean(String euclidean) {
        this.euclidean = euclidean;
    }

}
