package com.superidea.view.planetactionbar.planetactionbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



import java.util.List;

import static android.support.design.widget.FloatingActionButton.SIZE_MINI;

/**
 * Created by henryhhuang on 2018/11/2.
 */

public class MenuAdapter extends BaseAdapter{

    private List<PlanetMenu> datas;
    private Context context;

    public MenuAdapter(Context context, List<PlanetMenu> datas){
        this.datas = datas;
        this.context = context;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        if(datas != null){
            return datas.size();
        }
        return 0;
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        if(datas != null && position < datas.size() && position >= 0){
            return datas.get(position);
        }
        return null;
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView == null){
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_submenu, null);
            vh.fab = convertView.findViewById(R.id.fab);
            vh.fab_text = convertView.findViewById(R.id.fab_text);
            convertView.setTag(vh);
            //fabconvertView = new FloatingActionButton(context);
            //ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(20, 20);
            //convertView.setLayoutParams(params);
            //convertView.setBackgroundColor(Color.BLUE);
        }
        final PlanetMenu data = (PlanetMenu) getItem(position);
        if(data != null){
            vh = (ViewHolder) convertView.getTag();
            if(vh != null) {
                FloatingActionButton fab = vh.fab;//((FloatingActionButton)convertView);
                if(fab != null) {
                    fab.setImageResource(data.imageResource);
                    fab.setSize(SIZE_MINI);
                    fab.setBackgroundTintList(ColorStateList.valueOf(data.backgroudColor));
                }
                if(vh.fab_text != null){
                    vh.fab_text.setText(data.text);
                }

            }
            //fab.setBackgroundColor(data.backgroudColor);
//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(data.listener != null){
//                        data.listener.onclick(v, position);
//                    }
//                }
//            });
        }
        return convertView;
    }

    class ViewHolder{
        FloatingActionButton fab;
        TextView fab_text;
    }
}
