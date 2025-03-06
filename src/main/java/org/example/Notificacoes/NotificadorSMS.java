package org.example.Notificacoes;

public class NotificadorSMS implements Notificador {
    private String telefone;

    public NotificadorSMS(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public void notificar(String mensagem) {
        System.out.printf("Enviando SMS para %s: %s%n", telefone, mensagem);
    }
}
