package jasondebottis.animaltagandroid.Utilities;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import jasondebottis.animaltagandroid.Constants;

import static com.google.android.gms.plus.PlusOneDummyView.TAG;



public class LocationUtility implements GoogleApiClient.ConnectionCallbacks {
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private AddressResultReciever mResultReceiver;
    private Handler mHandler;
    private Context mContext;
    private GetLocationListener mGetLocationListener;

    public void setGetLocationListener(GetLocationListener inGetLocationListener) {
        this.mGetLocationListener = inGetLocationListener;
    }

    public LocationUtility(Context inContext) {
        mContext = inContext;
        InitGoogleApiClient();
    }


    public void ClientStart() {
        mGoogleApiClient.connect();
    }

    public void ClientStop() {
        mGoogleApiClient.disconnect();
    }

    private final GoogleApiClient.OnConnectionFailedListener kOnConnectionFailedListener = new GoogleApiClient.OnConnectionFailedListener() {
        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            AlertUtility.NewInstance(connectionResult.getErrorMessage());
        }
    };

    public void InitGoogleApiClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(kOnConnectionFailedListener)
                    .addApi(LocationServices.API)
                    .build();
            mHandler = new Handler();
            mResultReceiver = new AddressResultReciever(mHandler);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mLocation != null) {
                Log.d(TAG, "onConnected: " + mLocation.getLatitude() + "," + mLocation.getLongitude());
                if (mGetLocationListener != null) {
                    mGetLocationListener.getLocation(mLocation);
                }

            }
        } catch (SecurityException e) {
            AlertUtility.NewInstance(e.getMessage());
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

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

    public interface GetLocationListener {
        void getLocation(Location inLocation);
    }

}
