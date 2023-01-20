package com.example.ratingbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.review.model.ReviewErrorCode;


public class MainActivity extends AppCompatActivity {

    private  ReviewInfo reviewInfo ;
    private ReviewManager reviewManager;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activeReviewInfo();

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startReviewFlow();
            }
        });


    }
    void activeReviewInfo()
    {
        ReviewManager manager = ReviewManagerFactory.create(this);
        Task<ReviewInfo> managerInfoTask = manager.requestReviewFlow();
        managerInfoTask.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();
            } else {
                Toast.makeText(this, "Review failed to start", Toast.LENGTH_SHORT).show();
            }
        });

}

    void startReviewFlow()
    {
        if(reviewInfo!=null)
        {

           Task<Void> flow =  reviewManager.launchReviewFlow(this,reviewInfo);
            flow.addOnSuccessListener(task->
            {
                Toast.makeText(this, "Rating is complete", Toast.LENGTH_SHORT).show();

            });

        }
    }
}
