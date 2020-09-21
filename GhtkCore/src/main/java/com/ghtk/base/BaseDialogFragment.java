package com.ghtk.base;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.ghtk.base.log.Logger;
import com.ghtk.common.R;
import com.ghtk.utils.ActivityUtils;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

/**
 * Base Fragment
 * Created by neo on 3/22/2016.
 */
public abstract class BaseDialogFragment extends DialogFragment {
    private WindowManager manager;
    private DisplayMetrics metrics;
    private static final boolean DEFAULT_START_ON_ANIMATION_ENDED = false;

    protected View mRootView;

    // Animation enter/exit
    protected int mAnimIn = CoreDefault.ANIM_NONE;
    protected int mAnimOut = CoreDefault.ANIM_NONE;

    /**
     * This field decide the Fragment will be started fetching data after Fragment transaction
     * animation ended
     * Otherwise, it start {@code onActivityCreated}
     */
    protected boolean mStartOnAnimationEnded = DEFAULT_START_ON_ANIMATION_ENDED;

    protected boolean mIsStarted = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//    setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        this.getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        this.getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        metrics = new DisplayMetrics();

        manager = getActivity().getWindowManager();

        setupWindowDialog();
        // Inject views
        ButterKnife.setDebug(true);
        ButterKnife.bind(this, mRootView);

        mRootView.setClickable(true);
        return mRootView;
    }

    public void addChildFragment(Fragment fragment, int frameId, boolean addToBackStack,
                                 String tag) {
        ActivityUtils.addChildFragment(getChildFragmentManager(), fragment,
                frameId, addToBackStack, tag);
    }

    /**
     * Set out animation
     */
    public BaseDialogFragment setAnimOut(int animOut) {
        mAnimOut = animOut;
        return this;
    }

    /**
     * Set enter animation
     */
    public BaseDialogFragment setAnimIn(int animIn) {
        mAnimIn = animIn;
        return this;
    }

    /**
     * This method decide the Fragment will be started fetching data after Fragment transaction
     * animation ended
     * Otherwise, it start {@code onActivityCreated}
     */
    protected BaseDialogFragment setStartOnAnimationEnded(boolean startOnAnimationEnded) {
        mStartOnAnimationEnded = startOnAnimationEnded;
        return this;
    }

    /**
     * Return layout resource id for activity
     */
    protected abstract int getLayoutId();

    /**
     * Start present fragment
     */
    protected abstract void startPresent();

    // Arbitrary value; set it to some reasonable default
//    private static final int DEFAULT_CHILD_ANIMATION_DURATION = 250;
//
//    @Override
//    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
//        final Fragment parent = getParentFragment();
//
//        // Apply the workaround only if this is a child fragment, and the parent
//        // is being removed.
//        if (!enter && parent != null && parent.isRemoving()) {
//            // This is a workaround for the bug where child fragments disappear when
//            // the parent is removed (as all children are first removed from the parent)
//            // See https://code.google.com/p/android/issues/detail?id=55228
//            Animation doNothingAnim = new AlphaAnimation(1, 1);
//            doNothingAnim.setDuration(getNextAnimationDuration(parent,
// DEFAULT_CHILD_ANIMATION_DURATION));
//            return doNothingAnim;
//        } else {
//            return super.onCreateAnimation(transit, enter, nextAnim);
//        }
//    }

    //    @Override
