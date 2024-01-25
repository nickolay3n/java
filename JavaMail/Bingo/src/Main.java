import javax.swing.*;
//import main.javax.mail.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Properties;
import javax.mail.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.*;
import java.io.*;
//import springframework.mail.MailException;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.SimpleMailMessage;
public class Main {
    public static JPanel panel = new JPanel();
    public static JTextArea pass_email = new JTextArea(1,15);
    public static JTextArea user_email = new JTextArea(1,15);
    public static JTextArea myArchiveDate1 = new JTextArea(1,50);
    public static JTextArea myArchiveDate2 = new JTextArea(1,50);
    public static JTextArea mylog = new JTextArea(3,25);
    public static JCheckBox archiveBox =  new JCheckBox();

    public static void main(String[] args) {
        GridBagLayout mylayout = new GridBagLayout();//GridLayout(3, 2,5,5);
        panel.setLayout(mylayout);
        //panel.doLayout();
        JFrame window = new JFrame("Чистка почтового ящика");
        JButton button = new JButton("ЧИСТКА");
        button.setSize(5,3);

        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WorkerEml.getmymail();
                //WorkerEml.archive_render();
            }
        });

        //текстовые поля
        //JTextArea user = new JTextArea("e-mail: ", 1, 8);
        //user.setEditable(false);
        //user.setBackground(panel.getBackground());
        user_email.setText("machosracho88@mail.ru");
        //pass_email.setText("ZYtDfcz729!");
        user_email.setToolTipText("machosracho88@mail.ru");
        pass_email.setToolTipText("пароль сюда!");
        pass_email.setText("WhosYourDaddy");


        //JTextArea pass = new JTextArea("password: ", 1, 9);
        //pass.setEditable(false);
        //pass.setBackground(panel.getBackground());

        GridBagConstraints c = new GridBagConstraints();
        c.anchor=GridBagConstraints.NORTH;
        //c.anchor=GridBagConstraints.WEST;
        c.gridy = 0;       //1 row
        c.fill = GridBagConstraints.HORIZONTAL;//natural height, maximum width
        c.weighty = 0.1;   //request any extra vertical space
        c.gridwidth = 2;
        user_email.setFocusable(true);
        panel.add(new JLabel("e-mail: "),c);
        panel.add(user_email,c);
        c.gridwidth = 2;c.gridy = 1;       //2 row
        panel.add(new JLabel("password"),c);
        pass_email.setFocusable(true);
        panel.add(pass_email,c);
        c.gridy = 3;c.gridwidth = 1;c.weightx = 5;
        //c.fill = GridBagConstraints.WEST;
        //JLabel label = new JLabel("Архив с");
        archiveBox.setFocusable(true);
        panel.add(archiveBox,c);

        panel.add(new JLabel("Архив с"),c);
        c.gridy = 3;
        c.weightx = 40;
        c.gridwidth = 1;   //2 columns wide
        myArchiveDate1.setFocusable(true);
        myArchiveDate2.setFocusable(true);
        myArchiveDate1.setToolTipText("01.01.2020");
        myArchiveDate2.setToolTipText("01.01.2021");

        panel.add(myArchiveDate1,c);
        c.gridy = 4;
        panel.add(new JLabel("по"),c);
        c.gridx = 2;
        panel.add(myArchiveDate2,c);

        c.fill = GridBagConstraints.HORIZONTAL;
        //c.ipady = 0;       //reset to default

        //c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        //c.insets = new Insets(10,0,0,0);  //top padding
        //c.gridx = 1;       //aligned with button 2
        c.gridwidth = 4;   //2 columns wide
        c.gridy = 5;       //5 row
        panel.add(button,c);
        c.gridy = 6;       //6 row
        c.gridwidth = 3;
        c.weightx = 25;
        c.weighty=10;
        mylog.setAutoscrolls(true);
        mylog.setToolTipText("это просто лог");
        //mylog.setSize(25,10);

        panel.add(mylog,c);



        window.add(panel);
        window.setSize(400, 500);
//        button.addActionListener(e -> System.out.println("you click"));
        window.setVisible(true);
        window.addWindowListener(new WindowAdapter() { // при закрытии окна программа закрывается
                                     @Override
                                     public void windowClosing(WindowEvent e) {
                                         super.windowClosing(e);
                                         System.exit(0);
                                     }
                                 });
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        final Properties properties = new Properties();
//        properties.load(main.java.
    }
}