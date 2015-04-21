package com.example.rutaitam;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
    GrafoITAM grafo;
    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int ancho, alto;
		ancho = getWindowManager().getDefaultDisplay().getWidth();
		alto = getWindowManager().getDefaultDisplay().getHeight();
		LinearLayout layoutOpciones, layoutOrigen, layoutDestino;
		layoutOpciones = (LinearLayout)findViewById(R.id.layoutOpciones);
		layoutOpciones.getLayoutParams().height = alto/4;
		layoutOrigen = (LinearLayout)findViewById(R.id.layoutOrigen);
		layoutOrigen.getLayoutParams().width = ancho/2;
		layoutOrigen.getLayoutParams().height = alto/4;
		layoutDestino = (LinearLayout)findViewById(R.id.layoutDestino);
		layoutDestino.getLayoutParams().width = ancho/2;
		layoutDestino.getLayoutParams().height = alto/4;

        try {
            grafo = new GrafoITAM(getApplicationContext().getAssets().open("grafo.txt"));
        }catch (IOException ioe){
            Toast.makeText(this,"No se pudo formar el grafo del ITAM...",Toast.LENGTH_LONG).show();
        }
		final Spinner spinnerOrigen = (Spinner)findViewById(R.id.origen);
		final Spinner spinnerDestino = (Spinner)findViewById(R.id.destino);
    	
		String lugares [] = grafo.getNames();
		ArrayAdapter<String> adapterOrigen = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lugares);
		spinnerOrigen.setAdapter(adapterOrigen);
		ArrayAdapter<String> adapterDestino = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lugares);
		spinnerDestino.setAdapter(adapterDestino);
		final EditText txtRuta = (EditText)findViewById(R.id.txtRuta);
		txtRuta.setFocusable(false);

		Button ruta = (Button)findViewById(R.id.btnRuta);
		ruta.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String strOrigin = spinnerOrigen.getSelectedItem().toString();
				String strTarget = spinnerDestino.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(),strOrigin+"-"+strTarget,Toast.LENGTH_SHORT).show();
				ArrayList<String> path = grafo.getPath(strOrigin,strTarget);
                txtRuta.setText(path.toString());
			}
		});
		
		
		
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.aboutUs) {
        	Intent nosotros = new Intent(getApplicationContext(),AboutUs.class);
    		startActivity(nosotros);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
