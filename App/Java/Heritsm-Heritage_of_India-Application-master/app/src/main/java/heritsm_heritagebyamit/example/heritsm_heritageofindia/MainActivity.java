package heritsm_heritagebyamit.example.heritsm_heritageofindia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import heritsm_heritagebyamit.example.heritsm_heritageofindia.heritage.HeritageActivity;
import heritsm_heritagebyamit.example.heritsm_heritageofindia.heritage.HeritageListActivity;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4;
    TextView contact,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout1 = findViewById(R.id.main_page_1);
        linearLayout2 = findViewById(R.id.main_page_2);
        linearLayout3 = findViewById(R.id.main_page_3);
        linearLayout4 = findViewById(R.id.main_page_4);
        about = findViewById(R.id.about_us);
        contact = findViewById(R.id.contact_us);
        
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inURL = "https://www.linkedin.com/in/maityamit/";
                Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( inURL ) );

                startActivity( browse );
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inURL = "https://www.linkedin.com/in/maityamit/";
                Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( inURL ) );

                startActivity( browse );
            }
        });

        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateaListactivate("3");
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateaListactivate("4");
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateaListactivate("16");
            }
        });
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateaListactivate("0");
            }
        });


        relativeLayout = findViewById(R.id.heritage_all);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HeritageListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CreateaListactivate(String s) {

        Intent intent1 = new Intent(MainActivity.this, HeritageActivity.class);
        intent1.putExtra("ID",s);
        startActivity(intent1);

    }
}