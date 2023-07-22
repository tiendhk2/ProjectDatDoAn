package tiendhph30203.poly.projectdatdoan.SanPham;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tiendhph30203.poly.projectdatdoan.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SanPhamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SanPhamFragment extends Fragment {

    public SanPhamFragment() {

    }

    public static SanPhamFragment newInstance() {
        SanPhamFragment fragment = new SanPhamFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view =inflater.inflate(R.layout.fragment_san_pham, container, false);
        return view;
    }
}