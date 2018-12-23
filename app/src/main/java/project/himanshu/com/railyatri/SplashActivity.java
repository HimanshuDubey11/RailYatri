package project.himanshu.com.railyatri;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class SplashActivity extends AppCompatActivity {

    RelativeLayout splashRelativeLayout;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splashRelativeLayout = findViewById(R.id.splashscreenrelativelayout);
        splashRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this,Home.class);
                startActivity(intent);
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,Home.class);
                startActivity(intent);
            }
        },3000);


    }
}
