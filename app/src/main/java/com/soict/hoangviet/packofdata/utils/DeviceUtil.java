package com.soict.hoangviet.packofdata.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Surface;
import android.view.TextureView;
import android.view.inputmethod.InputMethodManager;
import com.soict.hoangviet.packofdata.BuildConfig;

public class DeviceUtil {
    private DeviceUtil(){}
    /**
     * Check internet connected
     *
     * @param context
     * @return
     */
    public static boolean hasConnection(Context context) {
        if (context == null) {
            return false;
        }

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }

        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }

        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }
        return false;
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
            }
        }
    }

    public static void clearFocus(Activity activity) {
        if (activity != null && activity.getCurrentFocus() != null) {
            activity.getCurrentFocus().clearFocus();
        }
    }

    public static String getAppVersion() {
        return BuildConfig.VERSION_NAME;
    }

    public static int convertDpToPx(Context context, int inputDp) {
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, inputDp, resources.getDisplayMetrics());
    }

    public static float convertPixelsToDp(float px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static int getSoftButtonsBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public static int widthScreenPixel(Context context) {
        if (context == null) {
            return 0;
        }
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        return metrics.widthPixels;
    }

    public static void transformImage(Activity activity, TextureView textureView, int width, int height) {
        if (textureView == null) {
            return;
        } else try {
            {
                Matrix matrix = new Matrix();
                int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
                RectF textureRectF = new RectF(0, 0, width, height);
                RectF previewRectF = new RectF(0, 0, textureView.getHeight(), textureView.getWidth());
                float centerX = textureRectF.centerX();
                float centerY = textureRectF.centerY();
                if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
                    previewRectF.offset(centerX - previewRectF.centerX(), centerY - previewRectF.centerY());
                    matrix.setRectToRect(textureRectF, previewRectF, Matrix.ScaleToFit.FILL);
                    float scale = Math.max((float) width / width, (float) height / width);
                    matrix.postScale(scale, scale, centerX, centerY);
                    matrix.postRotate(90 * (rotation - 2), centerX, centerY);
                }
                textureView.setTransform(matrix);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getRealPathFromURI(Context context, Uri contentURI) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        // Get the cursor
        Cursor cursor = context.getContentResolver().query(contentURI, filePathColumn, null, null, null);
        // Move to first row
        cursor.moveToFirst();
        //Get the column index of MediaStore.Images.Media.DATA
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        //Gets the String value in the column
        String imgDecodableString = cursor.getString(columnIndex);
        cursor.close();
        return imgDecodableString;
    }
}
