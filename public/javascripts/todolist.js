"use strict";

define(['jquery'], function($) {

    function updateToDoList() {
        console.log("ToDo List: Update!");
        var getTodosUrl = jsRoutes.controllers.ToDoController.getTodos();
        var getContent = $.get( getTodosUrl.url );

        getContent.done(function( data ) {
            data.forEach(function(val) {
                console.log(val);
            });
        });
    }

    function addTodoEntry( submitForm ) {
        var textForm = submitForm.find("#textToSubmit").val(); 

        // Send the data using post
        var addTodoUrl = jsRoutes.controllers.ToDoController.addTodo({done: false, text: textForm});
        var posting = $.post( addTodoUrl.url );
 
        // Put the results in a div
        posting.done(function( data ) {
            console.log("Receive on done: " + data);
            $('#insert-here').append("<tr><td>" + data.id + "</td><td><input type='checkbox'></td><td>" + data.text + "</td></tr>");
            // var content = $( data ).find( "#content" );
            // $( "#result" ).empty().append( content );
        });
    }

    return {
        updateToDoList: updateToDoList,
        addTodoEntry: addTodoEntry    
    }
});
