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
import com.example.eda.retrofitThigies.models.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<Category> category;
    RecyclerViewInterface recyclerViewInterface;
    public CategoryAdapter(ArrayList<Category> category, RecyclerViewInterface recyclerViewInterface){
        this.category = category;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_category,parent,false);
        return new ViewHolder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.category_name.setText(category.get(position).getCategoryName());
//        String picUrl = "";
//        switch (position){
//            case 0:
//                picUrl = "category_salads";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
//                break;
//            case 1:
//                picUrl = "category_first_dishes";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
//                break;
//            case 2:
//                picUrl = "category_second_dishes";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
//                break;
//            case 3:
//                picUrl = "category_side_dishes";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
//                break;
//            case 4:
//                picUrl = "category_deserts";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
//                break;
//            case 5:
//                picUrl = "category_bakery";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
//                break;
//            case 6:
//                picUrl = "category_pizza";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
//                break;
//            case 7:
//                picUrl = "category_drinks";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.background_icons));
//                break;
//
//        }

        //int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(ApiClient.PICTURES_URL + "categoriesPng/" +  category.get(position).getCategory() + ".png")
                //.load("http://95.165.91.211:8081/files/categories/category_drinks.svg")
                .into(holder.category_picture);
                //.into(new SvgSoftwareLayerSetter<>(holder.category_picture).getView());
    }

    @Override
    public int getItemCount() {
        return category.size();
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onCategoryCLick(pos);
                        }
                    }
                }
            });
        }
    };

}
