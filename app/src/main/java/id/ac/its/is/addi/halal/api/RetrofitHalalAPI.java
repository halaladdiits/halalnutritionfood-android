package id.ac.its.is.addi.halal.api;

/**
 * Created by ahm on 11/05/17.
 */

import java.util.List;

import id.ac.its.is.addi.halal.model.Certificate;
import id.ac.its.is.addi.halal.model.IngredientAdditives;
import id.ac.its.is.addi.halal.model.Related;
import id.ac.its.is.addi.halal.model.ResultEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RetrofitHalalAPI {

    @GET("search")
    Call<ResultEntity> getSearchEntity(@Query("q") String keyword);

    @GET("related")
    Call<List<Related>> getRelatedProduct(@Query("id") String id);

    @GET("certificate")
    Call<Certificate> getCertificate(@Query("id") String id);

    @GET("inglist")
    Call<List<IngredientAdditives>> getIngList(@Query("id") String id);

    @GET("addlist")
    Call<List<IngredientAdditives>> getAddList(@Query("id") String id);

    @GET("random")
    Call<ResultEntity> getRandomEntity();





}
