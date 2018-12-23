package project.himanshu.com.railyatri;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    static String pnrnumber, livetrainnumber, trainsource, traindestination, traindate;
    EditText pnrET, liveET, sourceET, destinationET;
    ImageView pnrSearch, liveSearch, trainSearch;
    RelativeLayout pnrRelativeLayout, liveRelativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pnrET = findViewById(R.id.pnredittext);
        pnrSearch = findViewById(R.id.pnrsearchpic);
        liveET = findViewById(R.id.liveedittext);
        liveSearch = findViewById(R.id.livesearchpic);
        sourceET = findViewById(R.id.trainfrom);
        destinationET = findViewById(R.id.trainTo);
        trainSearch = findViewById(R.id.trainsearchpic);
        pnrRelativeLayout = findViewById(R.id.pnrrelativelayout);
        liveRelativeLayout = findViewById(R.id.livestatusrelativelayout);

        pnrSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pnrnumber = pnrET.getText().toString();

                if(!pnrnumber.equals("")) {

                    startActivity(new Intent(Home.this,PnrActivity.class));

                } else {
                    Toast.makeText(Home.this, "PNR number can't be empty", Toast.LENGTH_SHORT).show();
                    pnrRelativeLayout.setBackgroundColor(Color.RED);

                }

            }
        });

        liveSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                livetrainnumber = liveET.getText().toString();

                if(!livetrainnumber.equals("")) {

                    startActivity(new Intent(Home.this,LiveStatusActivity.class));

                } else {

                    Toast.makeText(Home.this, "Train number can't be empty", Toast.LENGTH_SHORT).show();
                    liveRelativeLayout.setBackgroundColor(Color.RED);

                }

            }
        });



    }

}
