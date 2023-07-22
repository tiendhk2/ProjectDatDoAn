package tiendhph30203.poly.projectdatdoan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.QuanLyKhachHang.Adapter_QuanLyKhachHang;
import tiendhph30203.poly.projectdatdoan.QuanLyKhachHang.KhachHang;
import tiendhph30203.poly.projectdatdoan.QuanLyKhachHang.KhachHangDAO;

public class manhinhdangky extends AppCompatActivity {
    EditText editTextUsername, editTextPassword, editTextFullName, editTextPhoneNumber, editTextEmail, editTextAddress;
    Button buttonSignUp;
    KhachHangDAO khachHangDAO;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinhdangky);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAddress = findViewById(R.id.editTextAddress);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        khachHangDAO = new KhachHangDAO(getApplicationContext());


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten = editTextUsername.getText().toString();
                String username = editTextPassword.getText().toString();
                String password = editTextFullName.getText().toString();
                String sodienthoai = editTextPhoneNumber.getText().toString();
                String email = editTextEmail.getText().toString();
                String diachi = editTextAddress.getText().toString();
                String loaitaikhoan = "nguoidung";
                boolean check = khachHangDAO.themkhachhang(hoten, username, password, sodienthoai, email, diachi, loaitaikhoan);
                if (check) {
                    Toast.makeText(manhinhdangky.this, "Thêm khách hàng thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(manhinhdangky.this, manhinhlogin.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(manhinhdangky.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}