package jasondebottis.animaltagandroid.Views.TabViews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import jasondebottis.animaltagandroid.BaseClasses.BaseTab;
import jasondebottis.animaltagandroid.R;


public class InsectTab extends BaseTab {

    public InsectTab(Context context) {
        super(context);
        setWillNotDraw(false);
    }


    public InsectTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        Paint paint = new Paint();
        Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.insect_tab_background);
        canvas.drawBitmap(background, 0, 0, paint);

        super.dispatchDraw(canvas);
    }
}
