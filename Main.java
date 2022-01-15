package com.notes;
import express.Express;
import java.nio.file.Paths;
import java.util.List;

/*
 Main class the controller class which is hit by the api first
 it contains the api which will connect the business logic and database with the frontend
 */
public class Main {
    public static void main(String[] args) {
        Express app=new Express();
        Database db=new Database();

//        this api wil get the add the group data in the database (POST-http://localhost:80/addGroup)
        app.post("/addGroup",(req,res)->{
//            creating the object of group class
            Group group = new Group();
//            inserting the group name from the request body and inserting it in the group class class
        group.setGroupName((String) req.body().get("groupName"));
//        calling the database class function addGroup
        db.addGroup(group);
        res.send(200);
        });

//        this api will get the all the record present in the database in the form of list (GET-http://localhost:80/getGroup)
        app.get("/getGroup",(req,res)->{
//            calling the database class function and getting the result in the list of group
           List<Group> groups= db.getGroups();
//           returning the result in json form
           res.json(groups);
        });

//        this api will insert the note data to the database (POST-http://localhost:80/addNotes)
        app.post("/addNotes",(req,res)->{
//            creating the new note object and insert the values in it and passing it to Database class function
            Note note=new Note();
            note.setTitle(req.body().get("title").toString());
            note.setText(req.body().get("text").toString());
            note.setPath("abc");
            note.setCreationDate(req.body().get("creationDate").toString());
            note.setChecked(Boolean.parseBoolean((String) req.body().get("checked")));
            note.setFinishDate(req.body().get("finishDate").toString());
            note.setGroupId(Integer.parseInt(String.valueOf(req.body().get("groupId"))));
//            calling the Database class function addNote
            db.addNote(note);
        });

//        function to get the list for the notes for  a paticular group id (GET:http://localhost:80/getNote/id)
        app.get("/getNote/:groupId",(req,res)->{
//            get the group id from the request
            int id= Integer.parseInt(req.params("groupId"));
//            call the db function to get the list note by passing the group id
            List<Note> notes=db.getNote(id);
//            return response of group to the backend by converting the list to json
            res.json(notes);
        });

        app.get("/delNote/:noteId",(req,res)->{
           int id= Integer.parseInt(req.params("noteId"));
           db.delNote(id);
           res.json(200);
        });

//      here we are telling the java express present the webpage when server is hit by the web-browser
        app.useStatic(Paths.get("src/www/"));

//        running the server
        app.listen(3000);
        System.out.println("Server started");
    }
}
