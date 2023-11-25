package com.example.eda;

import android.media.Image;
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

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<CategoryDomain> CategoryDomains;

    public CategoryAdapter(ArrayList<CategoryDomain> categoryDomains) {
        CategoryDomains = categoryDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_category,parent,false);
        return new ViewHolder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.category_name.setText(CategoryDomains.get(position).getTitle());
        String picUrl = "";
        switch (position){
            case 0:
                picUrl = "category_salads";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
                break;
            case 1:
                picUrl = "category_first_dishes";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
                break;
            case 2:
                picUrl = "category_second_dishes";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
                break;
            case 3:
                picUrl = "category_side_dishes";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
                break;
            case 4:
                picUrl = "category_deserts";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
                break;
            case 5:
                picUrl = "category_bakery";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
                break;
            case 6:
                picUrl = "category_pizza";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
                break;
            case 7:
                picUrl = "category_drinks";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
                break;

        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.category_picture);
    }

    @Override
    public int getItemCount() {
        return CategoryDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView category_name;
        ImageView category_picture;
        ConstraintLayout mainLayout;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            category_name = itemView.findViewById(R.id.CategoryName);
            category_picture = itemView.findViewById(R.id.CategoryPic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    };

}
