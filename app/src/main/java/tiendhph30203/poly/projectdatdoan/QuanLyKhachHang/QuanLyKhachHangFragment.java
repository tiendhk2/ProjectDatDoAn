package tiendhph30203.poly.projectdatdoan.QuanLyKhachHang;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tiendhph30203.poly.projectdatdoan.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLyKhachHangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLyKhachHangFragment extends Fragment {

    public QuanLyKhachHangFragment() {

    }

    public static QuanLyKhachHangFragment newInstance() {
        QuanLyKhachHangFragment fragment = new QuanLyKhachHangFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_quan_ly_khach_hang, container, false);
        return view;
    }
}