package pointofsale;
import java.util.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class globalway {
    static String savefile(String path) {
        File f = new File(path);
        try {
            FileInputStream fis = new FileInputStream(f);
            String ext = path.substring(path.lastIndexOf("."));
            System.out.println("Extension" + ext);
            String fpath = "src\\pointofsale\\icons\\" + (new Date().getTime()) + "" + ext;
            FileOutputStream fos = new FileOutputStream(fpath);
            long fsize = f.length();
            int r = 0, count = 0;
            byte b[] = new byte[100000];
            while (true) {
                r = fis.read(b, 0, b.length);
                fos.write(b, 0, r);
                count = count + r;
                if (count == fsize) {
                    break;
                }

            }
            System.out.println("File copied");
            return fpath;
        }
        catch(Exception e1){
            e1.printStackTrace();
        }
        return null;
}
}