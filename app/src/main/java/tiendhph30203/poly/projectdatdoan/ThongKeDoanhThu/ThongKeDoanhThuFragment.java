package tiendhph30203.poly.projectdatdoan.ThongKeDoanhThu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tiendhph30203.poly.projectdatdoan.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongKeDoanhThuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongKeDoanhThuFragment extends Fragment {


    public ThongKeDoanhThuFragment() {

    }


    public static ThongKeDoanhThuFragment newInstance() {
        ThongKeDoanhThuFragment fragment = new ThongKeDoanhThuFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_thong_ke_doanh_thu, container, false);
        return view;
    }
}