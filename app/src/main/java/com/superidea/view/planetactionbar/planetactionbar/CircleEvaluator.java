package com.superidea.view.planetactionbar.planetactionbar;

import android.animation.TypeEvaluator;
import android.graphics.PointF;
import android.util.Log;

/**
 * Created by henryhhuang on 2018/11/1.
 */

public class CircleEvaluator implements TypeEvaluator<PointF> {

    private static final String TAG = CircleEvaluator.class.getSimpleName();
   // private int factor;


    public CircleEvaluator(){
        //this.factor = factor;
    }

    //直线方程
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        Log.d(TAG, "s=" + startValue.toString());
        Log.d(TAG, "e=" + endValue.toString());
        //Y);
        float k = (endValue.y - startValue.y)/(endValue.x-startValue.x);
        float b = endValue.y - k*endValue.x;
        float x =fraction * (endValue.x - startValue.x)+startValue.x;
        float y = k*x+b;
        return new PointF(x, y);
    }

   //圆形轨迹
//    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
//         Log.d(TAG, "s=" + startValue.toString());
//         Log.d(TAG, "e=" + endValue.toString());
//        double r = Math.pow((Math.pow((float)(endValue.x - startValue.x),2) + Math.pow((float)(endValue.y - startValue.y), 2)), 0.5);
//        float x =fraction * (endValue.x - startValue.x);
//        //float r = endValue.x - startValue.x;
//        if(r != 0) {
//            double angle = Math.acos(x / r);
//            Log.d(TAG, "angle=" + angle/(Math.PI/180));
//            float y = (float) (Math.sin(angle) * r);
//            //移动圆心
//            float tempX = (float) (startValue.x + x);
//            float tempY = (float) (startValue.y + y - r);
//            // 原始坐标
//            //float tempX = (float) (startValue.x + x);
//            //float tempY = (float) (startValue.y + y);
//            Log.d(TAG, "x=" + tempX);
//            Log.d(TAG, "y=" + tempY);
//            return new PointF(tempX, tempY);
//
//        }
//        return new PointF(0, 0);
//    }
}
