package id.ac.its.is.addi.halal.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import id.ac.its.is.addi.halal.R;
import id.ac.its.is.addi.halal.adapter.IngAddsAdapter;
import id.ac.its.is.addi.halal.api.RetrofitHalalAPI;
import id.ac.its.is.addi.halal.model.Atribute;
import id.ac.its.is.addi.halal.model.Certificate;
import id.ac.its.is.addi.halal.model.IngredientAdditives;
import id.ac.its.is.addi.halal.utils.Params;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailEntityFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailEntityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailEntityFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String TITLE = Params.TITLE_FRAGMENT_DETAIL_ENTITY;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DetailEntityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailEntityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailEntityFragment newInstance(String param1, String param2) {
        DetailEntityFragment fragment = new DetailEntityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static DetailEntityFragment newInstance() {
        DetailEntityFragment fragment = new DetailEntityFragment();

        return fragment;
    }

    static Atribute atribute;

    public static DetailEntityFragment newInstance(Atribute atributeP) {
        DetailEntityFragment fragment = new DetailEntityFragment();
        atribute = atributeP;
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, atribute.getLabel());
        args.putString(ARG_PARAM2, atribute.getContainsIngredient());
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Context context;
    TextView tv_foodid, tv_label, tv_ingredients, tv_info, tv_certificate;
    private RecyclerView recyclerViewIng, recyclerViewAdd;
    LinearLayout layout_certificate;
    IngAddsAdapter adapterIng, adapterAdd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_entity, container, false);
        context = view.getContext();
        recyclerViewIng = view.findViewById(R.id.recycler_viewIng);
        recyclerViewAdd = view.findViewById(R.id.recycler_viewAdd);
        RecyclerView.LayoutManager mLayoutManagerIng = new GridLayoutManager(getContext(), 1);
        RecyclerView.LayoutManager mLayoutManagerAdd = new GridLayoutManager(getContext(), 1);
        recyclerViewIng.setLayoutManager(mLayoutManagerIng);
        recyclerViewIng.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAdd.setLayoutManager(mLayoutManagerAdd);
        recyclerViewAdd.setItemAnimator(new DefaultItemAnimator());


        tv_foodid = view.findViewById(R.id.tv_foodid);
        tv_label = view.findViewById(R.id.tv_label);
        tv_ingredients = view.findViewById(R.id.tv_ingredients);
        tv_info = view.findViewById(R.id.tv_info);
        tv_certificate = view.findViewById(R.id.tv_certificate);
        layout_certificate = view.findViewById(R.id.layout_certificate);

        tv_foodid.setText(atribute.getFoodCode());
        tv_label.setText(atribute.getLabel());
        tv_ingredients.setText(atribute.getContainsIngredient());
        try {
            tv_info.setText(fullness());
        } catch (Exception e) {

        }

        if (atribute.getCertificate() != null) {
            layout_certificate.setVisibility(View.VISIBLE);
            getRetrofitIngAddListCertificate(atribute.getFoodproductId());
        } else  {
            layout_certificate.setVisibility(View.GONE);
        }

        getRetrofitIngAddListCertificate(atribute.getFoodproductId());



        return  view;
    }

    private void getRetrofitIngAddListCertificate(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Params.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitHalalAPI service = retrofit.create(RetrofitHalalAPI.class);

        Call<List<IngredientAdditives>> call = service.getIngList(id);
        Call<List<IngredientAdditives>> callAdd = service.getAddList(id);
        Call<Certificate> callCertificate = service.getCertificate(id);

        call.enqueue(new Callback<List<IngredientAdditives>>() {
            @Override
            public void onResponse(Call<List<IngredientAdditives>> call, Response<List<IngredientAdditives>> response) {
                if (response.isSuccessful()) {
                    //Toast.makeText(getContext(), "Respon = "+response.body().toString(), Toast.LENGTH_SHORT).show();
                    adapterIng = new IngAddsAdapter(getContext(), response.body());
                    //Toast.makeText(getContext(), "Respon = "+response.body().get(0).getName(), Toast.LENGTH_SHORT).show();

                    recyclerViewIng.setAdapter(adapterIng);
                } else {
                    Toast.makeText(getContext(), "Failed to connect Server", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<IngredientAdditives>> call, Throwable t) {
                //Toast.makeText(getContext(), "Error occured = "+t.getMessage()+" + "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });

        callCertificate.enqueue(new Callback<Certificate>() {
            @Override
            public void onResponse(Call<Certificate> call, Response<Certificate> response) {

                //Toast.makeText(context, "debug = "+response.errorBody(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    //Certificate certificate = response.body().getCExpire();
                    //Toast.makeText(context, "respon : "+response.body().getCCode(), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getContext(), "Respon = "+response.body().toString(), Toast.LENGTH_SHORT).show();
                    if (response.body().getCCode() != null) {
                        tv_certificate.setText(response.body().getCCode() != null ? response.body().getAllString() : "");
                    } else {
                        layout_certificate.setVisibility(View.GONE);
                    }

//                    );
                } else {
                    Toast.makeText(getContext(), "Failed to connect Server", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Certificate> call, Throwable t) {
                //Toast.makeText(getContext(), "Error occured = "+t.getMessage()+" + "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });

        callAdd.enqueue(new Callback<List<IngredientAdditives>>() {
            @Override
            public void onResponse(Call<List<IngredientAdditives>> call, Response<List<IngredientAdditives>> response) {
                if (response.isSuccessful()) {
                    //Toast.makeText(getContext(), "Respon = "+response.body().toString(), Toast.LENGTH_SHORT).show();
                    adapterAdd = new IngAddsAdapter(getContext(), response.body());
                    //Toast.makeText(getContext(), "Respon = "+response.body().get(0).getName(), Toast.LENGTH_SHORT).show();

                    recyclerViewAdd.setAdapter(adapterAdd);
                } else {
                    Toast.makeText(getContext(), "Failed to connect Server", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<IngredientAdditives>> call, Throwable t) {
                //Toast.makeText(getContext(), "Error occured = "+t.getMessage()+" + "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    public String fullness() throws NullPointerException
    {
        //Double foodCalories = Double.parseDouble(atribute.getCalories()) != null ? Double.parseDouble(atribute.getCalories()) : 0.0 ;
        Double CAL = Double.parseDouble(atribute.getCalories())*100/Double.parseDouble(atribute.getNetWeight());
        if(CAL<30) CAL=30.0;
        Double PR = Double.parseDouble(atribute.getProtein())*100/Double.parseDouble(atribute.getNetWeight());
        if(PR>30) PR=30.0;
        Double DF= Double.parseDouble(atribute.getFiber())*100/Double.parseDouble(atribute.getNetWeight());
        if(DF>12) DF=12.0;
        Double TF= Double.parseDouble(atribute.getFat())*100/Double.parseDouble(atribute.getNetWeight());
        if(TF>50) TF=50.0;

        Double FF = Math.max(0.5, Math.min(5.0, 41.7/Math.pow(CAL,0.7) + 0.05*PR + 6.17E-4*Math.pow(DF,3) - 7.25E-6*Math.pow(TF,3)+ 0.617));
        // FF=MAX(0.5, MIN(5.0, 41.7/CAL^0.7 + 0.05*PR + 6.17E-4*DF^3 - 7.25E-6*TF^3 + 0.617))
        if (FF < 1) {
            return "This food is not filling";
        } else if (FF >= 1 && FF < 2) {
            return "This food is less filling";
        } else if (FF >= 2 && FF < 3) {
            return "This food is enough filling";
        } else if (FF >= 3 && FF < 4) {
            return "This food is more filling";
        } else if (FF > 4) {
            return "This food is very filling";
        } else {
            return "This food out of the expectations";
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
