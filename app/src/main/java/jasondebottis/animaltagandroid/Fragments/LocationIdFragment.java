package jasondebottis.animaltagandroid.Fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.os.ResultReceiver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jasondebottis.animaltagandroid.Constants;
import jasondebottis.animaltagandroid.MainActivity;
import jasondebottis.animaltagandroid.Models.AnimalModel;
import jasondebottis.animaltagandroid.R;
import jasondebottis.animaltagandroid.Services.FetchAddressIntentService;
import jasondebottis.animaltagandroid.Utilities.AlertUtility;
import jasondebottis.animaltagandroid.databinding.LocationIdFragmentBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.android.gms.plus.PlusOneDummyView.TAG;

public class LocationIdFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks {
    private LocationIdFragmentBinding mBinding;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private AddressResultReciever mResultReceiver;
    private Handler mHandler;
    public static final String kPicArgs = "ResultPic";

    private final GoogleApiClient.OnConnectionFailedListener kOnConnectionFailedListener = new GoogleApiClient.OnConnectionFailedListener() {
        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            AlertUtility.NewInstance(connectionResult.getErrorMessage());
        }
    };

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
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(kOnConnectionFailedListener)
                .addApi(LocationServices.API)
                .build();
            mHandler = new Handler();
            mResultReceiver = new AddressResultReciever(mHandler);
        }
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
        mGoogleApiClient.connect();

    }

    @Override
    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mLocation != null) {
                Log.d(TAG, "onConnected: " + mLocation.getLatitude() + "," + mLocation.getLongitude());
                getLocation(mLocation);
            }
        } catch (SecurityException e) {
            AlertUtility.NewInstance(e.getMessage());
        }
        //startIntentService();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

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

    protected void startIntentService() {
        Intent intent = new Intent(MainActivity.GetContext(), FetchAddressIntentService.class);
        intent.putExtra(Constants.RECEIVER, mResultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, mLocation);
        MainActivity.GetContext().startService(intent);
    }

    public class AddressResultReciever extends ResultReceiver {


        /**
         * Create a new ResultReceive to receive results.  Your
         * {@link #onReceiveResult} method will be called from the thread running
         * <var>handler</var> if given, or from an arbitrary thread if null.
         *
         * @param handler
         */
        public AddressResultReciever(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            String AddressOutput = resultData.getString(Constants.RESULT_DATA_KEY);
            Log.i(TAG, "onReceiveResult: " + AddressOutput);
        }
    }


}
