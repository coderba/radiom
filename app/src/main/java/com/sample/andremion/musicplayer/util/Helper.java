package com.sample.andremion.musicplayer.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;

/**
 * Created by cembasarbaskan on 26/10/2016.
 */

public class Helper {

    public static byte[] drawableToByteArray(Drawable drawable) {

        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] bitmapData = stream.toByteArray();

        return bitmapData;
    }

    public static Bitmap byteArrayToBitmap(byte[] bytes) {

        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bitmap;

    }

}
