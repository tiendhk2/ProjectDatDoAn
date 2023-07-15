package tiendhph30203.poly.projectdatdoan.DonMua;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.R;

public class Adapter_DangGiao extends RecyclerView.Adapter<Adapter_DangGiao.ViewHolder> {
    ArrayList<HoaDon> list;
    Context context;
    private HoaDonDAO hoaDonDAO;

    public Adapter_DangGiao(ArrayList<HoaDon> list, Context context, HoaDonDAO hoaDonDAO) {
        this.list = list;
        this.context = context;
        this.hoaDonDAO = hoaDonDAO;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recyclehoadon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtMaHoaDon.setText("Mã hóa đơn: " + list.get(position).getMahoadon());
        holder.txtMaNguoiDung.setText("Mã người dùng: " + list.get(position).getManguoidung());
        holder.txtMaSanPham.setText("Mã sản phẩm: " + list.get(position).getMasanpham());
        holder.txtTenNguoiDung.setText("Tên người dùng: " + list.get(position).getHoten());
        holder.txtNgayMua.setText("Ngày mua: " + list.get(position).getNgaymua());
        holder.txtTongTien.setText("Tổng tiền: " + list.get(position).getTongtien());
        holder.txtTrangThai.setText("Trạng Thái: " + list.get(position).getTrangthai());
        holder.txtSoLuongDaMua.setText("Số lượng đã mua: " + list.get(position).getSoluongdamua());

        int tinhTrang = list.get(position).getTrangthai();
        if (tinhTrang == 0) {
            holder.txtTrangThai.setText("Trạng thái: " + "Chờ xác nhận");
            holder.btnXacNhanDonHang.setVisibility(View.VISIBLE);
            holder.txtTrangThai.setTextColor(Color.RED);

        } else if (tinhTrang == 1) {
            holder.txtTrangThai.setText("Trạng thái: " + "Đã giao");
            holder.btnXacNhanDonHang.setVisibility(View.GONE);
            holder.txtTrangThai.setTextColor(Color.GREEN);

        }

        holder.btnXacNhanDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacnhandonhang(context, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void xacnhandonhang(Context context, int i) {
        Button btnXacNhan;
        Button btnHuy;
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_xacnhandonhang);
        dialog.show();
        btnXacNhan = dialog.findViewById(R.id.btnXacNhan);
        btnHuy = dialog.findViewById(R.id.btnHuy);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDonDAO hoaDonDAO = new HoaDonDAO(context);
                HoaDon hoaDon = list.get(i);
                if (hoaDon.getTrangthai() == 0) {
                    hoaDon.setTrangthai(1);
                    hoaDonDAO.update(hoaDon);
                    list.remove(i);
                } else if (hoaDon.getTrangthai() == 1) {
                    hoaDonDAO.delete(hoaDon);
                    list.remove(i);
                }
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
//        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                HoaDonDAO hoaDonDAO = new HoaDonDAO(context);
//                HoaDon hoaDon = list.get(position);
//                if (hoaDon.getTrangthai() == 0) {
//                    holder.btnXacNhanDonHang.setVisibility(View.GONE);
//                    hoaDon.setTrangthai(1);
//                    hoaDonDAO.update(hoaDon);
//                    list.remove(position);
////                            for (int i = 0; i < list.size(); i++) {
////                                if (list.get(i).getTrangthai() == 1) {
////                                    list.add(list.get(i));
////                                }
////                            }
//
//                } else if (hoaDon.getTrangthai() == 1) {
//                    hoaDonDAO.delete(hoaDon);
//                    list.remove(position);
//                    holder.btnXacNhanDonHang.setVisibility(View.VISIBLE);
//                }
//                notifyDataSetChanged();
//            }
//        });
//        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMaHoaDon, txtMaNguoiDung, txtMaSanPham, txtTenNguoiDung, txtNgayMua, txtTongTien, txtSoLuongDaMua, txtTrangThai;
        Button btnXacNhanDonHang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaHoaDon = itemView.findViewById(R.id.txtMaHoaDon);
            txtMaNguoiDung = itemView.findViewById(R.id.txtMaNguoiDung);
            txtMaSanPham = itemView.findViewById(R.id.txtMaSanPham);
            txtTenNguoiDung = itemView.findViewById(R.id.txtTenNguoiDung);
            txtNgayMua = itemView.findViewById(R.id.txtNgayMua);
            txtTongTien = itemView.findViewById(R.id.txtTongTien);
            txtSoLuongDaMua = itemView.findViewById(R.id.txtSoLuongDaMua);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            btnXacNhanDonHang = itemView.findViewById(R.id.btnXacNhanDonHang);


        }
    }
}
