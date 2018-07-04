package me.yasiru.udacityapi.util;
import android.content.Context;
import android.util.DisplayMetrics;



public class Utility {

    // calculate number of column according to display size
    public static int calculateNoOfColumns(Context context) {
//        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
//        int noOfColumns = (int) (dpWidth / 180);
        return 1;
    }
}
