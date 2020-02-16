package fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hospitalsearchsystem.Commons;
import com.example.hospitalsearchsystem.HomePage;
import com.example.hospitalsearchsystem.R;
import com.example.hospitalsearchsystem.admin_dashboard;

import api.UsersAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import url.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserLogin extends Fragment {

    private EditText etUsername, etPassword;
    private Button btnLogin;

    public UserLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_login, container, false);

        etUsername =view.findViewById(R.id.etUsername);
        etPassword =view.findViewById(R.id.etPassword);
        btnLogin =view.findViewById(R.id.btnLoginUser);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        return view;
    }

    private void login(){
        if( etUsername.getText().toString().length() > 0 && etPassword.getText().toString().length() > 0 ) {

            if(etUsername.getText().toString().equals("admin")&& etPassword.getText().toString().equals("admin")){
                Commons.loggedIn = true;
                Commons.isAdmin = true;
                getActivity().finish();
                Intent in = new Intent(getContext(), admin_dashboard.class);
                startActivity(in);
            }else{
                UsersAPI usersAPI= URL.getRetrofitInstance().create(UsersAPI.class);
                Call<Commons.ResponseBody> userCall = usersAPI.authUser(etUsername.getText().toString(),etPassword.getText().toString());


                userCall.enqueue(new Callback<Commons.ResponseBody>() {
                    @Override
                    public void onResponse(Call<Commons.ResponseBody> call, Response<Commons.ResponseBody> response) {
                        Commons.ResponseBody resBody = response.body();
                        if(resBody.getStatus().equals("success")){
                            Commons.loggedIn = true;
                            Commons.isAdmin = false;
                            Commons.token = resBody.getAccessToken();
                            Commons.showNotification(getContext(),"Recommended","You can view most viewed hospitals as well.");
                            getActivity().finish();
                            Intent in = new Intent(getContext(), HomePage.class);
                            startActivity(in);
                        }else{
                            Commons.alert(getContext(),resBody.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<Commons.ResponseBody> call, Throwable t) {
                        Commons.alert(getContext(),t.getLocalizedMessage()+"");
                    }
                });
            }

        }else{
            Commons.alert(getContext(),"Please enter username and password to login.");
        }
    }

}
