package jasondebottis.animaltagandroid.BaseClasses;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;



public class BaseRecyclerView<TAdapter extends BaseAdapter> extends RecyclerView
{
    public BaseRecyclerView(Context inContext)
    {
        super(inContext);
    }



    public BaseRecyclerView(Context inContext, AttributeSet inAttributeSet)
    {
        super(inContext, inAttributeSet);
        setLayoutManager(new LinearLayoutManager(inContext));
    }



    public TAdapter GetAdapter()
    {
        return (TAdapter) getAdapter();
    }
}
