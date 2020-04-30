package com.emoemoemo.guitartrainer;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class note {
    char note;
    int fret, string;
    public Button clickButton;

    note(char note, int fret, int string){
        this.note = note;
        this.fret = fret;
        this.string = string;
    }

    Boolean is_note_right(int fret, int string)
    {
        if (this.fret == fret && this.string == string)
            return true;
        return false;
    }

    public char getNote() {
        return note;
    }

    public int getFret() {
        return fret;
    }

    public int getString() {
        return string;
    }
}
