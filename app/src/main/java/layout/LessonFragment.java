package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.randerath.johannes.msoplaner.R;


public class LessonFragment extends Fragment {

    public static final String ARG_INDEX = "ARG_INDEX";
    public static final String ARG_SBJNAME = "ARG_SBJNAME";
    public static final String ARG_PLACE = "ARG_PLACE";
    public static final String ARG_TEACHER = "ARG_TEACHER";
    public static final String ARG_TIME = "ARG_TIME";

    private int mIndex;
    private String mSubjectName;
    private String mPlace;
    private String mTeacher;
    private int mTime;

    public static LessonFragment newInstance(int index, String subjectName, String place, String teacher, /*int startHour, int startMin, int endHour, int endMin*/ int time) {
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        args.putString(ARG_SBJNAME, subjectName);
        args.putString(ARG_PLACE, place);
        args.putString(ARG_TEACHER, teacher);
        args.putInt(ARG_TIME, time);
        LessonFragment fragment = new LessonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndex = getArguments().getInt(ARG_INDEX);
        mSubjectName = getArguments().getString(ARG_SBJNAME);
        mPlace = getArguments().getString(ARG_PLACE);
        mTeacher = getArguments().getString(ARG_TEACHER);
        mTime = getArguments().getInt(ARG_TIME);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson, container, false);

        TextView sbjTV = (TextView) view.findViewById(R.id.subject);
        TextView teacherTV = (TextView) view.findViewById(R.id.teacher);
        TextView roomTV = (TextView) view.findViewById(R.id.room);

        sbjTV.setText(sbjTV.getText() + " " + mSubjectName);
        teacherTV.setText(teacherTV.getText() + " " + mTeacher);
        roomTV.setText(roomTV.getText() + " " + mPlace);

        return view;
    }

}