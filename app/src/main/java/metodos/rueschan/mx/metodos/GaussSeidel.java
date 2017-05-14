package metodos.rueschan.mx.metodos;

import java.util.Arrays;

/**
 * Created by Adrian on 09/05/2017.
 */

public class GaussSeidel {

    public GaussSeidel(){}

    public boolean Dominante(Float[][] mat)  {
        boolean[] lleno = new boolean[mat.length];
        int[] columnas = new int[mat.length];
        Arrays.fill(lleno, false);
        return hacerDominante(0, lleno, columnas, mat);
    }

    public boolean hacerDominante(int r, boolean[] V, int[] R, Float[][] matriz) {
        int n = matriz.length;
        double suma=0;
        if (r == matriz.length) {
            Float[][] matrizTem = new Float[n][n + 1];
            for (int i = 0; i < R.length; i++) {
                for (int j = 0; j < n + 1; j++)
                    matrizTem[i][j] = matriz[R[i]][j];
            }matriz = matrizTem;
            return true;}
        for (int x = 0; x < n; x++) {
            if (V[x]) continue;
            for (int j = 0; j < n; j++)
                suma += Math.abs(matriz[x][j]);
            if (2 * Math.abs(matriz[x][r]) > suma) {
                V[x] = true;
                R[r] = x;
                if (hacerDominante(r + 1, V, R, matriz))
                    return true;
                V[x] = false;}}
        return false;
    }



    public float[] resolver(Float[][] matriz, Float error, float[] matAnterior) {
        float[] anterior =  matAnterior;
        float[] nuevaMat = new float[matriz.length];
        while (true) {
            Float abs=0.0f;
            for (int x = 0; x < matriz.length; x++) {
                Float suma = matriz[x][matriz.length];
                for (int y = 0; y < matriz.length; y++)
                    if (y != x)
                        suma -= matriz[x][y]*nuevaMat[y];
                nuevaMat[x] = 1 / matriz[x][x] * suma;}
            for (int i = 0; i < nuevaMat.length; i++){
                abs += Math.abs((nuevaMat[i] - anterior
                        [i]) / anterior[i]);}
            if ((abs / nuevaMat.length) * 100 < error)
                break;
            anterior = nuevaMat;
        }return nuevaMat;}}

