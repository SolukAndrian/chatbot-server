package com.epam.chatbotserver.models;

public class Message {
  private String value;

  public Message() {
  }

  public Message(String value) {
    this.value = value + " bot.";
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}

