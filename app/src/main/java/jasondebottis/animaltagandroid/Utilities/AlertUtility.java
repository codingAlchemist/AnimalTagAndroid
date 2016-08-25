package jasondebottis.animaltagandroid.Utilities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import jasondebottis.animaltagandroid.R;
import jasondebottis.animaltagandroid.databinding.DialogFragmentBinding;


public class AlertUtility extends DialogFragment {
    private DialogFragmentBinding mBinding;
    public static final String DIALOG_TAG = "DialogTag";
    private String mMessage;

    public static AlertUtility NewInstance(String inMessage) {
        AlertUtility alertUtility = new AlertUtility();
        alertUtility.mMessage = inMessage;
        return alertUtility;
    }

    public AlertUtility() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_fragment, null, true);
        mBinding.alertMessageTextView.setText(mMessage);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(mBinding.getRoot());
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }


}
