package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.randerath.johannes.msoplaner.R;

import java.util.Date;

public class ExamFragment extends Fragment{
    public static final String ARG_INDEX = "ARG_INDEX";
    public static final String ARG_SUBJECT = "ARG_SUBJECT";
    public static final String ARG_DATE = "ARG_DATE";


    private int mIndex;
    private String mSubject;
    private String mDate;


    public static ExamFragment newInstance(int index, String subject, String date) {
        Bundle args = new Bundle();
        ExamFragment fragment = new ExamFragment();
        args.putInt(ARG_INDEX, index);
        args.putString(ARG_SUBJECT, subject);
        args.putString(ARG_DATE, date);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndex = getArguments().getInt(ARG_INDEX);
        mSubject = getArguments().getString(ARG_SUBJECT);
        mDate = getArguments().getString(ARG_DATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lesson, container, false);
        TextView subjectTV = (TextView) view.findViewById(R.id.subject);
        TextView dateTV = (TextView) view.findViewById(R.id.date);

        subjectTV.setText(subjectTV.getText() + mSubject);
        dateTV.setText(dateTV.getText() + mDate);

        return view;
    }
}
