package me.yasiru.udacityapi.activity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.yasiru.udacityapi.POJO.Course;
import me.yasiru.udacityapi.R;
import me.yasiru.udacityapi.adapter.RecyclerViewAdapter;
import me.yasiru.udacityapi.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements
        RecyclerViewAdapter.ItemClickListener, MainPresenter.View {

    @BindView(R.id.recycle_view_grid_image)
    RecyclerView mRecyclerView;
    ProgressDialog mDialog;
    private ArrayList<Course> mCourseList = new ArrayList<Course>();
    private RecyclerViewAdapter recyclerViewAdapter;
    private MainPresenter mMainPresenter;
    private int mColumnCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mMainPresenter = new MainPresenter();
        mMainPresenter.setView(this);

        if (!mMainPresenter.isInternetAvailable(this))
            return;
        //if internet available, start
        mDialog = new ProgressDialog(this);
        mDialog.setTitle(R.string.progress_title);
        mDialog.setMessage("Wait while loading...");
        mDialog.setCancelable(false); // disable dismiss by tapping outside of the dialog
        mDialog.show();
        //get data from API
        mMainPresenter.fetchData(mCourseList);


    }


    //if something get wrong display error message
    @Override
    public void displayError(String errorMsg) {
        mDialog.dismiss();
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
    }


    @Override
    public void renderGrid(ArrayList<Course> courseList
    ) {

        Log.i("TAG", " " + courseList.size());
        mDialog.dismiss();
        //get column number according to display size
        mMainPresenter.CalColumnCount(this);
        recyclerViewAdapter = new RecyclerViewAdapter(this, courseList);
        recyclerViewAdapter.setClickListener(this);
        //set grid layout
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, mColumnCount));
        mRecyclerView.setAdapter(recyclerViewAdapter);

    }


    @Override
    public void onItemClick(View view, int position) { //on  list item click
        Log.i("TAG", " which is at cell position " + position);

        // Start detail activity
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        // passing array index
        intent.putExtra("course", mCourseList.get(position));
        //add scale up animation
        ActivityOptions options = ActivityOptions.makeScaleUpAnimation(view, 0,
                0, view.getWidth(), view.getHeight());
        startActivity(intent, options.toBundle());
    }


    @Override
    public Context context() {
        return this;
    }


    // when back button press
    // minimize the app
    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }

    // get number of column
    @Override
    public void setColumnCount(int columnCount) {
        mColumnCount = columnCount;
    }

    //return string value of string.xml
    String getResourceString(int id) {
        return this.getResources().getString(id);
    }
}
