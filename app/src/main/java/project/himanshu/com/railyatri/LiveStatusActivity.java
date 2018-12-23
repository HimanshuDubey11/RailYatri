package project.himanshu.com.railyatri;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LiveStatusActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    ProgressDialog dialog;
    String url;
    TextView trainNumber, position, stationName, schArr, schDep, actArr, actDep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_status);

        trainNumber = findViewById(R.id.livestatustrainnumber);
        position = findViewById(R.id.livestatuscurrentposition);
        stationName = findViewById(R.id.livestatuscurrentstaion);
        schArr = findViewById(R.id.schedulearrival);
        schDep = findViewById(R.id.scheduledeparture);
        actArr = findViewById(R.id.actualarrival);
        actDep = findViewById(R.id.actualdeparture);

        dialog = new ProgressDialog(LiveStatusActivity.this);
        dialog.setMessage("Loading...");
        dialog.show();
        requestQueue = Volley.newRequestQueue(LiveStatusActivity.this);

        url = "http://indianrailapi.com/api/v2/livetrainstatus/apikey/800a4d27272944a7a7da007dfe8a17b1/trainnumber/12001/date/20181005/";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                dialog.dismiss();

                try {
                    trainNumber.setText(response.getString("TrainNumber"));
                    position.setText(response.getString("CurrentPosition"));

                    JSONObject object = response.getJSONObject("CurrentStation");

                    stationName.setText(object.getString("StationName") + "\n" + object.getString("StationCode"));

                    schArr.setText(object.getString("ScheduleArrival"));
                    schDep.setText(object.getString("ScheduleDeparture"));
                    actArr.setText(object.getString("ActualArrival"));
                    actDep.setText(object.getString("ActualDeparture"));

                } catch (JSONException e) {

                    e.printStackTrace();
                    Toast.makeText(LiveStatusActivity.this, "Error"+ e.toString() , Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);

    }
}
