import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class AssServer2 extends Thread
{
    public AssServer1 x1;
    public String x2="";
    public Socket connection;
    public DataOutputStream output;
    public DataInputStream input;
    public AssServer2(String x)
    {
        x2=x;
    }
    public void run()
    {
        try
        {
            x1=new AssServer1(x2);
            connection=new ServerSocket(Integer.parseInt(x2)).accept();
            output=new DataOutputStream(connection.getOutputStream());
            input=new DataInputStream(connection.getInputStream());
            String temp1="";
            while(true)
            {
                String menu=input.readUTF();
                if(menu.equals("hola"))
                {
                    File list[]=new File("ahmad").listFiles();
                    try
                    {
                        output.writeUTF(""+list.length);
                    }
                    catch(Exception err)
                    {
                    }
                }
                else if(menu.equals("hola2"))
                {
                    File list[]=new File("ahmad").listFiles();
                    try
                    {
                        for(File file:list)
                        {
                            if(file.isFile())
                            {
                                output.writeUTF(file.getName());
                            }
                        }
                    }
                    catch(Exception err)
                    {
                    }
                }
                else if(menu.equals("upload"))
                {
                    String fileName="ahmad/upload_"+input.readUTF();
                    FileOutputStream fout=new FileOutputStream(new File(fileName));
                    int ch;
                    String temp;
                    do
                    {
                        temp=input.readUTF();
                        ch=Integer.parseInt(temp);
                        if(ch!=-1)
                        {
                            fout.write(ch);
                        }
                    }
                    while(ch!=-1);
                    fout.close();
                    x1.main5.setText(x1.main5.getText()+"Client ("+temp1+") send ("+fileName.substring(6)+")\n");
                }
                else if(menu.equals("download"))
                {
                    String filename="ahmad/"+input.readUTF();
                    FileInputStream fin=new FileInputStream(new File(filename));
                    int ch;
                    do
                    {
                        ch=fin.read();
                        output.writeUTF(String.valueOf(ch));
                    }
                    while(ch!=-1);
                    fin.close();
                    x1.main5.setText(x1.main5.getText()+"Client ("+temp1+") get (download_"+filename.substring(6)+")\n");
                }
                else if(menu.equals("connect"))
                {
                    temp1=input.readUTF();
                    x1.main5.setText(x1.main5.getText()+"Client ("+temp1+") connected\n");
                }
            }
        }
        catch(Exception err)
        {
        }
    }
}
