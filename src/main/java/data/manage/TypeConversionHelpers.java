package data.manage;

public class TypeConversionHelpers {
    public static String doubleArrToString(double[] arr) {
        if (arr == null || arr.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            sb.append(",").append(arr[i]);
        }
        return sb.toString();
    }
    public static String intArrToString(int[] arr) {
        if (arr == null || arr.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            sb.append(",").append(arr[i]);
        }
        return sb.toString();
    }
    public static int[] StringToIntArr(String s) {
        if (s == null || s.isBlank()) {return new int[0];}
        String[] parts = s.split(",");
        int[] result = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            result[i] = Integer.parseInt(parts[i].trim());
        }
        return result;
    }
}
