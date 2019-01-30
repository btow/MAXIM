package ru.bww.app.testtask.ui.dialogs

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import ru.bww.app.testtask.R

class PdLoading(val context : Context) {
    val pdLoading = ProgressDialog(context)

    fun openDialog() = {
        pdLoading.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pdLoading.setCancelable(false)
        pdLoading.show()
        pdLoading.setContentView(R.layout.progress_bar)
    }

    fun closeDialog() = pdLoading.dismiss()
}

