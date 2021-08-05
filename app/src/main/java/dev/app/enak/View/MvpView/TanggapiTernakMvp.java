package dev.app.enak.View.MvpView;

import java.util.List;

import dev.app.enak.Model.DetailTernakResource;

public interface TanggapiTernakMvp {
    void PostSuccess();
    void PostFailed();
    void LoadData(List<DetailTernakResource> detailTernakResource);
    void DataNull();
}
