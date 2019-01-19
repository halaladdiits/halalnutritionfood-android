package id.ac.its.is.addi.halal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ahm on 29/12/2017.
 */

public class ResultEntity {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("entityData")
    @Expose
    private List<EntityDatum> entityData = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<EntityDatum> getEntityData() {
        return entityData;
    }

    public void setEntityData(List<EntityDatum> entityData) {
        this.entityData = entityData;
    }
}
