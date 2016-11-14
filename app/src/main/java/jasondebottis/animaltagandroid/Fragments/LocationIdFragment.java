package jasondebottis.animaltagandroid.Fragments;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jasondebottis.animaltagandroid.Models.AnimalModel;
import jasondebottis.animaltagandroid.R;
import jasondebottis.animaltagandroid.Utilities.LocationUtility;
import jasondebottis.animaltagandroid.databinding.LocationIdFragmentBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.android.gms.plus.PlusOneDummyView.TAG;

public class LocationIdFragment extends Fragment implements LocationUtility.GetLocationListener {
    private LocationIdFragmentBinding mBinding;
    public static final String kPicArgs = "ResultPic";
    private LocationUtility mLocationUtility;

    private final Callback<List<AnimalModel>> mGetAnimalsInLocationCallback = new Callback<List<AnimalModel>>() {
        @Override
        public void onResponse(Call<List<AnimalModel>> call, Response<List<AnimalModel>> response) {

        }

        @Override
        public void onFailure(Call<List<AnimalModel>> call, Throwable t) {

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.location_id_fragment, container, false);
        mLocationUtility = new LocationUtility(getContext());
        mLocationUtility.setGetLocationListener(this);
        Bundle args = getArguments();
        if (args != null) {
            byte[] bytes = args.getByteArray(kPicArgs);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            mBinding.captureResultImageView.setImageBitmap(bitmap);
            GetPrimaryColor();
        }
        return mBinding.getRoot();
    }

    private void GetPrimaryColor() {
        Bitmap bitmap = ((BitmapDrawable) mBinding.captureResultImageView.getDrawable()).getBitmap();
        for (int x = 0; x < bitmap.getWidth(); x++) {
            for (int y = 0; y < bitmap.getHeight(); y++) {
                int pixel = bitmap.getPixel(x, y);
                int redval = Color.red(pixel);
                int greenval = Color.green(pixel);
                int blueval = Color.blue(pixel);
                //Log.d(TAG, "GetPrimaryColor: (" + redval + ")-(" + greenval + ")-(" + blueval + ")");
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mLocationUtility.ClientStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mLocationUtility.ClientStop();
    }


    @Override
    public void getLocation(Location inLocation) {
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        List<Address> addresses = new ArrayList<>();

        Double latitude = inLocation.getLatitude();
        Double longitude = inLocation.getLongitude();

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);

            if (addresses != null && addresses.size() > 0) {
                String address = addresses.get(0).getAddressLine(0);
                Log.d(TAG, "address:" + address);
            }
        } catch (IOException e) {
            Log.e(TAG, "getLocation: ", e.getCause());
        }
    }
}
