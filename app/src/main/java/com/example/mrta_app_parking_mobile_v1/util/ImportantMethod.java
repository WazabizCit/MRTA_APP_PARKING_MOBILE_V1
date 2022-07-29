package com.example.mrta_app_parking_mobile_v1.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ImportantMethod extends AppCompatActivity {

    public void showToastLog(String text1, String text2) {
        Log.d(text1, text2);
    }


    public void showToastWarning(String text, Context context) {

        int bg_color = Color.parseColor("#ffc000");

        Toast tost  = Toast.makeText(context,text,Toast.LENGTH_LONG);
        View view  = tost.getView();
        view.getBackground().setColorFilter(bg_color, PorterDuff.Mode.SRC_IN);
        TextView textx = view.findViewById(android.R.id.message);
        textx.setTextSize(20);
        textx.setTextColor(Color.BLACK);
        tost.show();



    }



    public void showToastSuccess(String text, Context context) {


        int bg_color = Color.parseColor("#28a953");

        Toast tost  = Toast.makeText(context,text,Toast.LENGTH_LONG);
        View view  = tost.getView();
        view.getBackground().setColorFilter(bg_color, PorterDuff.Mode.SRC_IN);
        TextView textx = view.findViewById(android.R.id.message);
        textx.setTextSize(20);
        textx.setTextColor(Color.WHITE);
        tost.show();




    }



    public void showToastDanger(String text, Context context) {


        int bg_color = Color.parseColor("#e60000");

        Toast tost  = Toast.makeText(context,text,Toast.LENGTH_LONG);
        View view  = tost.getView();
        view.getBackground().setColorFilter(bg_color, PorterDuff.Mode.SRC_IN);
        TextView textx = view.findViewById(android.R.id.message);
        textx.setTextSize(20);
        textx.setTextColor(Color.WHITE);
        tost.show();




    }




    public static String getCurrentTimeStamp() {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date()); // Find todays date

            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }


    public static String getCurrentDate() {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String currentDateTime = dateFormat.format(new Date()); // Find todays date

            return currentDateTime;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }





    public  String getStringZplIN(String location_zpl,String license_plate,String time,String card_code,String card_name,
                                  String cashier,String type_car,String name_building_name) {

        String zpl_in = "^XA\n" +
                "^FO220,120^GFA,2520,2520,18,,::::::::::::::::::::R03IFEL01FC,P07CJFC01IFE7F,P01KFI07JFE,Q0JFCI01JF8,P01JF8J07BFF8,P03IFEK03IFC,P0JF8K01IFE,O03JF8K01JF8,O07JFCK03JFE,N01JF3EK0FE7IF,N07IFE0F8I03F81IFC,N0JF803EI07F00JF,M03IFEI0F001FC003IF8,M0JFCI07C07FJ0IFE,L01JFJ01F0FCJ07IF8,L07IFCK07BF8J01IFE,K01JF8K01FEL07IF,K07IFEL037CL03IFC,K0JF8L07FCM0JF,J03JFL01FDFM03IF8,J0JFCL07F07CL01IFE,I01JFM0FE01FM07IF8,I07IFEL03F800F8L01IFC,001JF8L0FEI03EM0JF,003IFEL01FCJ0F8L03IFC,00JFCL07FK03CM0IFE,01JFM0FEK03FM07IF8,00JF8L07FK03EM0JF,007IFEL03F8J0F8L03IFC,001JF8L0FEI03EM07IF,I07IFCL03F8007CL01IFE,I03JFL01FC01FM07IF8,J0JFCL07F07CM0IFE,J03IFEL01FCF8L03IFC,J01JF8L0FFEM0JF,K07IFEL03FCL01IFC,K01JFL01FEL07IF8,L0JFCK07FFK01IFE,L03JFJ01F1FCJ03IF8,M0JF8I03E07FJ0JF,M03IFEI0F801F8003IFC,M01JF803EI0FE007IF,N07IFC07CI03F81IFE,N01JF1FK0FC7IF8,O0KFCK07JFE,O03JF8K01FF7F8,P0JF8K01JF,P07IFEK01IFC,P01JFK07IF8,Q0JFCI01JF8,P01KFI03FF7FC,P03EJF800JF7F,P0F83IFE03IFC1FC,,::::::::::001F8J07F01JFC01LFC1F8,003FCJ07F01KF81LFC1F8,003FCJ0FF01KFC1LFC3FC,003FCJ0FF01KFE1LFC3FC,003FEJ0FF01FI0FFI03EI03FC,003FEI01FF01EI03FI03EI07FE,003FFI01FF01EI01FI03EI07BE,003FFI01FF01EJ0F8003EI0F9F,003EFI03FF01EJ0F8003EI0F9F,003E78003DF01EJ0F8003EI0F1F,003E78003DF01EJ0F8003E001F0F8,003E780079F01EJ0F8003E001F0F8,003E3C0079F01EI01FI03E001E078,003E3C0079F01EI01FI03E003E07C,003E3C00F1F01EI03FI03E003C07C,003E1E00F1F01F001FEI03E007C03E,003E1E00F1F01KFCI03E007C03E,003E1E01E1F01KFJ03E007803E,003E0F01E1F01KF8I03E00F801F,003E0F03E1F01KFCI03E00F801F,003E0F03C1F01EI07EI03E01KF8,003E0783C1F01EI03EI03E01KF8,003E0787C1F01EI01FI03E01KF8,003E03C781F01EI01FI03E03KFC,003E03C781F01EI01FI03E03EI07C,003E03CF01F01EI01FI03E03CI07E,003E01EF01F01EI01FI03E07CI03E,003E01FF01F01EJ0FI03E07CI03E,003E01FE01F01EJ0FI03E0F8I03F,003E00FE01F01EJ0FI03E0F8I01F,003E00FE01F01EJ0F8003E0F8I01F,003E00FC01F01EJ0F8003E1FJ01F8,003E007C01F01EJ0F8003E1FK0F8,001E007C01F01EJ07C003E1EK0FC,,::::::::::::::::::::^FS\n" +


                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO240,250^FD"+name_building_name+"^FS\n" +
                "^FS\n" +


                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO150,290^FDใบเสร็จทำรายการขาเข้า^FS\n" +
                "^FS\n" +


                "^FO70,350^GB460,1,3^FS\n" +
                "^CFA,30\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28 \n" +
                "^FO70,380^FDตำแหน่งเข้า :^FS\n" +
                "^FS\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28 \n" +
                "^FO250,380^FD "+location_zpl+" ^FS\n" +
                "^FS\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28 \n" +
                "^FO70,420^FDทะเบียนรถ :^FS\n" +
                "^FS\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO250,420^FD"+license_plate+"^FS\n" +
                "^FS\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO70,460^FDเวลาเข้า :^FS\n" +
                "^FS\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO180,460^FD"+time+"^FS\n" +
                "^FS\n" +



                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO70,500^FDCode :^FS\n" +
                "^FS\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO180,500^FD"+card_code+"^FS\n" +
                "^FS\n" +


                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO70,540^FDรหัสหลังบัตร :^FS\n" +
                "^FS\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO250,540^FD"+card_name+"^FS\n" +
                "^FS\n" +



                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO70,580^FDประภท :^FS\n" +
                "^FS\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO180,580^FD"+type_car+"^FS\n" +
                "^FS\n" +





                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO70,620^FDCashier :^FS\n" +
                "^FS\n" +

                "^CWT,E:ANGSANA.TTF\n" +
                "^CFT,40,40\n" +
                "^CI28\n" +
                "^FO180,620^FD"+cashier+"^FS\n" +
                "^FS\n" +


                "^CFA,15\n" +
                "^FO70,700^GB460,1,3^FS\n" +


                "^FO120,750^BY2\n" +
                "^BCN,50,Y,N,N\n" +
                "^FD "+card_code+" ^FS\n" +

                "^XZ;";





        return zpl_in;
    }


    public  String getStringZplOUT(
            String carout_building_tax,
            String carout_registered_no,
            String carout_cabinet_tax_code,
            String carout_cabinet_code,
            String carout_cabinet_name,
            String carout_carparking_out_time,
            String carout_receipt_no,
            String carout_estamp_status,
            String carout_car_type_name,
            String carout_license_plate,
            String carout_cardcode,
            String carout_carparking_in_time,
            String carout_building_tel,
            String carout_payment_amount,
            String carout_payment_fine_amount,
            String carout_payment_totle,
            String carout_payment_type_name_th,
            String carout_diff_time_in_out




    ) {

        String zpl_out =
                "^XA\n" +
                        "^FO220,50^GFA,2520,2520,18,,::::::::::::::::::::R03IFEL01FC,P07CJFC01IFE7F,P01KFI07JFE,Q0JFCI01JF8,P01JF8J07BFF8,P03IFEK03IFC,P0JF8K01IFE,O03JF8K01JF8,O07JFCK03JFE,N01JF3EK0FE7IF,N07IFE0F8I03F81IFC,N0JF803EI07F00JF,M03IFEI0F001FC003IF8,M0JFCI07C07FJ0IFE,L01JFJ01F0FCJ07IF8,L07IFCK07BF8J01IFE,K01JF8K01FEL07IF,K07IFEL037CL03IFC,K0JF8L07FCM0JF,J03JFL01FDFM03IF8,J0JFCL07F07CL01IFE,I01JFM0FE01FM07IF8,I07IFEL03F800F8L01IFC,001JF8L0FEI03EM0JF,003IFEL01FCJ0F8L03IFC,00JFCL07FK03CM0IFE,01JFM0FEK03FM07IF8,00JF8L07FK03EM0JF,007IFEL03F8J0F8L03IFC,001JF8L0FEI03EM07IF,I07IFCL03F8007CL01IFE,I03JFL01FC01FM07IF8,J0JFCL07F07CM0IFE,J03IFEL01FCF8L03IFC,J01JF8L0FFEM0JF,K07IFEL03FCL01IFC,K01JFL01FEL07IF8,L0JFCK07FFK01IFE,L03JFJ01F1FCJ03IF8,M0JF8I03E07FJ0JF,M03IFEI0F801F8003IFC,M01JF803EI0FE007IF,N07IFC07CI03F81IFE,N01JF1FK0FC7IF8,O0KFCK07JFE,O03JF8K01FF7F8,P0JF8K01JF,P07IFEK01IFC,P01JFK07IF8,Q0JFCI01JF8,P01KFI03FF7FC,P03EJF800JF7F,P0F83IFE03IFC1FC,,::::::::::001F8J07F01JFC01LFC1F8,003FCJ07F01KF81LFC1F8,003FCJ0FF01KFC1LFC3FC,003FCJ0FF01KFE1LFC3FC,003FEJ0FF01FI0FFI03EI03FC,003FEI01FF01EI03FI03EI07FE,003FFI01FF01EI01FI03EI07BE,003FFI01FF01EJ0F8003EI0F9F,003EFI03FF01EJ0F8003EI0F9F,003E78003DF01EJ0F8003EI0F1F,003E78003DF01EJ0F8003E001F0F8,003E780079F01EJ0F8003E001F0F8,003E3C0079F01EI01FI03E001E078,003E3C0079F01EI01FI03E003E07C,003E3C00F1F01EI03FI03E003C07C,003E1E00F1F01F001FEI03E007C03E,003E1E00F1F01KFCI03E007C03E,003E1E01E1F01KFJ03E007803E,003E0F01E1F01KF8I03E00F801F,003E0F03E1F01KFCI03E00F801F,003E0F03C1F01EI07EI03E01KF8,003E0783C1F01EI03EI03E01KF8,003E0787C1F01EI01FI03E01KF8,003E03C781F01EI01FI03E03KFC,003E03C781F01EI01FI03E03EI07C,003E03CF01F01EI01FI03E03CI07E,003E01EF01F01EI01FI03E07CI03E,003E01FF01F01EJ0FI03E07CI03E,003E01FE01F01EJ0FI03E0F8I03F,003E00FE01F01EJ0FI03E0F8I01F,003E00FE01F01EJ0F8003E0F8I01F,003E00FC01F01EJ0F8003E1FJ01F8,003E007C01F01EJ0F8003E1FK0F8,001E007C01F01EJ07C003E1EK0FC,,::::::::::::::::::::^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO240,170^FDแยกคปอ.^FS\n" +
                        "^FS\n" +


                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO150,200^FDใบเสร็จรับเงิน/ใบกำกับภาษีอย่างย่อ^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO100,230^FDRECEIPT/TAX INVOICE (ABB)(VAT INCLUDE)^FS\n" +
                        "^FS\n" +


                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,270^FDTAX ID/Branch No : "+carout_building_tax+"^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,300^FDTel. : "+carout_building_tel+"^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,330^FDREG No. :"+carout_registered_no+"^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,360^FDPOS No. :"+carout_cabinet_tax_code+"^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,390^FDCashier. :"+carout_cabinet_code+"^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,420^FDCashier box :"+carout_cabinet_name+"^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,450^FDวันที่ Date :"+carout_carparking_out_time+"^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,480^FDเลขที่ Invoice No. : "+carout_receipt_no+"^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,510^FDเลขที่ Ref.1 : ^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,540^FDเลขที่ Ref.2 : ^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO170,570^FDค่าบริการจอดรถ Parking fee^FS\n" +
                        "^FS\n" +


                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,600^FDประเภทลูกค้า : "+carout_estamp_status+"^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,630^FDประเภทรถ :  "+carout_car_type_name+"^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,660^FDทะเบียน License : "+carout_license_plate+"^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,690^FDหมายเลขบัตร Card No. : "+carout_cardcode+"^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,720^FDเวลาเข้า IN : "+carout_carparking_in_time+"^FS\n" +
                        "^FS\n" +



                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,750^FDเวลาออก OUT : "+carout_carparking_out_time+"^FS\n" +
                        "^FS\n" +


                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,780^FDระยะเวลาเข้าออก IN-OUT: "+carout_diff_time_in_out+"^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,810^FDค่าจอดรถ (V): "+carout_payment_amount+" บาท^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,840^FDค่าปรับค้างคืน(N): "+carout_payment_fine_amount+" บาท^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,870^FDค่าปรับบัตรหาย(N): 0 บาท^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,900^FDจำนวนเงิน Amount: "+carout_payment_totle+" บาท^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,930^FDชำระด้วยบัตรเครดิต: 0 บาท^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO40,960^FDช่องทำชำระเงิน: "+carout_payment_type_name_th+"^FS\n" +
                        "^FS\n" +


                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO170,1000^FDV = VAT , N = NON VAT^FS\n" +
                        "^FS\n" +

                        "^CWT,E:ANGSANA.TTF\n" +
                        "^CFT,30,30\n" +
                        "^CI28\n" +
                        "^FO120,1030^FDขอบคุณที่ใช้บริการ Thank you^FS\n" +
                        "^FS\n" +
                        "^XZ;";





        return zpl_out;
    }




    public  String getStringZplTEST() {

        String zpl_test = "^XA\n" +
                "^FO220,120^GFA,2520,2520,18,,::::::::::::::::::::R03IFEL01FC,P07CJFC01IFE7F,P01KFI07JFE,Q0JFCI01JF8,P01JF8J07BFF8,P03IFEK03IFC,P0JF8K01IFE,O03JF8K01JF8,O07JFCK03JFE,N01JF3EK0FE7IF,N07IFE0F8I03F81IFC,N0JF803EI07F00JF,M03IFEI0F001FC003IF8,M0JFCI07C07FJ0IFE,L01JFJ01F0FCJ07IF8,L07IFCK07BF8J01IFE,K01JF8K01FEL07IF,K07IFEL037CL03IFC,K0JF8L07FCM0JF,J03JFL01FDFM03IF8,J0JFCL07F07CL01IFE,I01JFM0FE01FM07IF8,I07IFEL03F800F8L01IFC,001JF8L0FEI03EM0JF,003IFEL01FCJ0F8L03IFC,00JFCL07FK03CM0IFE,01JFM0FEK03FM07IF8,00JF8L07FK03EM0JF,007IFEL03F8J0F8L03IFC,001JF8L0FEI03EM07IF,I07IFCL03F8007CL01IFE,I03JFL01FC01FM07IF8,J0JFCL07F07CM0IFE,J03IFEL01FCF8L03IFC,J01JF8L0FFEM0JF,K07IFEL03FCL01IFC,K01JFL01FEL07IF8,L0JFCK07FFK01IFE,L03JFJ01F1FCJ03IF8,M0JF8I03E07FJ0JF,M03IFEI0F801F8003IFC,M01JF803EI0FE007IF,N07IFC07CI03F81IFE,N01JF1FK0FC7IF8,O0KFCK07JFE,O03JF8K01FF7F8,P0JF8K01JF,P07IFEK01IFC,P01JFK07IF8,Q0JFCI01JF8,P01KFI03FF7FC,P03EJF800JF7F,P0F83IFE03IFC1FC,,::::::::::001F8J07F01JFC01LFC1F8,003FCJ07F01KF81LFC1F8,003FCJ0FF01KFC1LFC3FC,003FCJ0FF01KFE1LFC3FC,003FEJ0FF01FI0FFI03EI03FC,003FEI01FF01EI03FI03EI07FE,003FFI01FF01EI01FI03EI07BE,003FFI01FF01EJ0F8003EI0F9F,003EFI03FF01EJ0F8003EI0F9F,003E78003DF01EJ0F8003EI0F1F,003E78003DF01EJ0F8003E001F0F8,003E780079F01EJ0F8003E001F0F8,003E3C0079F01EI01FI03E001E078,003E3C0079F01EI01FI03E003E07C,003E3C00F1F01EI03FI03E003C07C,003E1E00F1F01F001FEI03E007C03E,003E1E00F1F01KFCI03E007C03E,003E1E01E1F01KFJ03E007803E,003E0F01E1F01KF8I03E00F801F,003E0F03E1F01KFCI03E00F801F,003E0F03C1F01EI07EI03E01KF8,003E0783C1F01EI03EI03E01KF8,003E0787C1F01EI01FI03E01KF8,003E03C781F01EI01FI03E03KFC,003E03C781F01EI01FI03E03EI07C,003E03CF01F01EI01FI03E03CI07E,003E01EF01F01EI01FI03E07CI03E,003E01FF01F01EJ0FI03E07CI03E,003E01FE01F01EJ0FI03E0F8I03F,003E00FE01F01EJ0FI03E0F8I01F,003E00FE01F01EJ0F8003E0F8I01F,003E00FC01F01EJ0F8003E1FJ01F8,003E007C01F01EJ0F8003E1FK0F8,001E007C01F01EJ07C003E1EK0FC,,::::::::::::::::::::^FS\n" +


                              "^XZ;";





        return zpl_test;
    }








}
