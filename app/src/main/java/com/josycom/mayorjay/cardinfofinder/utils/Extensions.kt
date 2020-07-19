package com.josycom.mayorjay.cardinfofinder.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.snack(
    message: String?,
    length: Int = Snackbar.LENGTH_SHORT,
    actionText: String? = null,
    actionCallBack: (() -> Unit)? = null
) {
    Snackbar.make(this, message.toString(), length).apply {
        actionCallBack?.let {
            actionText?.let {
                setAction(actionText) {
                    actionCallBack()
                }
            }
        }
    }.show()
}