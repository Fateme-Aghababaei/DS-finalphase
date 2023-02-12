/*
 * Created by JFormDesigner on Thu Jan 20 11:24:46 IRST 2022
 */

package pkg.Forms;

import java.awt.event.*;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pkg.User;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Fateme
 */
public class ProfileForm extends JFrame {
    JFrame previousFrame;
    User user;
    String loggedInUserID;

    public ProfileForm(JFrame previousFrame, String loggedInUserID, User user) {
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

        if(loggedInUserID.equals(user.getID())) btnConnect.setVisible(false);
        else {
            btnSuggestions.setVisible(false);
            if (User.getUser(loggedInUserID).getConnections().contains(user.getID()))
                btnConnect.setVisible(false);
        }
        lblID.setText(user.getID());
        lblName.setText(user.getName());
        lblBirthDate.setText("Date of Birth: " + user.getDateOfBirth());
        lblUni.setText("University Location: " + user.getUni());
        lblField.setText("Field: " + user.getField());
        lblWorkPlace.setText("Work Place: " + user.getWorkPlace());
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        for (String s : user.getSpecialties()) {
            defaultListModel.addElement(s);
        }
        listSpecialties.setModel(defaultListModel);

    }

    private void lblBackMouseClicked(MouseEvent e) {
        this.dispose();
        previousFrame.setVisible(true);
    }

    private void btnConnectionsActionPerformed(ActionEvent e) {
        IDsForm iDsForm = new IDsForm(this, loggedInUserID, user, true);
        iDsForm.setVisible(true);
        this.setVisible(false);
    }

    private void btnSuggestionsActionPerformed(ActionEvent e) {
        JTextField uniCoefficient = new JTextField();
        JTextField fieldCoefficient = new JTextField();
        JTextField workPlaceCoefficient = new JTextField();
        JTextField specialtiesCoefficient = new JTextField();
        JTextField levelCoefficient = new JTextField();
        Object[] message = {
                "Input University Coefficient: ", uniCoefficient,
                "Input Field Coefficient: ", fieldCoefficient,
                "Input Work Place Coefficient: ", workPlaceCoefficient,
                "Input Specialties Coefficient: ", specialtiesCoefficient,
                "Input Level Coefficient: ", levelCoefficient
        };
        JFrame jf = new JFrame();
        jf.setAlwaysOnTop(true);
        int option = JOptionPane.showConfirmDialog(jf, message, "Enter all the values", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int uni = Integer.parseInt(uniCoefficient.getText());
            int field = Integer.parseInt(fieldCoefficient.getText());
            int work = Integer.parseInt(workPlaceCoefficient.getText());
            int special = Integer.parseInt(specialtiesCoefficient.getText());
            int level = Integer.parseInt(levelCoefficient.getText());
            user.uniCoefficient = uni;
            user.fieldCoefficient = field;
            user.workPlaceCoefficient = work;
            user.specialtiesCoefficient = special;
            user.levelCoefficient = level;

            IDsForm iDsForm = new IDsForm(this, loggedInUserID, user, false);
            iDsForm.setVisible(true);
            this.setVisible(false);
        }
    }

