package com.example.stateprogressbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.kofigyan.stateprogressbar.StateProgressBar;

public class MainActivity extends AppCompatActivity {
    String[] descriptionData = {"Details", "Status", "Photo", "Confirm"};
    private Button nextBtn;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new ShippingFragment());

        nextBtn = findViewById(R.id.nextBtnId);
        frameLayout = findViewById(R.id.frameLayoutId);

        final StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.stateProgressbarId);
        stateProgressBar.setStateDescriptionData(descriptionData);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (stateProgressBar.getCurrentStateNumber()){
                    case 1:
                        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                        loadFragment(new PaymentFragment());
                        break;
                    case 2:
                        stateProgressBar.setAllStatesCompleted(true);;
                        loadFragment(new ConfirmFragment());
                        break;

                }
            }
        });
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutId, fragment);
        fragmentTransaction.commit();
    }
}
