/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yahoonewsremotecontrol;

import java.io.File;
import java.util.HashSet;
import javax.swing.ImageIcon;
import yahoonewsremotecontrol.net.Downloader;

/**
 *
 * @author CCFLAB
 */
public class FigureFileManager {
    HashSet<String> set;
    String dirPath;
    
    static String formatFileName(String filename){
        filename = filename.replaceAll("<", "");
        filename = filename.replaceAll(">", "");
        filename = filename.replaceAll("\\\\", "");
        filename = filename.replaceAll("/", "");
        filename = filename.replaceAll("|", "");
        filename = filename.replaceAll(":", "");
        filename = filename.replaceAll("\"", "");
        filename = filename.replaceAll("\\*", "");
        filename = filename.replaceAll("\\?", "");    
        
        return filename;
    }
    
//    public static void main(String[] args){
//        System.out.println(formatFileName("aaa\\bb"));
//    }
    
    public FigureFileManager(String tempFilePath){
        dirPath = tempFilePath;
        
        set = new HashSet<String>();
        File dir = new File(dirPath);
        File file[] = dir.listFiles();
        for(File i : file){
            if(!i.isDirectory()){
                set.add(i.getName());
            }
        }
    }
    
    public ImageIcon getImageIcon(String url){
        String[] split = url.split("/");
        String filename = formatFileName(split[split.length-1]);
        
        if(set.contains(filename)){
            return new ImageIcon(dirPath + "\\"+ filename);
        }
        
        String downloadFilePath = dirPath + "\\" + filename;
        if(Downloader.httpDownload(url, downloadFilePath)){
            set.add(filename);
            return new ImageIcon(downloadFilePath);
        }
        
        return new ImageIcon("src\\yahoonewsremotecontrol\\GUI\\fail.jpg");
    }
}
