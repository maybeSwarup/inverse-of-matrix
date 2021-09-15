package swarup.matrix;

import java.util.*;

public class matrix {
    public float mat[][];
    public float temp[][];
    public int m, n;
    cursor cu = new cursor();
    Scanner sc = new Scanner(System.in);

    public void enterMatrix(){
        System.out.print("Enter MxN deimensions -> ");
        n = sc.nextInt();
        cu.moveCursor("up", 1);
        cu.moveCursor("right", 25+String.valueOf(n).length());
        System.out.print("x");
        m = sc.nextInt();
        sc.nextLine();
        enterMatrix(m, n);
    }

    public void enterMatrix(float[][] mat) {
        this.mat = mat;
        m = mat.length;
        n = mat[0].length;
    }

    public void emptyMatrix(int m, int n) {
        float[][] mat = new float[m][n];
        this.mat = mat;
        this.m = m;
        this.n = n;
    }

    public void enterMatrix(int m, int n) {
        this.m = m;
        this.n = n;
        for (byte i = 0; i <= m; i++) {
            if (i == 0) {
                System.out.print(" _");
                for (byte j = 0; j < n; j++) {
                    if (j == 0) {
                        System.out.print("    ");
                    } else {
                        System.out.print("      ");
                    }
                }
                System.out.print("_ \n");
            } else if (i == m) {
                System.out.print("|");
                for (byte j = 0; j < n; j++) {
                    if (j == 0) {
                        System.out.print("_     ");
                    } else if (j == n - 1) {
                        System.out.print("     _");
                    } else {
                        System.out.print("      ");
                    }
                }
                System.out.print("|\n");
            } else {
                System.out.print("|");
                for (byte j = 0; j < n; j++) {
                    System.out.print("      ");
                }
                System.out.print("|\n");
            }
        }
        
        float[][] mat = new float[m][n];
        cu.moveCursor("up", m);
        for (byte i = 0; i < m; i++) {
            for (byte j = 1; j <= n; j++) {
                cu.moveCursor("right", j * 5 - 1);
                mat[i][j - 1] = sc.nextFloat();
                cu.moveCursor("up", 1);
            }
            cu.moveCursor("down", 1);
        }
        System.out.println();
        this.mat = mat;
    }

    public void displayMatrix() {
        for (byte i = 0; i < m; i++) {
            if(i == 0){
                for(byte j=0; j<n; j++){
                    if(j==0){
                        System.out.print(" _        ");
                    }
                    else if(j==n-1){
                        System.out.print("        _ \n");
                    }
                    else{
                        System.out.print("         ");
                    }
                }
            }
            if (i == m - 1) {
                for (byte j = 0; j < n; j++) {
                    if (j == 0) {
                        System.out.printf("|_%7.2f ", mat[i][j]);
                    } 
                    else if (j == n - 1) {
                        System.out.printf(" %7.2f_|\n", mat[i][j]);
                    } 
                    else {
                        System.out.printf(" %7.2f ", mat[i][j]);
                    }
                }
            }
            else {
                System.out.print("|");
                for (byte j = 0; j < n; j++) {
                    System.out.printf(" %7.2f ", mat[i][j]);
                }
                System.out.print("|\n");
            }
        }
        System.out.println();
    }

    public void displayMatrix(float[][] mat){
        matrix temp = new matrix();
        temp.enterMatrix(mat);
        temp.displayMatrix();
    }

    public float[][] trn() {
        float[][] trnMat = new float[n][m];
        for (byte i = 0; i < n; i++) {
            for (byte j = 0; j < m; j++) {
                trnMat[i][j] = mat[j][i];
            }
        }
        return trnMat;
    }

    public float[][] trn(float[][] mat){
        matrix trnMat = new matrix();
        trnMat.enterMatrix(mat);
        return trnMat.trn();
    }

    public float[][] subMat(byte a, byte b){
        float[][] subMat = new float[m-1][n-1];
        for(byte c=0, i=0; c<m; c++){
            if(c==a) continue;
            for(byte d=0, j=0; d<n; d++){
                if(d==b) continue;
                subMat[i][j++] = mat[c][d];
            }
            i++;
        }
        return subMat;
    }

