package com.notes;

import com.fasterxml.jackson.core.JsonProcessingException;
import nosqlite.utilities.Utils;

import java.sql.*;
import java.util.List;


public class Database {
//    object of connection type
    private Connection conn;
//    constructor which will be called for connecting to the database when object is called
    public Database()
    {
        try {
//            url for connecting to the database
            conn= DriverManager.getConnection("jdbc:sqlite:express.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    function to add the group in th database
    public boolean addGroup(Group group)
    {
        try {
//            checking the group already exists in the database if yes then return false
            PreparedStatement checkQuery=conn.prepareStatement("SELECT id FROM groups WHERE groupName = ?");
            checkQuery.setString(1,group.getGroupName());
            ResultSet i=checkQuery.executeQuery();

            if(i.isClosed()) {
//                if the group is not present in the database then insert it into the database
                PreparedStatement stat = conn.prepareStatement("INSERT INTO groups VALUES (?,?)");
//                set the values for the values in the insert statement command
                stat.setNull(1, 0);
                stat.setString(2, group.getGroupName());
//                execute the command
                stat.executeUpdate();
//                return true because we are able to save the data
                return true;
            }

            return false;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    function to get the list of groups present in the database
    public List<Group> getGroups()
    {
        PreparedStatement query= null;
        List<Group> groups=null;

        try {
//            creating the query for getting the group records
            query = conn.prepareStatement("SELECT * FROM GROUPS");
//            executing the query and saving the result it in the resultSet
            ResultSet result=query.executeQuery();
//            converting the result to array type
            Group[] groupsArr= Utils.resultSetToObject(result,Group[].class);
//            converting the array type to list type and return it
            groups= List.of(groupsArr);
            return groups;
        } catch (SQLException | JsonProcessingException e) {
            e.printStackTrace();
        }

        return groups;
    }

//    function to add the note to database
    public boolean addNote(Note note)
    {
        try {
//            statement to insert note data to database
            PreparedStatement stat=conn.prepareStatement("INSERT INTO notes VALUES(?,?,?,?,?,?,?,?)");
//            setting the values for the insert columns
            stat.setNull(1,0);
            stat.setString(2,note.getTitle());
            stat.setString(3,note.getText());
            stat.setString(4,note.getPath());
            stat.setInt(5,note.getGroupId());
            stat.setBoolean(6,note.isChecked());
            stat.setString(7,note.getCreationDate());
            stat.setString(8,note.getFinishDate());
//            executing the query
            stat.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    function to get the list of notes to particular group id
    public List<Note> getNote(int id)
    {
        List<Note> notesList=null;
        try {
//            select query statement for get the list of notes for particular note
            PreparedStatement stat = conn.prepareStatement("SELECT * FROM notes WHERE groupId=(?)");
//            setting the 1st parameter
            stat.setInt(1,id);
//            executing the query and geting the result
            ResultSet result=stat.executeQuery();
//            converting the resultSet to array of note
            Note[] note= Utils.resultSetToObject(result,Note[].class);
//            converting the array of note to list of note
            notesList=List.of(note);
//            returning the list of note
            return notesList;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  void delNote(int id)
    {
        try {
            PreparedStatement stat=conn.prepareStatement("DELETE FROM notes WHERE id=(?)");
            stat.setInt(1,id);
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
