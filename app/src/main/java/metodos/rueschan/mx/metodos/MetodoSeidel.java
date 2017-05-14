package metodos.rueschan.mx.metodos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Adrian on 09/05/2017.
 */
public class MetodoSeidel extends Activity{
    private EditText txtDatos,txtTamaño, txtMat,txtError;
    private Float error;
    private TextView resultado;
    private Integer Tamaño, flagArr1 = 0, flagArr2 = 0;
    private ArrayList<String> arreglo1 = new ArrayList<>();
    private ArrayList<String> arreglo2 = new ArrayList<>();
    private Button datosBtn,resolver, errorBtn, rangoBtn,tamañoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gauss_seidel);

        datosBtn = (Button) findViewById(R.id.datosMatrizButt);
        resolver = (Button)findViewById(R.id.Resolver);
        tamañoBtn = (Button)findViewById(R.id.tamañoMatriz);
        errorBtn = (Button)findViewById(R.id.Error);
        rangoBtn = (Button)findViewById(R.id.Rango);
        resultado = (TextView)findViewById(R.id.calculaResultado);
        txtDatos = (EditText)findViewById(R.id.numero);
        txtTamaño = (EditText)findViewById(R.id.tamaño);
        txtError = (EditText)findViewById(R.id.errorTxt);
        txtMat = (EditText)findViewById(R.id.matriz);

        tamañoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String leeTamaño = txtTamaño.getText().toString().trim();
                if(leeTamaño == null||leeTamaño.trim().equals("")){
                }else if (leeTamaño.trim().equals("0")||leeTamaño.trim().equals("1"))((EditText) findViewById(R.id.tamaño)).setText(" ");
                else{
                    ((EditText) findViewById(R.id.tamaño)).setText(" ");
                    if(tamañoBtn.isEnabled()) tamañoBtn.setEnabled(false);
                    Tamaño = Integer.parseInt(leeTamaño);}}});

        rangoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String leeRango = txtMat.getText().toString();
                if(leeRango == null||leeRango.trim().equals(""))Toast.makeText(getBaseContext(),"Ingresa el Rango", Toast.LENGTH_SHORT).show();
                else{
                    ((EditText) findViewById(R.id.matriz)).setText(" ");
                    arreglo2.add(leeRango.trim());
                    flagArr1 += 1;
                    if(flagArr1 >= Tamaño){
                        if(rangoBtn.isEnabled()) {
                            rangoBtn.setEnabled(false);
                        }}}}});

        errorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String leeError = txtError.getText().toString();
                if(leeError == null||leeError.trim().equals(""))Toast.makeText(getBaseContext(),"Ingresa el error", Toast.LENGTH_SHORT).show();
                 else{
                    ((EditText) findViewById(R.id.errorTxt)).setText(" ");
                    if(errorBtn.isEnabled()) {
                        errorBtn.setEnabled(false);
                    }error = Float.parseFloat(leeError) ;
                }}});

        datosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String leeDatos = txtDatos.getText().toString();
                if(leeDatos==null || leeDatos.trim().equals("")) Toast.makeText(getBaseContext(),"Llena la Matriz", Toast.LENGTH_SHORT).show();
                else{
                    ((EditText) findViewById(R.id.numero)).setText(" ");
                    arreglo1.add(leeDatos.trim());
                    flagArr2 += 1;
                    if(flagArr2 >= (Tamaño +1) * Tamaño){
                        if(datosBtn.isEnabled()) {
                            datosBtn.setEnabled(false);
                        }}}}});

        resolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int y = 0,x=0;
                GaussSeidel Seidel = new GaussSeidel();
                Float[][] matrix = new Float[Tamaño][Tamaño +1];
                float[] nMat = new float[Tamaño];
                for (int i = 1; i <= arreglo1.size(); i++) {
                    matrix[x][y] = Float.parseFloat(arreglo1.get(i-1));
                    if (i % (Tamaño +1) == 0) {
                        x++; y = 0;
                    }else y++;}
                for (int i = 0; i < arreglo2.size(); i++) {
                    nMat[i] =  Float.parseFloat(arreglo2.get(i));
                }

                if (Seidel.Dominante(matrix)){
                    float[] res = Seidel.resolver(matrix, error,nMat);
                    resultado.setText("Resultado \n" + Arrays.toString(res));
                }
                else  resultado.setText("Diagonal no dominante");
                arreglo1.clear();
                arreglo2.clear();
                flagArr1 = 0;
                flagArr2 = 0;
                if(!tamañoBtn.isEnabled() || !datosBtn.isEnabled() || !errorBtn.isEnabled() || !rangoBtn.isEnabled()) {
                    tamañoBtn.setEnabled(true);
                    datosBtn.setEnabled(true);
                    errorBtn.setEnabled(true);
                    rangoBtn.setEnabled(true);
                }}});
    }
}