    public float[][] minors(){
        float[][] minMat = new float[m][n];
        if(m==n){
            if(m==2){
                for(byte a=0; a<m; a++){
                    for(byte b=0; b<n; b++){
                        if(a==b){
                            if(a==0) minMat[a][b] = mat[a+1][b+1];
                            else minMat[a][b] = mat[a-1][b-1];
                        }
                        else minMat[a][b] = mat[b][a]; 

                        /*for(byte c=0; c<m; c++){
                            if(c==a) continue;
                            for(byte d=0; d<n; d++){
                                if(d==b) continue;
                                minMat[a][b] = mat[c][d];
                            }
                        }*/
                    }
                }
            }
            if(m>2){
                for(byte i=0; i<m; i++){
                    for(byte j=0; j<n;j++){
                        matrix subMat = new matrix();
                        subMat.enterMatrix(subMat(i,j));
                        minMat[i][j] = subMat.det();
                    }
                }
            }
        }
        return minMat;
    }

    public float[][] minors(float[][] minMat){
        matrix mat = new matrix();
        mat.enterMatrix(minMat);
        minMat = mat.minors();
        return minMat;
    }

    public float[][] cofactors(){
        float[][] coMat = minors();
        for(byte i=0, sign = 1; i<m; i++, sign *= -1){
            byte temp = sign;
            for(byte j=0; j<n; j++){
                coMat[i][j] = sign * coMat[i][j];
                sign *= -1;
            }
            sign = temp;
        }
        return coMat;
    }

    public float det(){
        float D = 0;
        if(m==n){
            float[] c = cofactors()[0], t = mat[0];
            for(byte i=0; i<n; i++){
                D += t[i] * c[i];
            }
        }
        return D;
    }

    public float[][] invMat(){
        float[][] invMat = new float[n][n];
        if(m==n){
            float D = det();
            if(D==0) System.out.println("The Determinant is "+D+"\nTherefore, the inverse of matrix A does not exists.");
            else System.out.println("The Determinant is "+D+", which is not equal to 0.\nTherefore, the inverse of matrix A exists.");
            float[][] trnMat = trn(cofactors());
            for(byte i=0; i<m; i++){
                for(byte j=0; j<n; j++){
                    invMat[i][j] = (1/D) * trnMat[i][j];
                }
            }
        }
        return invMat;
    }

    public float[][] multiplyMatrix(float[][] A, float[][] B){
        int m = Math.min(A.length, B.length);
        int n = Math.min(A[0].length, B[0].length);
        float[][] r = new float[m][n];
        for(byte i=0; i<m; i++){
            for(byte j=0; j<n; j++){
                for(byte k=0; k<A[0].length; k++){
                    r[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return r;
    }

    public boolean crossCheckInvMat(float[][] invMat){
        boolean flag = true;
        float[][] I =multiplyMatrix(this.mat, invMat);
        System.out.println("A x inv(A) = inv(A) x A = I\nAfter multiplying A wth inv(A)\nWe get,");
        displayMatrix(I);
        l1:for(byte i=0; i<m; i++){
            for(byte j=0; j<n; j++){
                if(i==j){
                    if(I[i][j]!=1){
                        flag = false;
                        break l1;
                    }
                }
                else{
                    if(I[i][j]!=0){
                        flag = false;
                        break l1;
                    }
                }
            }
        }
        if(flag) System.out.println("Which is an identity matrix I.\nTherefore, the resultant inverse of matrix A is indeed inverse of matrix A.");
        return flag;
    }

    public float[][] addMatrix(matrix A, matrix B){
        if(A.m==B.m && A.n==B.n){
            emptyMatrix(A.m, A.n);
            for(byte i=0; i<m; i++){
                for(byte j=0; j<n; j++){
                    mat[i][j] = A.mat[i][j] + B.mat[i][j];
                }
            }
        }
        else System.out.println("Addition is possible only if matrices are of same dimensions.");
        return mat;
    }
}