package com.svit.epolice.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.svit.epolice.R;
import com.svit.epolice.utilities.DataValidation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PasswordResetDialog extends DialogFragment {

    private Context mContext;
    private Button submitBTN;
    private EditText emailET;
    private ProgressBar resetPasswordPB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        View view = inflater.inflate(R.layout.dialog_password_reset, container, false);
        emailET = view.findViewById(R.id.password_reset_input_email);
        submitBTN = view.findViewById(R.id.password_reset_submit_button);
        resetPasswordPB = view.findViewById(R.id.reset_password_progressbar);
        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPasswordPB.setVisibility(View.VISIBLE);
                String email = emailET.getText().toString();
                if (DataValidation.isValidEmail(email)) {
                    sendPasswordResetEmail(email);
                } else {
                    Toast.makeText(
                            mContext
                            , "Invalid email"
                            , Toast.LENGTH_LONG
                    ).show();
                    resetPasswordPB.setVisibility(View.INVISIBLE);
                }
            }
        });
        //   getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        //   getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return view;
    }

    private void sendPasswordResetEmail(String email) {


        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: Password Reset Email sent.");
                            Toast.makeText(mContext, "Password Reset Link Sent to Email",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d(TAG, "onComplete: No user associated with that email.");
                            Toast.makeText(mContext, "No user is Associated with that Email",
                                    Toast.LENGTH_SHORT).show();
                        }
                        resetPasswordPB.setVisibility(View.INVISIBLE);
                    }
                });
    }

}

