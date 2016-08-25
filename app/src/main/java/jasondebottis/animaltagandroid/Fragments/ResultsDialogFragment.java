package jasondebottis.animaltagandroid.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import jasondebottis.animaltagandroid.R;
import jasondebottis.animaltagandroid.databinding.ResultsDialogFragmentBinding;

/**
 * Created by jasondebottis on 8/19/16.
 */

public class ResultsDialogFragment extends DialogFragment {
    private byte[] mBytes;
    private ResultsDialogFragmentBinding mBinding;

    public static ResultsDialogFragment newInstance(byte[] inImageBytes) {
        ResultsDialogFragment fragment = new ResultsDialogFragment();
        fragment.mBytes = inImageBytes;
        return fragment;
    }

    public ResultsDialogFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.results_dialog_fragment, null, true);
        Bitmap bitmap = BitmapFactory.decodeByteArray(mBytes, 0, mBytes.length);
        mBinding.resultImageView.setImageBitmap(bitmap);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(mBinding.getRoot());
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface inDialogInterface, int inI) {
                dismiss();
            }
        });
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface inDialogInterface, int inI) {
                dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }
}
