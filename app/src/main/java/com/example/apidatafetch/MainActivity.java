package com.example.apidatafetch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    final String TAG = "MainActivity";

    List<Comment> commentList;
    CommentAdapter commentAdapter;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("all");



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONPlaceholder jsonPlaceholder = retrofit.create(JSONPlaceholder.class);
        Call<List<Comment>> call = jsonPlaceholder.getPost();

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Data Fetched", Toast.LENGTH_SHORT).show();
                }

                listView = findViewById(R.id.list_comment);
                commentList = response.body();
                commentAdapter = new CommentAdapter(MainActivity.this,commentList);
                listView.setAdapter(commentAdapter);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to Load Data from API", Toast.LENGTH_SHORT).show();
            }
        });

       /* commentList = new ArrayList<>();
        listView = findViewById(R.id.list_comment);
        commentAdapter = new CommentAdapter(MainActivity.this,commentList);
        listView.setAdapter(commentAdapter);*/

    }

}