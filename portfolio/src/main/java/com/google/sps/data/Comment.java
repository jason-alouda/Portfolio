package com.google.sps.data;

/** An item on a todo list. */
public final class Comment {

  private final String user_comment;
  private final long timestamp;
  private final long id;
  private final double sentiment;

  public Comment(long id, String user_comment, long timestamp, double sentiment) {
    this.id = id;
    this.user_comment = user_comment;
    this.timestamp = timestamp;
    this.sentiment = sentiment;
  }
}