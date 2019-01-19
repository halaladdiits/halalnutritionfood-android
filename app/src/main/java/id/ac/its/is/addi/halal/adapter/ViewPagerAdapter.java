package id.ac.its.is.addi.halal.adapter;

/**
 * Created by ahm on 29/12/2017.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import id.ac.its.is.addi.halal.fragment.DetailEntityFragment;
import id.ac.its.is.addi.halal.fragment.NutritionFactsFragment;
import id.ac.its.is.addi.halal.fragment.RelatedProductsFragment;
import id.ac.its.is.addi.halal.model.Atribute;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static int TAB_COUNT = 3;
    Atribute atribute;

    public ViewPagerAdapter(FragmentManager fm, Atribute atributeP) {
        super(fm);
        this.atribute = atributeP;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return DetailEntityFragment.newInstance(atribute);
            case 1:
                return NutritionFactsFragment.newInstance(atribute);
            case 2:
                return RelatedProductsFragment.newInstance(atribute);
        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return DetailEntityFragment.TITLE;

            case 1:
                return NutritionFactsFragment.TITLE;

            case 2:
                return RelatedProductsFragment.TITLE;
        }
        return super.getPageTitle(position);
    }
}
