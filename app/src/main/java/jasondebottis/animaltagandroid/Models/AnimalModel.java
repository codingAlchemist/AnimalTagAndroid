package jasondebottis.animaltagandroid.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

import jasondebottis.animaltagandroid.BaseClasses.BaseData;

@Table(name = "Animals")
public class AnimalModel extends Model implements Parcelable, BaseData {

    @SerializedName("common")
    @Column(name = "common")
    public String mCommon;

    @SerializedName("latin")
    @Column(name = "latin")
    public String mLatin;

    @SerializedName("short")
    @Column(name = "short")
    public String mShortDesc;

    @SerializedName("long")
    @Column(name = "long")
    public String mLongDesc;

    @SerializedName("type")
    @Column(name = "type")
    public String mType;

    @SerializedName("habitat")
    @Column(name = "habitat")
    public String mHabitat;

    @SerializedName("image1")
    @Column(name = "image1")
    public String mImage1;

    @SerializedName("image2")
    @Column(name = "image2")
    public String mImage2;

    @SerializedName("image3")
    @Column(name = "image3")
    public String mImage3;


    @SerializedName("tag")
    @Column(name = "tag")
    public String mTag;


    public AnimalModel() {

    }

    public AnimalModel(String inCommon, String inLatin, String inType, String inShortDesc, String inLongDesc, String inImage1, String inImage2, String inImage3, String inHabitat, String inTag) {
        this.mCommon = inCommon;
        this.mLatin = inLatin;
        this.mType = inType;
        this.mShortDesc = inShortDesc;
        this.mLongDesc = inLongDesc;
        this.mImage1 = inImage1;
        this.mImage2 = inImage2;
        this.mImage3 = inImage3;
        this.mHabitat = inHabitat;
        this.mTag = inTag;
    }


    public AnimalModel(Parcel inParcel) {
        String[] data = new String[10];
        inParcel.readStringArray(data);
        this.mCommon = data[0];
        this.mLatin = data[1];
        this.mShortDesc = data[2];
        this.mLongDesc = data[3];
        this.mImage1 = data[4];
        this.mImage2 = data[5];
        this.mImage3 = data[6];
        this.mHabitat = data[7];
        this.mType = data[8];
        this.mTag = data[9];
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel inDest, int inFlags) {
        inDest.writeStringArray(new String[]{
            this.mCommon,
            this.mLatin,
            this.mShortDesc,
            this.mLongDesc,
            this.mImage1,
            this.mImage2,
            this.mImage3,
            this.mHabitat,
            this.mType,
            this.mTag
        });
    }


    public static final Parcelable.Creator<AnimalModel> CREATOR = new Parcelable.Creator<AnimalModel>() {

        @Override
        public AnimalModel createFromParcel(Parcel inParcel) {

            return new AnimalModel(inParcel);
        }


        @Override
        public AnimalModel[] newArray(int size) {
            return new AnimalModel[size];
        }
    };

}