    private void btnConnectActionPerformed(ActionEvent e) {
        User.getUser(loggedInUserID).connections.add(user.getID());
        btnConnect.setVisible(false);


        JSONArray jsonArray = new JSONArray();
        for (User u : User.AllUsers) {
            JSONObject obj = new JSONObject();
            obj.put("id", u.getID());
            obj.put("name", u.getName());
            obj.put("dateOfBirth", u.getDateOfBirth());
            obj.put("universityLocation", u.getUni());
            obj.put("field", u.getField());
            obj.put("workplace", u.getWorkPlace());
            obj.put("specialties", u.getSpecialties());
            obj.put("connectionId", u.getConnections());

            jsonArray.add(obj);
        }

        FileWriter file = null;
        try {
            file = new FileWriter("users2.json");
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        lblBack = new JLabel();
        lblProfilePhoto = new JLabel();
        lblID = new JLabel();
        lblName = new JLabel();
        lblBirthDate = new JLabel();
        lblSpecialties = new JLabel();
        lblWorkPlace = new JLabel();
        lblField = new JLabel();
        lblUni = new JLabel();
        btnConnect = new JButton();
        scrollPane1 = new JScrollPane();
        listSpecialties = new JList();
        btnConnections = new JButton();
        btnSuggestions = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(400, 700));
        setResizable(false);
        setTitle("Profile");
        setIconImage(new ImageIcon("E:\\Uni\\Data Structures\\Projects\\final\\finalproject-Fateme-Aghababaei\\linkedin-icon.png").getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();

        //---- lblBack ----
        lblBack.setBackground(new Color(214, 217, 223));
        lblBack.setIcon(new ImageIcon("E:\\Uni\\Data Structures\\Projects\\final\\finalproject-Fateme-Aghababaei\\back.png"));
        lblBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lblBackMouseClicked(e);
            }
        });

        //---- lblProfilePhoto ----
        lblProfilePhoto.setIcon(new ImageIcon("E:\\Uni\\Data Structures\\Projects\\final\\finalproject-Fateme-Aghababaei\\profile.png"));

        //---- lblID ----
        lblID.setText("ID");
        lblID.setFont(new Font("Calibri", Font.PLAIN, 20));

        //---- lblName ----
        lblName.setText("Name");
        lblName.setFont(new Font("Calibri", Font.PLAIN, 20));

        //---- lblBirthDate ----
        lblBirthDate.setText("Date of Birth: ");
        lblBirthDate.setFont(new Font("Calibri", Font.PLAIN, 20));

        //---- lblSpecialties ----
        lblSpecialties.setText("Specialties:");
        lblSpecialties.setFont(new Font("Calibri", Font.PLAIN, 20));

        //---- lblWorkPlace ----
        lblWorkPlace.setText("Work Place: ");
        lblWorkPlace.setFont(new Font("Calibri", Font.PLAIN, 20));

        //---- lblField ----
        lblField.setText("Field: ");
        lblField.setFont(new Font("Calibri", Font.PLAIN, 20));

        //---- lblUni ----
        lblUni.setText("University Location:");
        lblUni.setFont(new Font("Calibri", Font.PLAIN, 20));

        //---- btnConnect ----
        btnConnect.setText("Connect");
        btnConnect.setFont(new Font("Calibri", Font.PLAIN, 20));
        btnConnect.addActionListener(e -> btnConnectActionPerformed(e));

        //======== scrollPane1 ========
        {

            //---- listSpecialties ----
            listSpecialties.setFont(new Font("Calibri", Font.PLAIN, 18));
            scrollPane1.setViewportView(listSpecialties);
        }

        //---- btnConnections ----
        btnConnections.setText("Connections");
        btnConnections.setFont(new Font("Calibri", Font.PLAIN, 20));
        btnConnections.addActionListener(e -> btnConnectionsActionPerformed(e));

        //---- btnSuggestions ----
        btnSuggestions.setText("Suggestions");
        btnSuggestions.setFont(new Font("Calibri", Font.PLAIN, 20));
        btnSuggestions.addActionListener(e -> btnSuggestionsActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lblBack, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(lblProfilePhoto, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(lblID, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblName, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addComponent(btnConnect, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(lblSpecialties, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGroup(contentPaneLayout.createParallelGroup()
                                                .addComponent(lblBirthDate, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblUni, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblField, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblWorkPlace, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE))
                                            .addGap(0, 0, Short.MAX_VALUE)))))))
                    .addGap(21, 21, 21))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(118, 118, 118)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(btnSuggestions, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnConnections, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblBack, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblProfilePhoto, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(lblID)
                            .addGap(18, 18, 18)
                            .addComponent(lblName)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnConnect, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblBirthDate, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addComponent(lblUni, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblWorkPlace, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblSpecialties, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnConnections, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnSuggestions, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(16, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel lblBack;
    private JLabel lblProfilePhoto;
    private JLabel lblID;
    private JLabel lblName;
    private JLabel lblBirthDate;
    private JLabel lblSpecialties;
    private JLabel lblWorkPlace;
    private JLabel lblField;
    private JLabel lblUni;
    private JButton btnConnect;
    private JScrollPane scrollPane1;
    private JList listSpecialties;
    private JButton btnConnections;
    private JButton btnSuggestions;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
