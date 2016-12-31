package com.rohmanhakim.onboarding;

import android.content.Context;

/**
 * Created by rohmanhakim <rohmanhakim@live.com> on 12/31/16 23:32.
 */
public class Helper {
    public static int convertDensityToPixel(Context context, float value){
        return (int) (value * context.getResources().getDisplayMetrics().density + 0.5f ) ;
    }
}
