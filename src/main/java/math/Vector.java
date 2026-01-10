package math;

public class Vector {

    double[] elements;

    public Vector(double[] eles) {
        this.elements = eles;
    }
    public Vector() {
        this.elements = new double[19];
    }

    public int size() {
        return elements.length;
    }
    public double getElement(int idx) {
        return elements[idx];
    }
    public void setElement(int idx, double ele) {
        this.elements[idx] = ele;
    }
    public double[] getDoubles() {return elements;}

    public double norm() {
        double lenSqared = 0;
        for (int i = 0; i < this.size(); i++) {
            lenSqared += elements[i] * elements[i];
        }
        return Math.sqrt(lenSqared);
    }
    public void normalize() {
        double len = this.norm();
        for (int i = 0; i < elements.length; i++) {
            elements[i] /= len;
        }
    }


    public static Vector addVectors(Vector v1, Vector v2) {
        if (v1.size() != v2.size()) throw new IllegalArgumentException("Dimensions of vectors must match");
        double[] v = new double[v1.size()];
        for (int i = 0; i < v1.size(); i++) {
            v[i] = v1.getElement(i) + v2.getElement(i);
        }
        return new Vector(v);
    }
    public static Vector subtractVectors(Vector v1, Vector v2) {
        if (v1.size() != v2.size()) throw new IllegalArgumentException("Dimensions of vectors must match");
        double[] v = new double[v1.size()];
        for (int i = 0; i < v1.size(); i++) {
            v[i] = v1.getElement(i) - v2.getElement(i);
        }
        return new Vector(v);
    }
    public static Vector scalarMultiply(double scalar, Vector v1) {
        Vector v = new Vector(new double[v1.size()]);
        for (int i = 0; i < v1.size(); i++) {
            v.setElement(i, scalar * v1.getElement(i));
        }
        return v;
    }
    public static double dotProduct(Vector v1, Vector v2) {
        if (v1.size() != v2.size()) throw new IllegalArgumentException("Dimensions of vectors must match");
        double dotProd = 0;
        for (int i = 0; i < v1.size(); i++) {
            dotProd += v1.getElement(i) * v2.getElement(i);
        }
        return dotProd;
    }
    public static Vector project(Vector projected, Vector goal) {
        double scalar = Vector.dotProduct(projected, goal) / (goal.norm()* goal.norm());
        return Vector.scalarMultiply(scalar, goal);
    }

    public static Vector StringToVector(String str) { //
        return new Vector(new double[19]);
    }

}
