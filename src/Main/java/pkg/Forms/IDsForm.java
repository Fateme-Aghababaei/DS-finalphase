/*
 * Created by JFormDesigner on Thu Jan 20 11:47:24 IRST 2022
 */

package pkg.Forms;

import java.awt.event.*;
import pkg.User;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Fateme
 */
public class IDsForm extends JFrame {
    JFrame previousFrame;
    User user;
    String loggedInUserID;
    public IDsForm(JFrame previousFrame, String loggedInUserID, User user, boolean isConnections) {
        initComponents();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        this.previousFrame = previousFrame;
        this.user = user;
        this.loggedInUserID = loggedInUserID;

        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        if (isConnections) {
            this.setTitle("Connections");
            for (String s : user.getConnections()) {
                User u = User.getUser(s);
                defaultListModel.addElement(u.toString());
            }
        }
        else {
            this.setTitle("Suggestions");
            user.GenerateTop20Suggestions();
            for (User u : user.getTop20Suggestions()) {
                defaultListModel.addElement(u.toString());
            }
        }
        IDsList.setModel(defaultListModel);
    }

    private void btnBackActionPerformed(ActionEvent e) {
        this.dispose();
        previousFrame.setVisible(true);
    }

    private void IDsListMouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            String id = IDsList.getSelectedValue().toString().split(":")[0];
            id = id.substring(1, id.length() - 1);
            User u = User.getUser(id);
            ProfileForm profileForm = new ProfileForm(this, loggedInUserID, u);
            profileForm.setVisible(true);
            this.setVisible(false);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        IDsList = new JList();
        btnBack = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(400, 700));
        setResizable(false);
        setTitle("Connections");
        setIconImage(new ImageIcon("E:\\Uni\\Data Structures\\Projects\\final\\finalproject-Fateme-Aghababaei\\linkedin-icon.png").getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- IDsList ----
            IDsList.setFont(new Font("Calibri", Font.PLAIN, 18));
            IDsList.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    IDsListMouseClicked(e);
                }
            });
            scrollPane1.setViewportView(IDsList);
        }

        //---- btnBack ----
        btnBack.setText("Back");
        btnBack.setFont(new Font("Calibri", Font.PLAIN, 20));
        btnBack.addActionListener(e -> btnBackActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(8, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(132, Short.MAX_VALUE)
                    .addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                    .addGap(123, 123, 123))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(7, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    private JList IDsList;
    private JButton btnBack;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
