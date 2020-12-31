package com.daiict.enterprizecomputing.reviewdekho.Comments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daiict.enterprizecomputing.reviewdekho.Classes.CommentSend;
import com.daiict.enterprizecomputing.reviewdekho.Classes.CommentsClass;
import com.daiict.enterprizecomputing.reviewdekho.Classes.SharedPrefManager;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserDataClass;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserReviewClass;
import com.daiict.enterprizecomputing.reviewdekho.DatabaseConnection.API;
import com.daiict.enterprizecomputing.reviewdekho.R;
import com.daiict.enterprizecomputing.reviewdekho.SignUp.SignupClass;
import com.daiict.enterprizecomputing.reviewdekho.SystemDashboard.CommentAdapter;
import com.daiict.enterprizecomputing.reviewdekho.SystemDashboard.ProfileAdapter;
import com.daiict.enterprizecomputing.reviewdekho.SystemDashboard.SettingsProfile;
import com.daiict.enterprizecomputing.reviewdekho.SystemDashboard.SystemDashboard;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentsView extends AppCompatActivity {
    ArrayList<CommentsClass> arrayListComments = new ArrayList<>();
    RecyclerView recyclerView;
    CommentAdapter commentAdapter;

    SharedPrefManager sharedPrefManager;

    String description;
    private int userID = -1;
    TextView desc;

    EditText editTextComment;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_view);
        desc = findViewById(R.id.details_desc_text);
        sharedPrefManager = new SharedPrefManager(this);
        linearLayout = findViewById(R.id.ll_not_visitor);
        if (getIntent().getExtras() != null) {
            //do here
            Intent mIntent = getIntent();
            userID = mIntent.getIntExtra("review_id", 0);
            description = mIntent.getStringExtra("desc");

            desc.setText(description);
        }

        if(sharedPrefManager.getUserId()==5)
        {
            linearLayout.setVisibility(View.INVISIBLE);
            editTextComment = findViewById(R.id.comments_add);
        }
        else
        {

        }


        editTextComment = findViewById(R.id.comments_add);



        //setting up adapter and fetching data and everything
        recyclerView = findViewById(R.id.recyclerview_comments);
        commentAdapter = new CommentAdapter(this, arrayListComments);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commentAdapter);
        databaseConnection();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, SystemDashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.putExtra("Fragment",fragmentName);
        startActivity(intent);
        finish();
    }

    public void databaseConnection() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sharedPrefManager.getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API instanceofapi = retrofit.create(API.class);
        Call<ArrayList<CommentsClass>> call = instanceofapi.getCommentsByReviewId(userID);
        call.enqueue(new Callback<ArrayList<CommentsClass>>() {
            @Override
            public void onResponse(Call<ArrayList<CommentsClass>> call, Response<ArrayList<CommentsClass>> response) {
                if (response.isSuccessful()) {
                    arrayListComments = response.body();
                    Log.e("Comments Class : ", "Data Got : " + arrayListComments.size());

                    dataChangeRView();
//                    Toast.makeText(getContext(), userDataClasses.size(), Toast.LENGTH_SHORT).show();
                }
                // Log.e("Feed Fragment : ","Data Got : "+userDataClasses.size());
            }

            @Override
            public void onFailure(Call<ArrayList<CommentsClass>> call, Throwable t) {
                Log.e("Comments : ", "Error : " + t.getMessage());

            }
        });
    }

    private void dataChangeRView() {
        commentAdapter = new CommentAdapter(this, arrayListComments);
        recyclerView.setAdapter(commentAdapter);
    }

    public void sendButtonPressedComments(View view) {

        if ((editTextComment.getText().toString()).isEmpty()) {
            editTextComment.setError("Kindly Enter Comment");
        } else {
            databaseSendComment();
        }

    }

    private void databaseSendComment() {
       CommentSend commentSend;
        Toast.makeText(this, ""+sharedPrefManager.getUserId()+"  "+userID, Toast.LENGTH_SHORT).show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sharedPrefManager.getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //TOdo

        try {
            UserDataClass userDataClass = new UserDataClass(sharedPrefManager.getUserId());
            UserReviewClass userReviewClass = new UserReviewClass(userID);
            commentSend = new CommentSend(editTextComment.getText().toString(),userDataClass,userReviewClass);
            Log.e("data ",editTextComment.getText().toString()+" "+userDataClass.getUser_id()+" "+userReviewClass.getReviewID());

            Gson gSon = new Gson();

            API instanceofapi = retrofit.create(API.class);
            Call<CommentSend> call = instanceofapi.createComment(commentSend);
            call.enqueue(new Callback<CommentSend>() {
                @Override
                public void onResponse(Call<CommentSend> call, Response<CommentSend> response) {
                    if(response.isSuccessful()) {
                        Log.e("Data Updated", "done");
                        databaseConnection();
                    }
                }

                @Override
                public void onFailure(Call<CommentSend> call, Throwable t) {
                    Log.e("Data not Updated ","failed");
                   // databaseConnection();
                }
            });

        } catch (Exception e) {

        }
    }
}