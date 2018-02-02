package com.yundang.mingtian;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yundang.mingtian.cart.CartFragment;
import com.yundang.mingtian.common.BaseActivity;
import com.yundang.mingtian.felicity.FelicityFragment;
import com.yundang.mingtian.home.HomeFragment;
import com.yundang.mingtian.my.MyFragment;
import com.yundang.mingtian.shopping.ShoppingFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Bind(R.id.rg_main)
    RadioGroup rg_main;
    private FragmentTransaction transaction;
    private CartFragment cartFragment;
    private FelicityFragment felicityFragment;
    private HomeFragment homeFragment;
    private MyFragment myFragment;
    private ShoppingFragment shoppingFragment;
    private int position;
    @Override
    protected void initData() {
        initFragment();
        setSelect(0);
    }

    @Override
    protected void initTitle() {

    }


    private void initFragment() {
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_felicity:
                        position = 1;
                        break;
                    case R.id.rb_shopping:
                        position = 2;
                        break;
                    case R.id.rb_cart:
                        position = 3;
                        break;
                    case R.id.rb_my:
                        position = 4;
                        break;
                }
                setSelect(position);
            }
        });
        rg_main.check(R.id.rb_home);
    }

    //提供相应的fragment的显示
    private void setSelect(int i) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        //隐藏所有Fragment的显示
        hideFragments();
        switch (i) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();//创建对象以后，并不会马上调用生命周期方法。而是在commit()之后，方才调用
                    transaction.add(R.id.fl_main, homeFragment);
                }
                //显示当前的fragment
                transaction.show(homeFragment);
                break;
            case 1:
                if (felicityFragment == null) {
                    felicityFragment = new FelicityFragment();
                    transaction.add(R.id.fl_main, felicityFragment);
                }
                transaction.show(felicityFragment);
                break;
            case 2:
                if (shoppingFragment == null) {
                    shoppingFragment = new ShoppingFragment();
                    transaction.add(R.id.fl_main, shoppingFragment);
                }
                transaction.show(shoppingFragment);
                break;
            case 3:
                if (cartFragment == null) {
                    cartFragment = new CartFragment();
                    transaction.add(R.id.fl_main, cartFragment);
                }
                transaction.show(cartFragment);

                break;
            case 4:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    transaction.add(R.id.fl_main, myFragment);
                }
                transaction.show(myFragment);
                break;
        }
        transaction.commit();//提交事务

    }

    private void hideFragments() {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (felicityFragment != null) {
            transaction.hide(felicityFragment);
        }
        if (shoppingFragment != null) {
            transaction.hide(shoppingFragment);
        }
        if (cartFragment != null) {
            transaction.hide(cartFragment);
        }
        if (myFragment != null) {
            transaction.hide(myFragment);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
