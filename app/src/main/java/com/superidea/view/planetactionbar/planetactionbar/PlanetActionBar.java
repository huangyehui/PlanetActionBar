package com.superidea.view.planetactionbar.planetactionbar;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PointF;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;


import java.util.ArrayList;
import java.util.List;

//import com.tencent.faceidentify.R;

/**
 * Created by henryhhuang on 2018/11/1.
 */

public class PlanetActionBar extends RelativeLayout{

    private static final String TAG = PlanetActionBar.class.getSimpleName();

    private List<ValueAnimator> listAnimator = new ArrayList<>();
    private MenuAdapter adapter = null;
    private List<View> listView = new ArrayList<>();
    //private RelativeLayout container = null;
    public int width;
    public int height;
    public int viewW;
    public int viewH;
    private boolean isClicked = false;
   // private ColorStateList backgroudColor = Color.GREEN;
    private int icon = R.drawable.ic_launcher_background;
    private int nColor = 0;
    private int nIconRes = 0;
    private Dircetor direct = Dircetor.LEFT;
    int nColorRes = -1;
    private OnItemClickedListener listener;

    enum Dircetor{
        LEFT,
        TOP,
        RIGHT,
        BOTTOM
    }

    public interface OnItemClickedListener{
        void onItemClicked(int postion, View view);
    }


    public PlanetActionBar(Context context) {
        super(context);
        init(context);
    }

    public PlanetActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams(context, attrs);
        init(context);
    }

    public PlanetActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(context, attrs);
        init(context);
    }

    private void initParams(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PlanetActionBar);

        try {
            nColorRes = a.getResourceId(R.styleable.PlanetActionBar_backgroundColor, -1);
            if (nColorRes == -1) {
                nColor = a.getColor(R.styleable.PlanetActionBar_backgroundColor, 0);
            }
            nIconRes = a.getResourceId(R.styleable.PlanetActionBar_src, -1);
            int nDirect = a.getInt(R.styleable.PlanetActionBar_direct, -1);
            if (nDirect == 0) {
                direct = Dircetor.TOP;
            } else if (nDirect == 1) {
                direct = Dircetor.LEFT;
            } else if (nDirect == 2) {
                direct = Dircetor.BOTTOM;
            } else if (nDirect == 3) {
                direct = Dircetor.RIGHT;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        a.recycle();//回收typedArray
    }

    public void setAdatper(MenuAdapter adapter){
        this.adapter = adapter;
        listView.clear();
        listAnimator.clear();
        int count = adapter.getCount();
        for(int i = 0; i < count; i++){
            final int pos = i;
            final View view =  adapter.getView(i, null, null);
            view.setBackgroundColor(Color.TRANSPARENT);
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            view.setLayoutParams(lp);
            view.setVisibility(View.GONE);
            view.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onItemClicked(pos, view);
                    }
                }
            });
            listView.add(view);

            addView(view, 0);
        }
    }

    public void setOnItemClickListener(OnItemClickedListener listener){
        this.listener = listener;
    }

    private void initAnim(Dircetor direct){
        for(int i = 0; i < listView.size(); i++) {
            final int index = i;
            PointF start = new PointF(width / 2, height /2);//caleStart(width / 2, height /2);//new PointF(width/2 + viewW/6 * (i+1), height/2 + viewH/2 - viewH/6 * (i+1));
            float tempAngle = 0;
            if(direct == Dircetor.LEFT){
                tempAngle = -(float)Math.PI*4/3;
            }
            if(direct == Dircetor.RIGHT){
                tempAngle = (float)Math.PI*4/3;
            }
            if(direct == Dircetor.TOP){
                tempAngle = -(float)Math.PI;
            }

            float angle = (float)(i * Math.PI /3) + tempAngle;
            PointF end = calePostion(angle, start, width/2+viewW, height/2+viewW);
            //PointF end = new PointF(width -viewW, height /3-viewH*(index+1));
            ValueAnimator animator = ValueAnimator.ofObject(new CircleEvaluator(), start, end);
            Log.d(TAG, "view_" + i +" start="+start.toString());
            Log.d(TAG, "view_" + i +" end= "+end.toString());
            animator.setInterpolator(new AccelerateInterpolator());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                Log.d(TAG, "update:" + pointF.toString()+"by " + index);
                if(listView == null){
                    return;
                }
                View item = listView.get(index);
                //item.layout((int) pointF.x, (int) pointF.y, (int) (pointF.x + item.getWidth()), (int) (pointF.y + (i+1)*item.getHeight());
                item.setVisibility(View.VISIBLE);
                item.setX(pointF.x-viewW/3);
                item.setY(pointF.y-viewW/3);
                //item.layout((int) pointF.x, (int) pointF.y, (int) (pointF.x + tempW), (int) (pointF.y + tempH));

                }


            });
            animator.addListener(new ValueAnimator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    View item = listView.get(index);
                    if(!isClicked){
                        item.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.setDuration(300);
            listAnimator.add(animator);
        }
    }

    private PointF calePostion(float angle, PointF start, float x, float y){
        PointF end = new PointF(x, y);
        double r = Math.pow((Math.pow((end.x - start.x),2) + Math.pow((float)(end.y - start.y), 2)), 0.5);
        double outX = r * Math.cos(angle) + start.x;
        double outY = r * Math.sin(angle) + start.y;
        return new PointF((float)outX, (float)outY);

    }

    private PointF calePostion2(int index, float x, float y, int r){
        double t = Math.PI / 4;
        double orignal = -Math.PI/3;  //初始角度60
        double outX = r * Math.cos(orignal - t * index) + x;
        double outY = r * Math.sin(orignal - t * index) + y;
        return new PointF((float)outX, (float)outY);

    }




    private void init(Context context){
        View rootView = LayoutInflater.from(context).inflate(R.layout.solar_action_menu, null);
        final FloatingActionButton mainBtn = rootView.findViewById(R.id.main_btn);
        if(nIconRes != -1){
            mainBtn.setImageResource(nIconRes);
        }
        if(nColor != 0){
            mainBtn.setBackgroundTintList(ColorStateList.valueOf(nColor));
            //mainBtn.setBackgroundColor(nColor);
        }
        else if(nColorRes != -1){
            mainBtn.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(nColorRes)));
        }

        //ValueAnimator animator = ValueAnimator.ofObject(evaluator, new PointF(rand.nextInt(getWidth()), 0), new PointF(rand.nextInt(getWidth()), mHeight - dHeight));
        mainBtn.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {


            /**
             * Callback method to be invoked when the global layout state or the visibility of views
             * within the view tree changes
             */
            @Override
            public void onGlobalLayout() {
               // Log.d(TAG, "onGlobalLayout");
                width = getWidth();
                height = getHeight();
                Log.d(TAG, "width=" + width);
                Log.d(TAG, "height=" + height);
                viewW = mainBtn.getWidth();
                viewH = mainBtn.getHeight();
                int centerX = width / 2;
                int centerY = height / 2;
                Log.d(TAG, "view width=" + viewW);
                Log.d(TAG, "view height=" + viewH);
                initAnim(direct);
            }
        });
        mainBtn.setOnClickListener(new OnClickListener(){
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                if(!isClicked) {
                    for (ValueAnimator item : listAnimator) {
                        item.start();
                    }
                    isClicked = true;
                }
                else{
                    for (ValueAnimator item : listAnimator) {
                        item.reverse();
                    }
                    isClicked = false;
                }
            }
        });

        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        rootView.setLayoutParams(lp);
        addView(rootView);
    }
}
