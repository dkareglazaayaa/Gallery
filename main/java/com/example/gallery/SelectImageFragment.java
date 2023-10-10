package com.example.gallery;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.gallery.Utils.SoundManager;
import com.example.gallery.model.Gallery;
import com.example.gallery.model.ImageFolder;

import java.io.File;


public class SelectImageFragment extends Fragment {
    private Gallery gallery;
    private ImageView imagePreview;
    private ImageFolder image;
    private Button selectImageButton;
    private Button addImageButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_image, container, false);
        gallery = Gallery.getGallery();

        selectImageButton = view.findViewById(R.id.selectImageButton);
        addImageButton=view.findViewById(R.id.addImageButton);
        imagePreview = view.findViewById(R.id.imagePreview);

        setSelectImageButton();
        setAddImageButton();

        return view;
    }


    public void setSelectImageButton() {
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                someActivityResultLauncher.launch(intent);
            }
        });
    }
    public void setAddImageButton() {
        addImageButton.setVisibility(View.INVISIBLE);
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gallery.addImageFolder(image);

                GalleryFragment gallery_fragment = GalleryFragment.getGalleryFragment();
                gallery_fragment.addImageView(image);
            }
        });
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        if (result.getResultCode() == RESULT_OK) {
                            Uri selectedImage = result.getData().getData();
                            String[] filePathColumn = {MediaStore.Images.Media.DATA};

                            Cursor cursor = getActivity().getContentResolver()
                                    .query(selectedImage, filePathColumn, null, null, null);
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            String imagePath = cursor.getString(columnIndex);
                            cursor.close();

                            setImagePreview(imagePath);
                            addImageButton.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });

    private void setImagePreview(String imagePath) {
        image = new ImageFolder(gallery.getImages().size(),imagePath, SoundManager.GenerateSound());

        File imageFile = new File(image.getImagePath());
        Bitmap bm = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        imagePreview.setImageBitmap(bm);
    }
}