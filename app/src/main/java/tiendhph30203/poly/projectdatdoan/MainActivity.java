package tiendhph30203.poly.projectdatdoan;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import tiendhph30203.poly.projectdatdoan.FragmentHoaDon.DonMuaFragment;
import tiendhph30203.poly.projectdatdoan.GioHang.XacNhanThongTinGioHang;
import tiendhph30203.poly.projectdatdoan.LoaiSanPham.LoaiSanPhamFragment;
import tiendhph30203.poly.projectdatdoan.ManHinhChinh.ManHinhChinhFragment;
import tiendhph30203.poly.projectdatdoan.QuanLyKhachHang.QuanLyKhachHangFragment;
import tiendhph30203.poly.projectdatdoan.SanPham.SanPhamFragment;
import tiendhph30203.poly.projectdatdoan.TaiKhoan.TaiKhoanDAO;
import tiendhph30203.poly.projectdatdoan.ThongKeDoanhThu.ThongKeDoanhThuFragment;
import tiendhph30203.poly.projectdatdoan.ThongKeTop.ThongKeTopFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FrameLayout frameLayout;
    Toolbar toolbar;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.Toobar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.Nav);
        frameLayout = findViewById(R.id.LayoutConten);
        drawerLayout = findViewById(R.id.Drawer);

        if (savedInstanceState == null) {
            repLaceFragment(ManHinhChinhFragment.newInstance());
            setTitle("Màn hình chính");
        }


        // Thay đổi avatar
        View header = navigationView.getHeaderView(0);
        TextView txtTen = header.findViewById(R.id.txtTen);
        TextView txtMail = header.findViewById(R.id.txtMail);
        ImageView thaydoianh = header.findViewById(R.id.thaydoianh);


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, toolbar, 0, 0);
        drawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences("thongtin", MODE_PRIVATE);
        String loaitaikhoan = sharedPreferences.getString("loaitaikhoan", "");
        if (!loaitaikhoan.equals("admin")) {
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.itLoaiSanPham).setVisible(false);
            menu.findItem(R.id.itSanPham).setVisible(false);
            menu.findItem(R.id.itQuanLyHoaDon).setVisible(false);
            menu.findItem(R.id.itQuanLyKhachHang).setVisible(false);
            menu.findItem(R.id.itThongKeDT).setVisible(false);
            menu.findItem(R.id.itThongKeTop).setVisible(false);
        }
        String hoten = sharedPreferences.getString("hoten", "");
        String email = sharedPreferences.getString("email", "");
        txtTen.setText(hoten);
        txtMail.setText(email);

        ActivityResultLauncher<Intent> chooseImage = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Uri selectedImageUri = data.getData();
                            if (null != selectedImageUri) {
                                thaydoianh.setImageURI(selectedImageUri);
                                BitmapDrawable bitmapDrawable = (BitmapDrawable) thaydoianh.getDrawable();
                                bitmap = bitmapDrawable.getBitmap();

                            }
                        }
                    }
                });

        thaydoianh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                chooseImage.launch(i);

            }
        });
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itManHinhChinh) {
            repLaceFragment(ManHinhChinhFragment.newInstance());
            drawerLayout.close();
        } else if (id == R.id.itLoaiSanPham) {
            repLaceFragment(LoaiSanPhamFragment.newInstance());
            drawerLayout.close();
        } else if (id == R.id.itSanPham) {
            repLaceFragment(SanPhamFragment.newInstance());
            drawerLayout.close();
        } else if (id == R.id.itQuanLyHoaDon) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = null;
            fragment = new Tablayoutactivity();
            drawerLayout.close();
            fragmentManager.beginTransaction().replace(R.id.LayoutConten, fragment).commit();
        } else if (id == R.id.itQuanLyDonMua) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = null;
            fragment = new TablayoutDonMua();
            drawerLayout.close();
            fragmentManager.beginTransaction().replace(R.id.LayoutConten, fragment).commit();
        } else if (id == R.id.itQuanLyKhachHang) {
            repLaceFragment(QuanLyKhachHangFragment.newInstance());
            drawerLayout.close();
        }  else if (id == R.id.itGioHang) {
            startActivity(new Intent(MainActivity.this,XacNhanThongTinGioHang.class));
            drawerLayout.close();
        }else if (id == R.id.itThongKeDT) {
            repLaceFragment(ThongKeDoanhThuFragment.newInstance());
            drawerLayout.close();
        } else if (id == R.id.itThongKeTop) {
            repLaceFragment(ThongKeTopFragment.newInstance());
            drawerLayout.close();
        } else if (id == R.id.itDoiMatKhau) {
            showDialogDoiMatKhau();
            drawerLayout.close();
        } else if (id == R.id.itDangXuat) {
            Intent intent = new Intent(MainActivity.this, manhinhlogin.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {

            repLaceFragment(ManHinhChinhFragment.newInstance());

            return super.onOptionsItemSelected(item);
        }
        toolbar.setTitle(item.getTitle());

        return true;
    }

    //Chuyển fragment
    public void repLaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.LayoutConten, fragment);
        fragmentTransaction.commit();
    }


    private void showDialogDoiMatKhau() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.diaalog_doimatkhau, null);
        EditText edtPassCu = view.findViewById(R.id.edtPassCu);
        EditText edtPassMoi = view.findViewById(R.id.edtPassMoi);
        EditText edtPassMoi2 = view.findViewById(R.id.edtPassMoi2);

        builder.setView(view);
        builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passcu = edtPassCu.getText().toString().trim();
                String passmoi = edtPassMoi.getText().toString().trim();
                String passmoi2 = edtPassMoi2.getText().toString().trim();

                if (passmoi.equals(passmoi2)) {
                    SharedPreferences sharedPreferences = getSharedPreferences("thongtin", MODE_PRIVATE);
                    String username = sharedPreferences.getString("username", "");
                    TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(MainActivity.this);
                    boolean check = taiKhoanDAO.capnhatmatkhau(username, passcu, passmoi);
                    if (check) {
                        Toast.makeText(MainActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, manhinhlogin.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Mật khẩu phải trùng với nhau", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}