package me.yasiru.udacityapi.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import java.util.ArrayList;
import me.yasiru.udacityapi.POJO.Course;
import me.yasiru.udacityapi.POJO.UdacityResponse;
import me.yasiru.udacityapi.R;
import me.yasiru.udacityapi.rest.RestClient;
import me.yasiru.udacityapi.util.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter extends Presenter<MainPresenter.View> {

    @Override public void terminate() {
        super.terminate();
        setView(null);
    }

    //check for internet
    public boolean isInternetAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting())
            return  true;
        else {
            //display error message
            getView().displayError(context.getResources().getString(R.string.net_error));
            return  false;
        }

    }


    //get image url and store in array list
    public void fetchData(final ArrayList<Course> courseList ) {

        Call<UdacityResponse> call = RestClient.getRetrofitService().getCourses();
        call.enqueue(new Callback<UdacityResponse>() {
            @Override
            public void onResponse(Call<UdacityResponse> call, Response<UdacityResponse> response) {
                if (response.body() != null) {
                    courseList.addAll(response.body().getData());
                    getView().renderGrid(courseList);
                }
            }

            @Override
            public void onFailure(Call<UdacityResponse> call, Throwable t) {
                //Handle failure
                getView().displayError(t.toString());
                Log.i("error",t.toString());
            }
        });
    }


    // to get number of column according to display
    public void CalColumnCount(Context context){
        getView().setColumnCount(Utility.calculateNoOfColumns(context));
    }



    public interface View extends Presenter.View {
        //set number of column
        void setColumnCount(int columnCount);


        void displayError(String errorMsg);

        void renderGrid(ArrayList<Course> courseList);


    }
}