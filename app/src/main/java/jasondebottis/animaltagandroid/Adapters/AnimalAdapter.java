package jasondebottis.animaltagandroid.Adapters;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import jasondebottis.animaltagandroid.BaseClasses.BaseAdapter;
import jasondebottis.animaltagandroid.Models.AnimalModel;
import jasondebottis.animaltagandroid.R;
import jasondebottis.animaltagandroid.Views.AnimalViewHolder;
import jasondebottis.animaltagandroid.databinding.AnimalViewHolderBinding;


public class AnimalAdapter extends BaseAdapter<AnimalModel, AnimalViewHolder> {
    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup inParent, int inViewType) {
        LayoutInflater inflater = LayoutInflater.from(inParent.getContext());
        return new AnimalViewHolder((AnimalViewHolderBinding) DataBindingUtil.inflate(inflater,
                R.layout.animal_view_holder, inParent, false),
                inParent.getContext());
    }
}
