/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ghtk.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ghtk.base.BaseDialogFragment;
import com.ghtk.base.CoreDefault;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This provides methods to help Activities load their UI.
 */
public class ActivityUtils {

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation
     * is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {

        addFragmentToActivity(fragmentManager, fragment, frameId, false, null);
    }

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation
     * is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId, boolean
                                                     addToBackStack, String tag) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
//    transaction.setCustomAnimations(
//        R.anim.screen_enter,
//        R.anim.screen_exit,
//        R.anim.slide_none,
//        R.anim.screen_pop_exit);

        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation
     * is
     * performed by the {@code fragmentManager}.
     */
    public static void addChildFragment(@NonNull FragmentManager fragmentManager,
                                        @NonNull Fragment fragment, int frameId,
                                        boolean addToBackStack, String tag) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
//    transaction.setCustomAnimations(R.anim.screen_enter,
//        R.anim.screen_exit, R.anim.slide_none,
//        R.anim.screen_pop_exit);

        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }
        if (fragment instanceof BaseDialogFragment) {
            transaction.add(fragment, fragment.toString());
        } else {
            transaction.add(frameId, fragment);
        }
        transaction.commit();
    }

    public static void replaceChildFragment(@NonNull FragmentManager fragmentManager,
                                        @NonNull Fragment fragment, int frameId,
                                        boolean addToBackStack, String tag) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
//    transaction.setCustomAnimations(R.anim.screen_enter,
//        R.anim.screen_exit, R.anim.slide_none,
//        R.anim.screen_pop_exit);


        transaction.replace(frameId, fragment);
        transaction.commit();
    }

    public static void addChildFragment(FragmentManager fragmentManager, Fragment fragment,
                                        int rootFrameId, boolean addToBackstack) {
        addChildFragment(fragmentManager, fragment, rootFrameId, addToBackstack,
                fragment.getClass().getSimpleName());
    }

    /**
     * Start activity
     */
    public static <T extends Activity> void startActivity(Context context, Class<T> clazz) {
        context.startActivity(new Intent(context, clazz));
    }

    /**
     * Start activity for result
     */
    public static <T extends Activity> void startActivityForResult(Activity activity, Class<T> clazz, int requestCode) {
        activity.startActivityForResult(new Intent(activity, clazz), requestCode);
    }

    /**
     * Start activity with extras
     */
    public static <T extends Activity> void startActivity(Context context, Class<T> clazz,
                                                          Bundle extras) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(extras);
        context.startActivity(intent);
    }

    /**
     * Start activity with extras for result
     */
    public static <T extends Activity> void startActivityForResult(Activity activity, Class<T> clazz,
                                                                   Bundle extras, int requestCode) {
        Intent intent = new Intent(activity, clazz);
        intent.putExtras(extras);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * Start activity with extras
     */
    public static <T extends Activity> void startActivity(Context context, Class<T> clazz,
                                                          Bundle extras, boolean withAnim) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(extras);
        context.startActivity(intent);

        if (context instanceof Activity && withAnim) {
            ((Activity) context).overridePendingTransition(CoreDefault.ANIM_IN,
                    CoreDefault.ANIM_OUT);
        }
    }

    public static <T extends Activity> void startActivitySingle(Context context, Class<T> clazz,
                                                                Bundle extras, boolean withAnim) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(extras);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);

        if (context instanceof Activity && withAnim) {
            ((Activity) context).overridePendingTransition(CoreDefault.ANIM_IN,
                    CoreDefault.ANIM_OUT);
        }
    }
}
