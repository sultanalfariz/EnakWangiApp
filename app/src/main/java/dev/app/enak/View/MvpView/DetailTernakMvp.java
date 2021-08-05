package dev.app.enak.View.MvpView;

import java.util.List;

import dev.app.enak.Model.DetailTernakResource;

public interface DetailTernakMvp {
    void LoadData(List<DetailTernakResource> detailTernakResource);
    void DataNull();
}
