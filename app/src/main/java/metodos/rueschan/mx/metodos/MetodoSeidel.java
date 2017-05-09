package metodos.rueschan.mx.metodos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Adrian on 09/05/2017.
 */
public class MetodoSeidel extends Activity{
    private EditText tamanoTxt,numerosTxt,txtError, listaTxt;
    private ArrayList<String> arregloM = new ArrayList<String>();
    private ArrayList<String> arregloL = new ArrayList<String>();


    private Integer tamano, bastaL = 0, bastaM = 0;
    private TextView resultado;
    private Button botonTamano, calcular ,botonDatos, botonError, botonLista;
    private Float error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide the Title bar of this activity screen
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_gauss_seidel);

        ImageButton back = (ImageButton)findViewById(R.id.back);
        botonDatos = (Button) findViewById(R.id.btnMatriz);
        calcular  = (Button)findViewById(R.id.gauss_seidel);
        botonTamano = (Button)findViewById(R.id.tamanoButton);
        botonError = (Button)findViewById(R.id.botonError);
        botonLista = (Button)findViewById(R.id.btnLista);

        //Datos
        numerosTxt = (EditText)findViewById(R.id.numero);
        tamanoTxt = (EditText)findViewById(R.id.tamano);
        txtError = (EditText)findViewById(R.id.errorTxt);
        listaTxt = (EditText)findViewById(R.id.lista);
        //Resultado
        resultado = (TextView)findViewById(R.id.resultado);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inti = new Intent(getBaseContext(),MainActivity.class);
                startActivity(inti);
            }
        });

        botonLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getInput = listaTxt.getText().toString();

                if(getInput == null||getInput.trim().equals("")){
                    Toast.makeText(getBaseContext(),"Dato faltante", Toast.LENGTH_SHORT).show();
                } else{
                    ((EditText) findViewById(R.id.lista)).setText(" ");
                    arregloL.add(getInput.trim());
                    bastaL += 1;
                    if(bastaL >= tamano){
                        if(botonLista.isEnabled()) {
                            botonLista.setEnabled(false);
                        }
                    }
                }
            }
        });

        botonError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getInput = txtError.getText().toString();

                if(getInput == null||getInput.trim().equals("")){
                    Toast.makeText(getBaseContext(),"Dato faltante", Toast.LENGTH_SHORT).show();
                } else{
                    ((EditText) findViewById(R.id.errorTxt)).setText(" ");
                    Toast.makeText(getBaseContext(), "Error: "+getInput,Toast.LENGTH_LONG).show();
                    if(botonError.isEnabled()) {
                        botonError.setEnabled(false);
                    }

                    error = Float.parseFloat(getInput) ;
                }
            }
        });

        botonDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getInput = numerosTxt.getText().toString();

                if(getInput==null || getInput.trim().equals("")){
                    Toast.makeText(getBaseContext(),"Dato faltante", Toast.LENGTH_SHORT).show();
                }
                else{
                    ((EditText) findViewById(R.id.numero)).setText(" ");
                    arregloM.add(getInput.trim());
                    bastaM += 1;
                    if(bastaM >= (tamano+1) * tamano){
                        if(botonDatos.isEnabled()) {
                            botonDatos.setEnabled(false);
                        }
                    }
                }

                //Toast.makeText(getBaseContext(), arreglo.toString(),Toast.LENGTH_LONG).show();
            }
        });


        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GaussSeidel gausSeidel = new GaussSeidel();
                //Log.d("********************** ", "" + tamano);
                //Log.d("********************** ", "" + arreglo.toString());
                Float[][] M = new Float[tamano][tamano+1];
                float[] X = new float[tamano];
                int y = 0;
                int x = 0;

                for (int i = 1; i <= arregloM.size(); i++) {
                    M[x][y] = Float.parseFloat(arregloM.get(i-1));
                    if (i % (tamano+1) == 0) {
                        x++;
                        y = 0;
                    }else{
                        y++;
                    }
                }

                for (int i = 0; i < arregloL.size(); i++) {
                    X[i] =  Float.parseFloat(arregloL.get(i));
                }


                if (!gausSeidel.makeDominant(M)) {
                    resultado.setText("No se puede calcular; Diagonal no dominante");
                }else {
                    float[] res = gausSeidel.solve(M, error,X);
                    //Toast.makeText(getBaseContext(), "El resultado de la operacion es " + res, Toast.LENGTH_LONG).show();

                    resultado.setText("El resultado de la operacion es\n" + Arrays.toString(res));
                    // Log.d("********************** ", "RESULTADO  " + res);
                }

                arregloM.clear();
                arregloL.clear();
                bastaL = 0;
                bastaM = 0;

                if(!botonTamano.isEnabled() || !botonDatos.isEnabled() || !botonError.isEnabled() || !botonLista.isEnabled()) {
                    botonTamano.setEnabled(true);
                    botonDatos.setEnabled(true);
                    botonError.setEnabled(true);
                    botonLista.setEnabled(true);
                }

            }
        });

        botonTamano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getInput = tamanoTxt.getText().toString().trim();

                if(getInput == null||getInput.trim().equals("")){
                    Toast.makeText(getBaseContext(),"Dato faltante", Toast.LENGTH_SHORT).show();
                }else if (getInput.trim().equals("0")||getInput.trim().equals("1")){
                    ((EditText) findViewById(R.id.tamano)).setText(" ");
                    Toast.makeText(getBaseContext(),"La matriz no puede ser de "+getInput+"x"+getInput, Toast.LENGTH_LONG).show();
                }
                else{
                    ((EditText) findViewById(R.id.tamano)).setText(" ");
                    Toast.makeText(getBaseContext(), "La matriz es de "+getInput+"x"+getInput,Toast.LENGTH_LONG).show();
                    if(botonTamano.isEnabled()) {
                        botonTamano.setEnabled(false);
                    }
                    tamano = Integer.parseInt(getInput);
                }


            }
        });
    }
}



