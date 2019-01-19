package id.ac.its.is.addi.halal.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.ac.its.is.addi.halal.R;
import id.ac.its.is.addi.halal.activity.EntityActivity;
import id.ac.its.is.addi.halal.model.Atribute;
import id.ac.its.is.addi.halal.model.EntityDatum;

/**
 * Created by ahm on 11/05/17.
 */

public class EntitysHomeAdapter extends RecyclerView.Adapter<EntitysHomeAdapter.MyViewHolder> {

    private Context mContext;
    private List<EntityDatum> entityList = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_label, tv_containsIngredient;
        LinearLayout layout_item;
        ImageView iv_halal_icon;
        AlertDialog.Builder builder;

        public MyViewHolder(View view) {
            super(view);
            tv_label = (TextView) view.findViewById(R.id.tv_label);
            tv_containsIngredient = (TextView) view.findViewById(R.id.tv_containsIngredient);
            layout_item = (LinearLayout) view.findViewById(R.id.layout_item);
            iv_halal_icon = (ImageView) view.findViewById(R.id.iv_halal_icon);
            builder = new AlertDialog.Builder(mContext);
        }
    }


    public EntitysHomeAdapter(Context mContext, List<EntityDatum> entityListP) {
        this.mContext = mContext;
        this.entityList = entityListP;
    }

    @Override
    public EntitysHomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_entity_home, parent, false);




        return new EntitysHomeAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EntitysHomeAdapter.MyViewHolder holder, final int position) {
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