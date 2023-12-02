package com.example.eda.menuStuff;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eda.R;

import java.util.ArrayList;

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder> {
    ArrayList<GridViewDomain> gridViewDomains;
    RecyclerViewInterface recyclerViewInterface;

    public GridViewAdapter(ArrayList<GridViewDomain> gridViewDomains, RecyclerViewInterface recyclerViewInterface) {
        this.gridViewDomains = gridViewDomains;
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
        holder.grid_name.setText(gridViewDomains.get(position).getTitle());
        holder.GridFee.setText(String.valueOf(gridViewDomains.get(position).getFee()));
        String picUrl = "";
        switch (position){
            case 0:
                picUrl = "category_salads";
                holder.gridLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
                break;
            case 1:
                picUrl = "category_first_dishes";
                holder.gridLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
                break;
            case 2:
                picUrl = "category_second_dishes";
                holder.gridLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
                break;
            case 3:
                picUrl = "category_side_dishes";
                holder.gridLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
                break;
            case 4:
                picUrl = "category_deserts";
                holder.gridLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
                break;
            case 5:
                picUrl = "category_bakery";
                holder.gridLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
                break;
            case 6:
                picUrl = "category_pizza";
                holder.gridLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
                break;
            case 7:
                picUrl = "category_drinks";
                holder.gridLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
                break;

        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.grid_picture);
    }


    @Override
    public int getItemCount() {
        return gridViewDomains.size();
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
