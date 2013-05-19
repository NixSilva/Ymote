/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yahoonewsremotecontrol;

/**
 *
 * @author CCFLAB
 */
public class Command {
    Cmd cmd;
    String title;
    String contend;
    
    public static enum Cmd{
        Exception, unkonwnCommand, ShowFigure, showWeather, ScaleLarge, ScaleSmall, Visiable, Unvisiable, LeftEnd, RightEnd, Exit
    }
    
    public void setTitle(String s){
        title = s;
    }
    public void setCmd(Cmd cmd){
        this.cmd = cmd;
    }
    public void setContend(String contend){
        this.contend = contend;
    }
    public String getContend(){
        return this.contend;
    }
    public Cmd getCmd(){
        return this.cmd;
    }
    public String getTitle(){
        return title;
    }
    
    public Command(String command) {
        if (command.charAt(0) == 's') {
            String[] splitCmd = command.split("\t");
            if (splitCmd.length == 3) {
                cmd = Cmd.ShowFigure;
                title = splitCmd[1];
                contend = splitCmd[2];
            } 
        }
        
        else if(command.charAt(0) == 'w'){
            String[] splitCmd = command.split("\t");
            if (splitCmd.length == 2) {
                cmd = Cmd.showWeather;
                //title = splitCmd[1];
                contend = splitCmd[1];
            } 
        }
        
        else if(command.charAt(0) == 'n'){
            cmd = Cmd.Visiable;
        }
        
        else if(command.charAt(0) == 'f'){
            cmd = Cmd.Unvisiable;
        }
        
        else if(command.charAt(0) == 'i'){
            cmd = Cmd.ScaleLarge;
        }
        
        else if(command.charAt(0) == 'o'){
            cmd = Cmd.ScaleSmall;
        }
        
        else if(command.charAt(0) == 'e'){
            cmd = Cmd.Exit;
        }
        
        
        
        else if(command.equals("-1")){
            cmd = Cmd.LeftEnd;
        }
        
        else if(command.equals("-2")){
            cmd = Cmd.RightEnd;
        }
        
        else {
                cmd = Cmd.unkonwnCommand;
                contend = command;
            }
    }
    
    public Command(Cmd cmd, String contend) {
        this.cmd = cmd;
        this.contend = contend;
    }
}
