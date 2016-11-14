package jasondebottis.animaltagandroid.Fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import jasondebottis.animaltagandroid.Adapters.SavedAnimalsAdapter;
import jasondebottis.animaltagandroid.MainActivity;
import jasondebottis.animaltagandroid.Models.AnimalDataHolder;
import jasondebottis.animaltagandroid.R;
import jasondebottis.animaltagandroid.databinding.SavedAnimalsFragmentBinding;

import static com.google.android.gms.plus.PlusOneDummyView.TAG;


public class SavedAnimalsFragment extends Fragment {
    private SavedAnimalsFragmentBinding mBinding;
    List<AnimalDataHolder> mAnimals = new ArrayList<>();

    public SavedAnimalsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.saved_animals_fragment, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.toolBar);
        mBinding.toolBar.setTitle(getString(R.string.Favorites));
        mBinding.toolBar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mBinding.toolBar.setNavigationIcon(R.drawable.ic_keyboard_backspace);
        mBinding.toolBar.setNavigationOnClickListener(kGoBack);
        if (getAllAnimals() != null) {
            mAnimals = getAllAnimals();
        } else {
            Log.d(TAG, "onCreateView: no saved animals");
        }
        Log.d(TAG, "onCreateView: " + mAnimals.size());
        mBinding.savedAnimalsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.savedAnimalsRecyclerView.setAdapter(new SavedAnimalsAdapter());
        mBinding.savedAnimalsRecyclerView.GetAdapter().setDataItems(mAnimals);
        return mBinding.getRoot();
    }

    private List<AnimalDataHolder> getAllAnimals() {

        return new Select().from(AnimalDataHolder.class).execute();
    }

    private final View.OnClickListener kGoBack = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity.GoBack();
        }
    };

}
