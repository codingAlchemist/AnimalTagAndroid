package jasondebottis.animaltagandroid.BaseClasses;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;



public abstract class BaseAdapter<TData extends BaseData, TViewHolder extends BaseViewHolder> extends RecyclerView.Adapter<TViewHolder>
{

    private List<TData> mDataItems = new ArrayList<>();



    public void setDataItems(List<TData> inDataItems)
    {
        mDataItems = inDataItems;
        notifyDataSetChanged();
    }



    @Override
    public void onBindViewHolder(TViewHolder inHolder, int inPosition)
    {
        if (inPosition < mDataItems.size())
        {
            TData data = mDataItems.get(inPosition);
            inHolder.SetData(data);
        }
    }



    @Override
    public int getItemCount()
    {
        if (mDataItems != null)
        {
            return mDataItems.size();
        }
        return 0;
    }
}
