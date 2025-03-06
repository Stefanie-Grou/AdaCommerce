package org.example.Notificacoes;

public class NotificadorEmail implements Notificador {
    private String email;

    public NotificadorEmail(String email) {
        this.email = email;
    }

    @Override
    public void notificar(String mensagem) {
        System.out.printf("Enviando e-mail para %s: %s%n", email, mensagem);
    }
}
