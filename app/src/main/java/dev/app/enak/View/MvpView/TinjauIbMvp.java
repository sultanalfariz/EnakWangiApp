package dev.app.enak.View.MvpView;

import java.util.List;

import dev.app.enak.Model.DetailIbResource;

public interface TinjauIbMvp {
    void PostSuccess();
    void PostFailed();
    void LoadData(List<DetailIbResource> detailIbResource);
    void DataNull();
}
