package cn.alien95.fragmentpagerlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.alien95.view.FragmentPager;

public class MainActivity extends AppCompatActivity {

    private FragmentPager fragmentPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("FragmentPager");

        fragmentPager = (FragmentPager) findViewById(R.id.fragment_pager);
        fragmentPager.setFragmentManager(getSupportFragmentManager());

        fragmentPager.addFragments(new TestFragment(),new TestFragment(),new TestFragment());
        fragmentPager.setTitles("ONE","TWO","THREE","FOUR","FIVE","SIX");

    }
}
