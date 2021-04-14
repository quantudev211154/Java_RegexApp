package Manage;

import Entity.Regex_Sample;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class RegexModel extends AbstractTableModel {
    private final int regex = 0;
    private final int purpose = 1;
    private final int type = 2;
    private ArrayList<Regex_Sample> regexList;
    private String[] header = "Regex;Tác dụng;Kiểu".split(";");

    public RegexModel(ArrayList<Regex_Sample> regexList){
        this.regexList = regexList;
    }


    @Override
    public int getRowCount() {
        return regexList.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Regex_Sample r = regexList.get(rowIndex);
        switch (columnIndex){
            case regex:
                return r.getRegex();
            case purpose:
                return r.getPurpose();
            case type:
                return r.getType();
            default:
                return r;
        }
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }
}
