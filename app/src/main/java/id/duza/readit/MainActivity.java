package id.duza.readit;

import android.annotation.TargetApi;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etBox;
    private Button btnRead;
    private Button btnStop;
    private Button btnClean;

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();
        initializeTextToSpeech();

        btnRead.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnClean.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_speak: speak(); break;
            case R.id.btn_stop: stop(); break;
            case R.id.btn_clean: clean(); break;
        }
    }

    private void clean() {
        etBox.setText("");
    }

    private void stop() {
        tts.stop();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void speak() {
        tts.speak(getUserInput(), TextToSpeech.QUEUE_ADD, null, "DEFAULT");
    }

    private void initializeTextToSpeech() {
        TextToSpeech.OnInitListener listener = new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    Log.d("TTS", "Text to speech engine started successfully.");
                    tts.setLanguage(new Locale("id", "ID"));
                } else {
                    Log.d("TTS", "Error starting the text to speech engine.");
                }
            }
        };

        tts = new TextToSpeech(this, listener);
    }

    private CharSequence getUserInput() {
        return etBox.getText().toString().trim();
    }

    private void setupView() {
        etBox = (EditText) findViewById(R.id.et_main_box_to_read);
        btnRead = (Button) findViewById(R.id.btn_speak);
        btnStop = (Button) findViewById(R.id.btn_stop);
        btnClean = (Button) findViewById(R.id.btn_clean);
    }
}
