package com.superidea.view.planetactionbar.planetactionbar;

import android.view.View;

/**
 * Created by henryhhuang on 2018/11/2.
 */

public class PlanetMenu {

    public interface OnMenuSelectListener{
        void onclick(View view, int position);
    }

    public String text;
    public int imageResource;
    public int backgroudColor;
    public OnMenuSelectListener listener;
}
