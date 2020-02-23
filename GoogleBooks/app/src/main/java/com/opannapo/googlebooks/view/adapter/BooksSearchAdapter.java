package com.opannapo.googlebooks.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.opannapo.googlebooks.R;
import com.opannapo.googlebooks.domain.BookEntity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by napouser on 23,February,2020
 */
public class BooksSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BookEntity> data;
    private OnItemCliclListener listener;

    private static final int VIEW_TYPE_ITEM = 1;
    private static final int VIEW_TYPE_LOAD_MORE = 2;
    private int allCount;
    private boolean isNoMoreData;
    private Context context;


    public BooksSearchAdapter(Context context, List<BookEntity> data, int allCount, OnItemCliclListener listener) {
        this.listener = listener;
        this.data = data;
        this.allCount = allCount;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_ITEM) {
            return new ViewHolderItem(inflater.inflate(R.layout.item_book, parent, false));
        } else {
            return new ViewHolderLoadMore(inflater.inflate(R.layout.item_list_loading, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderItem) {
            ((ViewHolderItem) holder).bind(data.get(position), listener);
        } else if (holder instanceof ViewHolderLoadMore) {
            ((ViewHolderLoadMore) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == (getItemCount() - 1) ? VIEW_TYPE_LOAD_MORE : VIEW_TYPE_ITEM;
    }


    class ViewHolderItem extends RecyclerView.ViewHolder {
        TextView txtTitle, txtAuthor, txtPageCount, txtRatingsCount;
        ImageView imgBook;
        AppCompatRatingBar ratingBar;

        ViewHolderItem(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtPageCount = itemView.findViewById(R.id.txtPageCount);
            txtRatingsCount = itemView.findViewById(R.id.txtRatingsCount);
            imgBook = itemView.findViewById(R.id.imgBook);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }

        void bind(final BookEntity c, final OnItemCliclListener listener) {
            String author = Arrays.toString(c.getVolumeInfo().getAuthors()).replaceAll("null", "-");
            float averageRating = c.getVolumeInfo().getAverageRating();
            String ratingsCount = " Total Vote : " + c.getVolumeInfo().getRatingsCount();
            txtTitle.setText(c.getVolumeInfo().getTitle());
            txtAuthor.setText(author);
            txtPageCount.setText(c.getVolumeInfo().getPageCount());
            txtRatingsCount.setText(ratingsCount.replaceAll("null", ""));
            ratingBar.setRating(averageRating);
            if (c.getVolumeInfo().getImageLinks() != null) {
                String image = c.getVolumeInfo().getImageLinks().getThumbnail();
                if (image != null) {
                    Glide.with(context)
                            .load(image.replace("http", "https"))
                            .centerCrop()
                            .error(R.drawable.ic_sad_face)
                            .listener(new RequestListener<String, GlideDrawable>() {
                                @Override
                                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                    System.out.println("IMAGE onException " + e.getMessage());
                                    e.printStackTrace();
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                    System.out.println("IMAGE onResourceReady");
                                    return false;
                                }
                            })
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .thumbnail(0.1f)
                            .into(imgBook);
                }
            }

            itemView.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onItemClick(getAdapterPosition(), c);
                }
            });
        }
    }

    class ViewHolderLoadMore extends RecyclerView.ViewHolder {
        TextView txtLoadingIndicator;
        ProgressBar prbLoading;

        ViewHolderLoadMore(View itemView) {
            super(itemView);
            txtLoadingIndicator = itemView.findViewById(R.id.txtLoadingIndicator);
            prbLoading = itemView.findViewById(R.id.prbLoading);
        }

        void bind() {
            if (isNoMoreData) {
                prbLoading.setVisibility(View.GONE);
                txtLoadingIndicator.setText(allCount + " Data Loaded");
            } else {
                prbLoading.setVisibility(View.VISIBLE);
                txtLoadingIndicator.setText("Load " + data.size() + " of " + allCount + " ...");
            }
        }
    }

    public void notifyNoMoreData() {
        isNoMoreData = true;
        notifyItemChanged(getItemCount() - 1);
    }

    public void notifyAddMoreData(List<BookEntity> data) {
        this.data.addAll(data);
        this.notifyItemInserted(getItemCount() - 1);
    }

    public interface OnItemCliclListener {
        void onItemClick(int i, BookEntity c);
    }
}
