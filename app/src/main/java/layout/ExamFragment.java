package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.randerath.johannes.msoplaner.R;

import java.util.Date;

/**
 * UI Design class to visualize a single exam. Used in ExamActivity
 */

public class ExamFragment extends Fragment{
    public static final String ARG_INDEX = "ARG_INDEX";
    public static final String ARG_SUBJECT = "ARG_SUBJECT";
    public static final String ARG_DATE = "ARG_DATE";


    private int mIndex;
    private String mSubject;
    private String mDate;

    /**
     * Create new ExamFragment
     * @param index position in the list
     * @param subject subject as com.randerath.johannes.msoplaner.Subject
     * @param date date as dd.mm.yyyy
     * @return new ExamFragment
     */

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
        //assign variables from arguments
        mIndex = getArguments().getInt(ARG_INDEX);
        mSubject = getArguments().getString(ARG_SUBJECT);
        mDate = getArguments().getString(ARG_DATE);
    }

    /**
     * Called when fragment is actually shown.
     * @return parent view containing new ExamFragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam, container, false);
        TextView subjectTV = (TextView) view.findViewById(R.id.subject);
        TextView dateTV = (TextView) view.findViewById(R.id.dateTV);

        subjectTV.setText(mSubject + " " + subjectTV.getText());
        dateTV.setText(dateTV.getText() + " " + mDate);

        return view;
    }
}
