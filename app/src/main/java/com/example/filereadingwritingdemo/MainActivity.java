package com.example.filereadingwritingdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonReadInternal = (Button) findViewById(R.id.readInternalButton);
        Button buttonWriteInternal = (Button) findViewById(R.id.writeInternalButton);
        Button buttonReadExternal = (Button) findViewById(R.id.readExternalButton);
        Button buttonWriteExternal = (Button) findViewById(R.id.writeExternalButton);

        buttonReadInternal.setOnClickListener(this);
        buttonWriteInternal.setOnClickListener(this);
        buttonReadExternal.setOnClickListener(this);
        buttonWriteExternal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.readInternalButton:
                try {
                    readInternalStorage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.writeInternalButton:
                try {
                    writeInternalStorage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.readExternalButton:
                try {
                    readExternalStorage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.writeExternalButton:
                try {
                    writeExternalStorage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    private void readInternalStorage() throws IOException {
        FileInputStream inputStream = openFileInput("sample.txt");
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(streamReader);
        String message = reader.readLine();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void writeInternalStorage() throws IOException {

        String message = "These are sample contents written to internal file";

        FileOutputStream outputStream = openFileOutput("sample.txt", Context.MODE_PRIVATE);
        outputStream.write(message.getBytes());
        outputStream.close();

        Toast.makeText(this, "Write Successful", Toast.LENGTH_LONG).show();
    }

    private void readExternalStorage() throws IOException {

        File file = new File(Environment.getExternalStorageDirectory(), "sample.txt");
        FileInputStream inputStream = new FileInputStream(file);

        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(streamReader);

        String message = bufferedReader.readLine();

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    private void writeExternalStorage() throws IOException {
        String message = "These are the contents which would be written to external file";

        File file = new File(Environment.getExternalStorageDirectory(), "sample.txt");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(message.getBytes());
        outputStream.close();

        Toast.makeText(this, "Write Successful", Toast.LENGTH_LONG).show();
    }
}
