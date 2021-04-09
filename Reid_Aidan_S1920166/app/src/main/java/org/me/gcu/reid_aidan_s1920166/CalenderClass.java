package org.me.gcu.reid_aidan_s1920166;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

// Reid_Aidan_S1920166

public class CalenderClass extends DialogFragment
{
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        Calendar cal = Calendar.getInstance();
        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener)getActivity(), cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_WEEK));
    }
}
