package metodos.rueschan.mx.metodos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Adrian on 09/05/2017.
 */
public class MetodoBairstow extends AppCompatActivity {
    private double error;
    private double r, s;
    private int tamañoEc, termina;
    private TextView resultado;
    private Button resolver,FuncionBtn, RBtn, SBtn, tamañoBtn,ErrorBtn;
    private ArrayList<String> funcion = new ArrayList<String>();
    private EditText txtS,txtR,txtFuncion, txtTam,txtError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bairstow);
        FuncionBtn = (Button) findViewById(R.id.funcion);
        resolver = (Button)findViewById(R.id.resolver);
        RBtn = (Button)findViewById(R.id.RButt);
        ErrorBtn = (Button)findViewById(R.id.errorButt);
        SBtn = (Button)findViewById(R.id.SButt);
        tamañoBtn = (Button)findViewById(R.id.gradoEc);
        txtFuncion = (EditText)findViewById(R.id.EcuacionBr);
        txtR = (EditText)findViewById(R.id.r);
        txtS = (EditText)findViewById(R.id.s);
        txtError = (EditText)findViewById(R.id.error);
        txtTam = (EditText)findViewById(R.id.Grado);
        resultado = (TextView)findViewById(R.id.resultadoBr);

        if(RBtn.isEnabled() && SBtn.isEnabled() && ErrorBtn.isEnabled() && FuncionBtn.isEnabled() && tamañoBtn.isEnabled())if (resolver.isEnabled())resolver.setEnabled(false);


        FuncionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String leerFuncion = txtFuncion.getText().toString();
                if(leerFuncion == null||leerFuncion.trim().equals("")) Toast.makeText(getBaseContext(),"Ingresa funcion empezando por literal mas grande", Toast.LENGTH_SHORT).show();
                 else{
                    ((EditText) findViewById(R.id.EcuacionBr)).setText(" ");
                    funcion.add(leerFuncion.trim());
                    termina += 1;
                    if(termina >= tamañoEc){
                        if(FuncionBtn.isEnabled()) {
                            FuncionBtn.setEnabled(false);
                        }}
                    if(!tamañoBtn.isEnabled() && !FuncionBtn.isEnabled() && !ErrorBtn.isEnabled() && !RBtn.isEnabled() && SBtn.isEnabled()) {
                        if (!resolver.isEnabled()) {
                            resolver.setEnabled(true);}}}}});

        tamañoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String leerGrado = txtTam.getText().toString().trim();
                tamañoEc = Integer.parseInt(leerGrado);
                if(leerGrado == null||leerGrado.trim().equals("")) Toast.makeText(getBaseContext(),"Ingresa Grado", Toast.LENGTH_SHORT).show();
                else if (leerGrado.trim().equals("0")||leerGrado.trim().equals("1")){
                    ((EditText) findViewById(R.id.Grado)).setText(" ");
                    Toast.makeText(getBaseContext(),"No hay grado"+leerGrado, Toast.LENGTH_LONG).show();
                } else{
                    ((EditText) findViewById(R.id.Grado)).setText(" ");
                    if(tamañoBtn.isEnabled()) tamañoBtn.setEnabled(false);
                    if(!tamañoBtn.isEnabled() && !FuncionBtn.isEnabled() && !ErrorBtn.isEnabled() && !RBtn.isEnabled() && !SBtn.isEnabled()) {
                        if (!resolver.isEnabled()) resolver.setEnabled(true);
                    }tamañoEc = Integer.parseInt(leerGrado)+1;}}});

        ErrorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String leerError = txtError.getText().toString();
                if(leerError == null||leerError.trim().equals("")) Toast.makeText(getBaseContext(),"Ingresa Error", Toast.LENGTH_SHORT).show();
                 else{
                    ((EditText) findViewById(R.id.error)).setText(" ");
                    if(ErrorBtn.isEnabled()) ErrorBtn.setEnabled(false);
                    if(!tamañoBtn.isEnabled() && !FuncionBtn.isEnabled() && !ErrorBtn.isEnabled() && !RBtn.isEnabled() && !SBtn.isEnabled()) {
                        if (!resolver.isEnabled()) resolver.setEnabled(true);
                    }error = Double.parseDouble(leerError);}}});

        RBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String leerR = txtR.getText().toString().trim();
                if(leerR == null||leerR.trim().equals(""))Toast.makeText(getBaseContext(),"Ingresa R", Toast.LENGTH_SHORT).show();
                 else{
                    ((EditText) findViewById(R.id.r)).setText(" ");
                    if(RBtn.isEnabled()) RBtn.setEnabled(false);
                    if(!tamañoBtn.isEnabled() && !FuncionBtn.isEnabled() && !ErrorBtn.isEnabled() && !RBtn.isEnabled() && !SBtn.isEnabled()) {
                        if (!resolver.isEnabled())
                            resolver.setEnabled(true);}
                    r = Double.parseDouble(leerR);}}});

        SBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String leerS = txtS.getText().toString().trim();
                if(leerS == null||leerS.trim().equals(""))Toast.makeText(getBaseContext(),"Ingresa S", Toast.LENGTH_SHORT).show();
                else{
                    ((EditText) findViewById(R.id.s)).setText(" ");
                    if(SBtn.isEnabled()) SBtn.setEnabled(false);
                    if(!tamañoBtn.isEnabled() && !FuncionBtn.isEnabled() && !ErrorBtn.isEnabled() && !RBtn.isEnabled() && !SBtn.isEnabled()) {
                        if (!resolver.isEnabled()) resolver.setEnabled(true);
                    }s = Double.parseDouble(leerS);}}});

        resolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bairstow Bw = new Bairstow();
                double[] tam = new double[funcion.size()];

                for (int i = 0; i < funcion.size(); i++) tam[i] =  Double.parseDouble(funcion.get(i));

                double[] ren = new double[tam.length];
                double[] im = new double[tam.length];
                ArrayList<String> res = Bw.Bairstow(tam, r, s, ren, im, error, tamañoEc);
                String result = " ";

                for(int i=0;i<res.size();i++) result += res.get(i) + "\n";
                resultado.setText("El resultado es: \n" + result);
                funcion.clear();

                if(!RBtn.isEnabled() || !SBtn.isEnabled() || !ErrorBtn.isEnabled() || !FuncionBtn.isEnabled() || !tamañoBtn.isEnabled()) {
                    RBtn.setEnabled(true);
                    SBtn.setEnabled(true);
                    ErrorBtn.setEnabled(true);
                    FuncionBtn.setEnabled(true);
                    tamañoBtn.setEnabled(true);}
                if(resolver.isEnabled()) resolver.setEnabled(false);}});
    }}
