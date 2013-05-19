/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yahoonewsremotecontrol;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import yahoonewsremotecontrol.GUI.FigureShower;
import yahoonewsremotecontrol.GUI.LoadingDialog;
import yahoonewsremotecontrol.GUI.LoginFrame;
import yahoonewsremotecontrol.GUI.ScaleIcon;
import yahoonewsremotecontrol.net.ServerConnector;

/**
 *
 * @author CCFLAB
 */
public class YahooNewsRemoteControl {
    public static void main(String[] args){
        
        LoginFrame lm = new LoginFrame();
        lm.setVisible(true);
        
//        ServerConnector sc = new ServerConnector();
//        if(!sc.connect("127.0.0.1", 12345)){
//            JOptionPane.showMessageDialog(m, "Cann't connect to server!", "ERROR", JOptionPane.WARNING_MESSAGE);
//            //m.dispose();
//            return;
//        }
        
        ServerConnector sc = lm.getServerConnector();
        if(sc == null){
            JOptionPane.showMessageDialog(lm, "Unknown Error!", "ERROR", JOptionPane.WARNING_MESSAGE);
            return;
        }
        lm.dispose();
        
        FigureShower m = new FigureShower();
        m.setVisible(true);
        ImageIcon lastImg = null;
        
        final LoadingDialog ld = new LoadingDialog(m, true);
        //ld.setVisible(true);
        FigureFileManager ffm = new FigureFileManager("temp");
        while(true){
            Command c = sc.getCommand();            
            if(c.cmd == Command.Cmd.ShowFigure){
                System.out.println(c.contend);
                
                new Thread(new Runnable() {
                    public void run(){
                        ld.setVisible(true);
                    }
                }).start();
                
                lastImg = ffm.getImageIcon(c.contend);
                m.showFigure(c.getTitle(), lastImg);
                
                 new Thread(new Runnable() {
                    public void run(){
                        ld.setVisible(false);
                    }
                }).start();
            }
            else if(c.cmd == Command.Cmd.Visiable){
                m.setVisible(true);
            }
            else if(c.cmd == Command.Cmd.Unvisiable){
                m.setVisible(false);
            }
            else if(c.cmd == Command.Cmd.ScaleLarge){
                m.Scale(1.1);
            }
            else if(c.cmd == Command.Cmd.ScaleSmall){
                m.Scale(0.9);
            }
            else if(c.cmd == Command.Cmd.showWeather){
                System.out.println(c.contend);
                m.showFigure(c.contend, lastImg);
            }
            else if(c.cmd == Command.Cmd.LeftEnd || c.cmd == Command.Cmd.RightEnd){
                m.setNewsTitle("No more Pictures.");
            }
            else if(c.cmd == Command.Cmd.Exit){
                m.dispose();
                break;
            }
            else{
                //JOptionPane.showMessageDialog(m, "Unknown command", "ERROR", JOptionPane.WARNING_MESSAGE);
                break;
            }
        }
        //m.dispose();        
    }
}
