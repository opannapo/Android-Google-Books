package com.opannapo.googlebooks.usecase.books;

import android.content.Context;

import com.opannapo.googlebooks.core.usecase.BaseUseCaseInterface;
import com.opannapo.googlebooks.domain.BookEntity;

import java.util.List;

/**
 * Created by napouser on 23,February,2020
 */
public interface IBooksUsecase extends BaseUseCaseInterface {
    interface Action {
        void doLoadMore(Context context, String q, int start, int end);

        void doGetNext(String key, int start, int end);
    }

    interface View {
        void onProgressSeaching();

        void onSearchError(String errMsg);

        void onSearchResult(List<BookEntity> books, int totalItems);
    }
}