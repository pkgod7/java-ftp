import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
public class AssServer1 extends UnicastRemoteObject implements AssServerRMI
{
    public JFrame frame=new JFrame("Server");
    public JPanel main=new JPanel();
    public JLabel main1=new JLabel("Port Number");
    public JTextField main2=new JTextField("99");
    public JButton main3=new JButton("Start");
    public JButton main4[]=new JButton[13];
    public JTextArea main5=new JTextArea();
    public JScrollPane main6=new JScrollPane(main5);
    public JLabel main7=new JLabel();
    public AssServer1() throws RemoteException
    {
        main1.setBounds(10,10,100,40);
        main2.setFont(new Font("SansSerif",Font.BOLD,30));
        main2.setForeground(Color.blue);
        main2.setBounds(90,10,150,40);
        main3.setBounds(50,120,170,30);
        main3.addActionListener(new Listener1());
        for(int i=1; i<main4.length; i++)
        {
            main4[i]=new JButton();
            main4[i].addActionListener(new Listener1());
        }
        main4[1].setText("1");
        main4[2].setText("2");
        main4[3].setText("3");
        main4[4].setText("4");
        main4[5].setText("5");
        main4[6].setText("6");
        main4[7].setText("7");
        main4[8].setText("8");
        main4[9].setText("9");
        main4[10].setText(".");
        main4[11].setText("0");
        main4[12].setText("←");
        main4[1].setBounds(50,160,50,40);
        main4[2].setBounds(110,160,50,40);
        main4[3].setBounds(170,160,50,40);
        main4[4].setBounds(50,210,50,40);
        main4[5].setBounds(110,210,50,40);
        main4[6].setBounds(170,210,50,40);
        main4[7].setBounds(50,260,50,40);
        main4[8].setBounds(110,260,50,40);
        main4[9].setBounds(170,260,50,40);
        main4[10].setBounds(50,310,50,40);
        main4[11].setBounds(110,310,50,40);
        main4[12].setBounds(170,310,50,40);
        main5.setBounds(100,50,400,350);
        main5.setText("Welcome to simple FTP\n===================\n\n");
        main5.setBackground(Color.white);
        main5.setEditable(false);
        main5.setEnabled(false);
        main6.setBounds(260,120,300,240);
        try
        {
            main7.setText("IP ("+InetAddress.getLocalHost().getHostAddress()+")");
        }
        catch(Exception err)
        {
        }
        main7.setFont(new Font("SansSerif",Font.BOLD,30));
        main7.setBounds(300,8,300,40);
        main.setOpaque(true);
        main.setBackground(Color.WHITE);
        main.setLayout(null);
        main.add(main1);
        main.add(main2);
        main.add(main3);
        for(int i=1; i<main4.length; i++)
        {
            main.add(main4[i]);
        }
        main.add(main6);
        main.add(main7);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,450);
        frame.add(main);
        SwingUtilities.updateComponentTreeUI(frame);
    }
    public AssServer1(String x) throws RemoteException
    {
        try
        {
            LocateRegistry.createRegistry(997).rebind("connection1",this);
        }
        catch(Exception err)
        {
        }
        main1.setBounds(10,10,100,40);
        main2.setText(x);
        main2.setFont(new Font("SansSerif",Font.BOLD,30));
        main2.setForeground(Color.blue);
        main2.setBounds(90,10,150,40);
        main3.setBounds(50,120,170,30);
        main3.addActionListener(new Listener1());
        for(int i=1; i<main4.length; i++)
        {
            main4[i]=new JButton();
            main4[i].addActionListener(new Listener1());
        }
        main4[1].setText("1");
        main4[2].setText("2");
        main4[3].setText("3");
        main4[4].setText("4");
        main4[5].setText("5");
        main4[6].setText("6");
        main4[7].setText("7");
        main4[8].setText("8");
        main4[9].setText("9");
        main4[10].setText(".");
        main4[11].setText("0");
        main4[12].setText("←");
        main4[1].setBounds(50,160,50,40);
        main4[2].setBounds(110,160,50,40);
        main4[3].setBounds(170,160,50,40);
        main4[4].setBounds(50,210,50,40);
        main4[5].setBounds(110,210,50,40);
        main4[6].setBounds(170,210,50,40);
        main4[7].setBounds(50,260,50,40);
        main4[8].setBounds(110,260,50,40);
        main4[9].setBounds(170,260,50,40);
        main4[10].setBounds(50,310,50,40);
        main4[11].setBounds(110,310,50,40);
        main4[12].setBounds(170,310,50,40);
        main5.setBounds(100,50,400,350);
        main5.setText("Welcome to simple FTP\n===================\n\n");
        main5.setBackground(Color.white);
        main5.setEditable(false);
        main5.setEnabled(false);
        main6.setBounds(260,120,300,240);
        try
        {
            main7.setText("IP ("+InetAddress.getLocalHost().getHostAddress()+")");
        }
        catch(Exception err)
        {
        }
        main7.setFont(new Font("SansSerif",Font.BOLD,30));
        main7.setBounds(300,8,300,40);
        main2.setEnabled(false);
        main3.setEnabled(false);
        for(int i=1; i<main4.length; i++)
        {
            main4[i].setEnabled(false);
        }
        main5.setEnabled(true);
        main5.setText(main5.getText()+"Server listening to port "+main2.getText()+".....\n");
        main.setOpaque(true);
        main.setBackground(Color.WHITE);
        main.setLayout(null);
        main.add(main1);
        main.add(main2);
        main.add(main3);
        for(int i=1; i<main4.length; i++)
        {
            main.add(main4[i]);
        }
        main.add(main6);
        main.add(main7);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,450);
        frame.add(main);
        SwingUtilities.updateComponentTreeUI(frame);
    }
    public void establish(String x) throws RemoteException
    {
        main5.setText(main5.getText()+"RMI connection with "+x+".....\n");
    }
    class Listener1 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
                if(e.getSource()==main4[1])
                {
                    main2.setText(main2.getText()+"1");
                }
                else if(e.getSource()==main4[2])
                {
                    main2.setText(main2.getText()+"2");
                }
                else if(e.getSource()==main4[3])
                {
                    main2.setText(main2.getText()+"3");
                }
                else if(e.getSource()==main4[4])
                {
                    main2.setText(main2.getText()+"4");
                }
                else if(e.getSource()==main4[5])
                {
                    main2.setText(main2.getText()+"5");
                }
                else if(e.getSource()==main4[6])
                {
                    main2.setText(main2.getText()+"6");
                }
                else if(e.getSource()==main4[7])
                {
                    main2.setText(main2.getText()+"7");
                }
                else if(e.getSource()==main4[8])
                {
                    main2.setText(main2.getText()+"8");
                }
                else if(e.getSource()==main4[9])
                {
                    main2.setText(main2.getText()+"9");
                }
                else if(e.getSource()==main4[10])
                {
                    main2.setText(main2.getText()+".");
                }
                else if(e.getSource()==main4[11])
                {
                    main2.setText(main2.getText()+"0");
                }
                else if(e.getSource()==main4[12])
                {
                    if(main2.getText().length()>0)
                    {
                        main2.setText(main2.getText().substring(0,main2.getText().length()-1));
                    }
                }
                else if(e.getSource()==main3)
                {
                    AssServer2 c1=new AssServer2(main2.getText());
                    c1.start();
                    frame.setVisible(false);
                }
        }
    }
    public static void main(String args[])
    {
        try
        {
            new AssServer1();
        }
        catch(Exception err)
        {
        }
    }
}
