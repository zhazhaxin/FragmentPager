package cn.alien95.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.alien95.view.adapter.BaseAdapter;

/**
 * Created by linlongxin on 2016/5/3.
 */
public class FragmentPager extends ViewGroup {

    private final String TAG = "FragmentPager";
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private BaseAdapter adapter;

    private int background;
    private boolean backgroundIsColor = true;

    public FragmentPager(Context context) {
        this(context, null);
    }

    public FragmentPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FragmentPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.FragmentPager);
        background = typedArray.getColor(R.styleable.FragmentPager_tab_background, -1);
        if (background == -1) {
            background = typedArray.getResourceId(R.styleable.FragmentPager_tab_background, -1);
            if (background != -1) {
                backgroundIsColor = false;
            }
        }
        typedArray.recycle();
        initView();
    }

    public void initView() {
        View view = inflate(getContext(), R.layout.view_fragment_pager, this);
        tabLayout = (TabLayout) view.findViewById(R.id.$_tab);
        viewPager = (ViewPager) view.findViewById(R.id.$_view_pager);
        if (background != -1 && backgroundIsColor) {
            tabLayout.setBackgroundColor(background);
        } else if (background != -1) {
            tabLayout.setBackgroundResource(background);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        measureChild(getChildAt(0), widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getChildAt(0).getMeasuredWidth();
        int height = getChildAt(0).getMeasuredHeight();
        getChildAt(0).layout(0, 0, width, height);
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        adapter = new BaseAdapter(fragmentManager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setTabMode(int mode) {
        tabLayout.setTabMode(mode);
    }

    public void addFragment(List<Fragment> fragmentList) {
        if (adapter == null) {
            throw new RuntimeException("No set FragmentManager , adapter == null");
        }
        adapter.addAll(fragmentList);
    }

    public void addFragment(Fragment... fragments) {
        if (adapter == null) {
            throw new RuntimeException("No set FragmentManager , adapter == null");
        }
        adapter.addAll(fragments);
    }

    public void setTitles(String... titles) {
        if (adapter == null) {
            throw new RuntimeException("No set FragmentManager , adapter == null");
        }
        adapter.setTitles(titles);
    }

    public void setTitles(List<String> titles) {
        if (adapter == null) {
            throw new RuntimeException("No set FragmentManager , adapter == null");
        }
        adapter.setTitles(titles);
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }

}
