package metodos.rueschan.mx.metodos;

/**
 * Created by Adrian on 22/04/2017.
 */
public class Cramer {
    public Cramer() {}

    public float determinante(int y, float rc[][]) {
        if (rc.length == 2) {
            float det = rc[0][0] * rc[1][1] - rc[0][1] * rc[1][0];
            return det;
        }
        float det = 0;
        for (int i = 0; i < rc.length; i++) {
            float[][] prueb = this.MatrizPre(y, i, rc);
            det = (float) (det + Math.pow(-1, i + y) * rc[y][i] * this.determinante(y, prueb));
        }return det;
    }

    private float[][] MatrizPre(int y, int x, float[][] rc) {
        float[][] prueb = new float[rc.length - 1][rc.length - 1];
        int count1 = 0;
        int count2;
        for (int i = 0; i < rc.length; i++) {
            if (i != y) {
                count2 = 0;
                for (int j = 0; j < rc.length; j++) {
                    if (j != x) {
                        prueb[count1][count2] = rc[i][j];
                        count2++;
                    }}
                count1++;
            }}
        return prueb;

    }

    public float[][] cambiar(float rc[][], float ren[], int pos) {
        float changed[][] = new float[rc.length][rc.length];
        for (int i = 0; i < rc.length; i++) {
            for (int j = 0; j < rc.length; j++) {
                if (j == pos) {
                    changed[i][j] = ren[i];
                } else {
                    changed[i][j] = rc[i][j];
                }}}
        return changed;
    }


    public float[] cramer(float[][] rc, float[] ren) {
        float MetodoCram[] = new float[ren.length];
        float temp;
        float det = determinante(0, rc);
        if (det == 0) {
            float[] n=null;
            return n;}
        for (int i = 0; i < rc.length; i++) {
            float c[][] = cambiar(rc, ren, i);
            temp = determinante(0, c);
            MetodoCram[i] = temp / det;}
        return MetodoCram;
    }}

