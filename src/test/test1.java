package test;

import java.io.IOException;

public class test1 {
    public static void main(String[] args) {
        try {
            Runtime.getRuntime().exec("shutdown -a");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
