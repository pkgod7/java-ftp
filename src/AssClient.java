import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
public class AssClient
{
    public JFrame frame=new JFrame("Client");
    public JPanel main=new JPanel();
    public JButton main1=new JButton("Upload",new ImageIcon("upload.png"));
    public JButton main2=new JButton("Download",new ImageIcon("download.png"));
    public JPanel main4=new JPanel();
    public CardLayout main5=new CardLayout();
    public JLabel main6=new JLabel("Status : Welcome to simple FTP");
    public Socket connection;
    public DataOutputStream output;
    public DataInputStream input;
    public JPanel setup=new JPanel();
    public JLabel setup1=new JLabel("IP Address");
    public JLabel setup2=new JLabel("Port Number");
    public static JTextField setup3=new JTextField("localhost");
    public JTextField setup4=new JTextField("99");
    public JButton setup5=new JButton("Connect");
    public JButton setup6[]=new JButton[13];
    public String setup7="setup3"; 
    public JPanel upload=new JPanel();
    public JButton upload1[];
    public int upload2=0;
    public int upload3=20; 
    public JPanel download=new JPanel();
    public JButton download1[];
    public int download2=0;
    public int download3=20;
    
    public AssClient()
    {
        setup1.setFont(new Font("SansSerif", Font.BOLD,9));
        setup1.setBounds(6,55,100,20);
        setup2.setFont(new Font("SansSerif", Font.BOLD,9));
        setup2.setBounds(6,100,100,20);
        setup3.setFont(new Font("SansSerif",Font.BOLD,30));
        setup3.setBackground(Color.DARK_GRAY);
        setup3.setForeground(Color.blue);
        setup3.setBounds(66,43,240,40);
        setup3.addMouseListener(new Listener2());
        setup4.setFont(new Font("SansSerif",Font.BOLD,30));
        setup4.setBackground(Color.white);
        setup4.setForeground(Color.blue);
        setup4.setBounds(66,93,240,40);
        setup4.addMouseListener(new Listener2());
        setup5.setBounds(80,160,170,30);
        setup5.addActionListener(new Listener1());
        for(int i=1; i<setup6.length; i++)
        {
            setup6[i]=new JButton();
            setup6[i].addActionListener(new Listener1());
        }
        setup6[1].setText("1");
        setup6[2].setText("2");
        setup6[3].setText("3");
        setup6[4].setText("4");
        setup6[5].setText("5");
        setup6[6].setText("6");
        setup6[7].setText("7");
        setup6[8].setText("8");
        setup6[9].setText("9");
        setup6[10].setText(".");
        setup6[11].setText("0");
        setup6[12].setText("←");
        setup6[1].setBounds(80,200,50,40);
        setup6[2].setBounds(140,200,50,40);
        setup6[3].setBounds(200,200,50,40);
        setup6[4].setBounds(80,250,50,40);
        setup6[5].setBounds(140,250,50,40);
        setup6[6].setBounds(200,250,50,40);
        setup6[7].setBounds(80,300,50,40);
        setup6[8].setBounds(140,300,50,40);
        setup6[9].setBounds(200,300,50,40);
        setup6[10].setBounds(80,350,50,40);
        setup6[11].setBounds(140,350,50,40);
        setup6[12].setBounds(200,350,50,40);
        setup.setLayout(null);
        setup.add(setup1);
        setup.add(setup2);
        setup.add(setup3);
        setup.add(setup4);
        setup.add(setup5);
        for(int i=1; i<setup6.length; i++)
        {
            setup.add(setup6[i]);
        }
       
        main1.setBackground(Color.lightGray);
        main1.setOpaque(true);
        main1.setBorderPainted(false);
        main1.setEnabled(false);
        main1.setBounds(35,10,170,64);
        main1.addActionListener(new Listener1());
        main2.setBackground(Color.lightGray);
        main2.setOpaque(true);
        main2.setBorderPainted(false);
        main2.setEnabled(false);
        main2.setBounds(215,10,170,64);
        main2.addActionListener(new Listener1());
        main4.setBackground(Color.white);
        main4.setOpaque(true);
        main4.setBounds(35,80,350,430);
        main4.setLayout(main5);
        main4.add(setup,"0");
        main6.setFont(new Font("SansSerif", Font.BOLD,12));
        main6.setBounds(40,520,500,20);
        main.setOpaque(true);
        main.setBackground(Color.WHITE);
        main.setLayout(null);
        main.add(main1);
        main.add(main2);
        main.add(main4);
        main.add(main6);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,600);
        frame.add(main);
        SwingUtilities.updateComponentTreeUI(frame);
    }
    public void uploading(String x)
    {
         try
         {
             output.writeUTF("upload");
             String fileName=x;
             output.writeUTF(fileName);
             FileInputStream fin=new FileInputStream(new File("ahmad/"+fileName));
             int ch;
             do
             {
                 ch=fin.read();
                 output.writeUTF(String.valueOf(ch));
             }
             while(ch!=-1);
             fin.close();
             main6.setText("Status : Upload success, \"upload_"+fileName+"\"\n");
             upload=new JPanel();
             upload.setLayout(null);
             upload2=0;
             upload3=20;
             File list[]=new File("ahmad").listFiles();
             upload1=new JButton[list.length];
             for(File file:list)
             {
                 if(file.isFile())
                 {
                     upload1[upload2]=new JButton(""+file.getName());
                     upload1[upload2].addActionListener(new Listener1());
                     upload.add(upload1[upload2]);
                     upload.setPreferredSize(new Dimension(300,list.length*27));
                     upload1[upload2].setBounds(10,upload3,280,20);
                     upload2++;
                     upload3+=25;
                 }
             }
             main4.add(new JScrollPane(upload),"1");
             main5.show(main4,"1");
         }
         catch(Exception err)
         {
         }
    }
    public void downloading(String x)
    {
         try
         {
             output.writeUTF("download");
             String fileName=x;
             output.writeUTF(fileName);
             FileOutputStream fout=new FileOutputStream(new File("ahmad/download_"+fileName));
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
             main6.setText("Status : Download success, \"download_"+fileName+"\"\n");
             output.writeUTF("hola");
             String temp1=input.readUTF();
             download=new JPanel();
             download.setLayout(null);
             download2=0;
             download3=20;
             download1=new JButton[Integer.parseInt(temp1)];
             output.writeUTF("hola2");
             for(int i=0; i<Integer.parseInt(temp1); i++)
             {
                 download1[download2]=new JButton(""+input.readUTF());
                 download1[download2].addActionListener(new Listener1());
                 download.add(download1[download2]);
                 download.setPreferredSize(new Dimension(300,Integer.parseInt(temp1)*27));
                 download1[download2].setBounds(10,download3,280,20);
                 download2++;
                 download3+=25;
             }
             main4.add(new JScrollPane(download),"2");
             main5.show(main4,"2");
         }
         catch(Exception err)
         {
         }
    }
    class Listener1 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(setup7.equals("setup3"))
            {
                if(e.getSource()==setup6[1])
                {
                    setup3.setText(setup3.getText()+"1");
                }
                else if(e.getSource()==setup6[2])
                {
                    setup3.setText(setup3.getText()+"2");
                }
                else if(e.getSource()==setup6[3])
                {
                    setup3.setText(setup3.getText()+"3");
                }
                else if(e.getSource()==setup6[4])
                {
                    setup3.setText(setup3.getText()+"4");
                }
                else if(e.getSource()==setup6[5])
                {
                    setup3.setText(setup3.getText()+"5");
                }
                else if(e.getSource()==setup6[6])
                {
                    setup3.setText(setup3.getText()+"6");
                }
                else if(e.getSource()==setup6[7])
                {
                    setup3.setText(setup3.getText()+"7");
                }
                else if(e.getSource()==setup6[8])
                {
                    setup3.setText(setup3.getText()+"8");
                }
                else if(e.getSource()==setup6[9])
                {
                    setup3.setText(setup3.getText()+"9");
                }
                else if(e.getSource()==setup6[10])
                {
                    setup3.setText(setup3.getText()+".");
                }
                else if(e.getSource()==setup6[11])
                {
                    setup3.setText(setup3.getText()+"0");
                }
                else if(e.getSource()==setup6[12])
                {
                    if(setup3.getText().length()>0)
                    {
                        setup3.setText(setup3.getText().substring(0,setup3.getText().length()-1));
                    }
                }
            }
            else if(setup7.equals("setup4"))
            {
                if(e.getSource()==setup6[1])
                {
                    setup4.setText(setup4.getText()+"1");
                }
                else if(e.getSource()==setup6[2])
                {
                    setup4.setText(setup4.getText()+"2");
                }
                else if(e.getSource()==setup6[3])
                {
                    setup4.setText(setup4.getText()+"3");
                }
                else if(e.getSource()==setup6[4])
                {
                    setup4.setText(setup4.getText()+"4");
                }
                else if(e.getSource()==setup6[5])
                {
                    setup4.setText(setup4.getText()+"5");
                }
                else if(e.getSource()==setup6[6])
                {
                    setup4.setText(setup4.getText()+"6");
                }
                else if(e.getSource()==setup6[7])
                {
                    setup4.setText(setup4.getText()+"7");
                }
                else if(e.getSource()==setup6[8])
                {
                    setup4.setText(setup4.getText()+"8");
                }
                else if(e.getSource()==setup6[9])
                {
                    setup4.setText(setup4.getText()+"9");
                }
                else if(e.getSource()==setup6[10])
                {
                    setup4.setText(setup4.getText()+".");
                }
                else if(e.getSource()==setup6[11])
                {
                    setup4.setText(setup4.getText()+"0");
                }
                else if(e.getSource()==setup6[12])
                {
                    if(setup4.getText().length()>0)
                    {
                        setup4.setText(setup4.getText().substring(0,setup4.getText().length()-1));
                    }
                }
            }
            if(e.getSource()==setup5)
            {
                try
                {
                    connection=new Socket(setup3.getText(),Integer.parseInt(setup4.getText()));
                    output=new DataOutputStream(connection.getOutputStream());
                    input=new DataInputStream(connection.getInputStream());
                    main6.setText("Status : FTP is ready");
                    output.writeUTF("connect");
                    output.writeUTF(""+InetAddress.getLocalHost().getHostAddress());
                    setup3.setBackground(Color.white);
                    setup3.setEnabled(false);
                    setup3=new JTextField();
                    setup4.setBackground(Color.white);
                    setup4.setEnabled(false);
                    setup4=new JTextField();
                    setup5.setEnabled(false);
                    for(int i=1; i<setup6.length; i++)
                    {
                        setup6[i].setEnabled(false);
                    }
                    main1.setEnabled(true);
                    main2.setEnabled(true);
                }
                catch(Exception err)
                {
                    main6.setText("Status : Incorrect ip address or port number.");                    
                }
            }
            else if(e.getSource()==main1)
            {
                upload=new JPanel();
                upload.setLayout(null);
                upload2=0;
                upload3=20;
                File list[]=new File("ahmad").listFiles();
                main6.setText("Status : My files ("+list.length+")");
                upload1=new JButton[list.length];
                for(File file:list)
                {
                    if(file.isFile())
                    {
                        upload1[upload2]=new JButton(""+file.getName());
                        upload1[upload2].addActionListener(new Listener1());
                        upload.add(upload1[upload2]);
                        upload.setPreferredSize(new Dimension(300,list.length*27));
                        upload1[upload2].setBounds(10,upload3,280,20);
                        upload2++;
                        upload3+=25;
                    }
                }
                main4.add(new JScrollPane(upload),"1");
                main5.show(main4,"1");
            }
            else if(e.getSource()==main2)
            {
                try
                {
                    output.writeUTF("hola");
                    String temp1=input.readUTF();
                    main6.setText("Status : Server files ("+temp1+")");
                    download=new JPanel();
                    download.setLayout(null);
                    download2=0;
                    download3=20;
                    download1=new JButton[Integer.parseInt(temp1)];
                    output.writeUTF("hola2");
                    for(int i=0; i<Integer.parseInt(temp1); i++)
                    {
                        download1[download2]=new JButton(""+input.readUTF());
                        download1[download2].addActionListener(new Listener1());
                        download.add(download1[download2]);
                        download.setPreferredSize(new Dimension(300,Integer.parseInt(temp1)*27));
                        download1[download2].setBounds(10,download3,280,20);
                        download2++;
                        download3+=25;
                    }
                    main4.add(new JScrollPane(download),"2");
                    main5.show(main4,"2");
                }
                catch(Exception err)
                {
                }
            }
            for(int i=0; i<upload2; i++)
            {
                if(e.getSource()==upload1[i])
                {
                     main6.setText("Status : "+upload1[i].getText());
                     uploading(upload1[i].getText());
                }
            }
            for(int i=0; i<download2; i++)
            {
                if(e.getSource()==download1[i])
                {
                     main6.setText("Status : "+download1[i].getText());
                     downloading(download1[i].getText());
                }
            }
        }
    }
    class Listener2 extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            if(e.getSource()==setup3)
            {
                setup7="setup3";
                setup3.setBackground(Color.DARK_GRAY);
                setup4.setBackground(Color.white);
            }
            else if(e.getSource()==setup4)
            {
                setup7="setup4";
                setup3.setBackground(Color.white);
                setup4.setBackground(Color.DARK_GRAY);
            }
        }
    }
    public static void main(String args[])
    {
        new AssClient();
        AssServerRMI rmiServer;
       try
       {
           rmiServer=(AssServerRMI)(LocateRegistry.getRegistry(setup3.getText(),997).lookup("connection1"));
           rmiServer.establish(InetAddress.getLocalHost().getHostAddress());
       }
       catch(Exception err)
       {
       }
    }
}
