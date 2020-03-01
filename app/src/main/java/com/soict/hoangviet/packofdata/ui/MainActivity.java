package com.soict.hoangviet.packofdata.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.soict.hoangviet.packofdata.R;
import com.soict.hoangviet.packofdata.adapter.PackAdapter;
import com.soict.hoangviet.packofdata.utils.DeviceUtil;

public class MainActivity extends AppCompatActivity {
    private PackAdapter mPackAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initAdapter();
    }

    private void initViews() {
        mViewPager = findViewById(R.id.view_pager_pack);
    }

    private void initAdapter() {
        mPackAdapter = new PackAdapter(this);
        mViewPager.setClipToPadding(false);
        mViewPager.setOffscreenPageLimit(mViewPager.getChildCount());
        mViewPager.setPageMargin(DeviceUtil.convertDpToPx(this, (int) getResources().getDimension(R.dimen.margin_20_dp)));
        mViewPager.setPadding(
                DeviceUtil.convertDpToPx(this, (int) getResources().getDimension(R.dimen.content_padding_32_dp)),
                DeviceUtil.convertDpToPx(this, (int) getResources().getDimension(R.dimen.content_padding_40_dp)),
                DeviceUtil.convertDpToPx(this, (int) getResources().getDimension(R.dimen.content_padding_32_dp)),
                DeviceUtil.convertDpToPx(this, (int) getResources().getDimension(R.dimen.content_padding_40_dp)));
        mViewPager.setAdapter(mPackAdapter);
    }
}
