package com.icecream.memo;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private static String TAG = "OneMemo";

    Button load, save, delete;

    EditText inputText;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        load = (Button) findViewById(R.id.load);

        save = (Button) findViewById(R.id.save);

        delete = (Button) findViewById(R.id.delete);

        inputText = (EditText) findViewById(R.id.inputText);


        load.setOnClickListener(listener);

        save.setOnClickListener(listener);

        delete.setOnClickListener(listener);

    }


    View.OnClickListener listener = new View.OnClickListener() {

        @Override

        public void onClick(View view) {

            switch (view.getId()) {

                case R.id.load:

                    Log.i("TAG", "load 진행");

                    FileInputStream fis = null;

                    try {

                        fis = openFileInput("OneMemo.txt");

                        byte[] data = new byte[fis.available()];

                        while (fis.read(data) != -1) {

                        }


                        inputText.setText(new String(data));

                        Toast.makeText(MainActivity.this, "불러왔습니다.", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {

                        e.printStackTrace();

                    } finally {

                        try {
                            if (fis != null) fis.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    break;

                case R.id.save:

                    Log.i("TAG", "save 진행");

                    FileOutputStream fos = null;

                    try {

                        fos = openFileOutput("OneMemo.txt", Context.MODE_PRIVATE);

                        String out = inputText.getText().toString();

                        fos.write(out.getBytes());

                        Toast.makeText(MainActivity.this, "저장 완료", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {

                        e.printStackTrace();

                    } finally {

                        try {
                            if (fos != null) fos.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    break;

                case R.id.delete:

                    Log.i("TAG", "delete 진행");

                    boolean b = deleteFile("OneMemo.txt");

                    if (b) {

                        Toast.makeText(MainActivity.this, "삭제 완료", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(MainActivity.this, "삭제 실패", Toast.LENGTH_SHORT).show();

                    }

                    break;

            }

        }

    };

}