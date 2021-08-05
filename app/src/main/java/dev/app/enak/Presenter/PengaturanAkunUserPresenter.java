package dev.app.enak.Presenter;

import java.io.File;

public interface PengaturanAkunUserPresenter {
    void ubahPassword(String id, String PasswodLama, String PasswordBaru, String nik);
    void ubahFoto(Integer id, File foto);
    void ubahNama(Integer id, String nama_tampilan);
    void Relogin(String nik, String password);
    void getUser(String id);
}
