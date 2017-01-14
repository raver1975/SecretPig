import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Paul on 1/13/2017.
 */
public class Main {

    //private final PrintWriter out;
    //String outFile="saved.txt";



    public Main() {
//        try {
//            out = new PrintWriter(outFile);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        while(true) {
            getList();
        }
    }

    private void getList() {
        URL url = null;
        try {
            Random rand = new Random();
            int ran = rand.nextInt(78888000);
            url = new URL("https://api.github.com/repositories?since=" + ran);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream is = null;
        try {
            is = url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (is != null) {
            int ptr = 0;
            StringBuffer buffer = new StringBuffer();
            try {
                while ((ptr = is.read()) != -1) {
                    buffer.append((char) ptr);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String githubrepo = buffer.toString();
//                String[] data = location.split(";");
//                System.out.println(Arrays.toString(data));
            try {
                JSONArray jso = new JSONArray(githubrepo);
                for (int i=0;i<jso.length();i++){
                    JSONObject jjo=jso.getJSONObject(i);
                    String go=jjo.getString("html_url");
                    runPython(go);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }

    private void runPython(String go) {
        print(go);
        try {
            String line;
            File f=new File(".");
            String path=f.getAbsolutePath().substring(0,f.getAbsolutePath().length()-1);
            path=path.replace("\\","/");
            String exec="python3 "+path+"truffleHog.py "+go;
            Process p = Runtime.getRuntime().exec(exec);
            BufferedReader bri = new BufferedReader
                    (new InputStreamReader(p.getInputStream()));
            BufferedReader bre = new BufferedReader
                    (new InputStreamReader(p.getErrorStream()));
            while ((line = bri.readLine()) != null) {
                print(line);
            }
            bri.close();
            while ((line = bre.readLine()) != null) {
                //System.err.println(line);
            }
            bre.close();
            p.waitFor();
//            System.out.println("Done. "+go);
            print("------------------------------------------------------------------------");

        }
        catch (Exception err) {
            err.printStackTrace();
        }
    }

    private void print(String print) {
//        out.println(print);
//        out.flush();
        System.out.println(print);
    }

    public static void main(String[] args) {
        new Main();
    }

}
