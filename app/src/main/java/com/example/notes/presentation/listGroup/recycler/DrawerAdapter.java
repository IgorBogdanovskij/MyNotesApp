//package com.example.notes.presentation.listGroup.recycler;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.notes.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.VhDrawer> {
//
//    private List<String> mList = new ArrayList<>();
//    private Callback mCallback;
//
//
//
//    public interface Callback {
//        void onClickDrawer(View view, String s);
//        void onLongClick(View v, String s);
//    }
//
//    public DrawerAdapter(Callback callback) {
//        mCallback = callback;
//    }
//
//    @NonNull
//    @Override
//    public VhDrawer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drawer_adapter, parent, false);
//        VhDrawer vhDrawer = new VhDrawer(view);
//        return vhDrawer;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull VhDrawer holder, int position) {
//        Log.d("MyLog1", "onBindViewHolder: " + mList.get(position));
//
//        holder.setText(mList.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        if (mList.size() != 0) {
//            return mList.size();
//        }
//        return 0;
//    }
//
//    public void setGroupsDrawer(List<String> nameGroups) {
//        mList = nameGroups;
//        notifyDataSetChanged();
//    }
//
//    public List<?> getListNotes() {
//        return mList;
//    }
//
//    class VhDrawer extends RecyclerView.ViewHolder {
//        private ImageView mImageView;
//        private TextView mTextView;
//
//        public VhDrawer(@NonNull View itemView) {
//            super(itemView);
//
//            mImageView = itemView.findViewById(R.id.drawerImage);
//            mTextView = itemView.findViewById(R.id.drawerText);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String s = mList.get(getAdapterPosition());
//
//                    mCallback.onClickDrawer(itemView, s);
//                }
//            });
//
//
//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    mCallback.onLongClick(v, mList.get(getAdapterPosition()));
//                    return false;
//                }
//            });
//
//        }
//
//        public void setText(String s) {
//            mTextView.setText(s);
//        }
//    }
//}
