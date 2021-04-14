package UI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest extends JFrame implements ActionListener {
    private JLabel lbRegex, lbMatcher, lbNotify, lbPurpose, lbType;
    private static JTextField txtRegex, txtMatcher, txtType, txtPurpose;
    private JButton btnChecker, btnAdd, btnClearRegex, btnClearMatcher, btnClearPurpose, btnClearType;
    private int txtLength = 35;

    public RegexTest(){
        setTitle("Thử nghiệm Regex");
        buildUI();
        setVisible(true);
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void buildUI(){
        JPanel mainFrame = new JPanel();
        mainFrame.setLayout(new BoxLayout(mainFrame, BoxLayout.Y_AXIS));
//        Panel Regex
        JPanel p1 = new JPanel();
        p1.add(lbRegex = new JLabel("Regex:      "));
        p1.add(txtRegex = new JTextField(txtLength));
//        Panel Matcher
        JPanel p2 = new JPanel();
        p2.add(lbMatcher = new JLabel("Matcher:"));
        lbMatcher.setPreferredSize(lbRegex.getPreferredSize());
        p2.add(txtMatcher = new JTextField(txtLength));
//        Panel Notify
        JPanel p3 = new JPanel();
        p3.add(lbNotify = new JLabel(" "));
        lbNotify.setFont(new Font("Times New Roman", Font.ITALIC, 23));
//        Panel Button
        JPanel p4 = new JPanel();
        p4.add(btnChecker = new JButton("Check"));
        p4.add(btnClearRegex = new JButton("Clear Regex Field"));
        p4.add(btnClearMatcher = new JButton("Clear Matcher Field"));
//        Add Components
        mainFrame.add(Box.createVerticalStrut(20));
        mainFrame.add(p1);
        mainFrame.add(Box.createVerticalStrut(10));
        mainFrame.add(p2);
        mainFrame.add(Box.createVerticalStrut(10));
        mainFrame.add(p3);
        mainFrame.add(Box.createVerticalStrut(10));
        mainFrame.add(p4);
        mainFrame.add(Box.createVerticalStrut(10));
        mainFrame.add(pAdd());
        mainFrame.add(Box.createVerticalStrut(10));
        add(mainFrame);
        addAction();
    }

    public JPanel pAdd(){
        JPanel pAdd = new JPanel();
        pAdd.setLayout(new BoxLayout(pAdd, BoxLayout.Y_AXIS));
//        Panel Purpose
        JPanel p1 = new JPanel();
        p1.add(lbPurpose = new JLabel("Purpose"));
        lbPurpose.setPreferredSize(lbRegex.getPreferredSize());
        p1.add(txtPurpose = new JTextField(txtLength));
//        Panel Type
        JPanel p2 = new JPanel();
        p2.add(lbType = new JLabel("Type:"));
        lbType.setPreferredSize(lbRegex.getPreferredSize());
        p2.add(txtType = new JTextField(txtLength));
//        Panel Button Add
        JPanel p3 = new JPanel();
        p3.add(btnAdd = new JButton("Add Regex to Database"));
        p3.add(btnClearPurpose = new JButton("Clear Pupose Field"));
        p3.add(btnClearType = new JButton("Clear Type Field"));
//        Add Components
        pAdd.add(Box.createVerticalStrut(10));
        pAdd.add(p1);
        pAdd.add(Box.createVerticalStrut(10));
        pAdd.add(p2);
        pAdd.add(Box.createVerticalStrut(10));
        pAdd.add(p3);
        pAdd.add(Box.createVerticalStrut(10));
        pAdd.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Add Regex to Database"));
        return pAdd;
    }

    public void addAction(){
        btnAdd.addActionListener(this);
        btnChecker.addActionListener(this);
        btnClearRegex.addActionListener(this);
        btnClearMatcher.addActionListener(this);
        btnClearPurpose.addActionListener(this);
        btnClearType.addActionListener(this);
    }

    public static void fetchRegFromRegList(String reg){
        txtRegex.setText(reg);
        txtMatcher.requestFocus();
    }

    public void setNotify(boolean status, String notify){
        lbNotify.setText(notify);
        if (status)
            lbNotify.setForeground(Color.cyan);
        else
            lbNotify.setForeground(Color.red);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnChecker)){
            if (!txtRegex.getText().equals("") && !txtMatcher.getText().equals("")){
                Pattern p = Pattern.compile(txtRegex.getText().trim());
                String sample = txtMatcher.getText();
                Matcher m = p.matcher(sample);
                if (m.find())
                    setNotify(true, "Khớp");
                else
                    setNotify(false, "Không khớp");

            }
            else
                setNotify(false, "Trường Regex hoặc Matcher chưa được nhập đầy đủ");
        }
        if (o.equals(btnClearRegex)){
            txtRegex.setText("");
        }
        if (o.equals(btnClearMatcher)){
            txtMatcher.setText("");
        }
        if (o.equals(btnClearPurpose)){
            txtPurpose.setText("");
        }
        if (o.equals(btnClearType)){
            txtType.setText("");
        }
        if (o.equals(btnAdd)){
            String p = txtRegex.getText().trim();
            String purpose = txtPurpose.getText().trim();
            String type = txtType.getText().trim();
            if(!p.equals("") && !purpose.equals("") && !type.equals("")){
                if (MainUI.addRegexFromTestScreen(p, purpose, type))
                    setNotify(true, "Thêm thành công Regex vào Database");
                else
                    setNotify(false, "Đã có Regex này trong Database");
            }
            else
                setNotify(false, "Trường Regex, Purpose và Type phải được điền đủ");
        }
    }

}
