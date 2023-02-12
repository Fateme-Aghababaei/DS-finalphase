package pkg.Forms;
/*
 * Created by JFormDesigner on Thu Jan 20 10:44:55 IRST 2022
 */


import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import pkg.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import static javax.swing.UIManager.setLookAndFeel;

/**
 * @author Fateme
 */
public class LoginForm extends JFrame {
    ArrayList<User> users;
    public LoginForm(ArrayList<User> users) {
        initComponents();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        this.users = users;
    }

    private void btnSignInActionPerformed(ActionEvent e) {
        for (User u : users) {
            if (u.getID().equals(txtUsername.getText())) {
                if (u.getPassword().equals(txtPassword.getText())) {
                    ProfileForm profileForm;
                    if (txtCaptcha.getText().trim().equals("7364")) {
                        profileForm = new ProfileForm(this, u.getID(), u);
                        profileForm.setVisible(true);
                        this.setVisible(false);
                    }
                }
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblLogo = new JLabel();
        lblUsername = new JLabel();
        lblPassword = new JLabel();
        lblCaptcha = new JLabel();
        txtUsername = new JTextField();
        txtPassword = new JTextField();
        txtCaptcha = new JTextField();
        lblEnterCode = new JLabel();
        btnSignIn = new JButton();
        lblForget = new JLabel();
        btnJoinNow = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(400, 700));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setIconImage(new ImageIcon("E:\\Uni\\Data Structures\\Projects\\final\\finalproject-Fateme-Aghababaei\\linkedin-icon.png").getImage());
        var contentPane = getContentPane();

        //---- lblLogo ----
        lblLogo.setIcon(new ImageIcon("E:\\Uni\\Data Structures\\Projects\\final\\finalproject-Fateme-Aghababaei\\mainLogo.png"));

        //---- lblUsername ----
        lblUsername.setText("Username:");
        lblUsername.setFont(new Font("Calibri", Font.PLAIN, 20));

        //---- lblPassword ----
        lblPassword.setText("Password:");
        lblPassword.setFont(new Font("Calibri", Font.PLAIN, 20));

        //---- lblCaptcha ----
        lblCaptcha.setFont(new Font("Calibri", Font.PLAIN, 20));
        lblCaptcha.setIcon(new ImageIcon("E:\\Uni\\Data Structures\\Projects\\final\\finalproject-Fateme-Aghababaei\\captcha.jpg"));

        //---- txtUsername ----
        txtUsername.setFont(new Font("Calibri", Font.PLAIN, 20));

        //---- txtPassword ----
        txtPassword.setFont(new Font("Calibri", Font.PLAIN, 20));

        //---- txtCaptcha ----
        txtCaptcha.setFont(new Font("Calibri", Font.PLAIN, 20));

        //---- lblEnterCode ----
        lblEnterCode.setText("Enter Code:");
        lblEnterCode.setFont(new Font("Calibri", Font.PLAIN, 20));

        //---- btnSignIn ----
        btnSignIn.setText("Sign in");
        btnSignIn.setFont(new Font("Calibri", Font.PLAIN, 20));
        btnSignIn.addActionListener(e -> btnSignInActionPerformed(e));

        //---- lblForget ----
        lblForget.setText("Forget password?");
        lblForget.setFont(lblForget.getFont().deriveFont(lblForget.getFont().getStyle() | Font.ITALIC));

        //---- btnJoinNow ----
        btnJoinNow.setText("Join now");
        btnJoinNow.setFont(new Font("Calibri", Font.PLAIN, 20));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                        .addGroup(contentPaneLayout.createParallelGroup()
                                            .addComponent(txtCaptcha, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblEnterCode, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblCaptcha, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(btnJoinNow, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addComponent(btnSignIn, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addGap(126, 126, 126))
                            .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addComponent(lblForget, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                .addGap(143, 143, 143)))))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lblCaptcha, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(lblEnterCode, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtCaptcha, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addComponent(btnSignIn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblForget, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnJoinNow, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addGap(33, 33, 33))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel lblLogo;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblCaptcha;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTextField txtCaptcha;
    private JLabel lblEnterCode;
    private JButton btnSignIn;
    private JLabel lblForget;
    private JButton btnJoinNow;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
