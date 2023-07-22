package tiendhph30203.poly.projectdatdoan.AdapterHoaDon;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import tiendhph30203.poly.projectdatdoan.FragmentHoaDon.DaGiaoFragment;
import tiendhph30203.poly.projectdatdoan.FragmentHoaDon.DangGiaoFragment;
import tiendhph30203.poly.projectdatdoan.FragmentHoaDon.DonMua2Fragment;
import tiendhph30203.poly.projectdatdoan.FragmentHoaDon.DonMuaFragment;

public class DonMuaAdapter extends FragmentStateAdapter {
    public DonMuaAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new DonMuaFragment();
        }
        return new DonMua2Fragment();
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
