package nemcovisitiorslogbyfiver.example.nemcovisitorslog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView scan,save;



    static NotesAdapter adapter ;
    static ArrayList<Fav> courseModalArrayList;

    static SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scan = findViewById(R.id.scan_image);
        save = findViewById(R.id.save_image);







        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);


        Gson gson = new Gson();

        String json = sharedPreferences.getString("courses", null);

        Type type = new TypeToken<ArrayList<Fav>>() {}.getType();


        courseModalArrayList = gson.fromJson(json, type);


        if (courseModalArrayList == null) {
            courseModalArrayList = new ArrayList<>();
        }



        adapter = new NotesAdapter(courseModalArrayList, MainActivity.this);













        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentscan = new Intent(MainActivity.this,QRCodeScan.class);
                startActivity(intentscan);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentscan = new Intent(MainActivity.this,ResultShow.class);
                startActivity(intentscan);
            }
        });

    }
}