package com.example.shotlist.w_project.events

import androidx.fragment.app.Fragment
import com.example.shotlist.base_mvi.BaseEvent
import com.google.android.material.datepicker.MaterialDatePicker

class DatePickerDialogEvent() : BaseEvent {
    override fun performEvent(fragment: Fragment) {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setTitleText("Select date")
                .build()

        datePicker.show(fragment.parentFragmentManager,"tag")
        datePicker.selection


        // Makes only dates from today forward selectable.
//        val constraintsBuilder =
//            CalendarConstraints.Builder()
//                .setValidator(DateValidatorPointForward.now)
    }
}