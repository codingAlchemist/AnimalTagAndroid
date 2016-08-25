package jasondebottis.animaltagandroid.Views;

import jasondebottis.animaltagandroid.BaseClasses.BaseData;
import jasondebottis.animaltagandroid.BaseClasses.BaseViewHolder;
import jasondebottis.animaltagandroid.Models.AnimalModel;
import jasondebottis.animaltagandroid.databinding.SavedAnimalsViewholderBinding;

public class SavedAnimalsViewHolder extends BaseViewHolder<SavedAnimalsViewholderBinding> {

    public SavedAnimalsViewHolder(SavedAnimalsViewholderBinding inBinding) {
        super(inBinding);
    }

    @Override
    public void SetData(BaseData... inData) {
        for (BaseData data : inData) {
            if (data instanceof AnimalModel) {
                mBinding.setAnimalModel((AnimalModel) data);
            }
        }
    }
}
