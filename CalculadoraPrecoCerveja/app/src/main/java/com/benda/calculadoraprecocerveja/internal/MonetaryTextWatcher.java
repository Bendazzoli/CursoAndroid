package com.benda.calculadoraprecocerveja.internal;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.Locale;

public class MonetaryTextWatcher implements TextWatcher {

    private WeakReference<EditText> editTextRef;

    public MonetaryTextWatcher(EditText editText) {
        editTextRef = new WeakReference<>(editText);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable value) {
        EditText editText = editTextRef.get();

        if (editText != null) {
            editText.removeTextChangedListener(this);

            editText.setText(MonetaryHelper.formatToMonetary(value.toString()));
            editText.setSelection(editText.getText().length());
            editText.addTextChangedListener(this);
        }
    }
}
