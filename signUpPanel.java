
package jeddahseason;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.regex.Pattern;
import javax.swing.*;


public class signUpPanel extends JFrame {
    private JFrame frame;
    private JLabel mainLabel;
    private JLabel userLabel;
    private JLabel passLabel;
    private JLabel emailLabel;
    public JTextField userText;
    public JTextField passText;
    public JTextField emailText;
    private JButton signButton;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel container;
    private FileWriter fileWriter;
    private PrintWriter outputFile;

    public signUpPanel(){
        frame = new JFrame();
        frame.setTitle("");
        frame.setLocationRelativeTo(null);
        frame.setSize(400,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildPanel();
        frame.add(container);
        frame.setVisible(true);
    }
    

    
    public void buildPanel(){
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER,6,15));
        userLabel = new JLabel("User Name");
        userLabel.setForeground(new Color(60,110,150));
        userText = new JTextField(15);
        userText.setBackground(Color.LIGHT_GRAY);
        panel1.setBackground(Color.WHITE);
        panel1.add(userLabel);
        panel1.add(userText);
        
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER,10,15));
        passLabel = new JLabel("Password");
        passLabel.setForeground(new Color(60,110,150));
        passText = new JTextField(15);
        passText.setBackground(Color.LIGHT_GRAY);
        panel2.setBackground(Color.WHITE); 
        panel2.add(passLabel);
        panel2.add(passText);
       
        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER,30,15));
        emailLabel = new JLabel("Email");
        emailLabel.setForeground(new Color(60,110,150));
        emailText = new JTextField(15);
        emailText.setBackground(Color.LIGHT_GRAY);
        panel3.setBackground(Color.WHITE);
        panel3.add(emailLabel);
        panel3.add(emailText);
        
        panel4 = new JPanel();
        signButton = new JButton("Sign Up");
        signButton.setBackground(new Color(60,110,150));
        signButton.setForeground(Color.WHITE);
        signButton.addActionListener(new signUpButtonListener() );
        panel4.setBackground(Color.WHITE);
        panel4.add(signButton);
        
        container = new JPanel();
        container.setLayout(new GridLayout(5,1));
        container.setBackground(new Color(60,110,150));
        mainLabel = new JLabel("SIGN UP");
        mainLabel.setFont(new Font("DialogInput",Font.BOLD, 15));
        mainLabel.setForeground(Color.WHITE);
        container.add(mainLabel);
        container.add(panel1);
        container.add(panel2);
        container.add(panel3);
        container.add(panel4);
    }
    
    private class signUpButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ea){
            String user = userText.getText();
            String pass = passText.getText();
            String email = emailText.getText();          
 
            if(ea.getActionCommand() == signButton.getActionCommand()){
   
                if(checkValidPUserName(user)){
                    if(checkValidPassword(pass)){
                        if(checkValidEmail(email) ){
                            try{
                                fileWriter = new FileWriter("user.txt",true);
                                outputFile = new PrintWriter(fileWriter);
                                outputFile.write(user+"\n");
                                outputFile.write(pass+"\n");
                                outputFile.write(email+"\n");
                                outputFile.println("");                             
                                outputFile.close();
                                JOptionPane.showMessageDialog(null, "Account has been created");
                                frame.dispose();
                                JeddahSeason js = new JeddahSeason();

                            }
                            catch(Exception e){
                                JOptionPane.showMessageDialog(null, ""+e.getMessage());
                            }
                        }
                    }
                }         
            }
        }
    }
    
    
    
    
    public static boolean checkValidPUserName(String userName)
    {
        boolean isValid = true;
        if(userName.isEmpty()){
            JOptionPane.showMessageDialog(null,"User Name must be entered.");
            isValid = false;

        }
        if (userName.length() > 10 || userName.length() < 6 )
        {
            JOptionPane.showMessageDialog(null,"User Name must be less than 10 and more than 6 characters in length.");
            isValid = false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!userName.matches(upperCaseChars ))
        {
            JOptionPane.showMessageDialog(null,"User Name must have atleast one uppercase character");
            isValid = false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!userName.matches(lowerCaseChars ))
        {
            JOptionPane.showMessageDialog(null,"User Name must have atleast one lowercase character");
            isValid = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!userName.matches(numbers ))
        {
            JOptionPane.showMessageDialog(null,"User Name must have atleast one number");
            isValid = false;
        }

        return isValid;
    }
    
    
    public static boolean checkValidPassword(String password)
    {
        boolean isValid = true;
        if(password.isEmpty()){
         JOptionPane.showMessageDialog(null,"Password must be entered.");
         isValid = false;
        }
        if (password.length() > 15 || password.length() < 8)
        {
            JOptionPane.showMessageDialog(null,"Password must be less than 20 and more than 8 characters in length.");
            isValid = false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars ))
        {
            JOptionPane.showMessageDialog(null,"Password must have atleast one uppercase character");
            isValid = false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars ))
        {
            JOptionPane.showMessageDialog(null,"Password must have atleast one lowercase character");
            isValid = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers ))
        {
            JOptionPane.showMessageDialog(null,"Password must have atleast one number");
            isValid = false;
        }
        String specialChars = "(.*[@,#,$,%,-,_].*$)";
        if (!password.matches(specialChars ))
        {
            JOptionPane.showMessageDialog(null,"Password must have atleast one special character among @#$%");
            isValid = false;
        }


        return isValid;
}

    public static boolean checkValidEmail(String email)
    {  
        String emailFormate ="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\."  
                + "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";  
          
        Pattern p = Pattern.compile(emailFormate);  
        if(email.isEmpty()){  
            JOptionPane.showMessageDialog(null,"must enter email");
            return false;  
        }   
        if(!p.matcher(email).matches()){
            JOptionPane.showMessageDialog(null,"Not correct email");
            return false; 
        }
        return p.matcher(email).matches();  
    }  
       

}