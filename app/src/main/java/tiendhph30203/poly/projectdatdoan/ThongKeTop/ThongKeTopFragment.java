package tiendhph30203.poly.projectdatdoan.ThongKeTop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.R;
import tiendhph30203.poly.projectdatdoan.SanPham.SanPham;


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
        RecyclerView recycletop10 = view.findViewById(R.id.recycleTop10);
        ThongKeTopDAO thongKe = new ThongKeTopDAO(getContext());
        ArrayList<SanPham> list = thongKe.getTop10();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycletop10.setLayoutManager(linearLayoutManager);
        Adapter_Top adapter_top10 = new Adapter_Top(getContext(), list);
        recycletop10.setAdapter(adapter_top10);
        return view;
    }
}