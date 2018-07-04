package me.yasiru.udacityapi.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.yasiru.udacityapi.POJO.Course;


public class DetailPresenter extends Presenter<DetailPresenter.View> {


    @Override
    public void terminate() {
        super.terminate();
        setView(null);
    }

    public void DisplayInstructor(Course course, Context context) {
        // scroll view to display all the instructors
        ScrollView scrollView = new ScrollView(context);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        //get instructors
        List<Course.Instructors> instructors = course.getInstructors();
        Log.i("TAG", instructors.size() + "");
        // add instructor's details name , bio , image
        for (int i = 0; i < instructors.size(); i++) {
            ImageView imageView = new ImageView(context);
            if (!instructors.get(i).getImage().equals(""))
                Picasso.with(context)
                        .load(instructors.get(i).getImage())
                        .resize(500, 400)
                        .into(imageView);
            TextView textViewName = new TextView(context);
            textViewName.setText(instructors.get(i).getName());
            textViewName.setTextAppearance(context, android.R.style.TextAppearance_DeviceDefault_Medium);
            TextView textViewBio = new TextView(context);
            textViewBio.setText(instructors.get(i).getBio());
            linearLayout.addView(textViewName);
            linearLayout.addView(imageView);
            linearLayout.addView(textViewBio);

        }

        // add instructors details to scrollview
        scrollView.addView(linearLayout);
        getView().GenerateInstructorLayout(scrollView);

    }

    public interface View extends Presenter.View {

        void GenerateInstructorLayout(ScrollView scrollView);

    }
}
