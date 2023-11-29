package com.example.eda.menuStuff;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
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
import com.example.eda.retrofitThigies.models.GetFoodResponse;
import com.example.eda.retrofitThigies.models.MenuItemEntity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<MenuItemEntity> menuItemEntities;
    RecyclerViewInterface recyclerViewInterface;
    private Context context;

    public CategoryAdapter(ArrayList<MenuItemEntity> menuItemEntities, RecyclerViewInterface recyclerViewInterface, Context context) {
        this.menuItemEntities = menuItemEntities;
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflator = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_category, parent, false);
        return new ViewHolder(inflator);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.category_name.setText(menuItemEntities.get(position).getName());
        String picUrl = "http://95.165.91.211:8081/api/v1/user/getMenuItemPicture/";
//        switch (position) {
//            case 0:
//                picUrl = "category_salads";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.background_icons));
//                break;
//            case 1:
//                picUrl = "category_first_dishes";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.background_icons));
//                break;
//            case 2:
//                picUrl = "category_second_dishes";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.background_icons));
//                break;
//            case 3:
//                picUrl = "category_side_dishes";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.background_icons));
//                break;
//            case 4:
//                picUrl = "category_deserts";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.background_icons));
//                break;
//            case 5:
//                picUrl = "category_bakery";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.background_icons));
//                break;
//            case 6:
//                picUrl = "category_pizza";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.background_icons));
//                break;
//            case 7:
//                picUrl = "category_drinks";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.background_icons));
//                break;
//
//        }
        //int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", context.getPackageName());
        // захардкодил пока что, потом надо будет переделать
        Picasso.get()
                .load(picUrl + menuItemEntities.get(position).getId())
                .into(holder.category_picture);
//        Glide.with(holder.itemView.getContext())
//                .load(drawableResourceId)
//                .into(holder.category_picture);
    }

    @Override
    public int getItemCount() {
        return menuItemEntities.size();
    }


//    private void saveImageToCache(File file, ImageView imageView) {
//        try {
//            imageView.setDrawingCacheEnabled(true);
//            imageView.buildDrawingCache();
//            Bitmap bitmap = imageView.getDrawingCache();
//
//            FileOutputStream fos = new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//            fos.flush();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView category_name;
        ImageView category_picture;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category_name = itemView.findViewById(R.id.CategoryName);
            category_picture = itemView.findViewById(R.id.CategoryPic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemCLick(pos);
                        }
                    }
                }
            });
        }
    }

}
