package dev.app.enak.View.MvpView;

import dev.app.enak.Model.CekIdResource;

public interface CekIdMvp {

    void CekSuccess(CekIdResource cekIdResource);
    void CekFail(String message);
}
