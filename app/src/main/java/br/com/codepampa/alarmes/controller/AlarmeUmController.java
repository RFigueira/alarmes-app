package br.com.codepampa.alarmes.controller;

import android.app.TimePickerDialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import br.com.codepampa.alarmes.R;
import br.com.codepampa.alarmes.util.AlarmeUtil;

public class AlarmeUmController extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private static final String TAG = "Alarme um";
    private static final String TITULO_INTENT = "Me lembra de alguma coisa";
    private static final String CATEGORIA_INTENT = "android.intent.category.DEFAULT";
    private static final int ZERO = 0;
    private static final int NOVE = 9;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarme_um);

        editText = (EditText) findViewById(R.id.activity_um_edit_text_time);
        editText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                int hora = calendar.get(Calendar.HOUR_OF_DAY);
                int minutos = calendar.get(Calendar.MINUTE);

                new TimePickerDialog(AlarmeUmController.this, AlarmeUmController.this, hora, minutos,
                        android.text.format.DateFormat.is24HourFormat(AlarmeUmController.this)).show();
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        String mascaraMinutos;

        Intent intent = new Intent(TITULO_INTENT);
        intent.addCategory(CATEGORIA_INTENT);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, ZERO);
        calendar.set(Calendar.MILLISECOND, ZERO);

        AlarmeUtil.schedule(this, intent, calendar.getTimeInMillis());

        if(minute <= NOVE) {
            mascaraMinutos = "0" + minute;
        } else {
            mascaraMinutos = String.valueOf(minute);
        }

        editText.setText(hourOfDay + ':' + mascaraMinutos);

        Toast.makeText(AlarmeUmController.this, "Alarme agendado para: " + hourOfDay + ':' + mascaraMinutos, Toast.LENGTH_SHORT).show();

    }
}
