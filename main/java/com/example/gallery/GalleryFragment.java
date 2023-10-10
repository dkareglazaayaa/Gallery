package com.example.gallery;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.gallery.Utils.ImageManager;
import com.example.gallery.model.Gallery;
import com.example.gallery.model.ImageFolder;

import java.io.IOException;
import java.util.ArrayList;

public class GalleryFragment extends Fragment {
    private ArrayList<ImageFolder> images;

    private GridLayout galleryLayout;
    private Gallery gallery;
    private static GalleryFragment galleryFragment = new GalleryFragment();
    private MediaPlayer player = new MediaPlayer();
    private int soundId=-1;

    private GalleryFragment() {

    }

    public static GalleryFragment getGalleryFragment() {
        return galleryFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        galleryLayout = view.findViewById(R.id.images);

        gallery = Gallery.getGallery();
        images = gallery.getImages();

        gallery.fillGallery();
        fillGridLayout(view);

        return view;
    }

    private void fillGridLayout(View view) {
        for (ImageFolder image:images) {
            addImageView(image);
        }
    }

    public void addImageView(ImageFolder image) {
        ImageView imageView = new ImageView(this.getContext());
        imageView.setId(image.getId());

        ImageManager.SetImage(imageView, image, 350, 350);

        addImageViewButton(imageView);
        galleryLayout.addView(imageView,0);
    }
    private void addImageViewButton(ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                try {
                    if (id == soundId) {
                        player.stop();
                        player.reset();
                        return;
                    }
                    player.stop();
                    player.reset();

                    ImageFolder image = gallery.findById(id);
                    player.setDataSource(image.getSoundPath());

                    player.prepare();
                    player.start();

                    soundId = id;

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }
}