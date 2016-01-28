package br.com.appdev.leitorprovidercidades;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.txt);

        //Pesquisa
        Uri uriListar = Uri.parse("content://br.com.appdev.sqlitecidades/cities");
        Cursor cursor = getContentResolver().query(uriListar, null, null, null, null);
        int count = cursor.getCount();
        cursor.moveToFirst();
        String city = cursor.getString(cursor.getColumnIndex("city"));
        txt.setText("Cidade: " + city);

        //Inserção
        ContentValues values = new ContentValues();
        values.put("city", "Aqui");
        values.put("uf", "PR");
        values.put("people", 10);
        getContentResolver().insert(uriListar, values);

        //Exclusão
        Uri uriDeletar = Uri.parse("content://br.com.appdev.sqlitecidades/cities/1");
        getContentResolver().delete(uriDeletar, null, null);

        //Edição
        Uri uriEditar = Uri.parse("content://br.com.appdev.sqlitecidades/cities/2");
        getContentResolver().update(uriEditar, values, null, null);
    }
}
