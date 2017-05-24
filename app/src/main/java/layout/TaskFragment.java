package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.randerath.johannes.msoplaner.R;

/**
 * UI Design class to visualize a single homework. Used in HomeworkActivity
 */

public class TaskFragment extends Fragment{

    public static final String ARG_SUBJECT = "ARG_SUBJECT";
    public static final String ARG_DUEDATE = "ARG_DUEDATE";
    public static final String ARG_DESCRIPTION = "ARG_DESCRIPTION";

    private String mSubject;
    private String mDueDate;
    private String mDescription;


    /**
     * Create new ExamFragment
     * @param subject subject as com.randerath.johannes.msoplaner.Subject
     * @param duedate Duedate as dd.mm.yyyy
     * @param description description as String
     * @return new TaskFragment
     */

    public static TaskFragment newInstance(String subject, String duedate, String description) {
        Bundle args = new Bundle();
        args.putString(ARG_SUBJECT, subject);
        args.putString(ARG_DUEDATE, duedate);
        args.putString(ARG_DESCRIPTION, description);
        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubject = getArguments().getString(ARG_SUBJECT);
        mDueDate = getArguments().getString(ARG_DUEDATE);
        mDescription = getArguments().getString(ARG_DESCRIPTION);
    }

    /**
     * Called when fragment is actually shown.
     * @return parent view containing new TaskFragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View parent = inflater.inflate(R.layout.fragment_homework_exercise, container, false);

        TextView subjectTV = (TextView) parent.findViewById(R.id.subject);
        TextView dueDateTV = (TextView) parent.findViewById(R.id.until);
        TextView descriptionTV = (TextView) parent.findViewById(R.id.exercise);

        subjectTV.setText(mSubject);
        dueDateTV.setText(dueDateTV.getText() + " " + mDueDate);
        descriptionTV.setText(mDescription);

        return parent;

    }

}