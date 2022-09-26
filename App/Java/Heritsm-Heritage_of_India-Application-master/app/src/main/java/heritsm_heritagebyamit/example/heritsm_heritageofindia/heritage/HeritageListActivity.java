package heritsm_heritagebyamit.example.heritsm_heritageofindia.heritage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import heritsm_heritagebyamit.example.heritsm_heritageofindia.R;

public class HeritageListActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    DatabaseReference RootRef;
    GridLayoutManager gridLayoutManager;
    ProgressDialog progressDialog;
    ImageView cart_butoon;
    String string;
    HeritageAdapter goalAdapter;
    EditText goalSearch;
    ArrayList<Pair<String, productModel>> goalList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heritage_list);

        progressDialog = new ProgressDialog( HeritageListActivity.this);
        progressDialog.setContentView ( R.layout.loading );
        progressDialog.setTitle ( "Please Wait..." );
        progressDialog.setCanceledOnTouchOutside ( false );
        RootRef = FirebaseDatabase.getInstance ().getReference ().child("Sites");
        recyclerView = findViewById(R.id.recycler_view_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(HeritageListActivity.this));
        goalSearch = findViewById(R.id.goal_search);




        // Carrying out search when text is added to the goalSearch
        goalSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                // Creating new list of goals based on the entered value in the goalSearch
                ArrayList<Pair<String,productModel>> newList = new ArrayList<>();
                for(int i=0;i<goalList.size();i++)
                {
                    productModel item = goalList.get(i).second;

                    // Checking if the entered text matches with the goal name
                    if(item.getPlace_name().toLowerCase().contains(editable.toString().toLowerCase()))
                    {
                        newList.add(goalList.get(i));
                    }
                }

                if(!newList.isEmpty()){

                    goalAdapter.setGoalList(newList);

                }


                // Making the no result text visible based on the size of the new list
                if(!editable.toString().isEmpty() && newList.size()==0 && !(goalList.size() ==0))
                {
                    Toast.makeText(HeritageListActivity.this, "No Results !", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


    @Override
    public void onStart() {
        super.onStart ();

        progressDialog.show();
        recyclerView.setVisibility(View.GONE);
        RootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // Getting the latest list of goals and updating the goalList
                ArrayList<Pair<String,productModel>> currList = new ArrayList<>();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    productModel goal = dataSnapshot.getValue(productModel.class);
                    currList.add(new Pair<>(dataSnapshot.getKey(),goal ));
                }
                goalList = currList;

                // Updating the adapter with the new goal list
                goalAdapter.setGoalList(goalList);

                // Making the recycler view appear and the loading dialog disappear
                progressDialog.dismiss();
                recyclerView.setVisibility(View.VISIBLE);

                // Making the empty goal message visible when goal list is empty
//                if(goalList.size()==0) emptyGoal.setVisibility(View.VISIBLE);
//                else emptyGoal.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        goalAdapter = new HeritageAdapter(this,goalList);
        recyclerView.setAdapter ( goalAdapter );
        goalSearch.setText("");

    }



}