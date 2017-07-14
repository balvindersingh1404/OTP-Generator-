package com.example.smssending.otpsms.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.smssending.otpsms.fragment.ContactsFragment;
import com.example.smssending.otpsms.fragment.SentMessagesFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
 
    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
 
    @Override
    public Fragment getItem(int position) {
 
        switch (position) {
            case 0:
                ContactsFragment tab1 = new ContactsFragment();
                return tab1;
            case 1:
                SentMessagesFragment tab2 = new SentMessagesFragment();
                return tab2;
            default:
                return null;
        }
    }
 
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
