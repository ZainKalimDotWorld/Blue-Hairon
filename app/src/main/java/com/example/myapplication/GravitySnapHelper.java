/*
 * Copyright (C) 2016 The Android Open Source Project
 * Copyright (C) 2016 Rúben Sousa
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
 * See the License for the specific languag`e governing permissions and
 * limitations under the License.
 */
package com.example.myapplication;

import android.annotation.SuppressLint;

import android.view.Gravity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;


public class GravitySnapHelper extends LinearSnapHelper {

    private OrientationHelper verticalHelper;
    private OrientationHelper horizontalHelper;
    private int gravity;
    private boolean isSupportRtL;

    @SuppressLint("RtlHardcoded")
    public GravitySnapHelper(int gravity) {
        this.gravity = gravity;
        if (this.gravity == Gravity.LEFT) {
            this.gravity = Gravity.START;
        } else if (this.gravity == Gravity.RIGHT) {
            this.gravity = Gravity.END;
        }
    }

    @Override
    public void attachToRecyclerView(@Nullable RecyclerView recyclerView)
            throws IllegalStateException {
        if (recyclerView != null) {
            isSupportRtL = recyclerView.getContext().getResources().getBoolean(R.bool.is_rtl);
        }
        super.attachToRecyclerView(recyclerView);
    }

    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager,
                                              @NonNull View targetView) {
        int[] out = new int[2];

        if (layoutManager.canScrollHorizontally()) {
            if (gravity == Gravity.START) {
                out[0] = distanceToStart(targetView, getHorizontalHelper(layoutManager));
            } else { // END
                out[0] = distanceToEnd(targetView, getHorizontalHelper(layoutManager));
            }
        } else {
            out[0] = 0;
        }

        if (layoutManager.canScrollVertically()) {
            if (gravity == Gravity.TOP) {
                out[1] = distanceToStart(targetView, getVerticalHelper(layoutManager));
            } else { // BOTTOM
                out[1] = distanceToEnd(targetView, getVerticalHelper(layoutManager));
            }
        } else {
            out[1] = 0;
        }
        return out;
    }

    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            switch (gravity) {
                case Gravity.START:
                    return findStartView(layoutManager, getHorizontalHelper(layoutManager));
                case Gravity.TOP:
                    return findStartView(layoutManager, getVerticalHelper(layoutManager));
                case Gravity.END:
                    return findEndView(layoutManager, getHorizontalHelper(layoutManager));
                case Gravity.BOTTOM:
                    return findEndView(layoutManager, getVerticalHelper(layoutManager));
            }
        }

        return super.findSnapView(layoutManager);
    }

    private int distanceToStart(View targetView, OrientationHelper helper) {
        if (isSupportRtL) {
            return distanceToEnd(targetView, helper);
        }
        return helper.getDecoratedStart(targetView) - helper.getStartAfterPadding();
    }

    private int distanceToEnd(View targetView, OrientationHelper helper) {
        if (isSupportRtL) {
            return helper.getDecoratedStart(targetView) - helper.getStartAfterPadding();
        }
        return helper.getDecoratedEnd(targetView) - helper.getEndAfterPadding();
    }

    private View findStartView(RecyclerView.LayoutManager layoutManager,
                               OrientationHelper helper) {

        if (layoutManager instanceof LinearLayoutManager) {
            int firstChild = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();

            if (firstChild == RecyclerView.NO_POSITION) {
                return null;
            }

            View child = layoutManager.findViewByPosition(firstChild);

            if (helper.getDecoratedEnd(child) >= helper.getDecoratedMeasurement(child) / 2
                    && helper.getDecoratedEnd(child) > 0) {
                return child;
            } else {
                if (((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition()
                        == layoutManager.getItemCount() - 1) {
                    return null;
                } else {
                    return layoutManager.findViewByPosition(firstChild + 1);
                }
            }
        }

        return super.findSnapView(layoutManager);
    }

    private View findEndView(RecyclerView.LayoutManager layoutManager,
                             OrientationHelper helper) {

        if (layoutManager instanceof LinearLayoutManager) {
            int lastChild = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();

            if (lastChild == RecyclerView.NO_POSITION) {
                return null;
            }

            View child = layoutManager.findViewByPosition(lastChild);

            if (helper.getDecoratedStart(child) + helper.getDecoratedMeasurement(child) / 2
                    <= helper.getTotalSpace()) {
                return child;
            } else {
                if (((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition()
                        == 0) {
                    return null;
                } else {
                    return layoutManager.findViewByPosition(lastChild - 1);
                }
            }
        }

        return super.findSnapView(layoutManager);
    }

    private OrientationHelper getVerticalHelper(RecyclerView.LayoutManager layoutManager) {
        if (verticalHelper == null) {
            verticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return verticalHelper;
    }

    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        if (horizontalHelper == null) {
            horizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return horizontalHelper;
    }

}
