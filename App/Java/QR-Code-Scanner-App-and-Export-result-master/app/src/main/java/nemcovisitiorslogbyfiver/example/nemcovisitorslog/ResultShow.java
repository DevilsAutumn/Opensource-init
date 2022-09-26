package nemcovisitiorslogbyfiver.example.nemcovisitorslog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVWriter;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static android.content.ContentValues.TAG;

public class ResultShow extends AppCompatActivity {


    private RecyclerView recyclerView;

    Button button;

    final private int REQUEST_CODE_PERMISSION = 111;


    // variable for our adapter class and array list


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_show);
        button = findViewById(R.id.export_data);
        recyclerView = findViewById(R.id.fav_list_view);






        if (MainActivity.adapter.getItemCount()!=0){
            recyclerView.setVisibility(View.VISIBLE);
        }


        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);


        recyclerView.setLayoutManager(manager);


        recyclerView.setAdapter(MainActivity.adapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
createPDF();
            }
        });



    }

    private void createPDF() {
        int hasWritePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWritePermission != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    new AlertDialog.Builder(this)
                            .setMessage("Access Storage Permission")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .create()
                            .show();
                    return;
                }
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
            }
            return;
        } else {
            File docPath = new File(Environment.getExternalStorageDirectory() + "/Documents");
            if (!docPath.exists()) {
                docPath.mkdir();
            }


            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String csv = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/Documents/"+timeStamp+".csv"); // Here csv file name is MyCsvFile.csv



//            CSVWriter writer = null;
//            try {
//                writer = new CSVWriter(new FileWriter(csv));
//
//                List<String[]> data = new ArrayList<String[]>();
//                data.add(new String[]{"Country", "Capital"});
//                data.add(new String[]{"India", "New Delhi"});
//                data.add(new String[]{"United States", "Washington D.C"});
//                data.add(new String[]{"Germany", "Berlin"});
//
//                writer.writeAll(data); // data is adding to csv
//
//                writer.close();
//
//            }catch (IOException e){
//                e.printStackTrace();
//            }
                    CSVWriter writer = null;
                    try {
                        writer = new CSVWriter(new FileWriter(csv));


                List<String[]> data = new ArrayList<String[]>();

                for(int i=0;i<MainActivity.adapter.getItemCount();i++){
                    Fav fav = MainActivity.courseModalArrayList.get(i);
                    data.add(new String[]{fav.getName()});
                }

                        writer.writeAll(data); // data is adding to csv

                        writer.close();

                        Snackbar snacbar = Snackbar.make(findViewById(android.R.id.content), "MyCsvFile.csv" + " Saved: " +csv.toString(), Snackbar.LENGTH_SHORT);
                snacbar.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

        }
    }



}