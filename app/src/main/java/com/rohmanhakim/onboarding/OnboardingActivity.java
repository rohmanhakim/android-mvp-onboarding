package com.rohmanhakim.onboarding;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class OnboardingActivity extends AppCompatActivity {

    int[] icons;
    String[] titles;
    String[] messages;

    int lastPage = 0;

    ViewPager viewPager;
    ImageView imgIcon;
    ImageView imgIconNext;
    ViewGroup paginationCircles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        initStatusBarColor();

        icons = new int[]{
                R.drawable.onboarding_icon_1,
                R.drawable.onboarding_icon_2,
                R.drawable.onboarding_icon_3
        };

        titles = new String[]{
                "EASY ACCESSIBLE",
                "SECURE AND TRUSTED",
                "FAST AND RELIABLE"
        };

        messages = new String[]{
                "All You need is on Your phone. We trying to give You the best User Experience",
                "We handle Your money as ours. Every pieces is counted",
                "No one like to wait, so do us. We make this apps fastest as we can"
        };

        initViewPager();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initStatusBarColor() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.lightSkyBlue));
    }

    private void initViewPager(){
        viewPager = (ViewPager) findViewById(R.id.vp_item);
        imgIcon = (ImageView) findViewById(R.id.img_icon);
        imgIconNext = (ImageView) findViewById(R.id.img_icon_next);
        paginationCircles = (ViewGroup) findViewById(R.id.pagination_circles);
        viewPager.setAdapter(new OnboardingAdapter(this.titles,this.messages,this.paginationCircles));
        viewPager.setPageMargin(0);
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {}

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == ViewPager.SCROLL_STATE_IDLE || state == ViewPager.SCROLL_STATE_SETTLING){
                    if(lastPage != viewPager.getCurrentItem()){
                        lastPage = viewPager.getCurrentItem();

                        final ImageView fadeOutImage;
                        final ImageView fadeInImage;
                        if(imgIcon.getVisibility() == View.VISIBLE){
                            fadeOutImage = imgIcon;
                            fadeInImage = imgIconNext;
                        } else {
                            fadeOutImage = imgIconNext;
                            fadeInImage = imgIcon;
                        }

                        fadeInImage.bringToFront();
                        fadeInImage.setImageResource(icons[lastPage]);
                        fadeInImage.clearAnimation();
                        fadeOutImage.clearAnimation();

                        Animation outAnimation = AnimationUtils.loadAnimation(OnboardingActivity.this, R.anim.icon_anim_fade_out);
                        outAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                fadeOutImage.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                        Animation inAnimation = AnimationUtils.loadAnimation(OnboardingActivity.this, R.anim.icon_anim_fade_in);
                        inAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                                fadeInImage.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });


                        fadeOutImage.startAnimation(outAnimation);
                        fadeInImage.startAnimation(inAnimation);
                    }
                }
            }
        });

    }
}
