package com.laioffer.OOD.Playlist;

import java.util.Map;
import java.util.NoSuchElementException;

public class PlayList {

    static class Song {
        // String id; // playlist song id in case of song name is the same
        String name;
        String songTrackUrl; //can create class SongTrack if other formats
        String singer;
        String genre;
        int length; // in second
        Song next;
        Song prev;

        public Song(String name, String songTrackUrl) {
            this.name = name;
            this.songTrackUrl = songTrackUrl;
        }
        public void update(String newUrl) {
            songTrackUrl = newUrl;
        }
    }

    private static final int DEFAULT_CAPACITY = 100;
    private Song dummy; //
    private int size;
    private int cap;
    private Map<String, Song> map; // <key = songName, val = song>
    private Song currentPlaying;

    public PlayList(int cap) {
        if (cap <= 0 || cap > DEFAULT_CAPACITY) {
            throw new IllegalArgumentException("Capacity cannot <= 0 or > 100");
        }
        this.cap = cap;
        dummy = new Song(null, null);
        dummy.next = dummy; // dummy.next is the most recent song
        dummy.prev = dummy; // dummy.prev is the least recent song
        map = new HashMap<>();
    }
    // add song if not exist; update songTrackUrl if exist
    public void add(String name, String url) {
        Song song = map.get(name);
        if (song != null) {
            song.update(url); // update song
            // move to the most recent used side if considered most recent
            remove(song); // remove from list
            insert(song); // insert to the most recent used side
        } else {
            if (isFull()) { // remove least recent song first if full
                Song removed = remove(dummy.prev); // remove from list
                map.remove(removed.name); // remove from map
            }
            song = new Song(name, url); // create new record
            insert(song); // insert song to the most recent used
            map.put(name, song); // put song to map
        }
    }
    // insert song to the most recent used
    //    d <-> s3<->s1
    // dummy   s1 <-> s2   s3
    //   \______________/
    private void insert(Song song) {
        song.next = dummy.next;
        song.prev = dummy;
        dummy.next.prev = song;
        dummy.next = song;
    }
    // remove the song from list and return the removed song
    // dummy <-> s1 <- s2 -> s3   remove s2
    //    \___________________/
    private Song remove(Song song) {
        song.prev.next = song.next; // s1 -> s3
        song.next.prev = song.prev; // s1 <-> s3
        song.prev = null;
        song.next = null;
        return song;
    }
    public Song play(String name) {
        Song song = map.get(name);
        if (song == null) {
            throw new NoSuchElementException("The song dosen't exist in playlist");
        }
        currentPlaying = song;
        return song;
    }
    public void remove(String name) {
        Song song = map.get(name);
        if (song == null) {
            throw new NoSuchElementException("The song dosen't exist in playlist");
        }
        if (currentPlaying == song) {
            // IllegalThreadStateException extends IllegalArgumentException
            throw IllegalArgumentException("Cannot remove song that is currently playing");
        }
        remove(song);
    }
    public boolean isFull() { return size == cap; }

}
