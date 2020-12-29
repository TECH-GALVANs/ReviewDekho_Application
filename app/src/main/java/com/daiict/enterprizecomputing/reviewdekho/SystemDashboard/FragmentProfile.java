package com.daiict.enterprizecomputing.reviewdekho.SystemDashboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daiict.enterprizecomputing.reviewdekho.Classes.SharedPrefManager;
import com.daiict.enterprizecomputing.reviewdekho.Login.Login;
import com.daiict.enterprizecomputing.reviewdekho.R;

import java.util.Objects;

public class FragmentProfile extends Fragment {
    LinearLayout linearLayoutVisitor;
    Button buttonLogin,editProfile;
    LinearLayout linearLayoutUser;
    SharedPrefManager sharedPrefManager;
    TextView name,user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ImageView imageView_settings = view.findViewById(R.id.profile_btn_settings);
        linearLayoutUser = view.findViewById(R.id.linear_layout_user_visible);
        linearLayoutVisitor = view.findViewById(R.id.profile_fragment_profile_view);

        sharedPrefManager= new SharedPrefManager(Objects.requireNonNull(getContext()));

        if(sharedPrefManager.getRolePreference() != -1) {
            if (sharedPrefManager.getRolePreference() == 5) {
                linearLayoutVisitor.setVisibility(View.VISIBLE);





            } else {
                linearLayoutUser.setVisibility(View.VISIBLE);
                editProfile = view.findViewById(R.id.profilefrag_btn_edit_prof);
                editProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
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

}