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

async function clicked(id){
    if(id!=idGroupClicked && idGroupClicked!=-1)
    {
        document.getElementById("noteUploadForm").style.display="none";
        $('.myNoteCardId').remove();
    }
    idGroupClicked=id;

    
}


// function to get the list of note
async function getNote()
{
   
    // getting the group id for which we want the list of note
    $('.myNoteCardId').remove();
    let groupd={
        groupId:idGroupClicked
    }
    // fetching the result from the backend
    let result=await fetch("/getNote/"+idGroupClicked);
    // converting the result to json
    let notes=await result.json();
    // creating empty variable for putting the html in it
    let html="";
    // getting the groupname
    let anchorval=document.getElementById(idGroupClicked).text;
    // getting the root note tag for the current tag
    let myNoteCard=document.getElementById("myNoteCard");
    for(let i=0;i<notes.length;i++)
    {
        html=`<div class="container my-3 myNoteCardId" >
        <div class="card"><br><br>
        	 <div class="notes-print-text">
             	 <P>${anchorval}</P>
             </div>
        	<div class="date"><br>
        		<label class="due-date" for="start"><b>Due date<b></label>
				<p>${notes[i].finishDate}</p>
        	</div>
        	
        
            <div class="card-body">
            	<h3 class="heading-text">
                   <b>${notes[i].title}</b> 
                </h3><br><br>
                <p style="text-align: center; " id="">
                	${notes[i].text}
            
                </p>
            <div class="clearfix">
        
 			</div>
           <br><br><br><br><br><br>
           <div>
           <button class="btn" style="position: absolute; right: 150px;" type="button" ><i class="fa fa-trash" id=${notes[i].id} onClick="del(this.id)"> Delete </i></button>
      </div>
           <br><br><br>
           </div>
           </div>`;      
        //    inserting the new component to the html
           myNoteCard.innerHTML+=html;  
    }
        // console.log(html)
}


async function del(id)
{
    let result=await fetch("/delNote/"+id);
    getNote();
}

