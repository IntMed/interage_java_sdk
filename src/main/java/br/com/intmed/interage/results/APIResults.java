package br.com.intmed.interage.results;

import br.com.intmed.interage.resources.APIResource;

import java.util.List;


public abstract class APIResults<T extends APIResource> {
    private long count;
    private String next;
    private String previous;
    private List<T> results;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
