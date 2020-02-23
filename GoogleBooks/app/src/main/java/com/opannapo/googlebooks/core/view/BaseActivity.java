package com.opannapo.googlebooks.core.view;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.opannapo.googlebooks.core.usecase.BaseUseCase;
import com.opannapo.googlebooks.core.utils.StringRes;

/**
 * Created by napouser on 23,February,2020
 */
public abstract class BaseActivity<T extends BaseUseCase> extends AppCompatActivity {
    public interface AlertDialogCallback {
        void onButtonClicked(boolean isPositive, DialogInterface dialog, int which);
    }

    public T useCase;

    public abstract T initUseCase();

    public abstract int initLayout();

    public abstract void onCreated(Bundle savedInstanceState);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.useCase = initUseCase();
        setContentView(initLayout());
        onCreated(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showSnack(String msg, boolean isPositive) {
        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        view.setBackgroundColor(Color.parseColor(isPositive ? "#1E88E5" : "#C2185B"));

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.width = FrameLayout.LayoutParams.MATCH_PARENT;
        view.setLayoutParams(params);

        snack.show();
    }

    public void showSnack(String msg, boolean isPositive, CharSequence actionLabel, final View.OnClickListener actionClick) {
        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        view.setBackgroundColor(Color.parseColor(isPositive ? "#1E88E5" : "#C2185B"));

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.width = FrameLayout.LayoutParams.MATCH_PARENT;
        view.setLayoutParams(params);

        if (actionClick != null && actionLabel != null) {
            snack.setActionTextColor(Color.parseColor("#FFFFFF"));
            snack.setAction(actionLabel, actionClick);
        }

        snack.show();
    }

    public void showDialogAlert(@NonNull String title,
                                @NonNull String msg,
                                @NonNull String positifTitle,
                                @NonNull String negativeTitle,
                                @NonNull AlertDialogCallback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton(positifTitle, (dialog, which) -> callback.onButtonClicked(true, dialog, which));
        builder.setNegativeButton(negativeTitle, (dialog, which) -> callback.onButtonClicked(false, dialog, which));
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showDialogAlert(@NonNull String title,
                                @NonNull String msg,
                                @NonNull String positifTitle,
                                @NonNull AlertDialogCallback callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton(positifTitle, (dialog, which) -> callback.onButtonClicked(true, dialog, which));
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public String stringInject(int res, Object... value) {
        return StringRes.inject(this, res, value);
    }

    public String stringOf(int res) {
        return StringRes.of(this, res);
    }
}