package demo.yc.joviality.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.OnClick;
import demo.yc.joviality.ui.activity.base.BaseAppActivity;
import demo.yc.joviality.ui.fragment.ImageListFragment;
import demo.yc.joviality.ui.fragment.base.SubTypeFragment;
import demo.yc.jovialityyc.R;
import demo.yc.lib.utils.CommonUtil;

public class SearchActivity extends BaseAppActivity
{

    @BindView(R.id.search_back_btn)
    ImageView mBackBtn;
    @BindView(R.id.search_input_text)
    EditText mInputText;
    @BindView(R.id.search_audio_btn)
    ImageView mAudioBtn;
    @BindView(R.id.search_check_btn)
    ImageView mCheckBtn;

    String type;
    SubTypeFragment lastFrag;
    String lastContent="";
    FragmentManager fm;
    @BindView(R.id.search_type_layout)
    LinearLayout mTypeLayout;

    @Override
    protected void getBundleExtras(Bundle extras)
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_search;
    }

    @Override
    protected void initEvents()
    {
        fm = getSupportFragmentManager();
    }

    @OnClick(R.id.search_back_btn)
    public void onMBackBtnClicked()
    {
        lastFrag = null;
        finish();
    }

    @OnClick(R.id.search_audio_btn)
    public void onMAudioBtnClicked()
    {
    }

    @OnClick(R.id.search_check_btn)
    public void onMCheckBtnClicked()
    {
        String content = mInputText.getText().toString();
        if (!CommonUtil.isEmpty(content))
        {
            if (!content.equals(lastContent))
            {
                remove(lastFrag);
                lastContent = content;
                lastFrag = ImageListFragment.newInstance(content);
                mTypeLayout.setVisibility(View.GONE);
                add(lastFrag);
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        if (lastFrag != null)
        {
            remove(lastFrag);
            mTypeLayout.setVisibility(View.VISIBLE);
        } else
            super.onBackPressed();
    }

    private void add(SubTypeFragment fragment)
    {
        fm.beginTransaction().add(R.id.search_frag_layout,fragment).commit();
        lastFrag.setUserVisibleHint(true);
    }

    private void remove(SubTypeFragment fragment)
    {
        if(fragment != null)
        {
            fragment.setUserVisibleHint(false);
            fm.beginTransaction().remove(fragment).commit();
            lastFrag = null;
        }

    }



}
