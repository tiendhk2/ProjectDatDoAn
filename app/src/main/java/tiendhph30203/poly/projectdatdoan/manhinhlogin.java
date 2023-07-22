package tiendhph30203.poly.projectdatdoan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import tiendhph30203.poly.projectdatdoan.TaiKhoan.TaiKhoanDAO;

public class manhinhlogin extends AppCompatActivity {
    private EditText edtUser, edtPass;
    private Button btnDangNhap, btnDangKy;
    private CheckBox cbLuuMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhlogin);
        edtUser = findViewById(R.id.edtTenDangNhap);
        edtPass = findViewById(R.id.edtMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        cbLuuMatKhau = findViewById(R.id.cbNhoMatKhau);
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(this);
        btnDangKy = findViewById(R.id.btnDangKy);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                if (user.equals("")) {
                    Toast.makeText(manhinhlogin.this, "Tên đăng nhập không được để rỗng", Toast.LENGTH_SHORT).show();
                }
                if (pass.equals("")) {
                    Toast.makeText(manhinhlogin.this, "Mật khẩu không được để rỗng", Toast.LENGTH_SHORT).show();
                } else if (taiKhoanDAO.checkDangNhap(user, pass)) {
                    Toast.makeText(manhinhlogin.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(manhinhlogin.this, MainActivity.class));
                } else {
                    Toast.makeText(manhinhlogin.this, "Tài khoản chưa có trên hệ thống", Toast.LENGTH_SHORT).show();

                }


            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(manhinhlogin.this,manhinhdangky.class));
            }
        });

    }
}