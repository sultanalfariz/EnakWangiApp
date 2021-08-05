package dev.app.enak.View.MvpView;

import dev.app.enak.Model.GetPetugasResource;

public interface LoginPetugasMvp {
    void LoginSuccess(GetPetugasResource getPetugasResource);
    void LoginFail(String message);
}
