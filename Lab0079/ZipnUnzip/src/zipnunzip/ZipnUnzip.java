package zipnunzip;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DeflaterInputStream;
import java.util.zip.InflaterInputStream;
import util.GetInput;
import view.Menu;
public class ZipnUnzip {
//    Zip:
    // pathSrc: đường dẫn chứa file sẽ được nén (kết quả)
    // fileZipName: tên file sau khi zip 
    // pathCompress: đường dẫn của file cần nén 
//    Output: Zip Status 
     public static boolean compressTo(String pathSrc, String fileZipName,String pathCompress) {
         boolean flag = false;
         FileInputStream fis = null;
         DeflaterInputStream dis = null;
         FileOutputStream fos = null;
         try {
             fis = new FileInputStream(pathCompress);
             dis = new DeflaterInputStream(fis);
             
//             fos = new FileOutputStream(pathSrc + "/" + fileZipName);
             fos = new FileOutputStream(fileZipName);

             int code;
             while((code = dis.read()) != -1){
                 fos.write(code);
             }
         } catch (FileNotFoundException ex) {
             Logger.getLogger(ZipnUnzip.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(ZipnUnzip.class.getName()).log(Level.SEVERE, null, ex);
         } finally{
             try {
                 if(fis != null)
                     try {
                         fis.close();
                     } catch (IOException ex) {
                         Logger.getLogger(ZipnUnzip.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 if(dis != null){
                     try {
                         dis.close();
                     } catch (IOException ex) {
                         Logger.getLogger(ZipnUnzip.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 if(fos != null)
                     fos.close();
                 System.out.println("Compression Successfull!");
                 flag = true;
             } catch (IOException ex) {
                 Logger.getLogger(ZipnUnzip.class.getName()).log(Level.SEVERE, null, ex);
             }
    }
         return flag;
     }
    
// Unzip:
    // input: pathZipFile: đường dẫn của file đã nén 
    // pathExtract: đường dẫn vị trí chứa file sẽ được giải nén 
    // Output: Unzip status 
    public static boolean extractTo(String pathZipFile, String pathExtract) {
       boolean flag = false;
         FileInputStream fis = null;
         InflaterInputStream iis = null;
         FileOutputStream fos = null;
         try {
             fis = new FileInputStream(pathZipFile);
             iis = new InflaterInputStream(fis);
             
             fos = new FileOutputStream(pathExtract);
             int code;
             while((code = iis.read()) != -1){
                 fos.write(code);
             }
         } catch (FileNotFoundException ex) {
             Logger.getLogger(ZipnUnzip.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(ZipnUnzip.class.getName()).log(Level.SEVERE, null, ex);
         } finally{
             try {
                 if(fis != null)
                     try {
                         fis.close();
                     } catch (IOException ex) {
                         Logger.getLogger(ZipnUnzip.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 if(iis != null){
                     try {
                         iis.close();
                     } catch (IOException ex) {
                         Logger.getLogger(ZipnUnzip.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 if(fos != null)
                     fos.close();
                 System.out.println("Extraction Successfull!");
                 flag = true;
             } catch (IOException ex) {
                 Logger.getLogger(ZipnUnzip.class.getName()).log(Level.SEVERE, null, ex);
             }
    }
         return flag;
    }
    
    

  
    public static void main(String[] args) {
        // TODO code application logic here
        Menu menu = new Menu();
        menu.addItem("1. Compression");
        menu.addItem("2. Extraction");
        menu.addItem("3. Exit");
        boolean confirm = false;
        do {
            System.out.println("---------Zipper program-------");
            menu.showMenu();
            int choice = menu.getChoice();

            switch (choice) {
                case 1 ->  {
                    System.out.println("---------Compression-------");
                    String pathCompress = GetInput.getString("File chua nen: ");
                    String pathSrc = GetInput.getString("Vi tri file nen: ");
                    String fileZipName = GetInput.getString("Ten file nen: ");
                    compressTo(pathSrc,fileZipName,pathCompress);
                }
                case 2 ->  {
                    System.out.println("---------Extraction-------");
                    String pathZipFile = GetInput.getString("Enter Source File: ");
                    String pathExtract = GetInput.getString("Enter Destination Folder: ");
                    extractTo(pathZipFile, pathExtract);

                }
                case 3 -> confirm = menu.confirm("Do you want to quit?(Y/N): ");

            }

        } while (!confirm);

    }

}
