package adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mxn.soul.flowingdr.FullScreenImage;
import com.mxn.soul.flowingdr.R;

import java.util.List;

import models.Clothes;

public class RecyclerViewGalleryMain extends RecyclerView.Adapter<RecyclerViewGalleryMain.ViewHolder> {
    @SuppressLint("StaticFieldLeak")
  public static int resurse;
    int img;
    int img1;



  private ImageView imageViewForFullScreen;
    private  TextView copyShopNameTextOnImage;
    private TextView copyPriceTextOnImage;
    private ImageButton copyOnImageFullScreen;
    private ImageButton copyOnImageFavorite;

    Context context;

    private List<Clothes> clothesList;
    private OnClickListenerFullScreen mOnClickListenerFullScreen;
    private OnClickListenerShop mOnClickListenerShop;

    public RecyclerViewGalleryMain(List<Clothes> clothesList, OnClickListenerFullScreen onClickListenerFullScreen
    , OnClickListenerShop mOnClickListenerShop,Context context) {
        this.context = context;
        this.clothesList = clothesList;
        this.mOnClickListenerFullScreen = onClickListenerFullScreen;
        this.mOnClickListenerShop = mOnClickListenerShop;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_gallery_main, viewGroup, false);

        return new ViewHolder(view, mOnClickListenerFullScreen, mOnClickListenerShop);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {

          img = clothesList.get(viewHolder.getAdapterPosition()).getClotheImage();
          img1 = clothesList.get(viewHolder.getAdapterPosition()).getClotheImage();




        viewHolder.image.setImageResource(img);
        viewHolder.image.setTag(img);

        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageViewForFullScreen != null ){
                    imageViewForFullScreen.clearColorFilter();
                    copyShopNameTextOnImage.setVisibility(View.INVISIBLE);
                   copyPriceTextOnImage.setVisibility(View.INVISIBLE);
                   copyOnImageFullScreen.setVisibility(View.INVISIBLE);
                   copyOnImageFavorite.setVisibility(View.INVISIBLE);


                }
                if (viewHolder.priceTextOnImage.getVisibility() == View.INVISIBLE) {
                    viewHolder.image.setColorFilter(Color.HSVToColor(170, new float[3]));
                    viewHolder.priceTextOnImage.setVisibility(View.VISIBLE);
                    viewHolder.onImageFavorite.setVisibility(View.VISIBLE);
                    viewHolder.onImageFullScreen.setVisibility(View.VISIBLE);
                    viewHolder.shopNameTextOnImage.setVisibility(View.VISIBLE);

                    copyShopNameTextOnImage = viewHolder.itemView.findViewById(viewHolder.shopNameTextOnImage.getId());
                    copyPriceTextOnImage = viewHolder.itemView.findViewById(viewHolder.priceTextOnImage.getId());
                   copyOnImageFullScreen = viewHolder.itemView.findViewById(viewHolder.onImageFullScreen.getId());
                   copyOnImageFavorite = viewHolder.itemView.findViewById(viewHolder.onImageFavorite.getId());
                    imageViewForFullScreen = viewHolder.itemView.findViewById(viewHolder.image.getId());

                }
            }
        });
        viewHolder.onImageFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resurse = img1;
                context.startActivity(new Intent(context,FullScreenImage.class));
                Toast.makeText(context, String.valueOf(resurse), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return clothesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView priceTextOnImage;
        ImageButton onImageFavorite;
        ImageButton onImageFullScreen;
        TextView shopNameTextOnImage;

        OnClickListenerFullScreen onClickListenerFullScreen;
        OnClickListenerShop onClickListenerShop;

        ViewHolder(@NonNull View itemView, final OnClickListenerFullScreen onClickListenerFullScreen
                   , final OnClickListenerShop onClickListenerShop) {
            super(itemView);
            image = itemView.findViewById(R.id.image_gallery_main);
            priceTextOnImage = itemView.findViewById(R.id.priceText_onImage_main);
            onImageFavorite = itemView.findViewById(R.id.onImage_favorite);
            onImageFullScreen = itemView.findViewById(R.id.onImage_fullScreen);
            shopNameTextOnImage = itemView.findViewById(R.id.shopNameText_onImage_main);

        }
    }

    public interface OnClickListenerFullScreen {
        void onItemImageFullScreenClick(int position);
    }
    public interface OnClickListenerShop{
        void onItemImageShopClick(int position);
    }
}
