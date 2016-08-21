package com.example.hao.Note;

import android.app.Fragment;

/**
 * Created by hao on 2016-08-05.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
