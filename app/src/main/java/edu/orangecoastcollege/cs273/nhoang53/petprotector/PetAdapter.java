package edu.orangecoastcollege.cs273.nhoang53.petprotector;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joseph on 11/3/2016.
 */

public class PetAdapter extends ArrayAdapter<Pet>{

    private Context mContext;
    private int mResourceId;
    private List<Pet> mPetList = new ArrayList<>();

    public PetAdapter(Context context, int resource, List<Pet> petList) {
        super(context, resource, petList);
        mContext = context;
        mResourceId = resource; // reourceID is a layout.xml
        mPetList = petList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // LayoutInflater is to CAST the layout.xml to View(pet_list_item)
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        // TODO:  Write the code to correctly inflate the view (pet_list_item) with
        // TODO:  all widgets filled with the appropriate Pet information.
        TextView nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        TextView detailTextView = (TextView) view.findViewById(R.id.detailTextView);
        ImageView imageView = (ImageView) view.findViewById(R.id.petImageView);

        Pet selectedPet = mPetList.get(position);

        nameTextView.setText(selectedPet.getName());
        detailTextView.setText(selectedPet.getDetail());
        imageView.setImageURI(selectedPet.getmImageUri());

        // set Tag
        LinearLayout selectedLinearLayout = (LinearLayout) view.findViewById(R.id.petListLinearLayout);
        selectedLinearLayout.setTag(selectedPet);

        return view;
    }
}
