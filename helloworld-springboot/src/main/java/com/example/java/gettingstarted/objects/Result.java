package com.example.java.gettingstarted.objects;

import java.util.List;

public class Result<K> {
  private List<K> results;

  public List<K> getResults() {
    return results;
  }

  public Result(List<K> results) {
    this.results = results;
  }
}
