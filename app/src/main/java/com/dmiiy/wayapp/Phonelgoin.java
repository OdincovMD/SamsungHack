package com.dmiiy.wayapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dmiiy.wayapp.databinding.ActivityPhonelgoinBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class Phonelgoin extends AppCompatActivity {
    private ActivityPhonelgoinBinding binding;
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;
    private static final String TAG = "MAIN_TAG";
    private ProgressDialog pd;
    private FirebaseAuth mAu;
    private TextView back;
    private ProgressBar prBar;
    private EditText editn, editphone;
    private Button btnreg;
    private DatabaseReference mDatab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonelgoin);
        binding = ActivityPhonelgoinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.phoneLl.setVisibility(View.VISIBLE);
        binding.codeLl.setVisibility(View.GONE);
        mAu = FirebaseAuth.getInstance();

        pd = new ProgressDialog(this);
        pd.setTitle("Please wait");
        pd.setCanceledOnTouchOutside(false);
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
signInwithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                pd.dismiss();
                Toast.makeText(Phonelgoin.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                super.onCodeSent(verificationId, forceResendingToken);
                Log.d(TAG,"onCodeSent: "+verificationId);
                mVerificationId=verificationId;
                forceResendingToken=token;
                pd.dismiss();

                binding.codeLl.setVisibility(View.GONE);
                binding.codeLl.setVisibility(View.VISIBLE);
                Toast.makeText(Phonelgoin.this, "Verification code sent", Toast.LENGTH_SHORT).show();
                binding.codeSentDescription.setText("Please type the verification code \nto "+binding.phoneEt.getText().toString().trim());
            }
        };

        binding.phoneContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
String phone = binding.phoneEt.getText().toString().trim();
if (TextUtils.isEmpty(phone)){
    Toast.makeText(Phonelgoin.this, "Please enter your phone", Toast.LENGTH_SHORT).show();
}
else {
    startPhoneNumberVerification(phone);
}
            }
        });
      binding.resendCodeTv.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String phone = binding.phoneEt.getText().toString().trim();
              if (TextUtils.isEmpty(phone)){
                  Toast.makeText(Phonelgoin.this, "Please enter your phone", Toast.LENGTH_SHORT).show();
              }
              else {
                  resendVerificationCode(phone,forceResendingToken);
              }
          }
      });
      binding.codeSubmitBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String code = binding.codeEt.getText().toString().trim();
              if (TextUtils.isEmpty(code)){
                  Toast.makeText(Phonelgoin.this, "Please enter verification code", Toast.LENGTH_SHORT).show();
              }
              else{
                  verifyPhoneNumberWithCode(mVerificationId,code);
              }
          }
      });
    }

    private void startPhoneNumberVerification(String phone) {
        pd.setMessage("Verifying Phone Number");
        pd.show();

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAu)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(mCallbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void resendVerificationCode(String phone, PhoneAuthProvider.ForceResendingToken token) {
        pd.setMessage("Resending Code");
        pd.show();

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAu)
                        .setPhoneNumber(phone)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallbacks)
                        .setForceResendingToken(token)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        pd.setMessage("Verifying code");
        pd.show();
        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verificationId,code);
        signInwithPhoneAuthCredential(credential);
    }

    private void signInwithPhoneAuthCredential(PhoneAuthCredential credential) {
        pd.setMessage("Logging In");
        mAu.signInWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        pd.dismiss();
                        String phone = mAu.getCurrentUser().getPhoneNumber();
                        Toast.makeText(Phonelgoin.this, "Logged In as"+phone, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Phonelgoin.this,MainActivity.class));

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(Phonelgoin.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
}