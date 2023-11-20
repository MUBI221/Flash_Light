package com.example.flash_light;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Switch aSwitch;
    TextView tv_result;
    CameraManager cameraManager;
    String cameraId;
    String result;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aSwitch = findViewById(R.id.myswitch1);
        tv_result = findViewById(R.id.mytextViewid);
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                torch(isChecked);
            }
        });
    }

    private void torch(boolean isChecked) {
        try {
            String[] cameraIdList = cameraManager.getCameraIdList();
            if (cameraIdList.length > 0) {
                cameraId = cameraIdList[0];
                cameraManager.setTorchMode(cameraId, isChecked);
                result = isChecked ? "ON" : "OFF";
                tv_result.setText(result);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace(); // Log the exception for debugging
        }
    }
}
