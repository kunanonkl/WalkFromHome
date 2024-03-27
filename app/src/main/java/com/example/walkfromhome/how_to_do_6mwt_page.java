package com.example.walkfromhome;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import java.lang.reflect.Array;

public class how_to_do_6mwt_page extends AppCompatActivity {
    private RadioButton radioButton;
    Button next_button,back_button;
    CheckBox check_rule;
    private int check_for_checkbox = 0;
    TextView how_to_do_text;
    StringBuilder stringBuilder = new StringBuilder();
    String description = "        ขั้นตอนการใช้งานแอปพลิเคชัน\n" +
            "1.  กรอกข้อมูลก่อนการทดสอบ\n" +
            "1.1. อัตราการเต้นของหัวใจและปริมาณออกซิเจนในเลือด วัดโดยใช้ Oximeter \n" +
            "1.2. ค่าความดันโลหิต วัดโดยใช้เครื่องวัดความดันโลหิต \n" +
            "1.3. ระดับความเหนื่อยสามารถกดดูระดับความเหนื่อยได้เพื่อประกอบการตัดสินใจ\n" +
            "1.4. กรอกค่าน้ำหนัก ส่วนสูง และเพศ\n" +
            "1.4.1. หากไม่สะดวกที่จะทำการ Calibrate ให้กดปุ่มถัดไป จะเข้าสู่หน้าเลือกเงื่อนไขการเดิน\n" +
            "1.4.2. หากสะดวกที่จะทำการ Calibrate ให้กดปุ่ม Calibrate โดยที่การ Calibrate ทำโดยให้กำหนดระยะทางที่ชัดเจน(แนะนำ 6 เมตรขึ้นไป) จากนั้นนำระยะทางที่ได้กับจำนวนก้าวมากรอกในแอปพลิเคชันเพื่อคำนวณระยะก้าว\n" +
            "2.  การทำการทดสอบ\n" +
            "2.1. เมื่อเข้าสู่หน้าทำการทดสอบ จะเห็นเวลา 6.10 นาที\n" +
            "2.2. เมื่อพร้อมทำการทดสอบแล้ว ให้กดปุ่มเริ่ม  แอปพลิเคชันจะทำการนับเวลาถอยหลัง 10 วินาที ก่อนเริ่มจับเวลา 6 นาที\n" +
            "2.3. ให้ผู้ทำการทดสอบนำโทรศัพติดไว้ที่บริเวณหลังก่อนเริ่มเดิน เมื่อครบ 10 วินาที แอปพลิเคชันจะแจ้งเตือนเสียงให้ทราบ และเริ่มการทดสอบได้ \n" +
            "2.4. ทุกๆ1นาทีแอปพลิเคชันจะแจ้งเตือนเพื่อให้ผู้ทำการทดสอบทราบเวลาว่าเดินไปแล้วกี่นาที เมื่อเดินครบเวลาแล้ว ให้กดปุ่มสิ้นสุดการทดสอบ\n" +
            "2.5. ในกรณีที่ไม่สามารถเดินครบเวลา 6 นาทีได้ ให้กดหยุดเวลาและกดสิ้นสุดการทดสอบ\n" +
            "2.6. หน้าสรุปผลการทดสอบ ตรวจสอบชื่อ-นามสกุล ข้อมูลจากการทดสอบว่าถูกต้องหรือไม่ และการปุ่มบันทึกข้อมูล เป็นอันสิ้นสุดการทดสอบ\n" +
            "คำแนะนำในการทำการทดสอบ\n" +
            "ในระหว่างการทดสอบผู้ทดสอบสามารถพักได้โดยไม่ต้องหยุดเวลา เมื่อพักเรียบร้อยแล้วก็สามารถทำการทดสอบได้จนครบกำหนดเวลา\n" +
            "     ข้อควรระวังในการทำการทดสอบ\n" +
            "1.ผู้ที่ควรห้ามหรือควรเฝ้าระวัง ได้แก่ ผู้รับการ" +
            "ทดสอบเคยมีอาการเจ็บหน้าอกเฉียบพลันหรือแบบไม่คงที่ (unstable angi\u0002na) หรือภาวะกล้ามเนื้อหัวใจตายบางส่วน (myocardial infarction) ในช่วง" +
            "ระยะเวลา 1 เดือนที่ผ่านมา \n" +
            "2.ขณะพักมีอัตราการเต้นของหัวใจมากกว่า 120" +
            "ครั้ง/นาที\n" +
            "3.มีความดันโลหิตช่วงหัวใจบีบตัว (systolic blood pressure)" +
            "มากกว่า180มิลลิเมตรปรอทและความดันโลหิตช่วงหัวใจคลายตัว(diastolic" +
            "blood pressure) มากกว่า 100 มิลลิเมตรปรอท\n" +
            "4.ออกซิเจนในเลือด(SpO2)ไม่ควรต่ำกว่า 95 เปอร์เซ็นต์\n" +
            "  หมายเหตุ : ควรหยุดการทดสอบเมื่อมีอาการดังนี้ \n" +
            "  เจ็บหน้าอก , เหนื่อยหอบ , ขาเป็นตะคริว , มึนงง , เดินโซเซ , เหงื่อแตกและหน้าซีด ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_do_6mwt_page);
        how_to_do_text = findViewById(R.id.scroll_text);
        stringBuilder.append(description);
        how_to_do_text.setText(stringBuilder.toString());


        next_button = findViewById(R.id.next_button_how_to_page);
        back_button = findViewById(R.id.back_button_how_to_page);
        radioButton = findViewById(R.id.radioButton);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_for_checkbox++;
                if(check_for_checkbox > 1) {
                    check_for_checkbox = 1;
                }
            }
        });


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back_page = new Intent(how_to_do_6mwt_page.this,login_page.class);
                startActivity(back_page);
                finish();
                if(GlobalVariable.login_check == true){
                    Intent back_toProfile = new Intent(how_to_do_6mwt_page.this,profile_page.class);
                    startActivity(back_toProfile);
                    finish();
                }
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_for_checkbox == 1){
                    Intent intent_data_before_test = new Intent(getApplicationContext(),data_before_test_page.class);
                    startActivity(intent_data_before_test);
                    finish();
                    check_for_checkbox =0;
                }
                else
                {
                    alert("กรุณาอ่านคำอธิบายทั้งหมด");
                }
            }
        });

    }
    public void alert(String message){
        AlertDialog dlg = new AlertDialog.Builder(how_to_do_6mwt_page.this)
                                .setTitle("Alert").setMessage(message)
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).create();
                                dlg.show();

    }
}