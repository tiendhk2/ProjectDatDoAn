package tiendhph30203.poly.projectdatdoan.ManHinhChinh;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tiendhph30203.poly.projectdatdoan.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManHinhChinhFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManHinhChinhFragment extends Fragment {



    public ManHinhChinhFragment() {

    }

    public static ManHinhChinhFragment newInstance() {
        ManHinhChinhFragment fragment = new ManHinhChinhFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_man_hinh_chinh, container, false);
        return view;
    }
}