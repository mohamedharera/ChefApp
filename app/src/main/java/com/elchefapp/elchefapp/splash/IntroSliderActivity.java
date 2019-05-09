package com.elchefapp.elchefapp.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.elchefapp.elchefapp.R;
import com.elchefapp.elchefapp.activities.MainActivity;

public class IntroSliderActivity extends AppCompatActivity {

    private ViewPager introSliderViewPager;
    private SlideAdapter introSliderAdapter;
    public int[] lst_images = {
            R.drawable.indecator_one,
            R.drawable.indecator_two,
            R.drawable.indecator_three,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);

        introSliderViewPager =  findViewById(R.id.viewpager);
        introSliderAdapter = new SlideAdapter(this);
        introSliderViewPager.setAdapter(introSliderAdapter);

        final ViewPager.OnPageChangeListener pageChangeListener= new ViewPager.OnPageChangeListener() {

            boolean lastPageChange = false;
            @Override
            public void onPageScrollStateChanged(int state) {
                int lastIdx = introSliderAdapter.getCount() - 1;

                int curItem = introSliderViewPager.getCurrentItem();
                if(curItem==lastIdx&& state==1) {
                    lastPageChange = true;
                    Log.e("tag","page scroll state >>>> ");
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    lastPageChange = false;
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int lastIdx = introSliderAdapter.getCount() - 1;
                if(lastPageChange && position == lastIdx) {
                }
                }

            @Override
            public void onPageSelected(int position) { }
        };

        introSliderViewPager.setOnPageChangeListener(pageChangeListener);
        // do this in a runnable to make sure the viewPager's views are already instantiated before triggering the onPageSelected call
        introSliderViewPager.post(new Runnable()
        {
            @Override
            public void run()
            {
                pageChangeListener .onPageSelected(introSliderViewPager.getCurrentItem());
            }
        });

    }



    public class SlideAdapter extends PagerAdapter {
        Context context;
        LayoutInflater inflater;
        public SlideAdapter(Context context) {
            this.context = context;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view==(ConstraintLayout)object);
        }

        @Override
        public int getCount() {
            return lst_images.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.slide,container,false);
            ImageView imgslide = (ImageView)  view.findViewById(R.id.slider_image);
            imgslide.setImageResource(lst_images[position]);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ConstraintLayout)object);
        }
    }


}
