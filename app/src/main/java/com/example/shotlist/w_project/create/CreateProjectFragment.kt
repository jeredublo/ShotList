package com.example.shotlist.w_project.create

import android.view.View
import android.widget.Button
import com.example.shotlist.R
import com.example.shotlist.base_mvi.BaseAction
import com.example.shotlist.base_mvi.MVIFragment
import com.example.shotlist.w_project.data_structs.CreateProjectState
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CreateProjectFragment : MVIFragment<CreateProjectState, CreateProjectViewModel>(CreateProjectViewModel::class.java, R.layout.create_project_fragment) {
    override fun getInitAction(): BaseAction<CreateProjectState>? {
        return null     // no info needed for this screen
    }

    override fun initUI(view: View) {
        // init all the text fields and attach listeners

        view.findViewById<TextInputLayout>(R.id.date_field).setOnClickListener() { view ->
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
                    .setTitleText("Select date")
                    .build()

            datePicker.apply {
                show(this@CreateProjectFragment.requireActivity().supportFragmentManager,"DATE_PICKER")
                addOnPositiveButtonClickListener {
                    val date = this.headerText
//                    view.findViewById<TextInputEditText>(R.id.date_field_input).text = date
                }
            }
//            datePicker.show(fragmentManager, "tag")

        }
    }

    override fun renderUI(newState: CreateProjectState) {

    }
}