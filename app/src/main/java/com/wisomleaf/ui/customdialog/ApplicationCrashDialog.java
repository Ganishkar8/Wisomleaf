package com.wisomleaf.ui.customdialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.RequiresApi;
import com.wisomleaf.databinding.AppCrashedDialogBinding;
import com.wisomleaf.ui.activity.SplashScreen;

public class ApplicationCrashDialog extends Activity {

    private AppCrashedDialogBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature (Window.FEATURE_NO_TITLE);
        binding = AppCrashedDialogBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.appCrashedBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(ApplicationCrashDialog.this, SplashScreen.class));
                finish();
            }
        });
    }

}
