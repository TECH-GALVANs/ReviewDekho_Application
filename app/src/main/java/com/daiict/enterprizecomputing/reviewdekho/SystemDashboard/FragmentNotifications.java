package com.daiict.enterprizecomputing.reviewdekho.SystemDashboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.daiict.enterprizecomputing.reviewdekho.Classes.SharedPrefManager;
import com.daiict.enterprizecomputing.reviewdekho.Login.Login;
import com.daiict.enterprizecomputing.reviewdekho.R;

import java.util.Objects;


public class FragmentNotifications extends Fragment {
    SharedPrefManager sharedPrefManager;
    LinearLayout linearLayoutVisitor;
    Button buttonLogin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
          sharedPrefManager = new SharedPrefManager(getActivity());
        linearLayoutVisitor = view.findViewById(R.id.notification_fragment_visitor_view);
        if(sharedPrefManager.getRolePreference() != -1) {
            if (sharedPrefManager.getRolePreference() == 5) {
                linearLayoutVisitor.setVisibility(View.VISIBLE);
            } else {
                linearLayoutVisitor.setVisibility(View.GONE);
            }
        }
        if(linearLayoutVisitor.getVisibility()==View.VISIBLE)
        {
            buttonLogin = view.findViewById(R.id.notification_login_button);
            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Login.class);
                    //intent.putExtra("Fragment", "profilefragment");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    SharedPrefManager sharedPrefManager = new SharedPrefManager(Objects.requireNonNull(getContext()));
                    sharedPrefManager.setRolePreference(-1);
                    startActivity(intent);
                }
            });
        }
        return view;
    }
}