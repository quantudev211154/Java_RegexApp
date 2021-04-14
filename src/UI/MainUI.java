package UI;

import DB.Database_Action;
import Entity.Regex_Sample;
import Manage.RegexModel;
import Manage.Regex_List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainUI extends JFrame implements ActionListener {
    private JLabel lbTitle, lbRegex, lbPurpose, lbNotify, lbType;
    private JTextField txtRegex, txtPurpose, txtType;
    private JButton btnAdd, btnDelete, btnUpdate, btnClear, btnSave, btnCreateReg, btnTestReg;
    private static JTable table;
    private RegexModel model;
    private static Regex_List regexList;
    private int txtLength = 63;

    public MainUI(){
        setTitle("Regex Sample");
        buildUI();
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void buildUI(){
        JPanel mainFrame = new JPanel(new BorderLayout());

        mainFrame.add(pNorth(), BorderLayout.NORTH);
        mainFrame.add(pCenter(), BorderLayout.CENTER);
        mainFrame.add(pSouth(), BorderLayout.SOUTH);
        add(mainFrame);
    }

    public JPanel pNorth(){
        JPanel pNorth = new JPanel();
        pNorth.add(lbTitle = new JLabel("Regex List and Implement"));
        lbTitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lbTitle.setForeground(Color.MAGENTA);
        return pNorth;
    }

    public JPanel pCenter(){
        JPanel pCenter = new JPanel();
        JSplitPane splPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pCenTop(), pCenBot());
        splPane.setBorder(null);
        splPane.setDividerSize(0);
        splPane.setPreferredSize(new Dimension(790, 500));
        pCenter.add(splPane);
        return pCenter;
    }

    public JPanel pCenTop(){
        JPanel pCenTop = new JPanel();
//        Panel Purpose
        JPanel pur = new JPanel();
        pur.add(lbPurpose = new JLabel("Purpose:      "));
        pur.add(txtPurpose = new JTextField(txtLength));
//        Panel Regex
        JPanel regex = new JPanel();
        regex.add(lbRegex = new JLabel("Regex:"));
        lbRegex.setPreferredSize(lbPurpose.getPreferredSize());
        regex.add(txtRegex = new JTextField(txtLength));
//        Panel Type
        JPanel pType = new JPanel();
        pType.add(lbType = new JLabel("Type:"));
        lbType.setPreferredSize(lbPurpose.getPreferredSize());
        pType.add(txtType = new JTextField(txtLength));
//        Panel Notify
        JPanel noti = new JPanel();
        noti.add(lbNotify = new JLabel(" "));
        lbNotify.setFont(new Font("Times New Roman", Font.ITALIC, 25));
//        Add Components
        pCenTop.add(regex);
        pCenTop.add(pur);
        pCenTop.add(pType);
        pCenTop.add(noti);
        pCenTop.setPreferredSize(new Dimension(790, 150));
        return pCenTop;
    }

    public JPanel pCenBot(){
        JPanel pBot = new JPanel();
        table = new JTable();
        preProcess();
        JScrollPane scrPane = new JScrollPane(table);
        scrPane.setPreferredSize(new Dimension(790, 310));
        pBot.add(scrPane);
        return pBot;
    }

    public JPanel pSouth(){
        JPanel pSouth = new JPanel();
        pSouth.add(btnAdd = new JButton("Add"));
        pSouth.add(btnClear = new JButton("Clear"));
        pSouth.add(btnUpdate = new JButton("Update"));
        pSouth.add(btnDelete = new JButton("Delete"));
        pSouth.add(btnSave = new JButton("Save"));
        pSouth.add(btnCreateReg = new JButton("Create Regex"));
        pSouth.add(btnTestReg = new JButton("Test Regex"));
        btnAdd.addActionListener(this);
        btnClear.addActionListener(this);
        btnDelete.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnSave.addActionListener(this);
        btnCreateReg.addActionListener(this);
        btnTestReg.addActionListener(this);
        return pSouth;
    }

    public void preProcess(){
        regexList = new Regex_List();
        regexList.pullData();
        updateTableData();
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = table.getSelectedRow();
                fetchDataToForm(row);
            }
        });
    }

    public void fetchDataToForm(int row){
        if (row != -1){
            String regex = (String) table.getValueAt(row, 0);
            Regex_Sample r = new Regex_Sample(regex);
            ArrayList<Regex_Sample> arr = regexList.getRegexList();
            if (arr.contains(r)){
                r = arr.get(arr.indexOf(r));
                txtRegex.setText(r.getRegex());
                txtPurpose.setText(r.getPurpose());
                txtType.setText(r.getType());
            }
        }
    }

    public Regex_Sample revertRegexFromForm(){
        String regex = "", purpose = "", type = "";
        regex = txtRegex.getText().trim();
        purpose = txtPurpose.getText().trim();
        type = txtType.getText().trim();
        return new Regex_Sample(regex, purpose, type);
    }

    public static boolean addRegexFromTestScreen(String reg, String pur, String type){
        Regex_Sample newReg = new Regex_Sample(reg, pur, type);
        if (regexList.addRegex(newReg)){
            updateTableData();
            return true;
        }
        return false;
    }

    public static void updateTableData(){
        RegexModel regexModel = new RegexModel(regexList.getRegexList());
        table.setModel(regexModel);
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
        if (o.equals(btnAdd)){
            if (!txtRegex.getText().equals("") && !txtPurpose.getText().equals("") || !txtType.getText().equals("")){
                Regex_Sample r = revertRegexFromForm();
                if (regexList.addRegex(r)){
                    setNotify(true, "Thêm Regex thành công");
                    updateTableData();
                }
                else
                    setNotify(false, "Regex đã tồn tại. Không thêm được.");
            }
            else
                setNotify(false, "Các trường dữ liệu chưa được nhập đầy đủ");
        }
        if (o.equals(btnClear)){
            txtRegex.setText("");
            txtPurpose.setText("");
            txtType.setText("");
            txtRegex.requestFocus();
        }
        if (o.equals(btnDelete)){
            int row = table.getSelectedRow();
            if (row != -1){
                int dec = JOptionPane.showConfirmDialog(this, "Anh xóa thiệt hả anh?", "Chú ý", JOptionPane.YES_NO_OPTION);
                if (dec == JOptionPane.YES_OPTION){
                    regexList.deleteRegexSample(row);
                    updateTableData();
                    setNotify(true, "Đã xóa xong. Hờn anh quá à.");
                }
                else
                    setNotify(false, "Gà dữ. Bạn không dám xóa à.");
            }
            else
                setNotify(false, "Hãy chọn dòng cần xóa");
        }
        if (o.equals(btnUpdate)){
            int row = table.getSelectedRow();
            if (row != -1){
                String oldRegex = (String) table.getValueAt(row, 0);
                Regex_Sample newRegex = revertRegexFromForm();
                regexList.update(oldRegex, newRegex);
                updateTableData();
                setNotify(true, "Cập nhật thành công");
            }
            else
                setNotify(false, "Hãy chọn dòng cần cập nhật");
        }
        if (o.equals(btnSave)){
            ArrayList<Regex_Sample> regexFinal = regexList.getRegexList();
            Database_Action.writeToFile(regexFinal);
            setNotify(true, "Lưu thành công");
        }
        if (o.equals(btnCreateReg)){
            new RegexTest().setVisible(true);
        }
        if (o.equals(btnTestReg)){
            int rowIndex = table.getSelectedRow();
            if (rowIndex != -1){
                String reg = (String) table.getValueAt(rowIndex, 0);
                new RegexTest().setVisible(true);
                RegexTest.fetchRegFromRegList(reg);
            }
            else
                setNotify(false, "Hãy chọn Regex cần Test");
        }
    }
}
