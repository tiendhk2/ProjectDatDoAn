package tiendhph30203.poly.projectdatdoan.GioHang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.DonMua.GioHang;
import tiendhph30203.poly.projectdatdoan.DonMua.GioHangDAO2;
import tiendhph30203.poly.projectdatdoan.Interface.ChangeNumberItemCartList;
import tiendhph30203.poly.projectdatdoan.QuanLyKhachHang.KhachHang;
import tiendhph30203.poly.projectdatdoan.QuanLyKhachHang.KhachHangDAO;
import tiendhph30203.poly.projectdatdoan.R;
import tiendhph30203.poly.projectdatdoan.SanPham.Adapter_SanPham;

public class GioHangActivity extends AppCompatActivity {
    private RecyclerView rclHoaHon;
    private TextView tvThanhTienMN ,tvThueVATMN,tvPhiKhacMN,tvTongTienMN,tvThongBaoKhongcogi,tvThongBaoKhongcogi2;
    private Button btnThanhToan;
    private ImageView imgNotThing;
    private Adapter_SanPham adapter_sanPham;
    private beemart.fpoly.beemart.Adapter.CartListAdapter cartListAdapter;
    private double tongTien = 0;
    private GioHangDAO2 gioHangDAO2;
    private Toolbar toolbar;
    private LinearLayout linerGioHang;
    private ScrollView scrollViewGioHang;
    private KhachHangDAO khachHangDAO;
    private KhachHang khachHang;
    private ArrayList<GioHang> listGioHang;
    private double tax;
    private int maQuyen,quyenNguoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        rclHoaHon = findViewById(R.id.rclHoaHon);
        tvThanhTienMN = findViewById(R.id.tvThanhTienMN);
        tvThueVATMN = findViewById(R.id.tvThueVATMN);
        tvPhiKhacMN = findViewById(R.id.tvPhiKhacMN);
        tvTongTienMN = findViewById(R.id.tvTongTienMN);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        toolbar = findViewById(R.id.idToolBar);
        scrollViewGioHang = findViewById(R.id.scrollViewGioHang);
        linerGioHang = findViewById(R.id.linerGioHang);

        SharedPreferences preferences = getSharedPreferences("thongtin",MODE_PRIVATE);
        String userName = (preferences.getString("username",""));
        String quyen = (preferences.getString("loaitaikhoan",""));
        khachHangDAO = new KhachHangDAO(this);
        if(quyen.equals("nguoidung")){
            khachHang = khachHangDAO.getUserName(userName);
            maQuyen = khachHang.getManguoidung();
            quyenNguoiDung = 1;
        }

        gioHangDAO2 = new GioHangDAO2(this);
        listGioHang = (ArrayList<GioHang>) gioHangDAO2.getAll(maQuyen+"",quyenNguoiDung);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rclHoaHon.setLayoutManager(linearLayoutManager);
        cartListAdapter = new beemart.fpoly.beemart.Adapter.CartListAdapter(listGioHang, this, new ChangeNumberItemCartList() {
            @Override
            public void changed() {
                tinhTien();
            }
        });

        rclHoaHon.setAdapter(cartListAdapter);

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GioHangActivity.this, XacNhanThongTinGioHang.class);
                intent.putExtra("totalMoney",tongTien);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    private void tinhTien(){
        double thue = 0.02;
        double phidichvu = 2000;
        tax = Math.round((gioHangDAO2.getTolalFee(maQuyen+"",quyenNguoiDung)*thue)*100)/100;
        double total = Math.round(((gioHangDAO2.getTolalFee(maQuyen+"",quyenNguoiDung)+tax)*100)/100);
        double itemTotal = Math.round(gioHangDAO2.getTolalFee(maQuyen+"",quyenNguoiDung)*100)/100;
        if(itemTotal > 0){
            tvThueVATMN.setText(tax +" VND");
            tvTongTienMN.setText(total+phidichvu +" VND");
            tvPhiKhacMN.setText(phidichvu + " VND");
            tvThanhTienMN.setText((itemTotal) + " VND");
            imgNotThing.setVisibility(View.INVISIBLE);
            tvThongBaoKhongcogi.setVisibility(View.INVISIBLE);
            tvThongBaoKhongcogi2.setVisibility(View.INVISIBLE);
        }else{
            tvThongBaoKhongcogi.setVisibility(View.VISIBLE);
            tvThongBaoKhongcogi2.setVisibility(View.VISIBLE);
            imgNotThing.setVisibility(View.VISIBLE);
            linerGioHang.setVisibility(View.INVISIBLE);

        }
    }
}