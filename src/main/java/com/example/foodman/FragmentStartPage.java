package com.example.foodman;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class StartPageFragment extends Fragment {
    RelativeLayout donatorOpt, volunteerOpt;
    private DonatorVlounteerTransactionListener listener;
    private Context context;


    public StartPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.listener = (DonatorVlounteerTransactionListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_fragment_start_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        donatorOpt = view.findViewById(R.id.donatorOpt);
        volunteerOpt = view.findViewById(R.id.volunteerOpt);

        //got donator info fragment
        donatorOpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.goToDonatorInfoPage();
            }
        });

        //goto volunteer fragment
        volunteerOpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.goToVolunterLogin();
            }
        });
    }
    public interface DonatorVlounteerTransactionListener{
        void goToDonatorInfoPage();
        void goToVolunterLogin();
    }
}
