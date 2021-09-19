//package com.example.notes.presentation.edit;
//
//import android.os.Bundle;
//import android.view.View;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProviders;
//import androidx.navigation.Navigation;
//
//import com.example.noteEntityKotlins.R;
//
//import java.util.List;
//
//public class EditFragment extends Fragment {
//
//
//    private EditViewModel mEditViewModel;
//    private EditView mEditView;
//
//    public EditFragment() {
//        super(R.layout.fragment_edit);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        mEditViewModel = ViewModelProviders.of(this).get(EditViewModel.class);
//        mEditView = new EditView(view, new EditView.Callback() {
//
//            @Override
//            public void onSaveClick(Note noteEntityKotlin) {
//                // TODO: 24/05/2021
//
//                mEditViewModel.updateNote(noteEntityKotlin);
//                Navigation.findNavController(view).popBackStack();
//
//            }
//
//            @Override
//            public void onBackPressed() {
//                Navigation.findNavController(view).popBackStack();
//            }
//        });
//        getBundle();
//
//
//        mEditViewModel.getGroupNameFromNote().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
//            @Override
//            public void onChanged(List<String> list) {
//                mEditView.setListAndCreateAdapter(list);
//            }
//        });
//    }
//
//
//    private void getBundle() {
//        if (getArguments() != null) {
//            int id = getArguments().getInt("id");
//            mEditView.setNote(mEditViewModel.getNote(id));
//        }
//
//    }
//}
