package id.ac.its.is.addi.halal.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.its.is.addi.halal.R;
import id.ac.its.is.addi.halal.adapter.EntitysAdapter;
import id.ac.its.is.addi.halal.adapter.EntitysHomeAdapter;
import id.ac.its.is.addi.halal.api.RetrofitHalalAPI;
import id.ac.its.is.addi.halal.model.EntityDatum;
import id.ac.its.is.addi.halal.model.ResultEntity;
import id.ac.its.is.addi.halal.utils.Params;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    List<EntityDatum> listEntity;
    private RecyclerView recyclerView, recyclerViewHome;
    EntitysAdapter adapter;
    EntitysHomeAdapter adapterHome;
    ResultEntity resultEntity;
    ScrollView scrollViewMain;
    Button btn_doSearch, btn_webSubmit;
    FloatingActionButton fab;
    private ProgressDialog dialog;
    TextView tv_messageSearch, tv_no_randomproducts_found;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        scrollViewMain = findViewById(R.id.scrollViewMain);
        scrollViewMain.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerViewHome = findViewById(R.id.recycler_viewHome);
        tv_messageSearch = findViewById(R.id.tv_messageSearch);
        tv_no_randomproducts_found = findViewById(R.id.tv_no_randomproducts_found);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollViewMain.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                tv_messageSearch.setVisibility(View.GONE);
            }
        });

        dialog = new ProgressDialog(MainActivity.this);
        dialog.setCancelable(true);
        dialog.setMessage("Loading");


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getBaseContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        RecyclerView.LayoutManager mLayoutManagerHome = new GridLayoutManager(getBaseContext(), 1);
        recyclerViewHome.setLayoutManager(mLayoutManagerHome);
        recyclerViewHome.setItemAnimator(new DefaultItemAnimator());

        bindRandomProducts();

        listEntity = new ArrayList<>();

        btn_doSearch = findViewById(R.id.btn_doSearch);
        btn_webSubmit = findViewById(R.id.btn_submit);
        btn_doSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setFocusable(true);
                searchView.setIconified(false);
            }
        });
        btn_webSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(Params.BASE_URL+"foodproduct/create"));
                startActivity(i);
            }
        });




    }

    private void bindRandomProducts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Params.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitHalalAPI service = retrofit.create(RetrofitHalalAPI.class);

        Call<ResultEntity> call = service.getRandomEntity();

        call.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                if (response.body().getEntityData().size() == 0) {
                    //Toast.makeText(MainActivity.this, "Entity not found", Toast.LENGTH_SHORT).show();
                    tv_no_randomproducts_found.setVisibility(View.VISIBLE);
                }

                List<EntityDatum> listEntityHome = new ArrayList<>();
                for (int i = 0; i < response.body().getEntityData().size()-2 ; i++) {
                    listEntityHome.add(response.body().getEntityData().get(i));
                }

                adapterHome = new EntitysHomeAdapter(MainActivity.this, listEntityHome);
                recyclerViewHome.setAdapter(adapterHome);

            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                tv_no_randomproducts_found.setVisibility(View.VISIBLE);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Toast like print
                //Toast.makeText(MainActivity.this, "Query = "+query, Toast.LENGTH_SHORT).show();

                getRetrofitSearch(query);
                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                searchView.setQuery(query, false);
                myActionMenuItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                scrollViewMain.setVisibility(View.GONE);
                fab.setVisibility(View.VISIBLE);
                return false;
            }
        });
        return true;
    }

    private void getRetrofitSearch(String query) {
        dialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Params.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitHalalAPI service = retrofit.create(RetrofitHalalAPI.class);

        Call<ResultEntity> call = service.getSearchEntity(query);

        call.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                resultEntity = new ResultEntity();
                tv_messageSearch.setVisibility(View.VISIBLE);
                tv_messageSearch.setText(response.body().getMessage());
                resultEntity.setMessage(response.body().getMessage());
                resultEntity.setEntityData(response.body().getEntityData());

                listEntity = response.body().getEntityData();
                if (response.body().getEntityData().size() == 0) {
                    Toast.makeText(MainActivity.this, "Entity not found", Toast.LENGTH_SHORT).show();
                }


                adapter = new EntitysAdapter(MainActivity.this, listEntity);
                recyclerView.setAdapter(adapter);

                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error occured", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        minimizeApp();

    }

    public void minimizeApp() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }



}
