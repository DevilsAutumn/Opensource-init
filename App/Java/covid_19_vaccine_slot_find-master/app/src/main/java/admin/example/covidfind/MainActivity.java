package admin.example.covidfind;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private EditText pinnum;
    private ImageView date_choose;
    private TextView button,date_show;
    String strdate="";
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pinnum  = findViewById(R.id.pin_input);
        date_choose = findViewById(R.id.image_view);
        button = findViewById(R.id.button);
        date_show = findViewById(R.id.date_show);


        Date d = new Date();
        int dmonth = d.getMonth();
        int dyear = d.getYear()+1900;//there is an addition of 1900 because java date subtracts 1900 from the current year

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                //Validation to check that date is not before current date
                if (year<dyear || year==dyear && monthOfYear<dmonth){
                    Toast.makeText(MainActivity.this, "Please Enter a valid Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                updateLabel();
            }

        };

        date_choose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string_pin = pinnum.getText().toString();
                //Validate if pin does not exceeds or is below 6 characters
                if (TextUtils.isEmpty(string_pin)||string_pin.length()!=6)
                {
                    Toast.makeText(MainActivity.this, "Enter Appropriate PIN", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent1 = new Intent(MainActivity.this,ResultShowActivity.class);
                    intent1.putExtra("PIN",string_pin);
                    intent1.putExtra("DATE",strdate);
                    startActivity(intent1);
                }
            }
        });





    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        strdate = sdf.format(myCalendar.getTime());

        date_show.setText(sdf.format(myCalendar.getTime()));
    }
}