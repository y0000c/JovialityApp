package demo.yc.joviality.ui.fragment.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import demo.yc.lib.base.BaseFragment;
import demo.yc.lib.utils.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class SubTypeFragment extends BaseFragment
{

    protected static final String SUB_TYPE = "subType";

    private boolean isViewInitiated;
    private boolean isVisibleToUser;
    private boolean isDataInitiated;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.d(TAG,"is visible to User-->"+isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        initGetData();
    }

    // 所有View创建好了，这是onStart前一个生命周期
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        initGetData();
    }

    /**
     * 满足所有条件才去获取数据
     */
    protected void initGetData()
    {
        if(isViewInitiated && isVisibleToUser && !isDataInitiated)
        {
            getData();
            isDataInitiated = true;
        }
    }

    /**
     * 获取数据的地方
     */
    protected abstract void getData();

}
