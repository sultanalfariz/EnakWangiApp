package dev.app.enak.View.MvpView;

import dev.app.enak.Model.GetPetugasResource;
import dev.app.enak.Model.GetUserResource;

public interface PengaturanAkunPetugasMvp {
    void PostSuccess();
    void PostFail();
    void PostSuccessPass(GetPetugasResource getPetugasResource);
    void PostFailPass(String message);
    void Relogin(String nip, String password);
    void LoadData(GetPetugasResource getPetugasResource);
}
