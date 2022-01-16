package com.notes;

import java.util.Date;

public class Note {
//    id of the record
    private int id;
//    title of the note
    private String title;
//    content of the note
    private String text;
//    path of the photo
    private String path;
//    id of the group
    private int groupId;
//    is it check
    private boolean checked;
//    the data it is created
    private  String creationDate;
//    the date it is finished
    private String finishDate;

//    parameterized constructor
    public Note(int id, String title, String text, String path, int groupId, boolean checked, String creationDate, String finishDate) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.path = path;
        this.groupId = groupId;
        this.checked = checked;
        this.creationDate = creationDate;
        this.finishDate = finishDate;
    }

//    function to get the date creation of the node
    public String getCreationDate() {
        return creationDate;
    }

//    function to set the creation date of the note
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

//    function to get the id of the note
    public int getId() {
        return id;
    }

//    function to set the id of the note
    public void setId(int id) {
        this.id = id;
    }
//      function to get the title of the note
    public String getTitle() {
        return title;
    }
//        function to set the title of note
    public void setTitle(String title) {
        this.title = title;
    }
//  function to get the content of the note
    public String getText() {
        return text;
    }
//     function to set the text of the code
    public void setText(String text) {
        this.text = text;
    }
//   function to get the image path
    public String getPath() {
        return path;
    }
//      function to set the image path
    public void setPath(String path) {
        this.path = path;
    }
//      function to get the groupId
    public int getGroupId() {
        return groupId;
    }
//      function to set the groupId
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
//      function get the checked
    public boolean isChecked() {
        return checked;
    }
//      function to set the checked
    public void setChecked(boolean checked) {
        this.checked = checked;
    }




//      function to get the finish date of the note
    public String getFinishDate() {
        return finishDate;
    }
//      function to set the finnish date of the note
    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

//    default constructor
    public Note() {
    }

//    overriding the toString function
    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", path='" + path + '\'' +
                ", groupId=" + groupId +
                ", checked=" + checked +
                ", creationDate='" + creationDate + '\'' +
                ", finishDate='" + finishDate + '\'' +
                '}';
    }
}
