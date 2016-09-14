package mcxtzhang.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(/*"http://www.kuaidi100.com/"*/"https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<ResponseBody> repos = service.listRepos("mcxtzhang");
        repos.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("APP",response.body().source().toString());
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

/*
GitHubService service = retrofit.create(GitHubService.class);
Call<PostQueryInfo> yuantong = service.search("yuantong", "500379523313");
        yuantong.enqueue(new Callback<PostQueryInfo>() {
            @Override
            public void onResponse(Call<PostQueryInfo> call, Response<PostQueryInfo> response) {
                Log.e("APP",response.body().getNu());
            }

            @Override
            public void onFailure(Call<PostQueryInfo> call, Throwable t) {

            }
        });*/
    }
}
