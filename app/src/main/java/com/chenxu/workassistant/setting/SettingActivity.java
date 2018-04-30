package com.chenxu.workassistant.setting;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.chenxu.workassistant.BaseActivity;
import com.chenxu.workassistant.R;
import com.chenxu.workassistant.databinding.ActivitySettingBinding;
import com.chenxu.workassistant.utils.StatusBarUtil;

public class SettingActivity extends BaseActivity<ActivitySettingBinding> implements SettingContract.View,View.OnClickListener {

    private SettingContract.Presenter mPresenter;
    public static final String VIEW_ANIM = "SETTING:ANIM";
    public static final String SP_SHOW_HIDE_FILE = "SP:SHOWHIDEFILE";
    public static final String SP_FILTER_FILE = "SP:FILTERFILE";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        mPresenter = new SettingPresenter(this,this);
        StatusBarUtil.darkMode(this);
        ViewCompat.setTransitionName(mBinding.tvBarTitle,VIEW_ANIM);
        mPresenter.start();
    }

    @Override
    protected void bindEvent() {
        mBinding.btnBack.setOnClickListener(this::onClick);
        mBinding.swShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mPresenter.setSPForHideFileState(b);
            }
        });
        mBinding.swFilter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mPresenter.setSPForFilterFileState(b);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void setShowHideFileState(boolean checked) {
        mBinding.swShow.setChecked(checked);
    }

    @Override
    public void setFilterFileState(boolean checked) {
        mBinding.swFilter.setChecked(checked);
    }
}