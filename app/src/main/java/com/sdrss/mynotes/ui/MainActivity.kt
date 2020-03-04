package com.sdrss.mynotes.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.sdrss.mynotes.R
import com.sdrss.mynotes.network.NetworkHelper
import com.sdrss.mynotes.pojo.User
import com.sdrss.mynotes.pojo.UserResponse
import com.sdrss.mynotes.utils.isOnline
import com.sdrss.mynotes.utils.showDLog
import com.sdrss.mynotes.utils.showNotesAlertDialog
import com.sdrss.mynotes.utils.showVLog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private var user: User? = null

    private val adapter by lazy { NotesAdapter() }

    private var isUpdate: Boolean = false
    private var notesDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatingActionButton.setOnClickListener {
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

        loadData() //load Data From Api
    }

    private fun loadData() {

        if (isOnline()) {
            CoroutineScope(Dispatchers.IO).launch {
                val result = NetworkHelper.getUserListNetworkRequest()
                Log.d("result", result)
                runOnUiThread {
                    updateUI(result)
                    // tvData.text = result
                }
            }
        } else {
            //noInternet
        }

    }

    private fun updateUI(result: String) {
        val userResponse = Gson().fromJson<UserResponse>(result, UserResponse::class.java)
        rvNotes.adapter = adapter
        userResponse?.data?.let { adapter.swapData(it) }
        showDLog("$userResponse")
        showDLog("${userResponse?.data}")
    }

    private fun updateData(user: User?) {

    }

    private fun insertData(user: User?) {

    }
}
