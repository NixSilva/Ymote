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
        Exception, unkonwnCommand, ShowFigure
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
        String[] splitCmd = command.split("\t");

        if (splitCmd.length == 2) {
            cmd = Cmd.ShowFigure;
            title = splitCmd[0]; 
            contend = splitCmd[1];
        } else {
            cmd = Cmd.unkonwnCommand;
            contend = command;
        }
    }
    
    public Command(Cmd cmd, String contend) {
        this.cmd = cmd;
        this.contend = contend;
    }
}
