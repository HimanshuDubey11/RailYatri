package project.himanshu.com.railyatri;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PnrActivity extends AppCompatActivity {

    TextView pnrActvityNumber, pnrActvityTrain, pnrActvityDate, pnrActvitySource, pnrActvityDest;
    RequestQueue requestQueue;
    ProgressDialog dialog;
    String url;
    RecyclerView recyclerView;
    ArrayList<PnrPassengerData> pnrPassengerData = new ArrayList<PnrPassengerData>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pnr);

        dialog = new ProgressDialog(PnrActivity.this);
        dialog.setMessage("Loading...");
        dialog.show();
        recyclerView = findViewById(R.id.pnractivityrecyclerview);
        pnrActvityNumber = findViewById(R.id.pnractivitynumber);
        pnrActvityTrain = findViewById(R.id.pnractivitytrain);
        pnrActvityDate = findViewById(R.id.pnractivitydate);
        pnrActvitySource = findViewById(R.id.pnractivityfrom);
        pnrActvityDest = findViewById(R.id.pnractivityto);
        requestQueue = Volley.newRequestQueue(PnrActivity.this);
//
//        url = "https://api.railwayapi.com/v2/pnr-status/pnr/" + "4703704576" +
//                "/apikey/" + "2g6h4pzx2f" +
//                "/" ;

        url = "http://indianrailapi.com/api/v2/PNRCheck/apikey/65ad39e4d61158a1506d562725b7ddb3/" +
                "PNRNumber/" + Home.pnrnumber +
                "/";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET
                , url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                dialog.dismiss();

                try {

                    pnrActvityNumber.setText(response.getString("PnrNumber"));
                    pnrActvityDate.setText(response.getString("JourneyDate"));

                    pnrActvityTrain.setText(response.getString("TrainName") + "\n" + response.getString("TrainNumber"));

                    pnrActvitySource.setText(response.getString("From"));

                    pnrActvityDest.setText(response.getString("To"));

                    JSONArray passengerArray = response.getJSONArray("Passangers");

                    for (int i = 0; i < passengerArray.length(); i++) {

                        JSONObject passengerObject = passengerArray.getJSONObject(i);

                        String no = passengerObject.getString("Passenger");
                        String current_status = passengerObject.getString("CurrentStatus");
                        String booking_status = passengerObject.getString("BookingStatus");

                        pnrPassengerData.add(new PnrPassengerData(no,current_status,booking_status));

                    }

                    if (response.getString("Status").equals("FAILED")) {

                        Toast.makeText(PnrActivity.this, "Invalid PNR", Toast.LENGTH_SHORT).show();

                    } else {

                        recyclerView.setLayoutManager(new LinearLayoutManager(PnrActivity.this,LinearLayoutManager.VERTICAL,false));
                        recyclerView.setAdapter(new PnrPassengerAdapter(pnrPassengerData));

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(PnrActivity.this, "" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

        requestQueue.add(request);
    }



}
