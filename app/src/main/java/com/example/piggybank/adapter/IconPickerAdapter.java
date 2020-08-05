package com.example.piggybank.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.piggybank.R;

public class IconPickerAdapter extends RecyclerView.Adapter<IconPickerAdapter.ItemViewHolder> {

    int icons[];
    Context context;
    private onItemClickListener mlistener;
    public interface onItemClickListener{
        void onItemClick(int position);
    }
    public  void setOnItemClickListener(onItemClickListener listener){
        mlistener=listener;
    }

    public IconPickerAdapter(int[] icons, Context context) {
        this.icons = icons;
        this.context = context;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_picker, parent, false);
        return new ItemViewHolder(view,mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

            holder.img.setImageResource(icons[position]);

    }

    @Override
    public int getItemCount() {

            return icons.length;

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        public ItemViewHolder(@NonNull View itemView,final onItemClickListener listener) {
            super(itemView);
            img=itemView.findViewById(R.id.img_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(listener!=null){

                            int position1 = getAdapterPosition();
                            listener.onItemClick(position1);

                    }
                }
            });
        }
    }
}
