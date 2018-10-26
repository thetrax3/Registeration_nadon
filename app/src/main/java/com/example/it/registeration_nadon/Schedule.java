package com.example.it.registeration_nadon;

public class Schedule {
    private String monday[] = new String[14];
    private String tuesday[] = new String[14];
    private String wednesday[] = new String[14];
    private String thursday[] = new String[14];
    private String friday[] = new String[14];

    public Schedule(){
        for(int i =0; i<14; i++){
            monday[i]="";
            tuesday[i]="";
            wednesday[i]="";
            thursday[i]="";
            friday[i]="";
        }
    }

    public void addSchedule(String scheduleText){
        int temp;
        //월[3][4][5]
        if((temp = scheduleText.indexOf("월"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i= temp; i<scheduleText.length() && scheduleText.charAt(i)!=':'; i++){
                if(scheduleText.charAt(i)=='['){
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    monday[Integer.parseInt(scheduleText.substring(startPoint+1, endPoint))]="수업";
                }
            }
        }
        if((temp = scheduleText.indexOf("화"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i= temp; i<scheduleText.length() && scheduleText.charAt(i)!=':'; i++){
                if(scheduleText.charAt(i)=='['){
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    tuesday[Integer.parseInt(scheduleText.substring(startPoint+1, endPoint))]="수업";
                }
            }
        }
        if((temp = scheduleText.indexOf("수"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i= temp; i<scheduleText.length() && scheduleText.charAt(i)!=':'; i++){
                if(scheduleText.charAt(i)=='['){
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    wednesday[Integer.parseInt(scheduleText.substring(startPoint+1, endPoint))]="수업";
                }
            }
        }
        if((temp = scheduleText.indexOf("목"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i= temp; i<scheduleText.length() && scheduleText.charAt(i)!=':'; i++){
                if(scheduleText.charAt(i)=='['){
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    thursday[Integer.parseInt(scheduleText.substring(startPoint+1, endPoint))]="수업";
                }
            }
        }
        if((temp = scheduleText.indexOf("금"))>-1){
            temp +=2;
            int startPoint = temp;
            int endPoint = temp;
            for(int i= temp; i<scheduleText.length() && scheduleText.charAt(i)!=':'; i++){
                if(scheduleText.charAt(i)=='['){
                    startPoint = i;
                }
                if(scheduleText.charAt(i)==']'){
                    endPoint = i;
                    friday[Integer.parseInt(scheduleText.substring(startPoint+1, endPoint))]="수업";
                }
            }
        }
    }

    public boolean validate(String scheduleText) {
        if (scheduleText.equals("")) {
            return true;
        }
        int temp;
        if ((temp = scheduleText.indexOf("월")) > -1) {
            temp += 2;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    if (!monday[Integer.parseInt(
                            scheduleText.substring(startPoint + 1, endPoint))].equals("")) {
                        return false;
                    }
                }
            }
        }
        if ((temp = scheduleText.indexOf("화")) > -1) {
            temp += 2;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    if (!tuesday[Integer.parseInt(
                            scheduleText.substring(startPoint + 1, endPoint))].equals("")) {
                        return false;
                    }
                }
            }
        }
        if ((temp = scheduleText.indexOf("수")) > -1) {
            temp += 2;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    if (!wednesday[Integer.parseInt(
                            scheduleText.substring(startPoint + 1, endPoint))].equals("")) {
                        return false;
                    }
                }
            }
        }
        if ((temp = scheduleText.indexOf("목")) > -1) {
            temp += 2;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    if (!thursday[Integer.parseInt(
                            scheduleText.substring(startPoint + 1, endPoint))].equals("")) {
                        return false;
                    }
                }
            }
        }
        if ((temp = scheduleText.indexOf("금")) > -1) {
            temp += 2;
            int startPoint = temp;
            int endPoint = temp;
            for (int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++) {
                if (scheduleText.charAt(i) == '[') {
                    startPoint = i;
                }
                if (scheduleText.charAt(i) == ']') {
                    endPoint = i;
                    if (!friday[Integer.parseInt(
                            scheduleText.substring(startPoint + 1, endPoint))].equals("")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
