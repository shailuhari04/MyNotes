package com.sdrss.mynotes.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.sdrss.mynotes.R
import com.sdrss.mynotes.utils.BaseDialogHelper

class NotesDialogHelper(context: Context) : BaseDialogHelper() {

    //  dialog view
    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.dialog_layout, null)
    }

    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)

    //  name editText
    val etName: TextInputEditText by lazy {
        dialogView.findViewById<TextInputEditText>(R.id.etName)
    }

    //etAge edit text
    val etAge: TextInputEditText by lazy {
        dialogView.findViewById<TextInputEditText>(R.id.etAge)
    }

    //etEmail editText
    val etEmail: TextInputEditText by lazy {
        dialogView.findViewById<TextInputEditText>(R.id.etEmail)
    }

    //etPassword editText
    val etPassword: TextInputEditText by lazy {
        dialogView.findViewById<TextInputEditText>(R.id.etPassword)
    }
    //etPhone editText
    val etPhone: TextInputEditText by lazy {
        dialogView.findViewById<TextInputEditText>(R.id.etPhone)
    }

    //tvTitle textView
    val tvTitle: MaterialTextView by lazy {
        dialogView.findViewById<MaterialTextView>(R.id.tvTitle)
    }

    //close Button
    private val btnClose: MaterialButton by lazy {
        dialogView.findViewById<MaterialButton>(R.id.btnClose)
    }

    //  submit Button
    private val btnSubmit: MaterialButton by lazy {
        dialogView.findViewById<MaterialButton>(R.id.btnSubmit)
    }

    //  btnCloseClickListener with listener
    fun btnCloseClickListener(func: (() -> Unit)? = null) =
        with(btnClose) {
            setClickListenerToDialogIcon(func)
        }

    //  btnSubmitClickListener with listener
    fun btnSubmitClickListener(func: (() -> Unit)? = null) =
        with(btnSubmit) {
            setClickListenerToDialogIcon(func)
        }

    //  view click listener as extension function
    private fun View.setClickListenerToDialogIcon(func: (() -> Unit)?) =
        setOnClickListener {
            func?.invoke()
            dialog?.dismiss()
        }
}