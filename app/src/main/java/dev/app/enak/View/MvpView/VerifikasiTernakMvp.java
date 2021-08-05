package dev.app.enak.View.MvpView;

import java.util.List;

import dev.app.enak.Model.DetailTernakResource;

public interface VerifikasiTernakMvp {
    void VerifySuccess();
    void VerifyFail(String message);
    void ChangeImgSuccess();
    void LoadData(List<DetailTernakResource> detailTernakResource);
    void DataNull();
}
