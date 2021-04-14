package Manage;

import DB.Database_Action;
import Entity.Regex_Sample;

import java.util.ArrayList;

public class Regex_List {
    private ArrayList<Regex_Sample> regexList;

    public Regex_List() {
        this.regexList = new ArrayList<Regex_Sample>();
    }

    public ArrayList<Regex_Sample> getRegexList() {
        return regexList;
    }

    public boolean addRegex(Regex_Sample r){
        if (!regexList.contains(r))
            return regexList.add(r);
        return false;
    }

    public void pullData(){
        this.regexList = Database_Action.readFile();
    }

    public int count(){
        return regexList.size();
    }

    public boolean deleteRegexSample(int index){
        if (index >= 0 && index <= regexList.size()){
            regexList.remove(index);
            return true;
        }
        return false;
    }

    public boolean update(String regex, Regex_Sample newRegex){
        Regex_Sample r = new Regex_Sample(regex);
        if (regexList.contains(r)){
            r = regexList.get(regexList.indexOf(r));
            r.setRegex(newRegex.getRegex());
            r.setPurpose(newRegex.getPurpose());
            return true;
        }
        return false;
    }

}
