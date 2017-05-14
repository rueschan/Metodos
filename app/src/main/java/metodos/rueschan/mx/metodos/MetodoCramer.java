package metodos.rueschan.mx.metodos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Adrian on 22/04/2017.
 */
public class MetodoCramer extends Activity {
    private ArrayList<String> arregloMatriz = new ArrayList<>();
    private Integer tamañoMatriz, hastaFlag = 0;
    private TextView resultado;
    private Button tamañoBtn, resolverBtn, datosBtn;
    private EditText txtMatriz,txtDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cramer);
        datosBtn = (Button) findViewById(R.id.tamañoMatriz);
        resolverBtn = (Button)findViewById(R.id.resolverMetodo);
        tamañoBtn = (Button)findViewById(R.id.tamañoMatrizButton);
        txtDatos = (EditText)findViewById(R.id.matriz);
        txtMatriz = (EditText)findViewById(R.id.tamaño);

        resultado = (TextView)findViewById(R.id.calculaResultado);
        if(tamañoBtn.isEnabled() && datosBtn.isEnabled()){
            if (resolverBtn.isEnabled()){
                resolverBtn.setEnabled(false);
            }
        }

        datosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getInput = txtDatos.getText().toString();
                if(getInput==null || getInput.trim().equals("")){
                    Toast.makeText(getBaseContext(),"Llena la matiz", Toast.LENGTH_SHORT).show();
                }
                else{
                    ((EditText) findViewById(R.id.matriz)).setText(" ");
                    arregloMatriz.add(getInput.trim());
                    hastaFlag += 1;
                    if(hastaFlag >= (tamañoMatriz +1) * tamañoMatriz){
                        if(datosBtn.isEnabled()|| !resolverBtn.isEnabled()) {
                            datosBtn.setEnabled(false);
                            resolverBtn.setEnabled(true);
                        }
                    }
                }
            }
        });


        tamañoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getInput = txtMatriz.getText().toString().trim();
                tamañoMatriz = Integer.parseInt(getInput);

                if(getInput == null||getInput.trim().equals("")){
                    Toast.makeText(getBaseContext(),"Da un tamaño a la matriz", Toast.LENGTH_SHORT).show();
                }else if (getInput.trim().equals("0")||getInput.trim().equals("1")){
                    ((EditText) findViewById(R.id.tamaño)).setText(" ");
                    Toast.makeText(getBaseContext(),"La matriz no puede ser de ese tamaño ", Toast.LENGTH_LONG).show();
                }
                else{
                    ((EditText) findViewById(R.id.tamaño)).setText(" ");
                    if(tamañoBtn.isEnabled()) {
                        tamañoBtn.setEnabled(false);
                    }
                    tamañoMatriz = Integer.parseInt(getInput);
                }

            }
        });

        resolverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String res ="";
                int x=0,y=0;
                Cramer cram = new Cramer();
                float[][] rc = new float[tamañoMatriz][tamañoMatriz];
                float[] ren  = new float[tamañoMatriz];

                for (int i = 1; i <= arregloMatriz.size(); i++) {
                    if (i % (tamañoMatriz +1) == 0) {
                        ren[x] = Float.parseFloat(arregloMatriz.get(i-1));
                        x++;
                        y = 0;
                    }else{
                        rc[x][y] = Float.parseFloat(arregloMatriz.get(i-1));
                        y++;
                    }
                }

                float[] cramer = cram.cramer(rc,ren);
                if(cramer != null){
                    for(int i=0;i<cramer.length;i++){
                        if(i!=cramer.length-1)
                            res += cramer[i] + " , ";
                        else
                            res += cramer[i];
                    }
                }

                resultado.setText("El resultado es\n"+res);
                arregloMatriz.clear();
                tamañoMatriz = 0;
                hastaFlag = 0;
                if(!tamañoBtn.isEnabled() || !datosBtn.isEnabled()) {
                    tamañoBtn.setEnabled(true);
                    datosBtn.setEnabled(true);
                }
                if(resolverBtn.isEnabled()){
                    resolverBtn.setEnabled(false);
                }

            }
        });
    }
}
