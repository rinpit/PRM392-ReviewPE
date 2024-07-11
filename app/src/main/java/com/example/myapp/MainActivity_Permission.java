package com.example.myapp;

import android.os.Bundle;
import android.content.pm.PackageManager;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.content.ContextCompat;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import android.widget.Toast;


public class MainActivity_Permission extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Code khởi chạy hệ thống
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_permission);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Check if the permission is already granted
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            // Permission is already granted, you can perform the related task
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
        } else {
            // Request the permission
            requestPermissionLauncher.launch(android.Manifest.permission.READ_CONTACTS);
        }
    }
    // Request the permission
    private final ActivityResultLauncher<String>
            requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. You can perform therelated task
                    Toast.makeText(MainActivity_Permission.this, "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    // Permission is denied. Show a message to the user
                    Toast.makeText(MainActivity_Permission.this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            });
}
