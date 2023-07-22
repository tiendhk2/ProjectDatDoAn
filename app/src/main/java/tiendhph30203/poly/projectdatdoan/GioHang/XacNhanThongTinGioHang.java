package tiendhph30203.poly.projectdatdoan.GioHang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import tiendhph30203.poly.projectdatdoan.DonMua.GioHang;
import tiendhph30203.poly.projectdatdoan.DonMua.GioHangDAO2;
import tiendhph30203.poly.projectdatdoan.QuanLyKhachHang.KhachHang;
import tiendhph30203.poly.projectdatdoan.QuanLyKhachHang.KhachHangDAO;
import tiendhph30203.poly.projectdatdoan.R;

public class XacNhanThongTinGioHang extends AppCompatActivity {
    private TextView tvKhachHang, tvSoDienThoai, tvDiaChi, tvDiemThuongHoaDon;
    private RecyclerView recyclerViewChiTietHoaDon;
    private Button btnXacNhan, btnXacNhanDiemThuong;
    private ArrayList<GioHang> list;
    private CartListComfirmAdapter cartListAdapter;
    private GioHangDAO2 gioHangDAO2;
    private Toolbar toolbar;
    private double tax;
    private KhachHangDAO khachHangDAO;
    private KhachHang objKhachHang;
    private List<PhuongThucThanhToan> listThanhToan;
    //    private SpinnerPhuongThucThanhToanAdapter spinnerPhuongThucThanhToanAdapter;
    private Spinner spinnerThanhToan;
    private EditText edDiemThuongHoaDon;
    private String tenKh, quyen, diachi;
    private int maGioHang, maNV, maKH;
    private Long soDienThoai;
    private String titlePhuongThucThanhToan, userNameKhachHang = null;
    private ConstraintLayout constraintManHinhXacNhan;
    private int maQuyen;
    private LinearLayout linearLayoutDiaChi, linerDialog;
    private double diemThuongAll = 0;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private int diemThuongSend;
    private int maPhuongThucThanhToan;
    private Boolean checkDiaChi = true;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_thong_tin_gio_hang);
        tvKhachHang = findViewById(R.id.tvKhachHang);
        tvSoDienThoai = findViewById(R.id.tvSoDienThoai);
        recyclerViewChiTietHoaDon = findViewById(R.id.recyclerViewChiTietHoaDon);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        toolbar = findViewById(R.id.idToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Giỏ Hàng");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        constraintManHinhXacNhan = findViewById(R.id.constraintManHinhXacNhan);
//        linearLayoutDiaChi = findViewById(R.id.linearLayoutDiaChi);
//        tvDiaChi = findViewById(R.id.tvDiaChi);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            getWindow().setNavigationBarColor(Color.TRANSPARENT);
//
//        } else {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }

//        khachHangDAO = new KhachHangDAO(this);
//        thongTinHoaDon();
//        thongTinNhanHang();
//        gioHangDAO2 = new GioHangDAO2(this);
//        list = (ArrayList<GioHang>) gioHangDAO2.getListCart(maGioHang+"",maQuyen);
//        initList();
//        tinhTien();
//        Log.d("zzzz", "Tong tien" + tinhTien());
//
//
//
//
//
//
//
//        btnXacNhan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(diachi == null && maNV == 0){
//                    Toast.makeText(XacNhanThongTinGioHang.this, "Bạn chưa cật nhật địa chỉ nhận hàng", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//
//
//
////                openBottomSheetFragment();
//
//            }
//        });

    }

//    private void openBottomSheetFragment() {
//        Order order = new Order(list,tenKh,soDienThoai,diachi,tongTienSauKhiGiam(),maPhuongThucThanhToan,maKH,maNV,maGioHang,maQuyen,diemThuongSend);
//        BottomSheetDialogXacNhanHoaDon bottomSheetDialogXacNhanHoaDon = BottomSheetDialogXacNhanHoaDon.newInstance(order);
//        bottomSheetDialogXacNhanHoaDon.show(getSupportFragmentManager(),bottomSheetDialogXacNhanHoaDon.getTag());
//        bottomSheetDialogXacNhanHoaDon.setCancelable(false);
//    }

    private void thongTinHoaDon() {
        SharedPreferences preferences = getSharedPreferences("thongtin", MODE_PRIVATE);
        String userName = (preferences.getString("username", ""));
        String quyen = (preferences.getString("loaitaikhoan", ""));
        if (quyen.equals("nguoidung")) {
            objKhachHang = khachHangDAO.getUserName(userName);
            if (objKhachHang != null) {
                maKH = objKhachHang.getManguoidung();

            }
        }
    }

    private void thongTinNhanHang() {
        SharedPreferences preferences = getSharedPreferences("thongtin", MODE_PRIVATE);
        String userName = (preferences.getString("username", ""));
        String quyen = (preferences.getString("loaitaikhoan", ""));
        if (quyen.equals("nguoidung")) {
            maQuyen = 1;
            objKhachHang = khachHangDAO.getUserName(userName);
            if (objKhachHang != null) {
                tvKhachHang.setText("Khách hàng : " + objKhachHang.getManguoidung());
                tenKh = objKhachHang.getHoten();
                tvSoDienThoai.setText("Số điện thoại : 0" + objKhachHang.getSodienthoai() + "");
                soDienThoai = Long.valueOf(objKhachHang.getSodienthoai());
                maGioHang = objKhachHang.getManguoidung();
                tvDiaChi.setText(objKhachHang.getDiachi() == null ? "Địa chỉ :" + "Chưa thêm --> Click vào để thêm" : "Địa chỉ :" + objKhachHang.getDiachi());
                diachi = objKhachHang.getDiachi();
                userNameKhachHang = objKhachHang.getUsername();
            }
        }
    }

    private double tinhTien(){
        double TongTien;
        double thue = 0.02;
        double phidichvu = 2000;
        tax = Math.round((gioHangDAO2.getTolalFee(maGioHang+"",maQuyen)*thue)*100)/100;
        double total = Math.round(((gioHangDAO2.getTolalFee(maGioHang+"",maQuyen)+tax)*100)/100);
        btnXacNhan.setText(total+phidichvu +" VND");
        TongTien = total+phidichvu;

        return TongTien;

    }
    public void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewChiTietHoaDon.setLayoutManager(linearLayoutManager);
        cartListAdapter = new CartListComfirmAdapter(list);
        recyclerViewChiTietHoaDon.setAdapter(cartListAdapter);
        RecyclerView.ItemDecoration itemDecoration =  new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerViewChiTietHoaDon.addItemDecoration(itemDecoration);

    }
}