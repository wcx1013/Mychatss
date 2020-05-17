package com.mychat.fragments.chat;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.mychat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageFragment extends Fragment {

    @BindView(R.id.img_big)
    PhotoView imgBig;

    private SingleClick singleClick;


    public static ImageFragment getInstance(String url){
        ImageFragment fragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void addSingleClickListener(SingleClick singleClick){
        this.singleClick = singleClick;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_preview, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        String url  = getArguments().getString("url");
        if(!TextUtils.isEmpty(url)){
            Glide.with(getContext()).load(url).into(imgBig);
        }

        //设置photoview单击事件
        PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(imgBig);
        photoViewAttacher.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                //单击图片
                if(singleClick != null){
                    singleClick.click();
                }
            }
        });
    }

    public interface SingleClick{
        void click();
    }

}
