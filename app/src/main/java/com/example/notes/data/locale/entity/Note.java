//package com.example.notes.data.locale.entity;
//
//
//import androidx.room.Entity;
//import androidx.room.Ignore;
//import androidx.room.PrimaryKey;
//import androidx.room.TypeConverters;
//
//import com.example.notes.data.DataConverter;
//
//import java.util.Comparator;
//import java.util.Date;
//
//@Entity(tableName = "Notes")
//public class Note implements Comparator<Note> {
//
//    @PrimaryKey(autoGenerate = true)
//    private int id;
//    private String title;
//    private String description;
//    private int colorBackground = 0;
//    private int colorText = 0;
//    @TypeConverters(DataConverter.class)
//    private Date data;
//    private String nameGroup;
//
//    public Note() {
//
//    }
//
//    @Ignore
//    public Note(String title, String description) {
//        this.title = title;
//        this.description = description;
//    }
//
//
//    public Note(String title, String description, int colorBackground, int colorText, Date data) {
//
//        this.title = title;
//        this.description = description;
//        this.colorBackground = colorBackground;
//        this.colorText = colorText;
//        this.data = data;
//    }
//
//    public String getNameGroup() {
//        return nameGroup;
//    }
//
//    public void setNameGroup(String nameGroup) {
//        this.nameGroup = nameGroup;
//    }
//
//    public int getColorText() {
//        return colorText;
//    }
//
//    public void setColorText(int colorText) {
//        this.colorText = colorText;
//    }
//
//    public Date getData() {
//        return data;
//    }
//
//    public void setData(Date data) {
//        this.data = data;
//    }
//
//    public int getColorBackground() {
//        return colorBackground;
//    }
//
//    public void setColorBackground(int colorBackground) {
//        this.colorBackground = colorBackground;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    @Override
//    public int compare(Note o1, Note o2) {
//        return 0;
//    }
//}
