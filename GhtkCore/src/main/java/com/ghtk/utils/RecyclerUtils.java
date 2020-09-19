package com.ghtk.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Collection Utils
 * Created by neo on 7/20/2016.
 */
public class RecyclerUtils {

  public static void setupVerticalRecyclerView(Context context, RecyclerView mRecyclerView) {
    LinearLayoutManager layoutManager = new LinearLayoutManager(context);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setClipToPadding(false);
    mRecyclerView.setHasFixedSize(true);
  }

  public static void setupVerticalRecyclerViewWithStackFromEnd(Context context, RecyclerView mRecyclerView) {
    LinearLayoutManager layoutManager = new LinearLayoutManager(context);
    layoutManager.setReverseLayout(true);
    layoutManager.setStackFromEnd(true);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setClipToPadding(false);
    mRecyclerView.setHasFixedSize(true);
  }

  public static void setupHorizontalRecyclerView(Context context, RecyclerView mRecyclerView) {
    LinearLayoutManager layoutManager = new GridLayoutManager(context, 1,
            GridLayoutManager.HORIZONTAL, false);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setClipToPadding(false);
    mRecyclerView.setHasFixedSize(true);
  }

  public  static void setupHorizontalRecyclerViewWith2Columns(Context context,RecyclerView mRecyclerView){
    LinearLayoutManager layoutManager = new GridLayoutManager(context, 2,
            GridLayoutManager.HORIZONTAL, false);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setClipToPadding(false);
    mRecyclerView.setHasFixedSize(true);
  }

  public static void setupHorizontalRecyclerViewForProducts(Context context, RecyclerView mRecyclerView) {
    LinearLayoutManager layoutManager = new GridLayoutManager(context, 1,
            GridLayoutManager.HORIZONTAL, false);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setClipToPadding(false);
    mRecyclerView.setHasFixedSize(true);
  }

  public static void setupStaggeredVerticalRecyclerView(RecyclerView mRecyclerView, int spanCount) {
    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
    mRecyclerView.setClipToPadding(false);
    mRecyclerView.setHasFixedSize(true);
  }

  public static void setupHorizontalRecyclerViewWithoutSpan(Context context, RecyclerView mRecyclerView) {
    LinearLayoutManager layoutManager = new LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setClipToPadding(false);
    mRecyclerView.setHasFixedSize(true);
  }

  /**
   * Common Setup for grid recycler view
   */
  public static void setupGridRecyclerView(Context context, RecyclerView mRecyclerView, int spanCount) {
    LinearLayoutManager layoutManager = new GridLayoutManager(context, spanCount);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setClipToPadding(false);
    mRecyclerView.setHasFixedSize(true);
  }


  public static void setupGridRecyclerViewWithStackFromEnd(Context context, RecyclerView mRecyclerView, int spanCount) {


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && spanCount > 2) {
      LinearLayoutManager layoutManager = new GridLayoutManager(context, 3);
      mRecyclerView.setLayoutManager(layoutManager);
      mRecyclerView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }else {
      LinearLayoutManager layoutManager =  new GridLayoutManager(context, 1,
              GridLayoutManager.HORIZONTAL, false);
      mRecyclerView.setLayoutManager(layoutManager);
      //mRecyclerView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    }
    mRecyclerView.setClipToPadding(false);
    mRecyclerView.setHasFixedSize(true);
  }
}
