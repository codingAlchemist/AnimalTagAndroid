package jasondebottis.animaltagandroid.Adapters;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import jasondebottis.animaltagandroid.BaseClasses.BaseAdapter;
import jasondebottis.animaltagandroid.Models.AnimalModel;
import jasondebottis.animaltagandroid.R;
import jasondebottis.animaltagandroid.Views.SavedAnimalsViewHolder;
import jasondebottis.animaltagandroid.databinding.SavedAnimalsViewholderBinding;

public class SavedAnimalsAdapter extends BaseAdapter<AnimalModel, SavedAnimalsViewHolder> {

    @Override
    public SavedAnimalsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SavedAnimalsViewHolder((SavedAnimalsViewholderBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.saved_animals_viewholder, parent, false));
    }
}