//    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
//        return enter ? AnimationUtils.loadAnimation(getActivity(), R.anim.slide_right_in) :
// AnimationUtils.loadAnimation(getActivity(), R.anim.slide_right_out);
//    }
    private static long getNextAnimationDuration(Fragment fragment, long defValue) {
        try {
            // Attempt to get the resource ID of the next animation that
            // will be applied to the given fragment.
            Field nextAnimField = Fragment.class.getDeclaredField("mNextAnim");
            nextAnimField.setAccessible(true);
            int nextAnimResource = nextAnimField.getInt(fragment);
            Animation nextAnim = AnimationUtils.loadAnimation(fragment.getActivity(), nextAnimResource);

            // ...and if it can be loaded, return that animation's duration
            return (nextAnim == null) ? defValue : nextAnim.getDuration();
        } catch (NoSuchFieldException | IllegalAccessException | Resources.NotFoundException ex) {
            Logger.w("Unable to load next animation from parent." + ex.getMessage());
            return defValue;
        }
    }

    protected abstract boolean needTranslationAnimation();

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (!needTranslationAnimation()) {
            return null;
        }

        Animation anim;
        if (enter) {
            anim = AnimationUtils.loadAnimation(getActivity(), mAnimIn);
        } else {
            anim = AnimationUtils.loadAnimation(getActivity(), mAnimOut);
//      anim.setDuration(getContext().getResources().getInteger(R.integer.anim_duration));
        }

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                if (mRootView != null) {
                    mRootView.setLayerType(View.LAYER_TYPE_NONE, null);
                }

                if (mStartOnAnimationEnded && !mIsStarted) {
                    startPresent();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {

            }
        });

        // Apply the workaround only if this is a child fragment, and the parent
        // is being removed.
        final Fragment parent = getParentFragment();
        if (!enter && parent != null && parent.isRemoving()) {
            // This is a workaround for the bug where child fragments disappear when
            // the parent is removed (as all children are first removed from the parent)
            // See https://code.google.com/p/android/issues/detail?id=55228
            Animation doNothingAnim = new AlphaAnimation(1, 1);
            doNothingAnim.setDuration(getNextAnimationDuration(parent, getContext().getResources()
                    .getInteger(R.integer.anim_duration)));
            return doNothingAnim;
        }
        return anim;
    }

    //value between 0 and 1
    protected abstract float setWidth();

    //value between 0 and 1
    protected abstract float setHeight();

    private void setupWindowDialog() {
        if (manager != null) {
            manager.getDefaultDisplay().getMetrics(metrics);
            float with;
            float height;

            if (setWidth() > 0 && setWidth() <= 1) {
                with = metrics.widthPixels * setWidth();
                mRootView.setMinimumWidth((int) with);
            }

            if (setHeight() > 0 && setHeight() <= 1) {
                height = metrics.heightPixels * setHeight();
                mRootView.setMinimumHeight((int) height);
            }

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        if (manager != null) {
            manager.getDefaultDisplay().getMetrics(metrics);
            float with;
            float height;

            if (setWidth() > 0 && setWidth() < 1 && setHeight() > 0 && setHeight() < 1) {
                with = metrics.widthPixels * setWidth();
                height = metrics.heightPixels * setHeight();
                window.setLayout((int) with, (int) height);
            } else if (setHeight() == 1 && setWidth() == 1) {
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            } else if (setWidth() > 0 && setWidth() < 1) {
                with = metrics.widthPixels * setWidth();
                window.setLayout((int) with, ViewGroup.LayoutParams.WRAP_CONTENT);
            } else if (setHeight() > 0 && setHeight() < 1) {
                height = metrics.heightPixels * setHeight();
                window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, (int) height);
            } else {
                window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            window.setGravity(Gravity.CENTER);
        }

    }

    public void showDialogFragment(BaseDialogFragment dialogFragment) {
        dialogFragment.show(getFragmentManager(), dialogFragment.getClass().toString());
    }

    public void runActivity(Class clazz) {
        Intent intent = new Intent(getContext(), clazz);
        startActivity(intent);
    }
    KProgressHUD mProgressHUD = null;
    public void showProgressDialog(boolean show) {
        if (show) {
            if (mProgressHUD != null) {
                mProgressHUD.dismiss();
            }
            mProgressHUD = KProgressHUD.create(getContext())
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                .setLabel("Please wait")
//                .setDetailsLabel("Downloading data")
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .show();
        } else {
            if (mProgressHUD != null) {
                mProgressHUD.dismiss();
            }
        }
    }
}
