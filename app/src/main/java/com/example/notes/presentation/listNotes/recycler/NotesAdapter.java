//package com.example.notes.presentation.listNotes.recycler;
//
//import android.os.Build;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Filter;
//import android.widget.Filterable;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.notes.R;
//import com.example.notes.data.locale.entity.Note;
//import com.example.notes.utility.Utils;
//import com.google.android.material.card.MaterialCardView;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import static android.view.View.VISIBLE;
//
//public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.Vh> implements Filterable {
//
//
//    public interface Callback {
//        void onClick(Note noteEntityKotlin);
//
//        void onLongClick(View view, Note noteEntityKotlin, RecyclerView.ViewHolder holder);
//    }
//
//
//    private Callback mCallback;
//    List<Note> mNoteEntityKotlins;
//
//    public NotesAdapter(Callback callback) {
//        mCallback = callback;
//        setHasStableIds(true);
//    }
//
//    @NonNull
//    @Override
//    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes, parent, false);
//        Vh vh = new Vh(view);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Vh holder, int position) {
//
//        String title = mNoteEntityKotlins.get(position).getTitle();
//        String description = mNoteEntityKotlins.get(position).getDescription();
//        holder.settingVh(title, description, mNoteEntityKotlins.get(position).getColorBackground(), mNoteEntityKotlins.get(position).getColorText());
//    }
//
//    @Override
//    public int getItemCount() {
//        if (mNoteEntityKotlins != null) {
//            return mNoteEntityKotlins.size();
//        }
//        return 0;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return mNoteEntityKotlins.get(position).getId();
//    }
//
//    public void setNotes(List<Note> noteEntityKotlins) {
//        this.mNoteEntityKotlins = noteEntityKotlins;
//         notifyDataSetChanged();
//    }
//
//    public List<Note> getListNotes() {
//        return mNoteEntityKotlins;
//    }
//
//    public Note getNoteFromAdapter(int position) {
//        return mNoteEntityKotlins.get(position);
//    }
//
//    private Filter filter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//
//            String searchText = constraint.toString().toLowerCase();
//            List<Note> listFilter = new ArrayList<>();
//            for (Note noteEntityKotlinEntry :
//                    mNoteEntityKotlins) {
//                if (noteEntityKotlinEntry.getTitle().toLowerCase().contains(searchText)) {
//                    listFilter.add(noteEntityKotlinEntry);
//                }
//            }
//            FilterResults filterResults = new FilterResults();
//            filterResults.values = listFilter;
//            return filterResults;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            if (mNoteEntityKotlins != null) {
//                mNoteEntityKotlins.clear();
//                mNoteEntityKotlins.addAll((Collection<? extends Note>) results.values);
//            }
//
//            notifyDataSetChanged();
//        }
//    };
//
//    @Override
//    public Filter getFilter() {
//        return filter;
//    }
//
//    class Vh extends RecyclerView.ViewHolder {
//
//        private MaterialCardView mMaterialCardView;
//        private TextView mTitle;
//        private TextView mDescription;
//        private TextView mGroupName;
//
//        public Vh(@NonNull View itemView) {
//            super(itemView);
//
//            mTitle = itemView.findViewById(R.id.textForAdapterItemTitle);
//            mDescription = itemView.findViewById(R.id.textForAdapterItemDescription);
//            mGroupName = itemView.findViewById(R.id.groupName);
//            mMaterialCardView = itemView.findViewById(R.id.cardViewForNote);
//
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Note noteEntityKotlin = mNoteEntityKotlins.get(getAdapterPosition());
//                    mCallback.onClick(noteEntityKotlin);
//                }
//            });
//
//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    Note noteEntityKotlin = mNoteEntityKotlins.get(getAdapterPosition());
//                    mCallback.onLongClick(v, noteEntityKotlin, Vh.this);
//                    return false;
//                }
//            });
//        }
//
//        private void settingVh(String title, String description, int color, int colorText) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                mTitle.setMaxLines(Utils.getRandomNumber(1, 2));
//            }
//            if (colorText != 0) {
//                mTitle.setTextColor(itemView.getContext().getColor(colorText));
//            }
//            mTitle.setText(title);
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                mDescription.setMaxLines(Utils.getRandomNumber(2, 4));
//            }
//            if (colorText != 0) {
//                mDescription.setTextColor(itemView.getContext().getColor(colorText));
//                mGroupName.setTextColor(itemView.getContext().getColor(colorText));
//            }
//
//            if (mNoteEntityKotlins.get(getAdapterPosition()).getNameGroup() != null && !mNoteEntityKotlins.get(getAdapterPosition()).getNameGroup().isEmpty()) {
//                mGroupName.setText(mNoteEntityKotlins.get(getAdapterPosition()).getNameGroup());
//                mGroupName.setVisibility(VISIBLE);
//            } else {
//                mGroupName.setVisibility(View.GONE);
//                mGroupName.setBackgroundResource(0);
//            }
//            mDescription.setText(description);
//            if (mNoteEntityKotlins.get(getAdapterPosition()).getColorBackground() != 0) {
//                mMaterialCardView.setCardBackgroundColor(itemView.getContext().getColor(color));
//            }
//
//
//        }
//    }
//}
