package ru.job4j.dream.store;

import org.junit.Test;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class DbStoreTest  {
    @Test
    public void whenCreatePost() {
        Store store = DbStore.instOf();
        Post post = new Post(0, "Java Job");
        store.savePost(post);
        Post postInDb = store.findByIdPost(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenCreateCandidate() {
        Store store = DbStore.instOf();
        Candidate candidate = new Candidate(0, "Alex");
        store.saveCandidate(candidate);
        Candidate candidateInDb = store.findByCandidate(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void deletePost() {
        Store store = DbStore.instOf();
        Post post = new Post(1, "Alex");
        store.savePost(post);
        store.deletePost(1);
        assertThat(store.findByIdPost(1), is(nullValue()));
    }
    @Test
    public void deleteCandidate() {
        Store store = DbStore.instOf();
        Candidate candidate = new Candidate(1, "Java");
        store.saveCandidate(candidate);
        store.deleteCandidate(1);
        assertThat(store.findByCandidate(1), is(nullValue()));
    }
   @Test
   public void updatePost() {
       Store store = DbStore.instOf();
       Post post = new Post(0, "Java ");
       store.savePost(post);
       post.setName("Java job4j");
       store.savePost(post);
       Post postInDb = store.findByIdPost(post.getId());
       assertThat(postInDb.getName(), is("Java job4j"));

    }
    @Test
    public void updateCandidate() {
        Store store = DbStore.instOf();
        Candidate candidate = new Candidate(0, "Alex1");
        store.saveCandidate(candidate);
        candidate.setName("Alex2");
        store.saveCandidate(candidate);
        Candidate candidate2 = store.findByCandidate(candidate.getId());
        assertThat(candidate2.getName(), is("Alex2"));

    }

    
}