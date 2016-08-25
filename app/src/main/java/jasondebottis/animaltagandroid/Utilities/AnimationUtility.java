package jasondebottis.animaltagandroid.Utilities;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import jasondebottis.animaltagandroid.BaseClasses.BaseTab;


public class AnimationUtility {
    public static void TakeTabOut(final BaseTab inSelectedTab) {
        if (inSelectedTab != null) {
            Animator moveLeft = ObjectAnimator.ofFloat(inSelectedTab, "translationX", -inSelectedTab.getWidth());
            Animator moveBack = ObjectAnimator.ofFloat(inSelectedTab, "translationX", 0);
            Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }


                @Override
                public void onAnimationEnd(Animator animation) {
                    inSelectedTab.setZ(4);
                    inSelectedTab.isCurrentlySelected = true;
                }


                @Override
                public void onAnimationCancel(Animator animation) {

                }


                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            };

            moveLeft.addListener(animatorListener);
            moveLeft.setDuration(300);
            moveBack.setDuration(300);


            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(moveLeft).before(moveBack);
            animatorSet.start();

        }


    }


    public static void ToggleTabs(final BaseTab inOldSelectedTab, final BaseTab inSelectedTab) {
        if (inOldSelectedTab != null) {
            Animator moveLeft = ObjectAnimator.ofFloat(inOldSelectedTab, "translationX", -inOldSelectedTab.getWidth());
            Animator moveBack = ObjectAnimator.ofFloat(inOldSelectedTab, "translationX", 0);
            Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }


                @Override
                public void onAnimationEnd(Animator animation) {
                    inOldSelectedTab.setZ(2);
                    inOldSelectedTab.isCurrentlySelected = false;
                    TakeTabOut(inSelectedTab);
                }


                @Override
                public void onAnimationCancel(Animator animation) {

                }


                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            };

            moveLeft.addListener(animatorListener);
            moveLeft.setDuration(300);
            moveBack.setDuration(300);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(moveLeft).before(moveBack);
            animatorSet.start();
        } else {
            TakeTabOut(inSelectedTab);
        }

    }

    public static void moveVewUp(View inView, Float inAmount) {
        ObjectAnimator up = ObjectAnimator.ofFloat(inView, "tranlationY", inAmount);
        up.setDuration(500);
        up.start();
    }

    public static void moveViewLeft(View inView, Float inAmount) {
        ObjectAnimator left = ObjectAnimator.ofFloat(inView, "translationX", inAmount);
        left.setDuration(500);
        left.start();
    }

}
