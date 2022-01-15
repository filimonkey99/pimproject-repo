package com.notes;

//DTO and entity table to map the record from api and database side
public class Group {
//    id for the record
    private Integer id;
//    Name of the group
    private  String groupName;

//    function to get the id
    public Integer getId() {
        return id;
    }
//  function to set the id
    public void setId(Integer id) {
        this.id = id;
    }

//function to get the groupName
    public String getGroupName() {
        return groupName;
    }
// function to set the groupName
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
// default constructor
    public Group() {
    }

//    parameterised constructor
    public Group(Integer id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

//    overriding the toString function
    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
