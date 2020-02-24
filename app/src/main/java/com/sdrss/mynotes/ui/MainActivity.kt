package com.sdrss.mynotes.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sdrss.mynotes.R
import com.sdrss.mynotes.User
import com.sdrss.mynotes.network.NetworkHelper
import com.sdrss.mynotes.utils.isOnline
import com.sdrss.mynotes.utils.showNotesAlertDialog
import com.sdrss.mynotes.utils.showVLog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private var user: User? = null

    private var isUpdate: Boolean = false
    private var notesDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatingActionButton.setOnClickListener {
            tvData.text = ""
            if (notesDialog == null)
                notesDialog = showNotesAlertDialog {

                    cancelable = false

                    if (!isUpdate) insertData(user = user) else updateData(user = user)

                    btnCloseClickListener {
                        showVLog("Notes Dialog Close Button clicked")
                    }

                    btnSubmitClickListener {
                        showVLog("Notes Dialog Submit Button clicked")
                    }
                }
            //  and showing
            notesDialog?.show()
        }

        if (isOnline()) {
            loadData() //load Data From Api
        } else {

        }
    }

    private fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = NetworkHelper.getNetworkRequest()
            Log.d("result", result)
            runOnUiThread {
                tvData.text = result
            }
        }

    }

    private fun updateData(user: User?) {

    }

    private fun insertData(user: User?) {

    }
}
