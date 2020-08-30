package com.example.piggybank.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.piggybank.R;
import com.example.piggybank.databinding.ItemPickerBinding;
/**
 * adapter for  icon of <code>cardView</code> and transaction's type
 */
public class IconPickerAdapter extends RecyclerView.Adapter<IconPickerAdapter.ItemViewHolder> {

    private int icons[];
    private Context context;
    private ItemPickerBinding itemPickerBinding;
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
        itemPickerBinding= DataBindingUtil.bind(view);
        return new ItemViewHolder(itemPickerBinding,mlistener);
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
        public ItemViewHolder(@NonNull ItemPickerBinding itemView,final onItemClickListener listener) {
            super(itemView.getRoot());
            img=itemPickerBinding.imgItem;
            itemView.getRoot().setOnClickListener(view -> {
                if(listener!=null){
                        int position1 = getAdapterPosition();
                        listener.onItemClick(position1);

                }
            });
        }
    }
}
