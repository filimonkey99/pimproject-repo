var idGroupClicked=-1;
var actualImage=""
//function to send the data to backend for inserting the record in the database
async function addGroup(){
//get the element whose value we want to insert
    let element=document.getElementById("groupValue");

//    create the variabe for it
    let group={
        groupName:element.value
    }
// call the fetch function to call the backend
    let result=await fetch("/addGroup",{
        method:"POST",
        body:JSON.stringify(group)
 });
}

//function to get the record from the database for displaying the group
async function setGroupList()
{
//calling the backend for fetching the details
    let result=await fetch("/getGroup");
//    converting the result to json
    let groups=await result.json();
//    selecting the tag where we want to insert the data
    let sidenev=document.getElementById("groupNamesList");
    for(let i=0;i<groups.length;i++){
//    create the anchor tag in javascript
            let anchor=document.createElement("A");
//            set the content in it
            anchor.textContent=groups[i].groupName;
//            set the conclick function
            anchor.setAttribute('onclick','w3_open1();clicked(this.id);getNote()');
            anchor.id=groups[i].id;
            sidenev.appendChild(anchor);
//        console.log(groups[i].groupName)
    }
}

function getPhoto()
{
    let inFile=document.getElementById("myFile");
    const reader=new FileReader();
    let actualImage="";
    reader.onload=function (event) {
        actualImage = reader.result;
    
    };

    reader.readAsDataURL(inFile.files[0]);
    return actualImage;
}

//function to add the note to the backend
async function addNote()
{
//get the value for the postion
// document.getElementById("noteUploadForm").style.display = "none";
    let titleStr=$("#addTitle").val();
    let textStr=$("#addTxt").val();
    let FinishdateStr=$("#start").val();
    let today = new Date();
    let creationDateStr=new Date().toJSON().slice(0,10).replace(/-/g,'/');
    // getPhoto();
    // console.log(actualImage);




let note={
    title:titleStr,
    text:textStr,
    creationDate:creationDateStr,
    checked:"0",
    finishDate:FinishdateStr,
    groupId:idGroupClicked,

}
//call the fetch to call the backend
let result=await fetch("/addNotes",{
    method:"POST",
    body:JSON.stringify(note)
});

}