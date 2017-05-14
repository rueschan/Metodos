package metodos.rueschan.mx.metodos;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by Adrian on 09/05/2017.
 */
public  class Bairstow extends Activity{
    public ArrayList<String> Bairstow(double arr[], double r0, double s0, double re[], double im[], double error, int tamaño) {
        int n = tamaño;
        double b[] = new double[n], c[] = new double[n];
        double e1 = 1, e2 = 1, E = error;
        double r=r0, s=s0,det, ds, dr;
        int iterMax = 100, i;
        ArrayList<String> resultado = new ArrayList<>();
        String string;

        for(int iter=0; iter< iterMax && n>3; iter++) {
            do {
                Derivada(arr, b, c, r, s, n);
                det = c[2]*c[2] - c[3]*c[1];
                if(det!=0) {
                    dr = (-b[1]*c[2] + b[0]*c[3])/det;
                    ds = (-b[0]*c[2] + b[1]*c[1])/det;
                    r = r+dr;
                    s = s+ds;
                    if(r!=0) e1 = Math.abs(dr/r)*100.0;
                    if(s!=0) e2 = Math.abs(ds/s)*100.0;
                } else {
                    r = 5*r+1;
                    s = s+1;
                    iter = 0;
                }}
            while ((e1 > E) && (e2 > E));
            obtenerRaices(r, s, re, im, n);
            n = n-2;
            for(i=0; i<n; i++)
                arr[i] = b[i+2];
            if (n < 4) break;
        }
        if(n==3) {
            r = -arr[1]/arr[2];
            s = -arr[0]/arr[2];
            obtenerRaices(r, s, re, im, n);
        }
        else {
            re[n-1] = -arr[0]/arr[1];
            im[n-1] = 0;}
        for(i=1; i<re.length; i++){
            string = ("X grado "+i+" = " + re[i] + ", imaginario:  " + im[i]);
            resultado.add(string);
        }return resultado;
    }

    public static void obtenerRaices(double r, double s, double re[], double im[], int n) {
        double d = r*r + 4*s;
        if(d > 0)
        {
            re[n-1] = (r + Math.sqrt(d))/2.0;
            re[n-2] = (r - Math.sqrt(d))/2.0;
            im[n-1] = 0;
            im[n-2] = 0;
        } else {
            re[n-1] = r/2.0;
            re[n-2] = re[n-1];
            im[n-1] = Math.sqrt(-d)/2.0;
            im[n-2] = -im[n-1];
        }}


    public static void Derivada(double a[], double b[], double c[], double r, double s, int n) {
        int i;
        b[n-1] = a[n-1];
        b[n-2] = a[n-2] + r*b[n-1];
        c[n-1] = b[n-1];
        c[n-2] = b[n-2] + r*c[n-1];
        for(i=n-3; i>=0; i--) {
            b[i] = a[i] + r*b[i+1] + s*b[i+2];
            c[i] = b[i] + r*c[i+1] + s*c[i+2];}}
}
