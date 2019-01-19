package id.ac.its.is.addi.halal.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.its.is.addi.halal.activity.EntityActivity;
import id.ac.its.is.addi.halal.R;
import id.ac.its.is.addi.halal.api.RetrofitHalalAPI;
import id.ac.its.is.addi.halal.model.Atribute;
import id.ac.its.is.addi.halal.model.IngredientAdditives;
import id.ac.its.is.addi.halal.model.ResultEntity;
import id.ac.its.is.addi.halal.utils.Params;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ahm on 11/05/17.
 */

public class IngAddsAdapter extends RecyclerView.Adapter<IngAddsAdapter.MyViewHolder> {

    private Context mContext;
    private List<IngredientAdditives> ingAddList = new ArrayList<>();
    Atribute atribute;
    private ProgressDialog dialog;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;

        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
        }
    }


    public IngAddsAdapter(Context mContext, List<IngredientAdditives> ingAddListP) {
        this.mContext = mContext;
        this.ingAddList = ingAddListP;
        dialog = new ProgressDialog(mContext);
        dialog.setCancelable(true);
        dialog.setMessage("Loading");
    }

    @Override
    public IngAddsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingadd, parent, false);

        return new IngAddsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final IngAddsAdapter.MyViewHolder holder, final int position) {
        final IngredientAdditives ingredientAdditives = ingAddList.get(position);

        holder.tv_name.setText(ingredientAdditives.getName() != null ? ingredientAdditives.getName() : "Null");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getRetrofitSearch("rank"+ingredientAdditives.getId());

                } catch (Exception e) {
                    Toast.makeText(mContext, "Failed to connect Server", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getRetrofitSearch(String query) {
        dialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Params.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitHalalAPI service = retrofit.create(RetrofitHalalAPI.class);

        Call<ResultEntity> call = service.getSearchEntity(query);

        call.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                if (response.body().getEntityData().size() == 0) {
                    Toast.makeText(mContext, "Entity not found", Toast.LENGTH_SHORT).show();
                } else {
                    atribute = response.body().getEntityData().get(0).getAtribute();
                    if (atribute != null) {
                        lauchFoodProductActivity(atribute);
                    } else {

                    }

                }

            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                Toast.makeText(mContext, "Error occured", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void lauchFoodProductActivity(Atribute atributeP) {
        dialog.dismiss();
        Bundle bundle = new Bundle();
        bundle.putParcelable("atribute", atributeP);
        Intent intent = new Intent(mContext, EntityActivity.class);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }


    @Override
    public int getItemCount() {
        return ingAddList.size();
    }
}