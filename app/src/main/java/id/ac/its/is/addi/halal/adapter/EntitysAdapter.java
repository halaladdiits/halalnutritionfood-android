package id.ac.its.is.addi.halal.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.its.is.addi.halal.activity.EntityActivity;
import id.ac.its.is.addi.halal.R;
import id.ac.its.is.addi.halal.model.Atribute;
import id.ac.its.is.addi.halal.model.EntityDatum;

/**
 * Created by ahm on 11/05/17.
 */

public class EntitysAdapter extends RecyclerView.Adapter<EntitysAdapter.MyViewHolder> {

    private Context mContext;
    private List<EntityDatum> entityList = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_label, tv_containsIngredient, tv_Score;
        LinearLayout layout_item;
        ImageView iv_halal_icon, iv_score_icon;
        AlertDialog.Builder builder;

        public MyViewHolder(View view) {
            super(view);
            tv_label = (TextView) view.findViewById(R.id.tv_label);
            tv_containsIngredient = (TextView) view.findViewById(R.id.tv_containsIngredient);
            tv_Score = (TextView) view.findViewById(R.id.tv_score);
            layout_item = (LinearLayout) view.findViewById(R.id.layout_item);
            iv_halal_icon = (ImageView) view.findViewById(R.id.iv_halal_icon);
            iv_score_icon = (ImageView) view.findViewById(R.id.iv_score_icon);
            builder = new AlertDialog.Builder(mContext);
        }
    }


    public EntitysAdapter(Context mContext, List<EntityDatum> entityListP) {
        this.mContext = mContext;
        this.entityList = entityListP;
    }

    @Override
    public EntitysAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_entity, parent, false);




        return new EntitysAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EntitysAdapter.MyViewHolder holder, final int position) {
        final EntityDatum entity  = entityList.get(position);

        holder.tv_label.setText(entity.getLabel() != null ? entity.getLabel() : "Null");
        String description = entity.getAtribute().getContainsIngredient() != null ?
                "Ingredients : "+entity.getAtribute().getContainsIngredient().toString() : "Label : "+entity.getAtribute().getLabel().toString();
        holder.tv_containsIngredient.setText(description.length() >= 100 ? description.substring(0,100) +".. more" : description);
        if (entity.getAtribute().getCertificate() != null) {
            holder.iv_halal_icon.setVisibility(View.VISIBLE);
        } else {
            holder.iv_halal_icon.setVisibility(View.GONE);
        }

        holder.iv_score_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Score : "+entity.getScore().toString()+"\n"+entity.getStatsScore();
                message = message.replace("-","\n").replace("Ss", "Static score")
                        .replace("Qs", "Query score").replace("Fs","Final score");
                showDialog(message);
            }
        });
        if (entity.getAtribute().getType() != null && entity.getAtribute().getType().contains("Ingredient")) {
            //holder.tv_Score.setText("Type : Ingredient \n Score : "+entity.getScore().toString());
            holder.tv_Score.setText("Type : Ingredient");
            holder.layout_item.setBackgroundColor(mContext.getResources().getColor(R.color.entity_ingredient));
        } else {
            holder.tv_Score.setText("Type : Food Product");
            holder.layout_item.setBackgroundColor(mContext.getResources().getColor(R.color.entity_product));
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Atribute atribute = entityList.get(position).getAtribute();
                Bundle bundle = new Bundle();
                bundle.putParcelable("atribute", atribute);
                Intent intent = new Intent(mContext, EntityActivity.class);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
                //Toast.makeText(mContext, entity.getLabel().toString()+" has been clciked :p", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDialog(final String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(true);
        builder.setTitle("Score Stats");
        builder.setMessage(message);

        builder.setPositiveButton("Ok", null);

        builder.create().show();
    }


    @Override
    public int getItemCount() {
        return entityList.size();
    }
}