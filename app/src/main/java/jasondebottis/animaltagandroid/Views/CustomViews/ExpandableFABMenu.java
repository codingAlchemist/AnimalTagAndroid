package jasondebottis.animaltagandroid.Views.CustomViews;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import jasondebottis.animaltagandroid.Fragments.TabFragment;
import jasondebottis.animaltagandroid.MainActivity;
import jasondebottis.animaltagandroid.R;
import jasondebottis.animaltagandroid.Utilities.NavUtility;
import jasondebottis.animaltagandroid.databinding.FabExpandableMenuBinding;


public class ExpandableFABMenu extends RelativeLayout implements FABMenuItem.OnClickFabItemListener {

    private FabExpandableMenuBinding mBinding;
    public static final float kTranslationAmount = 210f;
    private boolean menuOpen = false;

    public ExpandableFABMenu(Context context) {
        super(context);
        init(context);
    }

    public ExpandableFABMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context inContext) {
        LayoutInflater inflater = (LayoutInflater) inContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fab_expandable_menu, this, true);
        mBinding.fabMenuController.setOnClickListener(kOnControllerClick);
        mBinding.fabMenuItem1.setOnClickFabItemListener(this);
        mBinding.fabMenuItem2.setOnClickFabItemListener(this);
        mBinding.fabMenuItem3.setOnClickFabItemListener(this);
    }

    private final View.OnClickListener kOnControllerClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TabFragment.TAG, "onClick: ");
            if (menuOpen) {
                collapseMenu();
            } else {
                expandMenu();
            }
        }
    };


    private void expandMenu() {
        menuOpen = true;
        ObjectAnimator controllerRotate = ObjectAnimator.ofFloat(mBinding.fabMenuController, "rotation", -45f);
        ObjectAnimator item1Up = ObjectAnimator.ofFloat(mBinding.fabMenuItem1, "translationY", -kTranslationAmount);
        ObjectAnimator item2Up = ObjectAnimator.ofFloat(mBinding.fabMenuItem2, "translationY", -kTranslationAmount);
        ObjectAnimator item2Left = ObjectAnimator.ofFloat(mBinding.fabMenuItem2, "translationX", -kTranslationAmount);
        ObjectAnimator item3Left = ObjectAnimator.ofFloat(mBinding.fabMenuItem3, "translationX", -kTranslationAmount);
        AnimatorSet menuAnimSet = new AnimatorSet();
        menuAnimSet.playTogether(item1Up, item2Up, item2Left, item3Left, controllerRotate);
        menuAnimSet.setDuration(50);
        menuAnimSet.start();
    }

    private void collapseMenu() {
        menuOpen = false;
        ObjectAnimator controllerRotate = ObjectAnimator.ofFloat(mBinding.fabMenuController, "rotation", 0f);
        ObjectAnimator item1Up = ObjectAnimator.ofFloat(mBinding.fabMenuItem1, "translationY", 0);
        ObjectAnimator item2Up = ObjectAnimator.ofFloat(mBinding.fabMenuItem2, "translationY", 0);
        ObjectAnimator item2Left = ObjectAnimator.ofFloat(mBinding.fabMenuItem2, "translationX", 0);
        ObjectAnimator item3Left = ObjectAnimator.ofFloat(mBinding.fabMenuItem3, "translationX", 0);
        AnimatorSet menuAnimSet = new AnimatorSet();
        menuAnimSet.playTogether(item1Up, item2Up, item2Left, item3Left, controllerRotate);
        menuAnimSet.setDuration(50);
        menuAnimSet.start();

    }


    @Override
    public void didClickItem(String tag) {
        
        if (tag.contentEquals("item1")) {
            MainActivity.NavigateTo(NavUtility.DestinationFragment.kTabFragment, null);
        } else if (tag.contentEquals("item2")) {
            MainActivity.NavigateTo(NavUtility.DestinationFragment.kFavoriteAnimals, null);
        } else if (tag.contentEquals("item3")) {
            MainActivity.NavigateTo(NavUtility.DestinationFragment.kTakeAnimalPictureFragment, null);
        }

    }
}
