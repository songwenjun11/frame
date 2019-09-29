package com.example.frame.classimp;

import com.example.frame.base.BasePresenter;
import com.example.frame.configs.ApiConfig;
import com.example.frame.configs.RefreshConfig;
import com.example.frame.interfaces.ICommonPresenter;
import com.example.frame.interfaces.ICommonView;

/**
 * Song Wenjun
 * Created by dell on 2019/2/23 14:15
 * Created prepare
 * package is com.example.frame.classimp
 * <p>
 * This class is used to do:超级p层，万能p层
 */
public class CommonPresenter extends BasePresenter implements ICommonPresenter, ICommonView {
    @Override
    public void universalNode(RefreshConfig refreshConfig, ApiConfig apiConfig, Object... t) {
        switch (refreshConfig) {
            case GET_DATA: {
                if (getModule() != null)
                    getModule().getData(this, refreshConfig, apiConfig, t);
            }
            break;
            case FORM_DATA: {
                if (getModule() != null)
                    getModule().fromData(this, refreshConfig, apiConfig, t);
            }
            break;
            case JSON_DATA: {
                if (getModule() != null)
                    getModule().jsonData(this, String.valueOf(t[1]), refreshConfig, apiConfig, t);
            }
            break;
            case POST_DATA: {
                if (getModule() != null)
                    getModule().postData(this, refreshConfig, apiConfig, t);
            }
            break;
            case FILE_COMMIT: {
                if (getModule() != null)
                    getModule().fileCommit(this, refreshConfig, apiConfig, t);
            }
            break;
        }
    }

    @Override
    public void onRespone(RefreshConfig refreshConfig, ApiConfig apiConfig, Object o) {
        if (getView() != null) {
            getView().onRespone(refreshConfig, apiConfig, o);
        }
    }

    @Override
    public void onError(ApiConfig apiConfig, String e) {
        if (getView() != null) {
            getView().onError(apiConfig, e);
        }
    }
}
