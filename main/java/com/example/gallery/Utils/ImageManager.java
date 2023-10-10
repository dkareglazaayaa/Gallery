package com.example.gallery.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gallery.R;
import com.example.gallery.model.ImageFolder;

import java.io.File;

public class ImageManager {

    public static void SetImage(ImageView imageView, ImageFolder image,int width,int height) {

        File imageFile = new File(image.getImagePath());
        Bitmap bm = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        imageView.setImageBitmap(bm);
        imageView.setPadding(0, 0, 20, 0);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);;
        imageView.setLayoutParams(params);

    }

    public static class OwnFragmentManager {
        public static void changeFragment(FragmentManager fm, Fragment newFragment){
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.fragment,newFragment);
            ft.commit();
        }
    }
}
