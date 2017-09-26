package edu.depaul.csc472.m.mcclellan.mcclellanmracketbuilder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class BuildActivity extends Activity {

    TextView headSizeView, weightView, balanceView, lengthView, stiffnessView,
            tensionView, numMainsView, numCrossesView, gaugeView, labelParamsView;
    EditText nameView;
    SeekBar headSizeBar, weightBar, balanceBar, lengthBar, stiffnessBar,
    tensionBar, numMainsBar, numCrossesBar, gaugeBar;
    ProgressBar powerBar, spinBar, swingBar, controlBar, volleyBar;
    Button backButton, toggleButton, appraiseButton;
    ImageButton parameterInfoButton, performanceInfoButton;
    Spinner stringTypeMainSpinner, stringTypeCrossesSpinner;
    ImageView racket_image;
    Profile profile;
    LinearLayout racket_parameters, string_parameters;

    static final String[] STRINGTYPES = {
            "Gut/Synthetic Gut",
            "Multifilament",
            "Monofilament"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        if (intent != null) {
            // find text views
            headSizeView = (TextView) findViewById(R.id.head_size);
            weightView = (TextView) findViewById(R.id.weight);
            balanceView = (TextView) findViewById(R.id.balance);
            lengthView = (TextView) findViewById(R.id.length);
            stiffnessView = (TextView) findViewById(R.id.stiffness);
            tensionView = (TextView) findViewById(R.id.tension);
            numMainsView = (TextView) findViewById(R.id.num_mains);
            numCrossesView = (TextView) findViewById(R.id.num_crosses);
            gaugeView = (TextView) findViewById(R.id.gauge);
            nameView = (EditText) findViewById(R.id.name);
            labelParamsView = (TextView) findViewById(R.id.label_parameters);

            // find seek bars
            headSizeBar = (SeekBar) findViewById(R.id.seekbar_head_size);
            weightBar = (SeekBar) findViewById(R.id.seekbar_weight);
            balanceBar = (SeekBar) findViewById(R.id.seekbar_balance);
            lengthBar = (SeekBar) findViewById(R.id.seekbar_length);
            stiffnessBar = (SeekBar) findViewById(R.id.seekbar_stiffness);
            tensionBar = (SeekBar) findViewById(R.id.seekbar_tension);
            numMainsBar = (SeekBar) findViewById(R.id.seekbar_num_mains);
            numCrossesBar = (SeekBar) findViewById(R.id.seekbar_num_crosses);
            gaugeBar = (SeekBar) findViewById(R.id.seekbar_gauge);

            // find performance bars
            powerBar = (ProgressBar) findViewById(R.id.power);
            spinBar = (ProgressBar) findViewById(R.id.spin);
            swingBar = (ProgressBar) findViewById(R.id.swing_speed);
            controlBar = (ProgressBar) findViewById(R.id.control);
            volleyBar = (ProgressBar) findViewById(R.id.volley);

            // find spinners
            stringTypeMainSpinner = (Spinner) findViewById(R.id.spinner_stringtype_main);
            stringTypeCrossesSpinner = (Spinner) findViewById(R.id.spinner_stringtype_crosses);
            ArrayAdapter<String> adapter =
                    new ArrayAdapter<>(this,
                            android.R.layout.simple_spinner_dropdown_item, STRINGTYPES);
            adapter.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item);
            stringTypeMainSpinner.setAdapter(adapter);
            stringTypeCrossesSpinner.setAdapter(adapter);

            profile = intent.getParcelableExtra("Profile");
            nameView.setText(profile.getName());
            // Set listener to clear edit text focus after input
            nameView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if(actionId== EditorInfo.IME_ACTION_DONE){
                        nameView.clearFocus();
                        InputMethodManager imm = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                        profile.setName(nameView.getText().toString());
                        profile.setUpdateTime(System.currentTimeMillis());
                    }
                    return true;
                }
            });

            headSizeBar.setProgress(profile.getHeadSize());
            weightBar.setProgress(profile.getWeight());
            balanceBar.setProgress(profile.getBalance());
            lengthBar.setProgress(profile.getLength());
            stiffnessBar.setProgress(profile.getStiffness());
            tensionBar.setProgress(profile.getTension());
            numMainsBar.setProgress(profile.getNumMains());
            numCrossesBar.setProgress(profile.getNumCrosses());
            gaugeBar.setProgress(profile.getGauge());
            stringTypeMainSpinner.setSelection(profile.getTypeMain());
            stringTypeCrossesSpinner.setSelection(profile.getTypeCrosses());

            setTextViews();
            setPerformanceBars();
            setSeekBarListeners();

            // Get the Buttons
            backButton = (Button) findViewById(R.id.button_back);
            toggleButton = (Button) findViewById(R.id.button_parameter_toggle);
            appraiseButton = (Button) findViewById(R.id.button_appraise);
            parameterInfoButton = (ImageButton) findViewById(R.id.button_info);
            performanceInfoButton = (ImageButton) findViewById(R.id.button_metric_info);

            setButtonListeners();

            // Get the layouts
            racket_parameters = (LinearLayout) findViewById(R.id.racket_parameters);
            string_parameters = (LinearLayout) findViewById(R.id.string_parameters);

            racket_image = (ImageView) findViewById(R.id.racket_view);
            //racket_image.setColorFilter(0xffff0000);


        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void finish() {
        profile.setName(nameView.getText().toString());
        profile.setUpdateTime(System.currentTimeMillis());
        Intent buildResult = new Intent();
        buildResult.putExtra("Profile", (Parcelable) profile);
        setResult(RESULT_OK, buildResult);
        super.finish();
    }

    // set Text Views
    private void setTextViews() {
        StringBuilder strbuilder = new StringBuilder();

        int addition = headSizeBar.getProgress() * 5;
        strbuilder.append(85 + addition).append(" sq. in.");
        headSizeView.setText(strbuilder);
        strbuilder.setLength(0);

        float addition2 = weightBar.getProgress() / 2;
        strbuilder.append(9.0 + addition2).append(" oz.");
        weightView.setText(strbuilder);
        strbuilder.setLength(0);

        addition = balanceBar.getProgress();
        if (addition < 4)
            strbuilder.append((4 - addition) * 5).append(" pts HL");
        else if (addition > 4)
            strbuilder.append(-20 + addition * 5).append(" pts HH");
        else strbuilder.append("Balanced");
        balanceView.setText(strbuilder);
        strbuilder.setLength(0);

        addition2 = lengthBar.getProgress() / 2;
        strbuilder.append(27 + addition2).append(" in.");
        lengthView.setText(strbuilder);
        strbuilder.setLength(0);

        addition = stiffnessBar.getProgress() * 5;
        strbuilder.append(55 + addition).append(" RA");
        stiffnessView.setText(strbuilder);
        strbuilder.setLength(0);

        addition = tensionBar.getProgress() * 5;
        strbuilder.append(-20 + addition).append(" lbs");
        tensionView.setText(strbuilder);
        strbuilder.setLength(0);

        addition = numMainsBar.getProgress();
        strbuilder.append(16 + addition);
        numMainsView.setText(strbuilder);
        strbuilder.setLength(0);

        addition = numCrossesBar.getProgress();
        strbuilder.append(18 + addition);
        numCrossesView.setText(strbuilder);
        strbuilder.setLength(0);

        addition = gaugeBar.getProgress();
        strbuilder.append(15 + addition);
        gaugeView.setText(strbuilder);
        strbuilder.setLength(0);
    }

    // Set all the performance bars at once
    private void setPerformanceBars() {
        setPowerBar();
        setSpinBar();
        setSwingBar();
        setControlBar();
        setVolleyBar();
    }

    // Calculate and set individual performance bars
    private void setPowerBar() {
        int power = 0;
        power += headSizeBar.getProgress() * 5;
        power += lengthBar.getProgress() * 2;
        power += stiffnessBar.getProgress();

        int weight = weightBar.getProgress();
        if (weight < 7) {
            power += weight;
        }
        else  {
            power += 6 - (weight - 6);
        }

        int balance = balanceBar.getProgress();
        if (balance > 6) balance = 6;
        power += balance;

        int tension = tensionBar.getProgress();
        power += 2 * (8 - tension);

        int gauge = gaugeBar.getProgress();
        if (gauge > 2) gauge = 2;
        power += gauge;

        int mainstrings = stringTypeMainSpinner.getSelectedItemPosition();
        if (mainstrings == 0) power += 2;
        if (mainstrings == 1) power += 4;
        int crossstrings = stringTypeCrossesSpinner.getSelectedItemPosition();
        if (crossstrings == 0) power += 2;
        if (crossstrings == 1) power += 4;

        powerBar.setProgress(power);
    }

    private void setSpinBar() {
        int spin = 0;

        spin += headSizeBar.getProgress() * 2;
        spin += stiffnessBar.getProgress() * 2;
        spin += 10 * (2 - numMainsBar.getProgress());
        spin += 3 * (2 - numMainsBar.getProgress());
        int mainstrings = stringTypeMainSpinner.getSelectedItemPosition();
        if (mainstrings == 0) spin += 15;
        if (mainstrings == 2) spin += 30;
        int crossstrings = stringTypeCrossesSpinner.getSelectedItemPosition();
        if (crossstrings == 0) spin += 4;
        if (crossstrings == 2) spin += 8;
        spin += 2 * gaugeBar.getProgress();

        spinBar.setProgress(spin);
    }

    private void setSwingBar() {
        int swingspeed = 0;
        swingspeed += (8 - weightBar.getProgress()) * 6;
        swingspeed += (8 - balanceBar.getProgress()) * 3;
        swingspeed += (4 - lengthBar.getProgress()) * 5;
        if (headSizeBar.getProgress() >= 2)
            swingspeed += (10 - headSizeBar.getProgress());
        else swingspeed += 8;

        swingBar.setProgress(swingspeed);
    }

    private void setControlBar() {
        int control = 0;

        control += (10 - headSizeBar.getProgress()) * 2;
        control += 8 - weightBar.getProgress();
        control += (8 - balanceBar.getProgress()) * 2;
        control += (4 - lengthBar.getProgress()) * 2;
        control += (4 - stiffnessBar.getProgress());
        control += 3 * numMainsBar.getProgress();
        control += numCrossesBar.getProgress();
        int mainstrings = stringTypeMainSpinner.getSelectedItemPosition();
        if (mainstrings == 0) control += 4;
        if (mainstrings == 2) control += 8;
        int crossstrings = stringTypeCrossesSpinner.getSelectedItemPosition();
        if (crossstrings == 0) control += 2;
        if (crossstrings == 2) control += 4;
        control += 3 * tensionBar.getProgress();

        controlBar.setProgress(control);

    }

    private void setVolleyBar() {
        int volley = 0;

        int weight = weightBar.getProgress();
        if (weight < 7) {
            volley += weight * 2;
        } else {
            volley += (6 - (weight - 6)) * 2;
        }

        int balance = balanceBar.getProgress();
        if (balance < 7) {
            volley += balance * 8;
        } else {
            volley += 48 - (4*(balance - 6));
        }

        volley += (4 - lengthBar.getProgress());
        volley += stiffnessBar.getProgress() * 2;
        volley += 2 * numMainsBar.getProgress();
        volley += 2 * numCrossesBar.getProgress();

        int mainstrings = stringTypeMainSpinner.getSelectedItemPosition();
        if (mainstrings == 0) volley += 4;
        if (mainstrings == 1) volley += 8;
        int crossstrings = stringTypeCrossesSpinner.getSelectedItemPosition();
        if (crossstrings == 0) volley += 4;
        if (crossstrings == 1) volley += 8;

        int tension = tensionBar.getProgress();
        if (tension < 5) {
            volley += 2 * tension;
        }
        else {
            volley += 8 - (2*(tension - 4));
        }


        volleyBar.setProgress(volley);
    }

    // set seek bar listeners
    private void setSeekBarListeners() {
        headSizeBar.setOnSeekBarChangeListener(
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    profile.setHeadSize(progress);
                    headSizeView.setText(85 + (progress*5) + " sq. in.");
                    setPowerBar();
                    setSpinBar();
                    setSwingBar();
                    setControlBar();
                    profile.setUpdateTime(System.currentTimeMillis());
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            }
        );

        weightBar.setOnSeekBarChangeListener(
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    profile.setWeight(progress);
                    weightView.setText(9 + ((float)progress/2) + " oz.");
                    setPowerBar();
                    setSwingBar();
                    setControlBar();
                    setVolleyBar();
                    profile.setUpdateTime(System.currentTimeMillis());
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            }
        );

        balanceBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        profile.setBalance(progress);
                        String temp;
                        if (progress < 4)
                            temp =((4 - progress)*5) + " pts HL";
                        else if (progress > 4)
                            temp = -20 + (progress*5) + " pts HH";
                        else temp = "Balanced";
                        balanceView.setText(temp);
                        setPowerBar();
                        setSwingBar();
                        setControlBar();
                        setVolleyBar();
                        profile.setUpdateTime(System.currentTimeMillis());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        lengthBar.setOnSeekBarChangeListener(
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    profile.setLength(progress);
                    lengthView.setText(27 + ((float)progress/2) + " in.");
                    setPowerBar();
                    setSwingBar();
                    setControlBar();
                    profile.setUpdateTime(System.currentTimeMillis());
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            }
        );

        stiffnessBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        profile.setStiffness(progress);
                        stiffnessView.setText(55 + (progress*5) + " RA");
                        setPowerBar();
                        setSpinBar();
                        setControlBar();
                        setVolleyBar();
                        profile.setUpdateTime(System.currentTimeMillis());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        tensionBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        profile.setTension(progress);
                        tensionView.setText(-20 + (progress*5) + " lbs");
                        setPowerBar();
                        setControlBar();
                        setVolleyBar();
                        profile.setUpdateTime(System.currentTimeMillis());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        numMainsBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        profile.setNumMains(progress);
                        numMainsView.setText(16 + progress + "");
                        setSpinBar();
                        setControlBar();
                        setVolleyBar();
                        profile.setUpdateTime(System.currentTimeMillis());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        numCrossesBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        profile.setNumCrosses(progress);
                        numCrossesView.setText(18 + progress + "");
                        setSpinBar();
                        setControlBar();
                        setVolleyBar();
                        profile.setUpdateTime(System.currentTimeMillis());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        gaugeBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        profile.setGauge(progress);
                        gaugeView.setText(15 + progress + "");
                        setPowerBar();
                        setSpinBar();
                        profile.setUpdateTime(System.currentTimeMillis());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        stringTypeMainSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        profile.setTypeMain(position);
                        setPowerBar();
                        setSpinBar();
                        setControlBar();
                        setVolleyBar();
                        profile.setUpdateTime(System.currentTimeMillis());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

        stringTypeCrossesSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        profile.setTypeCrosses(position);
                        setPowerBar();
                        setSpinBar();
                        setControlBar();
                        setVolleyBar();
                        profile.setUpdateTime(System.currentTimeMillis());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
    }

    // Set the button listeners
    private void setButtonListeners() {
        backButton.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );

        toggleButton.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String racketMode = "Set Racket";
                        String racketLabel = "Racket Parameters:";
                        String stringMode = "Set Strings";
                        String stringLabel = "String Parameters:";
                        if (racket_parameters.getVisibility() == View.VISIBLE) {
                            racket_parameters.setVisibility(View.INVISIBLE);
                            string_parameters.setVisibility(View.VISIBLE);
                            toggleButton.setText(racketMode);
                            labelParamsView.setText(stringLabel);
                        } else {
                            racket_parameters.setVisibility(View.VISIBLE);
                            string_parameters.setVisibility(View.INVISIBLE);
                            toggleButton.setText(stringMode);
                            labelParamsView.setText(racketLabel);
                        }
                    }
                }
        );

        appraiseButton.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(BuildActivity.this, AppraiseActivity.class);

                        intent.putExtra("Profile", (Parcelable) profile);
                        startActivity(intent);
                    }
                }
        );

        parameterInfoButton.setOnClickListener(
                new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(BuildActivity.this, InfoActivity.class);
                        if (racket_parameters.getVisibility() == View.VISIBLE)
                            intent.putExtra("Info", 1);
                        else intent.putExtra("Info", 2);
                        startActivity(intent);
                    }
                }
        );

        performanceInfoButton.setOnClickListener(
                new ImageButton.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(BuildActivity.this, InfoActivity.class);
                        intent.putExtra("Info", 0);
                        startActivity(intent);
                    }
                }
        );
    }
}
