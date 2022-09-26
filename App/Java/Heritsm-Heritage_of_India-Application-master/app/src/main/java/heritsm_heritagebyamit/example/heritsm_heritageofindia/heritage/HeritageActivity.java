package heritsm_heritagebyamit.example.heritsm_heritageofindia.heritage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import heritsm_heritagebyamit.example.heritsm_heritageofindia.R;

public class HeritageActivity extends AppCompatActivity {

    DatabaseReference RootRef;
    TextView place_namee,place_periodd,place_loc,place_desccc;
    LinearLayout place_go_to;
    ImageView imageView;
    String go_to_link = "";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heritage);
        Intent intent = getIntent();
        String key = intent.getStringExtra("ID");

        InitializeMethods();

        RetriveData(key);



        place_go_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToLink();
            }
        });


    }




    private void InitializeMethods() {


        progressDialog = new ProgressDialog( HeritageActivity.this);
        progressDialog.setContentView ( R.layout.loading );
        progressDialog.setTitle ( "Please Wait..." );
        progressDialog.setCanceledOnTouchOutside ( false );

        place_go_to = findViewById(R.id.place_name_go_to);
        place_namee = findViewById(R.id.place_name_detail);
        place_periodd = findViewById(R.id.place_name_period);
        place_loc = findViewById(R.id.place_name_location);
        place_desccc = findViewById(R.id.place_name_desc);
        imageView = findViewById(R.id.place_name_image);


        RootRef = FirebaseDatabase.getInstance ().getReference ().child("Sites");

    }

    private void GoToLink() {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(go_to_link));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.android.chrome");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            // Chrome browser presumably not installed so allow user to choose instead
            intent.setPackage(null);
            startActivity(intent);
        }

    }

    private void RetriveData(String key) {
        progressDialog.show();

        RootRef.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {

                    String place_name = dataSnapshot.child("place_name").getValue().toString();
                    String place_location = dataSnapshot.child("location").getValue().toString();
                    String place_period = dataSnapshot.child("period").getValue().toString();
                    String place_go_to = dataSnapshot.child("go_to").getValue().toString();
                    String place_description = dataSnapshot.child("description").getValue().toString();
                    String place_image = dataSnapshot.child("image").getValue().toString();


                    place_namee.setText(place_name);
                    place_periodd.setText(place_period);
                    place_loc.setText(place_location);
                    go_to_link = place_go_to;

                    place_desccc.setText(place_description);
                    Picasso.get().load(place_image).into(imageView);
                    progressDialog.dismiss();




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}