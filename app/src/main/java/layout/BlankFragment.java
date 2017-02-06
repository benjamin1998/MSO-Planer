package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.benjaminsalamon.msoplaner.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SUBJECT = "param1";
    private static final String ARG_DATE = "param2";
    private static final String ARG_NOTES = "param3";
    private static final String ARG_COLOR = "param4";

    // TODO: Rename and change types of parameters
    private String mSubject;
    private String mDate;
    private String mNotes;
    private int mColor;

    private OnFragmentInteractionListener mListener;

    private Button color;
    private TextView subject;
    private TextView date;
    private TextView notes;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param subject Subject.
     * @param place Place.
     * @param teacher Teacher.
     * @param index Index.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String subject, String date, String notes, int color) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SUBJECT, subject);
        args.putString(ARG_DATE, date);
        args.putString(ARG_NOTES, notes);
        args.putInt(ARG_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSubject = getArguments().getString(ARG_SUBJECT);
            mDate = getArguments().getString(ARG_DATE);
            mNotes = getArguments().getString(ARG_NOTES);
            mColor = getArguments().getInt(ARG_COLOR);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mSubject = getArguments().getString("ARG_SUBJECT");
        mDate = getArguments().getString("ARG_DATE");
        mNotes = getArguments().getString("ARG_NOTES");
        mColor = getArguments().getInt("ARG_COLOR");

        View rootView = inflater.inflate(R.layout.fragment_exam, container, false);
        color = (Button) rootView.findViewById(R.id.color);
        subject = (TextView) rootView.findViewById(R.id.subject);
        subject.setText(getResources().getString(R.string.subject) + " " + mSubject);
        date = (TextView) rootView.findViewById(R.id.date);
        date.setText(getResources().getString(R.string.date) + " " + mDate);
        notes = (TextView) rootView.findViewById(R.id.notes);
        notes.setText(getResources().getString(R.string.notes) + " " + mNotes);
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
