package jhutdown;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bernard Mutahi
 */
public enum NumsEnum {
    SECS(0, 60, 10),
    MINS(0, 60, 5),
    HOURS(0, 48, 2),
    DAYS(0, 30, 1);

    int start;
    int end;
    int sep;

    private NumsEnum(int start, int end, int sep) {
        this.start = start;
        this.end = end;
        this.sep = sep;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
    
    public static String getDefault(NumsEnum times) {
            String str = "";
            str += times.start;
        return str;
    }
    
    public static ArrayList getList(NumsEnum times) {
        ArrayList<String> list = new ArrayList<>();
        for (int i=times.start; i<times.end; i++){
            String str = "";
            if (i%times.sep == 0){
                str += i;
                list.add(str);
            }
        }
        return list;
    }
}