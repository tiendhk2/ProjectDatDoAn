package tiendhph30203.poly.projectdatdoan.FragmentHoaDon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.DonMua.Adapter_DaGiao;
import tiendhph30203.poly.projectdatdoan.DonMua.Adapter_DangGiao;
import tiendhph30203.poly.projectdatdoan.DonMua.HoaDon;
import tiendhph30203.poly.projectdatdoan.DonMua.HoaDonDAO;
import tiendhph30203.poly.projectdatdoan.R;

public class DangGiaoFragment extends Fragment {

    RecyclerView recycleViewDangGiao;
    private RecyclerView recyclerViewDonMua;
    HoaDonDAO qlhd;
    private HoaDonDAO hoaDonDAO;
    ArrayList<HoaDon> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_don_mua, container, false);
        recyclerViewDonMua = view.findViewById(R.id.rcDonMua);
        loadData();
        return view;
    }
    public void loadData() {
        qlhd = new HoaDonDAO(getContext());
        list = (ArrayList<HoaDon>) hoaDonDAO.getAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewDonMua.setLayoutManager(linearLayoutManager);
        Adapter_DangGiao adapter_phieuMuon = new Adapter_DangGiao(list, getContext(),qlhd);
        recyclerViewDonMua.setAdapter(adapter_phieuMuon);
    }



}
