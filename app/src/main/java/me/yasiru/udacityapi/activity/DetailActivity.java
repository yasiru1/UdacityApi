package me.yasiru.udacityapi.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yasiru.udacityapi.POJO.Course;
import me.yasiru.udacityapi.R;
import me.yasiru.udacityapi.presenter.DetailPresenter;
import me.yasiru.udacityapi.presenter.MainPresenter;

public class DetailActivity extends AppCompatActivity implements DetailPresenter.View {

    @BindView(R.id.banner_image)
    ImageView mImageView;
    Course mCourse;

    @BindView(R.id.course_title)
    TextView mCourseTitle;

    @BindView(R.id.course_subtitle)
    TextView mCourseSubTitle;

    @BindView(R.id.relative_layout_instructor)
    RelativeLayout mInstructorLayout;


    private DetailPresenter mDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        //get course from intent
        mCourse =(Course) getIntent().getSerializableExtra("course");
        mDetailPresenter =new DetailPresenter();
        mDetailPresenter.setView(this);

        Picasso.with(this)
                .load(mCourse.getBanner_image())
                .resize(800, 500)
                .into(mImageView);

        mCourseTitle.setText(mCourse.getTitle());
        mCourseSubTitle.setText(mCourse.getShort_summary());
        mDetailPresenter.DisplayInstructor(mCourse,this);

    }

    @Override
    public void GenerateInstructorLayout(ScrollView scrollView){
        mInstructorLayout.addView(scrollView);

    }

    @Override
    public Context context() {
        return this;
    }
}


