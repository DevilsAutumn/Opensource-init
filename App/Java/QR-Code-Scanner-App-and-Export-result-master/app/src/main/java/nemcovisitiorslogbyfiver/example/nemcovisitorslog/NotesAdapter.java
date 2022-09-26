package nemcovisitiorslogbyfiver.example.nemcovisitorslog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<Fav> courseModalArrayList = new ArrayList<>();
    private Context context;

    // creating a constructor for our variables.
    public NotesAdapter(ArrayList<Fav> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.save_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        Fav modal = courseModalArrayList.get(position);
        holder.details.setText(modal.getName());


        holder.delte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.courseModalArrayList.remove(position);
                notifyItemRemoved(position);



                SharedPreferences preferences = context.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor mEditor = preferences.edit();
                Gson gson = new Gson();

                String jsonString = gson.toJson(courseModalArrayList);
                mEditor.putString("courses", jsonString);
                mEditor.apply();
            }



        });


    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our views.
        private TextView details;
        private ImageView delte;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids.
            details = itemView.findViewById(R.id.fav_user_details);
            delte = itemView.findViewById(R.id.fav_user_delete);

        }
    }
}