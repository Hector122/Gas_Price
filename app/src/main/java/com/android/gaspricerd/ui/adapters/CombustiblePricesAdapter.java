package com.android.gaspricerd.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.gaspricerd.R;
import com.android.gaspricerd.model.Combustible;

import java.util.ArrayList;

/**
 * Adapter class for list prices.
 */
public class CombustiblePricesAdapter extends RecyclerView.Adapter<CombustiblePricesAdapter.ViewHolder> {
    private ArrayList<Combustible> combustibles;
    private Context context;

    public CombustiblePricesAdapter(Context context, ArrayList<Combustible> combustibles) {
        this.context = context;
        this.combustibles = combustibles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.list_price_layout_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Combustible combustible = combustibles.get(position);

        holder.imageView.setImageResource(combustible.getImageViewId());
        holder.title.setText(combustible.getTitle());
        holder.price.setText(String.valueOf(combustible.getCurrentPrice()));

        double difference = combustible.getLastPrice() - combustible.getCurrentPrice();
       // TODO: move String to xml.
        holder.difference.setText(difference > 0 ? "Diferencia: + " + difference: "Diferencia: " + difference);

        // animation

       // holder.container.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fate_scale));
       holder.container.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fate_transition));
    }

    @Override
    public int getItemCount() {
        return combustibles.size();
    }

    /**
     * Class holder for item view.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView container;
        TextView price, difference, title;

        ViewHolder(View view) {
            super(view);
            container = view.findViewById(R.id.card_view);
            imageView = view.findViewById(R.id.image_variation);
            price = view.findViewById(R.id.txt_current_price);
            difference = view.findViewById(R.id.txt_different_price);
            title = view.findViewById(R.id.txt_title);
        }
    }
}
