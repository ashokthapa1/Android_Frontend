package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hospitalsearchsystem.Commons;
import com.example.hospitalsearchsystem.R;


import api.UsersAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import url.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class Signup extends Fragment implements View.OnClickListener{

    private EditText etUsername1, etFullName, etEmail, etAddress, etPassword1;
    private Button btnRegister;

    public Signup() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        etUsername1 =view.findViewById(R.id.etUsername1);
        etFullName =view.findViewById(R.id.etFullName);
        etEmail =view.findViewById(R.id.etEmail);
        etAddress =view.findViewById(R.id.etAddress);
        etPassword1 =view.findViewById(R.id.etPassword1);
        btnRegister =view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);
        return view;
    }

    private void userRegister(){

        String givenUsername, givenFullname, givenEmail, givenAddress, givenPassword;


        givenUsername = etUsername1.getText().toString();
        givenFullname = etFullName.getText().toString();
        givenEmail = etEmail.getText().toString();
        givenAddress = etAddress.getText().toString();
        givenPassword = etPassword1.getText().toString();

        UsersAPI usersAPI= URL.getRetrofitInstance().create(UsersAPI.class);
        Call<Void> voidCall = usersAPI.registerUser(givenUsername, givenFullname, givenEmail, givenAddress, givenPassword);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(retrofit2.Call<Void> call, Response<Void> response) {
                if ( !response.isSuccessful() ){
                    Commons.alert(getContext(),"Code: "+response.code());
                    return;
                }
                Commons.alert(getContext(),"You have been registered sucessfully! Login now.");
                etUsername1.setText("");
                etFullName.setText("");
                etEmail.setText("");
                etAddress.setText("");
                etPassword1.setText("");
            }

            @Override
            public void onFailure(retrofit2.Call<Void> call, Throwable t) {
                Commons.alert(getContext(),"An error occured during registration process.");
            }
        });

    }


    @Override
    public void onClick(View v) {

        if( etUsername1.getText().toString().length() > 0 && etFullName.getText().toString().length() > 0 && etEmail.getText().toString().length() > 0 && etAddress.getText().toString().length() > 0 && etPassword1.getText().toString().length() > 0 ){
            userRegister();
        }else{
            Commons.alert(getContext(),"Please enter all field to register.");
        }

    }
}
