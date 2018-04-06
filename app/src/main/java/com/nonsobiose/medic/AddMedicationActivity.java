package com.nonsobiose.medic;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nonsobiose.medic.DatabaseUtils.MedicationDatabase;
import com.nonsobiose.medic.model.Medication;

import java.util.Calendar;
import java.util.Date;

public class AddMedicationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText mMedicationNameEditText;
    private EditText mMedicationDescriptionEditText;
    private EditText mMedicationIntervalNumberEditText;
    private Spinner mMedicationIntervalTypeSpinner;
    private Button mMedicationStartDateButton;
    private Button mMedicationEndDateButton;
    private FloatingActionButton mAddMedicationButton;

    private static boolean sIsStartDateSelected = false;
    private DialogFragment mDialogFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med);

        // Initialize the views
        mMedicationNameEditText = findViewById(R.id.editText_medication_name);
        mMedicationDescriptionEditText = findViewById(R.id.editText_medication_description);
        mMedicationIntervalNumberEditText = findViewById(R.id.editText_medication_interval_number);
        mMedicationIntervalTypeSpinner = findViewById(R.id.spinner_medication_interval);
        mMedicationStartDateButton = findViewById(R.id.button_medication_start_date);
        mMedicationEndDateButton = findViewById(R.id.button_medication_end_date);
        mAddMedicationButton = findViewById(R.id.fab_add_medication);

        // Setup click listeners for mMedicationStartDateButton and mMedicationEndDateButton to select date
        mDialogFragment = new DatePickerFragment();

        mMedicationStartDateButton.setOnClickListener(view -> {
            sIsStartDateSelected = true;
            mDialogFragment.show(getSupportFragmentManager(), "datePicker");
        });

        mMedicationEndDateButton.setOnClickListener(view -> {
            sIsStartDateSelected = false;
            mDialogFragment.show(getSupportFragmentManager(), "datePicker");
        });

        // Setup click listeners for mAddMedicationButton to add add new medication
        mAddMedicationButton.setOnClickListener(view -> {
            String medicationName = mMedicationNameEditText.getText().toString().trim();
            String medicationDescription = mMedicationDescriptionEditText.getText().toString().trim();
            String medicationIntervalNumber = mMedicationIntervalNumberEditText.getText().toString().trim();
            String medicationIntervalType = mMedicationIntervalTypeSpinner.getSelectedItem().toString();
            String medicationStartDate = mMedicationStartDateButton.getText().toString();
            String medicationEndDate = mMedicationEndDateButton.getText().toString();
            boolean newMedicationAdded = Medication.AddNewMedication(medicationName, medicationDescription,
                    medicationStartDate,
                    medicationEndDate,
                    medicationIntervalNumber,
                    medicationIntervalType,
                    this);
            if(newMedicationAdded) finish();

        });
    }

    public static class DatePickerFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the current date as the default date in the picker
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String currentSetDate = dayOfMonth + "-" + month + "-" + year;
        if(sIsStartDateSelected) mMedicationStartDateButton.setText(currentSetDate);
        else if(!sIsStartDateSelected) mMedicationEndDateButton.setText(currentSetDate);

    }


}
