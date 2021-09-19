//package com.example.notes.presentation.writeNote;
//
//import android.app.Activity;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.AutoCompleteTextView;
//import android.widget.EditText;
//import android.widget.SearchView;
//import android.widget.Toolbar;
//
//import androidx.annotation.Nullable;
//
//import com.example.notes.R;
//import com.example.notes.data.locale.entity.Note;
//import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
//import com.google.android.material.textfield.TextInputLayout;
//
//import java.util.Date;
//import java.util.List;
//
//public class NotesViewWrite {
//
//
//    interface CallbackWriteFragment {
//        void onFloatingClick(int id, Note note);
//    }
//
//    private int mId;
//    private Toolbar mToolbar;
//    private SearchView mSearchView;
//    private EditText mEditTextTitle;
//    private EditText mEditTextDescription;
//    private EditText mEditTextNameGroup;
//    private ExtendedFloatingActionButton mFloatingActionButton;
//    private Date mDate;
//    private int mColor;
//    private int mColorText;
//    private String mNameGroup;
//    private String mNameGroupIfNew;
//    private ArrayAdapter mArrayAdapter;
//    private final TextInputLayout mTextInputLayout;
//    private final AutoCompleteTextView mAutoCompleteTextView;
//
//
//    public NotesViewWrite(Activity activity, View view, CallbackWriteFragment callbackWriteFragment) {
//        mEditTextTitle = view.findViewById(R.id.editTextTitle);
//        mEditTextDescription = view.findViewById(R.id.editTextTextDescription);
//        mTextInputLayout = view.findViewById(R.id.textInputLayoutWrite);
//        mAutoCompleteTextView = view.findViewById(R.id.autoCompleteTextViewWrite);
//        mAutoCompleteTextView.setThreshold(1);
//
//        mToolbar = view.findViewById(R.id.toolbarNotes);
//        mFloatingActionButton = view.findViewById(R.id.floatingActionButtonAdd);
//        mFloatingActionButton.setOnClickListener(v -> {
//            String title = mEditTextTitle.getText().toString();
//            String description = mEditTextDescription.getText().toString();
//            if (mAutoCompleteTextView.getText().toString() != null && !mAutoCompleteTextView.getText().toString().isEmpty()) {
//                mNameGroupIfNew = mAutoCompleteTextView.getText().toString();
//            } else {
//                mNameGroupIfNew = "";
//            }
//
//            Note note = new Note();
//            if (mId != 0) {
//                note.setId(mId);
//                note.setTitle(title);
//                note.setDescription(description);
//                note.setColorBackground(mColor);
//                note.setColorText(mColorText);
//                note.setData(mDate);
//                if (mAutoCompleteTextView.getText().toString() != null && !mAutoCompleteTextView.getText().toString().isEmpty()) {
//                    note.setNameGroup(mAutoCompleteTextView.getText().toString());
//                } else {
//                    note.setNameGroup(mNameGroup);
//                }
//            } else {
//                note.setId(mId);
//                note.setTitle(title);
//                note.setDescription(description);
//                note.setColorBackground(0);
//                note.setColorText(0);
//                note.setData(new Date());
//                note.setNameGroup(mNameGroupIfNew);
//
//            }
//            callbackWriteFragment.onFloatingClick(mId, note);
//
//        });
//
//
//    }
//
//    public void setNote(@Nullable Note note) {
//        if (note != null) {
//            if (note.getNameGroup() != null && !note.getNameGroup().isEmpty()) {
//                mNameGroup = note.getNameGroup();
//            }
//            mColorText = note.getColorText();
//            mColor = note.getColorBackground();
//            mDate = note.getData();
//            mId = note.getId();
//            mEditTextTitle.setText(note.getTitle());
//            mEditTextDescription.setText(note.getDescription());
//
//        }
//
//    }
//
//    public void setListAndCreateAdapter(List<String> list) {
//
//        mArrayAdapter = new ArrayAdapter<>(mAutoCompleteTextView.getContext(), R.layout.support_simple_spinner_dropdown_item, list);
//        mAutoCompleteTextView.setAdapter(mArrayAdapter);
//
//    }
//
//}
