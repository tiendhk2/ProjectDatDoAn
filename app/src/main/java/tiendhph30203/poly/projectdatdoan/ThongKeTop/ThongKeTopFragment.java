package tiendhph30203.poly.projectdatdoan.ThongKeTop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tiendhph30203.poly.projectdatdoan.R;


public class ThongKeTopFragment extends Fragment {


    public ThongKeTopFragment() {

    }


    public static ThongKeTopFragment newInstance() {
        ThongKeTopFragment fragment = new ThongKeTopFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke_top, container, false);
        return view;
    }
}