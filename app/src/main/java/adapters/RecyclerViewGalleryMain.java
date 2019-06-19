package adapters;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mxn.soul.flowingdrawer.R;

import java.util.List;

import models.Clothes;

public class RecyclerViewGalleryMain extends RecyclerView.Adapter<RecyclerViewGalleryMain.ViewHolder> {

    private List<Clothes> clothesList;
    private OnClickListenerFullScreen mOnClickListenerFullScreen;
    private OnClickListenerShop mOnClickListenerShop;

    public RecyclerViewGalleryMain(List<Clothes> clothesList, OnClickListenerFullScreen onClickListenerFullScreen
    , OnClickListenerShop mOnClickListenerShop) {
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
        int img = clothesList.get(position).getClotheImage();
        viewHolder.image.setImageResource(img);
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.priceTextOnImage.getVisibility() == View.INVISIBLE) {
                    viewHolder.image.setColorFilter(Color.HSVToColor(170, new float[3]));
                    viewHolder.priceTextOnImage.setVisibility(View.VISIBLE);
                    viewHolder.onImageFavorite.setVisibility(View.VISIBLE);
                    viewHolder.onImageFullScreen.setVisibility(View.VISIBLE);
                    viewHolder.shopNameTextOnImage.setVisibility(View.VISIBLE);
                }
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

            this.onClickListenerFullScreen = onClickListenerFullScreen;
            onImageFullScreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListenerFullScreen.onItemImageFullScreenClick(getAdapterPosition());
                }
            });

            this.onClickListenerShop = onClickListenerShop;
            shopNameTextOnImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListenerShop.onItemImageShopClick(getAdapterPosition());
                }
            });
        }
    }

    public interface OnClickListenerFullScreen {
        void onItemImageFullScreenClick(int position);
    }
    public interface OnClickListenerShop{
        void onItemImageShopClick(int position);
    }
}
