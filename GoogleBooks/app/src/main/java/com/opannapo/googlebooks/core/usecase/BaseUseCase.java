package com.opannapo.googlebooks.core.usecase;

/**
 * Created by napouser on 23,February,2020
 */
public abstract class BaseUseCase<T> {
    protected T view;


    public BaseUseCase(T view) {
        this.view = view;
    }
}