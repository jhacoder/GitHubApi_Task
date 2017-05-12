package sushantkumarjha.gtithubapi_task;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.Callback;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Callback<gitRepoIssue>{
    RecyclerView recyclerView;
    EditText editText;
    AdapterHolder adapterHolder;
    public  String url = new String();
    private List<String> title = new ArrayList<String>();
    public Button search;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        editText=(EditText)findViewById(R.id.searchid);
        search =(Button)findViewById(R.id.search_button);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                        .create();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(IGitRepo.APIENDPOINT)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();
                    IGitRepo repoIssue = retrofit.create(IGitRepo.class);
                    url = editText.getText().toString();
                if(!TextUtils.isEmpty(url)) {
                    String[] data = url.split("/");
                    Log.e("URL", url);
                   // Log.e("Data", data[0] + data[1]);
                    if (data.length == 2) {
                        Call<List<gitRepoIssue>> repoIssueList = repoIssue.getIssues(data[0].toString(), data[1].toString());
                        repoIssueList.enqueue(new Callback<List<gitRepoIssue>>() {
                            @Override
                            public void onResponse(Call<List<gitRepoIssue>> call, Response<List<gitRepoIssue>> response) {
                                try {
                                    List<gitRepoIssue> list = response.body();
                                    for (int i = 0; i < response.body().toArray().length; i++) {

                                        title.add(list.get(i).title.toString().trim());
                                    }
                                    adapterHolder = new AdapterHolder(title);
                                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                                    recyclerView.setHasFixedSize(true);
                                    recyclerView.setLayoutManager(mLayoutManager);
                                    recyclerView.setAdapter(adapterHolder);
                                } catch (Exception e) {
                                    Log.e("ERROR:", e.toString());
                                }
                            }

                            @Override
                            public void onFailure(Call<List<gitRepoIssue>> call, Throwable t) {
                                Log.e("Bad Response", "LOG FAILED");
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Input Format:{user/repo}", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });


    }


    @Override
    public void onResponse(Call<gitRepoIssue> call, Response<gitRepoIssue> response) {
        Log.e("Response", response.code()+"");
    }

    @Override
    public void onFailure(Call<gitRepoIssue> call, Throwable t) {
        Log.e("Bad Response","LOGGGGG FAILEDD");
    }
}
