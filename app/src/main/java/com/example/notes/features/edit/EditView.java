//package com.example.notes.presentation.edit;
//
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.AutoCompleteTextView;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.widget.Toolbar;
//
//import com.example.noteEntityKotlins.R;
//import com.example.data.locale.entity.Note;
//import com.google.android.material.appbar.MaterialToolbar;
//import com.google.android.material.textfield.TextInputLayout;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class EditView {
//
//    private String mNameGroup;
//    private Note mNoteEntityKotlin;
//    private int mColor = 0;
//
//    public static Bundle argsOfNotes(int id) {
//        Bundle bundle = new Bundle();
//        bundle.putInt("id", id);
//        return bundle;
//    }
//
//    interface Callback {
//        void onSaveClick(Note noteEntityKotlin);
//
//        void onBackPressed();
//    }
//
//    private ArrayList<String> mStringArrayList;
//    private ArrayAdapter<String> mArrayAdapter;
//    private TextInputLayout mTextInputLayout;
//    private AutoCompleteTextView mAutoCompleteTextView;
//    private Callback mCallback;
//    private MaterialToolbar mToolbar;
//
//    public EditView(View view, Callback callback) {
//        mCallback = callback;
//        mTextInputLayout = view.findViewById(R.id.textInputLayout);
//        mToolbar = view.findViewById(R.id.toolbarNotes);
//        mAutoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);
//        mStringArrayList = new ArrayList<>();
//        mAutoCompleteTextView.setThreshold(1);
//        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
//        ColorPicker colorPicker = new ColorPicker(view);
//
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                callback.onBackPressed();
//            }
//        });
//
//
//        if (mAutoCompleteTextView.getText().toString() != null && !mAutoCompleteTextView.getText().toString().isEmpty()) {
//            mNameGroup = mAutoCompleteTextView.getText().toString();
//        } else {
//            mNameGroup = "";
//        }
//        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                if (item.getItemId() == R.id.saveEdit) {
//
//                    if (colorPicker.getColor() != 0) {
//                        mNoteEntityKotlin.setColorBackground(colorPicker.getColor());
//                        mNoteEntityKotlin.setColorText(R.color.black);
//                    } else {
//                        if (mColor != 0) {
//                            mNoteEntityKotlin.setColorText(R.color.black);
//                        } else {
//                            mNoteEntityKotlin.setColorText(0);
//
//                        }
//                        mNoteEntityKotlin.setColorBackground(mColor);
//
//                    }
//                    if (mAutoCompleteTextView.getText().toString() != null && !mAutoCompleteTextView.getText().toString().isEmpty()) {
//                        mNoteEntityKotlin.setNameGroup(mAutoCompleteTextView.getText().toString());
//                    } else {
//                        mNoteEntityKotlin.setNameGroup(mNameGroup);
//                    }
//                }
//                mCallback.onSaveClick(mNoteEntityKotlin);
//                return true;
//            }
//
//        });
//    }
//
//
//    public void setNote(@Nullable Note noteEntityKotlin) {
//        if (noteEntityKotlin != null) {
//            mNoteEntityKotlin = noteEntityKotlin;
//            if (noteEntityKotlin.getNameGroup() != null && !noteEntityKotlin.getNameGroup().isEmpty()) {
//                mNameGroup = noteEntityKotlin.getNameGroup();
//                mColor = noteEntityKotlin.getColorBackground();
//            }
//
//        }
//
//    }
//
//    public void setListAndCreateAdapter(List<String> list) {
//        mStringArrayList.addAll(list);
//        mArrayAdapter = new ArrayAdapter<>(mAutoCompleteTextView.getContext(), R.layout.support_simple_spinner_dropdown_item, mStringArrayList);
//        mAutoCompleteTextView.setAdapter(mArrayAdapter);
//    }
//}
