package tiendhph30203.poly.projectdatdoan.AdapterHoaDon;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

import tiendhph30203.poly.projectdatdoan.DonMua.HoaDon;
import tiendhph30203.poly.projectdatdoan.DonMua.HoaDonDAO;
import tiendhph30203.poly.projectdatdoan.FragmentHoaDon.DaGiaoFragment;
import tiendhph30203.poly.projectdatdoan.FragmentHoaDon.DangGiaoFragment;
import tiendhph30203.poly.projectdatdoan.R;

public class HoaDonApdater extends FragmentStateAdapter {

    public HoaDonApdater(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new DangGiaoFragment();
        }
        return new DaGiaoFragment();
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
