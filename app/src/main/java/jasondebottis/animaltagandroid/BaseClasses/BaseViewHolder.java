package jasondebottis.animaltagandroid.BaseClasses;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;



public abstract class BaseViewHolder<TBinding extends ViewDataBinding> extends RecyclerView.ViewHolder
{
    protected TBinding mBinding;



    public abstract void SetData(BaseData... inData);



    public BaseViewHolder(TBinding inBinding)
    {
        super(inBinding.getRoot());
        mBinding = inBinding;
    }


}
