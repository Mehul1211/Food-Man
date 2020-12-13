package com.example.foodman;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;

public class DonationListFragment extends Fragment {
    private RecyclerView recyclerView;
    private DonationAdapter adapter;
    List<InformationModel> modelList = new ArrayList<>();
    Context context;

    public DonationListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_fragment_donetion_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.donationRecycler);
        modelList = DonationDatabase.getInstance(context).getDonationDao().getAllDonationList();
        Collections.reverse(modelList);
        adapter = new DonationAdapter(context, modelList);
        GridLayoutManager glm = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(adapter);
    }
}
