package kushpatel.foodme.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import kushpatel.foodme.R;
import kushpatel.foodme.model.Item;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Viewholder>{

    ArrayList<Item> itemlist;
    Context context;

    public RecyclerViewAdapter(Context context, ArrayList<Item> itemlist){
        this.itemlist = itemlist;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.Viewholder holder, int position) {

        Item item = itemlist.get(position);
        holder.textViewFood.setText(item.getFoodName());
        holder.textViewPrice.setText(item.getPrice().toString());

    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        public TextView textViewFood;
        public TextView textViewPrice;
        public Viewholder(View itemView) {
            super(itemView);
            textViewFood = (TextView) itemView.findViewById(R.id.tv_food);
            textViewPrice = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }
}
