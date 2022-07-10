package com.example.navdemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    Button button;
    String choice, choice1, choice2, choice3;
    EditText editTextName;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Spinner spinner1, spinner2, spinner3;


        spinner1 = getView().findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        spinner2 = getView().findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner2, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        spinner3 = getView().findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.spinner3, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(this);

        editTextName = getView().findViewById(R.id.editTextName);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        choice = adapterView.getItemAtPosition(i).toString();

        try {
            if(choice.equals("溝壩國小") || choice.equals("斗南國小")){
                choice1 = choice;
//                Log.d("Debug", "choice1");
            }
            else if(choice.equals("三年級") || choice.equals("四年級") || choice.equals("五年級")){
                choice2 = choice;
//                Log.d("Debug", "choice2");
            }
            else if(choice.equals("甲") || choice.equals("乙") || choice.equals("丙") || choice.equals("丁") || choice.equals("戊") || choice.equals("己")){
                choice3 = choice;
//                Log.d("Debug", "choice3");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }



//        bundle.putString("grade", choice2);
//        bundle.putString("class", choice3);
        try {
//            Log.d("Debug", "school = " + choice1.getClass().getSimpleName());
//            Log.d("Debug", "school = " + choice1);

//            Log.d("Debug", "grade = " + choice2);
//            Log.d("Debug", "class = " + choice3);
//            Log.d("Debug", String.valueOf(adapterView.getId()));
        }catch (Exception e) {
            e.printStackTrace();
        }

        button = getView().findViewById(R.id.submit_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextName.getText().toString().isEmpty()){
                    editTextName.setError("請輸入Name！");
//                    Log.d("Debug", choice1);
//                    Log.d("Debug", choice2);
//                    Log.d("Debug", choice3);
                }
                else if(choice1 == null || choice2 == null || choice3 == null){
                    Log.d("Debug", "請選擇項目");
                }
                else{
                    Bundle bundle = new Bundle();
                    bundle.putString("school", choice1);
                    bundle.putString("grade", choice2);
                    bundle.putString("class", choice3);
                    bundle.putString("name", editTextName.getText().toString());
                    NavController controller = Navigation.findNavController(view);
                    controller.navigate(R.id.action_homeFragment2_to_detailFragment2, bundle);
                }

            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}