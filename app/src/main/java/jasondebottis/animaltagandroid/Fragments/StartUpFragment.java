package jasondebottis.animaltagandroid.Fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jasondebottis.animaltagandroid.R;
import jasondebottis.animaltagandroid.databinding.StartUpFragmentBinding;

public class StartUpFragment extends Fragment {
    private StartUpFragmentBinding mBinding;

    public static StartUpFragment newInstance() {
        StartUpFragment startUpFragment = null;
        if (startUpFragment == null) {
            startUpFragment = new StartUpFragment();
        }
        return startUpFragment;
    }

    public StartUpFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.start_up_fragment, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.toolBar);
        mBinding.toolBar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mBinding.toolBar.setTitle(getString(R.string.appTitle));

        return mBinding.getRoot();
    }


}
