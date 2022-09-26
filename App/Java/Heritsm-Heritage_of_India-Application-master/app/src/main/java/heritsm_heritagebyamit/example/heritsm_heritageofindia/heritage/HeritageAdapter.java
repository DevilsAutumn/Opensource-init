package heritsm_heritagebyamit.example.heritsm_heritageofindia.heritage;

import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import heritsm_heritagebyamit.example.heritsm_heritageofindia.R;

public class HeritageAdapter extends RecyclerView.Adapter<HeritageAdapter.StudentViewHolder2> {

    // List of goals, goals are stored as a pair of the key of the goal and the goal object
    ArrayList<Pair<String, productModel>> goalList;
    HeritageListActivity fragment;


    HeritageAdapter(HeritageListActivity fragment, ArrayList<Pair<String,productModel>> goalList)
    {
        this.fragment=fragment;
        this.goalList=goalList;
    }



    @NonNull
    @Override
    public StudentViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fragment).inflate(R.layout.heritage_site_layout,parent,false);
        return new StudentViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder2 holder, int position) {


        // Key of the goal is the first element of the pair
        String listPostKey = goalList.get(position).first;
        DatabaseReference db = fragment.RootRef.child(listPostKey);
        StringBuilder sb = new StringBuilder();
        StringBuilder retriveBreakEndDate = new StringBuilder();
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                holder.prDate.setText(snapshot.child("period").getValue().toString());
                holder.prName.setText(snapshot.child("place_name").getValue().toString());
                holder.prState.setText(snapshot.child("location").getValue().toString());
                Picasso.get().load(snapshot.child("image").getValue().toString()).into(holder.prImg);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fragment, HeritageActivity.class);
                intent.putExtra("ID", listPostKey);
                fragment.startActivity(intent);
            }
        });


    }



    @Override
    public int getItemCount() {
        return goalList.size();
    }

    // Changing the list when searching is carried out and notifying the change
    public void setGoalList(ArrayList<Pair<String,productModel>> list)
    {
        goalList= list;
        notifyDataSetChanged();
    }


    public static class StudentViewHolder2 extends  RecyclerView.ViewHolder
    {

        TextView prName,prDate,prState;
        ImageView prImg;
        public StudentViewHolder2(@NonNull View itemView) {
            super ( itemView );
            prName = itemView.findViewById(R.id.place_name);
            prState = itemView.findViewById(R.id.place_state);
            prDate = itemView.findViewById(R.id.place_date);
            prImg = itemView.findViewById(R.id.place_image);
        }
    }


}

