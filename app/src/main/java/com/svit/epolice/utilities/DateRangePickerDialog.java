package com.svit.epolice.utilities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;
import com.svit.epolice.R;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DateRangePickerDialog extends DialogFragment {


    private static final String TAG = "DateRangePickerDialog";
    public OnInputListener mOnInputListener;
    Calendar mStartDate;
    Calendar mEndDate;
    DateRangeCalendarView calendar;
    TextView acationOk;
    TextView actionCancel;

    public DateRangePickerDialog() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_date_picker, container, false);

        calendar = view.findViewById(R.id.calendar);
        acationOk = view.findViewById(R.id.actionOk);
        actionCancel = view.findViewById(R.id.actionCancel);
        calendar.setCalendarListener(new DateRangeCalendarView.CalendarListener() {
            @Override
            public void onFirstDateSelected(Calendar startDate) {
                //Toast.makeText(getContext(), "Start Date: " + startDate.getTime().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDateRangeSelected(Calendar startDate, Calendar endDate) {
                // Toast.makeText(getContext(), "Start Date: " + startDate.getTime().toString() + " End date: " + endDate.getTime().toString(), Toast.LENGTH_SHORT).show();
                mStartDate = startDate;
                mEndDate = endDate;
            }
        });


        actionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        acationOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mStartDate != null && mEndDate != null) {
                    mOnInputListener.sendInput(mStartDate, mEndDate);
                    getDialog().dismiss();
                } else {
                    Toast.makeText(getContext(), "date select kar le bhai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnInputListener = (OnInputListener) getActivity();
        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage());
        }
    }

    public interface OnInputListener {
        void sendInput(Calendar startDate, Calendar endDate);
    }

}

