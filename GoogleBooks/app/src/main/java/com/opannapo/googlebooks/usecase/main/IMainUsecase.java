package com.opannapo.googlebooks.usecase.main;

import android.content.Context;

import com.google.gson.JsonObject;
import com.opannapo.googlebooks.core.usecase.BaseUseCaseInterface;
import com.opannapo.googlebooks.domain.BookEntity;

import java.util.List;

/**
 * Created by napouser on 23,February,2020
 */
public interface IMainUsecase extends BaseUseCaseInterface {
    interface Action extends IMainUsecase {
        void doSearch(Context context, String q);
    }

    interface View extends IMainUsecase {
        void onProgressSeaching();

        void onSearchError(String errMsg);

        void onSearchResult(JsonObject res, int totalItems);
    }
}