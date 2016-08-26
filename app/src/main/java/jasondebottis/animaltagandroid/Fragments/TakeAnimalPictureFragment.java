package jasondebottis.animaltagandroid.Fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;

import jasondebottis.animaltagandroid.MainActivity;
import jasondebottis.animaltagandroid.R;
import jasondebottis.animaltagandroid.Utilities.AlertUtility;
import jasondebottis.animaltagandroid.Utilities.NavUtility;
import jasondebottis.animaltagandroid.databinding.TakeAnimalPictureFragmentBinding;

@SuppressWarnings("deprecation")
public class TakeAnimalPictureFragment extends Fragment implements ResultsDialogFragment.OnDialogButtonClickedListener, SurfaceHolder.Callback {
    private static final String TAG = "AndroidCameraApi";
    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    private byte[] mBytes;
    private Bundle mArgs;
    private Camera mCamera;
    private TakeAnimalPictureFragmentBinding mBinding;
    private SurfaceHolder mSurfaceHolder;

    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] inBytes, Camera inCamera) {
            mArgs = new Bundle();
            mArgs.putByteArray(LocationIdFragment.kPicArgs, inBytes);
            MainActivity.NavigateTo(NavUtility.DestinationFragment.kLocationIdFragment, mArgs);
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.take_animal_picture_fragment, container, false);
        mBinding.takePictureButton.setOnClickListener(kOnTakePictureClickListener);
        mSurfaceHolder = mBinding.surfaceView.getHolder();
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mSurfaceHolder.addCallback(this);
        return mBinding.getRoot();
    }


    private final View.OnClickListener kOnTakePictureClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View inView) {
            mCamera.takePicture(null, null, mPictureCallback);
        }
    };

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void OkClicked() {
        Log.d(TAG, "OkClicked: OK");
    }

    @Override
    public void CancelClicked() {

    }

    @Override
    public void surfaceCreated(SurfaceHolder inSurfaceHolder) {
        try {
            mCamera = Camera.open();
        } catch (RuntimeException e) {
            AlertUtility alert = AlertUtility.NewInstance(e.getMessage());
            alert.show(MainActivity.GetFragmentManager(), "Alert");
        }

        Camera.Parameters param;
        param = mCamera.getParameters();
        param.setPreviewSize(400, 400);
        mCamera.setParameters(param);

        try {
            mCamera.setPreviewDisplay(mSurfaceHolder);
            mCamera.startPreview();
        } catch (Exception e) {
            AlertUtility alert = AlertUtility.NewInstance(e.getMessage());
            alert.show(MainActivity.GetFragmentManager(), "Alert");
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder inSurfaceHolder, int inI, int inI1, int inI2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder inSurfaceHolder) {
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }
}
