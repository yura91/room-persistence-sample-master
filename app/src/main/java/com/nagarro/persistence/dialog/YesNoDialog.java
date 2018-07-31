package com.nagarro.persistence.dialog;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.nagarro.persistence.R;
import com.nagarro.persistence.databinding.DialogYesNoBinding;

/**
 * Created by Dre on 10.11.2016.
 */

public class YesNoDialog extends BaseDialogFragment {

    public static final String TAG = "YesNoDialog";
    private static final String ARG_TEXT = "text";

    private DialogYesNoBinding dataBinding;
    private OnYesClickListener onYesClickListener;
    private String text;


    public interface OnYesClickListener {
        void onYesClick();
    }

    public static YesNoDialog newInstance(String text) {
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);

        YesNoDialog fragment = new YesNoDialog();
        fragment.setArguments(args);
        return fragment;
    }

    public void setText(String text) {
        this.text = text;
        getArguments().putString(ARG_TEXT, text);
    }

    public void setOnYesClickListener(OnYesClickListener onYesClickListener) {
        this.onYesClickListener = onYesClickListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            text = getArguments().getString(ARG_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_yes_no, null, false);
        dataBinding.setPresenter(this);
        dataBinding.text.setText(text);

        Dialog dialog = getDialog();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);

        return dataBinding.getRoot();
    }

    public void onYesClick() {
        if (onYesClickListener != null) {
            onYesClickListener.onYesClick();
        }

        dismiss();
    }

    public void onNoClick() {
        dismiss();
    }
}
