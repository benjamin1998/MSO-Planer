package com.randerath.johannes.msoplaner;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Simple Dialog to pick date from calendar view.
 */

public class DatePickerDialogRandy extends DialogFragment implements android.app.DatePickerDialog.OnDateSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar cal = Calendar.getInstance();

        return new android.app.DatePickerDialog(getActivity(), this, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Perform action when user chose date.
     */

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}