package com.example.myroom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;

public class ActionActivity extends AppCompatActivity {
    TabHost tabHost;
    EditText edtten,edtngaysinh,edtcmnd,edtngaythue,edttenphong,edtsodienthoai,edttienphong,edttiendichvu,edttiendien,edttiennuoc,edtghichu;
    Button btnxacnhan;
    Database datainfo;
    ListView lvdata;
    int idpos;
    Customadapter adapter ;
    ArrayList<RoomInfomation> dataArraylist;
    ArrayList<RoomInfomation> dataArrayList1;
    public static String roomselected;
    int pos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        datainfo=new Database(this,"thongtinnnN.sqlite",null,1);
        datainfo.QuerryData("CREATE TABLE IF NOT EXISTS thongtintungphonggg1(Id INTEGER PRIMARY KEY AUTOINCREMENT,Name VARCHAR(200),Age VARCHAR(200),Phone VARCHAR(200),Cmnd VARCHAR(200)," +
                "Ngaythue VARCHAR(200),Roomname VARCHAR(200),Tienphong INTEGER,Tiendichvu INTEGER,Tiendien INTEGER,Tiennuoc INTEGER,Note VARCHAR(200))");
        addControl();
        addEvent();

    }

    private void addEvent() {
        idpos=0;
        Cursor data=datainfo.getData("SELECT * FROM thongtintungphonggg1");
        dataArraylist.clear();
        while (data.moveToNext())
        {
            int id=data.getInt(0); //id
            String a=data.getString(1); //name
            String b=data.getString(2); //age
            String c=data.getString(3); //phone
            String d=data.getString(4); //cmnd
            String e=data.getString(5); //ngaythue
            String f=data.getString(6); //roomname
            int a1=data.getInt(7); //tienphong
            int b1=data.getInt(8); //tiendichvu
            int c1=data.getInt(9); //tiendien
            int d1=data.getInt(10);//tiennuoc
            String gchu=data.getString(11); //ghi chu
            idpos=id;
            dataArraylist.add(new RoomInfomation(a,b,c,d,e,f,a1,b1,c1,d1,gchu));
            dataArrayList1.add(new RoomInfomation(id,a,b,c,d,e,f,a1,b1,c1,d1));
        }
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values=new ContentValues();
                if(edtten.getText().toString().equals(""))
                {
                    edtten.setText("Đang để trống");
                }
                if(edtngaysinh.getText().toString().equals(""))
                {
                    edtngaysinh.setText("Đang để trống");
                }
                if(edtsodienthoai.getText().toString().equals(""))
                {
                    edtsodienthoai.setText("Đang để trống");
                }
                if(edtcmnd.getText().toString().equals(""))
                {
                    edtcmnd.setText("Đang để trống");
                }
                if(edtngaythue.getText().toString().equals(""))
                {
                    edtngaythue.setText("Đang để trống");
                }
                if(edttenphong.getText().toString().equals(""))
                {
                    edttenphong.setText("Đang để trống");
                }
                if(edttienphong.getText().toString().equals(""))
                {
                    edttienphong.setText("0");
                }
                if(edttiendien.getText().toString().equals(""))
                {
                    edttiendien.setText("0");
                }
                if(edttiennuoc.getText().toString().equals(""))
                {
                    edttiennuoc.setText("0");
                }
                if(edttiendichvu.getText().toString().equals(""))
                {
                    edttiendichvu.setText("0");
                }
                if(edtghichu.getText().toString().equals(""))
                {
                    edtghichu.setText("Không có ghi chú");
                }
                values.put("Name",edtten.getText().toString());
                values.put("Age",edtngaysinh.getText().toString());
                values.put("Phone",edtsodienthoai.getText().toString());
                values.put("Cmnd",edtcmnd.getText().toString());
                values.put("Ngaythue",edtngaythue.getText().toString());
                values.put("Roomname",edttenphong.getText().toString());
                values.put("Tienphong",Integer.parseInt(edttienphong.getText().toString()));
                values.put("Tiendichvu",Integer.parseInt(edttiendichvu.getText().toString()));
                values.put("Tiendien",Integer.parseInt(edttiendien.getText().toString()));
                values.put("Tiennuoc",Integer.parseInt(edttiennuoc.getText().toString()));
                values.put("Note",edtghichu.getText().toString());

                long result=datainfo.getWritableDatabase().insert("thongtintungphonggg1",null,values);
                if(result>0)
                {
                    Toast.makeText(ActionActivity.this,"Thanh Cong",Toast.LENGTH_SHORT).show();
                    dataArraylist.add(new RoomInfomation(edtten.getText().toString(),edtngaysinh.getText().toString(),
                            edtsodienthoai.getText().toString(),edtcmnd.getText().toString(),edtngaythue.getText().toString(),edttenphong.getText().toString(),
                            Integer.parseInt(edttienphong.getText().toString()),Integer.parseInt(edttiendichvu.getText().toString()),
                            Integer.parseInt(edttiendien.getText().toString()),Integer.parseInt(edttiennuoc.getText().toString()),edtghichu.getText().toString()));
                    dataArrayList1.add(new RoomInfomation(idpos+1,edtten.getText().toString(),edtngaysinh.getText().toString(),
                            edtsodienthoai.getText().toString(),edtcmnd.getText().toString(),edtngaythue.getText().toString(),edttenphong.getText().toString(),
                            Integer.parseInt(edttienphong.getText().toString()),Integer.parseInt(edttiendichvu.getText().toString()),
                            Integer.parseInt(edttiendien.getText().toString()),Integer.parseInt(edttiennuoc.getText().toString())));
                    adapter.updated();
                }
                else
                {
                    Toast.makeText(ActionActivity.this,"Khong Thanh Cong",Toast.LENGTH_SHORT).show();
                }
            }
        });
        lvdata.setAdapter(adapter);
        lvdata.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                pos=i;
                roomselected= dataArraylist.get(pos).getTenphong();
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.contextmenu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menuxemthongtin:
                //code here
                Intent intent=new Intent(ActionActivity.this,ThongtinActivity.class);

                String a=dataArraylist.get(pos).getHovaten();
                String b=dataArraylist.get(pos).getNgaysinh();
                String c=dataArraylist.get(pos).getCmnd();
                String d=dataArraylist.get(pos).getSodienthoai();
                String e=dataArraylist.get(pos).getNgaythue();
                String f=dataArraylist.get(pos).getTenphong();
                int a1=dataArraylist.get(pos).getTienphong();
                int b1=dataArraylist.get(pos).getTiendichvu();
                int c1=dataArraylist.get(pos).getTiendien();
                int d1=dataArraylist.get(pos).getTiennuoc();
                String gichu=dataArraylist.get(pos).getGhichu();
                RoomInfomation roomInfomation=new RoomInfomation(a,b,d,c,e,f,a1,b1,c1,d1,gichu);
                intent.putExtra("info",roomInfomation);
                startActivity(intent);
                break;
            case R.id.menuedit:
                //code here
                Intent intent2=new Intent(ActionActivity.this,EditActivity.class);
                int idx=dataArrayList1.get(pos).getId();
                String ax=dataArrayList1.get(pos).getHovaten();
                String bx=dataArrayList1.get(pos).getNgaysinh();
                String cx=dataArrayList1.get(pos).getCmnd();
                String dx=dataArrayList1.get(pos).getSodienthoai();
                String ex=dataArrayList1.get(pos).getNgaythue();
                String fx=dataArrayList1.get(pos).getTenphong();
                int a1x=dataArrayList1.get(pos).getTienphong();
                int b1x=dataArrayList1.get(pos).getTiendichvu();
                int c1x=dataArrayList1.get(pos).getTiendien();
                int d1x=dataArrayList1.get(pos).getTiennuoc();
                RoomInfomation roomInfomation1=new RoomInfomation(idx,ax,bx,dx,cx,ex,fx,a1x,b1x,c1x,d1x);
                intent2.putExtra("info1",roomInfomation1);
                startActivityForResult(intent2,113);

                break;
            case R.id.menudelete:
                //code here
                dataArraylist.remove(pos);
                datainfo.QuerryData("DELETE FROM thongtintungphonggg1 WHERE Roomname = '"+roomselected +"'");
                adapter.updated();
                break;
            case R.id.menutinhtien:
                Intent intentfee=new Intent(ActionActivity.this,TinhtienActivity.class);
                String ten=dataArraylist.get(pos).getHovaten();
                String sinh=dataArraylist.get(pos).getNgaysinh();
                String cmndfee=dataArraylist.get(pos).getCmnd();
                String sodienthoaifee=dataArraylist.get(pos).getSodienthoai();
                String ngaythuefee=dataArraylist.get(pos).getNgaythue();
                String tenphongfee=dataArraylist.get(pos).getTenphong();
                int tienphongfee=dataArraylist.get(pos).getTienphong();
                int tiendichvufee=dataArraylist.get(pos).getTiendichvu();
                int tiendienfee=dataArraylist.get(pos).getTiendien();
                int tiennuocfee=dataArraylist.get(pos).getTiennuoc();
                String ggchu=dataArraylist.get(pos).getGhichu();
                RoomInfomation roomfee=new RoomInfomation(ten,sinh,cmndfee,sodienthoaifee,ngaythuefee,tenphongfee,tienphongfee,tiendichvufee,tiendienfee,tiennuocfee,ggchu);
                intentfee.putExtra("roomtotalfee",roomfee);
                startActivity(intentfee);
                //code here
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==113 && resultCode==115)
        {
            RoomInfomation getroom= (RoomInfomation) data.getSerializableExtra("sendinfo");
            dataArraylist.get(pos).setHovaten(getroom.getHovaten());
            dataArraylist.get(pos).setNgaysinh(getroom.getNgaysinh());
            dataArraylist.get(pos).setSodienthoai(getroom.getSodienthoai());
            dataArraylist.get(pos).setCmnd(getroom.getCmnd());
            dataArraylist.get(pos).setNgaythue(getroom.getNgaythue());
            dataArraylist.get(pos).setTenphong(getroom.getTenphong());
            dataArraylist.get(pos).setTienphong(getroom.getTienphong());
            dataArraylist.get(pos).setTiendien(getroom.getTiendien());
            dataArraylist.get(pos).setTiennuoc(getroom.getTiennuoc());
            dataArraylist.get(pos).setTiendichvu(getroom.getTiendichvu());
            dataArraylist.get(pos).setGhichu(getroom.getGhichu());
            dataArrayList1.get(pos).setHovaten(getroom.getHovaten());
            dataArrayList1.get(pos).setNgaysinh(getroom.getNgaysinh());
            dataArrayList1.get(pos).setSodienthoai(getroom.getSodienthoai());
            dataArrayList1.get(pos).setCmnd(getroom.getCmnd());
            dataArrayList1.get(pos).setNgaythue(getroom.getNgaythue());
            dataArrayList1.get(pos).setTenphong(getroom.getTenphong());
            dataArrayList1.get(pos).setTienphong(getroom.getTienphong());
            dataArrayList1.get(pos).setTiendien(getroom.getTiendien());
            dataArrayList1.get(pos).setTiennuoc(getroom.getTiennuoc());
            dataArrayList1.get(pos).setTiendichvu(getroom.getTiendichvu());
            dataArrayList1.get(pos).setGhichu(getroom.getGhichu());
            adapter.updated();
        }
    }

    private void addControl() {
        //set up tabhost len man hinh
        tabHost=(TabHost) findViewById(R.id.tabhost);
        tabHost.setup();
        //tao tab cho man hinh
        TabHost.TabSpec tab1= tabHost.newTabSpec("t1");// t1=id
        tab1.setContent(R.id.tab1);// thiet lap noi dung+
        tab1.setIndicator("Thêm Phòng"); //set Title
        tabHost.addTab(tab1);
        TabHost.TabSpec tab2=tabHost.newTabSpec("t2"); // t2=id
        tab2.setContent(R.id.tab2);// thiet lap noi dung+
        tab2.setIndicator("Danh sách phòng");//set Title
        tabHost.addTab(tab2);
        edtten=(EditText) findViewById(R.id.edthovaten);
        edtngaysinh=(EditText) findViewById(R.id.edtngaysinh);
        edtsodienthoai=(EditText) findViewById(R.id.edtsodienthoai);
        edtcmnd=(EditText) findViewById(R.id.edtcmnd);
        edtngaythue=(EditText) findViewById(R.id.edtngaythue);
        edttenphong=(EditText) findViewById(R.id.edttenphong);
        edttiendichvu=(EditText) findViewById(R.id.edttiendichvu);
        edttienphong=(EditText) findViewById(R.id.edttienphong);
        edttiennuoc=(EditText) findViewById(R.id.edttiennuoc);
        edttiendien=(EditText) findViewById(R.id.edttiendien);
        btnxacnhan=(Button) findViewById(R.id.btnxacnhan);
        edtghichu=(EditText) findViewById(R.id.edtghichu);
        lvdata=(ListView) findViewById(R.id.lvdata);
        dataArraylist=new ArrayList<>();
        dataArrayList1=new ArrayList<>();
        adapter=new Customadapter(this,R.layout.hienthilist,dataArraylist);
        registerForContextMenu(lvdata);
    }
}