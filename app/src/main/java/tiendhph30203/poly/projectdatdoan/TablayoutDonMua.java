package tiendhph30203.poly.projectdatdoan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import tiendhph30203.poly.projectdatdoan.AdapterHoaDon.DonMuaAdapter;
import tiendhph30203.poly.projectdatdoan.AdapterHoaDon.HoaDonApdater;

public class TablayoutDonMua extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tablayoutdonmua, container, false);
        TabLayout tabLayout2 = view.findViewById(R.id.tabLayout2);
        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager2);
        DonMuaAdapter adapter = new DonMuaAdapter(getActivity());
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout2, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Đang Giao");

                } else {
                    tab.setText("Đã Giao");
                }

            }
        }).attach();
        return view;
    }
}
