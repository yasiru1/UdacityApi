package me.yasiru.udacityapi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.yasiru.udacityapi.POJO.Course;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.yasiru.udacityapi.R;

/**
 * Created by yasiru on 2/28/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ArrayList<String> mImageurlList =new ArrayList<String>();
    private ArrayList<String> mTitleList =new ArrayList<String>();
    private ArrayList<String> mSubTitleList =new ArrayList<String>();
    private ArrayList<String> mLabelList =new ArrayList<String>();
    private Context context;
    private final static int SCALE_UP_DURATION = 1000; //SCALE_UP_DURATION in milliseconds





    // data is passed into the constructor
    public RecyclerViewAdapter(Context mcontext, ArrayList<Course> courseList ) {
        context=mcontext;
        this.mInflater = LayoutInflater.from(context);

                            for(int i = 0; i < courseList.size(); i++) {

                                Course course = courseList.get(i);

                                    mImageurlList.add(course.getBanner_image());
                                    mTitleList.add(course.getTitle());
                                    mSubTitleList.add(course.getSubtitle());
                                    mLabelList.add(course.getLevel());

                            }


    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the image view in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageView imageView = (ImageView) holder.mImageView;
        holder.mTextViewLable.setText(mLabelList.get(position));
        holder.mTextViewTitle.setText(mTitleList.get(position));
        holder.mTextViewSubTitle.setText(mSubTitleList.get(position));
        Picasso.with(context)
                .load(mImageurlList.get(position))
                .resize(500, 300)
                .into(imageView);
         // set scale up animation
        setScaleAnimation(holder.mImageView);
     //  Log.i("TAG", " aaaaa " +mLabelList.get(position) + "  "+mTitleList.get(position) );
    }


    //image Scale up animation
    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(SCALE_UP_DURATION);
        view.startAnimation(anim);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mImageurlList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        @BindView(R.id.course_thumbnail)
        ImageView mImageView;
        @BindView(R.id.course_title)
        TextView mTextViewTitle;
        @BindView(R.id.course_subtitle)
        TextView mTextViewSubTitle;
        @BindView(R.id.course_lable)
        TextView mTextViewLable;

        public  ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }




    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent me.yasiru.udacityapi.activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}