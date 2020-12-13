package com.example.foodman;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentVolunteerRegistration extends Fragment {
    EditText email, pass;
    Context context;
    Button confirmBtn;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;
    private RegistrationSuccessfulListener listener;


    public FragmentVolunteerRegistration() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.listener = (RegistrationSuccessfulListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_fragment_volunteer_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = view.findViewById(R.id.emailPutET);
        pass = view.findViewById(R.id.passwordPutET);
        confirmBtn = view.findViewById(R.id.signUpOk);
        firebaseAuth = FirebaseAuth.getInstance();

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = email.getText().toString();
                String userPass = pass.getText().toString();
                firebaseAuth.createUserWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            currentUser =  firebaseAuth.getCurrentUser();
                            Toast.makeText(getActivity(), "registration successful", Toast.LENGTH_SHORT).show();
                            listener.registrationSuccess();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("failed", e.getLocalizedMessage());
                    }
                });
            }
        });
    }
    public interface RegistrationSuccessfulListener{
        void registrationSuccess();
    }
}
