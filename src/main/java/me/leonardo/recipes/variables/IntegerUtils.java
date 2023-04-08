package me.leonardo.recipes.variables;

public class IntegerUtils {

    public boolean isInteger(String str) {
        int num;

        try{
            num = Integer.parseInt(str);
            return true;
        }catch (Exception ignored) {
        }

        return false;
    }

}
