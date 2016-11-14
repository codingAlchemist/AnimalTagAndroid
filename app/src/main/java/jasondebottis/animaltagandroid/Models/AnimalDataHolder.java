package jasondebottis.animaltagandroid.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import jasondebottis.animaltagandroid.BaseClasses.BaseData;

/**
 * Created by jasondebottis on 11/2/16.
 */
@Table(name = "Animals")
public class AnimalDataHolder extends Model implements BaseData {

    @Column(name = "common")
    public String mCommon;

    @Column(name = "latin")
    public String mLatin;

    @Column(name = "short")
    public String mShortDesc;

    @Column(name = "long")
    public String mLongDesc;

    @Column(name = "type")
    public String mType;

    @Column(name = "habitat")
    public String mHabitat;

    @Column(name = "image1")
    public String mImage1;

    @Column(name = "image2")
    public String mImage2;

    @Column(name = "image3")
    public String mImage3;

    @Column(name = "tag")
    public String mTag;

    public AnimalDataHolder() {

    }
}
