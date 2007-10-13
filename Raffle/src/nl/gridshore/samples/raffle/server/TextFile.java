package nl.gridshore.samples.raffle.server;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Jettro.Coenradie
 * Date: 10-okt-2007
 * Time: 21:51:14
 * To change this template use File | Settings | File Templates.
 */
public class TextFile extends ArrayList<String> {
    public TextFile() {
        throw new UnsupportedOperationException();
    }

    public static String read(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
            try {
                String s;
                while((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    public static void write(String filename, String text) {
        try {
            PrintWriter out = new PrintWriter(new File(filename).getAbsoluteFile());
            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public TextFile(String filename) {
        super(Arrays.asList(read(filename).split("\n")));
        if (get(0).equals("")) remove(0);
    }

    public void write(String fileName) {
        try {
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
            try {
                for(String item : this) {
                    out.println(item);
                }
            } finally {
                out.close();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
