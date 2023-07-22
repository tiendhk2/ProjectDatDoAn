package tiendhph30203.poly.projectdatdoan.ManHinhChinh;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.LoaiSanPham.Adapter_LoaiSanPham;
import tiendhph30203.poly.projectdatdoan.LoaiSanPham.LoaiSanPham;
import tiendhph30203.poly.projectdatdoan.LoaiSanPham.LoaiSanPhamDAO;
import tiendhph30203.poly.projectdatdoan.R;

public class Adapter_LoaiSanPhamManHinhChinh extends RecyclerView.Adapter<Adapter_LoaiSanPhamManHinhChinh.ViewHolder> {

    ArrayList<LoaiSanPham> list;
    Context context;
    private LoaiSanPhamDAO loaiSanPhamDAO;

    public Adapter_LoaiSanPhamManHinhChinh(ArrayList<LoaiSanPham> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public Adapter_LoaiSanPhamManHinhChinh(ArrayList<LoaiSanPham> list, Context context, LoaiSanPhamDAO loaiSanPhamDAO) {
        this.list = list;
        this.context = context;
        this.loaiSanPhamDAO = loaiSanPhamDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisanphammanhinhchinh, parent, false);
        return new Adapter_LoaiSanPhamManHinhChinh.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTenLoaiSanPhamManHinhChinh.setText(list.get(position).getTenLoaiSanPham());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenLoaiSanPhamManHinhChinh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenLoaiSanPhamManHinhChinh = itemView.findViewById(R.id.tvTenLoaiSanPhamManHinhChinh);

        }
    }
}
