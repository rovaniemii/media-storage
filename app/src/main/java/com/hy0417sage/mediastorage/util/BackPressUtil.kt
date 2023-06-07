package com.hy0417sage.mediastorage.util

import android.app.Activity
import android.widget.Toast
import com.hy0417sage.mediastorage.R

class BackPressUtil(private val activity: Activity) {
    private var backKeyPressedTime = 0L
    private var toast: Toast? = null

    fun onBackPressed() {
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            toast!!.cancel()
            activity.finish()
        } else {
            backKeyPressedTime = System.currentTimeMillis()
            showToast()
        }
    }

    private fun showToast() {
        toast =
            Toast.makeText(activity, activity.getString(R.string.go_to_pre), Toast.LENGTH_SHORT)
        toast!!.show()
    }
}