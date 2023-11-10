package by.evgen.fuelcalculator.model

import android.content.Context
import android.widget.EditText
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil

fun setFocus(context: Context, editText: EditText) { //sets focus and calls a virtual keyboard
    editText.requestFocus()
    UIUtil.showKeyboard(context, editText)
}