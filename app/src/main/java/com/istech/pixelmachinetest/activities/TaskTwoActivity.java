package com.istech.pixelmachinetest.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.istech.pixelmachinetest.R;
import com.istech.pixelmachinetest.adapter.ImagesAdapter;
import com.istech.pixelmachinetest.databinding.ActivityTaskTwoBinding;
import com.istech.pixelmachinetest.model.ImagesBitmap;
import com.istech.pixelmachinetest.utils.Commn;

import java.io.IOException;
import java.util.ArrayList;

public class TaskTwoActivity extends AppCompatActivity {
    private Context context;
    private TaskTwoActivity activity;
    private ActivityTaskTwoBinding binding;
    int PICK_GALLERY_IMAGE = 1;
    private ArrayList<ImagesBitmap> mList = new ArrayList<>();
    int PICK_CAMERA_IMAGE = 2;
    private ImagesAdapter imagesAdapter = new ImagesAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_task_two);
        context = activity = this;
        initData();
        handleclick();
    }

    private void initData() {
        binding.rvImages.setAdapter(imagesAdapter);
    }

    private void handleclick() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.tvDeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                picker();

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PICK_CAMERA_IMAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera Permission is required to use Camera", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == PICK_GALLERY_IMAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
            }
        }
    }

    private void picker() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Add photo");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (options[which].equals("Take Photo")) {
                    askCameraPermissions();
                } else if (options[which].equals("Choose from Gallery")) {
                    askGallery();
                } else if (options[which].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void askGallery() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            androidx.core.app.ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_GALLERY_IMAGE);
        } else {
            openGallery();
        }
    }

    private void askCameraPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            androidx.core.app.ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PICK_CAMERA_IMAGE);
        } else {
            openCamera();
        }
    }

    private void openCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, PICK_CAMERA_IMAGE);
        } else {
            Toast.makeText(context, "Cannot Open Camera", Toast.LENGTH_SHORT).show();
        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_GALLERY_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        Commn.log("data:" + data.toString());
        if (requestCode == PICK_GALLERY_IMAGE) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                mList.add(new ImagesBitmap(bitmap));
                imagesAdapter.updateData(mList);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (requestCode == PICK_CAMERA_IMAGE) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image
                Bitmap newProfilePic = extras.getParcelable("data");
                mList.add(new ImagesBitmap(newProfilePic));
                imagesAdapter.updateData(mList);
            }
        }
    }
}