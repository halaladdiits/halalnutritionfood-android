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
import android.widget.Toast;

import java.util.List;

import id.ac.its.is.addi.halal.R;
import id.ac.its.is.addi.halal.adapter.ReleatedAdapter;
import id.ac.its.is.addi.halal.api.RetrofitHalalAPI;
import id.ac.its.is.addi.halal.model.Atribute;
import id.ac.its.is.addi.halal.model.Related;
import id.ac.its.is.addi.halal.utils.Params;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RelatedProductsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RelatedProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RelatedProductsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String TITLE = Params.TITLE_FRAGMENT_RELEATED_PRODUCTS;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RelatedProductsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RelatedProductsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RelatedProductsFragment newInstance(String param1, String param2) {
        RelatedProductsFragment fragment = new RelatedProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static RelatedProductsFragment newInstance() {
        RelatedProductsFragment fragment = new RelatedProductsFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    static Atribute atribute;
    public static RelatedProductsFragment newInstance(Atribute atributeP) {
        RelatedProductsFragment fragment = new RelatedProductsFragment();
        atribute = atributeP;

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

    private RecyclerView recyclerView;
    ReleatedAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_releated_products, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Toast.makeText(getContext(), "Atribute = "+ atribute.getFoodproductId() + atribute.getLabel(), Toast.LENGTH_SHORT).show();

        getRetrofitReleatedProducts(atribute.getFoodproductId());

        return view;
    }

    private void getRetrofitReleatedProducts(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Params.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitHalalAPI service = retrofit.create(RetrofitHalalAPI.class);

        Call<List<Related>> call = service.getRelatedProduct(id);

        call.enqueue(new Callback<List<Related>>() {
            @Override
            public void onResponse(Call<List<Related>> call, Response<List<Related>> response) {
                if (response.isSuccessful()) {
                    //Toast.makeText(getContext(), "Respon = "+response.body().toString(), Toast.LENGTH_SHORT).show();
                    adapter = new ReleatedAdapter(getContext(), response.body());
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Failed to connect Server", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Related>> call, Throwable t) {
                //Toast.makeText(getContext(), "Error occured = "+t.getMessage()+" + "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });

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
