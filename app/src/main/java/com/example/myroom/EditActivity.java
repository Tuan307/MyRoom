package com.example.myroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    EditText edttenedit,edtngaysinhedit,edtcmndedit,edtngaythueedit,edttenphongedit,edtsodienthoaiedit,edttienphongedit
            ,edttiendichvuedit,edttiendienedit,edttiennuocedit,edtghichuedit;
    Button btnxacnhanedit;
    Database editdatabase;
    Intent intent=null;
    int pos=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editdatabase=new Database(this,"thongtinnnN.sqlite",null,1);
        addControl();
        addEvent();
    }

    private void addEvent() {
        intent =getIntent();
        RoomInfomation room=(RoomInfomation) intent.getSerializableExtra("info1");
        int mainid=room.getId();
        System.out.println(mainid);
        edttenedit.setText(room.getHovaten());
        edtngaysinhedit.setText(room.getNgaysinh());
        edtcmndedit.setText(room.getCmnd());
        edtsodienthoaiedit.setText(room.getSodienthoai());
        edtngaythueedit.setText(room.getNgaythue());
        edttenphongedit.setText(room.getTenphong());
        edttienphongedit.setText(String.valueOf(room.getTienphong()));
        edttiendichvuedit.setText(String.valueOf(room.getTiendichvu()));
        edttiennuocedit.setText(String.valueOf(room.getTiennuoc()));
        edttiendienedit.setText(String.valueOf(room.getTiendien()));
        btnxacnhanedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values=new ContentValues();
                if(edttenedit.getText().toString().equals(""))
                {
                    edttenedit.setText("Đang để trống");
                }
                if(edtngaysinhedit.getText().toString().equals(""))
                {
                    edtngaysinhedit.setText("Đang để trống");
                }
                if(edtsodienthoaiedit.getText().toString().equals(""))
                {
                    edtsodienthoaiedit.setText("Đang để trống");
                }
                if(edtcmndedit.getText().toString().equals(""))
                {
                    edtcmndedit.setText("Đang để trống");
                }
                if(edtngaythueedit.getText().toString().equals(""))
                {
                    edtngaythueedit.setText("Đang để trống");
                }
                if(edttenphongedit.getText().toString().equals(""))
                {
                    edttenphongedit.setText("Đang để trống");
                }
                if(edttienphongedit.getText().toString().equals(""))
                {
                    edttienphongedit.setText("0");
                }
                if(edttiendienedit.getText().toString().equals(""))
                {
                    edttiendienedit.setText("0");
                }
                if(edttiennuocedit.getText().toString().equals(""))
                {
                    edttiennuocedit.setText("0");
                }
                if(edttiendichvuedit.getText().toString().equals(""))
                {
                    edttiendichvuedit.setText("0");
                }
                if(edtghichuedit.getText().toString().equals(""))
                {
                    edtghichuedit.setText("Không có ghi chú");
                }
                values.put("Name",edttenedit.getText().toString());
                values.put("Age",edtngaysinhedit.getText().toString());
                values.put("Phone",edtsodienthoaiedit.getText().toString());
                values.put("Cmnd",edtcmndedit.getText().toString());
                values.put("Ngaythue",edtngaythueedit.getText().toString());
                values.put("Roomname",edttenphongedit.getText().toString());
                values.put("Tienphong",Integer.parseInt(edttienphongedit.getText().toString()));
                values.put("Tiendichvu",Integer.parseInt(edttiendichvuedit.getText().toString()));
                values.put("Tiendien",Integer.parseInt(edttiendienedit.getText().toString()));
                values.put("Tiennuoc",Integer.parseInt(edttiennuocedit.getText().toString()));
                values.put("Note",edtghichuedit.getText().toString());
                String mid=String.valueOf(mainid);
                long res=editdatabase.getWritableDatabase().update("thongtintungphonggg1",values,"Id=?",new String[]{mid});
                if(res>0)
                {
                    Toast.makeText(EditActivity.this,"Thanh cong",Toast.LENGTH_SHORT).show();
//                    Intent newin=new Intent(EditActivity.this,ActionActivity.class);
                    String name=edttenedit.getText().toString();
                    String age=edtngaysinhedit.getText().toString();
                    String phone =edtsodienthoaiedit.getText().toString();
                    String cmnd=edtcmndedit.getText().toString();
                    String ngaythue=edtngaythueedit.getText().toString();
                    String tenphong=edttenphongedit.getText().toString();
                    int tienphong=Integer.parseInt(edttienphongedit.getText().toString());
                    int tiendichvu=Integer.parseInt(edttiendichvuedit.getText().toString());
                    int tiendien=Integer.parseInt(edttiendienedit.getText().toString());
                    int tiennuoc=Integer.parseInt(edttiennuocedit.getText().toString());
                    String ghichupedit=edtghichuedit.getText().toString();
                    RoomInfomation sendroom=new RoomInfomation(name,age,phone,cmnd,ngaythue,tenphong,tienphong,tiendichvu,tiendien,tiennuoc,ghichupedit);
                    intent.putExtra("sendinfo",sendroom);
//                    startActivity(newin);
                    setResult(115,intent);
                    finish();
                }
                else
                {
                    Toast.makeText(EditActivity.this,"Khong thanh cong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addControl() {

        edttenedit=(EditText) findViewById(R.id.edttenedit);
        edtngaysinhedit=(EditText) findViewById(R.id.edtngaysinhedit);
        edtsodienthoaiedit=(EditText) findViewById(R.id.edtsodienthoaiedit);
        edtcmndedit=(EditText) findViewById(R.id.edtcmndedit);
        edtngaythueedit=(EditText) findViewById(R.id.edtngaythueedit);
        edttenphongedit=(EditText) findViewById(R.id.edttenphongedit);
        edttienphongedit=(EditText) findViewById(R.id.edttienphongedit);
        edttiendichvuedit=(EditText) findViewById(R.id.edttiendichvuedit);
        edttiendienedit=(EditText) findViewById(R.id.edttiendienedit);
        edttiennuocedit=(EditText) findViewById(R.id.edttiennuocedit);
        btnxacnhanedit=(Button) findViewById(R.id.btnxachnhanedit);
        edtghichuedit=(EditText) findViewById(R.id.edtghichuedit);
    }
}