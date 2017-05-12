package sushantkumarjha.gtithubapi_task;

/**
 * Created by Sushant kumar jha on 12-05-2017.
 */
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface IGitRepo {
    String APIENDPOINT = "https://api.github.com";
    @GET("/repos/{user}/{repo}/issues")
    Call<List<gitRepoIssue>> getIssues(@Path("user") String user,
                                       @Path("repo") String repo);

}
