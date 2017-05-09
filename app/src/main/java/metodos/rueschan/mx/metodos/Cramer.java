package metodos.rueschan.mx.metodos;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Adrian on 22/04/2017.
 */
public class Cramer {
    public Cramer() {}


    public float determinante(int j, float a[][]) {

        if (a.length == 2) {
            float det = a[0][0] * a[1][1] - a[0][1] * a[1][0];

            return det;
        }

        float det = 0;
        for (int i = 0; i < a.length; i++) {
            float[][] temp = this.SubMatriz(j, i, a);
            det = (float) (det + Math.pow(-1, i + j) * a[j][i] * this.determinante(j, temp));
        }

        return det;

    }

    private float[][] SubMatriz(int j, int i, float[][] a) {
        float[][] temp = new float[a.length - 1][a.length - 1];
        int count1 = 0;
        int count2 = 0;

        for (int k = 0; k < a.length; k++) {
            if (k != j) {

                count2 = 0;
                for (int l = 0; l < a.length; l++) {
                    if (l != i) {
                        temp[count1][count2] = a[k][l];
                        count2++;
                    }
                }
                count1++;
            }

        }

        return temp;

    }

    public float[][] sustituye(float a[][], float b[], int pos) {

        float c[][] = new float[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (j == pos) {
                    c[i][j] = b[i];
                } else {
                    c[i][j] = a[i][j];
                }
            }

        }

        return c;
    }


    public float[] cramer(float[][] a, float[] b) {
        float Rcramer[] = new float[b.length];



        float det = determinante(0, a);
        if (det == 0) {
            return null;
        }

        float detTemp;


        for (int i = 0; i < a.length; i++) {
            float c[][] = sustituye(a, b, i);
            detTemp = determinante(0, c);
            Rcramer[i] = detTemp / det;
        }
        return Rcramer;

    }

}

