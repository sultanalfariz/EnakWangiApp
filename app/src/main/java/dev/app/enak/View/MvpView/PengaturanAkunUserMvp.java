package dev.app.enak.View.MvpView;

import dev.app.enak.Model.GetUserResource;

public interface PengaturanAkunUserMvp {
    void PostSuccessUpload();
    void PostSuccess();
    void PostSuccessPass(GetUserResource getUserResource);
    void PostFailPass(String message);
    void PostFail();
    void Relogin(String nik, String password);
    void LoadData(GetUserResource getUserResource);
}
