package id.ac.its.is.addi.halal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahm on 14/01/2018.
 */

public class Certificate {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("cCode")
    @Expose
    private String cCode;
    @SerializedName("cExpire")
    @Expose
    private String cExpire;
    @SerializedName("cStatus")
    @Expose
    private String cStatus;
    @SerializedName("cOrganization")
    @Expose
    private String cOrganization;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCCode() {
        return cCode;
    }

    public void setCCode(String cCode) {
        this.cCode = cCode;
    }

    public String getCExpire() {
        return cExpire;
    }

    public void setCExpire(String cExpire) {
        this.cExpire = cExpire;
    }

    public String getCStatus() {
        return cStatus;
    }

    public void setCStatus(String cStatus) {
        this.cStatus = cStatus;
    }

    public String getCOrganization() {
        return cOrganization;
    }

    public void setCOrganization(String cOrganization) {
        this.cOrganization = cOrganization;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAllString() {
        String allString = "Certificate Code : "+cCode + " by "+ cOrganization + " - expired in "+cExpire;

        return allString;

    }

}