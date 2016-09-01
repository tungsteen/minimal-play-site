"use strict";

define(['jquery'], function($) {

    function addTodoEntry( submitForm ) {
        var textForm = submitForm.find("#textToSubmit").val(); 

        // Send the data using post
        var addTodoUrl = jsRoutes.controllers.ToDoController.addTodo({done: false, text: textForm});
        var posting = $.post( addTodoUrl.url );
 
        // Put the results in a div
        posting.done(function( data ) {
            $('#insert-here').append("<tr class='todo-row' entryId='" + data.id +"'>" + 
                "<td class='col-md-1'><input autocomplete='off' type='checkbox'></td>" + 
                "<td class='todo-text'>" + data.text + "</td>" + 
                "<td class='col-md-1'><span class='glyphicon glyphicon-remove delete-button'></span></td>" + 
                "</tr>");
        });
    }

    function doneTodoEntry(todoRow)
    {
        var entryId = todoRow.attr("entryId");
        var doneTodoUrl = jsRoutes.controllers.ToDoController.doneTodo(entryId);
        $.ajax({ 
            url: doneTodoUrl.url,
            type: 'PUT'
        }).done(function(isDone) {
            todoRow.find("input[type='checkbox']").prop("checked", isDone);
            todoRow.find("td.todo-text").css("text-decoration", isDone ? "line-through" : "none");
            todoRow.find("span.delete-button").css("visibility", isDone ? "visible" : "hidden");
        });
    }

    function deleteTodoEntry(clickedSpan)
    {
        var entryId = clickedSpan.closest("tr").attr("entryId");

        var deleteUrl = jsRoutes.controllers.ToDoController.deleteTodo(entryId);
        $.ajax({ 
            url: deleteUrl.url,
            type: 'DELETE',
            success: function() { clickedSpan.closest("tr").remove(); }
        });
    }

    return {
        addTodoEntry: addTodoEntry,
        doneTodoEntry: doneTodoEntry,
        deleteTodoEntry: deleteTodoEntry
    }
});
