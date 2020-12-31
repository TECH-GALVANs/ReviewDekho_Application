package com.daiict.enterprizecomputing.reviewdekho.SystemDashboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daiict.enterprizecomputing.reviewdekho.Classes.SharedPrefManager;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserReviewClass;
import com.daiict.enterprizecomputing.reviewdekho.DatabaseConnection.API;
import com.daiict.enterprizecomputing.reviewdekho.Login.Login;
import com.daiict.enterprizecomputing.reviewdekho.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentProfile extends Fragment {
    LinearLayout linearLayoutVisitor;

    Button buttonLogin,editProfile;

    LinearLayout linearLayoutUser,linearLayoutNodata,linearLayoutreviewer;

    SharedPrefManager sharedPrefManager;

    TextView name,user;

    ImageView imageNodata;

    FloatingActionButton floatingActionButton;

    de.hdodenhof.circleimageview.CircleImageView imageView;

    ArrayList<UserReviewClass> userDataClasses = new ArrayList<UserReviewClass>();
    RecyclerView recyclerView;
    ProfileAdapter adapterUserReviewDisplay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ImageView imageView_settings = view.findViewById(R.id.profile_btn_settings);

        linearLayoutUser = view.findViewById(R.id.linear_layout_user_visible);
        linearLayoutVisitor = view.findViewById(R.id.profile_fragment_profile_view);
        linearLayoutNodata = view.findViewById(R.id.ll_user_profile);
        linearLayoutreviewer = view.findViewById(R.id.its_reviewer);



        sharedPrefManager= new SharedPrefManager(Objects.requireNonNull(getContext()));



        if(sharedPrefManager.getRolePreference() != -1) {
            if (sharedPrefManager.getRolePreference() == 5) {
                linearLayoutVisitor.setVisibility(View.VISIBLE);

            } else {

                linearLayoutUser.setVisibility(View.VISIBLE);
                if(sharedPrefManager.getRolePreference()==0)
                {
                    linearLayoutreviewer.setVisibility(View.GONE);
                    linearLayoutNodata.setVisibility(View.VISIBLE);

                }
                floatingActionButton = view.findViewById(R.id.profilefrag_btn_floating_addimage);

                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                //Setting up image

                imageView = view.findViewById(R.id.circleImageView_profile);
                if(sharedPrefManager.getImage().equals(""))
                {

                }
                else
                {
                    byte[] decodedString = Base64.decode(sharedPrefManager.getImage(), Base64.DEFAULT);
                    Glide.with(getActivity()).asBitmap().load(decodedString).circleCrop().into(imageView);
                }


                //setting up adapter and fetching data and everything
                recyclerView = view.findViewById(R.id.profilefrag_recyclerview_data);
                adapterUserReviewDisplay = new ProfileAdapter(getActivity(),userDataClasses);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                recyclerView.setAdapter(adapterUserReviewDisplay);
                databaseConnection();





                editProfile = view.findViewById(R.id.profilefrag_btn_edit_prof);
                editProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), EditProfileGeneralSettings.class);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        //intent.putExtra("Fragment", "profilefragment");
                        startActivity(intent);
                    }
                });
                name = view.findViewById(R.id.profilefrag_text_name);
                user = view.findViewById(R.id.profile_txt_user);

                name.setText(sharedPrefManager.getUserName());
                if(sharedPrefManager.getRolePreference() == 0)
                     user.setText("U S E R");
                else {
                    user.setText("R E V I E W E R ");
                    user.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.intro_image_skip,0);
                }


            }

        }







        imageView_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SettingsProfile.class);
                //intent.putExtra("Fragment", "profilefragment");

                startActivity(intent);
            }
        });

        linearLayoutVisitor = view.findViewById(R.id.profile_fragment_profile_view);
        if(linearLayoutVisitor.getVisibility()==View.VISIBLE)
        {
            buttonLogin = view.findViewById(R.id.profile_login);
            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //intent.putExtra("Fragment", "profilefragment");
                    SharedPrefManager sharedPrefManager = new SharedPrefManager(Objects.requireNonNull(getContext()));
                    sharedPrefManager.setRolePreference(-1);
                    startActivity(intent);

                }
            });
        }

        return view;
    }

    public void databaseConnection()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sharedPrefManager.getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API instanceofapi = retrofit.create(API.class);
        Call<ArrayList<UserReviewClass>> call = instanceofapi.getReviewsById(sharedPrefManager.getUserId());
        call.enqueue(new Callback<ArrayList<UserReviewClass>>() {
            @Override
            public void onResponse(Call<ArrayList<UserReviewClass>> call, Response<ArrayList<UserReviewClass>> response) {
                if(response.isSuccessful())
                {
                    userDataClasses = response.body();
                    Log.e("Profile Fragment : ","Data Got : "+userDataClasses.size());

                    dataChangeRView();
//                    Toast.makeText(getContext(), userDataClasses.size(), Toast.LENGTH_SHORT).show();
                }
                // Log.e("Feed Fragment : ","Data Got : "+userDataClasses.size());
            }

            @Override
            public void onFailure(Call<ArrayList<UserReviewClass>> call, Throwable t) {
                Log.e("Profile Fragment : ","Error : "+t.getMessage());

            }
        });
    }
    private void dataChangeRView()
    {
        adapterUserReviewDisplay  = new ProfileAdapter(getActivity(),userDataClasses);
        recyclerView.setAdapter(adapterUserReviewDisplay);
    }

}