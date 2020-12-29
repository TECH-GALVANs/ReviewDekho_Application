package com.daiict.enterprizecomputing.reviewdekho.SystemDashboard;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daiict.enterprizecomputing.reviewdekho.Classes.Category;
import com.daiict.enterprizecomputing.reviewdekho.Classes.SharedPrefManager;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserDataClass;
import com.daiict.enterprizecomputing.reviewdekho.DatabaseConnection.API;
import com.daiict.enterprizecomputing.reviewdekho.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragmentCategory extends Fragment {
    RecyclerView recyclerViewCategories;
    AdapterCategory adapterCategory;
    ArrayList<Category> categoryArrayList;
    ProgressDialog dialog;
    SharedPrefManager sharedPrefManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        sharedPrefManager = new SharedPrefManager(getActivity());
        categoryArrayList = new ArrayList<>();
        recyclerViewCategories = view.findViewById(R.id.recyclerview_category);
        getData();

        adapterCategory = new AdapterCategory(getActivity(),categoryArrayList);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewCategories.setAdapter(adapterCategory);

       // getData();


        return view;
    }

    void getData()
    {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Fetching Categories . . .");
        dialog.setCancelable(true);
        dialog.show();


        //Lets Create Retrofit // by creating API

        //Do add comverter Dependency
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sharedPrefManager.getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API instanceofapi = retrofit.create(API.class);
        Call<ArrayList<Category>>  call = instanceofapi.getCategory();
        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    dialog.dismiss();
                    categoryArrayList = response.body();
                    dataChangeRView();
                }
                else
                {
                    Log.e("Category","Not getting Category "+response.code());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                Log.e("Category","Not getting Category "+t.getMessage());
            }
        });

    }
    private void dataChangeRView()
    {
        adapterCategory  = new AdapterCategory(getActivity(),categoryArrayList);
        recyclerViewCategories.setAdapter(adapterCategory);
    }
}