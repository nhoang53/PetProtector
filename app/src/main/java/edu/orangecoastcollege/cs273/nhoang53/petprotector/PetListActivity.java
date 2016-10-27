package edu.orangecoastcollege.cs273.nhoang53.petprotector;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class PetListActivity extends AppCompatActivity {

    private ImageView petImageView;
    private static final int REQUEST_CODE = 100; // successful values, and it can be any number

    // This member variable stores the URI to whatever image has been selected
    // Default: none.png (R.drawable.none)
    // (e.g.  android.resource://edu.orangecoastcollege.cs273.nhoang53.petprotector/54 )
    private Uri imageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        // Hook up the perImageView to the layout:
        petImageView = (ImageView)findViewById(R.id.petImageView);

        // Constructs a full URI to any android resource (id, drawable, color, layout, etc)
        //imageURI = getUriToResource(this,R.drawable.none);

        // set the imageURI of the ImageView in code
        //petImageView.setImageURI(imageURI);
    }

    public void selectedPetImage(View view){

        // List of all permissions we need to request from user
        ArrayList<String> permList = new ArrayList<>();

        // Start by seeing if we have permission to camera
        int cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if(cameraPermission != PackageManager.PERMISSION_GRANTED)
        {
            permList.add(Manifest.permission.CAMERA); // hold Ctrl and hoover mouse object to see values
        }

        // Next check to see if we have read external storage permission
        int readExternamStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(readExternamStoragePermission != PackageManager.PERMISSION_GRANTED)
            permList.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        // Next check to see if we have read external storage permission
        int writeExternamStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(writeExternamStoragePermission != PackageManager.PERMISSION_GRANTED)
            permList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        // If the list has items (sieze > 0), we need to request permissions from user:

        if(permList.size() > 0)
        {
            // Convert the ArrayList ito an Array of Strings
            String[] perms = new String[permList.size()];
            // Request permissions from the user: (We have no Control for that pop-up)
            ActivityCompat.requestPermissions(this, permList.toArray(perms), REQUEST_CODE);
        }

        // If we have all 3 permissions, open ImageGallery:
        if(cameraPermission == PackageManager.PERMISSION_GRANTED
            && readExternamStoragePermission == PackageManager.PERMISSION_GRANTED
            && writeExternamStoragePermission == PackageManager.PERMISSION_GRANTED)
        {
            // Use an Intent to launch gallery and take pictures
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, REQUEST_CODE);
        }
        else
            Toast.makeText(this, "Pet Protector requires camera and external storage permission.",
                    Toast.LENGTH_LONG).show();
    }

    @Override // Ctrl + O to get this activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        // Code to handle when the user closes the image gallery (by selecting an image
        // or pressing the back button

        // intent data is the URI selected from image gallery
        // RESULT_OK: built in activity
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null){
            imageURI = data.getData();
            petImageView.setImageURI(imageURI);
        }
    }

    /**
     * Get uri to any resources type within an Android Studio project. Method is public static
     * to allow other classes to use it as a helper function.
     *
     * @param context The current context
     * @param resId The resource identifier of the drawble
     * @return Uri to resource by given id
     * @throws Resources.NotFoundException If the given resource id does not exist.
     */

    public static Uri getUriToResource(@NonNull Context context, @AnyRes int resId) throws Resources.NotFoundException
    {
        /** Return a Resources instance for your application's package */
        Resources res = context.getResources();
        /** return URI */

        Log.i("imageUri: " + Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                + "://" + res.getResourcePackageName(resId)
                + '/' + res.getResourceTypeName(resId)
                + '/' + res.getResourceEntryName(resId)
        ), "");

        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                + "://" + res.getResourcePackageName(resId)
                + '/' + res.getResourceTypeName(resId)
                + '/' + res.getResourceEntryName(resId)
                        );
    }
}
