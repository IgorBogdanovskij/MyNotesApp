//package com.example.notes.presentation.edit;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
//import com.example.notes.R;
//
//import java.util.ArrayList;
//
//public class ColorPicker implements IColorPicker {
//
//    private int mColor;
//    private ArrayList<ImageView> mCheck;
//    private int[] mColors = new int[]{R.color.red, R.color.pink, R.color.purple,
//            R.color.deep_purple, R.color.blue,
//            R.color.cyan, R.color.teal, R.color.green,
//            R.color.light_green, R.color.lime, R.color.yellow,
//            R.color.amber, R.color.orange, R.color.deep_orange,
//            R.color.red, R.color.brown, R.color.grey,
//            R.color.blue_grey};
//
//
//    public ColorPicker(View view) {
//        mCheck = new ArrayList<>();
//        LinearLayout forOval = view.findViewById(R.id.forOval);
//        for (int i = 0; i < mColors.length; i++) {
//            View view2 = LayoutInflater.from(view.getContext()).inflate(R.layout.oval, null);
//            ImageView oval = view2.findViewById(R.id.oval);
//            ImageView check = view2.findViewById(R.id.check);
//            mCheck.add(check);
//            final int colorId = mColors[i];
//
//            oval.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    setColor(colorId);
//                    for (ImageView imageView :
//                            mCheck) {
//                        if (imageView == check) {
//                            check.setVisibility(View.VISIBLE);
//                        } else {
//                            imageView.setVisibility(View.INVISIBLE);
//                        }
//                    }
//                }
//            });
//            oval.setColorFilter(view.getResources().getColor(mColors[i]));
//            forOval.addView(view2);
//        }
//    }
//
//    @Override
//    public void setColor(int colorId) {
//
//        mColor = colorId;
//    }
//
//    @Override
//    public int getColor() {
//        return mColor;
//    }
//}
