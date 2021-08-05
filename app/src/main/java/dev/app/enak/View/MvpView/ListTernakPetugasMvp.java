package dev.app.enak.View.MvpView;

import java.util.List;

import dev.app.enak.Model.ListTernakResource;

public interface ListTernakPetugasMvp {
    void LoadTernakWaitVerify(List<ListTernakResource> listTernakResources);
    void LoadTernakWaitTinjau(List<ListTernakResource> listTernakResources);
    void DataEmpty(String message);
    void DataNull();
}
