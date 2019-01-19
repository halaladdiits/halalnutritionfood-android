package id.ac.its.is.addi.halal.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import id.ac.its.is.addi.halal.R;
import id.ac.its.is.addi.halal.model.Atribute;
import id.ac.its.is.addi.halal.utils.Params;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NutritionFactsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NutritionFactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NutritionFactsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String TITLE = Params.TITLE_FRAGMENT_NUTRITION_FACTS;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NutritionFactsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NutritionFactsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NutritionFactsFragment newInstance(String param1, String param2) {
        NutritionFactsFragment fragment = new NutritionFactsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static NutritionFactsFragment newInstance() {
        NutritionFactsFragment fragment = new NutritionFactsFragment();

        return fragment;
    }

    static Atribute atribute;
    public static NutritionFactsFragment newInstance(Atribute atributeP) {
        NutritionFactsFragment fragment = new NutritionFactsFragment();
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

    TextView tv_label, tv_calories, tv_totalFat, tv_percentTotalFat, tv_totalFatSaturatedFat,
            tv_percentSaturatedFat, tv_totalTransFat, tv_cholestrol, tv_percentCholestrol,
            tv_sodium, tv_percentSodium, tv_totalCarbohydrates, tv_percenttotalCarbohydrates,
            tv_totalDietaryFiber, tv_percentDietaryFiber, tv_totalSugars,
            tv_totalProtein, tv_percentVitaminA, tv_percentVitaminC, tv_percentCalcium, tv_percentIron,
            tv_ingredients;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nutrition_facts, container, false);
        tv_label = view.findViewById(R.id.tv_label);
        tv_calories = view.findViewById(R.id.tv_calories);
        tv_totalFat = view.findViewById(R.id.tv_totalFat);
        tv_percentTotalFat = view.findViewById(R.id.tv_percentTotalFat);
        tv_totalFatSaturatedFat = view.findViewById(R.id.tv_totalFatSaturatedFat);
        tv_percentSaturatedFat = view.findViewById(R.id.tv_percentSaturatedFat);
        tv_totalTransFat = view.findViewById(R.id.tv_totalTransFat);
        tv_cholestrol = view.findViewById(R.id.tv_cholestrol);
        tv_percentCholestrol = view.findViewById(R.id.tv_percentCholestrol);
        tv_sodium = view.findViewById(R.id.tv_sodium);
        tv_percentSodium = view.findViewById(R.id.tv_percentSodium);
        tv_totalCarbohydrates = view.findViewById(R.id.tv_totalCarbohydrates);
        tv_percenttotalCarbohydrates = view.findViewById(R.id.tv_percenttotalCarbohydrates);
        tv_totalDietaryFiber = view.findViewById(R.id.tv_totalDietaryFiber);
        tv_percentDietaryFiber = view.findViewById(R.id.tv_percentDietaryFiber);
        tv_totalSugars= view.findViewById(R.id.tv_totalSugars);
        tv_totalProtein = view.findViewById(R.id.tv_totalProtein);
        tv_percentVitaminA = view.findViewById(R.id.tv_percentVitaminA);
        tv_percentVitaminC = view.findViewById(R.id.tv_percentVitaminC);
        tv_percentCalcium = view.findViewById(R.id.tv_percentCalcium);
        tv_percentIron = view.findViewById(R.id.tv_percentIron);
        tv_ingredients = view.findViewById(R.id.tv_ingredients);

        try {
            setValues();
        } catch (Exception e) {

        }

        return view;
    }

    private void setValues() throws NullPointerException {
        tv_label.setText(atribute.getLabel() != null ? atribute.getLabel() : "Null");
        tv_calories.setText(atribute.getCalories() != null ? "Calories "+atribute.getCalories() : "Calories 0");
        tv_totalFat.setText(atribute.getFat() != null ? "Total Fat "+atribute.getCalories()+"g": "Total Fat 0g");
        try {
            Float totalFat = Float.parseFloat(atribute.getFat()) * 100 / 65;
            tv_percentTotalFat.setText(atribute.getFat() != null ? Math.round(totalFat) + "%" : "0%");
        } catch (Exception e) {
            tv_percentTotalFat.setText(atribute.getFat() != null ? "0%" : "0%");
        }
        tv_totalFatSaturatedFat.setText(atribute.getSaturatedFat() != null ? "     Saturated Fat "+atribute.getSaturatedFat()+"g" : "     Saturated Fat 0g");
        try {
            Float saturatedFat = Float.parseFloat(atribute.getSaturatedFat())*100/20;
            tv_percentSaturatedFat.setText(atribute.getLabel() != null ? Math.round(saturatedFat)+"%" : "0%");
        } catch (Exception e) {
            tv_percentSaturatedFat.setText(atribute.getLabel() != null ? "0%" : "0%");
        }
        tv_totalTransFat.setText(atribute.getTransFat() != null ? "     Trans Fat"+atribute.getTransFat()+"g" : "     Trans Fat 0g");
        tv_cholestrol.setText(atribute.getCholesterol() != null ? "Cholesterol "+atribute.getCholesterol()+"mg" : "Cholesterol 0mg");
        try {
            Float cholesterol = Float.parseFloat(atribute.getCholesterol())*100/300;
            tv_percentCholestrol.setText(atribute.getCholesterol() != null ? Math.round(cholesterol)+"%" : "0%");
        } catch (Exception e) {
            tv_percentCholestrol.setText(atribute.getCholesterol() != null ? "0%" : "0%");
        }

        tv_sodium.setText(atribute.getSodium() != null ? "Sodium "+atribute.getSodium()+"mg" : "0mg");
        try {
            Float sodium = Float.parseFloat(atribute.getSodium())*100/2400;
            tv_percentSodium.setText(atribute.getSodium() != null ? Math.round(sodium)+"%" : "0%");
        } catch (Exception e) {
            tv_percentSodium.setText(atribute.getSodium() != null ? "0%" : "0%");
        }

        tv_totalCarbohydrates.setText(atribute.getTotalCarbohydrates() != null ? "Total Carbohydrates "+atribute.getTotalCarbohydrates()+"g" : "Total Carbohydrates 0g");
        try {
            Float totalCarbohydrates = Float.parseFloat(atribute.getTotalCarbohydrates())*100/300;
            tv_percenttotalCarbohydrates.setText(atribute.getTotalCarbohydrates() != null ? Math.round(totalCarbohydrates)+"%" : "0%");
        } catch (Exception e) {
            tv_percenttotalCarbohydrates.setText(atribute.getTotalCarbohydrates() != null ? "0%" : "0%");
        }
        tv_totalDietaryFiber.setText(atribute.getDietaryFiber() != null ? "Dietary Fiber  "+atribute.getDietaryFiber()+"g" : "Dietary Fiber 0g");
        try {
            Float dietaryFiber = Float.parseFloat(atribute.getDietaryFiber())*100/300;
            tv_percentDietaryFiber.setText(atribute.getDietaryFiber() != null ? Math.round(dietaryFiber)+"%" : "0%");
        } catch (Exception e) {
            tv_percentDietaryFiber.setText(atribute.getDietaryFiber() != null ? "0%" : "0%");
        }

        tv_totalSugars.setText(atribute.getSugar() != null ? "Sugars "+atribute.getSugar()+"g" : "Sugars 0g");
        tv_totalProtein.setText(atribute.getProtein() != null ? "Protein "+atribute.getProtein()+"g" : "Protein 0g");
        tv_percentVitaminA.setText(atribute.getVitaminA() != null ? atribute.getVitaminA()+"%" : "0%");
        tv_percentVitaminC.setText(atribute.getVitaminC() != null ? atribute.getVitaminC()+"%" : "0%");
        tv_percentCalcium.setText(atribute.getCalcium() != null ? atribute.getCalcium()+"%" : "0%");
        tv_percentIron.setText(atribute.getIron() != null ? atribute.getIron()+"%" : "0%");
        tv_ingredients.setText(atribute.getContainsIngredient() != null ? "INGREDIENTS: "+atribute.getContainsIngredient() : "INGREDIENTS: ");


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
