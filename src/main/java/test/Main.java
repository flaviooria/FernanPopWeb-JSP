package test;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        /*File f = new File("WEB-INF/config/config.properties");
        if (f.exists()) System.out.println("existe");
        System.out.println(f.getPath());
        System.out.println(f.getAbsolutePath());*/

        String allNombre = "flavio oria pinto";
        StringBuffer strbf = new StringBuffer();
        Matcher match = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(allNombre);
        while(match.find())
        {
            match.appendReplacement(strbf, match.group(1).toUpperCase() + match.group(2).toLowerCase());
        }
        System.out.println(match.appendTail(strbf));
    }
}
