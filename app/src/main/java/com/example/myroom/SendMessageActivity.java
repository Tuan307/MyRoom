package com.example.myroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SendMessageActivity extends AppCompatActivity {
    EditText edtnhapsodienthoai,edtnhaptinnhan;
    Button btnguitin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        addControl();
        addEvent();
    }

    private void addEvent() {
        Intent in=getIntent();
        int tongtien=in.getIntExtra("tong",0);
        int tiendien=in.getIntExtra("dien",0);
        int tiennuoc=in.getIntExtra("nuoc",0);
        int tiendichvu=in.getIntExtra("dichvu",0);
        int tienphong=in.getIntExtra("tienphong",0);
        String sms="Tiền tháng này của bạn là: "+String.valueOf(tongtien)+"\n"
                +"Cụ thể là: "+ " Tiền phòng: "+String.valueOf(tienphong)+"\n"+
                " Tiền điện "+String.valueOf(tiendien)+"\n"
                +" Tiền nước"+String.valueOf(tiennuoc)+"\n"+" Tiền dịch vụ "+String.valueOf(tiendichvu);
        edtnhaptinnhan.setText(sms);
        btnguitin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(ContextCompat.checkSelfPermission(SendMessageActivity.this, Manifest.permission.SEND_SMS)
//                == PackageManager.PERMISSION_GRANTED)
//                {
//                    sendMess();
//                }
//                else
//                {
//                    ActivityCompat.requestPermissions(SendMessageActivity.this,new String[]{Manifest.permission.SEND_SMS},100);
//                    Toast.makeText(SendMessageActivity.this,"Bạn phải cấp quyền truy cập!!!",Toast.LENGTH_LONG ).show();
//                }
//            }
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("smsto:" + edtnhapsodienthoai.getText().toString()));
                i.putExtra("sms_body", edtnhaptinnhan.getText().toString());
                startActivity(i);
            }
        });
    }

    private void sendMess() {
        String mess=edtnhaptinnhan.getText().toString();
        String phone=edtnhapsodienthoai.getText().toString();
        if(!phone.equals("")&&!mess.equals(""))
        {
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(phone,null,mess,null,null);
            Toast.makeText(SendMessageActivity.this,"Gui tin nhan thanh cong",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(SendMessageActivity.this,"Không để trống số điện thoại và tin nhắn!!!",Toast.LENGTH_LONG ).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100 && grantResults.length>0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            sendMess();
        }
        else
        {
            Toast.makeText(SendMessageActivity.this,"Bạn phải cấp quyền truy cập!!!",Toast.LENGTH_LONG ).show();
        }
    }

    private void addControl() {
        edtnhapsodienthoai=(EditText) findViewById(R.id.nhapdienthoai);
        edtnhaptinnhan=(EditText) findViewById(R.id.nhaptinnhan);
        btnguitin=(Button) findViewById(R.id.btnguitinnhan);
    }
}