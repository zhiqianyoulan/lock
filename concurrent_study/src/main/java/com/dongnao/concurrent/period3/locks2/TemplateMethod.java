package com.dongnao.concurrent.period3.locks2;

public class TemplateMethod {
    public static void main(String args[]){
        PPT01 ppt = new PPT01();
        ppt.show();

        System.out.println("");
        System.out.println("");


        PPT02 ppt2 = new PPT02();
        ppt2.show();
    }
}

class MotherMash{

    void tile(){
        throw new UnsupportedOperationException();
    }

    void content(){
        throw new UnsupportedOperationException();
    }

    void end(){
        throw new UnsupportedOperationException();
    }


    public final void show(){
        System.out.print("======== 黑色、36号 华文行楷");
        tile();
        System.out.println("");


        System.out.println("#######  华文行楷 28 黑色   #########");
        content();
        System.out.println("#######  正文 end   #########");
        System.out.println("");

        System.out.print("-------------页脚：");
        end();
        System.out.println("----------------");

    }

}


class PPT01 extends MotherMash {

    @Override
    void tile() {
        System.out.println("AQS 抽象队列同步器");
    }

    @Override
    void content() {
        System.out.println("RenntrantLock");
    }

    @Override
    void end() {
        System.out.print("网易云课堂");
    }
}


class PPT02 extends MotherMash {

    @Override
    void tile() {
        System.out.println("Sync");
    }

    @Override
    void content() {
        System.out.println("轻量级、重量级、偏向");
    }

    @Override
    void end() {
        System.out.print("网易云课堂");
    }
}