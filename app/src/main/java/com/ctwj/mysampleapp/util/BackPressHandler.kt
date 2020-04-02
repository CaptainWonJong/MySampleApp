package com.ctwj.mysampleapp.util

import android.app.Activity
import android.widget.Toast
import com.ctwj.mysampleapp.R
import com.ctwj.mysampleapp.ui.MainActivity

/**
 * @author CaptainWonJong@gmail.com
 */
class BackPressHandler(
    private val activity: Activity
) {
    private var backKeyPressedTime: Long = 0
    private lateinit var toast: Toast

    fun onBackPressed(msg: String = activity.resources.getString(R.string.msg_back_closed), time: Int = 2000) {
        if (System.currentTimeMillis() > backKeyPressedTime + time) {
            backKeyPressedTime = System.currentTimeMillis()
            toast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT).apply {
                show()
            }
            return
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + time) {
            toast.cancel()
            when(activity) {
                is MainActivity -> activity.finish()
            }
        }
    }
}