package com.selimcinar.storingdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Global tanımlar
    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //id bağlantıları değişkenlere eşitlendi
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        //SharedPreferences küçük boyutta verileri tutar ve MODE_PRIVATE SADECE BENIM UYGULAMAMDA GÖZÜKSÜN DEMEK.
        sharedPreferences = this.getSharedPreferences("com.selimcinar.storingdata",MODE_PRIVATE);

        //Kaydedilen veriyi geri almak
        int storedAge= sharedPreferences.getInt("storedAge",0);
        if(storedAge==0){//default değer gelirse
            textView.setText("Your age : ");
        }
        else{//default değer gelmez yeni veri girilirse
            //kaydedilen veriyi yazdır
            textView.setText("Your age : "+storedAge);
        }

    }

    public void save(View view){
        //Dışardan veri girişinde boşluk olmazsa çalışır
        if(!editText.getText().toString().matches("")){
            //Dışardan veri girişi integer tipte
            int userAge = Integer.parseInt(editText.getText().toString());
            //Widgeta objeye değer ekleme
            textView.setText("Your age : "+userAge);

            //Verileri kaydetme
            sharedPreferences.edit().putInt("storedAge",userAge).apply();
        }

    }
    public void  delete(View view){
        //Kaydedilen verileri silme
        int storedAge= sharedPreferences.getInt("storedAge",0);
        if(storedAge !=0){
            //Kaydedilen veriyi silen kod
            sharedPreferences.edit().remove("storedAge").apply();
            textView.setText("Your age: ");

        }

    }
}