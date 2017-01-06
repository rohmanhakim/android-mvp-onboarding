package com.rohmanhakim.onboardingview;

import android.content.Context;

/**
 * Created by rohmanhakim <rohmanhakim@live.com> on 1/6/17 17:24.
 */
public class Helper {
    public static int convertDensityToPixel(Context context, float value){
        return (int) (value * context.getResources().getDisplayMetrics().density + 0.5f ) ;
    }
}
