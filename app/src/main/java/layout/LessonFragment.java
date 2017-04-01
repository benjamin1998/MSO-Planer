package layout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.randerath.johannes.msoplaner.DayFragmentPagerAdapater;
import com.randerath.johannes.msoplaner.EditLessonActivity;
import com.randerath.johannes.msoplaner.Logic;
import com.randerath.johannes.msoplaner.R;


public class LessonFragment extends Fragment {

    public static final String ARG_INDEX = "ARG_INDEX";
    public static final String ARG_SBJNAME = "ARG_SBJNAME";
    public static final String ARG_PLACE = "ARG_PLACE";
    public static final String ARG_TEACHER = "ARG_TEACHER";
    public static final String ARG_TIME = "ARG_TIME";
    public static final String ARG_DAY = "ARG_DAY";
    public static final String ARG_LOGIC = "ARG_LOGIC";

    private int mIndex;
    private String mSubjectName;
    private String mPlace;
    private String mTeacher;
    private int mTime;
    private int mDay;
    private Logic mLogic;

    public static LessonFragment newInstance(int index, String subjectName, String place, String teacher, int time, int day,  Logic logic) {
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        args.putString(ARG_SBJNAME, subjectName);
        args.putString(ARG_PLACE, place);
        args.putString(ARG_TEACHER, teacher);
        args.putInt(ARG_TIME, time);
        args.putInt(ARG_DAY, day);

        Gson gson = new Gson();
        String logicS = gson.toJson(logic);
        args.putString(ARG_LOGIC, logicS);

        LessonFragment fragment = new LessonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Gson gson = new Gson();
        mIndex = getArguments().getInt(ARG_INDEX);
        mSubjectName = getArguments().getString(ARG_SBJNAME);
        mPlace = getArguments().getString(ARG_PLACE);
        mTeacher = getArguments().getString(ARG_TEACHER);
        mTime = getArguments().getInt(ARG_TIME);
        mDay = getArguments().getInt(ARG_DAY);
        mLogic = gson.fromJson(getArguments().getString(ARG_LOGIC), Logic.class);
    }

    private View.OnLongClickListener onLongClickListener() {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                LinearLayout linear = (LinearLayout) v.getParent();
                LinearLayout container = (LinearLayout) linear.getParent();
                ViewPager pager = (ViewPager) container.getParent().getParent();
                int index = container.indexOfChild(linear);
                Intent intent = new Intent(linear.getContext(), EditLessonActivity.class);
                Gson gson = new Gson();
                intent.putExtra("dayIndex", mDay);
                intent.putExtra("lessonIndex", index);
                String logicS = gson.toJson(mLogic);
                intent.putExtra("logic", logicS);
                Log.i("logicString", logicS);
                startActivity(intent);
                return true;
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson, container, false);

        view.setLongClickable(true);
        view.setOnLongClickListener(onLongClickListener());

        Log.i("container", container.getParent().getParent().getClass().toString());

        TextView sbjTV = (TextView) view.findViewById(R.id.subject);
        TextView teacherTV = (TextView) view.findViewById(R.id.teacher);
        TextView roomTV = (TextView) view.findViewById(R.id.room);

        sbjTV.setText(sbjTV.getText() + " " + mSubjectName);
        teacherTV.setText(teacherTV.getText() + " " + mTeacher);
        roomTV.setText(roomTV.getText() + " " + mPlace);

        return view;
    }

}