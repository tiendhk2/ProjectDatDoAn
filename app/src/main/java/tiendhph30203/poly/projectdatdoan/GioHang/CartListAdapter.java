package beemart.fpoly.beemart.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


import tiendhph30203.poly.projectdatdoan.DonMua.GioHang;
import tiendhph30203.poly.projectdatdoan.DonMua.GioHangDAO2;
import tiendhph30203.poly.projectdatdoan.Interface.ChangeNumberItemCartList;
import tiendhph30203.poly.projectdatdoan.R;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartListViewHolder> {
    private ArrayList<GioHang> list;
    private GioHangDAO2 gioHangDAO2;
    private ChangeNumberItemCartList changeNumberItemCartList;
    private Context context;

    public CartListAdapter(ArrayList<GioHang> list, Context context, ChangeNumberItemCartList changeNumberItemCartList) {
        this.list = list;
        this.gioHangDAO2 = new GioHangDAO2(context);
        this.changeNumberItemCartList = changeNumberItemCartList;
        this.context = context;
    }

    @NonNull
    @Override
    public CartListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cart_list_adapter, parent, false);
        return new CartListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListViewHolder holder, int position) {
        final GioHang obj = list.get(position);
        if(obj == null){
            return;
        }


        if(obj.getAnhsanpham() == null){
//            Picasso.get().load(obj.getLinkAnhSP()).into(holder.imageAvataSanPham);
            obj.setAnhsanpham("Anh");
        }else if(obj.getLinkanhsanpham() == null){
//            Bitmap bitmap = BitmapFactory.decodeByteArray(obj.getAnhSP(),0,obj.getAnhSP().length);
//            holder.imageAvataSanPham.setImageBitmap(bitmap);.
            obj.setLinkanhsanpham("Anh");
        }
        holder.tenSanPham.setText(obj.getTensanpham());
        holder.tvGiaGoc.setText(obj.getGiasanpham()+" VND");
        holder.tvSoLuong.setText(obj.getSoluong()+"");
        holder.tvGiaTong.setText((Math.round(obj.getSoluong()*obj.getGiasanpham()*100)/100) +" VNĐ");
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gioHangDAO2.plusNumber(list, position,context,holder.itemView.getRootView(), new ChangeNumberItemCartList() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemCartList.changed();
                    }
                });
            }
        });
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gioHangDAO2.minusNumber(list, position,holder.itemView.getContext(), new ChangeNumberItemCartList() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemCartList.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public class CartListViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageAvataSanPham,btnPlus;
        private ImageButton btnMinus;
        private TextView tenSanPham,tvSoLuong,tvGiaGoc,tvGiaTong;
        public CartListViewHolder(@NonNull View itemView) {
            super(itemView);

            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            tenSanPham = itemView.findViewById(R.id.tenSanPham);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
            tvGiaGoc = itemView.findViewById(R.id.tvGiaGoc);
            tvGiaTong = itemView.findViewById(R.id.tvGiaTong);

        }
    }
}
