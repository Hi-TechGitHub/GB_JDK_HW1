package org.example.Chat.Model;


import java.time.LocalDateTime;
public class Message {
    private LocalDateTime date;
    private String login;
    private String text;

    public Message(LocalDateTime now, String text, String text1) {
        this.date = now;
        this.login = text;
        this.text = text1;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", date, login, text);
    }
}