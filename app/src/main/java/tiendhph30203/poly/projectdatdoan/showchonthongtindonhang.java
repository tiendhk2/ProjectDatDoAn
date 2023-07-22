package tiendhph30203.poly.projectdatdoan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.DonMua.GioHang;
import tiendhph30203.poly.projectdatdoan.DonMua.GioHangDAO2;
import tiendhph30203.poly.projectdatdoan.QuanLyKhachHang.KhachHang;
import tiendhph30203.poly.projectdatdoan.QuanLyKhachHang.KhachHangDAO;
import tiendhph30203.poly.projectdatdoan.SanPham.SanPham;
import tiendhph30203.poly.projectdatdoan.SanPham.SanPhamDAO;

public class showchonthongtindonhang extends AppCompatActivity {
    Toolbar toolbar;
    SanPham sanPham;
    ArrayList<SanPham> list;
    int numberOrder = 1, tienSanPham = 0;
    TextView tvTenSanPhamChonThongTin, tvGiaSanPhamChonThongTin, tvSoLuongShowDetal, tvNoiDungChonThongTinDonHang;
    ImageView imgAnhSanPhamChonThongTin, imgMinus, imgPlus;
    Button btnThemVaoGioHang;
    KhachHang khachHang;
    private SanPhamDAO sanPhamDAO;
    private GioHangDAO2 gioHangDAO2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showchonthongtindonhang);
        toolbar = (Toolbar) findViewById(R.id.idToolBarGioHang);
        tvTenSanPhamChonThongTin = findViewById(R.id.tvTenSanPhamChonThongTin);
        tvGiaSanPhamChonThongTin = findViewById(R.id.tvGiaSanPhamChonThongTin);
        tvSoLuongShowDetal = findViewById(R.id.tvSoLuongShowDetal);
        tvNoiDungChonThongTinDonHang = findViewById(R.id.tvNoiDungChonThongTinDonHang);
        imgAnhSanPhamChonThongTin = findViewById(R.id.imgAnhSanPhamChonThongTin);
        imgMinus = findViewById(R.id.imgMinus);
        imgPlus = findViewById(R.id.imgPlus);
        btnThemVaoGioHang = findViewById(R.id.btnThemVaoGioHang);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hóa đơn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sanPhamDAO = new SanPhamDAO(this);
        gioHangDAO2 = new GioHangDAO2(this);
        list = (ArrayList<SanPham>) sanPhamDAO.getAll();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        int position = bundle.getInt("position");
        tvTenSanPhamChonThongTin.setText(list.get(position).getTensanpham());
        tvGiaSanPhamChonThongTin.setText(list.get(position).getGiasanpham() + " VNĐ");

        tvNoiDungChonThongTinDonHang.setText(
                "Tên sản phẩm : " + list.get(position).getTensanpham()
                        + "\n" + "Giá : " + list.get(position).getGiasanpham() + "\n"
                        + "Số lượng trong kho: " + list.get(position).getSoluongtrongkho() + "\n"
                        + "Ngày sản xuất : " + list.get(position).getNgaysanxuat()
                        + "\n" + "Hạn sử dụng : " + list.get(position).getHansudung());
        tvSoLuongShowDetal.setText(numberOrder + "");
        imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder < list.get(position).getSoluongtrongkho()) {
                    numberOrder = numberOrder + 1;
                }
                tvSoLuongShowDetal.setText(numberOrder + "");
            }

        });
        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                tvSoLuongShowDetal.setText(numberOrder + "");
            }
        });

        SharedPreferences preferences = getSharedPreferences("thongtin", MODE_PRIVATE);
        String userName = (preferences.getString("username", ""));
        String quyen = (preferences.getString("loaitaikhoan", ""));
        if (quyen.equals("nguoidung")) {
            KhachHangDAO khachHangDAO = new KhachHangDAO(this);
            khachHang = khachHangDAO.getUserName(userName);
        }

        btnThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GioHang gioHang = new GioHang();
                if (quyen.equals("nguoidung")) {
                    if (gioHangDAO2.checkValue(list.get(position).getMasanpham() + "", khachHang.getManguoidung() + "") > 0) {
                        Toast.makeText(showchonthongtindonhang.this, "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (list.get(position).getSoluongtrongkho() == 0) {
                    Toast.makeText(showchonthongtindonhang.this, "Sản phẩm đã hết hàng", Toast.LENGTH_SHORT).show();
                    return;
                }
                gioHang.setMasanpham(list.get(position).getMasanpham());
                gioHang.setTensanpham(list.get(position).getTensanpham());
                if (quyen.equals("nguoidung")) {
                    gioHang.setManguoidung(khachHang.getManguoidung());
                }

                if (list.get(position).getAnhsanpham() == null) {
                    gioHang.setAnhsanpham("Ảnh");
                }
                if (list.get(position).getLinkanhsanpham() == null) {
                    gioHang.setLinkanhsanpham("Link");
                }

                gioHang.setSoluong(numberOrder);
                gioHang.setGiasanpham(Integer.parseInt(list.get(position).getGiamgia()));

                long kq = gioHangDAO2.insertGioHang(gioHang);
                if (kq > 0) {
                    Toast.makeText(showchonthongtindonhang.this, "Thêm giỏ hàng thành công", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(showchonthongtindonhang.this, "Thêm giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}