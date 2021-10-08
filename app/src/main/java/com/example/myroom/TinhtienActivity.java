package com.example.myroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TinhtienActivity extends AppCompatActivity {
    EditText edtsonuocfee,edtngaytaodon,edttienphongfee,edttiendichvufee,edttiendienfee;
    TextView edttenfee,edttenphongfee;
    Button btntinhtien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinhtien);
        addControl();
        addEvent();
    }

    private void addEvent() {
        Intent gettotal=getIntent();
        RoomInfomation getRoom= (RoomInfomation) gettotal.getSerializableExtra("roomtotalfee");
        String name=getRoom.getHovaten().toString();
        String roomname=getRoom.getTenphong().toString();
        int tienphong=getRoom.getTienphong();
        int tiendichvu=getRoom.getTiendichvu();
        int tiendien=getRoom.getTiendien();
        int tiennuoc=getRoom.getTiennuoc();

        edttenfee.setText(name);
        edttenphongfee.setText(roomname);
        edttienphongfee.setText(String.valueOf(tienphong));
        edttiendichvufee.setText(String.valueOf(tiendichvu));
        btntinhtien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentgive=new Intent(TinhtienActivity.this,ThanhToanActivity.class);
                String tennguoithue=edttenfee.getText().toString();
                String tenphong=edttenphongfee.getText().toString();
                String ngaytao=edtngaytaodon.getText().toString();
                int tienphongthanhtoan=Integer.parseInt(edttienphongfee.getText().toString());
                int tiendichvuthanhtoan=Integer.parseInt(edttiendichvufee.getText().toString());
                if(edttiendienfee.getText().toString().equals(""))
                {
                    edttiendienfee.setText("0");
                }
                int tiendienthanhtoan=tiendien*Integer.parseInt(edttiendienfee.getText().toString());
                if(edtsonuocfee.getText().toString().equals(""))
                {
                    edtsonuocfee.setText("1");
                }
                int tiennuocthanhtoan=tiennuoc*Integer.parseInt(edtsonuocfee.getText().toString());
                RoomInfomation sendtt=new RoomInfomation(tennguoithue,tenphong,tienphongthanhtoan,tiendichvuthanhtoan,tiendienthanhtoan,tiennuocthanhtoan,ngaytao);
                intentgive.putExtra("thongtinthanhtoan",sendtt);
                startActivity(intentgive);
            }
        });

    }

    private void addControl() {
        edttenfee=(TextView) findViewById(R.id.txttenfee);
        edttenphongfee=(TextView) findViewById(R.id.edttenphongfee);
        edttienphongfee= (EditText) findViewById(R.id.edttienphongfee);
        edttiendichvufee= (EditText) findViewById(R.id.edttiendichvufee);
        edttiendienfee= (EditText) findViewById(R.id.edttiendienfee);
        edtsonuocfee=(EditText) findViewById(R.id.edttiennuocfee);
        //edttongtienfee=(TextView)findViewById(R.id.edttongtienfee);
        edtngaytaodon=(EditText) findViewById(R.id.ngaytaodonfee);
        btntinhtien=(Button) findViewById(R.id.btntinhtien);
    }
}