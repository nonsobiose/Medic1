package com.nonsobiose.medic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nonsobiose.medic.R;

import java.util.List;

import com.nonsobiose.medic.model.Medication;

/**
 * Created by Chidiebere on 4/4/2018.
 */

public class MedicationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Medication> mMedications;
    private static final int MEDICATION = 1;
    private static final int MONTH = 0;

    private Context mContext;

    int medicationMonth;

    int numbers[] = new int[] {};


    public MedicationAdapter(List<Medication> medications, Context mContext) {
        this.mMedications = medications;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case MEDICATION:
                View medicationView = LayoutInflater.from(mContext).inflate(R.layout.medication_item, parent, false);
                viewHolder = new MedicationItemViewHolder(medicationView);
                break;
            case MONTH:
                View monthView = LayoutInflater.from(mContext).inflate(R.layout.medication_month, parent, false);
                viewHolder = new MedicationMonthViewHolder(monthView);
                break;
            default:
                throw new IllegalStateException("Medication view or Medication month cant be inflated");
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Medication medication = mMedications.get(position);
        switch (holder.getItemViewType()) {
            case MEDICATION:
                ((MedicationItemViewHolder) holder).mMedicationName.setText(medication.getmName());
                ((MedicationItemViewHolder) holder).mStartDate.setText(medication.getmStartDate());
                ((MedicationItemViewHolder) holder).mEndDate.setText(medication.getmEndDate());
                break;
            case MONTH:
                medicationMonth = Integer.parseInt(medication.getmStartDate().split("-")[1]);
                String currentMonth = Medication.MEDICATION_MONTH[medicationMonth];
                ((MedicationMonthViewHolder) holder).mMedicationMonth.setText(currentMonth);
                break;
            default:
                throw new IllegalStateException("Medication view or Medication month cant be bound");
        }
    }

    @Override
    public int getItemCount() {
        return this.mMedications.size();
    }

    @Override
    public int getItemViewType(int position) {

        Medication medication = mMedications.get(position);
        int currentMedicationMonth = Integer.parseInt(medication.getmStartDate().split("-")[1]);


        return MEDICATION;
    }

    public void swapAdapter(List<Medication> medications) {
        mMedications = medications;
        notifyDataSetChanged();
    }

    public class MedicationItemViewHolder extends RecyclerView.ViewHolder {
        TextView mMedicationName;
        TextView mStartDate;
        TextView mEndDate;


        public MedicationItemViewHolder(View itemView) {
            super(itemView);
            mMedicationName = itemView.findViewById(R.id.medication_name);
            mStartDate = itemView.findViewById(R.id.medication_start_date);
            mEndDate = itemView.findViewById(R.id.medication_end_date);
        }

    }

    public class MedicationMonthViewHolder extends RecyclerView.ViewHolder {
        TextView mMedicationMonth;


        public MedicationMonthViewHolder(View itemView) {
            super(itemView);
            mMedicationMonth = itemView.findViewById(R.id.medication_month_title);
        }

    }
}
