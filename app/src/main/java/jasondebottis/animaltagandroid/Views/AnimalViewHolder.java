package jasondebottis.animaltagandroid.Views;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import jasondebottis.animaltagandroid.BaseClasses.BaseData;
import jasondebottis.animaltagandroid.BaseClasses.BaseViewHolder;
import jasondebottis.animaltagandroid.BuildConfig;
import jasondebottis.animaltagandroid.Fragments.AnimalDetailFragment;
import jasondebottis.animaltagandroid.MainActivity;
import jasondebottis.animaltagandroid.Models.AnimalModel;
import jasondebottis.animaltagandroid.Utilities.GeneralUtility;
import jasondebottis.animaltagandroid.Utilities.ImageUtiliy;
import jasondebottis.animaltagandroid.Utilities.NavUtility;
import jasondebottis.animaltagandroid.databinding.AnimalViewHolderBinding;


public class AnimalViewHolder extends BaseViewHolder<AnimalViewHolderBinding> implements View.OnClickListener {

    private Context mContext;
    private AnimalModel mAnimalModel;

    public AnimalViewHolder(AnimalViewHolderBinding inBinding, Context inContext) {
        super(inBinding);
        mContext = inContext;
        inBinding.getRoot().setOnClickListener(this);
    }


    @Override
    public void SetData(BaseData... inData) {
        for (BaseData data : inData) {

            if (data instanceof AnimalModel) {
                mAnimalModel = (AnimalModel) data;
                mBinding.setAnimalModel(mAnimalModel);

                if (!GeneralUtility.IsNullOrEmpty(((AnimalModel) data).mImage1)) {
                    ImageUtiliy.LoadImage(BuildConfig.baseUrl + "app/public/" + ((AnimalModel) data).mImage1, mBinding.animalImageView);
                }

            }
        }
    }

    @Override
    public void onClick(View inView) {
        Bundle args = new Bundle();
        args.putParcelable(AnimalDetailFragment.kAnimal, mAnimalModel);
        MainActivity.NavigateTo(NavUtility.DestinationFragment.kAnimalDetailFragment, args);
    }
}
