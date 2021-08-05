package dev.app.enak.View.MvpView;

import dev.app.enak.Model.GetUserResource;

public interface LoginUserMvp {
    void LoginSuccess(GetUserResource getUserResource);
    void LoginFail(String message);

}
