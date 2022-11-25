package com.example.newloginapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar t = Calendar.getInstance();
        int hour = t.get(Calendar.HOUR_OF_DAY);
        int minute = t.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this,  hour, minute, false);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        PresensiActivity main = (PresensiActivity) getActivity();
        main.processTimePickerResult(i, i1);
    }
}