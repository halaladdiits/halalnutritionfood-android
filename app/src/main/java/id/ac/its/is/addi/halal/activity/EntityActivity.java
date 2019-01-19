package id.ac.its.is.addi.halal.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import id.ac.its.is.addi.halal.R;
import id.ac.its.is.addi.halal.adapter.ViewPagerAdapter;
import id.ac.its.is.addi.halal.fragment.DetailEntityFragment;
import id.ac.its.is.addi.halal.fragment.NutritionFactsFragment;
import id.ac.its.is.addi.halal.fragment.RelatedProductsFragment;
import id.ac.its.is.addi.halal.model.Atribute;

public class EntityActivity extends AppCompatActivity implements DetailEntityFragment.OnFragmentInteractionListener, NutritionFactsFragment.OnFragmentInteractionListener, RelatedProductsFragment.OnFragmentInteractionListener {

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private ViewPager mViewPager;
    private Toolbar toolbar;
    private ViewPagerAdapter mViewPagerAdapter;
    private TabLayout mTabLayout;
    Atribute atribute;
    TextView tv_label, tv_eCode, tv_sameAs;
    RelativeLayout layout_entity;
    Button btn_sameAs;
    LinearLayout layout_sameAs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity);
        layout_entity = findViewById(R.id.layout_entity);

        Intent intent = getIntent();
        atribute = intent.getParcelableExtra("atribute");
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(atribute.getLabel());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (atribute.getFoodproductId() != null) {
            setViewPager();
        } else {
            hideViewPager();
            setEntityView();
        }

    }

    private void setEntityView() {
        layout_entity.setVisibility(View.VISIBLE);
        tv_label = findViewById(R.id.tv_label);
        tv_eCode = findViewById(R.id.tv_eCode);
        tv_sameAs = findViewById(R.id.tv_sameAs);
        layout_sameAs = findViewById(R.id.layout_sameAs);
        try {
            tv_label.setText(atribute.getLabel());
            tv_eCode.setText(atribute.getComment());
            if (atribute.getSameAs() != null) {
                layout_sameAs.setVisibility(View.VISIBLE);
                btn_sameAs = findViewById(R.id.btn_sameAs);
                btn_sameAs.setVisibility(View.VISIBLE);
                final String sameAs = "http://dbpedia.org/resource/"+atribute.getSameAs().replace(" ", "_");
                tv_sameAs.setText("DBPedia : "+sameAs);
                btn_sameAs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(sameAs));
                        startActivity(i);
                    }
                });
            } else {
                layout_sameAs.setVisibility(View.GONE);
            }

        } catch (Exception e) {

        }
    }

    private void setViewPager() {

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), atribute);
        mViewPager.setAdapter(mViewPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tab);
        mTabLayout.setupWithViewPager(mViewPager);
        layout_entity.setVisibility(View.GONE);
    }

    private void hideViewPager() {

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setVisibility(View.GONE);

        mTabLayout = (TabLayout) findViewById(R.id.tab);
        mTabLayout.setVisibility(View.GONE);
    }

}
