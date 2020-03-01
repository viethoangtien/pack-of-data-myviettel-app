package com.soict.hoangviet.packofdata.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.soict.hoangviet.packofdata.R;
import com.soict.hoangviet.packofdata.model.PackData;

import java.util.ArrayList;
import java.util.List;

public class PackAdapter extends PagerAdapter {
    private Context context;
    private List<PackData> packDataList = new ArrayList<>();

    public PackAdapter(Context context) {
        this.context = context;
        packDataList.add(new PackData());
        packDataList.add(new PackData());
        packDataList.add(new PackData());
        packDataList.add(new PackData());
        packDataList.add(new PackData());
    }

    @Override
    public int getCount() {
        return packDataList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = View.inflate(context, R.layout.item_pack_data, null);
        container.addView(itemView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ((TextView)itemView.findViewById(R.id.tv_des)).setText(Html.fromHtml(packDataList.get(position).getDescription(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            ((TextView)itemView.findViewById(R.id.tv_des)).setText(Html.fromHtml(packDataList.get(position).getDescription()));
        }
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
