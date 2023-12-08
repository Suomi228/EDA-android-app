package com.example.eda.menuStuff;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eda.R;
import com.example.eda.retrofitThigies.ApiClient;
import com.example.eda.retrofitThigies.models.MenuItemEntity;

import java.util.ArrayList;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder> {
    ArrayList<MenuItemEntity> menuItemEntities;
    RecyclerViewInterface recyclerViewInterface;

    public GridViewAdapter(ArrayList<MenuItemEntity> menuItemEntities, RecyclerViewInterface recyclerViewInterface) {
        this.menuItemEntities = menuItemEntities;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_view_menu,parent,false);
        return new ViewHolder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.grid_name.setText(menuItemEntities.get(position).getName());
        holder.GridFee.setText(String.valueOf((int) menuItemEntities.get(position).getPrice()));

        Glide.with(holder.itemView.getContext())
                    .load(ApiClient.PICTURES_URL
                            + menuItemEntities.get(position).getCategoryEntity().getCategory() + "/" + menuItemEntities.get(position).getPictureUrl() + ".png")
                    .into(holder.grid_picture);
    }


    @Override
    public int getItemCount() {
        return menuItemEntities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView grid_name, GridFee, add_btn;
        ImageView grid_picture;
        ConstraintLayout gridLayout;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            grid_name = itemView.findViewById(R.id.GridName);
            grid_picture = itemView.findViewById(R.id.GridPic);
            gridLayout = itemView.findViewById(R.id.gridLayout);
            GridFee = itemView.findViewById(R.id.GridFee);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onFoodCLick(pos);

                        }
                    }
                }
            });
        }
    }
}
