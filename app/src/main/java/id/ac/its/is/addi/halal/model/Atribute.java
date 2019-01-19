package id.ac.its.is.addi.halal.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahm on 29/12/2017.
 */

public class Atribute implements Parcelable {
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("containsIngredient")
    @Expose
    private String containsIngredient;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("sameAs")
    @Expose
    private String sameAs;
    @SerializedName("fiber")
    @Expose
    private String fiber;
    @SerializedName("calcium")
    @Expose
    private String calcium;
    @SerializedName("vitaminC")
    @Expose
    private String vitaminC;
    @SerializedName("calories")
    @Expose
    private String calories;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("sodium")
    @Expose
    private String sodium;
    @SerializedName("manufacture")
    @Expose
    private String manufacture;
    @SerializedName("saturatedFat")
    @Expose
    private String saturatedFat;
    @SerializedName("netWeight")
    @Expose
    private String netWeight;
    @SerializedName("protein")
    @Expose
    private String protein;
    @SerializedName("fat")
    @Expose
    private String fat;
    @SerializedName("iron")
    @Expose
    private String iron;
    @SerializedName("vitaminA")
    @Expose
    private String vitaminA;
    @SerializedName("sugar")
    @Expose
    private String sugar;
    @SerializedName("foodCode")
    @Expose
    private String foodCode;
    @SerializedName("foodproductId")
    @Expose
    private String foodproductId;
    @SerializedName("certificate")
    @Expose
    private String certificate;
    @SerializedName("transFat")
    @Expose
    private String transFat;
    @SerializedName("dietaryFiber")
    @Expose
    private String dietaryFiber;
    @SerializedName("cholesterol")
    @Expose
    private String cholesterol;
    @SerializedName("totalCarbohydrates")
    @Expose
    private String totalCarbohydrates;

    public String getTotalCarbohydrates() {
        return totalCarbohydrates;
    }

    public void setTotalCarbohydrates(String totalCarbohydrates) {
        this.totalCarbohydrates = totalCarbohydrates;
    }

    public String getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(String cholesterol) {
        this.cholesterol = cholesterol;
    }

    public String getTransFat() {
        return transFat;
    }

    public void setTransFat(String transFat) {
        this.transFat = transFat;
    }

    public String getDietaryFiber() {
        return dietaryFiber;
    }

    public void setDietaryFiber(String dietaryFiber) {
        this.dietaryFiber = dietaryFiber;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContainsIngredient() {
        return containsIngredient;
    }

    public void setContainsIngredient(String containsIngredient) {
        this.containsIngredient = containsIngredient;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSameAs() {
        return sameAs;
    }

    public void setSameAs(String sameAs) {
        this.sameAs = sameAs;
    }

    public String getFiber() {
        return fiber;
    }

    public void setFiber(String fiber) {
        this.fiber = fiber;
    }

    public String getCalcium() {
        return calcium;
    }

    public void setCalcium(String calcium) {
        this.calcium = calcium;
    }

    public String getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(String vitaminC) {
        this.vitaminC = vitaminC;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSodium() {
        return sodium;
    }

    public void setSodium(String sodium) {
        this.sodium = sodium;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(String saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getIron() {
        return iron;
    }

    public void setIron(String iron) {
        this.iron = iron;
    }

    public String getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(String vitaminA) {
        this.vitaminA = vitaminA;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(String foodCode) {
        this.foodCode = foodCode;
    }

    public String getFoodproductId() {
        return foodproductId;
    }

    public void setFoodproductId(String foodproductId) {
        this.foodproductId = foodproductId;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    protected Atribute(Parcel in) {
        path = in.readString();
        containsIngredient = in.readString();
        rank = in.readString();
        comment = in.readString();
        label = in.readString();
        sameAs = in.readString();
        fiber = in.readString();
        calcium = in.readString();
        vitaminC = in.readString();
        calories = in.readString();
        type = in.readString();
        sodium = in.readString();
        manufacture = in.readString();
        saturatedFat = in.readString();
        netWeight = in.readString();
        protein = in.readString();
        fat = in.readString();
        iron = in.readString();
        vitaminA = in.readString();
        sugar = in.readString();
        foodCode = in.readString();
        foodproductId = in.readString();
        certificate = in.readString();
        transFat = in.readString();
        dietaryFiber = in.readString();
        cholesterol = in.readString();
        totalCarbohydrates = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeString(containsIngredient);
        dest.writeString(rank);
        dest.writeString(comment);
        dest.writeString(label);
        dest.writeString(sameAs);
        dest.writeString(fiber);
        dest.writeString(calcium);
        dest.writeString(vitaminC);
        dest.writeString(calories);
        dest.writeString(type);
        dest.writeString(sodium);
        dest.writeString(manufacture);
        dest.writeString(saturatedFat);
        dest.writeString(netWeight);
        dest.writeString(protein);
        dest.writeString(fat);
        dest.writeString(iron);
        dest.writeString(vitaminA);
        dest.writeString(sugar);
        dest.writeString(foodCode);
        dest.writeString(foodproductId);
        dest.writeString(certificate);
        dest.writeString(transFat);
        dest.writeString(dietaryFiber);
        dest.writeString(cholesterol);
        dest.writeString(totalCarbohydrates);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Atribute> CREATOR = new Parcelable.Creator<Atribute>() {
        @Override
        public Atribute createFromParcel(Parcel in) {
            return new Atribute(in);
        }

        @Override
        public Atribute[] newArray(int size) {
            return new Atribute[size];
        }
    };
}