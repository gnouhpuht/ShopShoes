package com.ghtk.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ghtk.common.R;
import com.ghtk.ui.Views.GhtkTextView;

public class ErrorMessageDialog {

    private String msg = "";

    public ErrorMessageDialog setMessage(String message) {
        this.msg = message;
        return this;
    }

    private IFDialogCallBackListener<String> dialogCallback;

    public ErrorMessageDialog setDialogCallback(IFDialogCallBackListener<String> listener) {
        this.dialogCallback = listener;
        return this;
    }

    private int deviceWidth = 0;
    private float widthPerDevice = 0.8f;
    private Context context;
    private int width = 0;

    private ErrorMessageDialog() {

    }

    public static ErrorMessageDialog doCreate(Context mContext, WindowManager manager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        ErrorMessageDialog dialog = new ErrorMessageDialog();

        dialog.deviceWidth = displayMetrics.widthPixels;
        dialog.context = mContext;

        return dialog;
    }

    public ErrorMessageDialog setWidthPercent(float percent) {
        this.widthPerDevice = percent;
        return this;
    }

    public void show() {
        if (this.context == null) {
            return;
        }
        final Dialog dialogMessage = new Dialog(context);
        dialogMessage.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogMessage.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialogMessage.setContentView(R.layout.layout_dialog_message);
        if (!msg.equals("")) {
            GhtkTextView txtMessageContent = dialogMessage.findViewById(R.id.layout_dialog_message_txt_content);
            txtMessageContent.setText(msg);

            GhtkTextView btnClose = dialogMessage.findViewById(R.id.layout_dialog_message_btn_close);
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogMessage.dismiss();
                }
            });

            dialogMessage.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    if (dialogCallback != null) {
                        dialogCallback.onDataResult("");
                    }
                }
            });

            if (dialogMessage != null) {
                dialogMessage.show();
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(dialogMessage.getWindow().getAttributes());
                width = (int) (deviceWidth * widthPerDevice);
                layoutParams.width = width;
                dialogMessage.getWindow().setAttributes(layoutParams);
            }
        }
    }

    public interface IFDialogCallBackListener<T> {
        void onDataResult(T dataObject);
    }

}
