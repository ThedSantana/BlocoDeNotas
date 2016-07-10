package br.edu.ifpb.vibrar;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    EditText editText;
    ListView listView;

    ArrayList<String> notas = new ArrayList<String>();
    ArrayAdapter<String> adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        listView = (ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notas);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);



    }

    public void criar(View view){

        String textoNota = editText.getText().toString();

        if(textoNota.length() > 0) {
            editText.setText("");
            editText.findFocus();
            notas.add(textoNota);
            adapter.notifyDataSetChanged();
        }

    }

    public void btVibrar(View view){

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        vibrator.vibrate(100);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("Nota");
            alert.setMessage("Você deseja apagar esta nota?");
            final int positionToRemove = i;
            alert.setNegativeButton("Não", null);
            alert.setPositiveButton("Sim", new AlertDialog.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    notas.remove(positionToRemove);
                    adapter.notifyDataSetChanged();
                }
            });

            alert.show();
    }
}
