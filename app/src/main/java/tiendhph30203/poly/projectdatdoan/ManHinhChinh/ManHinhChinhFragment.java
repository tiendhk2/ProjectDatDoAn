package tiendhph30203.poly.projectdatdoan.ManHinhChinh;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

import tiendhph30203.poly.projectdatdoan.LoaiSanPham.Adapter_LoaiSanPham;
import tiendhph30203.poly.projectdatdoan.LoaiSanPham.LoaiSanPham;
import tiendhph30203.poly.projectdatdoan.LoaiSanPham.LoaiSanPhamDAO;
import tiendhph30203.poly.projectdatdoan.R;
import tiendhph30203.poly.projectdatdoan.SanPham.SanPham;
import tiendhph30203.poly.projectdatdoan.SanPham.SanPhamDAO;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManHinhChinhFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManHinhChinhFragment extends Fragment {
    ArrayList<LoaiSanPham> listLoaiSanPham = new ArrayList<>();
    ArrayList<SanPham> listSanPham = new ArrayList<>();


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
        View view = inflater.inflate(R.layout.fragment_man_hinh_chinh, container, false);
        ImageSlider slider = view.findViewById(R.id.slider);
        List<SlideModel> listSlide = new ArrayList<>();
        listSlide.add(new SlideModel("https://beptueu.vn/hinhanh/tintuc/top-15-hinh-anh-mon-an-ngon-viet-nam-khien-ban-khong-the-roi-mat-1.jpg", ScaleTypes.FIT));
        listSlide.add(new SlideModel("https://beptueu.vn/hinhanh/tintuc/banh-cuon-hinh-anh-mon-an-dac-san-viet-nam.jpg", ScaleTypes.FIT));
        listSlide.add(new SlideModel("https://beptueu.vn/hinhanh/tintuc/top-15-hinh-anh-mon-an-ngon-viet-nam-khien-ban-khong-the-roi-mat-5.jpg", ScaleTypes.FIT));
        listSlide.add(new SlideModel("https://beptueu.vn/hinhanh/tintuc/top-15-hinh-anh-mon-an-ngon-viet-nam-khien-ban-khong-the-roi-mat-8.jpg", ScaleTypes.FIT));
        slider.setImageList(listSlide);
        TextView textView = view.findViewById(R.id.dichuyen);
        Animation animation11 = AnimationUtils.loadAnimation(getContext(), R.anim.chuchay);
        textView.startAnimation(animation11);


        RecyclerView recyclerViewManHinhChinh = view.findViewById(R.id.recycle_loaisanphammanhinhchinh);
        LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO(getContext());
        listLoaiSanPham = (ArrayList<LoaiSanPham>) loaiSanPhamDAO.getAll();
        Adapter_LoaiSanPhamManHinhChinh adapter_loaiSanPhamManHinhChinh = new Adapter_LoaiSanPhamManHinhChinh(listLoaiSanPham, getContext(),loaiSanPhamDAO);
        recyclerViewManHinhChinh.setAdapter(adapter_loaiSanPhamManHinhChinh);


        RecyclerView recycle_sanphammanhinhchinh = view.findViewById(R.id.recycle_sanphammanhinhchinh);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recycle_sanphammanhinhchinh.setLayoutManager(gridLayoutManager);
        SanPhamDAO sanPhamDAO = new SanPhamDAO(getContext());
        listSanPham = (ArrayList<SanPham>) sanPhamDAO.getAll();
        Adapter_SanPhamManHinhChinh adapter_sanPhamManHinhChinh = new Adapter_SanPhamManHinhChinh(listSanPham, getContext(),sanPhamDAO);
        recycle_sanphammanhinhchinh.setAdapter(adapter_sanPhamManHinhChinh);


        return view;
    }

}