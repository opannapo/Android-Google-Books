package com.opannapo.googlebooks.core.utils;

import com.opannapo.googlebooks.core.view.BaseActivity;

/**
 * Created by napouser on 23,February,2020
 */
public class StringRes {
    public static String of(BaseActivity activity, int resId) {
        return activity.getResources().getString(resId);
    }

    public static String inject(BaseActivity activity, int res, Object... value) {
        return String.format(activity.getResources().getString(res), value);
    }

}
