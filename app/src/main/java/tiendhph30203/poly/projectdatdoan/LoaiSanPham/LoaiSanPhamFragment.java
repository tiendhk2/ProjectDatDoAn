package tiendhph30203.poly.projectdatdoan.LoaiSanPham;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tiendhph30203.poly.projectdatdoan.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoaiSanPhamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoaiSanPhamFragment extends Fragment {


    public LoaiSanPhamFragment() {

    }

    public static LoaiSanPhamFragment newInstance() {
        LoaiSanPhamFragment fragment = new LoaiSanPhamFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loai_san_pham, container, false);
        return view;
    }
}