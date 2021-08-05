package dev.app.enak.Presenter;

import java.io.File;

public interface PengaturanAkunPetugasPresenter {
    void ubahPassword(String id, String PasswodLama, String PasswordBaru, String nip);
    void ubahFoto(Integer id, File foto);
    void Relogin(String nip, String password);
    void getPetugas(String id);
}
