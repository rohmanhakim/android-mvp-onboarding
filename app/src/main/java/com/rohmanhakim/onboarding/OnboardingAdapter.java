package com.rohmanhakim.onboarding;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by rohmanhakim <rohmanhakim@live.com> on 12/31/16 21:31 21:39.
 */
public class OnboardingAdapter extends PagerAdapter{

    int itemCount;
    String[] titles;
    String[] messages;
    ViewGroup paginationCircles;

    public OnboardingAdapter(String[] titles, String[] messages, ViewGroup paginationCircles){
        super();
        this.titles = titles;
        this.messages = messages;
        this.paginationCircles = paginationCircles;
        this.itemCount = titles.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(container.getContext(), R.layout.layout_onboarding_items, null);
        TextView textItemMessage = (TextView) view.findViewById(R.id.text_item_message);
        TextView textItemTitle = (TextView) view.findViewById(R.id.text_item_title);
        container.addView(view,0);

        textItemTitle.setText(titles[position]);
        textItemMessage.setText(messages[position]);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        int count = paginationCircles.getChildCount();
        for (int a = 0; a < count; a++) {
            View child = paginationCircles.getChildAt(a);
            if (a == position) {
                child.setBackgroundResource(R.drawable.white_circle);
            } else {
                child.setBackgroundResource(R.drawable.light_sky_blue_circle);
            }
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public int getCount() {
        return this.itemCount;
    }
}
