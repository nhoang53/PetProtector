package edu.orangecoastcollege.cs273.nhoang53.petprotector;

import android.net.Uri;

/**
 * Created by Joseph on 11/3/2016.
 */

public class Pet {
    private int mId;
    private String mName;
    private String mDetail;
    private String mPhone;
    private Uri mImageUri;

    public Pet( int mId, String mname, String mdetail, String mphone, Uri mImageUri) {
        mName = mname;
        mDetail = mdetail;
        mPhone = mphone;
        this.mId = mId;
        this.mImageUri = mImageUri;
    }

    public Pet(String mname, String mdetail, String mphone, Uri mImageUri) {
        mId = -1;
        mName = mname;
        mDetail = mdetail;
        mPhone = mphone;
        this.mImageUri = mImageUri;
    }

    public String getName() {
        return mName;
    }

    public String getDetail() {
        return mDetail;
    }

    public String getPhone() {
        return mPhone;
    }

    public int getmId() {
        return mId;
    }

    public Uri getmImageUri() {
        return mImageUri;
    }

    public void setName(String mname) {
        mName = mname;
    }

    public void setDetail(String mdetail) {
        mDetail = mdetail;
    }

    public void setPhone(String mphone) {
        mPhone = mphone;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmImageUri(Uri mImageUri) {
        this.mImageUri = mImageUri;
    }

    @Override
    public String toString() {
        return "Pet: { ID = " + mId + ", "
                + " name=" + mName + ", "
                + " details=" + mDetail + ", "
                + " phone=" + mPhone + ", "
                + " imageUri=" + mImageUri + " } ";
    }
}
