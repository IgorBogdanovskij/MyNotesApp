//package com.example.notes.presentation.setting;
//
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AlertDialog;
//import androidx.fragment.app.DialogFragment;
//
//import com.example.notes.R;
//
//public class AlertDialogAccentColor extends DialogFragment {
//
//
//    interface Callback{
//        void getColor(int color);
//    }
//
//    private View mView;
//    private Callback mCallback;
//    public AlertDialogAccentColor(View view, Callback callback) {
//        mView = view;
//        mCallback = callback;
//    }
//
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        // TODO: 29/05/2021 colorpicker
//
//        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_color_accent, null);
//        ColorPickerAccent colorPickerAccent = new ColorPickerAccent(dialogView);
//        LinearLayout oval = dialogView.findViewById(R.id.forOvalSetting);
//        AlertDialog dialog = new AlertDialog.Builder(getContext())
//                .setView(dialogView)
//                .setTitle("Choose accent color")
//                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        mCallback.getColor(colorPickerAccent.getColor());
//                        Toast.makeText(dialogView.getContext(), "color = " + colorPickerAccent.getColor(), Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .create();
//        return dialog;
//
//    }
//}
